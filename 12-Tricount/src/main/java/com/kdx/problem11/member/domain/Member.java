package com.kdx.problem11.member.domain;

import com.kdx.problem11.member.dto.MemberResponseDto;
import com.kdx.problem11.settlement.domain.Settlement;
import com.kdx.problem11.settlement.dto.SettlementResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long id;
    private String name;
    private String password;
    private String nickname;
    private List<Settlement> settlements;

    public MemberResponseDto toDto() {
        if (settlements != null) {
        List<SettlementResponseDto> dtos = settlements.stream().map(Settlement::toDto).toList();
            return new MemberResponseDto(id ,name, nickname, dtos);
        }
        return new MemberResponseDto(id ,name, nickname, null);
    }
}
