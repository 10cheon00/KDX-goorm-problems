package com.kdx.problem11.settlement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kdx.problem11.expense.dto.ExpenseResponseDto;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SettlementResponseDto(Long id, String name, List<ExpenseResponseDto> expenses) {
}
