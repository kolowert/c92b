package fun.kolowert.c92b.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fun.kolowert.c92b.bean.Operator;
import fun.kolowert.c92b.dao.DaoOperator;

public class Entry extends HttpServlet {

	private static final long serialVersionUID = 16381350L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Entry#doGet"); // ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

		HttpSession session = request.getSession();
		Object dutyOperator = session.getAttribute("dutyOperator");

		// zeroing operator if is
		if (dutyOperator != null) {
			System.out.println("Entry#doGet -- zeroing operator"); // ||||||||||||||||||||||||||||||||||||||||||||||||||
			session.setAttribute("dutyOperator", null);
			session.setAttribute("dutyRole", null);
			session.setAttribute("briefInfo", null);
		}

		// get registered users from database
		DaoOperator daoOperator = DaoOperator.getInstance();
		List<Operator> operators = daoOperator.getOperators();
		// ..push them into attributes
		request.setAttribute("operators", operators);
		request.setAttribute("password", "undefined");
		// redirect to loginPage
		getServletContext().getRequestDispatcher("/play/login.jsp").forward(request, response);

	}
}
