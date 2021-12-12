package fun.kolowert.c92b.bean;

import fun.kolowert.c92b.utility.Utils;

public class Item {

	private int id;
	private String name;
	private MeasureUnit unit;
	private double quantity;
	private double price;
	
	public Item() {
		this.id = -1;
		this.name = "undefined";
		this.unit = MeasureUnit.piece;
		this.quantity = 0.0;
		this.price = 0.0;
	}

	public Item(int id, String name, MeasureUnit unit, double quantity, double price) {
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.quantity = quantity;
		this.price = price;
	}
	
	public static Item getNullItem() {
		return new Item(-1, "Null Item", MeasureUnit.piece, 0.0, 0.0);
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public MeasureUnit getUnit() {
		return unit;
	}

	public double getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUnit(MeasureUnit unit) {
		this.unit = unit;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String brief() {
		String idView = Utils.norm(id);
		return idView + " | " + name + "   >> " + price + " / " + unit + " ( " + quantity + " )";
	}
	
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", unit=" + unit + ", quantity=" + quantity + ", price=" + price
				+ "]";
	}
}
