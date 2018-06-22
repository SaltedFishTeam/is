package com.is.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.jms.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.is.entity.NoteReply;
import com.is.entity.TClassifyNode;
import com.is.entity.TNode;
import com.is.entity.TNodeMessage;
import com.is.entity.TNodeTag;
import com.is.entity.TUser;
import com.is.json.entty.BaseUserVO;
import com.is.json.entty.NoteClassifyVO;
import com.is.json.entty.NoteMessageVO;
import com.is.json.entty.NoteReplyVO;
import com.is.json.entty.NoteVO;
import com.is.json.entty.UserVO;
import com.is.json.status.DraftNoteSaveStatus;
import com.is.json.status.EvaluateStatus;
import com.is.json.status.GetClassifyStatus;
import com.is.json.status.HotListStatus;
import com.is.json.status.LoadNoteStatus;
import com.is.json.status.MakeNoteStatus;
import com.is.json.status.NotePageListStatus;
import com.is.json.status.ReplyNoteStatus;
import com.is.json.status.Status;
import com.is.repository.AddClassifyStatus;
import com.is.service.MessageService;
import com.is.service.NoteClassifyService;
import com.is.service.NoteMessageService;
import com.is.service.NoteReplyService;
import com.is.service.NoteService;
import com.is.service.NoteTagService;
import com.is.util.PackageUtil;
import com.is.util.RelativeDateFormat;
import com.is.websocket.SocketServer;
import com.is.websocket.vo.WebSocketMessageVO;


@RequestMapping("note")
@RestController
public class NoteController {
	
	@Autowired
	private NoteClassifyService noteClassify;
	@Autowired
	private NoteTagService noteTag;
	@Autowired
	private NoteService noteService;
	@Autowired
	private NoteMessageService noteMessageService;
	@Autowired
	private NoteReplyService noteReplyService;
	@Autowired
	private MessageService messageService;
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping(value="/init",method=RequestMethod.GET)
	public MakeNoteStatus init(HttpSession session) {
		TUser user = (TUser) session.getAttribute("user");
		MakeNoteStatus status = null;
		List<NoteClassifyVO> classifies = null;
		List<TNodeTag> tags = null;
		try {
			List<TClassifyNode> classifiesList = noteClassify.list(user.getUid());
			classifies = new ArrayList<NoteClassifyVO>();
			for(TClassifyNode note : classifiesList) {
				NoteClassifyVO vo = new NoteClassifyVO();
				PackageUtil.packageObject(vo, note);
				classifies.add(vo);
			}
			tags = noteTag.list();
		}catch(Exception e) {
			status = new MakeNoteStatus("fail", -1, "查询文章分类或者文章标签失败");
			return status;
		}
		status = new MakeNoteStatus("success", 1, "成功");
		status.setClassifies(classifies);
		status.setTags(tags);
		return status;
	}

	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Status makeNote(HttpSession session
			,@RequestBody NoteVO note) {
		System.out.println(note.getFilesUrl() + "文件路径");
		note.setTagIds(note.getTagIds() + " ");
		TUser user = (TUser) session.getAttribute("user");
		if(user == null) return new Status("fail", -1, "登陆超时");
		try {
//			System.out.println);
			noteService.noteSave(note, user);			
		} catch(Exception e) {
			e.printStackTrace();
			return new Status("fail",-1,"发布失败");
		}

		return new Status("success", 1, "发布成功");
	}
	
	
	@RequestMapping(value="/classify/delete/{classifyId}",method=RequestMethod.GET)
	public Status delClassify(HttpSession session,
			@PathVariable("classifyId") Integer classifyId) {
		TUser user = (TUser) session.getAttribute("user");
		if(user == null) return new Status("fail",-1,"请登陆");
		boolean flag = noteClassify.delete(classifyId);
		if(flag) return new Status("success",1,"登陆成功");
		else return new Status("fail",-1,"请登陆");
	}
	
	
	@RequestMapping(value="/classify/add/{classify}",method=RequestMethod.PUT)
	public AddClassifyStatus addClassify(HttpSession session,
			@PathVariable("classify") String classify) {
		NoteClassifyVO vo = null;
		TUser user = (TUser) session.getAttribute("user");
		if(user == null) return new AddClassifyStatus("fail", -1, "请先登陆");
		try {
			TClassifyNode newClassify = noteClassify.addClassify(classify, user.getUid());
			vo = new NoteClassifyVO();
			PackageUtil.packageObject(vo, newClassify);
		}catch(Exception e) {
			return new AddClassifyStatus("fail", -1, "添加失败");
		}
		AddClassifyStatus status = new AddClassifyStatus("success", 1, "添加成功");
		status.setNoteClassify(vo);
		return status;
	}
	
	@RequestMapping(value="/draft/save",method=RequestMethod.POST)
	public DraftNoteSaveStatus saveNoteDraft(HttpSession session
			,@RequestBody NoteVO note) {
		int draftId = 0;
		TUser user = (TUser) session.getAttribute("user");
		try {
			draftId = noteService.draftSave(note,user);
		}catch(Exception e) {
			e.printStackTrace();
			return new DraftNoteSaveStatus("fail", -1, "保存失败");
		}
		DraftNoteSaveStatus status = new DraftNoteSaveStatus("success", 1, "保存成功");
		status.setNoteId(draftId);
		return status;
	}
	
	@RequestMapping(value="/classify",method=RequestMethod.GET)
	public GetClassifyStatus selectClassify(HttpSession session) {
		TUser user = (TUser) session.getAttribute("user");
		List<NoteClassifyVO> classifies = new ArrayList<NoteClassifyVO>();
		try {
			List<TClassifyNode> list = noteClassify.list(user.getUid());
			
			for(TClassifyNode classify : list) {
				NoteClassifyVO vo = new NoteClassifyVO();
				PackageUtil.packageObject(vo, classify);
				classifies.add(vo);
			}
		} catch(Exception e) {
			return new GetClassifyStatus("fail", -1, e.getLocalizedMessage());
		}

		GetClassifyStatus status = new GetClassifyStatus("success", 1, "查询成功");
		status.setClassifies(classifies);
		return status;
	}
	
	@RequestMapping(value="/user/{classify}-{now}-{pageSize}",method=RequestMethod.GET)
	public Status listNote(HttpSession session,
			@PathVariable("now")int now,@PathVariable("classify")int classifyId,
			@PathVariable("pageSize")int pageSize) {
		TUser user = (TUser) session.getAttribute("user");
		//查询所有笔记
		NotePageListStatus status = null;
		PagedListHolder<TNode> pages = null;
		try {
			if(classifyId <= 0) {
				pages = noteService.list(user.getUid(), now, pageSize);
				
			} else {
				pages = noteService.list(user.getUid(), now, pageSize, classifyId);
			}
			List<TNode> pageList = pages.getPageList();
			List<NoteVO> notes = new ArrayList<NoteVO>();
			for(TNode note : pageList) {
				NoteVO vo = new NoteVO();
				PackageUtil.packageObject(vo, note);
				vo.setContent("");
				if(note.getNodeTagIds() != null && note.getNodeTagNames() != null) {
					vo.setTagIdList(note.getNodeTagIds().split(" "));
					vo.setTagNameList(note.getNodeTagNames().split(" "));
				}
				vo.setClassifyName(note.getTClassifyNode().getName());
				notes.add(vo);
			}
			status = new NotePageListStatus("success", 1, "成功");
			status.setNow(now);
			status.setPageCount(pages.getSource().size());
			status.setList(notes);
		} catch(Exception e) {
			status = new NotePageListStatus("fail", -1, ExceptionUtils.getFullStackTrace(e));
		}
		
		return status;
	}
	
	@RequestMapping(value="{noteId}",method=RequestMethod.GET)
	public LoadNoteStatus loadNote(HttpSession session,
			@PathVariable("noteId")Integer noteId,HttpServletResponse response,
			HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		boolean flag = false;
		for(Cookie cookie : cookies) {
			System.out.println(cookie.getName() + " : " + cookie.getValue());
		}
		Cookie cookie = new Cookie("noteId", String.valueOf(noteId));
//		cookie.setPath();
		response.addCookie(new Cookie("noteId", String.valueOf(noteId)));
		LoadNoteStatus status = null;
		List<NoteMessageVO> messages = null;
		UserVO userVO = new UserVO();
		NoteVO noteVO = new NoteVO();
		try {

			TNode tNote = noteService.findById(noteId);
			TUser user = tNote.getTUser();
			userVO.setUid(user.getUid());
			userVO.setUsername(user.getUsername());
			userVO.setIntro(user.getIntro());
			userVO.setAvatar(user.getAvatar());
			PackageUtil.packageObject(noteVO, tNote);
			noteVO.setContent(tNote.getContent());
			noteVO.setClassifyName(tNote.getTClassifyNode().getName());
			noteVO.setClassify(tNote.getTClassifyNode().getClassifyNotesId());
			noteVO.setTagIds(tNote.getNodeTagIds());
			noteVO.setTagNames(tNote.getNodeTagNames());
			if(tNote.getNodeTagIds() != null && tNote.getNodeTagNames() != null) {
				System.out.println("拆开");
				System.out.println(tNote.getNodeTagIds().trim());
				for(String s : tNote.getNodeTagIds().trim().split(" ")) System.out.println(s);
				noteVO.setTagIdList(tNote.getNodeTagIds().trim().split(" "));
				noteVO.setTagNameList(tNote.getNodeTagNames().trim().split(" "));
			}
			TUser userSession = (TUser) session.getAttribute("user");
			messages = noteMessageService.listByNoteId(noteId);
			
			if(userSession != null) {
				Integer uid = userSession.getUid();
				if(tNote.getStatIds() != null && 
						tNote.getStatIds().contains(" " + uid + " "))
					noteVO.setCanStat(false);
				else
					noteVO.setCanStat(true);
			} else {
				noteVO.setCanStat(true);
			}
		} catch(Exception e) {
			e.printStackTrace();
			status = new LoadNoteStatus("fail",-1,e.getMessage());
		}
		if(status == null) {
			status = new LoadNoteStatus("success", 1, "成功");
			status.setUser(userVO);
			status.setNote(noteVO);
			status.setMessages(messages);
		}
		System.out.println(status);
		return status;
	}
	
	/**
	 * status为true的时候是点赞
	 * status为false的时候是取消点赞
	 * @param noteId
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/stat/{noteId}/{status}",method=RequestMethod.PUT)
	public Status stat(HttpSession session,
			@PathVariable("noteId")Integer noteId,
			@PathVariable("status") boolean status) {
		TUser user = (TUser) session.getAttribute("user");
		if(user == null) {
			return new Status("fail", -1, "请登录在点赞");
		}
		if(status) {
			noteService.doStat(user.getUid(), noteId);
		} else {
			noteService.unDoStat(user.getUid(), noteId);
		}
		return new Status("success", 1, "点赞成功");
	}
	
	@RequestMapping(value="/query/{nowPage}/{key}",method=RequestMethod.GET)
	public HotListStatus queryKey(@PathVariable("nowPage")int nowPage,@PathVariable("key")String key) {
		System.out.println(key);
		PagedListHolder<NoteVO> pages = noteService.findKeyNotes(key);
		pages.setPageSize(8);
		pages.setPage(nowPage - 1);
		List<NoteVO> notes = pages.getPageList();
		for(NoteVO vo : notes) {
			if(vo.getTagNames() != null) {
				vo.setTagNameList(vo.getTagNames().split(" "));
			} else {
				vo.setTagNameList(new String[] {});
			}
		}
		HotListStatus status = new HotListStatus("success", 1, "成功");
		status.setPage(notes);
		status.setCount(pages.getSource().size());
		return status;
		
	}
	
	@RequestMapping(value="/evaluate/{noteId}",method=RequestMethod.POST)
	public EvaluateStatus evaluate(HttpSession session,
			@PathVariable("noteId")int noteId,
			@RequestBody Map<String,Object> params) {
		String content = (String) params.get("content");
		TUser user = (TUser) session.getAttribute("user");
		if(user == null) return new EvaluateStatus("fail", -1, "请登陆后再评论");
		try {
			TNodeMessage noteMessage = noteMessageService.save(content,user.getUid(),noteId);
			EvaluateStatus status = new EvaluateStatus("success", 1, "评价成功");
			NoteMessageVO vo = new NoteMessageVO();
			
			vo.setContent(noteMessage.getContent());
			vo.setId(noteMessage.getNodeMsgId());
			vo.setTime(noteMessage.getTime());
			vo.setUser(new BaseUserVO(
					noteMessage.getTUser().getUid(),
					noteMessage.getTUser().getUsername(),
					noteMessage.getTUser().getAvatar()));
			vo.setUrl(String.valueOf(params.get("url")) + "/" + noteMessage.getNodeMsgId());
			Integer uid = (Integer) params.get("noteUid");
			
			String time = vo.getTime();
			vo.setTime(format.format(System.currentTimeMillis()));
			WebSocketMessageVO messageVO = new WebSocketMessageVO(0, uid, 6, "成功", -1);
			
			messageVO.setMessage(JSON.toJSONString(vo));
			String json = JSON.toJSONString(messageVO);
			System.out.println(JSON.parseObject(json));
			messageService.saveChat(messageVO, false);
			System.out.println("评论" + uid);
			if(SocketServer.sessionPool.containsKey(uid)) {
				System.out.println("发送");
				SocketServer.sendMsg(uid, json);
			}
			vo.setTime(time);
			status.setVo(vo);
			return status;
			
		} catch (Exception e) {
			e.printStackTrace();
			return new EvaluateStatus("fail", -1, ExceptionUtils.getFullStackTrace(e));
		}
	}

	@RequestMapping(value="/reply/{nmId}/ruser/{ruid}",method=RequestMethod.POST)
	public ReplyNoteStatus reply(HttpSession session,@PathVariable("nmId") int nmId,
			@PathVariable("ruid") int ruid,
			@RequestBody Map<String,String> params) {
		try {
			TUser user = (TUser) session.getAttribute("user");
			String content = params.get("content");
			NoteReply noteReply = noteReplyService.save(user.getUid(), ruid, nmId, content);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
			NoteReplyVO vo = new NoteReplyVO(noteReply.getNoteReplyId(),noteReply.getContent(),
					RelativeDateFormat.format(format.parse(noteReply.getTime())),
							noteReply.getTUser().getUid(),noteReply.getTUser().getUsername(),noteReply.getTUser().getUsername(),
							noteReply.getRuser().getUid(),noteReply.getRuser().getUsername(),noteReply.getRuser().getUsername());
			ReplyNoteStatus status = new ReplyNoteStatus("success",1,"成功");
			
			
			NoteMessageVO mvo = new NoteMessageVO();
			
			mvo.setContent(noteReply.getContent());
			mvo.setId(noteReply.getTNodeMessage().getNodeMsgId());
			mvo.setTime(noteReply.getTime());
			mvo.setUser(new BaseUserVO(
					noteReply.getTUser().getUid(),
					noteReply.getTUser().getUsername(),
					noteReply.getTUser().getAvatar()));
			mvo.setUrl(String.valueOf(params.get("url")));
			
			vo.setTime(format.format(System.currentTimeMillis()));
			WebSocketMessageVO messageVO = new WebSocketMessageVO(0, ruid, 6, "成功", -1);
			
			messageVO.setMessage(JSON.toJSONString(mvo));
			String json = JSON.toJSONString(messageVO);
			
			System.out.println("评论" + ruid);
			if(SocketServer.sessionPool.containsKey(ruid)) {
				messageService.saveChat(messageVO, true);
				SocketServer.sendMsg(ruid, json);
			} else {
				messageService.saveChat(messageVO, false);
			}
			
			
			status.setReply(vo);
			return status;
		} catch (Exception e) {
			return new ReplyNoteStatus("fail", -1, ExceptionUtils.getFullStackTrace(e));
		}
	}
	
	@RequestMapping(value="/del/{noteId}",method=RequestMethod.GET)
	public Status delNote(@PathVariable("noteId")Integer nodeId) {

		System.out.println(nodeId);
		try {
			noteService.noteDel(nodeId);			
			return new Status("success", 1, "成功");
		}catch (Exception e) {
			e.printStackTrace();
			return new Status("fail", -1, "删除失败");
		}

	}
	

	@RequestMapping(value="/hot/list/{nowPage}",method=RequestMethod.GET)
	public HotListStatus hotList(@PathVariable("nowPage") int nowPage) {
		try {
			PagedListHolder<NoteVO> pages = noteService.findHotNotes();
			pages.setPage(nowPage - 1);
			pages.setPageSize(8);
			List<NoteVO> notes = pages.getPageList();
			for(NoteVO vo : notes) {
				if(vo.getTagNames() != null) {
					vo.setTagNameList(vo.getTagNames().split(" "));
				} else {
					vo.setTagNameList(new String[] {});
				}
			}
			HotListStatus status = new HotListStatus("success", 1, "成功");
			status.setPage(notes);
			status.setCount(pages.getSource().size());
			return status;
		} catch (Exception e) {
			return new HotListStatus("fail", -1, ExceptionUtils.getFullStackTrace(e));
		}
	}
	
	@RequestMapping(value="/new/list/{nowPage}",method=RequestMethod.GET)
	public @ResponseBody HotListStatus newList(@PathVariable("nowPage") int nowPage) {
		System.out.println("new");
		try {
			PagedListHolder<NoteVO> pages = noteService.findNewNotes();
			pages.setPage(nowPage - 1);
			pages.setPageSize(8);
			List<NoteVO> notes = pages.getPageList();
			for(NoteVO vo : notes) {
				if(vo.getTagNames() != null) {
					vo.setTagNameList(vo.getTagNames().split(" "));
				} else {
					vo.setTagNameList(new String[] {});
				}
			}
			HotListStatus status = new HotListStatus("success", 1, "成功");
			status.setPage(notes);
			status.setCount(pages.getSource().size());
			return status;
		} catch (Exception e) {
			return new HotListStatus("fail", -1, ExceptionUtils.getFullStackTrace(e));
		}
	}
}
