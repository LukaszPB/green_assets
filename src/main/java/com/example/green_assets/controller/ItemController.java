package com.example.green_assets.controller;

import com.example.green_assets.modelDTO.ItemDTO;
import com.example.green_assets.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/{id}")
    public ItemDTO getItems(@PathVariable UUID id) {
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
}
