package com.example.green_assets.config;

import com.example.green_assets.model.Account;
import com.example.green_assets.repo.AccountRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AccountRepo accountRepo;

    public SecurityConfig(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    //@EventListener(ApplicationReadyEvent.class)
    @PostConstruct
    public void setupFakeUser() {
        UserWithAccount fakeUser = new UserWithAccount(
                "fakeuser@example.com",
                "password",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")),
            //    accountRepo.findAll().get(1)
                new Account()
        );

        //System.out.println(accountRepo.findAll().get(1).getName());

        Authentication auth = new FakeAuthentication(fakeUser);
        SecurityContextHolder.getContext().setAuthentication(auth);

        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        if (currentAuth != null && currentAuth.isAuthenticated()) {
            System.out.println("Fake user is authenticated: " + currentAuth.getName());
        } else {
            System.out.println("No authenticated user in the context.");
        }
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/by_user").authenticated()
                        .anyRequest().permitAll()
                )
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
    private static class FakeAuthentication implements Authentication {

        private final UserWithAccount user;
        private boolean authenticated = true;

        public FakeAuthentication(UserWithAccount user) {
            this.user = user;
        }
        @Override
        public Collection<GrantedAuthority> getAuthorities() {
            return user.getAuthorities();
        }

        @Override
        public Object getCredentials() {
            return user.getPassword();
        }

        @Override
        public Object getDetails() {
            return user;
        }

        @Override
        public Object getPrincipal() {
            return user;
        }

        @Override
        public boolean isAuthenticated() {
            return authenticated;
        }

        @Override
        public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
            this.authenticated = authenticated;
        }

        @Override
        public String getName() {
            return user.getUsername();
        }
        public Account getAccount() {
            return user.getAccount();
        }
    }
}
