package com.amgl.o2.server.weixin.message;

import org.jdom2.Document;

public class WeixinSendTextMessage extends WeixinSendMessage {
	private String content;

	public WeixinSendTextMessage(WeixinSendMessage msg, String content) {
		super(msg);
		setMsgType("text");
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public Document toDocument() {
		Document doc = super.toDocument();
		createElement(doc.getRootElement(), "Content", getContent());
		return doc;
	}
}
