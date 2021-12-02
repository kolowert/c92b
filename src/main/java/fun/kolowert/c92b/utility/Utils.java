package fun.kolowert.c92b.utility;

import java.util.Calendar;
import java.util.List;

import fun.kolowert.c92b.bean.Operator;

public class Utils {

	/**
	 * used on login.jsp
	 */
	@SuppressWarnings("unchecked")
	public static List<Operator> convert(Object input) {
		Object preresult = null;
		if (input != null && input instanceof List<?>) {
			preresult = (List<Object>) input;
		}
		if (preresult != null && ((List<Operator>) preresult).get(0) instanceof Operator) {
			return (List<Operator>) preresult;
		}
		return null;
	}

	/**
	 * used on Login Servlet
	 */
	public static int parseOperatorToId(String input) {
		int result = -1;
		String[] parts = input.split("\s");
//		for (String part : parts) {
//			System.out.println(part);
//		}
		try {
			result = Integer.parseInt(parts[1]);
		} catch(NumberFormatException e) {
			// TODO log here
			e.printStackTrace();
		}
		
		return result;
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
