package com.example.green_assets.repo;

import com.example.green_assets.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepo extends JpaRepository<Account, UUID> {
}
