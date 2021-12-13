package fun.kolowert.c92b.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fun.kolowert.c92b.bean.Operator;
import fun.kolowert.c92b.dao.DaoOperator;
import fun.kolowert.c92b.utility.PasswordUtils;
import fun.kolowert.c92b.utility.Utils;

public class MainServ extends HttpServlet {

	private static final long serialVersionUID = 16381358L;
	
	private static final Logger logger = LogManager.getLogger("MainServ");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.debug("Main#doGet");

		String dir = request.getParameter("dir");

		HttpSession session = request.getSession();
		Object preRole = session.getAttribute("dutyRole");
		if (dir == null || preRole == null) {
			request.setAttribute("failMessage", "Current session went off. Register to continue!");
			getServletContext().getRequestDispatcher("/play/login-fail.jsp").forward(request, response);
			return;
		}
		String role = (String) preRole;
		
		if (dir.equals("base")) {
			logger.debug("Main#doGet >> dir.equals(\"base\")");
			getServletContext().getRequestDispatcher("/play/base.jsp").forward(request, response);
			return;
		}
		
		if (dir.equals("receipts")) {
			getServletContext().getRequestDispatcher("/play/receipts.jsp").forward(request, response);
			return;
		}
		
		if (dir.equals("report") && (role.equals("senior cashier") || role.equals("expert"))) {
			getServletContext().getRequestDispatcher("/play/report.jsp").forward(request, response);
			return;
		}
		
		if (dir.equals("store") && role.equals("expert")) {
			getServletContext().getRequestDispatcher("/play/store.jsp").forward(request, response);
			return;
		}
		
		if (dir.equals("staff") && role.equals("expert")) {
			getServletContext().getRequestDispatcher("/play/staff.jsp").forward(request, response);
			return;
		}
		
		logger.debug("Main#doGet -- not match any 'dir'");
		getServletContext().getRequestDispatcher("/play/base.jsp").forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.debug("Main#doPost");

		String operatorLabel = request.getParameter("operator");
		String passwordInput = request.getParameter("password");
		
		
		// parse operator to id
		int operatorId = Utils.parseOperatorToId(operatorLabel);

		// get Operator from database by id
		DaoOperator daoOperator = DaoOperator.getInstance();
		Operator operator = daoOperator.get(operatorId);

		if (operator == null) {
			logger.warn("Main#doPost >> operator == null !!!");
			request.setAttribute("failMessage", "Cash Register Operator has been not found");
			getServletContext().getRequestDispatcher("/play/login-fail.jsp").forward(request, response);
			return;
		}

		// check password hash
		if (PasswordUtils.verifyPassword(passwordInput, operator.getPassHash(), operator.getSalt())) {
			passwordInput = "erased";
			HttpSession session = request.getSession();
			session.setAttribute("dutyOperator", operator);
			session.setAttribute("dutyRole", operator.getRole());
			session.setAttribute("briefInfo", operator.briefInfo());
			getServletContext().getRequestDispatcher("/play/base.jsp").forward(request, response);
			return;
		}
		request.setAttribute("failMessage", "Incorrect password or inappropriate operator registration data");
		getServletContext().getRequestDispatcher("/play/login-fail.jsp").forward(request, response);
		return;

	}

}
