package com.kdx.problem11.expense.controller;

import com.kdx.problem11.expense.dto.ExpenseRequestDto;
import com.kdx.problem11.expense.dto.ExpenseResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    /**
     * get expense list
     *
     * @return
     */
    @GetMapping
    public List<ExpenseResponseDto> getExpenses() {
        return null;
    }

    /**
     * add expense
     *
     * @param expenseRequestDto
     * @return
     */
    @PostMapping("/{id}")
    public ExpenseResponseDto addExpense(@PathVariable("id") Long settlementId, @RequestBody ExpenseRequestDto expenseRequestDto) {
        return null;
    }

    /**
     * delete expense
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable("id") Long id) {
    }
}
