package com.phonebook.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Vadim on 19.01.2018.
 */
public class HomeConstraintValidator implements ConstraintValidator<HomeNumber,String> {

    @Override
    public void initialize(HomeNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("Value = " + value);
        if (value != null && (value.isEmpty() || value.length() == 13))
            return true; else return false;

    }
}
