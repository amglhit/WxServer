package com.amgl.o2.server.weixin.message;

import java.util.LinkedList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

public class WeixinSendNewMessage extends WeixinSendMessage {
	private List<WeixinSendNewMessageItem> items = new LinkedList<WeixinSendNewMessageItem>();

	public WeixinSendNewMessage(WeixinSendMessage msg) {
		super(msg);
		setMsgType("news");
	}

	public void setItems(List<WeixinSendNewMessageItem> items) {
		this.items = items;
	}

	public WeixinSendNewMessage addItem(String title, String description,
			String picUrl, String url) {
		if (items.size() >= 10) {
			throw new IllegalArgumentException("只能接受最多10个item...");
		}
		items.add(new WeixinSendNewMessageItem(title, description, picUrl, url));
		return this;
	}

	@Override
	public Document toDocument() {
		Document doc = super.toDocument();
		Element root = doc.getRootElement();
		createElement(root, "ArticleCount", String.valueOf(items.size()));
		Element articles = createElement(root, "Articles", "");
		for (WeixinSendNewMessageItem item : items) {
			Element i = createElement(articles, "item", "");
			createElement(i, "Title", item.getTitle());
			createElement(i, "Description", item.getDescription());
			createElement(i, "PicUrl", item.getPicUrl());
			createElement(i, "Url", item.getUrl());
		}
		return doc;
	}

	class WeixinSendNewMessageItem {
		private String title;
		private String description;
		private String picUrl;
		private String url;

		public WeixinSendNewMessageItem(String title, String description,
				String picUrl, String url) {
			this.title = title;
			this.description = description;
			this.picUrl = picUrl;
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

		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}
}
