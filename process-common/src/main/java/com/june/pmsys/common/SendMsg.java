package com.june.pmsys.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * Title: SendMsg_webchinese
 * <p>
 * Description:发送短信接口
 * <p>
 * Copyright: Copyright (c) 2016
 * <p>
 * Company:
 * <p>
 * 
 * @author zhoulin.zhu
 *         <p>
 *         2016年9月12日
 */
public class SendMsg {

	/**
	 * @see 发送短信 还剩下3条免费短信（使用中国网建接口）
	 * @param Uid
	 *            注册的用户名
	 * @param key
	 *            注册成功后,登录网站使用的密钥
	 * @param smsMob
	 *            接收人的手机号码，多个手机号请用半角逗号隔开
	 * @param smsText
	 *            设置短信内容
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * */
	public Integer sendMessage(String Uid, String key, String smsMob,
			String smsText) throws UnsupportedEncodingException, IOException {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");
		post.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("Uid", Uid),// 注册的用户名
				new NameValuePair("Key", key),// 注册成功后,登录网站使用的密钥
				new NameValuePair("smsMob", smsMob),// 手机号码 目的手机号码（多个手机号请用半角逗号隔开）
				new NameValuePair("smsText", smsText) };// 设置短信内容
		post.setRequestBody(data);
		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println(h.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes(
				"gbk"));
		if (Integer.valueOf(result) == 1) {
			System.out.println("发送成功！");
		} else {
			System.out.println("发送失败！");
		}
		post.releaseConnection();
		return Integer.valueOf(result);
	}

	public static void main(String[] args) throws Exception {

	}
}
