package com.is.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.is.entity.TSurvey;

public interface SurveyRepository extends JpaRepository<TSurvey, Integer> {

}
