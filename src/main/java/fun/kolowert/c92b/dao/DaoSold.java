package fun.kolowert.c92b.dao;

import java.util.ArrayList;
import java.util.List;

import fun.kolowert.c92b.bean.SoldItem;

public class DaoSold {
	
	private static DaoSold INSTANCE;

	// TODO rewrite this stubs
	private List<SoldItem> soldItems = new ArrayList<>();

	private DaoSold() {
	}
	
	public static DaoSold getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DaoSold();
		}
		return INSTANCE;
	}
	
	public boolean add(SoldItem s) {
		// TODO this stub
		return soldItems.add(s);
	}
	
	public List<SoldItem> getSoldItems() {
		return soldItems;
	}
	
	public List<SoldItem> getSoldItems(int receiptId) {
		// TODO this stub
		List<SoldItem> result = new ArrayList<>();
		for (SoldItem s : soldItems) {
			if (s.getReceiptId() == receiptId) {
				result.add(s);
			}
		}
		return result;
	}
	
	public List<SoldItem> getSoldItems(long timeFrom, long timeUntil) {
		// TODO this stub
		List<SoldItem> result = new ArrayList<>();
		for (SoldItem s : soldItems) {
			long receiptTime = s.getReceiptTime(); 
			if (receiptTime >= timeFrom || receiptTime < timeUntil) {
				result.add(s);
			}
		}
		return result;
	}
}
