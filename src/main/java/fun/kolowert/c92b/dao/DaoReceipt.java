package fun.kolowert.c92b.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fun.kolowert.c92b.bean.Receipt;

public class DaoReceipt {

	private static DaoReceipt INSTANCE;

	private DaoReceipt() {
	}

	public static DaoReceipt getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DaoReceipt();
		}
		return INSTANCE;
	}

	public Receipt getById(int id) {
		Receipt receipt = null;
		String sqlInstruction = "SELECT * FROM receipt WHERE id = " + id;
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				receipt = new Receipt(resultSet.getInt("id"), resultSet.getLong("opentime"),
						resultSet.getLong("closetime"), resultSet.getInt("operator_id"), resultSet.getDouble("sum"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.getInstance().release(con);
		}
		return receipt;
	}

	public boolean update(Receipt receipt) {
		Connection con = Connector.getInstance().getConnection();
		String sqlInstruction = "UPDATE receipt SET closetime = ?, sum = ? WHERE id = ?";
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			statement.setLong(1, System.currentTimeMillis());
			statement.setDouble(2, receipt.getSum());
			statement.setInt(3, receipt.getId());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			Connector.getInstance().release(con);
		}
	}

	public boolean delete(int id) {
		String sqlInstruction = "DELETE FROM receipt WHERE id = " + id;
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.getInstance().release(con);
		}
		return false;
	}

	public boolean insert(Receipt receipt) { // |||||||||||||||||||||||||||||||||||
		String sqlInstruction = "INSERT INTO receipt (opentime, closetime, operator_id, sum) Values (?, ?, ?, ?)";
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			statement.setLong(1, receipt.getOpentime());
			statement.setLong(2, receipt.getClosetime());
			statement.setInt(3, receipt.getOperatorId());
			statement.setDouble(4, receipt.getSum());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			Connector.getInstance().release(con);
		}
		System.out.println("DaoOperator# >> after adding"); // ||||||||||||||||||||||||||||||||||||||
		return true;
	}

	public Receipt getByOpenTime(long openTime) {
		Receipt receipt = null;
		String sqlInstruction = "SELECT * FROM receipt WHERE opentime = " + openTime;
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				receipt = new Receipt(resultSet.getInt("id"), resultSet.getLong("opentime"),
						resultSet.getLong("closetime"), resultSet.getInt("operator_id"), resultSet.getDouble("sum"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.getInstance().release(con);
		}
		return receipt;
	}

	public List<Receipt> getAll() {
		List<Receipt> receipts = new ArrayList<>();
		String sqlInstruction = "SELECT * FROM receipt";
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Receipt receipt = new Receipt(resultSet.getInt("id"), resultSet.getLong("opentime"),
						resultSet.getLong("closetime"), resultSet.getInt("operator_id"), resultSet.getDouble("sum"));
				receipts.add(receipt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.getInstance().release(con);
		}
		return receipts;
	}

}
