package com.phonebook.aop;

import com.phonebook.service.AccountUserServiceI;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Created by Vadim on 04.11.2017.
 */
@Component
@Aspect
public class RegistrationControllerAspect {

    @Autowired
    AccountUserServiceI accountUserService;

    @AfterReturning(pointcut = "execution(* com.phonebook.controllers.PhoneBookController.deleteAccount(..))&& args(auth,..)")
    public void afterRegistrationController(Authentication auth){
        accountUserService.deleteAccount(auth.getName());
    }
}
