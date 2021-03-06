package com.honsoft.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.honsoft.web.entity.User;
import com.honsoft.web.entity.Users;
import com.honsoft.web.service.FooService;

@Controller
public class FooController {
	@Autowired
	FooService fooService;
	
	@GetMapping("/foo")
	public String foo( @RequestParam(value="userId", required=false, defaultValue="001") String userId, Model m) {
		User user = fooService.doSomeBusinessStuff(userId);
		return "foo";
	}
	
	@GetMapping("/fooList")
	public String fooList(Model m) {
		List<Users> users = fooService.getUsersList();
		m.addAttribute("users", users);
		return "fooList";
	}
	
}
