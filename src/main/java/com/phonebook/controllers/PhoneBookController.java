package com.phonebook.controllers;


import com.phonebook.entity.ContactEntity;
import com.phonebook.entity.UserEntity;
import com.phonebook.service.AccountUserServiceI;
import com.phonebook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.NonUniqueResultException;
import javax.validation.Valid;

//import com.phonebook.repository.ContactEntityRepository;

/**
 * Created by Vadim on 31.08.2017.
 */
@Controller
public class PhoneBookController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "redirect:/user/edit";
    }

    @Autowired
    AccountUserServiceI accountUserService;

    @Autowired
    ContactService contactService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/user/register", method= RequestMethod.GET)

    public String registerForm(Model model){
        model.addAttribute("user", new UserEntity());
        return "registerForm";
    }

    @RequestMapping(value="/user/register", method= RequestMethod.POST)
    public String saveNewUser(
            @Valid @ModelAttribute(value = "user") UserEntity user, BindingResult result){

        if (result.hasErrors()){
            return "registerForm";
        }
            String username = user.getUsername();
            UserEntity userEntity;
            try{
               userEntity =  accountUserService.findByUsername(username);

            if (userEntity != null && userEntity.getUsername().equals(username)) {
                result.addError(new ObjectError("EqualsError", "Choose another username"));
                return "registerForm";
            }
            }catch (NullPointerException e){
                System.err.println(e.getMessage());
                return "redirect:/error";
            }catch (NonUniqueResultException exception){
                System.out.println(exception.getMessage());
                return "redirect:/error";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        accountUserService.save(user);
        return "redirect:/login";
    }

    @RequestMapping(value = "/user/edit", method = RequestMethod.GET)
    public String showUserProfile(
            Authentication authentication, Model model){
        UserEntity user = accountUserService.findByUsername(authentication.getName());

        model.addAttribute("user", user);
        model.addAttribute("phoneBook",user.getPhoneBook());
        model.addAttribute("message","From/user");
        model.addAttribute("displayBlock","none");
            model.addAttribute("contact", new ContactEntity());
        if(user.phoneBookIsEmpty()){
            model.addAttribute("phoneIsEmpty",true);
        }else{
            model.addAttribute("phoneIsEmpty",false);
        }
        return "edit";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/user/edit/add", method= RequestMethod.POST)
    public String addNewContact(@Valid @ModelAttribute(value = "contact") ContactEntity contact, BindingResult result,
                                Authentication authentication, Model model) {

        UserEntity userEntity = accountUserService.findByUsername(authentication.getName());
        if(result.hasErrors()) {

           model.addAttribute("message","From resultHasError");
           model.addAttribute("displayBlock","block");
            if(userEntity.phoneBookIsEmpty()) {
                model.addAttribute("phoneIsEmpty",true);
            }else
            {
                model.addAttribute("phoneEmpty",false);
            }

            model.addAttribute("user",userEntity);
            return "edit";
        }

        userEntity.setContact(contact);
        accountUserService.save(userEntity);
        return "redirect:/user/edit";

    }

    @RequestMapping(value = "/user/edit/change/{changeContact}",method = RequestMethod.GET)
    public String changeOneContact(@PathVariable String changeContact, Authentication authentication,
                                   Model model){
        UserEntity user = accountUserService.findByUsername(authentication.getName());
        Long id= Long.parseLong(changeContact);
        ContactEntity contactEntity = null;
        for(ContactEntity contact : user.getPhoneBook()){
            if (contact.getId() == id){
                contactEntity=contact;
            }
        }
                model.addAttribute("contact", contactEntity);
        model.addAttribute("changeContactId",changeContact);
        return "changeContact";
    }

    @RequestMapping(value = "/user/edit/save/{contactId}", method= RequestMethod.POST)
    public String saveChangedContact(@Valid @ModelAttribute(value = "contact") ContactEntity contact,
                                     @PathVariable String contactId,
                                     BindingResult result, Authentication authentication){
        UserEntity userEntity = accountUserService.findByUsername(authentication.getName());

        contact.setId(Long.parseLong(contactId));
        contactService.saveContact(contact);
        accountUserService.save(userEntity);
        return "redirect:/user/edit";
    }

    @RequestMapping(value = "/user/edit/delete/{removeId}",method = RequestMethod.GET)
    public String deleteOneContact(@PathVariable String removeId, Authentication authentication){

            UserEntity user = accountUserService.findByUsername(authentication.getName());
       Long id= Long.parseLong(removeId);
        ContactEntity contactEntity = null;
        for(ContactEntity contact : user.getPhoneBook()){
            if (contact.getId() == id){
                contactEntity=contact;
            }
        }
        user.removeContact(contactEntity);
        accountUserService.save(user);
        return "redirect:/user/edit";
    }

    @RequestMapping(value = "/user/settings",method = RequestMethod.GET)
    public String userSettings(Model model, Authentication auth){
        UserEntity user = accountUserService.findByUsername(auth.getName());
        model.addAttribute("userInfo", user);
        return "userSettings";
    }

    @RequestMapping(value="/user/settings/deleteAccount", method = RequestMethod.GET)
    public String deleteAccount(Authentication auth) {
    return "redirect:/logout";
    }

}
