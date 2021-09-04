package com.namnd.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author itsol.hungtt on 12/25/2020
 * Copyright @DONG.NV
 */
@Documented
@Constraint(validatedBy = NumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberConstraint {
    String message() default "Invalid number format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
