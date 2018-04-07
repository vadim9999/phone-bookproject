package com.phonebook.service;

import com.phonebook.entity.UserEntity;

import java.util.List;

/**
 * Created by Vadim on 14.10.2017.
 */
public interface AccountUserServiceI {
    /**
     * This method save an User
     * @param user
     * @return UserEntity
     */
    UserEntity save(UserEntity user);
    void deleteAccount(String username);
    UserEntity getByName(String name);
    UserEntity getByNameName(String name);
    UserEntity editUser(UserEntity user);
    List<UserEntity> getAll();
    UserEntity findByUsername(String username);

    void deleteContactById(String username, Long id);
//    boolean findUsername(String username);
//    void deletePhoneBook(UserEntity user,List<ContactEntity> list);
//    void deleteContact(UserEntity user,Long id);
}
