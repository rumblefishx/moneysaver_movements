package com.rumblesoftware.mv.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PostOfficer {

	@Autowired
	private MessageSource messageSource;
	
	public String getMessage(String messageId) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(messageId,null,locale);
	}
}
