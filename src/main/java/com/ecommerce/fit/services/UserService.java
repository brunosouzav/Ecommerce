package com.ecommerce.fit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.fit.exceptions.UserProblemException;
import com.ecommerce.fit.models.User;
import com.ecommerce.fit.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;
	
	public void createUser (User user) {
		userRepository.save(user);
	}
	
	public List<User> findAll() {
        return userRepository.findAll();
    }
	
	public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserProblemException("User not found with ID: " + id));
    }

	public Optional<User> findByEmail(String email) {
	     	Optional<User> user = userRepository.findByEmail(email);
	        return user;
	    }


	public void updateUser(Long id, User user) {
		User newUser = userRepository.findById(id)
		.orElseThrow(() -> new UserProblemException("Usuario não encontrado"));

		    
		    if (user.getPassword() != null) {
		        newUser.setPassword(user.getPassword());
		    }
		
		userRepository.save(newUser);
	}
	
	public void deleteUser (Long id) {
		User user =userRepository.findById(id)
		.orElseThrow(() -> new UserProblemException("Usuario não encontrado"));
		
		userRepository.delete(user);
		
	}
}
