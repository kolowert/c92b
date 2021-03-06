package fun.kolowert.c92b.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fun.kolowert.c92b.bean.Item;
import fun.kolowert.c92b.bean.Receipt;
import fun.kolowert.c92b.bean.SoldRecord;
import fun.kolowert.c92b.dao.DaoReceipt;
import fun.kolowert.c92b.dao.DaoSold;
import fun.kolowert.c92b.dao.DaoStore;
import fun.kolowert.c92b.utility.Utils;

public class ReceiptServ extends HttpServlet {

	private static final long serialVersionUID = 16388804L;
	
	private static final Logger logger = LogManager.getLogger("ReceiptServ");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// prepare common
		DaoStore daoStore = DaoStore.getInstance();
		DaoSold daoSold = DaoSold.getInstance();
		DaoReceipt daoReceipt = DaoReceipt.getInstance();
		String task = request.getParameter("task");
		int receiptId = Utils.parseIntIdFromObject(request.getParameter("receiptId"));

		// TASK deleteReceipt ____________________________________________________
		if (task != null && task.equals("deleteReceipt")) {
			logger.debug("Receipt#doGet >> task.equals \"deleteReceipt\"");
			
			// adjust quantity in store			
			List<SoldRecord> records = daoSold.getByReceipt(receiptId);
			for (SoldRecord record : records) {
				int itemId = record.getItemId();
				Item item = daoStore.get(itemId);
				item.setQuantity(item.getQuantity() + record.getSoldQuantity());
				daoStore.updateQuantity(item);
			}
			
			
			daoReceipt.delete(receiptId);
			getServletContext().getRequestDispatcher("/play/receipts.jsp").forward(request, response);
		}

		// TASK cancelRecord ______________________________________________________
		if (task != null && task.equals("cancelRecord")) {
			logger.debug("Receipt#doGet >> task.equals \"cancelRecord\"");
			
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
			int itemId = removedRecord.getItemId();
			Item item = daoStore.get(itemId);
			item.setQuantity(item.getQuantity() + removedRecord.getSoldQuantity());
			daoStore.updateQuantity(item);
			
			// go back to the receipt
			String forDirection = "/play/receipt.jsp?id=" + receiptId;
			getServletContext().getRequestDispatcher(forDirection).forward(request, response);
		}

	}

}
