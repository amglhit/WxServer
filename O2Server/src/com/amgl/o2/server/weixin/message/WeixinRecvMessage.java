package com.amgl.o2.server.weixin.message;

public class WeixinRecvMessage extends WeixinMessage {
	private String msgId;

	public WeixinRecvMessage(String toUser, String fromUser, String createDt,
			String msgType, String msgId) {
		super(toUser, fromUser, createDt, msgType);
		this.msgId = msgId;
	}

	public WeixinRecvMessage(WeixinRecvMessage msg) {
		this(msg.getToUser(), msg.getFromUser(), msg.getCreateDt(), msg
				.getMsgType(), msg.getMsgId());
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}
