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
	
	public boolean update(Receipt neo) {
		// TODO this stub
		for (Receipt old : receipts) {
			if (old.getId() == neo.getId()) {
				receipts.remove(old);
				receipts.add(neo);
			}
		}
		return false;
	}
	
	public boolean remove(int id) {
		// TODO this stub
		for (Receipt receipt : receipts) {
			if (receipt.getId() == id) {
				return receipts.remove(receipt);
			}
		}
		return false;
	}
	
	public Receipt createNewReceipt() {
		// TODO
		long unixTimeNow = System.currentTimeMillis();
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
