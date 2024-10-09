package com.example.green_assets.service;

import com.example.green_assets.model.Account;
import com.example.green_assets.modelDTO.AccountDTO;
import com.example.green_assets.repo.AccountRepo;
import com.example.green_assets.repo.RegionRepo;
import com.example.green_assets.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepo accountRepo;
    private final RegionRepo regionRepo;
    private final RoleRepo roleRepo;
    private final ItemService itemService;
    public Account getAccountById(UUID id) {
        return accountRepo.getReferenceById(id);
    }
    public AccountDTO getAccountDTOById(UUID id) {
        return convertToDTO(getAccountById(id));
    }
    public List<AccountDTO> getAllAccountsDTO(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        List<Account> accounts = accountRepo.findAll(pageable).getContent();

        return accounts.stream().map(this::convertToDTO).toList();
    }
    public void add(AccountDTO accountDTO) {
        Account account = Account.builder()
                .name(accountDTO.getName())
                .nip(accountDTO.getNip())
                .phoneNumber(accountDTO.getPhoneNumber())
                .email(accountDTO.getEmail())
                .postalCode(accountDTO.getPostalCode())
                .city(accountDTO.getCity())
                .street(accountDTO.getStreet())
                .region(regionRepo.findByName(accountDTO.getRegion()))
                .role(roleRepo.findByName(accountDTO.getRole()))
                .build();
        accountRepo.save(account);
    }
    public void update(UUID id, AccountDTO accountDTO) {
        Account account = getAccountById(id);

        account.setName(accountDTO.getName());
        account.setNip(accountDTO.getNip());
        account.setPhoneNumber(accountDTO.getPhoneNumber());
        account.setEmail(accountDTO.getEmail());
        account.setPostalCode(accountDTO.getPostalCode());
        account.setCity(accountDTO.getCity());
        account.setStreet(accountDTO.getStreet());
        account.setRegion(regionRepo.findByName(accountDTO.getRegion()));
        account.setRole(roleRepo.findByName(accountDTO.getRole()));
    }
    public void delete(UUID id) {
        accountRepo.delete(getAccountById(id));
    }
    public AccountDTO convertToDTO(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .name(account.getName())
                .nip(account.getNip())
                .phoneNumber(account.getPhoneNumber())
                .email(account.getEmail())
                .postalCode(account.getPostalCode())
                .city(account.getCity())
                .street(account.getStreet())
                .region(account.getRegion().getName())
                .role(account.getRole().getName())
                .build();
    }
}
