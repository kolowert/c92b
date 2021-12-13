package fun.kolowert.c92b.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fun.kolowert.c92b.bean.Operator;
import fun.kolowert.c92b.bean.Receipt;
import fun.kolowert.c92b.dao.DaoReceipt;
import fun.kolowert.c92b.utility.Report;
import fun.kolowert.c92b.utility.Utils;

public class ReportServ extends HttpServlet {

	private static final long serialVersionUID = 16393365L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("ReportServ#doGet"); // ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

		HttpSession session = request.getSession();
		Operator dutyOperator = obtainDutyOperator(session);

		String task = request.getParameter("task");
		String report = "no report yet -- ";

		// X-REPORT ____________________________
		if (task.equals("xReport")) {
			long now = System.currentTimeMillis();
			long dayStart = Utils.dayStart(now);
			report = makeReport("x-Report", dayStart, now, dutyOperator).toHTML();
		}

		// Y-REPORT ____________________________
		if (task.equals("zReport")) {
			long now = System.currentTimeMillis();
			long dayStart = Utils.dayStart(now);
			report = makeReport("z-Report", dayStart, now, dutyOperator).toHTML();
		}

		request.setAttribute("reportBody", report);
		getServletContext().getRequestDispatcher("/play/report.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("ReportServ#doPost"); // ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

		HttpSession session = request.getSession();
		Operator dutyOperator = obtainDutyOperator(session);

		String task = request.getParameter("task");
		String report = "no report yet -- ";

		if (task.equals("customReport")) {

			String fromYear = request.getParameter("fromYear");
			String fromMonth = request.getParameter("fromMonth");
			String fromDay = request.getParameter("fromDay");

			String toYear = request.getParameter("toYear");
			String toMonth = request.getParameter("toMonth");
			String toDay = request.getParameter("toDay");

			String dateFrom = fromYear + normMonth(fromMonth) + normDay(fromDay, fromMonth, fromYear);
			String dateTo = toYear + normMonth(toMonth) + normDay(toDay, toMonth, fromYear);

			long fromTime = Utils.txtDateToMilliseconds(dateFrom);
			long toTime = Utils.txtDateToMilliseconds(dateTo);

			report = makeReport("Custom Period Report", fromTime, toTime, dutyOperator).toHTML();
		}

		request.setAttribute("reportBody", report);
		getServletContext().getRequestDispatcher("/play/report.jsp").forward(request, response);
	}

	private Operator obtainDutyOperator(HttpSession session) {
		Object preDutyOperator = session.getAttribute("dutyOperator");
		Operator dutyOperator = Operator.getNullOperator();
		if (preDutyOperator instanceof Operator) {
			dutyOperator = (Operator) preDutyOperator;
		}
		return dutyOperator;
	}

	private Report makeReport(String type, long timeFrom, long timeTo, Operator dutyOperator) {

		DaoReceipt daoReceipt = DaoReceipt.getInstance();
		List<Receipt> receipts = daoReceipt.getByPeriod(timeFrom, timeTo);

		int receiptQuantity = 0;
		double totalSum = 0.0;
		for (Receipt receipt : receipts) {
			receiptQuantity++;
			totalSum += receipt.getSum();
		}

		return new Report(type, timeFrom, timeTo, receiptQuantity, totalSum, dutyOperator);
	}

	private String normMonth(String month) {
		int n = -1;
		try {
			n = Integer.parseInt(month);
		} catch (NumberFormatException e) {
			// TODO
			e.printStackTrace();
		}
		if (n < 10) {
			return "0" + n;
		}
		return "" + n;
	}

	private String normDay(String day, String month, String year) {
		int d = -1;
		int m = -1;
		int y = -1;
		try {
			d = Integer.parseInt(day);
			m = Integer.parseInt(month);
			y = Integer.parseInt(year);
		} catch (NumberFormatException e) {
			// TODO
			e.printStackTrace();
			return "00";
		}
		if (d > 30 && (m == 4 || m == 6 || m == 9 || m == 11)) {
			d = 30;
		}
		if (isLeapYear(y)) {
			if (m == 2 && d > 28) {
				d = 28;
			}
		} else {
			if (m == 2 && d > 29) {
				d = 29;
			}
		}
		if (d < 10) {
			return "0" + d;
		}
		return "" + d;
	}

	private boolean isLeapYear(int year) {
		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			return true;
		}
		return false;
	}
}
