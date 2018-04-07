package com.phonebook.repository;

import com.phonebook.entity.ContactEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Vadim on 14.10.2017.
 */
public interface ContactRepository extends CrudRepository<ContactEntity,Long> {

}
