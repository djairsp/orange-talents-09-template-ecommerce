package com.zup.mercadolivre.util.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    static final String AUTHORIZATION = "Authorization";
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private TokenService tokenService;
    private UsersService userService;

    public JwtAuthenticationFilter(TokenService tokenService, UsersService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Optional<String> token = getTokenFromRequest(request);
        if(token.isPresent() && tokenService.isValid(token.get())){
            String userName = tokenService.getUserName(token.get());
            UserDetails userDetails = userService.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private Optional<String> getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION);
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return Optional.ofNullable(null);
        }

        return Optional.ofNullable(token.substring(7, token.length()));
    }
}
