package com.amgl.o2.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.JDOMException;

import com.amgl.o2.server.weixin.Weixin;
import com.amgl.o2.server.weixin.message.WeixinMessage;
import com.amgl.o2.server.weixin.message.WeixinRecvEventMessage;
import com.amgl.o2.server.weixin.message.WeixinRecvGeoMessage;
import com.amgl.o2.server.weixin.message.WeixinRecvMessage;
import com.amgl.o2.server.weixin.message.WeixinRecvTextMessage;
import com.amgl.o2.server.weixin.message.WeixinSendMessage;
import com.amgl.o2.server.weixin.message.WeixinSendTextMessage;

/**
 * Servlet implementation class WxMessage
 */
// @WebServlet(description = "微信平台消息通知", urlPatterns = { "/WxMessage" })
public class WxMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Log LOG = LogFactory.getLog(WxMessage.class);

	private static final String TOKEN = "amglhit";

	private static final String PARAM_SIGNATURE = "signature";
	private static final String PARAM_TIMESTAMP = "timestamp";
	private static final String PARAM_NONCE = "nonce";
	private static final String PARAM_ECHOSTR = "echostr";

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WxMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("doGet");
		String signature = request.getParameter(PARAM_SIGNATURE);
		String timestamp = request.getParameter(PARAM_TIMESTAMP);
		String nonce = request.getParameter(PARAM_NONCE);
		String echostr = request.getParameter(PARAM_ECHOSTR);
		PrintWriter toClient = response.getWriter();
		if (signature != null && timestamp != null && nonce != null
				&& echostr != null) {
			try {
				LOG.debug("access");
				if (Weixin.access(TOKEN, signature, timestamp, nonce)) {
					LOG.info("access success");
					toClient.println(echostr);
					return;
				} else {
					LOG.info("access falied");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			toClient.write("bye");
		}
		toClient.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("doPost");
		try {
			WeixinRecvMessage receivedMessage = Weixin.recv(request
					.getInputStream());
			LOG.debug("messageReceived from: " + receivedMessage.getFromUser()
					+ "; type:" + receivedMessage.getMsgType());
			WeixinSendMessage sendMessage = handlerMessage(receivedMessage);
			Weixin.send(sendMessage, response.getOutputStream());
			LOG.debug("message sent to: " + sendMessage.getToUser()
					+ "; type: " + sendMessage.getMsgType());
		} catch (JDOMException e) {
			LOG.error(e);
			e.printStackTrace();
		}
	}

	private WeixinSendMessage handlerMessage(WeixinRecvMessage receivedMessage) {
		WeixinSendMessage sendMessage = Weixin
				.builderSendByRecv(receivedMessage);
		String msgType = receivedMessage.getMsgType();
		String sendText = "";
		if (msgType.equals(WeixinMessage.MSG_TYPE_TEXT)) {
			WeixinRecvTextMessage textMessage = (WeixinRecvTextMessage) receivedMessage;
			sendText = textMessage.getContent();
		} else if (msgType.equals(WeixinMessage.MSG_TYPE_EVENT)) {
			WeixinRecvEventMessage eventMessage = (WeixinRecvEventMessage) receivedMessage;
			sendText = eventMessage.getEvent();
		} else if (msgType.equals(WeixinMessage.MSG_TYPE_LOCATION)) {
			WeixinRecvGeoMessage geoMessage = (WeixinRecvGeoMessage) receivedMessage;
			sendText = geoMessage.getLabel();
		} else {
			sendText = "收到";
		}
		WeixinSendTextMessage sendTextMessage = new WeixinSendTextMessage(
				sendMessage, sendText);
		return sendTextMessage;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
}
