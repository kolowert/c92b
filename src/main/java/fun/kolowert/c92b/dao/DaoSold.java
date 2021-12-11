package fun.kolowert.c92b.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fun.kolowert.c92b.bean.SoldRecord;

public class DaoSold {

	private static DaoSold INSTANCE;

	private DaoSold() {
	}

	public static DaoSold getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DaoSold();
		}
		return INSTANCE;
	}

	public boolean insert(SoldRecord record) {
		String sqlInstruction = "INSERT INTO sold_record (receipt_id, receipt_time, item_id, price, quantity, cost) "
				+ "Values (?, ?, ?, ?, ?, ?)";
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			statement.setInt(1, record.getReceiptId());
			statement.setLong(2, record.getReceiptTime());
			statement.setInt(3, record.getItemId());
			statement.setDouble(4, record.getSoldPrice());
			statement.setDouble(5, record.getSoldQuantity());
			statement.setDouble(6, record.getSoldCost());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			Connector.getInstance().release(con);
		}
		return true;
	}

	public List<SoldRecord> getAll() {
		List<SoldRecord> soldRecords = new ArrayList<>();
		String sqlInstruction = "SELECT * FROM sold_record";
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				SoldRecord record = new SoldRecord(
						resultSet.getInt("id"), 
						resultSet.getInt("receipt_id"),
						resultSet.getLong("receipt_time"),
						resultSet.getInt("item_id"),
						resultSet.getDouble("price"),
						resultSet.getDouble("quantity"),
						resultSet.getDouble("cost")
						);
				soldRecords.add(record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.getInstance().release(con);
		}
		return soldRecords;
	}

	public List<SoldRecord> getByReceipt(int receiptId) {
		List<SoldRecord> records = new ArrayList<>();
		String sqlInstruction = "SELECT * FROM sold_record WHERE receipt_id = " + receiptId;
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				SoldRecord record = new SoldRecord(
						resultSet.getInt("id"), 
						resultSet.getInt("receipt_id"),
						resultSet.getLong("receipt_time"),
						resultSet.getInt("item_id"),
						resultSet.getDouble("price"),
						resultSet.getDouble("quantity"),
						resultSet.getDouble("cost")
						);
				records.add(record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.getInstance().release(con);
		}
		return records;
	}
	
	public SoldRecord getById(int id) {
		SoldRecord record = null;
		String sqlInstruction = "SELECT * FROM sold_record WHERE id = " + id;
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				record = new SoldRecord(
						resultSet.getInt("id"), 
						resultSet.getInt("receipt_id"),
						resultSet.getLong("receipt_time"),
						resultSet.getInt("item_id"),
						resultSet.getDouble("price"),
						resultSet.getDouble("quantity"),
						resultSet.getDouble("cost")
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.getInstance().release(con);
		}
		return record;
	}

//	public List<SoldRecord> getSoldRecords(long timeFrom, long timeUntil) {
//		// TODO this stub
//		List<SoldRecord> result = new ArrayList<>();
//		for (SoldRecord s : soldRecords) {
//			long receiptTime = s.getReceiptTime();
//			if (receiptTime >= timeFrom || receiptTime < timeUntil) {
//				result.add(s);
//			}
//		}
//		return result;
//	}

	/**
	 * @param recordId
	 * @return particular SoldRecord or null
	 */
	public SoldRecord removeSoldRecord(int recordId) {
		SoldRecord recordToRemove = getById(recordId);
		String sqlInstruction = "DELETE FROM sold_record WHERE id = " + recordId;
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.getInstance().release(con);
		}
		return recordToRemove;
	}

	public void deleteByReceipt(int receiptId) {
		String sqlInstruction = "DELETE FROM sold_record WHERE receipt_id = " + receiptId;
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.getInstance().release(con);
		}
	}
	
}
