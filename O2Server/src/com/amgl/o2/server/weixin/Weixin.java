package com.amgl.o2.server.weixin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.codec.digest.DigestUtils;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.output.XMLOutputter;

import com.amgl.o2.server.weixin.message.WeixinRecvMessage;
import com.amgl.o2.server.weixin.message.WeixinSendMessage;

public class Weixin {
	public static boolean access(String token, String signature,
			String timestamp, String nonce) {
		List<String> ss = new ArrayList<String>();
		ss.add(timestamp);
		ss.add(nonce);
		ss.add(token);

		Collections.sort(ss);

		StringBuilder builder = new StringBuilder();
		for (String s : ss) {
			builder.append(s);
		}
		return signature.equalsIgnoreCase(DigestUtils.sha1Hex(builder
				.toString()));
	}

	public static WeixinRecvMessage recv(InputStream in) throws JDOMException,
			IOException {
		return WeixinMessageParser.parse(in);
	}

	public static void send(WeixinSendMessage msg, OutputStream out)
			throws JDOMException, IOException {
		Document doc = msg.toDocument();
		if (null != doc) {
			new XMLOutputter().output(doc, out);
		} else {
			Logger.getAnonymousLogger().warning("发送消息时,解析出dom为空 msg :" + msg);
		}
	}

	public static WeixinSendMessage builderSendByRecv(WeixinRecvMessage msg) {
		WeixinRecvMessage m = new WeixinRecvMessage(msg);
		String from = m.getFromUser();
		m.setFromUser(m.getToUser());
		m.setToUser(from);
		m.setCreateDt((System.currentTimeMillis() / 1000) + "");
		return new WeixinSendMessage(m);
	}
}
