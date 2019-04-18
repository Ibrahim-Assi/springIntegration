package ims.health.tools;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTool {

	public static final String PATTERN_YYYY_MM_DD ="yyyy-MM-dd";
	
	public static String dateFormate(Date date, String pattern) {
         
	   	 LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault());
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		 
		 return ldt.format(formatter);

	}
}
