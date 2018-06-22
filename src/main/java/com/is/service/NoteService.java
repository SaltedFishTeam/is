package com.is.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import com.is.entity.TNode;
import com.is.entity.TUser;
import com.is.json.entty.NoteVO;
import com.is.json.status.Status;
import com.is.repository.NoteClassifyRepository;
import com.is.repository.NoteRepository;
import com.is.util.PackageUtil;

@Service
public class NoteService {

	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private NoteClassifyRepository noteClassifyRepository;
	
	/**
	 * 保存笔记草稿
	 * @param vo
	 * @return
	 */
	public int draftSave(NoteVO vo,TUser user) {
		TNode note = new TNode();
		note.setTUser(user);
		PackageUtil.packageObject(note, vo);
		System.out.println(vo.getContent());
		note.setFlag(0);
		note.setStatIds("");
		note = noteRepository.save(note);
		return note.getNodeId();
	}
	
	/**
	 * 笔记保存
	 * vo里面会有noteId
	 * @param vo
	 * @param user
	 */
	public void noteSave(NoteVO vo,TUser user) {
		TNode note = new TNode();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(format.format(System.currentTimeMillis()));
		System.out.println("时间" + note.getTime());
		note.setTUser(user);
		note.setSkimNum(0);
		note.setStatNum(0);
		note.setTClassifyNode(noteClassifyRepository
				.findById(vo.getClassify()).get());
		note.setNodeTagNames(vo.getTagNames());
		note.setNodeTagIds(vo.getTagIds());
		PackageUtil.packageObject(note, vo);
		note.setTime(format.format(System.currentTimeMillis()));
		note.setFlag(1);
		note.setStatIds("");
		note = noteRepository.save(note);
		
	}
	
	public void noteDel(int id) {
		noteRepository.delete(noteRepository.findById(id).get());
		
	}
	public PagedListHolder<TNode> list(int uid,int now,int pageSize) {
		List<TNode> list = noteRepository.findByUid(uid);
		PagedListHolder<TNode> pages = new PagedListHolder<>(list);
		pages.setPage(now);
		pages.setPageSize(pageSize);
		return pages;
	}
	
	public PagedListHolder<TNode> list(int uid,int now,int pageSize,int classifyId) {
		List<TNode> list = noteRepository.findByUidAndClassifyId(uid, classifyId);
		PagedListHolder<TNode> pages = new PagedListHolder<>(list);
		pages.setPage(now);
		pages.setPageSize(pageSize);
		return pages;
	}
	
	public TNode findById(Integer noteId) {
		TNode note = noteRepository.findById(noteId).get();
		return note;
	}
	
	public void doStat(Integer uid,Integer noteId) {
		TNode tNode = noteRepository.findById(noteId).get();
		tNode.setStatIds(tNode.getStatIds() + " " + uid + " ");
		tNode.setStatNum(tNode.getStatNum() + 1);
		noteRepository.save(tNode);
	}
	
	public void unDoStat(Integer uid,Integer noteId) {
		TNode tNode = noteRepository.findById(noteId).get();
		String statIds = tNode.getStatIds().replace(" " + uid + " ", "");
		tNode.setStatIds(statIds);
		tNode.setStatNum(tNode.getStatNum() - 1);
		noteRepository.save(tNode);
	}
	
	public PagedListHolder<NoteVO> findHotNotes() {
		List<NoteVO> notes = noteRepository.findHotNotes();
		PagedListHolder<NoteVO> pages = new PagedListHolder<>();
		pages.setSource(notes);
		return pages;
	}
	
	
	public PagedListHolder<NoteVO> findNewNotes() {
		List<NoteVO> notes = noteRepository.findNewNotes();
		PagedListHolder<NoteVO> pages = new PagedListHolder<>();
		pages.setSource(notes);
		return pages;
	}
	
	public PagedListHolder<NoteVO> findKeyNotes(String key) {
		List<NoteVO> notes = noteRepository.findKeyNotes(key);
		PagedListHolder<NoteVO> pages = new PagedListHolder<>();
		pages.setSource(notes);
		return pages;
	}
}
