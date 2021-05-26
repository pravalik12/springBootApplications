package com.sqltest.example.others;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@PostMapping("/postData")
	public String addPerson(@RequestBody Person p) {
		System.out.println("Logger 1");
		return null;
	}

}
