package com.example.UMC.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PageValidator.class)
public @interface ValidPage {
    String message() default "page must be greater than 0";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
