package com.is.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.entity.Customer;
import com.is.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public CustomerRepository getCustomerRepository() {
		return customerRepository;
	}

	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public Customer add(Customer customer) {
		Customer save = customerRepository.save(customer);
		return save;
	}
	
	public void del(Customer customer) {
		customerRepository.delete(customer);
	}
	
//	public List<Customer> list(Iterable<Integer> ids) {
//		customerRepository.find
////		return customerRepository.findAllById(ids);
//	}
	
	public void update(Customer customer) {
		customerRepository.save(customer);
	}
}
