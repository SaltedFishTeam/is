package com.is.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.is.entity.TChapter;
import com.is.entity.TCourse;
import com.is.entity.TSection;
import com.is.entity.TUser;
import com.is.json.entty.ChapterVO;
import com.is.json.entty.CourseVO;
import com.is.json.entty.SectionVO;
import com.is.json.entty.UserVO;
import com.is.json.status.CourseDetailStatus;
import com.is.json.status.CoursePageStatus;
import com.is.json.status.RecommendStatus;
import com.is.json.status.Status;
import com.is.service.CourseService;
import com.is.util.PackageUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * it's raining today 2018-05-12 by wcm
 * @author www
 *
 */
@Slf4j
@Controller
public class CourseController {

	@Autowired
	private CourseService course;
	
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
					course.setCourseLabels(t.getCourseLabel().split("/"));
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
	public @ResponseBody CourseDetailStatus getCourse(@PathVariable("cid") int cid) {
		System.out.println("测试方法");
		CourseDetailStatus status = null;
		TCourse tCourse = null;
		try {
			tCourse = course.getCourseByCid(cid);
			System.out.println(tCourse.getCourseName() + "测试");
		} catch(Exception e) {
			log.error("课程信息查找失败");
			status = new CourseDetailStatus("fail", -1, "课程信息查找失败");
			return status;
		}
		TUser user = tCourse.getTUser();
		System.out.println("user用户名" + user.getUsername());
		UserVO userVO = new UserVO();
		CourseVO courseVO = new CourseVO();
		try {
			PackageUtil.packageObject(userVO, user);
			System.out.println("用户名" + userVO.getUsername());
			PackageUtil.packageObject(courseVO, tCourse);
			List<ChapterVO> chapters = new ArrayList<ChapterVO>();
			for(TChapter chapter : tCourse.getTChapters()) {
				ChapterVO chapterVO = new ChapterVO();
				PackageUtil.packageObject(chapterVO, chapter);
				chapters.add(chapterVO);
				List<SectionVO> sections = new ArrayList<SectionVO>();
				for(TSection section : chapter.getTSections()) {
					SectionVO s = new SectionVO();
					PackageUtil.packageObject(s, section);
					sections.add(s);
				}
				chapterVO.setSections(sections);
			}
			courseVO.setChapters(chapters);
		}catch(Exception e) {
			log.error("课程详情内容封装失败" + e.getMessage());
			status = new CourseDetailStatus("fail", -1, "课程详情内容封装失败");
			return status;
		} finally {
		}
		
		status = new CourseDetailStatus("success", 1, "查询成功");
		status.setCourses(courseVO);
		status.setUser(userVO);
		return status;
	}
}
