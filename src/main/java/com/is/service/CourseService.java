package com.is.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.is.entity.TCourse;
import com.is.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository course;
	
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
	
	public TCourse getCourseByCid(int cid) {
		Optional<TCourse> tcourse = course.findById(cid);
		return tcourse.get();
	}
}
