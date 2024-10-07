package com.kdx.problem11.settlement.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class BalanceDao {
    private Long memberId;
    private String memberName;
    private BigDecimal amount;
}
