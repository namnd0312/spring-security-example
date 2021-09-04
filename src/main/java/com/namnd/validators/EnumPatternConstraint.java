package com.namnd.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author itsol.hungtt on 12/29/2020
 */
@Documented
@Constraint(validatedBy = EnumPatternValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface EnumPatternConstraint {
    String regexp();

    String message() default "Must macth \"{regexp}\"";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
