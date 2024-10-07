package com.kdx.problem11.expense.dto;

import java.math.BigDecimal;

public record ExpenseRequestDto(String name, BigDecimal amount) {
}
