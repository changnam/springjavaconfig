package com.honsoft.web.listener;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Component
public class SpringContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
	 public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("===================> Handling context re-freshed event. ");
		
		System.out.println(" beans count in applicationContext: "+event.getApplicationContext().getBeanDefinitionCount());
		System.out.println("============= List of beans ============");
		String[] beanNames = event.getApplicationContext().getBeanDefinitionNames();
		for (String beanName : beanNames)
			System.out.println(beanName + " : " + event.getApplicationContext().getBean(beanName).getClass().toString());
		
		if (event.getApplicationContext().getParent() != null) {
			System.out.println(" beans count in parent applicationContext: "+event.getApplicationContext().getParent().getBeanDefinitionCount());
			System.out.println("============= List of beans ============");
			beanNames = (event.getApplicationContext().getParent()).getBeanDefinitionNames();
			for (String beanName : beanNames)
				System.out.println(beanName + " : " + (event.getApplicationContext().getParent()).getBean(beanName).getClass().toString());
		
		}
		//--------------------------- list all mappingsinfo
		System.out.println("================= List of handlermappings ======================");
		   Map<String, HandlerMapping> handlerMappingMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(event.getApplicationContext(), HandlerMapping.class, true, false);
	       SimpleUrlHandlerMapping simpleUrlHandlerMapping = null;
	       RequestMappingHandlerMapping requestMappingHandlerMapping = null;
	       
		   for (String handlerMapping : handlerMappingMap.keySet()) {
	    	   System.out.println("==> handlerMapping : " +handlerMapping + " : " + handlerMappingMap.get(handlerMapping).getClass().toString());
	    	   //handlerMappingMap
	    	   if (handlerMappingMap.get(handlerMapping) instanceof SimpleUrlHandlerMapping) {
	    		   simpleUrlHandlerMapping = (SimpleUrlHandlerMapping) handlerMappingMap.get(handlerMapping);
	    		   System.out.println("Order : "+ simpleUrlHandlerMapping.getOrder());
	    		   for (String urlMap : simpleUrlHandlerMapping.getUrlMap().keySet()) {
	    			   System.out.println("==> urlMap : " + urlMap + " : " + simpleUrlHandlerMapping.getUrlMap().get(urlMap));
	    		   }
	    		   for (String handlerMap : simpleUrlHandlerMapping.getHandlerMap().keySet()) {
	    			   System.out.println("==> handlerMap : " + handlerMap + " : " + simpleUrlHandlerMapping.getUrlMap().get(handlerMap));
	    		   }
	    	   } else if (handlerMappingMap.get(handlerMapping) instanceof RequestMappingHandlerMapping) {
	    		   requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMappingMap.get(handlerMapping);
	    		  
	    		   System.out.println("Order : "+ requestMappingHandlerMapping.getOrder());
	    		   for (RequestMappingInfo urlMap :  requestMappingHandlerMapping.getHandlerMethods().keySet()) {
	    			   System.out.println("==> urlMap : " + urlMap + " : " + requestMappingHandlerMapping.getHandlerMethods().get(urlMap));
	    		   }
	    		 //  for (String handlerMap : simpleUrlHandlerMapping.getHandlerMap().keySet()) {
	    		//	   System.out.println("==> handlerMap : " + handlerMap + " : " + simpleUrlHandlerMapping.getUrlMap().get(handlerMap));
	    		 //  }
	    	   } else {
	    		   System.out.println("Order : "+((Ordered) handlerMappingMap.get(handlerMapping)).getOrder());
	    	   }
	       }
		   System.out.println("================= End of handlermappings ======================");
			
			//--------------------------- list all viewResolver
			System.out.println("================= List of viewResolver ======================");
			   Map<String, ViewResolver> viewResolverMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(event.getApplicationContext(), ViewResolver.class, true, false);
		       InternalResourceViewResolver internalResourceViewResolver = null;
			   for (String viewResolver : viewResolverMap.keySet()) {
		    	   System.out.println("==> viewResolver : " +viewResolver + " : " + viewResolverMap.get(viewResolver).getClass().toString());
		    	   if (viewResolverMap.get(viewResolver) instanceof InternalResourceViewResolver) {
		    		   internalResourceViewResolver = (InternalResourceViewResolver) viewResolverMap.get(viewResolver);
		    		   System.out.println("Order : " + internalResourceViewResolver.getOrder());		 
		    
		    		   //System.out.println("==> prefix : " + internalResourceViewResolver.g);
		    		   //System.out.println("==> suffix : " + internalResourceViewResolver.getUrlMap().get(handlerMap));
		    		 
		    	   } else {
		    		   System.out.println("Order : " + ((Ordered) viewResolverMap.get(viewResolver)).getOrder());	
		    	   }
		       }
			   System.out.println("================= End of viewResolver ======================");
						   
		   //try {
	        //    HandlerMapping handlerMapping = handlerMappingMap.get(RequestMappingHandlerMapping.class.getName());
	            //HandlerExecutionChain handlerExecutionChain = handlerMapping.getHandler(request);
	            //Object handler = handlerExecutionChain.getHandler();
	       //     if(handler instanceof HandlerMethod){
	        //        Annotation methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(MyAnnotation.class);
	       //         return methodAnnotation!=null;
	       //     }
	       // } catch (Exception e) {
	       //     logger.warn(e);
	       // }
	      //---------------
	        
		System.out.println("====================== Handling context re-freshed event Ended. ====================== ");
	}
}