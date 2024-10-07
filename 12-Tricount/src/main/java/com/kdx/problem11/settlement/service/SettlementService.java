package com.kdx.problem11.settlement.service;

import com.kdx.problem11.expense.domain.Expense;
import com.kdx.problem11.expense.repository.ExpenseRepository;
import com.kdx.problem11.settlement.dao.BalanceDao;
import com.kdx.problem11.settlement.dto.SettlementBalanceDto;
import com.kdx.problem11.member.domain.Member;
import com.kdx.problem11.settlement.domain.Settlement;
import com.kdx.problem11.settlement.dto.SettlementRequestDto;
import com.kdx.problem11.settlement.dto.SettlementResponseDto;
import com.kdx.problem11.membersettlement.repository.MemberSettlementRepository;
import com.kdx.problem11.settlement.repository.SettlementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@AllArgsConstructor
public class SettlementService {
    private final SettlementRepository settlementRepository;
    private final MemberSettlementRepository memberSettlementRepository;
    private final ExpenseRepository expenseRepository;

    public SettlementResponseDto createAndJoin(SettlementRequestDto dto, Member member) {
        // todo: 가입이 완료되었을 때엔 어떤 응답을 보여주어야 할까?
        Settlement settlement = Settlement.builder().name(dto.name()).build();
        settlement = settlementRepository.save(settlement);

        memberSettlementRepository.save(member.getId(), settlement.getId());
        return settlement.toDto();
    }

    public List<SettlementResponseDto> searchByMember(Member member) {
        return settlementRepository.findAllByMemberId(member.getId()).stream().map(Settlement::toDto).toList();
    }

    public SettlementResponseDto searchById(Long settlementId) {
        Optional<Settlement> settlementOptional = settlementRepository.findById(settlementId);
        if (settlementOptional.isEmpty()) {
            throw new RuntimeException("해당 id를 가진 정산이 존재하지 않습니다.");
        }
        return settlementOptional.get().toDto();
    }

    public void remove(Long memberId, Long settlementId) {
        if (memberSettlementRepository.existByIdAndMemberId(memberId, settlementId)) {
            settlementRepository.delete(settlementId);
        }
    }

    public void join(Long memberId, Long settlementId) {
        // todo: 가입이 완료되었을 때엔 어떤 응답을 보여주어야 할까?

        if (settlementRepository.existById(settlementId)) {
            throw new RuntimeException("해당 id를 가진 정산이 이미 존재합니다.");
        }
        memberSettlementRepository.save(memberId, settlementId);
    }

    public List<SettlementBalanceDto> calculateBalance(Long settlementId) {
        List<BalanceDao> balances = expenseRepository.findAllExpenseBySettlementIdAndGroupByMemberId(settlementId);
        PriorityQueue<BalanceDao> receivers = new PriorityQueue<>(Comparator.comparing(BalanceDao::getAmount).reversed());
        PriorityQueue<BalanceDao> senders = new PriorityQueue<>(Comparator.comparing(BalanceDao::getAmount));
        balances.stream().forEach(balanceDao -> {
            if (balanceDao.getAmount().compareTo(BigDecimal.ZERO) < 0) {
                senders.add(balanceDao);
            } else {
                receivers.add(balanceDao);
            }
        });

        List<SettlementBalanceDto> result = new ArrayList<>();
        while(!receivers.isEmpty()) {
            BalanceDao receiver = receivers.peek();
            BigDecimal receiverAmount = receiver.getAmount();
            BalanceDao sender = senders.peek();
            BigDecimal senderAmount = sender.getAmount();
            if (receiverAmount.add(senderAmount).compareTo(BigDecimal.ZERO) > 0) {
                // 덜 송금받은 상태
                result.add(new SettlementBalanceDto(
                        sender.getMemberId(),
                        sender.getMemberName(),
                        senderAmount.abs(),
                        receiver.getMemberId(),
                        receiver.getMemberName()
                ));
                senders.poll();
                receiver.setAmount(receiverAmount.add(senderAmount));
            } else if (receiverAmount.add(senderAmount).compareTo(BigDecimal.ZERO) < 0) {
                // 다 송금받은 상태, receiver의 상태 확인 필요
                result.add(new SettlementBalanceDto(
                        sender.getMemberId(),
                        sender.getMemberName(),
                        receiverAmount,
                        receiver.getMemberId(),
                        receiver.getMemberName()
                ));
                receivers.poll();
                senders.poll();
                sender.setAmount(senderAmount.add(receiverAmount));
                senders.add(sender);
            } else {
                // 다 송금받았고, 보내는 사람의 금액과 받아야할 금액이 같은 경우
                result.add(new SettlementBalanceDto(
                        sender.getMemberId(),
                        sender.getMemberName(),
                        receiverAmount,
                        receiver.getMemberId(),
                        receiver.getMemberName()
                ));
                receivers.poll();
                senders.poll();
            }
        }

        return result;
    }
}