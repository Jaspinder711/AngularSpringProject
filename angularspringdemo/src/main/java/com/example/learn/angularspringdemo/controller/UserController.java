package com.example.learn.angularspringdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.learn.angularspringdemo.model.User;
import com.example.learn.angularspringdemo.repository.UserRepository;


@RestController
@CrossOrigin(origins="http://localhost:4200")
public class UserController {

	public UserController() {}
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return (List<User>) userRepository.findAll();
	}
	
	@PostMapping("/users")
	public void addUser(@RequestBody User user) {
		userRepository.save(user);
	}

}
