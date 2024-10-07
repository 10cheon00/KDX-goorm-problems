package com.kdx.problem11.expense.service;

import com.kdx.problem11.expense.domain.Expense;
import com.kdx.problem11.expense.dto.ExpenseRequestDto;
import com.kdx.problem11.expense.dto.ExpenseResponseDto;
import com.kdx.problem11.expense.repository.ExpenseRepository;
import com.kdx.problem11.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public List<ExpenseResponseDto> searchExpensesBySettlementId(Long settlementId) {
        List<Expense> expenses = expenseRepository.findAllExpenseBySettlementId(settlementId);
        return expenses.stream().map(Expense::toDto).toList();
    }

    public ExpenseResponseDto create(Long settlementId, Member member, ExpenseRequestDto dto) {
        Expense expense = Expense.builder().name(dto.name()).member(member).amount(dto.amount()).build();
        return expenseRepository.save(settlementId, expense).toDto();
    }

    public void remove(Member member, Long expenseId) {
        if (expenseRepository.existByIdAndMemberId(expenseId, member.getId())) {
            expenseRepository.delete(expenseId);
        }
    }
}
