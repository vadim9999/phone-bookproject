package com.phonebook.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Vadim on 19.01.2018.
 */
public class UsernameConstraintValidator implements ConstraintValidator<Username, String> {
    @Override
    public void initialize(Username constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      return value.matches("^[a-zA-Z0-9]+$");
    }
}
