package com.example.green_assets.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class StartingDateValidator  implements ConstraintValidator<ValidStartingDate, LocalDateTime> {
    @Override
    public boolean isValid(LocalDateTime date, ConstraintValidatorContext constraintValidatorContext) {
        return date.isAfter(LocalDateTime.now());
    }
}
