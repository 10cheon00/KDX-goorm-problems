package com.kdx.problem11.expense.dto;

import com.kdx.problem11.member.dto.MemberResponseDto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record ExpenseResponseDto(Long id, String name, MemberResponseDto member,
                                 BigDecimal amount, Timestamp createdAt) {
}
