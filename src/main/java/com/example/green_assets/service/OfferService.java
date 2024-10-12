package com.example.green_assets.service;

import com.example.green_assets.model.ItemOffer;
import com.example.green_assets.model.Offer;
import com.example.green_assets.modelDTO.ItemDTO;
import com.example.green_assets.modelDTO.OfferDTO;
import com.example.green_assets.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepo offerRepo;
    private final AccountRepo accountRepo;
    private final MethodOfCollectionRepo methodOfCollectionRepo;
    private final RegionRepo regionRepo;
    private final ItemOfferRepo itemOfferRepo;
    private final ItemService itemService;
    public Offer getOfferById(UUID id) {
        return offerRepo.getReferenceById(id);
    }
    public OfferDTO getOfferDTOById(UUID id) {
        return convertToDTO(getOfferById(id));
    }
    public List<OfferDTO> getUserOffersDTO(UUID id) {
        return accountRepo
                .getReferenceById(id)
                .getPurchaseOffers()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    public List<OfferDTO> getAllOffersDTO(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return offerRepo
                .findAll(pageable)
                .getContent()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    public List<ItemDTO> getItems(UUID id) {
        return itemOfferRepo
                .findByOfferId(id)
                .stream()
                .map(ItemOffer::getItem)
                .map(itemService::convertToDTO)
                .toList();
    }
    public void add(OfferDTO offerDTO) {
        Offer offer = Offer.builder()
                .price(offerDTO.getPrice())
                .isAccepted(offerDTO.getIsAccepted())
                .pickupLocation(offerDTO.getPickupLocation())
                .description(offerDTO.getDescription())
                .client(accountRepo.getReferenceById(offerDTO.getClientId()))
                .methodOfCollection(methodOfCollectionRepo.findByName(offerDTO.getMethodOfCollection()))
                .region(regionRepo.findByName(offerDTO.getRegion()))
                .build();
        offerRepo.save(offer);
    }
    public void update(UUID id, OfferDTO offerDTO) {
        Offer offer = getOfferById(id);

        offer.setPrice(offerDTO.getPrice());
        offer.setIsAccepted(offerDTO.getIsAccepted());
        offer.setPickupLocation(offerDTO.getPickupLocation());
        offer.setDescription(offerDTO.getDescription());
        offer.setClient(accountRepo.getReferenceById(offerDTO.getClientId()));
        offer.setMethodOfCollection(methodOfCollectionRepo.findByName(offerDTO.getMethodOfCollection()));
        offer.setRegion(regionRepo.findByName(offerDTO.getRegion()));
    }
    public void delete(UUID id) {
        offerRepo.delete(getOfferById(id));
    }
    public OfferDTO convertToDTO(Offer offer) {
        return OfferDTO.builder()
                .id(offer.getId())
                .price(offer.getPrice())
                .isAccepted(offer.getIsAccepted())
                .pickupLocation(offer.getPickupLocation())
                .description(offer.getDescription())
                .clientId(offer.getClient().getId())
                .methodOfCollection(offer.getMethodOfCollection().getName())
                .region(offer.getRegion().getName())
                .build();
    }
}
