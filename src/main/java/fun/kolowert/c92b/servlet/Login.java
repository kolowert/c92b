package fun.kolowert.c92b.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fun.kolowert.c92b.bean.Operator;
import fun.kolowert.c92b.dao.DaoOperator;
import fun.kolowert.c92b.utility.Utils;

public class Login extends HttpServlet {

	private static final long serialVersionUID = 16381358L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
		response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
 
        String operatorLabel = request.getParameter("operator");
        String password = request.getParameter("password");
        
        // parse operator to id
        int operatorId = Utils.parseOperatorToId(operatorLabel);
        
        // TODO get Operator from database by id
        DaoOperator daoOperator = DaoOperator.getInstance();
        Operator operator = daoOperator.getOperatorById(operatorId);
        
        // TODO check password hash
        // TODO fill session attribute -> (dutyOperator)
        // TODO launch base page
        
        try {
            writer.println("<p>Operator: " + operator.getLogin() + "</p>");
            writer.println("<p>operator id: " + operator.getId() + "</p>");
            writer.println("<p>Password: " + operator.getPassHash() + "</p>");
            writer.println("<p>role: " + operator.getRole() + "</p>");
        } finally {
            writer.close();  
        }
	}
	
}
