package com.kdx.problem11.auth.controller;

import com.kdx.problem11.auth.config.SessionConst;
import com.kdx.problem11.auth.dto.CredentialDto;
import com.kdx.problem11.auth.service.LoginService;
import com.kdx.problem11.member.domain.Member;
import com.kdx.problem11.member.dto.MemberResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public void login(HttpServletRequest httpServletRequest, @RequestBody CredentialDto credentialDto) {
        loginService.login(credentialDto.name(), credentialDto.password()).ifPresentOrElse(member -> {
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute(SessionConst.LOGIN_MEMBER, member);
        }, () -> {
            throw new RuntimeException("Wrong credential");
        });
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        } else {
            throw new RuntimeException("Session is null");
        }
    }
}
