package com.rumblesoftware.mv.utils;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.rumblesoftware.mv.exception.DateConversionException;

@Component
@PropertySource("classpath:application.properties")
public class DateUtils {

	@Value(value = "${moneysaver.date.pattern}")
	public String datePattern;
	
	@Autowired
	private MessageSource ms;
	
	private Logger log = LogManager.getLogger(DateUtils.class);
	
	private static final String DATE_EXCEPTION_MSG = 
			"date.to.string.cast.exception";
	
	private static final String DATE_CONVERSION_MSG_PATTERN = 
			"date.to.string.cast.error.log";
	
	public Date castStringToDate(String dateStr) {
		
		Date dt = null;
		
		DateFormat df = new SimpleDateFormat(datePattern);
		
		try {			
			dt = df.parse(dateStr);
		} catch (ParseException e) {
			log.error(String.format(ms.getMessage(DATE_CONVERSION_MSG_PATTERN,null,Locale.getDefault()), dateStr,datePattern));
			throw new DateConversionException(ms.getMessage(DATE_EXCEPTION_MSG,null,Locale.getDefault()));			
		}
		
		return dt;
	}
	
	public String castDateToString(Date dt) {
		DateFormat df = new SimpleDateFormat(datePattern);
		return df.format(dt);
	}
	
}
