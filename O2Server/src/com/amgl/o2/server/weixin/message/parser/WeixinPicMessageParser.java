package com.amgl.o2.server.weixin.message.parser;

import org.jdom2.Element;
import org.jdom2.JDOMException;

import com.amgl.o2.server.weixin.message.WeixinRecvMessage;
import com.amgl.o2.server.weixin.message.WeixinRecvPicMessage;

public class WeixinPicMessageParser extends WeixinBaseMessageParser {

	@Override
	protected WeixinRecvPicMessage parse(Element root, WeixinRecvMessage msg)
			throws JDOMException {
		return new WeixinRecvPicMessage(msg, getElementText(root, "PicUrl"));
	}
}
