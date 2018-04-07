package com.phonebook.service;

import com.phonebook.entity.UserEntity;
import com.phonebook.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim on 07.09.2017.
 */
@Service
public class AccountUserService implements UserDetailsService {

    private final AccountRepository accountRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public AccountUserService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = accountRepository.findByUsername(username);

        if(user != null){
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new User(
                    user.getUsername(),
                    user.getPassword(),
                    authorities);
        }

       throw new UsernameNotFoundException("UserEntity '" + username + "' not found" );
    }



}
