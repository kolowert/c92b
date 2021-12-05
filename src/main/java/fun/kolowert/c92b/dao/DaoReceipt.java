package fun.kolowert.c92b.dao;

import java.util.ArrayList;
import java.util.List;

import fun.kolowert.c92b.bean.Receipt;

public class DaoReceipt {

	private static DaoReceipt INSTANCE;

	// TODO rewrite this stubs
	private List<Receipt> receipts = new ArrayList<Receipt>();

	private DaoReceipt() {
	}

	public static DaoReceipt getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DaoReceipt();
		}
		return INSTANCE;
	}

	public Receipt getReceiptById(int id) {
		for (Receipt receipt : receipts) {
			if (receipt.getId() == id) {
				return receipt;
			}
		}
		return null;
	}
	
	public Receipt createNewReceipt() {
		// TODO
		long unixTimeNow = System.currentTimeMillis() / 1000L;
		Receipt receipt = new Receipt(unixTimeNow);
		java.util.Random rnd = new java.util.Random();
		receipt.setId(rnd.nextInt(899) + 100);
		receipts.add(receipt);
		return receipt;
	}

	public List<Receipt> getReceipts() {
		return receipts;
	}

}
