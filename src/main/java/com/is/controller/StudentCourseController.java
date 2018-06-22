package com.is.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.is.entity.TUser;
import com.is.json.status.Status;
import com.is.service.StudentCourseService;

@RestController
public class StudentCourseController {
	
	@Autowired
	private StudentCourseService studentCourseService;
	
	@RequestMapping(value="/user/query/clazz/{cid}",method=RequestMethod.GET)
	public Status queryClazz(HttpSession session,
			@PathVariable("cid")int cid) {
		TUser user = (TUser) session.getAttribute("user");
		if(user == null) return new Status("fail", -2, "请回去登陆");
		try {
			boolean result = studentCourseService.queryByUidAndCid(user.getUid(), cid);
			if(result) return new Status("success", 2, "您已经有了这门课");
			else return new Status("success", 1, "您可以参加这门课");
		} catch (Exception e) {
			return new Status("fail", -1, ExceptionUtils.getFullStackTrace(e));
		}
	}
	
	/**
	 * 加入课程
	 * @param session
	 * @param cid
	 * @return
	 */
	@RequestMapping(value="/user/join/clazz/{cid}",method=RequestMethod.POST)
	public Status joinClazz(HttpSession session,
			@PathVariable("cid")int cid) {
		TUser user = (TUser) session.getAttribute("user");
		if(user == null) return new Status("fail", -2, "请登陆");
		try {
			studentCourseService.saveSC(user.getUid(), cid);			
			return new Status("success", 1, "加入课程成功");
		} catch (Exception e) {
			return new Status("success", -1, ExceptionUtils.getFullStackTrace(e));
		}
	}
	
	
	@RequestMapping(value="/user/finishstudy/{cid}/{sectionId}",method=RequestMethod.POST)
	public Status finishStudy(HttpSession session,
			@PathVariable("cid")int cid,@PathVariable("sectionId")int sectionId) {
		TUser user = (TUser) session.getAttribute("user");
		if(user == null) return new Status("fail", -2, "请登录");
		try {
			studentCourseService.updateFinishStudy(user.getUid(), cid, sectionId);
			return new Status("success", 1, "成功");
		} catch(Exception e) {
			return new Status("fail", -1, ExceptionUtils.getFullStackTrace(e));
		}
	}
}
