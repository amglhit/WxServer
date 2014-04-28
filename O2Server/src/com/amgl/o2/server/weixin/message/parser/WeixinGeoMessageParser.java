package com.amgl.o2.server.weixin.message.parser;

import org.jdom2.Element;
import org.jdom2.JDOMException;

import com.amgl.o2.server.weixin.message.WeixinRecvGeoMessage;
import com.amgl.o2.server.weixin.message.WeixinRecvMessage;

public class WeixinGeoMessageParser extends WeixinBaseMessageParser {

	@Override
	protected WeixinRecvGeoMessage parse(Element root, WeixinRecvMessage msg)
			throws JDOMException {
		String locationX = getElementText(root, "Location_X");
		String locationY = getElementText(root, "Location_Y");
		int scale = parseInt(getElementText(root, "Scale"), 0);
		String label = getElementText(root, "Label");

		double latitude = parseDouble(locationX, 0.0);
		double longitude = parseDouble(locationY, 0.0);
		return new WeixinRecvGeoMessage(msg, latitude, longitude, scale, label);
	}

	private double parseDouble(String val, double def) {
		try {
			return Double.parseDouble(val);
		} catch (Exception e) {
			return def;
		}
	}

	private int parseInt(String val, int def) {
		try {
			return Integer.parseInt(val);
		} catch (Exception e) {
			return def;
		}
	}

}
