package com.kdx.problem11.settlement.dto;

import java.math.BigDecimal;

public record SettlementBalanceDto(Long sendMemberId, String sendMemberName, BigDecimal sendAmount,
                                   Long receiveMemberId, String receiveMemberName) {
}