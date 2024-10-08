package com.example.green_assets.service;

import com.example.green_assets.model.Item;
import com.example.green_assets.modelDTO.ItemDTO;
import com.example.green_assets.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepo itemRepo;
    private final TypeRepo typeRepo;
    private final AccountRepo accountRepo;
    public Item getItemById(UUID id) {
        return itemRepo.getReferenceById(id);
    }
    public ItemDTO getItemDTOById(UUID id) {
        return convertToDTO(getItemById(id));
    }
    public List<ItemDTO> getUserItemsDTO(UUID id) {
        List<Item> items = itemRepo.findByOwnerId(id);

        return items.stream().map(this::convertToDTO).toList();
    }
    public List<ItemDTO> getAllItemDTO(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        List<Item> items = itemRepo.findAll(pageable).getContent();

        return items.stream().map(this::convertToDTO).toList();
    }
    public void add(ItemDTO itemDTO) {
        Item item = Item.builder()
                .name(itemDTO.getName())
                .description(itemDTO.getDescription())
                .weight(itemDTO.getWeight())
                .quantity(itemDTO.getQuantity())
                .measurements(itemDTO.getMeasurements())
                .modifiable(itemDTO.getModifiable())
                .owner(accountRepo.getReferenceById(itemDTO.getAccountId()))
                .type(typeRepo.findByName(itemDTO.getName()))
                .build();
        itemRepo.save(item);
    }
    public void update(UUID id, ItemDTO itemDTO) {
        Item item = getItemById(id);

        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setWeight(itemDTO.getWeight());
        item.setQuantity(itemDTO.getQuantity());
        item.setMeasurements(itemDTO.getMeasurements());
        item.setModifiable(itemDTO.getModifiable());
        item.setOwner(accountRepo.getReferenceById(itemDTO.getAccountId()));
        item.setType(typeRepo.findByName(itemDTO.getName()));

        itemRepo.save(item);
    }
    public void delete(UUID id) {
        itemRepo.delete(getItemById(id));
    }
    public ItemDTO convertToDTO(Item item) {
        return ItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .weight(item.getWeight())
                .quantity(item.getQuantity())
                .measurements(item.getMeasurements())
                .modifiable(item.getModifiable())
                .accountId(item.getOwner().getId())
                .type(item.getType().getName())
                .build();
    }
}
