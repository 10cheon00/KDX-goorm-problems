package com.kdx.problem11.auth.filter;

import com.kdx.problem11.auth.config.SessionConst;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class AuthenticationFilter extends OncePerRequestFilter {
    private final static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    private final List<String> whitelist = List.of("/auth", "/auth/login", "/auth/logout");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            logger.info("Authentication start.");

            String requestUri = request.getRequestURI();
            if (!isWhiteListUrl(requestUri)) {
                logger.info("Request URI '{}' is not included in whitelist.", requestUri);

                HttpSession session = request.getSession(false);

                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    logger.info("Authentication fail.");
                    return;
                }
            } else {
                logger.info("Request URI '{}' is whitelisted.", requestUri);
            }

            logger.info("Authentication success.");
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            logger.info("Authentication end for request uri '{}'.", request.getRequestURI());
        }
    }

    private boolean isWhiteListUrl(String requestUri) {
        return whitelist.contains(requestUri);
    }
}
