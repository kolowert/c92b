package fun.kolowert.c92b.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fun.kolowert.c92b.bean.Receipt;
import fun.kolowert.c92b.bean.SoldRecord;
import fun.kolowert.c92b.dao.DaoReceipt;
import fun.kolowert.c92b.dao.DaoSold;
import fun.kolowert.c92b.utility.Utils;

public class ReceiptServ extends HttpServlet {

	private static final long serialVersionUID = 16388804L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// prepare common
		DaoSold daoSold = DaoSold.getInstance();
		DaoReceipt daoReceipt = DaoReceipt.getInstance();
		String task = request.getParameter("task");
		int receiptId = Utils.parseIntIdFromObject(request.getParameter("receiptId"));

		// TASK deleteReceipt ____________________________________________________
		if (task != null && task.equals("deleteReceipt")) {
			System.out.println("Receipt#doGet >> task.equals \"deleteReceipt\""); // ||||||||||||||||||||||||||||||||||||||
			// TODO
			Receipt receipt = daoReceipt.getById(receiptId);
			
			// adjust quantity in store
			// TODO
			
			daoReceipt.delete(receiptId);
			
			getServletContext().getRequestDispatcher("/play/receipts.jsp").forward(request, response);
		}

		// TASK cancelRecord ______________________________________________________
		if (task != null && task.equals("cancelRecord")) {
			System.out.println("Receipt#doGet >> task.equals \"cancelRecord\""); // ||||||||||||||||||||||||||||||||||||||
			
			// get record removing it from database
			int recordId = Utils.parseIntIdFromObject(request.getParameter("recordId"));
			SoldRecord removedRecord = daoSold.removeSoldRecord(recordId);
			
			// adjust sum in receipt
			Receipt receipt = daoReceipt.getById(receiptId);
			double sum = receipt.getSum();
			sum = sum - removedRecord.getSoldCost();
			receipt.setSum(sum);
			daoReceipt.update(receipt);
			
			// adjust quantity in store
			// TODO
			
			// go back to the receipt
			String forDirection = "/play/receipt.jsp?id=" + receiptId;
			getServletContext().getRequestDispatcher(forDirection).forward(request, response);
		}

	}

}
