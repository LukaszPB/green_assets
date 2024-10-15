package com.example.green_assets.validators;

import com.example.green_assets.modelDTO.AuctionDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DatesOrderValidator implements ConstraintValidator<ValidDatesOrder, AuctionDTO> {
    @Override
    public boolean isValid(AuctionDTO auction, ConstraintValidatorContext context) {
        return auction.getStartDate().isBefore(auction.getEndDate());
    }
}