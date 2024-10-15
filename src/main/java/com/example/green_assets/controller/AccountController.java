package com.example.green_assets.controller;

import com.example.green_assets.config.UserWithAccount;
import com.example.green_assets.modelDTO.AccountDTO;
import com.example.green_assets.service.AccountService;
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
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    @GetMapping("/")
    public List<AccountDTO> getAuctions() {
        return accountService.getAllAccountsDTO(0,10);
    }
    @GetMapping("/contractors")
    public List<AccountDTO> getUserContractors(@AuthenticationPrincipal UserWithAccount user) {
        return accountService.getUserContractors(user.getAccount().getId());
    }
    @GetMapping("/{id}")
    public AccountDTO getAuction(@PathVariable UUID id) {
        return accountService.getAccountDTOById(id);
    }
    @PostMapping("/")
    public ResponseEntity<String> add(@RequestBody @Valid AccountDTO newAccount, BindingResult result) {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid data: " + result.getAllErrors());
        }
        accountService.add(newAccount);
        return ResponseEntity.ok("Successfully added");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody @Valid AccountDTO newAccount, BindingResult result) {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid data: " + result.getAllErrors());
        }
        accountService.update(id,newAccount);
        return ResponseEntity.ok("Successfully updated");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        accountService.delete(id);
        return ResponseEntity.ok("Successfully deleted");
    }
}
