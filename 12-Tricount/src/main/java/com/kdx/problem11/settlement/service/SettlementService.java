package com.kdx.problem11.settlement.service;

import com.kdx.problem11.member.domain.Member;
import com.kdx.problem11.member.repository.MemberRepository;
import com.kdx.problem11.membersettlement.domain.MemberSettlement;
import com.kdx.problem11.settlement.domain.Settlement;
import com.kdx.problem11.settlement.dto.SettlementRequestDto;
import com.kdx.problem11.settlement.dto.SettlementResponseDto;
import com.kdx.problem11.membersettlement.repository.MemberSettlementRepository;
import com.kdx.problem11.settlement.repository.SettlementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SettlementService {
    private final SettlementRepository settlementRepository;
    private final MemberRepository memberRepository;
    private final MemberSettlementRepository memberSettlementRepository;

    public SettlementResponseDto createAndJoin(SettlementRequestDto dto, Member member) {
        Settlement settlement = Settlement.builder().name(dto.name()).build();
        settlement = settlementRepository.save(settlement);

        memberSettlementRepository.save(member, settlement);
        return settlement.toDto();
    }

    public List<SettlementResponseDto> searchByMember(Member member) {
        return settlementRepository.findAllByMemberId(member.getId()).stream().map(Settlement::toDto).toList();
    }

    public SettlementResponseDto searchById(Long id) {
        Optional<Settlement> settlementOptional = settlementRepository.findById(id);
        if (settlementOptional.isEmpty()) {
            throw new RuntimeException("해당 id를 가진 정산이 존재하지 않습니다.");
        }
        return settlementOptional.get().toDto();
    }
}