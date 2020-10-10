package com.thang.webshop.common.message;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;


@Component
public class MsgUtil {

	private static ResourceBundleMessageSource messageSource;
	private static Locale locale = Locale.US;

	@Autowired
	MsgUtil(ResourceBundleMessageSource messageSource) {
		MsgUtil.messageSource = messageSource;
	}

	/**
	 * get message only
	 * 
	 * @param msgCode String
	 * @return String
	 */
	public static String getMsg(String msgCode) {
		return messageSource.getMessage(msgCode, null, locale);
	}

	/**
	 * get message with parameter
	 * 
	 * @param msgCode String
	 * @param param   Object[]
	 * @return String
	 */
	public static String getMsg(String msgCode, Object... param) {
		return messageSource.getMessage(msgCode, param, locale);
	}
}
