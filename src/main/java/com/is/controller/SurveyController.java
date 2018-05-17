package com.is.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.is.entity.TBreakSurvey;
import com.is.entity.TSurvey;
import com.is.entity.TUser;
import com.is.json.status.AnalysisStatus;
import com.is.json.status.Status;
import com.is.json.status.SurveyListStatus;
import com.is.service.BreakSurveyService;
import com.is.service.SurveyService;

@Controller
public class SurveyController {

	@Autowired
	private SurveyService survey;
	
	@Autowired
	BreakSurveyService breakSurvey;
	
	public void setSurvey(SurveyService survey) {
		this.survey = survey;
	}

	public void setBreakSurvey(BreakSurveyService breakSurvey) {
		this.breakSurvey = breakSurvey;
	}


	@RequestMapping(value="/survey/list",method=RequestMethod.GET)
	public @ResponseBody SurveyListStatus test() {
		List<TSurvey> list = survey.list();
		SurveyListStatus status = new SurveyListStatus("success", Status.SUCCESS, "");
		if(list != null) {
			status.setList(list);
		}
		return status;
	}
	
	@RequestMapping(value="/survey/analysis",method=RequestMethod.POST)
	public @ResponseBody AnalysisStatus analysisSurvey(@RequestBody TBreakSurvey survey) {
		
		System.out.println(survey.getBreakSurvey());
		AnalysisStatus status = null;
		int[] result = null;
		try {
			result = this.survey.analysis(survey.getBreakSurvey());
		} catch (Exception e) {
			status = new AnalysisStatus("fail", -1, "数组超出边界");
		}
		if(status == null && result != null) {
			status = new AnalysisStatus("success", 1, "分析成功");
			status.setResult(result);
		}
		System.out.println(status);
		return status;
	}
	
	/**
	 * 保存用户在性格测试中中途退出的答案, url: /breakSurvey/save POST
	 * @param breakSurvey
	 * @return
	 */
	@RequestMapping(value="/breakSurvey/save",method=RequestMethod.POST)
	public @ResponseBody Status saveSurvey(HttpSession session,@RequestBody TBreakSurvey survey) {
		System.out.println(survey);
		Status status = null;
		TUser user = (TUser) session.getAttribute("user");
		System.out.println("breakSurvey" + user);
		survey.setBid(user.getUid());
		boolean flag = breakSurvey.save(survey);
		if(flag) status = new Status("success", 1, "答案保存成功");
		else status = new Status("fail",-1,"答案保存失败");
		return status;
	}

	
}
