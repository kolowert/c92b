package fun.kolowert.c92b.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fun.kolowert.c92b.dao.DaoOperator;
import fun.kolowert.c92b.utility.Utils;

public class OperServ extends HttpServlet {

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

			// insert new operator in database
			DaoOperator daoOperator = DaoOperator.getInstance();
			daoOperator.insert(inputName, inputRole, inputPassword);
			inputPassword = "erased";
		}
		
		if (formTask.equals("editOper")) {
			System.out.println("Oper#doPost >> editOper"); // ||||||||||||||||||||||||||||||||
			int	id = Utils.parseIntIdFromObject(request.getParameter("id"));
			String inputLogin = request.getParameter("login");
			String inputRole = request.getParameter("role");
			String inputPassword = request.getParameter("password");
			DaoOperator daoOperator = DaoOperator.getInstance();
			daoOperator.update(id, inputLogin, inputRole, inputPassword);
			inputPassword = "erased";
		}
		
		if (formTask.equals("deleteOper")) {
			System.out.println("Oper#doPost >> DELETE OPER"); // ||||||||||||||||||||||||||||||||
			int	id = Utils.parseIntIdFromObject(request.getParameter("id"));
			DaoOperator daoOperator = DaoOperator.getInstance();
			daoOperator.delete(id);
		}
		
		getServletContext().getRequestDispatcher("/play/staff.jsp").forward(request, response);

	}
}