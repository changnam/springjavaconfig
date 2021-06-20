package com.honsoft.web.listener;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Component
public class SpringContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("===================> Handling context re-freshed event. ");

		Map<String, Object> allBeans = BeanFactoryUtils.beansOfTypeIncludingAncestors(event.getApplicationContext(),
				Object.class, true, false);

		if (!allBeans.isEmpty()) {
			int cnt = 0;
			for (String key : allBeans.keySet()) {
				Object bean = allBeans.get(key);
				if (bean instanceof HandlerMapping)
					printHandlerMapping((HandlerMapping) bean);
				else if (bean instanceof HandlerInterceptor)
					printHandlerInterceptor((HandlerInterceptor)bean);
				
				boolean orderedType = bean instanceof Ordered ? true : false;
				if (orderedType)
					System.out.println(cnt++ + " " + key + " : " + bean.getClass().toString() + " , "
							+ ((Ordered) bean).getOrder());
				else
					System.out.println(cnt++ + " " + key + " : " + bean.getClass().toString());
			}
		}

		System.out.println("====================== Handling context re-freshed event Ended. ====================== ");
	}

	private void printHandlerInterceptor(HandlerInterceptor handlerInterceptor) {
		System.out.println("handlerInterceptor class : "+handlerInterceptor.getClass().toString());
		
	}

	public void printHandlerMapping(HandlerMapping handlerMapping) {
		// --------------------------- list all mappingsinfo
		System.out.println("================= List of handlermappings ======================");
		System.out.println("handlerMapping class : "+handlerMapping.getClass().toString());
		if (handlerMapping instanceof BeanNameUrlHandlerMapping) {
			BeanNameUrlHandlerMapping beanNameUrlHandlerMapping = (BeanNameUrlHandlerMapping) handlerMapping;
			//for (String urlMap : beanNameUrlHandlerMapping.getp().keySet()) {
			//	System.out.println("==> urlMap : " + urlMap + " : " + beanNameUrlHandlerMapping.getUrlMap().get(urlMap));
			//}
		} else if (handlerMapping instanceof SimpleUrlHandlerMapping) {
			SimpleUrlHandlerMapping simpleUrlHandlerMapping = (SimpleUrlHandlerMapping) handlerMapping;
			for (String urlMap : simpleUrlHandlerMapping.getUrlMap().keySet()) {
				System.out.println("==> urlMap : " + urlMap + " : " + simpleUrlHandlerMapping.getUrlMap().get(urlMap));
			}
			for (String handlerMap : simpleUrlHandlerMapping.getHandlerMap().keySet()) {
				System.out.println(
						"==> handlerMap : " + handlerMap + " : " + simpleUrlHandlerMapping.getUrlMap().get(handlerMap));
			}
		} else if (handlerMapping instanceof RequestMappingHandlerMapping) {
			RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;

			System.out.println("Order : " + requestMappingHandlerMapping.getOrder());
			for (RequestMappingInfo urlMap : requestMappingHandlerMapping.getHandlerMethods().keySet()) {
				System.out.println("==> urlMap : " + urlMap + " : "
						+ requestMappingHandlerMapping.getHandlerMethods().get(urlMap));
			}
		} else {
				System.out.println("******* "+handlerMapping.getClass().toString());
		}

		System.out.println("================= End of handlermappings ======================");

	}
}