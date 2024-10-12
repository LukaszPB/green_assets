package com.example.green_assets.config;

import com.example.green_assets.repo.AccountRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AccountRepo accountRepo;

    public SecurityConfig(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .addFilterBefore(new FakeAuthenticationFilter(accountRepo),
                        org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
}
