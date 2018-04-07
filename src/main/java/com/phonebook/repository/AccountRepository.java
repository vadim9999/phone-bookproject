package com.phonebook.repository;

import com.phonebook.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vadim on 31.08.2017.
 */
@Repository
public interface AccountRepository extends CrudRepository<UserEntity, Long> {

   public UserEntity findByUsername(String username);
    void deleteByUsername(String username);

}
