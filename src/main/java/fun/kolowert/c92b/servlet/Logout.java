package fun.kolowert.c92b.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {

	private static final long serialVersionUID = 16384428L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Logout#doGet"); // ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		
		HttpSession session = request.getSession();
		session.setAttribute("dutyOperator", null);
		session.setAttribute("briefInfo", "operator logget out");
		
		getServletContext().getRequestDispatcher("/loginServ").forward(request, response);
	}

}
