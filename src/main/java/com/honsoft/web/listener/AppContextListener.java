package com.honsoft.web.listener;

import java.util.Map;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;



@WebListener
public class AppContextListener implements ServletContextListener {

	public AppContextListener() {
		System.out.println("AppContextListener is being instantiated.");
	}
	
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	System.out.println("AppContextListener is called. (ServletContext created)");
    	ServletContext ctx = servletContextEvent.getServletContext();
    	    	
    	Map<String, ? extends ServletRegistration> servletRegistrations = ctx.getServletRegistrations();
		

		System.out.println("------------------ registerd servlets -------------------------");
    	for (String servletName : servletRegistrations.keySet()) {
    		//System.out.println("==> "+);
			System.out.println("name: " + servletName + " , class: " +  servletRegistrations.get(servletName).getClassName() + " , mapping: " + servletRegistrations.get(servletName).getMappings() );
    	}
    	System.out.println("--------------------------------------------------------------");
    	
    	Map<String, ? extends FilterRegistration> filterRegistrations = ctx.getFilterRegistrations();
    	
    	System.out.println("------------------ registerd filters -------------------------");
    	for (String filterName : filterRegistrations.keySet()) {
    		//System.out.println("==> "+);
			System.out.println("name: " + filterName + " , class: " +  filterRegistrations.get(filterName).getClassName() + " , mapping: " + filterRegistrations.get(filterName).getUrlPatternMappings() + filterRegistrations.get(filterName).getServletNameMappings());
    	}
    	System.out.println("------------------------------------------");
    	System.out.println("--------- End of contextInitialized in AppContextListener ---------------------------------");
    	
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	ServletContext ctx = servletContextEvent.getServletContext();
    	System.out.println("Database connection closed for Application.");
    	
    }
	
}

