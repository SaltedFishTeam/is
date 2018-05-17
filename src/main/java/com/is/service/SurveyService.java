package com.is.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.entity.TStyleTag;
import com.is.entity.TSurvey;
import com.is.repository.SurveyRepository;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SurveyService {

	private static int TYPE = 4;
	
	@Autowired
	private SurveyRepository survey;

	@Autowired
	private StyleTagService styleTag;
	
	public void setSurvey(SurveyRepository survey) {
		this.survey = survey;
	}
	
	public List<TSurvey> list() {
		List<TSurvey> list = null;
		try {
			list = survey.findAll();			
		} catch(Exception e) {
			log.error("测试题库查询失败");
		}
		return list;
	}
	
	/**
	 * 将规定格式的答案转化为一个二维数组
	 * 数组的col是性格的种类数量
	 * row是题目总量 与 性格种类数量的 除 取整数
	 * @param answer
	 */
	private int[][] digstAnswer(String answer) {
		Pattern pattern = Pattern.compile("radio:(-?\\d)]");
		Matcher matcher = pattern.matcher(answer);
		int col = (int) styleTag.count();
		long count = survey.count();
		int row = (int) (count / col);
		int[][] val = new int[col][row];
		for(int i = 0; matcher.find(); i++) {
			val[i % col][i / col] 
					= Integer.valueOf(matcher.group(1));
		}
		return val;
	}

	/**
	 * 前端传来的答案
	 * 根据所罗门学习风格分析表
	 * 分析结果
	 * 返回的是一个一维数组
	 * 数组每一个值代表着对应类型性格的某一个性格
	 * @param answer
	 * @throws Exception
	 * 可能因为数组越界抛出异常
	 */
	public int[] analysis(String answer) throws Exception {
		int[] types = null;
		try {
			int[][] val = this.digstAnswer(answer);
			types = new int[val.length];
			for(int i = 0; i < val.length; i++) {
				for(int l = 0; l < val[i].length; l++) {
					//-1为未填选项
					if(val[i][l] != -1)
						types[i] += val[i][l];
				}
			}
			for(int i = 0; i < types.length; i++) {
				if(types[i] >= Math.ceil(val[i].length / 2.0)) {
					types[i] = 1;
				} else {
					types[i] = 0;
				}
			}
		} catch(Exception e) {
			log.error("分析所罗门测试表答案出错" + e.getMessage());
			throw new Exception("分析所罗门测试表答案出错");
		}
//		List<TStyleTag> list = styleTag.list();
//		String[] result = null;
//		if(list != null && types != null) {
//			result = new String[types.length];
//			for(int i = 0; i < types.length; i++) {
//				TStyleTag style = list.get(i);
//				String[] tags = style.getName().split("/");
//				result[i] = tags[types[i]];
//			}
//		}
		return types;
	}	
}
