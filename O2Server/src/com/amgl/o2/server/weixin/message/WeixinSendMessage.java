package com.amgl.o2.server.weixin.message;

import org.jdom2.Document;
import org.jdom2.Element;

public class WeixinSendMessage extends WeixinMessage {
	private boolean star;

	public WeixinSendMessage(String toUser, String fromUser, String createDt,
			String msgType, boolean star) {
		super(toUser, fromUser, createDt, msgType);
		this.star = star;
	}

	public WeixinSendMessage(WeixinMessage msg) {
		this(msg.getToUser(), msg.getFromUser(), msg.getCreateDt(), msg
				.getMsgType(), false);
	}

	public WeixinSendMessage(WeixinSendMessage msg) {
		this(msg.getToUser(), msg.getFromUser(), msg.getCreateDt(), msg
				.getMsgType(), msg.isStar());
	}

	public boolean isStar() {
		return star;
	}

	public void setStar(boolean star) {
		this.star = star;
	}

	public Document toDocument() {
		Document doc = new Document();
		Element root = new Element("xml");
		doc.setRootElement(root);

		createElement(root, "ToUserName", getToUser());
		createElement(root, "FromUserName", getFromUser());
		createElement(root, "CreateTime", getCreateDt());
		createElement(root, "MsgType", getMsgType());
		createElement(root, "FuncFlag", isStar() ? "1" : "0");

		return doc;
	}

	protected Element createElement(Element parent, String name, String value) {
		Element elem = new Element(name);
		elem.setText(value);
		parent.getChildren().add(elem);
		return elem;
	}
}
