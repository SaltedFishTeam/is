package com.is.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.is.entity.TCourse;
import com.is.entity.TSc;
import com.is.entity.TScId;
import com.is.entity.TUser;
import com.is.json.entty.CourseUserVO;
import com.is.repository.CourseRepository;
import com.is.repository.StudentCourseRepository;
import com.is.repository.UserRepository;

@Service
public class StudentCourseService {
	
	@Autowired
	private StudentCourseRepository studentCourseRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CourseRepository courseRepository;
	
	public boolean queryByUidAndCid(int uid,int cid) {
		List<TSc> list = studentCourseRepository.queryByUidAndCid(uid, cid);
		if(list == null || list.size() == 0) return false;
		else return true;
	}
	
	public TSc getByUidAndCid(int uid,int cid) {
		TScId id = new TScId(uid, cid);
		return studentCourseRepository.getOne(id);
	}
	
	
	public void saveSC(int uid,int cid) {
		TUser tUser = userRepository.findById(uid).get();
		TCourse tCourse = courseRepository.findById(cid).get();
		tCourse.setStudentNum(tCourse.getStudentNum() + 1);
		courseRepository.save(tCourse);
		TSc sc = new TSc();
		TScId id = new TScId();
		id.setUid(uid);
		id.setCourseId(cid);
		sc.setId(id);
		sc.setArriveTime(0);
		sc.setSpeakTime(0);
		sc.setFinishStudy("");
		sc.setStudyNum(0);
		studentCourseRepository.save(sc);
	}
	
	public void updateFinishStudy(int uid,int cid,int sectionId) {
		TScId id = new TScId(uid, cid);
		TSc tSc = studentCourseRepository.getOne(id);
		tSc.setFinishStudy(tSc.getFinishStudy() + " " + String.valueOf(sectionId) + " ");
		tSc.setStudyNum(tSc.getStudyNum() + 1);
		studentCourseRepository.save(tSc);
	}
	
	public TSc studyNum(int uid,int cid) {
		TScId id = new TScId(uid, cid);
		TSc tSc = studentCourseRepository.getOne(id);
		return tSc;
	}

	public PagedListHolder<CourseUserVO> userCourseList(int uid) {
		List<CourseUserVO> source = studentCourseRepository.listByUid(uid);
		PagedListHolder<CourseUserVO> pages = new PagedListHolder<>(source);
		return pages;
	}
	
	
}
