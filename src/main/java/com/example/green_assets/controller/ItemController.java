package com.example.green_assets.controller;

import com.example.green_assets.config.UserWithAccount;
import com.example.green_assets.modelDTO.ItemDTO;
import com.example.green_assets.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/")
    public List<ItemDTO> getItems() {
        return itemService.getAllItemDTO(0,10);
    }
    @GetMapping("/by_user")
    public List<ItemDTO> getUserItems(@AuthenticationPrincipal UserWithAccount user) {
        return itemService.getUserItemsDTO(user.getAccount().getId());
    }
    @GetMapping("/{id}")
    public ItemDTO getItem(@PathVariable UUID id) {
        return itemService.getItemDTOById(id);
    }
    @PostMapping("/")
    public ResponseEntity<String> addItem(@RequestBody ItemDTO newItem) {
        itemService.add(newItem);
        return ResponseEntity.ok("Successfully added");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@PathVariable UUID id, @RequestBody ItemDTO newItem) {
        itemService.update(id,newItem);
        return ResponseEntity.ok("Successfully updated");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        itemService.delete(id);
        return ResponseEntity.ok("Successfully deleted");
    }
}
