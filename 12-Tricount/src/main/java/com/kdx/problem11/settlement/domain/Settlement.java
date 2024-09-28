package com.kdx.problem11.settlement.domain;

import com.kdx.problem11.expense.domain.Expense;
import com.kdx.problem11.expense.dto.ExpenseResponseDto;
import com.kdx.problem11.settlement.dto.SettlementResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Settlement {
    private Long id;
    private String name;
    private List<Expense> expenses;

    public SettlementResponseDto toDto() {
        List<ExpenseResponseDto> expenseResponseDtos = null;
        if (expenses != null) {
            expenseResponseDtos = expenses.stream().map(Expense::toDto).toList();
        }
        return new SettlementResponseDto(id, name, expenseResponseDtos);
    }
}
