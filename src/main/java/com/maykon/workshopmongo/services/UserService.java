package com.maykon.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maykon.workshopmongo.domain.User;
import com.maykon.workshopmongo.repository.UserRepository;

@Service
public class UserService {

	@Autowired // Injeto a instacia
	private UserRepository repo;
	
	
	public List<User> findAll(){
		
		return repo.findAll();
		
	}
	
}
