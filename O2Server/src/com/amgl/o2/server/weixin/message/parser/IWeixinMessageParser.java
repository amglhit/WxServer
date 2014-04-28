package com.amgl.o2.server.weixin.message.parser;

import org.jdom2.Document;
import org.jdom2.JDOMException;

import com.amgl.o2.server.weixin.message.WeixinRecvMessage;

public interface IWeixinMessageParser {
	public WeixinRecvMessage parse(Document doc) throws JDOMException;
}
