package com.phonebook.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Vadim on 12.11.2017.
 */
public class MobileConstraintValidator implements ConstraintValidator<MobileNumber, String> {
    private  List<String> operatorCodes = Arrays.asList("50", "95", "99",
            "66", "67", "97", "96", "98", "68", "63", "93", "73", "94", "92", "91");
    @Override
    public void initialize(MobileNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.length() > 18) {
            return false;
        }else{
            value = value.replaceAll(" ", "");
            return (((value.length() == 15) || (value.length() == 13) )&&(checkMobilePhone(value)))?true:false;
        }
    }
    public boolean checkMobilePhone(String str){
        //check valid mobile number for Ukraine code is +38
        if (str.contains("+380")) {
            str = str.substring(4);
            String operatorCode=null;
            if ((str.charAt(0) == '(' && str.charAt(3) == ')')) {
                operatorCode = String.copyValueOf(str.toCharArray(), 1, 2);
            } else {
                operatorCode = str.substring(0, 2);
            }
            if (operatorCodes.contains(operatorCode))
                return true;
            else
                return false;

        }else return false;
    }
}
