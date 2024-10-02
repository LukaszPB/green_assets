package com.example.green_assets.repo;

import com.example.green_assets.model.MethodOfCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MethodOfCollectionRepo extends JpaRepository<MethodOfCollection, UUID> {
    MethodOfCollection findByName(String name);
}
