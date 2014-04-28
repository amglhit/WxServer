package com.amgl.o2.server.weixin.message;

public class WeixinRecvLinkMessage extends WeixinRecvMessage {
	private String title;
	private String description;
	private String url;

	public WeixinRecvLinkMessage(WeixinRecvMessage msg, String title,
			String descString, String url) {
		super(msg);
		this.title = title;
		this.description = descString;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
