package com.is.entity;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;

@CacheConfig(cacheNames="customers")
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
