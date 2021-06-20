package com.honsoft.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class BeanNameController extends AbstractController{
	 @Override
	   protected ModelAndView handleRequestInternal(HttpServletRequest request,
	      HttpServletResponse response) throws Exception {
	      ModelAndView model = new ModelAndView("hello");
	      model.addObject("message", "Hello World!");
	      return model;
	   }
}
