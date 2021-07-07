package com.esraoz.customerarchiving.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esraoz.customerarchiving.exception.ResourceNotFoundException;
import com.esraoz.customerarchiving.dataAccess.abstracts.CustomerRepository;
import com.esraoz.customerarchiving.entities.concretes.Customer;
import com.esraoz.customerarchiving.util.Constants;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping(path = "/customers")
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@GetMapping(path = "/customer/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId)
			throws NotFoundException, ResourceNotFoundException {
		final Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.ERR_MSG_CUSTOMER + customerId));
		return ResponseEntity.ok().body(customer);
	}

	@PostMapping(path = "/customer")
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

	@PutMapping(path = "/customer/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
			@RequestBody Customer updatedCustomer) throws ResourceNotFoundException {
		final Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.ERR_MSG_CUSTOMER + customerId));
		updatedCustomer.setId(customer.getId());
		return ResponseEntity.ok().body(customerRepository.save(updatedCustomer));

	}

	@DeleteMapping(path = "/customer/{id}")
	public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId)
			throws ResourceNotFoundException {
		final Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.ERR_MSG_CUSTOMER + customerId));
		customerRepository.delete(customer);
		Map<String, Boolean> response = new HashMap<>();
		response.put(Constants.DELETED, Boolean.TRUE);
		return response;
	}

}
