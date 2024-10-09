package com.example.green_assets.repo;

import com.example.green_assets.model.ItemOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemOfferRepo extends JpaRepository<ItemOffer, UUID> {
    List<ItemOffer> findByOfferId(UUID id);
}