package fun.kolowert.c92b.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fun.kolowert.c92b.bean.Item;
import fun.kolowert.c92b.bean.MeasureUnit;
import fun.kolowert.c92b.utility.Utils;

public class DaoStore {
	
	private static final Logger logger = LogManager.getLogger("DaoStore");

	private static DaoStore INSTANCE;

	private DaoStore() {
	}

	public static DaoStore getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DaoStore();
		}
		return INSTANCE;
	}
	
	public boolean insert(Item item) {
		String sqlInstruction = "INSERT INTO store (name, measure_unit, quantity, price) "
				+ "Values (?, ?, ?, ?)";
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			statement.setString(1, item.getName());
			int unitId = MeasureUnit.valueOf(item.getUnit().toString()).ordinal() + 1;
			statement.setInt(2, unitId);
			statement.setDouble(3, item.getQuantity());
			statement.setDouble(4, item.getPrice());
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("exception" + e);
			return false;
		} finally {
			Connector.getInstance().release(con);
		}
		return true;
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
						Utils.findMesureUnitValue(resultSet.getInt("measure_unit")),
						resultSet.getDouble("quantity"), 
						resultSet.getDouble("price"));
			}
		} catch (SQLException e) {
			logger.error("exception" + e);
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
						Utils.findMesureUnitValue(resultSet.getInt("measure_unit")),
						resultSet.getDouble("quantity"), 
						resultSet.getDouble("price"));
				items.add(item);
			}
		} catch (SQLException e) {
			logger.error("exception" + e);
		} finally {
			Connector.getInstance().release(con);
		}
		items.sort(new Comparator<Item>() {
			@Override
			public int compare(Item i1, Item i2) {
				return i1.getName().compareTo(i2.getName());
			}
		});
		return items;
	}
	
	public boolean update(Item item) {
		Connection con = Connector.getInstance().getConnection();
		String sqlInstruction = "UPDATE store SET name = ?, measure_unit = ?, quantity = ?, price = ? WHERE item_id = ?";
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			statement.setString(1, item.getName());
			statement.setInt(2, Utils.findMesureUnitId(item.getUnit()));
			statement.setDouble(3, item.getQuantity());
			statement.setDouble(4, item.getPrice());
			statement.setInt(5, item.getId());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("exception" + e);
			return false;
		} finally {
			Connector.getInstance().release(con);
		}
	}
	
	public boolean updateQuantity(Item item) {
		Connection con = Connector.getInstance().getConnection();
		String sqlInstruction = "UPDATE store SET quantity = ? WHERE item_id = ?";
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			statement.setDouble(1, item.getQuantity());
			statement.setInt(2, item.getId());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("exception" + e);
			return false;
		} finally {
			Connector.getInstance().release(con);
		}
	}
	
	public boolean delete(int id) {
		String sqlInstruction = "DELETE FROM store WHERE item_id = " + id;
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("exception" + e);
		} finally {
			Connector.getInstance().release(con);
		}
		return false;
	}
	
}
