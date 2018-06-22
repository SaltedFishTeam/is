package com.is.service;


import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.is.entity.TCourse;
import com.is.entity.TCoursecomment;
import com.is.entity.TUser;
import com.is.json.entty.CourseCommentVO;
import com.is.repository.CourseCommentRepository;
import com.is.repository.CourseRepository;
import com.is.repository.UserRepository;


@Service
public class CourseCommentService {

	@Autowired
	private CourseCommentRepository courseCommentRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CourseRepository courseRepository;
	
	public void save(int uid,int cid,TCoursecomment courseComment) {
		TUser user = userRepository.getOne(uid);
		TCourse course = courseRepository.getOne(cid);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		String time = format.format(System.currentTimeMillis());
		courseComment.setCommentTime(time);
		courseComment.setTUser(user);
		courseComment.setTCourse(course);
		courseCommentRepository.save(courseComment);
	}
	
	public PagedListHolder<CourseCommentVO> list(int clazzId) {
		List<CourseCommentVO> list = courseCommentRepository.queryCourseCommentByClazzId(clazzId);
		PagedListHolder<CourseCommentVO> pages = new PagedListHolder<>(list);
		pages.setPageSize(4);
		
		return pages;
	}
}
