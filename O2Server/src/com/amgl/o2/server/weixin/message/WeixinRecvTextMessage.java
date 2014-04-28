package com.amgl.o2.server.weixin.message;

public class WeixinRecvTextMessage extends WeixinRecvMessage {
	private String content;

	public WeixinRecvTextMessage(WeixinRecvMessage msg, String content) {
		super(msg);
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
