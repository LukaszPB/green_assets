package com.example.green_assets.controller;

import com.example.green_assets.modelDTO.AccountDTO;
import com.example.green_assets.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    @GetMapping("/")
    public List<AccountDTO> getAuctions() {
        return accountService.getAllAccountsDTO(0,10);
    }
    @GetMapping("/{id}")
    public AccountDTO getAuction(@PathVariable UUID id) {
        return accountService.getAccountDTOById(id);
    }
    @PostMapping("/")
    public ResponseEntity<String> add(@RequestBody AccountDTO newAccount) {
        accountService.add(newAccount);
        return ResponseEntity.ok("Successfully added");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody AccountDTO newAccount) {
        accountService.update(id,newAccount);
        return ResponseEntity.ok("Successfully updated");
    }
}
