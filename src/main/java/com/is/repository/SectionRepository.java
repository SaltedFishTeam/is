package com.is.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.is.entity.TSection;

public interface SectionRepository extends JpaRepository<TSection, Integer> {

}
