package com.maykon.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.maykon.workshopmongo.domain.User;
import com.maykon.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		User maykon = new User(null, "Maykon Ferreira", "maykon@gmail.com");
		User ana = new User(null, "Ana Ferreira", "ana@gmail.com");
				
		userRepository.saveAll(Arrays.asList(maria,alex,bob,maykon,ana));
		
		
		
		
		
	}

	
	
}
