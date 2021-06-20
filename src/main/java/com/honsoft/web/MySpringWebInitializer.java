package com.honsoft.web;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.h2.server.web.WebServlet;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.honsoft.web.config.SecurityConfig;
import com.honsoft.web.config.WebConfig;

public class MySpringWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

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

		return null;
		// return new Filter[] { characterEncodingFilter, loggingFilter};
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		super.onStartup(servletContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("h2-console", new WebServlet());
		servlet.setLoadOnStartup(2);
		servlet.addMapping("/h2-console/*");
	}
}
