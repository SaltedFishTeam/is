package com.is.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_style_tag",catalog="is")
public class TStyleTag {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tid;
	
	@Column(name="name")
	private String name;

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
