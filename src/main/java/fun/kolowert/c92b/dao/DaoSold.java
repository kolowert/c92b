package fun.kolowert.c92b.dao;

import java.util.ArrayList;
import java.util.List;

import fun.kolowert.c92b.bean.SoldRecord;

public class DaoSold {

	private static DaoSold INSTANCE;

	// TODO rewrite this stubs
	private List<SoldRecord> soldRecords = new ArrayList<>();

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
		return soldRecords.add(s);
	}

	public List<SoldRecord> getSoldRecords() {
		return soldRecords;
	}

	public List<SoldRecord> getSoldRecords(int receiptId) {
		// TODO this stub
		List<SoldRecord> result = new ArrayList<>();
		for (SoldRecord s : soldRecords) {
			if (s.getReceiptId() == receiptId) {
				result.add(s);
			}
		}
		return result;
	}

	public List<SoldRecord> getSoldRecords(long timeFrom, long timeUntil) {
		// TODO this stub
		List<SoldRecord> result = new ArrayList<>();
		for (SoldRecord s : soldRecords) {
			long receiptTime = s.getReceiptTime();
			if (receiptTime >= timeFrom || receiptTime < timeUntil) {
				result.add(s);
			}
		}
		return result;
	}
	
	/**
	 * @param recordId
	 * @return particular SoldRecord or null
	 */
	public SoldRecord removeSoldRecord(int recordId) {
		// TODO this stub
		SoldRecord result = null; 
		for (SoldRecord s : soldRecords) {
			if (s.getId() == recordId) {
				int index = soldRecords.indexOf(s);
				return soldRecords.remove(index);
			}
		}
		return result;
	}
	
	public boolean removeSoldRecords(int receiptId) {
		// TODO this stub
		boolean isRemoved = false;
		for (SoldRecord s : soldRecords) {
			if (s.getReceiptId() == receiptId) {
				isRemoved = soldRecords.remove(s);
			}
		}
		return isRemoved;
	}
}
