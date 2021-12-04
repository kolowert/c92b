package fun.kolowert.c92b.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fun.kolowert.c92b.dao.DaoOperator;
import fun.kolowert.c92b.utility.Utils;

public class Oper extends HttpServlet {

	private static final long serialVersionUID = 16386269L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Oper#doPost"); // ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

		// check what task is it
		String formTask = request.getParameter("task");
		System.out.println("formTask: " + formTask); // ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

		if (formTask.equals("inputNew")) {
			// receive from form
			String inputName = request.getParameter("login");
			String inputRole = request.getParameter("role");
			String inputPassword = request.getParameter("password");

			// create new operator in database
			DaoOperator daoOperator = DaoOperator.getInstance();
			daoOperator.createNewInDataBase(inputName, inputRole, inputPassword);
			inputPassword = "erased";
		}
		
		if (formTask.equals("editOper")) {
			System.out.println("Oper#doPost >> editOper"); // ||||||||||||||||||||||||||||||||
			
		}
		
		if (formTask.equals("deleteOper")) {
			System.out.println("Oper#doPost >> DELETE OPER"); // ||||||||||||||||||||||||||||||||
			int	id = Utils.parseIntIdFromObject(request.getParameter("id"));
			DaoOperator daoOperator = DaoOperator.getInstance();
			daoOperator.deleteOperator(id);
		}
		
		getServletContext().getRequestDispatcher("/staff.jsp").forward(request, response);

	}
}
