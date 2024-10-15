package com.example.green_assets.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegionExistValidator.class)
public @interface ValidRegionExist {
    String message() default "Region with given name does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
