package com.udb.springboot_webflux_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udb.springboot_webflux_demo.entity.Customer;
import com.udb.springboot_webflux_demo.service.CustomerServiceImpl;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerServiceImpl service;
	
	// Normal Approach
	@RequestMapping("/")
	public List<Customer> getAllCustomers(){
		
		return service.getAllCustomers();
		
	}
	// Asynchronous Approach --possible by WebFlux
	@RequestMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Customer> getAllStreamCustomers(){
		
		return service.getAllStreamCustomers();
		
	}
	
	
	
	
}
