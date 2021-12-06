package fun.kolowert.c92b.utility;

import java.util.Calendar;
import java.util.List;

import fun.kolowert.c92b.bean.Operator;

public class Utils {

	/**
	 * used on login.jsp
	 */
	@SuppressWarnings("unchecked")
	public static List<Operator> convertToOperator(Object input) {
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
	 * used on input-oper.jsp.jsp
	 */
	@SuppressWarnings("unchecked")
	public static List<String> convertToString(Object input) {
		Object preresult = null;
		if (input != null && input instanceof List<?>) {
			preresult = (List<String>) input;
		}
		if (preresult != null && ((List<String>) preresult).get(0) instanceof String) {
			return (List<String>) preresult;
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
		} catch (NumberFormatException e) {
			// TODO log here
			e.printStackTrace();
		}
		return result;
	}

	public static int parseStringToInt(String s) {
		int result = -1;
		if (s == null) {
			return result;
		}
		try {
			result = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			// TODO log here
			e.printStackTrace();
		}
		return result;
	}

	public static double parseStringToDouble(String s) {
		double result = -1.0;
		if (s == null) {
			return result;
		}
		try {
			result = Double.parseDouble(s);
		} catch (NumberFormatException e) {
			// TODO log here
			e.printStackTrace();
		}
		return result;
	}
	
	public static String atributeToStringOrStub(Object input, String stub) {
		if (input != null && input instanceof String) {
			return input.toString();
		}
		return stub;
	}

	/**
	 * It converts SomeServlet#request.getParameter("id") to primitive integer
	 * 
	 * @param usually SomeServlet#request.getParameter("id")
	 * @return parsed value or -1 if input can't be parsed
	 */
	public static int parseIntIdFromObject(Object input) {
		int result = -1;
		if (input instanceof String) {
			try {
				result = Integer.parseInt((String) input);
			} catch (NumberFormatException e) {
				// TODO log mismatch
			}
		}
		return result;
	}
	
	public static String unixTimeToTimeStamp(long t) {
		// TODO here!
		return "timestamp stub";
	}
	
	public static String reportDayOfWeek() {
		Calendar c = Calendar.getInstance();
		c.setTime(c.getTime());
		int d = c.get(Calendar.DAY_OF_WEEK);
		if (d == 1)
			return "Sunday";
		if (d == 2)
			return "Monday";
		if (d == 3)
			return "Tuesday";
		if (d == 4)
			return "Wednesday";
		if (d == 5)
			return "Thursday";
		if (d == 6)
			return "Friday";
		if (d == 7)
			return "Suturday";
		return "error";
	}

}
