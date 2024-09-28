package com.kdx.problem11.auth.service;

import com.kdx.problem11.member.domain.Member;
import com.kdx.problem11.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Optional<Member> login(String name, String password) {
        return memberRepository.findByName(name).filter(m -> Objects.equals(m.getPassword(), password));
    }
}
