package com.tiandu.common.tencent.entity;

import java.util.Date;

public class TextMessage extends BaseMessage
{
	private static String messageXML = "<xml>"
			+ "<ToUserName><![CDATA[_toUser_]]></ToUserName>"
			+ "<FromUserName><![CDATA[_fromUser_]]></FromUserName>"
			+ "<CreateTime>_createTime_</CreateTime>"
			+ "<MsgType><![CDATA[text]]></MsgType>"
			+ "<Content><![CDATA[_content_]]></Content>"
			+ "</xml>";
	private String content = "欢迎！";

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		
		return messageXML.replace("_toUser_", this.getToUserName()).replace("_fromUser_", this.getFromUserName()).replace("_createTime_", new Date().getTime() +"").replace("_content_", content);
	}
	
}
