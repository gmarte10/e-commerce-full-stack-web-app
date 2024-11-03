package com.giancarlos.config.filter;

import com.giancarlos.model.AccountDetails;
import com.giancarlos.service.AccountDetailsService;
import com.giancarlos.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// For every request you want the filter to be used once
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext applicationContext; // Used to get bean in spring framework

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*
        From client side you get from authorization header:
        Bearer[space][jwtToken]
        Ex:
        Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJvc3dhbGRjb2JibGVwb3RAb3V0bG9vay5jb20iLCJpYXQiOjE3MzA1OTk1NjQsImV4cCI6MTczMDU5OTY3Mn0.PSJCH46DeJP6uduoe6_2MDatv1lnJOoCkZTQHV6WaZ4
        */
        String authHeader = request.getHeader("Authorization"); // Only header you need
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Bearer[space] = index 0 - 6, token starts at index 7
            username = jwtService.extractUserName(token);
        }
        // Username should not be empty nor should it already be authenticated, Authentication should not be there by default
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = applicationContext.getBean(AccountDetailsService.class).loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken uPassAuthToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                uPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(uPassAuthToken); // You are adding the token in the filter chain
            }
        }
        filterChain.doFilter(request, response); // Go to the next filter
    }
}
