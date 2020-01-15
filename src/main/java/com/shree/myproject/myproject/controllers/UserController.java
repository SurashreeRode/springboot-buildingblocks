package com.shree.myproject.myproject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;


import com.shree.myproject.myproject.entities.User;
import com.shree.myproject.myproject.exceptions.UserExistsException;
import com.shree.myproject.myproject.exceptions.UserNotFound;
import com.shree.myproject.myproject.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	// getAllUsers Method
	@GetMapping("/users")
	public List<User> getAllUsers() {

		return userService.getAllUsers();

	}
	// Create User Method
		// @RequestBody Annotation
		// @PostMapping Annotation
		@PostMapping("/users")
		public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder) {
			try {
				userService.createUser(user);
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
				return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
				
			} catch(UserExistsException ex) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
			}
		}
		

		// getUserById
		@GetMapping("/users/{id}")
		public Optional<User> getUserById(@PathVariable("id") Long id) {

			try {
				return userService.getUserById(id);
			} catch (UserNotFound ex) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			}

		}

		// updateUserById
		@PutMapping("/users/{id}")
		public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {

			try {
				return userService.updateUserById(id, user);
			} catch (UserNotFound ex) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
			}

		}

		// deleteUserById
		@DeleteMapping("/users/{id}")
		public void deleteUserById(@PathVariable("id") Long id) {
			userService.deleteUserById(id);
		}

		// getUserByUsername
		@GetMapping("/users/byusername/{username}")
		public User getUserByUsername(@PathVariable("username") String username) {
			return userService.getUserByUsername(username);
		}
}
