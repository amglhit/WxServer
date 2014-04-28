package com.amgl.o2.server.weixin.message.parser;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.output.XMLOutputter;

import com.amgl.o2.server.weixin.message.WeixinRecvMessage;

public abstract class WeixinBaseMessageParser implements IWeixinMessageParser {
	protected final Log LOG = LogFactory.getLog(WeixinBaseMessageParser.class);

	public WeixinRecvMessage parse(Document doc) throws JDOMException {
		try {
			new XMLOutputter().output(doc, System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Element root = doc.getRootElement();
		String toUserName = getElementText(root, "ToUserName");
		String fromUserName = getElementText(root, "FromUserName");
		String createTime = getElementText(root, "CreateTime");
		String msgType = getElementText(root, "MsgType");
		String msgId = getElementText(root, "MsgId");

		return parse(root, new WeixinRecvMessage(toUserName, fromUserName,
				createTime, msgType, msgId));
	}

	protected abstract WeixinRecvMessage parse(Element root,
			WeixinRecvMessage msg) throws JDOMException;

	protected String getElementText(Element elem, String name)
			throws JDOMException {
		Element e = elem.getChild(name);
		String text = e.getText();
		return text;
	}
}
