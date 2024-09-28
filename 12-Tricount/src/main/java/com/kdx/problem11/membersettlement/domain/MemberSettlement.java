package com.kdx.problem11.membersettlement.domain;

import com.kdx.problem11.member.domain.Member;
import com.kdx.problem11.settlement.domain.Settlement;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSettlement {
    private Long id;
    private Member member;
    private Settlement settlement;
}
