package fun.kolowert.c92b.dao;

import java.util.ArrayList;
import java.util.List;

import fun.kolowert.c92b.bean.SoldRecord;

public class DaoSold {

	private static DaoSold INSTANCE;

	// TODO rewrite this stubs
	private List<SoldRecord> soldItems = new ArrayList<>();

	private DaoSold() {
	}

	public static DaoSold getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DaoSold();
		}
		return INSTANCE;
	}

	public boolean add(SoldRecord s) {
		// TODO this stub
		return soldItems.add(s);
	}

	public List<SoldRecord> getSoldRecords() {
		return soldItems;
	}

	public List<SoldRecord> getSoldRecords(int receiptId) {
		// TODO this stub
		List<SoldRecord> result = new ArrayList<>();
		for (SoldRecord s : soldItems) {
			if (s.getReceiptId() == receiptId) {
				result.add(s);
			}
		}
		return result;
	}

	public List<SoldRecord> getSoldRecords(long timeFrom, long timeUntil) {
		// TODO this stub
		List<SoldRecord> result = new ArrayList<>();
		for (SoldRecord s : soldItems) {
			long receiptTime = s.getReceiptTime();
			if (receiptTime >= timeFrom || receiptTime < timeUntil) {
				result.add(s);
			}
		}
		return result;
	}

	public boolean removeSoldRecords(int receiptId) {
		// TODO this stub
		boolean isRemoved = false;
		for (SoldRecord s : soldItems) {
			if (s.getReceiptId() == receiptId) {
				isRemoved = soldItems.remove(s);
			}
		}
		return isRemoved;
	}
}
