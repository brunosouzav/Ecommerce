package com.ecommerce.fit.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.fit.models.User;
import com.ecommerce.fit.services.UserService;


@RestController
@RequestMapping("/api/users")
public class UsersController {

	@Autowired
    private UserService userService;
	
	@PostMapping("/create")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
       
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
	}
    
	@GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
    	return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable String email) {
    	return ResponseEntity.status(HttpStatus.OK).body(userService.findByEmail(email));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
       
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  
   }
}
