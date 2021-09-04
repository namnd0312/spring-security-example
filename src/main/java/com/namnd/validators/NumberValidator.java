package com.namnd.validators;


import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author itsol.hungtt on 12/25/2020
 * Copyright @DONG.NV
 */
public class NumberValidator implements ConstraintValidator<NumberConstraint, String> {
    @Override
    public void initialize(NumberConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(s)) {
            return true;
        }
        return NumberUtils.isCreatable(s);
    }
}
