package com.phonebook.service;

import com.phonebook.entity.ContactEntity;
import com.phonebook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Vadim on 14.10.2017.
 */
@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public void delete(Long id){
        contactRepository.deleteById(id);
    }

    @Override
    public ContactEntity getContactById(Long id) {
        return  contactRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void saveContact(ContactEntity contactEntity) {
        contactRepository.save(contactEntity);
    }
}
