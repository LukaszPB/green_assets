package com.example.green_assets.service;

import com.example.green_assets.model.Account;
import com.example.green_assets.model.Auction;
import com.example.green_assets.model.ItemAuction;
import com.example.green_assets.modelDTO.AuctionDTO;
import com.example.green_assets.modelDTO.BidRequest;
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
public class AuctionService {
    private final AuctionRepo auctionRepo;
    private final AccountRepo accountRepo;
    private final MethodOfCollectionRepo methodOfCollectionRepo;
    private final RegionRepo regionRepo;
    private final ItemAuctionRepo itemAuctionRepo;
    private final ItemService itemService;
    public Auction getAuctionById(UUID id) {
        return auctionRepo.getReferenceById(id);
    }
    public AuctionDTO getAuctionDTOById(UUID id) {
        return convertToDTO(getAuctionById(id));
    }
    public List<AuctionDTO> getUserAuctionsDTO(UUID id) {
        return accountRepo.getReferenceById(id)
                .getWinningAuctions()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    public List<AuctionDTO> getAllAuctionsDTO(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return auctionRepo
                .findAll(pageable)
                .getContent()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    public List<ItemDTO> getItems(UUID id) {
        return itemAuctionRepo
                .findByAuctionId(id)
                .stream()
                .map(ItemAuction::getItem)
                .map(itemService::convertToDTO)
                .toList();
    }
    public List<AuctionDTO> getObservedAuctionsDTO(UUID id) {
        return accountRepo
                .getReferenceById(id)
                .getObservedAuctions()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    public void observe(UUID account_id, UUID auction_id) {
        Account account = accountRepo.getReferenceById(account_id);
        account.getObservedAuctions().add(auctionRepo.getReferenceById(auction_id));
        accountRepo.save(account);
    }
    public void unobserve(UUID account_id, UUID auction_id) {
        Account account = accountRepo.getReferenceById(account_id);
        account.getObservedAuctions().remove(getAuctionById(auction_id));
        accountRepo.save(account);
    }
    public void bid(UUID account_id, UUID auction_id, BidRequest bid) {
        Auction auction = getAuctionById(auction_id);

        if(auction.getBid().compareTo(bid.getAmount()) > 0)
            return;

        auction.setBid(bid.getAmount());
        auction.setWinningAccount(accountRepo.getReferenceById(account_id));
        auctionRepo.save(auction);
    }
    public void add(AuctionDTO auctionDTO) {
        Auction auction = Auction.builder()
                .bid(auctionDTO.getBid())
                .startDate(auctionDTO.getStartDate())
                .endDate(auctionDTO.getEndDate())
                .hasEnded(auctionDTO.getHasEnded())
                .pickupLocation(auctionDTO.getPickupLocation())
                .description(auctionDTO.getDescription())
                .winningAccount(accountRepo.getReferenceById(auctionDTO.getWinningAccountId()))
                .methodOfCollection(methodOfCollectionRepo.findByName(auctionDTO.getMethodOfCollection()))
                .region(regionRepo.findByName(auctionDTO.getRegion()))
                .build();
        auctionRepo.save(auction);
    }
    public void update(UUID id, AuctionDTO auctionDTO) {
        Auction auction = getAuctionById(id);

        auction.setBid(auctionDTO.getBid());
        auction.setStartDate(auctionDTO.getStartDate());
        auction.setEndDate(auction.getEndDate());
        auction.setHasEnded(auctionDTO.getHasEnded());
        auction.setPickupLocation(auctionDTO.getPickupLocation());
        auction.setDescription(auctionDTO.getDescription());
        auction.setWinningAccount(accountRepo.getReferenceById(auctionDTO.getWinningAccountId()));
        auction.setMethodOfCollection(methodOfCollectionRepo.findByName(auctionDTO.getMethodOfCollection()));
        auction.setRegion(regionRepo.findByName(auctionDTO.getRegion()));
    }
    public void delete(UUID id) {
        auctionRepo.delete(getAuctionById(id));
    }
    public AuctionDTO convertToDTO(Auction auction) {
        return AuctionDTO.builder()
                .id(auction.getId())
                .bid(auction.getBid())
                .startDate(auction.getStartDate())
                .endDate(auction.getEndDate())
                .hasEnded(auction.getHasEnded())
                .pickupLocation(auction.getPickupLocation())
                .description(auction.getDescription())
                .winningAccountId(auction.getWinningAccount().getId())
                .methodOfCollection(auction.getMethodOfCollection().getName())
                .region(auction.getRegion().getName())
                .build();
    }
}