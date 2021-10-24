package com.usermanagement.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = UsernameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameConstraint {
    String message() default "Bu istifadəçi adı artıq mövcuddur.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}