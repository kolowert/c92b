package fun.kolowert.c92b.servlet;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Play {

	public static void main(String[] args) {
		String timeStamp = new SimpleDateFormat("dd MM yyyy").format(Calendar.getInstance().getTime());
		System.out.println(timeStamp);
		
		System.out.println("reportDayOfWeek: " + reportDayOfWeek());
		
	}
	
	public static int thisDayOfWeek() {
		Calendar c = Calendar.getInstance();
		c.setTime(c.getTime());
		int d = c.get(Calendar.DAY_OF_WEEK);
		return d;
	}
	
	public static String reportDayOfWeek() {
		Calendar c = Calendar.getInstance();
		c.setTime(c.getTime());
		int d = c.get(Calendar.DAY_OF_WEEK);
		if (d == 1) return "Sunday";
		if (d == 2) return "Monday";
		if (d == 3) return "Tuesday";
		if (d == 4) return "Wednesday";
		if (d == 5) return "Thursday";
		if (d == 6) return "Friday";
		if (d == 7) return "Suturday";
		return "error";
	}
	
}
