package com.phonebook;

import com.phonebook.config.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhoneBookProjectApp {

	public static void main(String[] args) {
		SpringApplication.run(PhoneBookProjectApp.class, args);
	}


}
