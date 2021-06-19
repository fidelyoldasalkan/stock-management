package com.fid.demo.auth;

import com.fid.demo.service.IUserService;
import com.fid.demo.service.dto.SimpleUser;
import io.jsonwebtoken.ExpiredJwtException;
import javassist.tools.web.BadHttpRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final TokenManager tokenManager;
    private final IUserService userService;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String path = httpServletRequest.getRequestURI();

        if (path.startsWith("/auth/")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        final String authHeader = httpServletRequest.getHeader("Authorization");

        String token = null;
        String userName = null;

        if (authHeader == null) {
            throw new BadHttpRequest(new RuntimeException("Authorization is required"));
        }

//        try {
            if (authHeader.contains("Bearer")) {
                token = authHeader.substring(7);
                userName = tokenManager.getUserFromToken(token);
            }
// SignatureException
            // BadCredentialsException
//        ExpiredJwtException

            if (userName != null) {
                if (tokenManager.tokenValidate(token)) {
                    Integer id = userService.inquireUserByUsername(userName);
                    var u = new UsernamePasswordAuthenticationToken(new SimpleUser(id, userName), null, new ArrayList<>());
                    u.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.setContext(new SecurityContextImpl(u));
                }
            }
//        } catch (ExpiredJwtException e) {
//            httpServletResponse.getOutputStream().write("ExpiredJwtException".getBytes(StandardCharsets.UTF_8));
//        }


        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
