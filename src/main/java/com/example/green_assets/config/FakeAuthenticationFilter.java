package com.example.green_assets.config;

import com.example.green_assets.repo.AccountRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
@AllArgsConstructor
public class FakeAuthenticationFilter extends OncePerRequestFilter {

    private AccountRepo accountRepo;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        UserWithAccount fakeUser = new UserWithAccount(
                "fakeuser@example.com",
                "password",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")),
                accountRepo.findAll().get(1)
        );

        // Tworzenie obiektu autentykacji
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(fakeUser, null, null);

        // Ustawienie użytkownika w SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kontynuacja przetwarzania żądania
        filterChain.doFilter(request, response);
    }
}