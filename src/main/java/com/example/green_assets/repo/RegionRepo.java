package com.example.green_assets.repo;

import com.example.green_assets.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepo extends JpaRepository<Region,Long> {
    Region findByName(String name);
}
