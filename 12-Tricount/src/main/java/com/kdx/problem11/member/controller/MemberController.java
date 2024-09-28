package com.kdx.problem11.member.controller;

import com.kdx.problem11.member.domain.Member;
import com.kdx.problem11.member.dto.MemberRequestDto;
import com.kdx.problem11.member.dto.MemberResponseDto;
import com.kdx.problem11.member.repository.MemberRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     *
     * @param memberRequestDto
     * @return
     */
    @PostMapping
    public MemberResponseDto createMember(@RequestBody MemberRequestDto memberRequestDto) {
        Member member = Member.builder()
                .name(memberRequestDto.name())
                .nickname(memberRequestDto.nickname())
                .password(memberRequestDto.password())
                .build();

        return memberRepository.save(member).toDto();
    }
}
