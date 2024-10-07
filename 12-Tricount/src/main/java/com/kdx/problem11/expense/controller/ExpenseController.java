package com.kdx.problem11.expense.controller;

import com.kdx.problem11.auth.config.Authentication;
import com.kdx.problem11.expense.dto.ExpenseRequestDto;
import com.kdx.problem11.expense.dto.ExpenseResponseDto;
import com.kdx.problem11.expense.service.ExpenseService;
import com.kdx.problem11.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settlements/{settlementId}/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    /**
     * get expense list
     *
     * @return
     */
    @GetMapping
    public List<ExpenseResponseDto> getExpenses(@PathVariable("settlementId") Long settlementId) {
        return expenseService.searchExpensesBySettlementId(settlementId);
    }

    /**
     * add expense
     *
     * @param expenseRequestDto
     * @return
     */
    @PostMapping()
    public ExpenseResponseDto addExpense(@Authentication Member member,
            @PathVariable("settlementId") Long settlementId,
                                         @RequestBody ExpenseRequestDto expenseRequestDto) {
        return expenseService.create(settlementId, member, expenseRequestDto);
    }

    /**
     * delete expense
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteExpense(@Authentication Member member, @PathVariable("id") Long id) {
        expenseService.remove(member, id);
    }
}
