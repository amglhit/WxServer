package com.amgl.o2.server.weixin.message.parser;

import org.jdom2.Element;
import org.jdom2.JDOMException;

import com.amgl.o2.server.weixin.message.WeixinRecvLinkMessage;
import com.amgl.o2.server.weixin.message.WeixinRecvMessage;

public class WeixinLinkMessageParser extends WeixinBaseMessageParser {

	@Override
	protected WeixinRecvLinkMessage parse(Element root, WeixinRecvMessage msg)
			throws JDOMException {

		String title = getElementText(root, "Title");
		String description = getElementText(root, "Description");
		String url = getElementText(root, "Url");
		return new WeixinRecvLinkMessage(msg, title, description, url);
	}

}
