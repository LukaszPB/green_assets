package com.example.green_assets.repo;

import com.example.green_assets.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TypeRepo extends JpaRepository<Type, UUID> {
    Type findByName(String name);
}
