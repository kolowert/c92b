package fun.kolowert.c92b.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fun.kolowert.c92b.bean.Item;
import fun.kolowert.c92b.bean.Operator;
import fun.kolowert.c92b.bean.Receipt;
import fun.kolowert.c92b.dao.DaoReceipt;
import fun.kolowert.c92b.dao.DaoStore;
import fun.kolowert.c92b.utility.Utils;

public class Order extends HttpServlet {

	private static final long serialVersionUID = 16387224L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Order#doGet"); // ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		
		String task = request.getParameter("task");
		System.out.println("Order#doGet >> task=" + task); // ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Order#doPost"); // ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

		// check what task is it
		String formTask = request.getParameter("task");
		System.out.println("formTask: " + formTask); // ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

		HttpSession session = request.getSession();

		if (formTask.equals("addToReceipt")) {
			// receive item brief information from form
			String inputItemBrief = request.getParameter("itemBrief");
			String inputQuantity = request.getParameter("quantity");
			int requestQuantity = Utils.parseStringToInt(inputQuantity);
			System.out.println("Order#doPost >> inputItem:" + inputItemBrief + " inputQuantity:" + inputQuantity); // ||||||||
			int itemId = parseIdFromBrief(inputItemBrief);
			System.out.println("Order#doPost >> itemId:" + itemId); // ||||||||
			// check input
			if (itemId < 0) {
				request.setAttribute("messageType", "fail"); // are types "good" or "fail"
				request.setAttribute("orderMessage", "Rejected! Wrong Item");
				getServletContext().getRequestDispatcher("/play/base.jsp").forward(request, response);
				return;
			}
			if (requestQuantity < 0) {
				request.setAttribute("messageType", "fail"); // are types "good" or "fail"
				request.setAttribute("orderMessage", "Rejected! Wrong Quantity");
				getServletContext().getRequestDispatcher("/play/base.jsp").forward(request, response);
				return;
			}

			// check if item present in database
			DaoStore daoStore = DaoStore.getInstance();
			Item item = daoStore.getItemById(itemId);
			if (item != null) {
				// check quantity
				if (item.getQuantity() < requestQuantity) {
					request.setAttribute("messageType", "fail"); // are types "good" or "fail"
					request.setAttribute("orderMessage", "Rejected! There are not enough " + item.getName()
							+ "in store! Asked for " + requestQuantity + "but there are " + item.getQuantity());
					getServletContext().getRequestDispatcher("/play/base.jsp").forward(request, response);
					return;
				}

				// adjust quantity in store
				// TODO

				// prepare current receipt
				Receipt currentReceipt = null;
				Object preCurrentReceipt = session.getAttribute("currentReceipt");
				if (preCurrentReceipt instanceof Receipt) {
					currentReceipt = (Receipt) preCurrentReceipt;
				} else {
					DaoReceipt daoReceipt = DaoReceipt.getInstance();
					currentReceipt = daoReceipt.createNewReceipt();
					currentReceipt.setOperatorId(getDutyOperatorId(session));
				}

				// increase sum in receipt
				double cost = requestQuantity * item.getPrice();
				double sum = currentReceipt.getSum() + cost;
				currentReceipt.setSum(sum);

				// put line into soldRecords
				// TODO
				
				// expose sold records
				// TODO

				// finally set attributes
				session.setAttribute("currentReceipt", currentReceipt);
				request.setAttribute("messageType", "good"); // are types "good" or "fail"
				String orderMessage = "Added: " + item.getName() + " >>> " + requestQuantity + " " + item.getUnit()
						+ " >>> cost=" + cost + " >>> TOTAL > " + sum;
				request.setAttribute("orderMessage", orderMessage);
			}
		}

		System.out.println("Order#doPost >> exit to /play/base.jsp"); // |||||||||||||||||||||||||||||||||||||||
		getServletContext().getRequestDispatcher("/play/base.jsp").forward(request, response);
	}

	private int getDutyOperatorId(HttpSession session) {
		Object preDutyOperator = session.getAttribute("dutyOperator");
		if (preDutyOperator instanceof Operator) {
			Operator dutyOperator = (Operator) preDutyOperator;
			return dutyOperator.getId();
		}
		return -1;
	}

	private int parseIdFromBrief(String brief) {
		int result = -1;
		if (brief == null) {
			return result;
		}
		String[] parts = brief.split("\s");
		try {
			result = Integer.parseInt(parts[0]);
		} catch (NumberFormatException e) {
			// TODO log here
			e.printStackTrace();
		}
		return result;
	}

}
