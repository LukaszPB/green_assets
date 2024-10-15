package com.example.green_assets.validators;

import com.example.green_assets.model.Region;
import com.example.green_assets.repo.RegionRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class RegionExistValidator implements ConstraintValidator<ValidRegionExist, String> {
    @Autowired
    private RegionRepo regionRepo;

    @Override
    public boolean isValid(String regionName, ConstraintValidatorContext constraintValidatorContext) {
        Region region = regionRepo.findByName(regionName);
        return region != null;
    }
}
