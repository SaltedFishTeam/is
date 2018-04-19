package com.example.entity;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_user", catalog = "is")
public class TUser implements java.io.Serializable {

	// Fields

	private Long uid;
	private String username;
	private String account;
	private String pwd;
	private String phone;
	private Integer role;
	private Double credit;
	private String intro;
	private String friends;
	private Set<TMessage> TMessagesForSendId = new HashSet<TMessage>(0);
	private Set<TTopicreply> TTopicreplies = new HashSet<TTopicreply>(0);
	private Set<TTeacher> TTeachers = new HashSet<TTeacher>(0);
	private Set<TTopic> TTopics = new HashSet<TTopic>(0);
	private Set<TClassifyNode> TClassifyNodes = new HashSet<TClassifyNode>(0);
	private Set<TNodeMessage> TNodeMessages = new HashSet<TNodeMessage>(0);
	private Set<TActionReply> TActionReplies = new HashSet<TActionReply>(0);
	private Set<TStudentwork> TStudentworks = new HashSet<TStudentwork>(0);
	private Set<TPlan> TPlans = new HashSet<TPlan>(0);
	private Set<TSc> TScs = new HashSet<TSc>(0);
	private Set<TQuestion> TQuestions = new HashSet<TQuestion>(0);
	private Set<TTest> TTests = new HashSet<TTest>(0);
	private Set<TSearch> TSearchs = new HashSet<TSearch>(0);
	private Set<TBlacklist> TBlacklists = new HashSet<TBlacklist>(0);
	private Set<TStudent> TStudents = new HashSet<TStudent>(0);
	private Set<TMessage> TMessagesForReceiveId = new HashSet<TMessage>(0);
	private Set<TActionMessage> TActionMessages = new HashSet<TActionMessage>(0);
	private Set<TTopicmessage> TTopicmessages = new HashSet<TTopicmessage>(0);
	private Set<TAction> TActions = new HashSet<TAction>(0);
	private Set<TCoursecomment> TCoursecomments = new HashSet<TCoursecomment>(0);

	// Constructors

	/** default constructor */
	public TUser() {
	}

	/** minimal constructor */
	public TUser(Long uid, String username, String account, String pwd,
			String phone, Integer role, Double credit) {
		this.uid = uid;
		this.username = username;
		this.account = account;
		this.pwd = pwd;
		this.phone = phone;
		this.role = role;
		this.credit = credit;
	}

	/** full constructor */
	public TUser(Long uid, String username, String account, String pwd,
			String phone, Integer role, Double credit, String intro,
			String friends, Set<TMessage> TMessagesForSendId,
			Set<TTopicreply> TTopicreplies, Set<TTeacher> TTeachers,
			Set<TTopic> TTopics, Set<TClassifyNode> TClassifyNodes,
			Set<TNodeMessage> TNodeMessages, Set<TActionReply> TActionReplies,
			Set<TStudentwork> TStudentworks, Set<TPlan> TPlans, Set<TSc> TScs,
			Set<TQuestion> TQuestions, Set<TTest> TTests,
			Set<TSearch> TSearchs, Set<TBlacklist> TBlacklists,
			Set<TStudent> TStudents, Set<TMessage> TMessagesForReceiveId,
			Set<TActionMessage> TActionMessages,
			Set<TTopicmessage> TTopicmessages, Set<TAction> TActions,
			Set<TCoursecomment> TCoursecomments) {
		this.uid = uid;
		this.username = username;
		this.account = account;
		this.pwd = pwd;
		this.phone = phone;
		this.role = role;
		this.credit = credit;
		this.intro = intro;
		this.friends = friends;
		this.TMessagesForSendId = TMessagesForSendId;
		this.TTopicreplies = TTopicreplies;
		this.TTeachers = TTeachers;
		this.TTopics = TTopics;
		this.TClassifyNodes = TClassifyNodes;
		this.TNodeMessages = TNodeMessages;
		this.TActionReplies = TActionReplies;
		this.TStudentworks = TStudentworks;
		this.TPlans = TPlans;
		this.TScs = TScs;
		this.TQuestions = TQuestions;
		this.TTests = TTests;
		this.TSearchs = TSearchs;
		this.TBlacklists = TBlacklists;
		this.TStudents = TStudents;
		this.TMessagesForReceiveId = TMessagesForReceiveId;
		this.TActionMessages = TActionMessages;
		this.TTopicmessages = TTopicmessages;
		this.TActions = TActions;
		this.TCoursecomments = TCoursecomments;
	}

	// Property accessors
	@Id
	@Column(name = "uid", unique = true, nullable = false)
	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	@Column(name = "username", nullable = false, length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "account", nullable = false, length = 50)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "pwd", nullable = false, length = 50)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "phone", nullable = false, length = 50)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "role", nullable = false)
	public Integer getRole() {
		return this.role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	@Column(name = "credit", nullable = false, precision = 22, scale = 0)
	public Double getCredit() {
		return this.credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	@Column(name = "intro", length = 200)
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name = "friends", length = 65535)
	public String getFriends() {
		return this.friends;
	}

	public void setFriends(String friends) {
		this.friends = friends;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUserBySendId")
	public Set<TMessage> getTMessagesForSendId() {
		return this.TMessagesForSendId;
	}

	public void setTMessagesForSendId(Set<TMessage> TMessagesForSendId) {
		this.TMessagesForSendId = TMessagesForSendId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TTopicreply> getTTopicreplies() {
		return this.TTopicreplies;
	}

	public void setTTopicreplies(Set<TTopicreply> TTopicreplies) {
		this.TTopicreplies = TTopicreplies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TTeacher> getTTeachers() {
		return this.TTeachers;
	}

	public void setTTeachers(Set<TTeacher> TTeachers) {
		this.TTeachers = TTeachers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TTopic> getTTopics() {
		return this.TTopics;
	}

	public void setTTopics(Set<TTopic> TTopics) {
		this.TTopics = TTopics;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TClassifyNode> getTClassifyNodes() {
		return this.TClassifyNodes;
	}

	public void setTClassifyNodes(Set<TClassifyNode> TClassifyNodes) {
		this.TClassifyNodes = TClassifyNodes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TNodeMessage> getTNodeMessages() {
		return this.TNodeMessages;
	}

	public void setTNodeMessages(Set<TNodeMessage> TNodeMessages) {
		this.TNodeMessages = TNodeMessages;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TActionReply> getTActionReplies() {
		return this.TActionReplies;
	}

	public void setTActionReplies(Set<TActionReply> TActionReplies) {
		this.TActionReplies = TActionReplies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TStudentwork> getTStudentworks() {
		return this.TStudentworks;
	}

	public void setTStudentworks(Set<TStudentwork> TStudentworks) {
		this.TStudentworks = TStudentworks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TPlan> getTPlans() {
		return this.TPlans;
	}

	public void setTPlans(Set<TPlan> TPlans) {
		this.TPlans = TPlans;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TSc> getTScs() {
		return this.TScs;
	}

	public void setTScs(Set<TSc> TScs) {
		this.TScs = TScs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TQuestion> getTQuestions() {
		return this.TQuestions;
	}

	public void setTQuestions(Set<TQuestion> TQuestions) {
		this.TQuestions = TQuestions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TTest> getTTests() {
		return this.TTests;
	}

	public void setTTests(Set<TTest> TTests) {
		this.TTests = TTests;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TSearch> getTSearchs() {
		return this.TSearchs;
	}

	public void setTSearchs(Set<TSearch> TSearchs) {
		this.TSearchs = TSearchs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TBlacklist> getTBlacklists() {
		return this.TBlacklists;
	}

	public void setTBlacklists(Set<TBlacklist> TBlacklists) {
		this.TBlacklists = TBlacklists;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TStudent> getTStudents() {
		return this.TStudents;
	}

	public void setTStudents(Set<TStudent> TStudents) {
		this.TStudents = TStudents;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUserByReceiveId")
	public Set<TMessage> getTMessagesForReceiveId() {
		return this.TMessagesForReceiveId;
	}

	public void setTMessagesForReceiveId(Set<TMessage> TMessagesForReceiveId) {
		this.TMessagesForReceiveId = TMessagesForReceiveId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TActionMessage> getTActionMessages() {
		return this.TActionMessages;
	}

	public void setTActionMessages(Set<TActionMessage> TActionMessages) {
		this.TActionMessages = TActionMessages;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TTopicmessage> getTTopicmessages() {
		return this.TTopicmessages;
	}

	public void setTTopicmessages(Set<TTopicmessage> TTopicmessages) {
		this.TTopicmessages = TTopicmessages;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TAction> getTActions() {
		return this.TActions;
	}

	public void setTActions(Set<TAction> TActions) {
		this.TActions = TActions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	public Set<TCoursecomment> getTCoursecomments() {
		return this.TCoursecomments;
	}

	public void setTCoursecomments(Set<TCoursecomment> TCoursecomments) {
		this.TCoursecomments = TCoursecomments;
	}

}