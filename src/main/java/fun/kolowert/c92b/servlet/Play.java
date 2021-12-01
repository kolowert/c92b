package fun.kolowert.c92b.servlet;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Play {

	public static void main(String[] args) {
		String timeStamp = new SimpleDateFormat("dd MM yyyy").format(Calendar.getInstance().getTime());
		System.out.println(timeStamp);
	}

}
