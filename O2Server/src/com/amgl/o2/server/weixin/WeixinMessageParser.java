package com.amgl.o2.server.weixin;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.amgl.o2.server.weixin.message.WeixinRecvMessage;
import com.amgl.o2.server.weixin.message.parser.IWeixinMessageParser;
import com.amgl.o2.server.weixin.message.parser.WeixinBaseMessageParser;
import com.amgl.o2.server.weixin.message.parser.WeixinEventMessageParser;
import com.amgl.o2.server.weixin.message.parser.WeixinGeoMessageParser;
import com.amgl.o2.server.weixin.message.parser.WeixinLinkMessageParser;
import com.amgl.o2.server.weixin.message.parser.WeixinPicMessageParser;
import com.amgl.o2.server.weixin.message.parser.WeixinTextMessageParser;

public class WeixinMessageParser {
	private static final Log LOG = LogFactory.getLog(WeixinMessageParser.class);
	private static final Map<String, WeixinBaseMessageParser> recvParserMap = new HashMap<String, WeixinBaseMessageParser>();

	static {
		// 文本消息解析程序
		recvParserMap.put("text", new WeixinTextMessageParser());
		// 链接消息解析程序
		recvParserMap.put("link", new WeixinLinkMessageParser());
		// 地址消息解析程序
		recvParserMap.put("location", new WeixinGeoMessageParser());
		// 图片消息解析程序
		recvParserMap.put("image", new WeixinPicMessageParser());
		// 事件消息解析程序
		recvParserMap.put("event", new WeixinEventMessageParser());
	}

	public static WeixinRecvMessage parse(InputStream in) throws JDOMException,
			IOException {
		Document dom = new SAXBuilder().build(in);
		Element msgType = dom.getRootElement().getChild("MsgType");
		if (null != msgType) {
			String strMsgType = msgType.getText().toLowerCase();
			IWeixinMessageParser parser = recvParserMap.get(strMsgType);
			if (null != parser) {
				WeixinRecvMessage message = parser.parse(dom);
				return message;
			} else {
				LOG.warn("没有识别消息类型：" + strMsgType);
			}
		}
		return null;
	}
}
