package com.example.validation.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

    // Your custom logic (course code must be prefixed with LUV)
    public String value() default "LUV";

    // What message to show when validation fails
    public String message() default "must start with LUV";

    // Group constraints for conditional validation
    public Class<?>[] groups() default {};

    // Attach custom metadata
    public Class<? extends Payload>[] payload() default{}; 
}
