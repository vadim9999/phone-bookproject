package com.phonebook.service;

import com.phonebook.entity.ContactEntity;

/**
 * Created by Vadim on 14.10.2017.
 */
public interface ContactService {
    public void delete(Long id);
    public ContactEntity getContactById(Long id);
    public void saveContact(ContactEntity contactEntity);
}
