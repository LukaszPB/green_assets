package com.example.green_assets.controller;

import com.example.green_assets.config.UserWithAccount;
import com.example.green_assets.modelDTO.AuctionDTO;
import com.example.green_assets.modelDTO.BidRequest;
import com.example.green_assets.modelDTO.ItemDTO;
import com.example.green_assets.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @GetMapping("/by_user")
    public List<AuctionDTO> getUserAuctions(@AuthenticationPrincipal UserWithAccount user) {
        return auctionService.getUserAuctionsDTO(user.getAccount().getId());
    }
    @GetMapping("/{id}")
    public AuctionDTO getAuction(@PathVariable UUID id) {
        return auctionService.getAuctionDTOById(id);
    }
    @GetMapping("/items/{id}")
    public List<ItemDTO> getItems(@PathVariable UUID id) {
        return auctionService.getItems(id);
    }
    @GetMapping("/observed")
    public List<AuctionDTO> getObservedAuctions(@AuthenticationPrincipal UserWithAccount user) {
        return auctionService.getObservedAuctionsDTO(user.getAccount().getId());
    }
    @PostMapping("/observe/{id}")
    public ResponseEntity<String> observe(@PathVariable UUID id, @AuthenticationPrincipal UserWithAccount user) {
        auctionService.observe(user.getAccount().getId(), id);
        return ResponseEntity.ok("Successfully observed");
    }
    @DeleteMapping("/unobserve/{id}")
    public ResponseEntity<String> unobserve(@PathVariable UUID id, @AuthenticationPrincipal UserWithAccount user) {
        auctionService.unobserve(user.getAccount().getId(), id);
        return ResponseEntity.ok("Successfully unobserved");
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
    @PutMapping("/bid/{id}")
    public ResponseEntity<String> bid(
            @PathVariable UUID id,
            @RequestBody BidRequest bid,
            @AuthenticationPrincipal UserWithAccount user) {

        auctionService.bid(user.getAccount().getId(), id, bid);
        return ResponseEntity.ok("Successfully bid");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        auctionService.delete(id);
        return ResponseEntity.ok("Successfully deleted");
    }
}