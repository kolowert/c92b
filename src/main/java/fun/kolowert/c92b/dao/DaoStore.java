package fun.kolowert.c92b.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fun.kolowert.c92b.bean.Item;
import fun.kolowert.c92b.bean.MeasureUnit;

public class DaoStore {

	private static DaoStore INSTANCE;

	private DaoStore() {
	}

	public static DaoStore getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DaoStore();
		}
		return INSTANCE;
	}

	public Item get(int id) {
		Item item = null;
		String sqlInstruction = "SELECT * FROM store WHERE item_id = " + id;
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				item = new Item(
						resultSet.getInt("item_id"), 
						resultSet.getString("name"), 
						findMesureUnit(resultSet.getInt("measure_unit")),
						resultSet.getDouble("quantity"), 
						resultSet.getDouble("price"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.getInstance().release(con);
		}
		return item;
	}

	public List<Item> getAll() {
		List<Item> items = new ArrayList<>();
		String sqlInstruction = "SELECT * FROM store";
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Item item = new Item(
						resultSet.getInt("item_id"), 
						resultSet.getString("name"), 
						findMesureUnit(resultSet.getInt("measure_unit")),
						resultSet.getDouble("quantity"), 
						resultSet.getDouble("price"));
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.getInstance().release(con);
		}
		return items;
	}

	private MeasureUnit findMesureUnit(int measureUnitId) {
		MeasureUnit measureUnit = MeasureUnit.values()[0];
		try {
			measureUnit = MeasureUnit.values()[measureUnitId - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO
		}
		return measureUnit;
	}
}
