package com.nexgen.sanjeevani.hospital_managment.service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    // This is used for jwt related processing
    private final JwtService jwtService;

    // This is required to fetch user details from token extracted username
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        log.info("We are in the jwt filter");
        String authorization = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (authorization != null && authorization.startsWith("Bearer ")) {
            jwtToken = authorization.substring(7);
            username = jwtService.extractUserName(jwtToken);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Here we are getting the user details from repo
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            if (jwtService.validateToken(jwtToken, userDetails)) {
                log.info("We have successfully passed the validation");

                // Here we are creating spring security token without credentials
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                // We are storing the currently authenticated user in context holder.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
