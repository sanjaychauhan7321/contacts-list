package com.kuehne.nagel.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactListApplication {

	@Autowired
	public DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(ContactListApplication.class, args);
	}
}
