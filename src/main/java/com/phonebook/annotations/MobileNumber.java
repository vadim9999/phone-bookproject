package com.phonebook.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Vadim on 19.11.2017.
 */
@Constraint(validatedBy = MobileConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MobileNumber {
    String message() default "Phone";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
