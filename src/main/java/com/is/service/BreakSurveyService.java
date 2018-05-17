package com.is.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.entity.TBreakSurvey;
import com.is.repository.BreakSurveyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BreakSurveyService {

	@Autowired
	private BreakSurveyRepository breakSurvey;

	public void setBreakSurvey(BreakSurveyRepository breakSurvey) {
		this.breakSurvey = breakSurvey;
	}
	
	/**
	 * 性格测试答案保存成功返回true
	 * 保存失败返回false
	 * @param brakSurvey
	 * @return
	 */
	public boolean save(TBreakSurvey brakSurvey) {
		boolean flag = true;
		try {
			breakSurvey.save(brakSurvey);			
		} catch(Exception e) {
			log.error("退出性格测试保存答案失败");
			flag = false;
		}
		return flag;
		
	}
}
