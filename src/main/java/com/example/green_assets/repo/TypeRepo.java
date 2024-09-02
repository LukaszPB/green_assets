package com.example.green_assets.repo;

import com.example.green_assets.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepo extends JpaRepository<Type,Long> {
    Type findByName(String name);
}
