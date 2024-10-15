package com.example.green_assets.validators;

import com.example.green_assets.model.Account;
import com.example.green_assets.repo.AccountRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class AccountExistValidator implements ConstraintValidator<ValidAccountExist, UUID> {
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public boolean isValid(UUID id, ConstraintValidatorContext constraintValidatorContext) {
        Account account = accountRepo.findById(id).orElse(null);
        return account != null;
    }
}
