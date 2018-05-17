package com.is.demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.is.repository.SurveyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SurveyRepositoryTest {

	@Autowired
	private SurveyRepository survey;
	
	@Test
	public void count() {
		long count = survey.count();
		System.out.println(count);
	}
}
