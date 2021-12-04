package fun.kolowert.c92b.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fun.kolowert.c92b.dao.DaoOperator;

public class Oper extends HttpServlet {
	
	private static final long serialVersionUID = 16386269L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Oper#doPost"); // ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		
		// receive from form 
		String inputName = request.getParameter("login");
		String inputRole = request.getParameter("role");
		String inputPassword = request.getParameter("password");
		
		// create new operator in database
		DaoOperator daoOperator = DaoOperator.getInstance();
		daoOperator.createNewInDataBase(inputName, inputRole, inputPassword);
		inputPassword = "erased";
		
		getServletContext().getRequestDispatcher("/staff.jsp").forward(request, response);
		
	}
}
