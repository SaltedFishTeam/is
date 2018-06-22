package com.is.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.is.entity.*;
import com.is.entity.TCourse;
import com.is.entity.TCoursecomment;
import com.is.entity.TSc;
import com.is.entity.TSection;
import com.is.entity.TSectionMessage;
import com.is.entity.TSectionReply;
import com.is.entity.TUser;
import com.is.json.entty.ChapterVO;
import com.is.json.entty.CourseCommentVO;
import com.is.json.entty.CourseUserVO;
import com.is.json.entty.CourseVO;
import com.is.json.entty.SectionMessageVO;
import com.is.json.entty.SectionVO;
import com.is.json.entty.UserVO;
import com.is.json.status.CourseCommentStatus;
import com.is.json.status.CourseDetailStatus;
import com.is.json.status.CoursePageStatus;
import com.is.json.status.RecommendStatus;
import com.is.json.status.SectionCommentStatus;
import com.is.json.status.Status;
import com.is.json.status.StudyContentStatus;
import com.is.json.status.UserCourseListStatus;
import com.is.service.ChapterService;
import com.is.service.CourseCommentService;
import com.is.service.CourseService;
import com.is.service.SectionMessageService;
import com.is.service.SectionReplyService;
import com.is.service.SectionService;
import com.is.service.StudentCourseService;
import com.is.service.UserService;
import com.is.util.PackageUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * it's raining today 2018-05-12 by wcm
 * @author www
 *
 */
@Slf4j
@RestController
public class CourseController {

	@Autowired
	private CourseService course;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private StudentCourseService studentCourseService;
	@Autowired
	private CourseCommentService courseCommentService;
	@Autowired
	private SectionMessageService sectionMessageService;
	@Autowired
	private SectionReplyService sectionReplyService;
	@Autowired
	private UserService userService;
	@Autowired
	private ChapterService chapterService;
	/**
	 * 根据num查询num个推荐课程 url /course/recommend/{num}
	 * @param num
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/course/recommend/{num}",method=RequestMethod.GET)
	public @ResponseBody RecommendStatus getRecommend(@PathVariable("num") int num) {
		
		List<TCourse> list = course.recommendList(num);
		System.out.println(list.size());
		RecommendStatus status = null;
		if(list == null) {
			status = new RecommendStatus("fail", -1, "推荐课程获取失败");
		} else {
			status = new RecommendStatus("success", 1, "推荐课程获取成功");
			List<CourseVO> courses = new ArrayList<CourseVO>();
			for(TCourse t : list) {
				CourseVO course = new CourseVO();
				PackageUtil.packageObject(course, t);
				if(t.getCourseLabel() != null)
					course.setCourseLabel(t.getCourseLabel());
				courses.add(course);
			}
			status.setCourses(courses);
		}
		return status;
	}
	
	/**
//	 * 根据前端传过来的now 和 size
//	 * 分页查询
//	 * now：当前第几页 最小为0
//	 * size：当前页面展示数据的条数
//	 * @param now
//	 * @param size
//	 * @return
//	 */
//	@RequestMapping(value="/course/{now}/{size}",method=RequestMethod.GET)
//	public @ResponseBody Status courseList(@PathVariable("now") int now,@PathVariable("size")int size) {
//		List<TCourse> list = null;
//		if(now >= 0 && size > 0)
//			list = course.pageList(now, size);
////		else
//			
//		return null;
//	}
	
	@RequestMapping(value="/course/{key}/{now}",method=RequestMethod.GET)
	public @ResponseBody CoursePageStatus searchCourse(@PathVariable("key") String key,
			@PathVariable(value="now") int now) {
		int size = 10;
		List<TCourse> page = course.pageListByKey(key, now, size);
		List<CourseVO> courses = new ArrayList<CourseVO>();
		for(TCourse c : page) {
			CourseVO course = new CourseVO();
			PackageUtil.packageObject(course, c);
			courses.add(course);
		}
		CoursePageStatus status = new CoursePageStatus("fail", 1, "查询成功");
		status.setCourses(courses);
		return status;
	}
	
	/**
	 * url: course/{cid} GET
	 * 根据课程id查询课程的详细信息
	 * 课程基本信息
	 * 课程章节
	 * 开课老师基本信息
	 * @param cid
	 * @return
	 */
	@RequestMapping(value="course/{cid}",method=RequestMethod.GET)
	public @ResponseBody CourseDetailStatus getCourse(HttpSession session,
			@PathVariable("cid") int cid) {
		TUser user = (TUser) session.getAttribute("user");
		CourseDetailStatus status = course.getCourseByCid(cid);
		if(user != null) {
			TSc sc = studentCourseService.getByUidAndCid(user.getUid(), cid);
			List<ChapterVO> chapters = status.getCourses().getChapters();
			for(ChapterVO chapter : chapters) {
				for(SectionVO section : chapter.getSections()) {
					try {
						if(sc != null)
							if(sc.getFinishStudy()
									.indexOf(" " + section.getSectionId() + " ") != -1) {
								section.setStatus(true);
							} else {
								section.setStatus(false);
							}
						else section.setStatus(false);
					} catch(Exception e) {
						section.setStatus(false);
					}
				}
			}
		}
		return status;
	}
	
	
	
	@RequestMapping(value="test",method=RequestMethod.POST)
	public @ResponseBody String test() {
		System.out.println("执行测试");
		return "测试";
	}
	
	@RequestMapping(value="/course/add",method=RequestMethod.POST)
	public @ResponseBody Status addCourse(@RequestBody TCourse course) {
		try {
			this.course.save(course);
			return new Status("success", 1, "成功");
		} catch (Exception e) {
			return new Status("fail", -1, ExceptionUtils.getFullStackTrace(e));
		}
	}
	
	
	@RequestMapping(value="/study/{clazzId}/{chapterId}/{sectionId}",method=RequestMethod.GET)
	public StudyContentStatus getSectionContent(
			@PathVariable("clazzId")int clazzId,
			@PathVariable("chapterId")int chapterId,
			@PathVariable("sectionId")int sectionId) {
		try {
			TSection section = sectionService.getSection(sectionId);
			SectionVO vo = new SectionVO();
			PackageUtil.packageObject(vo, section);
			TCourse tCourse = course.onlyGetCourseById(clazzId);
			CourseVO courseVO = new CourseVO();
			PackageUtil.packageObject(courseVO, tCourse);
			courseVO.setCourseLive("rtmp://172.20.25.37:5136/oflaDemo/" + courseVO.getCourseId());
			StudyContentStatus status = new StudyContentStatus("success", 1, "成功");
			status.setSectionVO(vo);
			status.setCourseVO(courseVO);
			status.setTeacherId(tCourse.getTUser().getUid());
			return status;
		} catch (Exception e) {
			return new StudyContentStatus("fail", -1, "失败");
		}
	}

	@RequestMapping(value="/course/comment/{clazzId}",method=RequestMethod.POST)
	public Status courseComment(HttpSession session,
			@PathVariable("clazzId")int clazzId,
			@RequestBody Map<String, Object> params) {
		TUser user = (TUser) session.getAttribute("user");
		if(user == null) return new Status("fail", -2, "请登录");
		try {
			Integer uid = user.getUid();
			
			TSc tSc = studentCourseService.studyNum(uid, clazzId);
			TCourse tCourse = this.course.onlyGetCourseById(clazzId);
			if(tSc.getStudyNum() != tCourse.getSectionNum()) {
				return new Status("fail", -3, "请完成课程后再来评论");
			}
			String content = (String) params.get("content");
			int teacherStarLevel = (int) params.get("teacherStarLevel");
			int courseStarLevel = (int) params.get("courseStarLevel");
			TCoursecomment courseComment = new TCoursecomment();
			courseComment.setCommentContent(content);
			courseComment.setTeacherStarLevel(Float.valueOf(teacherStarLevel));
			courseComment.setCourseStarLevel(Float.valueOf(courseStarLevel));
			courseCommentService.save(uid, clazzId, courseComment);
			return new Status("success", 1, "成功");
		} catch (Exception e) {
			return new Status("fail", -1, ExceptionUtils.getFullStackTrace(e));
		}
	}
	
	@RequestMapping(value="/course/comment/{clazzId}/{nowPage}")
	public CourseCommentStatus courseCommentList(@PathVariable("clazzId")int clazzId,
			@PathVariable("nowPage")int nowPage) {
		PagedListHolder<CourseCommentVO> pages = courseCommentService.list(clazzId);
		pages.setPage(nowPage - 1);
		CourseCommentStatus status = new CourseCommentStatus("success", 1, "成功");
		status.setList(pages.getPageList());
		status.setCount(pages.getSource().size());
		return status;
	}
	
	@RequestMapping(value="/course/section/comment/list/{sectionId}/{nowPage}"
			, method=RequestMethod.GET)
	public SectionCommentStatus sectionCommentList(@PathVariable("sectionId")int sectionId,
			@PathVariable("nowPage")int nowPage) {
		try {
			PagedListHolder<SectionMessageVO> pages = sectionMessageService.listBySectionId(sectionId,nowPage);
			List<SectionMessageVO> pageList = pages.getPageList();
			SectionCommentStatus status = new SectionCommentStatus("success", 1, "成功");
			status.setMessages(pageList);
			status.setCount(pages.getSource().size());
			return status;
		} catch (Exception e) {
			return new SectionCommentStatus("fail", -1, "失败");
		}
	}
	
	
	@RequestMapping(value="/course/section/comment/add/{sectionId}",method=RequestMethod.POST)
	public Status sectionCommentAdd(HttpSession session,
			@PathVariable("sectionId")int sectionId,
			@RequestBody TSectionMessage sectionMessage) {
		System.out.println("content" + sectionMessage.getContent());
		TUser user = (TUser) session.getAttribute("user");
		if(user != null)
			try {
				sectionMessageService.save(user.getUid(),sectionId,sectionMessage);
			} catch (Exception e) {
				return new Status("fail",-1,ExceptionUtils.getFullStackTrace(e));
			}
		else return new Status("fail",-2,"请登陆");
		return new Status("success",1,"成功");
	}
	
	@RequestMapping(value="/course/section/reply/{rid}/{sectionMessageId}",method=RequestMethod.POST)
	public Status sectionReplyAdd(HttpSession session,
			@PathVariable("rid")int rid,
			@PathVariable("sectionMessageId")int sectionMessageId,
			@RequestBody TSectionReply sectionReply) {
		TUser user = (TUser) session.getAttribute("user");
		if(user == null) return new Status("fail", -2, "请先登录再回复");
		try {
			sectionReplyService.save(user.getUid(), rid, sectionMessageId, sectionReply);
		} catch (Exception e) {
			return new Status("fail", -1, ExceptionUtils.getFullStackTrace(e));
		}
		return new Status("success", 1, "成功");
	}
	
	@RequestMapping(value="/course/study/check/{clazzId}",method=RequestMethod.GET)
	public Status checkCanStudy(HttpSession session,
			@PathVariable("clazzId")int clazzId) {
		TUser user = (TUser)session.getAttribute("user");
		if(user == null) return new Status("fail",-2,"请登录");
		try {
			//假如是教师身份
			if(user.getRole() == 2) {
				TCourse tCourse = course.onlyGetCourseById(clazzId);
				if(tCourse.getTUser().getUid() == user.getUid()) return new Status("success", 1, "成功");
			}
			boolean flag = studentCourseService.queryByUidAndCid(user.getUid(), clazzId);
			if(flag) return new Status("success", 1, "成功");
			else return new Status("fail", -3, "您没有参加该课程");
		} catch(Exception e) {
			return new Status("fail", -1, ExceptionUtils.getFullStackTrace(e));
		}
	}
	
	@RequestMapping(value="/course/section/reply/{id}/{rid}",method = RequestMethod.GET)
	public Status sectionReply(HttpSession session,
			@PathVariable("id")int id,
			@PathVariable("rid")int rid,
			@RequestBody TSectionReply reply) {
		TUser user = (TUser) session.getAttribute("user");
		if(user == null) return new Status("fail",-2,"请登录");
		try {
			sectionReplyService.save(user.getUid(), rid, id, reply);
			return new Status("success",1,"成功");
		} catch (Exception e) {
			return new Status("fail",-3,"id无效");
		}
	}
	
	@RequestMapping(value="/course/list/{nowPage}",method = RequestMethod.GET)
	public UserCourseListStatus listUserCourse(HttpSession session,
			@PathVariable("nowPage")int nowPage) {
		TUser user = (TUser) session.getAttribute("user");
		if(user == null) return new UserCourseListStatus("fail", -2, "请登录");
		PagedListHolder<CourseUserVO> pages = null;
		try {
			if(user.getRole() == 3) {
				pages = studentCourseService.userCourseList(user.getUid());
			} else if(user.getRole() == 2) {
				pages = userService.teacherCourseList(user.getUid());
			}
		} catch (Exception e) {
			return new UserCourseListStatus("fail", -1, "失败");
		}
		UserCourseListStatus status = new UserCourseListStatus("success", 1, "成功");
		pages.setPageSize(3);
		pages.setPage(nowPage - 1);
		List<CourseUserVO> list = pages.getPageList();
		
		status.setList(list);
		status.setCount(pages.getSource().size());
		return status;
	}
	
	
	@RequestMapping(value="/course/section/remove/{sectionId}",method=RequestMethod.POST)
	public Status delSection(@PathVariable("sectionId")int sectionId) {
		try {
			sectionService.delSection(sectionId);
		} catch(Exception e) {
			return new Status("fail", -1, ExceptionUtils.getFullStackTrace(e));
		}
		return new Status("success", 1, "成功");
	}
	
	@RequestMapping(value="/course/chapter/remove/{chaterId}",method=RequestMethod.POST)
	public Status delChapter(@PathVariable("chaterId")int chaterId) {
		try {
			chapterService.delChapter(chaterId);
		} catch(Exception e) {
			return new Status("fail", -1, ExceptionUtils.getFullStackTrace(e));
		}
		return new Status("success", 1, "成功");
	}
	
	@RequestMapping(value="/course/del/{courseId}",method=RequestMethod.GET)
	public Status delCourse(@PathVariable("courseId")int courseId) {
		try {
			course.del(courseId);
			return new Status("success", 1, "成功");
		} catch (Exception e) {
			return new Status("fail", -1, "失败");
		}

	}
	
}
