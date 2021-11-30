package fun.kolowert.c92b.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fun.kolowert.c92b.bean.Operator;
import fun.kolowert.c92b.dao.DaoOperator;
import fun.kolowert.c92b.utility.PasswordUtils;
import fun.kolowert.c92b.utility.Utils;

public class Login extends HttpServlet {

	private static final long serialVersionUID = 16381358L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Login#doGet"); // ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		try {
			writer.println("<h1 Style=\"color:Brown\">Hello from Login Servlet</h1>");
		} finally {
			writer.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Login#doPost"); // ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

		String operatorLabel = request.getParameter("operator");
		String passwordInput = request.getParameter("password");

		// parse operator to id
		int operatorId = Utils.parseOperatorToId(operatorLabel);

		// TODO get Operator from database by id
		DaoOperator daoOperator = DaoOperator.getInstance();
		Operator operator = daoOperator.getOperatorById(operatorId);

		if (operator == null) {
			// TODO something with it
			System.out.println("operator == null !!!"); // |||||||||||||||||||||||||||||||||||||||||||||||
			request.setAttribute("failMessage", "Cash Register Operator has been not found");
			getServletContext().getRequestDispatcher("/login-fail.jsp").forward(request, response);
		}

		// TODO check password hash
		if (PasswordUtils.verifyPassword(passwordInput, operator.getPassHash(), operator.getSalt())) {
			// TODO fill session attribute -> (dutyOperator)
			HttpSession session = request.getSession();
			session.setAttribute("dutyOperator", operator);
			// TODO launch base page
			getServletContext().getRequestDispatcher("/base.jsp").forward(request, response);
			return;
		}

		// next is running when incorrect password
		request.setAttribute("failMessage", "Incorrect password");
		getServletContext().getRequestDispatcher("/login-fail.jsp").forward(request, response);
		
//		response.setContentType("text/html");
//		PrintWriter writer = response.getWriter();
//		try {
//			writer.println("<h3>Hello from Login Servlet</h3>");
//			writer.println("<h2 style=\"color: Red\">Bad password!</h2>");
//			writer.println("<p>Operator: " + operator.getLogin() + "</p>");
//			writer.println("<p>operator id: " + operator.getId() + "</p>");
//			writer.println("<p>Password: " + operator.getPassHash() + "</p>");
//			writer.println("<p>role: " + operator.getRole() + "</p>");
//			writer.println("<br>");
//
//			Optional<String> hashedPass = PasswordUtils.hashTextPassword(passwordInput, operator.getSalt());
//			writer.println("<p>dbPassHash   : " + operator.getPassHash() + "</p>");
//			writer.println("<p>inputPassHash: " + hashedPass.get() + "</p>");
//			writer.println("<p>inputPassword: " + passwordInput + "</p>");
//			writer.println("<p>Is Password Correct: " + operator.getPassHash().equals(hashedPass.get()) + "</p>");
//
//		} finally {
//			writer.close();
//		}
	}

}
