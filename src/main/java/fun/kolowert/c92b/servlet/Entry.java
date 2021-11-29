package fun.kolowert.c92b.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fun.kolowert.c92b.bean.Operator;

public class Entry extends HttpServlet {

	private static final long serialVersionUID = 16381350L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String dutyUser = (String) session.getAttribute("dutyUser");

		if (dutyUser == null) {
			// get registered users from database
			// TODO instead of stub
			Operator[] preOperators = {new Operator(123, "Arny"), new Operator(234, "Bruce"), new Operator(69, "Chack")};
			List<Operator> operators =  Arrays.asList(preOperators);
			request.setAttribute("operators", operators);
			request.setAttribute("password", "undefined");
			// redirect to loginPage
			String path = "/login.jsp";
			getServletContext().getRequestDispatcher(path).forward(request, response);
		}

		// if dutyUser is defined go to basePage
		// TODO

//		response.setContentType("text/html");
//		PrintWriter writer = response.getWriter();
//		try {
//			writer.println("<h1 Style=\"color:Brown\">Hello from Entry Servlet</h1>");
//		} finally {
//			writer.close();
//		}

	}
}
