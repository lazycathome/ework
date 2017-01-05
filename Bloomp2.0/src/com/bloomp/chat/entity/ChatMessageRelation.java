package com.bloomp.chat.entity;

public class ChatMessageRelation {

	private long id;
	
	private long chatId;
	
	private long chatMessageId;
	
	private long createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getChatId() {
		return chatId;
	}

	public void setChatId(long chatId) {
		this.chatId = chatId;
	}

	public long getChatMessageId() {
		return chatMessageId;
	}

	public void setChatMessageId(long chatMessageId) {
		this.chatMessageId = chatMessageId;
	}
	
	public boolean isEmpty(){
		return id <= 0;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
}
