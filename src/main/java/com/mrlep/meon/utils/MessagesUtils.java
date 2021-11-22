package com.mrlep.meon.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessagesUtils {

	private static ResourceBundleMessageSource messageSource;

	@Autowired
    MessagesUtils(ResourceBundleMessageSource messageSource) {
		MessagesUtils.messageSource = messageSource;
	}

	public static String getMessage(String msgCode) {
		try {
			Locale locale = LocaleContextHolder.getLocale();
			return messageSource.getMessage(msgCode, null, locale);
		} catch (Exception e) {
			LoggingUtils.logVTMException(e);
			return "";
		}
	}

	public static String getMessageVi(String msgCode) {
		try {
			return messageSource.getMessage(msgCode, null, new Locale("vi"));
		} catch (Exception e) {
			LoggingUtils.logVTMException(e);
			return "";
		}
	}

}