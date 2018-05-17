package com.is.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_break_survey", catalog = "is")
public class TBreakSurvey {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer bid;
	
	@Column(name = "break_survey")
	private String breakSurvey;
	
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getBreakSurvey() {
		return breakSurvey;
	}
	public void setBreakSurvey(String breakSurvey) {
		this.breakSurvey = breakSurvey;
	}
	
	
}
