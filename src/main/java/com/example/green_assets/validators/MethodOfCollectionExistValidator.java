package com.example.green_assets.validators;

import com.example.green_assets.model.MethodOfCollection;
import com.example.green_assets.repo.MethodOfCollectionRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class MethodOfCollectionExistValidator implements ConstraintValidator<ValidMethodOfCollectionExist, String> {
    @Autowired
    private MethodOfCollectionRepo methodOfCollectionRepo;

    @Override
    public boolean isValid(String methodOfCollectionName, ConstraintValidatorContext constraintValidatorContext) {
        MethodOfCollection methodOfCollection = methodOfCollectionRepo.findByName(methodOfCollectionName);
        return methodOfCollection != null;
    }
}
