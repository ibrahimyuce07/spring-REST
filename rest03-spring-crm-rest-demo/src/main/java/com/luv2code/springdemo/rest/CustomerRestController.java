package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	//autowire the customer service
	@Autowired
	private CustomerService service;
	
	//add mapping for GET /customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		
		return service.getCustomers();
	}
	
	//add mapping for GET /customers/{customerId}
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		
		Customer theCustomer = service.getCustomer(customerId);
		
		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		
		return theCustomer; 
	}
	
	//add mapping for POST /customers to add customers
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
		//also just in case the pass an id in JSON ...	set id to 0 so it can create new item instead of update
		theCustomer.setId(0);
		
		service.saveCustomer(theCustomer);
		
		return theCustomer;
		
	}
	
	//add mapping for PUT /customers to update customers
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		service.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	
	//add mapping for DELETE /customers to delete customers
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		//throw exception if null
		Customer theCustomer =  service.getCustomer(customerId);
		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		
		service.deleteCustomer(customerId);
		
		return 	"Deleted customer id: " +customerId;
	}
	
}
