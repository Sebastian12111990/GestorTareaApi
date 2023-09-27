package com.GestorTareaApi.app.security;

import java.io.IOException;

import java.util.Objects;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @Autowired
    private JwtTokenService jwtService;

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String AUTHORIZATION_HEADER_BEARER = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {

        final var requestTokenHeader =  request.getHeader(AUTHORIZATION_HEADER);

        String username = null;

        String jwt = null;

        if(Objects.nonNull(requestTokenHeader) && requestTokenHeader.startsWith(AUTHORIZATION_HEADER_BEARER)) {
            jwt = requestTokenHeader.substring(7);

            try {
                username = jwtService.getUsernameFromJWT(jwt);
            }catch(IllegalArgumentException ex) {
                ex.printStackTrace();
            }catch(ExpiredJwtException ex){
                ex.printStackTrace();
            }catch(SignatureException ex){

            }
        }

        if(Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {

            final var userDetails = this.jwtUserDetailService.loadUserByUsername(username);

            if(this.jwtService.validateToken(jwt, userDetails)) {

                var usernameAndPasswordAuthToken = new UsernamePasswordAuthenticationToken(userDetails , null , null);

                usernameAndPasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernameAndPasswordAuthToken);
            }

        }

        filterChain.doFilter(request, response);


    }

}
