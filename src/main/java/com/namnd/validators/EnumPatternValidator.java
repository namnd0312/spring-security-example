package com.namnd.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author itsol.hungtt on 12/29/2020
 */
public class EnumPatternValidator implements ConstraintValidator<EnumPatternConstraint, String> {

    private Pattern pattern;

    @Override
    public void initialize(EnumPatternConstraint constraintAnnotation) {
        try {
            pattern = Pattern.compile(constraintAnnotation.regexp());
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Given regex is invalid", e);
        }
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return false;
        }
        Matcher m = pattern.matcher(s);
        return m.matches();
    }
}
