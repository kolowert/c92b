package fun.kolowert.c92b.dao;

import java.util.Arrays;
import java.util.List;

import fun.kolowert.c92b.bean.Item;
import fun.kolowert.c92b.bean.MeasureUnit;

public class DaoStore {

	private static DaoStore daoStore;

	// TODO rewrite this stubs
	private List<Item> items;

	private DaoStore() {
		// This is stub
		Item[] preItems = { 
				new Item(11, "Marmalade", MeasureUnit.jar, 100, 250.00),
				new Item(12, "Eclair", MeasureUnit.bar, 45, 55.00),
				new Item(13, "Strawberry Pie", MeasureUnit.thing, 20, 25.00),
				new Item(30, "Vanilla Pudding", MeasureUnit.thing, 32, 16.95),			
				new Item(23, "Pie with Cream", MeasureUnit.thing, 15, 18.50),
				new Item(15, "Cranberry Cookies", MeasureUnit.kilogram, 28, 25.65),
				new Item(16, "Red Currants Jelly", MeasureUnit.tonn, 0.55, 130.00),
				new Item(17, "Custard Cake", MeasureUnit.kilogram, 9, 19.99),
				new Item(18, "Shortcrust Pastry with Cheese", MeasureUnit.thing, 30, 12.50),
				new Item(19, "Honey Biscuits", MeasureUnit.kilogram, 15, 9.96),
				new Item(20, "Pecan Pie", MeasureUnit.kilogram, 1.1, 145.00),
				new Item(21, "Poppy Seed Cake", MeasureUnit.kilogram, 3.5, 169.00),
				new Item(22, "Bilberry Tart", MeasureUnit.thing, 10, 45.00),
				new Item(14, "Tiramisu", MeasureUnit.jar, 14, 99.99),
				new Item(24, "Cheesecake with Raisins", MeasureUnit.kilogram, 3.9, 51.00),
				new Item(25, "Rhubarb Pie", MeasureUnit.thing, 8, 41.00),
				new Item(26, "Cherry Strudel", MeasureUnit.kilogram, 2.5, 65.00),
				new Item(27, "Apple Strudel", MeasureUnit.kilogram, 5.6, 75.00),
				new Item(28, "Christstollen", MeasureUnit.bar, 24, 45.00),
				new Item(32, "Cream Cake", MeasureUnit.bar, 3, 50.00),
				new Item(33, "Fashion Bagel", MeasureUnit.thing, 20, 18.75),
				new Item(29, "Сhocolate", MeasureUnit.bar, 100, 10.00) };
		
		items = Arrays.asList(preItems);
	}

	public static DaoStore getInstance() {
		if (daoStore != null) {
			return daoStore;
		}
		return new DaoStore();
	}
	
	public List<Item> getItems() {
		return items;
	}
	
}
