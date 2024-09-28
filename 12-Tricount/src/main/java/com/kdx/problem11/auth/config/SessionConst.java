package com.kdx.problem11.auth.config;

import java.util.List;

public class SessionConst {
    public static final String LOGIN_MEMBER = "member";
    public static final List<String> WHITELIST = List.of("/auth/**", "/auth/**", "/members");
}
