package com.example.green_assets.controller;

import com.example.green_assets.modelDTO.AuctionDTO;
import com.example.green_assets.modelDTO.ItemDTO;
import com.example.green_assets.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auction")
public class AuctionController {
    private final AuctionService auctionService;

    @GetMapping("/")
    public List<AuctionDTO> getAuctions() {
        return auctionService.getAllAuctionsDTO(0,10);
    }
    @GetMapping("/{id}")
    public AuctionDTO getAuction(@PathVariable UUID id) {
        return auctionService.getAuctionDTOById(id);
    }
    @GetMapping("/items/{id}")
    public List<ItemDTO> getItems(@PathVariable UUID id) {
        return auctionService.getItems(id);
    }
    @PostMapping("/")
    public ResponseEntity<String> add(@RequestBody AuctionDTO newAuction) {
        auctionService.add(newAuction);
        return ResponseEntity.ok("Successfully added");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody AuctionDTO newAuction) {
        auctionService.update(id,newAuction);
        return ResponseEntity.ok("Successfully updated");
    }
}