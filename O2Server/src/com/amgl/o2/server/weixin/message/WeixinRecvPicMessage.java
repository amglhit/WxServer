package com.amgl.o2.server.weixin.message;

public class WeixinRecvPicMessage extends WeixinRecvMessage {
	private String picUrl;

	public WeixinRecvPicMessage(WeixinRecvMessage msg, String picUrl) {
		super(msg);
		this.picUrl = picUrl;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
