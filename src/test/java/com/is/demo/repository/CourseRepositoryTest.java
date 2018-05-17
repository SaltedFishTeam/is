package com.is.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.is.entity.TCourse;
import com.is.json.entty.CourseVO;
import com.is.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {

	@Autowired
	private CourseRepository course;
	
	public void setCourse(CourseRepository course) {
		this.course = course;
	}



	@Test
	public void test() {
		List<TCourse> list = course.recommendList();
		System.out.println(list.size());
	}
	
	@Test
	public void pageTest() {
		List<TCourse> list = course.findAll();
		course.findAll();
		PagedListHolder<TCourse> courses = new PagedListHolder<>(list);
		courses.setPage(0);
		courses.setPageSize(3);
		List<TCourse> pages = courses.getPageList();
		System.out.println("第一页" + pages.get(0).getCourseId());
		courses.setPage(1);
		List<TCourse> pages2 = courses.getPageList();
		System.out.println("第二页" + pages2.get(0).getCourseId());
		List<CourseVO> test = new ArrayList<CourseVO>();
		BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(test);
		
	}
}
