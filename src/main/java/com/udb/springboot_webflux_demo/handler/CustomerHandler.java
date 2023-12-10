package com.udb.springboot_webflux_demo.handler;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.udb.springboot_webflux_demo.entity.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {
	
	
	public Mono<ServerResponse> getAllCustomers(ServerRequest request){
		
		Flux<Customer> customers=Flux.range(1, 20)
				.doOnNext(i -> System.out.println("Customer Record :"+i))
				.map(i -> new Customer(i,"Customer "+i));
		
		return ServerResponse.ok().body(customers, Customer.class);
		
	}
	public Mono<ServerResponse> getCustomerById(ServerRequest request){
		String pathVariable =request.pathVariable("customer_id");
		Integer customer_id=Integer.valueOf(pathVariable);
		
		Flux<Customer> customers=Flux.range(1, 20)
				.map(i -> new Customer(i,"Customer "+i));
		Mono<Customer> customer = customers.filter(cust ->cust.getCustomer_id() ==customer_id).next();
		
		return ServerResponse.ok().body(customer, Customer.class);
	}
	
	public Mono<ServerResponse> addCustomer(ServerRequest request){
		Mono<Customer> bodyToMono = request.bodyToMono(Customer.class);
		Mono<String> sCustomer = bodyToMono.map(cust -> cust.getCustomer_id()+" : "+cust.getCustomer_name());
		
		return ServerResponse.ok().body(sCustomer, String.class);
	}

}
