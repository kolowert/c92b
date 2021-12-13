package fun.kolowert.c92b.servlet;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import fun.kolowert.c92b.utility.Utils;

public class PlayServ {

	public static void main(String[] args) {
//		String timeStamp = new SimpleDateFormat("dd MM yyyy").format(Calendar.getInstance().getTime());
//		System.out.println(timeStamp);
//		System.out.println("reportDayOfWeek: " + reportDayOfWeek_());

		playData3();

	}

	public static void playData3() {
		//Converting milliseconds to Date using java.util.Date
	    //current time in milliseconds
	    long currentDateTime = System.currentTimeMillis();
	     
	    //creating Date from millisecond
	    Date currentDate = new Date(currentDateTime);
	     
	    //printing value of Date
	    System.out.println("current Date: " + currentDate);
	     
	    DateFormat df1 = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
	     
	    //formatted value of current Date
	    System.out.println("Milliseconds to Date: " + df1.format(currentDate));
	    
	    DateFormat df2 = new SimpleDateFormat("YYYYMMdd");
	    String dateInString = df2.format(currentDate);
	    System.out.println("Milliseconds to Date: " + dateInString);
	    
	    long mills = Utils.txtDateToMilliseconds(dateInString);
	    System.out.println(mills);
	    System.out.println(new Date(mills));
	    
	    System.out.println("~ ~ ~");
	    
	    long now = System.currentTimeMillis();
	    DateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
	    String txtdate = dateFormat.format(now);
	    long dayStart = Utils.txtDateToMilliseconds(txtdate);
	    System.out.println("dayStart: " + dayStart);
	}

	public static void playData2() {

		String dateInString = "20211213";
		LocalDate date = LocalDate.parse(dateInString, DateTimeFormatter.BASIC_ISO_DATE);

		ZoneId zoneId = ZoneId.systemDefault(); // or: ZoneId.of("Europe/Oslo");
		long epoch = date.atStartOfDay(zoneId).toEpochSecond();

		long milliseconds = epoch * 1000;

		String timeStamp = Utils.unixTimeToTimeStamp(milliseconds);

		System.out.println("milliseconds: " + milliseconds);
		System.out.println("timeStamp: " + timeStamp);
	}

	public static void playData() {
		String dateInString = "2021-12-31 00:00:00";
		SimpleDateFormat f = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		long milliseconds = -1L;
		try {
			Date d = f.parse(dateInString);
			milliseconds = d.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String timeStamp = Utils.unixTimeToTimeStamp(milliseconds);

		System.out.println("milliseconds: " + milliseconds);
		System.out.println("timeStamp: " + timeStamp);
	}

	public static int thisDayOfWeek() {
		Calendar c = Calendar.getInstance();
		c.setTime(c.getTime());
		int d = c.get(Calendar.DAY_OF_WEEK);
		return d;
	}

	public static String reportDayOfWeek_() {
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
