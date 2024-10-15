package com.example.green_assets.controller;

import com.example.green_assets.config.UserWithAccount;
import com.example.green_assets.modelDTO.ItemDTO;
import com.example.green_assets.modelDTO.OfferDTO;
import com.example.green_assets.service.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
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
    @GetMapping("/by_user")
    public List<OfferDTO> getUserOffers(@AuthenticationPrincipal UserWithAccount user) {
        return offerService.getUserOffersDTO(user.getAccount().getId());
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
    public ResponseEntity<String> add(@RequestBody @Valid OfferDTO newOffer, BindingResult result) {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid data: " + result.getAllErrors());
        }
        offerService.add(newOffer);
        return ResponseEntity.ok("Successfully added");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody @Valid OfferDTO newOffer, BindingResult result) {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid data: " + result.getAllErrors());
        }
        offerService.update(id,newOffer);
        return ResponseEntity.ok("Successfully updated");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        offerService.delete(id);
        return ResponseEntity.ok("Successfully deleted");
    }
}