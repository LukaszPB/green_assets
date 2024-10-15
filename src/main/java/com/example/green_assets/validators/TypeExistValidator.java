package com.example.green_assets.validators;

import com.example.green_assets.model.Type;
import com.example.green_assets.repo.TypeRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class TypeExistValidator implements ConstraintValidator<ValidTypeExist, String> {
    @Autowired
    private TypeRepo typeRepo;

    @Override
    public boolean isValid(String typeName, ConstraintValidatorContext constraintValidatorContext) {
        Type t = typeRepo.findByName(typeName);
        System.out.println(t);
        return t != null;
    }
}
