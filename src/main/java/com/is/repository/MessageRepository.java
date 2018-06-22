package com.is.repository;

import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.is.entity.TMessage;
import com.is.entity.TUser;

public interface MessageRepository extends JpaRepository<TMessage, Integer> {

	/**
	 * 查询之前已经发送的
	 * 好友请求
	 * @param receive
	 * @param send
	 * @param status
	 * @param type
	 * @return
	 */
	@Query("select m"
			+ " from TMessage m"
			+ " where m.TUserByReceiveId = :receive"
			+ " and m.TUserBySendId = :send"
			+ " and m.status = :status and m.type = :type")
	public TMessage findAddFriendMessage(@Param("receive") TUser receive
			,@Param("send") TUser send,@Param("status") boolean status
			,@Param("type") int type);
	
	/**
	 * 根据receive和send
	 * 查询send发送的且处理的消息
	 * @param receive
	 * @param send
	 * @return
	 */
	@Query("select m"
			+ " from TMessage m"
			+ " where m.TUserByReceiveId = :receive"
			+ " and m.TUserBySendId = :send"
			+ " and m.status = false"
			+ " order by m.messageId DESC ")
	public List<TMessage> findByReceiveId(@Param("receive") TUser receive
			,@Param("send") TUser send);
	
	/**
	 * 查询未处理消息的发送者
	 * @param receive
	 * @return
	 */
	@Query("select DISTINCT m.TUserBySendId"
			+ " from TMessage m"
			+ " where m.TUserByReceiveId = :receive"
			+ " and m.status = false")
	public List<TUser> findSender(@Param("receive") TUser receive);
	
	@Query(value="select *"
			+ " from t_message"
			+ " where"
			+ " (send_id = :id1 and receive_id = :id2 and type = 2)"
			+ " or"
			+ " (send_id = :id2 and receive_id = :id1 and type = 2)"
			+ " limit 50;",nativeQuery=true)
	public List<TMessage> findChatMessage(@Param("id1") int id1,@Param("id2") int id2);
	
	@Query(value="select *"
			+ " from t_message"
			+ " where"
			+ " (send_id = :id1 and receive_id = :id2 and type = 6 and status = false)"
			+ " or"
			+ " (send_id = :id2 and receive_id = :id1 and type = 6 and status = false)",nativeQuery=true)
	public List<TMessage> findSystemChatMessage(@Param("id1") int id1,@Param("id2") int id2);
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update t_message"
			+ " set status = true"
			+ " where send_id = :sendId and receive_id = :receiveId",nativeQuery=true)
	public void updateUnDoMsg(@Param("sendId") int sendId, @Param("receiveId") int receiveId);
}
