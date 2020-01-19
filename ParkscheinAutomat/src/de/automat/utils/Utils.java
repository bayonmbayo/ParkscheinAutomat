package de.automat.utils;

import java.util.Calendar;

public class Utils {

	
	/**
	 *
	 * @return current Date from Calendar in dd/MM/yyyy format
	 * adding 1 into month because Calendar month starts from zero
	 */
	public static String getDate(Calendar cal){
	    return "" + cal.get(Calendar.DATE) +"." +
	            (cal.get(Calendar.MONTH)+1) + "." + cal.get(Calendar.YEAR);
	}

	/**
	 *
	 * @return current Date from Calendar in HH:mm:SS format
	 *
	 * adding 1 into month because Calendar month starts from zero
	 */
	public static String getTime(Calendar cal){
	    return "" + cal.get(Calendar.HOUR_OF_DAY) +":" +
	            (cal.get(Calendar.MINUTE));
	}
	
	public static boolean liegtInDerOeffnungszeit(int hh, int mm,int hh1, int mm1,int hh2, int mm2) {
		int minute = hh*60 + mm;
		int minute1 = hh1*60 + mm1;
		int minute2 = hh2*60 + mm2;
		if(minute > minute1 && minute < minute2) {
			return true;
		}		
		return false;
	}
}
