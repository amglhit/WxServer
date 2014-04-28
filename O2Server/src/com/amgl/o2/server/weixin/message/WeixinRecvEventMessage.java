package com.amgl.o2.server.weixin.message;

public class WeixinRecvEventMessage extends WeixinRecvMessage {
	private String event;
	private String eventKey;

	public WeixinRecvEventMessage(WeixinRecvMessage msg, String event,
			String eventKey) {
		super(msg);
		this.event = event;
		this.eventKey = eventKey;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getEvent() {
		return event;
	}

	public String getEventKey() {
		return eventKey;
	}
}
