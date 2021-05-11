package com.honsoft.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.honsoft.web.entity.User;

@Controller
public class WelcomeController{

	@GetMapping("/welcome")
	public @ResponseBody String welcome() {
		return "this is string ";
	}
	
	@GetMapping(value="/welcomeuser")
	public @ResponseBody User welcomeuser() {
		User user = new User();
		user.setUsername("changnam");
    	
    	return user;
	}
	
	@GetMapping(value="/welcomejson", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String welcomejson() {
		return "this is string welcome json ";
	}
	
	@GetMapping(value="/welcomejsonobj", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User welcomejsonobj() {
		User user = new User();
		user.setUsername("changnam");
    	
    	return user;
	}
	
	
	@GetMapping(value="/welcomexml", produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody String welcomexml(){
		return "welcome xml";
	}
	
	@GetMapping(value="/welcomexmlobj", produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody User welcomexmlobj(){
		User user = new User();
		user.setUsername("changnam");
    	
    	return user;
	}
	
	@GetMapping("/welcomejsp")
	public String welcomejsp(Model m){
		m.addAttribute("message","hello spring initializer");
		return "welcome";
	}
}
