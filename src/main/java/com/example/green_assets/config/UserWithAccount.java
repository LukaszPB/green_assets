package com.example.green_assets.config;

import com.example.green_assets.model.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Setter
@Getter
public class UserWithAccount extends User {

    private Account account;

    public UserWithAccount(String username, String password, Collection<? extends GrantedAuthority> authorities, Account account) {
        super(username, password, authorities);
        this.account = account;
    }
}