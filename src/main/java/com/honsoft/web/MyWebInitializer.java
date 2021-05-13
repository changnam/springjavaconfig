package com.honsoft.web;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.honsoft.web.config.SecurityConfig;
import com.honsoft.web.config.WebConfig;


public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		 return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	 @Override
     protected Filter[] getServletFilters() {

       CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
       characterEncodingFilter.setEncoding("UTF-8");
       characterEncodingFilter.setForceEncoding(true);
       characterEncodingFilter.setForceRequestEncoding(true);
       return new Filter[] { characterEncodingFilter};
     }
}
