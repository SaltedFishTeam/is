package com.is.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import com.is.entity.Customer;

@CacheConfig(cacheNames="customers")
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
