package com.udb.springboot_webflux_demo.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.udb.springboot_webflux_demo.entity.Customer;

import reactor.core.publisher.Flux;

@Service
public class CustomerServiceImpl {

	public List<Customer> getAllCustomers() {
		Long start = System.currentTimeMillis();
		List<Customer> customers = new ArrayList<>();
		for(int i=1; i<=20;i++) {
			customers.add(new Customer(i,"Customer "+i));
			System.out.println(" Fetching Customer Record :"+i);
			try {
				Thread.sleep(1000);  // Delaying by 1 second
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Long end = System.currentTimeMillis();
		System.out.println("Total time Taken for API :"+(end-start));
		
		return customers;
	}

	public Flux<Customer> getAllStreamCustomers() {
		Long start = System.currentTimeMillis();
		Flux<Customer> customers =  Flux.range(1, 20)
			   .delayElements(Duration.ofSeconds(1))  // Delaying by 1 second
			   .doOnNext(i -> System.out.println("Customer Record :"+i))
			   .map(i -> new Customer(i,"Customer "+i));
		Long end = System.currentTimeMillis();
		System.out.println("Total time Taken for API :"+(end-start));
		
		return customers;
	}

}
