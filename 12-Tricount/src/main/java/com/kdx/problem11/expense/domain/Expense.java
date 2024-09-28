package com.kdx.problem11.expense.domain;

import com.kdx.problem11.expense.dto.ExpenseResponseDto;
import com.kdx.problem11.member.domain.Member;
import com.kdx.problem11.settlement.domain.Settlement;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    private Long id;
    private String name;
    private Member member; // one to one
    private BigDecimal amount;
    private Timestamp createdAt;

    public ExpenseResponseDto toDto() {
        return new ExpenseResponseDto(id, name, member.toDto(), amount, createdAt);
    }
}
