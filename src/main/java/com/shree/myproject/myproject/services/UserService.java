package com.shree.myproject.myproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shree.myproject.myproject.entities.User;
import com.shree.myproject.myproject.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	   // get all User method

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	    //CreateUser Method
		public User createUser(User user) {
			return userRepository.save(user);
}
		//getUserById
		public Optional<User> getUserById(Long id) {
			Optional<User> user = userRepository.findById(id);
			
			return user;
		}
		//updateUserById
		public User updateUserById(Long id, User user) {
			user.setId(id);
			return userRepository.save(user);
			
		}
		
		
		//deleteUserById
		public void deleteUserById(Long id) {
			if(userRepository.findById(id).isPresent()) {
				userRepository.deleteById(id);
				
			}
		}
		
		
		//getUserByUsername
		
		public User getUserByUsername(String username) {
			return userRepository.findByUsername(username);
		}
}
