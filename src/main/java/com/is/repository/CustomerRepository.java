package com.is.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.is.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
