package com.example.green_assets.validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StartingDateValidator.class)
public @interface ValidStartingDate {
    String message() default "Starting date must be after current date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
