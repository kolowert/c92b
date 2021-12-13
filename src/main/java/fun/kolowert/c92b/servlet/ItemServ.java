package fun.kolowert.c92b.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fun.kolowert.c92b.bean.Item;
import fun.kolowert.c92b.dao.DaoStore;
import fun.kolowert.c92b.utility.Utils;

public class ItemServ extends HttpServlet {

	private static final long serialVersionUID = 16392397L;
	
	private static final Logger logger = LogManager.getLogger("ItemServ");

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.debug("ItemServ#doPost");

		// check what task is it
		String formTask = request.getParameter("task");
		logger.debug("formTask: " + formTask);
		
		if (formTask.equals("inputNew")) {
			// receive from form
			String inputName = request.getParameter("name");
			String inputUnit = request.getParameter("unit");
			String inputQuantity = request.getParameter("quantity");
			String inputPrice = request.getParameter("price");
			
			// create new object
			Item item = new Item(-1, inputName, Utils.findMesureUnitValue(inputUnit), 
					Utils.parseStringToDouble(inputQuantity), Utils.parseStringToDouble(inputPrice));
			
			// insert to database
			DaoStore daoStore = DaoStore.getInstance();
			daoStore.insert(item);
		}
		
		if (formTask.equals("editItem")) {
			logger.debug("ItemServ#doPost >> editItem");
			
			int	id = Utils.parseIntIdFromObject(request.getParameter("id"));
			
			DaoStore daoStore = DaoStore.getInstance();
			Item item = daoStore.get(id);
			
			String inputUnit = request.getParameter("unit");
			String inputQuantity = request.getParameter("quantity");
			String inputPrice = request.getParameter("price");
			
			item.setUnit(Utils.findMesureUnitValue(inputUnit));
			item.setQuantity(Utils.parseStringToDouble(inputQuantity));
			item.setPrice(Utils.parseStringToDouble(inputPrice));
			
			daoStore.update(item);
			
			logger.debug("ItemServ#doPost >> item#Brief " + item.brief());
		}
		
		if (formTask.equals("deleteItem")) {
			logger.debug("Oper#doPost >> DELETE ITEM");
			int	id = Utils.parseIntIdFromObject(request.getParameter("id"));
			logger.debug("Oper#doPost >> DELETE ITEM >> id=" + id);
			DaoStore daoStore = DaoStore.getInstance();
			daoStore.delete(id);
		}
		
		getServletContext().getRequestDispatcher("/play/store.jsp").forward(request, response);
	}

}
