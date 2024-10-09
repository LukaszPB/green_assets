package com.example.green_assets.repo;

import com.example.green_assets.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRepo extends JpaRepository<Offer, UUID> {
    List<Offer> findByClientId(UUID id);
}
