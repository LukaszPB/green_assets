package com.example.green_assets.repo;

import com.example.green_assets.model.ItemAuction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemAuctionRepo extends JpaRepository<ItemAuction, UUID> {
    List<ItemAuction> findByAuctionId(UUID id);
}