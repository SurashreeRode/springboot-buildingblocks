package com.shree.myproject.myproject.hellowworldcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowWorldController {

	@GetMapping("/helloworld")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping("/helloworld-bean")
	public UserDetails helloworldbean() {
		return new UserDetails("Shree", "Rode", "Nagpur");
	}
}
