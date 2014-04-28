package com.amgl.o2.server.weixin.message.parser;

import org.jdom2.Element;
import org.jdom2.JDOMException;

import com.amgl.o2.server.weixin.message.WeixinRecvEventMessage;
import com.amgl.o2.server.weixin.message.WeixinRecvMessage;

public class WeixinEventMessageParser extends WeixinBaseMessageParser {

	@Override
	protected WeixinRecvEventMessage parse(Element root, WeixinRecvMessage msg)
			throws JDOMException {
		String event = getElementText(root, "Event");
		String eventKey = getElementText(root, "EventKey");

		return new WeixinRecvEventMessage(msg, event, eventKey);
	}
}
