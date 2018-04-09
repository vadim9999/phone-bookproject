package com.phonebook.service;

import com.phonebook.entity.ContactEntity;
import com.phonebook.entity.UserEntity;
import com.phonebook.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vadim on 14.10.2017.
 */
@Service
@Transactional(readOnly = true)
public class AccountUserServiceImpl implements AccountUserServiceI {
    @Autowired
    private AccountRepository repo;

    /**
     * This method returns User
     * @param user
     * @return UserEntity
     */
    @Transactional
    public UserEntity save(UserEntity user){
        return repo.save(user);
    }


    @Override
    @Transactional
    public void deleteAccount(String username) {
    repo.deleteByUsername(username);
    }

    @Override
    public UserEntity getByName(String name) {
        return null;
    }


    @Override
    public UserEntity getByNameName(String name) {
        return null;
    }

    @Override
    public UserEntity editUser(UserEntity user) {

        return null;
    }

    @Override
    public List<UserEntity> getAll() {
        return null;
    }

    @Override
    @Transactional
    public UserEntity findByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    @Transactional
    public void deleteContactById(String username,Long id) {
        UserEntity user = repo.findByUsername(username);

        List<ContactEntity> contacts =  user.getPhoneBook();
        contacts.stream().filter(contact -> contact.getId() == id).forEach(contacts::remove);
        user.setPhoneBook(contacts);
        repo.save(user);
    }

//    @Override
//    public boolean findUsername(String username) {
//        return repo.findUsername(username);
//    }

//    @Override
//    public void deletePhoneBook(UserEntity user, List<ContactEntity> list) {
//        repo.deletePhoneBook(user,list);
//    }
//
//
//
//    @Override
//    public void deleteContact(UserEntity user, Long id) {
//        List<ContactEntity> contacts =  user.getPhoneBook();
//        for(ContactEntity contact : contacts){
//            if (contact.getId() == id){
//
//                repo.deleteContact(user,contact);
//            }
//        }

//    }
}
