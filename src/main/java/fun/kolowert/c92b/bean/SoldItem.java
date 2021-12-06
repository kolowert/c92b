package fun.kolowert.c92b.bean;

public class SoldItem {

	private int receiptId;
	private long receiptTime;
	private int ItemId;
	private double soldPrice;
	private double soldQuantity;
	private double soldCost;

	public SoldItem(int receiptId, long receiptTime, int itemId, double soldPrice, double soldQuantity,
			double soldCost) {
		this.receiptId = receiptId;
		this.receiptTime = receiptTime;
		ItemId = itemId;
		this.soldPrice = soldPrice;
		this.soldQuantity = soldQuantity;
		this.soldCost = soldCost;
	}

	public int getReceiptId() {
		return receiptId;
	}

	public long getReceiptTime() {
		return receiptTime;
	}

	public int getItemId() {
		return ItemId;
	}

	public double getSoldPrice() {
		return soldPrice;
	}

	public double getSoldQuantity() {
		return soldQuantity;
	}

	public double getSoldCost() {
		return soldCost;
	}

	@Override
	public String toString() {
		return "SoldItem [receiptId=" + receiptId + ", ItemId=" + ItemId + ", soldPrice=" + soldPrice
				+ ", soldQuantity=" + soldQuantity + ", soldCost=" + soldCost + "]";
	}

	public String brief() {
		return "price:" + soldPrice + ", quantity:" + soldQuantity + ", cost:" + soldCost;
	}
}
