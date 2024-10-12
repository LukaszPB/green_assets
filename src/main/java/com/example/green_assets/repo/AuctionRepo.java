package com.example.green_assets.repo;

import com.example.green_assets.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuctionRepo extends JpaRepository<Auction, UUID> {
}
