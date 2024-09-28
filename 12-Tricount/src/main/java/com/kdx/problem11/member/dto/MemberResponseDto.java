package com.kdx.problem11.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kdx.problem11.settlement.dto.SettlementResponseDto;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MemberResponseDto(Long id, String name, String nickname, List<SettlementResponseDto> settlements) {
}
