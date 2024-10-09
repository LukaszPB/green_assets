package com.example.green_assets.controller;

import com.example.green_assets.modelDTO.ItemDTO;
import com.example.green_assets.modelDTO.OfferDTO;
import com.example.green_assets.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/offer")
public class OfferController {
    private final OfferService offerService;

    @GetMapping("/")
    public List<OfferDTO> getOffers() {
        return offerService.getAllOffersDTO(0,10);
    }
    @GetMapping("/{id}")
    public OfferDTO getOffer(@PathVariable UUID id) {
        return offerService.getOfferDTOById(id);
    }
    @GetMapping("/items/{id}")
    public List<ItemDTO> getItems(@PathVariable UUID id) {
        return offerService.getItems(id);
    }
    @PostMapping("/")
    public ResponseEntity<String> add(@RequestBody OfferDTO newOffer) {
        offerService.add(newOffer);
        return ResponseEntity.ok("Successfully added");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody OfferDTO newOffer) {
        offerService.update(id,newOffer);
        return ResponseEntity.ok("Successfully updated");
    }
}