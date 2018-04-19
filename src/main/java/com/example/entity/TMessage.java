package com.example.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * TMessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_message", catalog = "is")
public class TMessage implements java.io.Serializable {

	// Fields

	private Long messageId;
	private TUser TUserByReceiveId;
	private TUser TUserBySendId;
	private String sendTime;
	private String message;
	private Boolean status;

	// Constructors

	/** default constructor */
	public TMessage() {
	}

	/** minimal constructor */
	public TMessage(Long messageId, TUser TUserByReceiveId,
			TUser TUserBySendId, String message, Boolean status) {
		this.messageId = messageId;
		this.TUserByReceiveId = TUserByReceiveId;
		this.TUserBySendId = TUserBySendId;
		this.message = message;
		this.status = status;
	}

	/** full constructor */
	public TMessage(Long messageId, TUser TUserByReceiveId,
			TUser TUserBySendId, String sendTime, String message, Boolean status) {
		this.messageId = messageId;
		this.TUserByReceiveId = TUserByReceiveId;
		this.TUserBySendId = TUserBySendId;
		this.sendTime = sendTime;
		this.message = message;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "message_id", unique = true, nullable = false)
	public Long getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receive_id", nullable = false)
	public TUser getTUserByReceiveId() {
		return this.TUserByReceiveId;
	}

	public void setTUserByReceiveId(TUser TUserByReceiveId) {
		this.TUserByReceiveId = TUserByReceiveId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "send_id", nullable = false)
	public TUser getTUserBySendId() {
		return this.TUserBySendId;
	}

	public void setTUserBySendId(TUser TUserBySendId) {
		this.TUserBySendId = TUserBySendId;
	}

	@Column(name = "send_time", length = 50)
	public String getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "message", nullable = false, length = 500)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "status", nullable = false)
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}