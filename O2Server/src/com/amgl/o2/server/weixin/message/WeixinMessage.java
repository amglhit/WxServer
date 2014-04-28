package com.amgl.o2.server.weixin.message;

public class WeixinMessage {
	public static final String MSG_TYPE_TEXT = "text";
	public static final String MSG_TYPE_LOCATION = "location";
	public static final String MSG_TYPE_EVENT = "event";
	public static final String MSG_TYPE_IMAGE = "image";
	public static final String MSG_TYPE_LINK = "link";
	public static final String MSG_TYPE_MUSIC = "music";
	public static final String MSG_TYPE_NEWS = "news";

	private String toUser;
	private String fromUser;
	private String createDt;
	private String msgType;

	public WeixinMessage(String toUser, String fromUser, String createDt,
			String msgType) {
		this.toUser = toUser;
		this.fromUser = fromUser;
		this.createDt = createDt;
		this.msgType = msgType;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getCreateDt() {
		return createDt;
	}

	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
}
