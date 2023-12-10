package com.udb.springboot_webflux_demo.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.udb.springboot_webflux_demo.handler.CustomerHandler;

@Configuration
public class CustomerRouter {
	
	@Autowired
	private CustomerHandler handler;
	
	@Bean
	public RouterFunction<ServerResponse> customerRouterFunction(){
		
		return RouterFunctions.route()
				.GET("/customer/",handler::getAllCustomers)
				.GET("/customer/{customer_id}",handler::getCustomerById)
                .POST("/customer/save", handler::addCustomer)
				.build();
	}
	
	

}
