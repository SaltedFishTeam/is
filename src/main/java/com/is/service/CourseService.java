package com.is.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.is.entity.TChapter;
import com.is.entity.TCourse;
import com.is.entity.TSection;
import com.is.entity.TUser;
import com.is.json.entty.ChapterVO;
import com.is.json.entty.CourseVO;
import com.is.json.entty.SectionVO;
import com.is.json.entty.UserVO;
import com.is.json.status.CourseDetailStatus;
import com.is.repository.CourseRepository;
import com.is.repository.UserRepository;
import com.is.util.PackageUtil;

@Service
public class CourseService {

	@Autowired
	private CourseRepository course;
	@Autowired
	private UserRepository user;
	/**
	 * 获得推荐的课程信息
	 * @return
	 */
	public List<TCourse> recommendList(int num) {	
		List<TCourse> list = course.recommendList();
		List<TCourse> recommend = list.subList(0, num < list.size() ? num : list.size());
		return recommend;
	}
	
	/**
	 * 分页查询
	 * now是当前第几页	从0开始
	 * pageSize 是一页有多少数据
	 * @param now
	 * @param pageSize
	 * @return
	 */
	public List<TCourse> pageList(int now,int pageSize) {
		List<TCourse> list = course.findAll();
		PagedListHolder<TCourse> courses = new PagedListHolder<>(list);
		courses.setPage(now);
		courses.setPageSize(pageSize);
		List<TCourse> page = courses.getPageList();
		return page;
	}

	/**
	 * 根据key搜索
	 * @param key
	 * @param now
	 * @param pageSize
	 * @return
	 */
	public List<TCourse> pageListByKey(String key,int now,int pageSize) {
		List<TCourse> list = course.listByKey(key);
		PagedListHolder<TCourse> courses = new PagedListHolder<>(list);
		courses.setPage(now);
		courses.setPageSize(pageSize);
		List<TCourse> page = courses.getPageList();
		return page;
	}
	
	public void save(TCourse course) {
		TUser tUser = user.findById(47).get();
		course.setCourseStarLevel(0.0f);
		course.setTUser(tUser);		
		for(TChapter chapter : course.getTChapters()) {
			
			chapter.setTCourse(course);
			for(TSection section : chapter.getTSections()) {
				section.setTChapter(chapter);
			}
		}
		TCourse course2 = this.course.save(course);
	}
	
	public CourseDetailStatus getCourseByCid(int cid) {
		System.out.println("查询课程信息");
		try {
			Optional<TCourse> tcourse = course.findById(cid);
			TCourse coursePoJo = tcourse.get();
			CourseVO vo = new CourseVO();
			PackageUtil.packageObject(vo, coursePoJo);
			vo.setCourseIntro(coursePoJo.getCourseIntro());
			List<ChapterVO> chapters = new ArrayList<>();
			for(TChapter chapter : coursePoJo.getTChapters()) {
				ChapterVO chapterVO = new ChapterVO();
				PackageUtil.packageObject(chapterVO, chapter);
				List<SectionVO> sections = new ArrayList<>();
				for(TSection section : chapter.getTSections()) {
					SectionVO sectionVO = new SectionVO();
					PackageUtil.packageObject(sectionVO, section);
					sections.add(sectionVO);
				}
				Collections.sort(sections);
				chapterVO.setSections(sections);
				chapters.add(chapterVO);
			}
			vo.setChapters(chapters);
//			chapters.sort(Comparator.naturalOrder());
			Collections.sort(chapters);
			vo.setChapters(chapters);
			CourseDetailStatus status = new CourseDetailStatus("success", 1, "成功");
			UserVO user = new UserVO();
			PackageUtil.packageObject(user, coursePoJo.getTUser());
			status.setUser(user);
			status.setCourses(vo);
			return status;
		} catch (Exception e) {
			return new CourseDetailStatus("fail",-1,ExceptionUtils.getFullStackTrace(e));
		}

	}
	
	public void del(int courseId) {
		TCourse tCourse = course.findById(courseId).get();
		course.delete(tCourse);
	}
	
	
	public TCourse onlyGetCourseById(int cid) {
		return course.getOne(cid);
	}
}
