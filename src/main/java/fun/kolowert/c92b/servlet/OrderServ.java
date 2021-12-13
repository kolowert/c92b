package fun.kolowert.c92b.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fun.kolowert.c92b.bean.Item;
import fun.kolowert.c92b.bean.MeasureUnit;
import fun.kolowert.c92b.bean.Operator;
import fun.kolowert.c92b.bean.Receipt;
import fun.kolowert.c92b.bean.SoldRecord;
import fun.kolowert.c92b.dao.DaoReceipt;
import fun.kolowert.c92b.dao.DaoSold;
import fun.kolowert.c92b.dao.DaoStore;
import fun.kolowert.c92b.utility.Utils;

public class OrderServ extends HttpServlet {

	private static final long serialVersionUID = 16387224L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		DaoReceipt daoReceipt = DaoReceipt.getInstance();
		DaoSold daoSold = DaoSold.getInstance();

		String baseMessage = "no message here";
		String task = request.getParameter("task");

		int currentReceiptId = -1;
		Receipt currentReceipt = null;
		Object preCurrentReceipt = session.getAttribute("currentReceipt");
		if (preCurrentReceipt instanceof Receipt) {
			currentReceipt = (Receipt) preCurrentReceipt;
			currentReceiptId = currentReceipt.getId();
		}

		// TASK FINISH comes from base.jsp
		if (task != null && task.equals("finish")) {
			// fill some fields in receipt
			if (currentReceipt != null) {
				currentReceipt.setClosetime(System.currentTimeMillis());
				currentReceipt.setOperatorId(getDutyOperatorId(session));
				daoReceipt.update(currentReceipt);
			}		
			baseMessage = "Receipt #" + currentReceiptId + " finished";
		}

		// TASK CANCEL comes from base.jsp
		if (task != null && task.equals("cancel")) {

			// remove canceled receipt
			daoReceipt.delete(currentReceiptId);

			// remove sold by this receipt items
			daoSold.deleteByReceipt(currentReceiptId);

			baseMessage = "Last receipt has been canceled";
		}

		// FINALIZING
		// adjust attributes
		request.setAttribute("soldRecords", null);
		session.setAttribute("currentReceipt", null);

		// message
		request.setAttribute("messageType", "good"); // are types "good" or "fail"
		request.setAttribute("orderMessage", baseMessage);

		getServletContext().getRequestDispatcher("/play/base.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Order#doPost"); // ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

		// check what task is it
		String formTask = request.getParameter("task");
		System.out.println("formTask: " + formTask); // ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

		HttpSession session = request.getSession();
		
		//check duty Operator
		int dutyOperatorId = getDutyOperatorId(session);
		if (dutyOperatorId < 0) {
			request.setAttribute("messageType", "fail"); // should be type "good" or "fail"
			request.setAttribute("orderMessage", "The Operator is out of session! Please do Log-Out and Log-In again");
			getServletContext().getRequestDispatcher("/play/base.jsp").forward(request, response);
			return;
		}
		
		// comes from base.jsp
		if (formTask.equals("addToReceipt")) {
			// receive item brief information from form
			String inputItemBrief = request.getParameter("itemBrief");
			String inputQuantity = request.getParameter("quantity");
			double requestQuantity = Utils.parseStringToDouble(inputQuantity);
			System.out.println("Order#doPost >> inputItem:" + inputItemBrief + " inputQuantity:" + inputQuantity); // ||||||||
			int itemId = parseIdFromBrief(inputItemBrief);
			System.out.println("Order#doPost >> itemId:" + itemId); // ||||||||
			// check input
			if (itemId < 0) {
				request.setAttribute("messageType", "fail"); // should be type "good" or "fail"
				request.setAttribute("orderMessage", "Rejected! Wrong Item");
				getServletContext().getRequestDispatcher("/play/base.jsp").forward(request, response);
				return;
			}
			if (requestQuantity < 0.0) {
				request.setAttribute("messageType", "fail"); // should be type "good" or "fail"
				request.setAttribute("orderMessage", "Rejected! Wrong Quantity");
				getServletContext().getRequestDispatcher("/play/base.jsp").forward(request, response);
				return;
			}

			// check if item present in database
			DaoStore daoStore = DaoStore.getInstance();
			Item item = daoStore.get(itemId);
			if (item != null) {
				// check when quantity must be integer
				MeasureUnit unit = item.getUnit();
				if (unit != MeasureUnit.kilogram && unit != MeasureUnit.tonne) {
					double fractional = requestQuantity - (int) requestQuantity;
					if (fractional != 0.0) {
						request.setAttribute("messageType", "fail"); // should be type "good" or "fail"
						request.setAttribute("orderMessage",
								"Rejected! Wrong Quantity. Should be integer for " + unit);
						getServletContext().getRequestDispatcher("/play/base.jsp").forward(request, response);
						return;
					}
				}

				// check quantity
				if (item.getQuantity() < requestQuantity) {
					request.setAttribute("messageType", "fail"); // should be type "good" or "fail"
					request.setAttribute("orderMessage", "Rejected! There are not enough " + item.getName()
							+ " in store! Asked for " + requestQuantity + "but there are " + item.getQuantity());
					getServletContext().getRequestDispatcher("/play/base.jsp").forward(request, response);
					return;
				}

				// adjust quantity in store
				Double remains = item.getQuantity() - requestQuantity;
				item.setQuantity(remains);
				daoStore.updateQuantity(item);
				
				// prepare current receipt
				Receipt currentReceipt = null;
				Object preCurrentReceipt = session.getAttribute("currentReceipt");
				if (preCurrentReceipt instanceof Receipt) {
					currentReceipt = (Receipt) preCurrentReceipt;
				} else {
					long unixTimeNow = System.currentTimeMillis();
					Receipt risingReceipt = new Receipt(-1, unixTimeNow, unixTimeNow, dutyOperatorId,
							0.0);
					DaoReceipt daoReceipt = DaoReceipt.getInstance();
					daoReceipt.insert(risingReceipt);
					currentReceipt = daoReceipt.getByOpenTime(unixTimeNow);
				}

				// increase sum in receipt and update in database
				double cost = requestQuantity * item.getPrice();
				double sum = currentReceipt.getSum() + cost;
				currentReceipt.setSum(sum);
				DaoReceipt daoReceipt = DaoReceipt.getInstance();
				daoReceipt.update(currentReceipt);

				// update soldRecords
				SoldRecord soldRecord = new SoldRecord(-1, currentReceipt.getId(), currentReceipt.getOpentime(),
						itemId, item.getPrice(), requestQuantity, cost);
				DaoSold daoSold = DaoSold.getInstance();
				daoSold.insert(soldRecord);
				List<SoldRecord> soldRecords = daoSold.getByReceipt(currentReceipt.getId());

				// finally set attributes
				request.setAttribute("soldRecords", soldRecords);
				session.setAttribute("currentReceipt", currentReceipt);
				request.setAttribute("messageType", "good"); // should be type "good" or "fail"
				String orderMessage = "Added: " + item.getName() + " >>> " + Utils.norm(requestQuantity) + " "
						+ item.getUnit() + " >>> cost=" + Utils.norm(cost);
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
		String[] parts = brief.split("\\s");
		try {
			result = Integer.parseInt(parts[0]);
		} catch (NumberFormatException e) {
			// TODO log here
			e.printStackTrace();
		}
		return result;
	}

}
