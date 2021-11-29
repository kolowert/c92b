package fun.kolowert.c92b.utility;

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
		for (String part : parts) {
			System.out.println(part);
		}
		try {
			result = Integer.parseInt(parts[1]);
		} catch(NumberFormatException e) {
			// TODO log here
			e.printStackTrace();
		}
		
		return result;
	}
}
