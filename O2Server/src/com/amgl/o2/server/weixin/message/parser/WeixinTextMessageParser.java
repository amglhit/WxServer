package com.amgl.o2.server.weixin.message.parser;

import org.jdom2.Element;
import org.jdom2.JDOMException;

import com.amgl.o2.server.weixin.message.WeixinRecvMessage;
import com.amgl.o2.server.weixin.message.WeixinRecvTextMessage;

public class WeixinTextMessageParser extends WeixinBaseMessageParser {

	@Override
	protected WeixinRecvTextMessage parse(Element root, WeixinRecvMessage msg)
			throws JDOMException {
		return new WeixinRecvTextMessage(msg, getElementText(root, "Content"));
	}
}
