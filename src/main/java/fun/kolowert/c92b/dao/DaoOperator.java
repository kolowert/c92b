package fun.kolowert.c92b.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fun.kolowert.c92b.bean.Operator;
import fun.kolowert.c92b.utility.PasswordUtils;

public final class DaoOperator {

	private static DaoOperator INSTANCE;

	private DaoOperator() {
	}

	public static DaoOperator getInstance() {

		System.out.println("DaoOperator#getInstance >> daoOperator: " + INSTANCE); // |||||||||||||||||||||||||||

		if (INSTANCE == null) {
			INSTANCE = new DaoOperator();
			System.out.println("DaoOperator#getInstance >> ~~~ NEW ~~~"); // |||||||||||||||||||||||||||
		}
		return INSTANCE;
	}

	public void insert(String login, String role, String password) {
		String salt = PasswordUtils.generateSalt(16);
		System.out.println("DaoOperator# >> salt " + salt); // ||||||||||||||||||||||||||||||||||||||
		String passHash = PasswordUtils.hashTextPassword(password, salt).get();
		System.out.println("DaoOperator# >> passHass " + passHash); // ||||||||||||||||||||||||||||||||||||||

		String sqlInstruction = "INSERT INTO operator (login, passHash, role, salt) Values (?, ?, ?, ?)";
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			statement.setString(1, login);
			statement.setString(2, passHash);
			statement.setString(3, role);
			statement.setString(4, salt);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connector.getInstance().release(con);
		System.out.println("DaoOperator# >> after adding"); // ||||||||||||||||||||||||||||||||||||||
	}

	public Operator get(int id) {
		Operator operator = null;
		String sqlInstruction = "SELECT * FROM operator WHERE id = " + id;
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				operator = new Operator(resultSet.getInt("id"), resultSet.getString("login"),
						resultSet.getString("passHash"), resultSet.getString("role"), resultSet.getString("salt"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.getInstance().release(con);
		}
		return operator;
	}

	public boolean update(int id, String inputLogin, String inputRole, String inputPassword) {
		Connection con = Connector.getInstance().getConnection();
		if (inputPassword != null && inputPassword.length() > 2) {
			String salt = PasswordUtils.generateSalt(16);
			String passHash = PasswordUtils.hashTextPassword(inputPassword, salt).get();
			String sqlInstruction = "UPDATE operator SET login = ?, passHash = ?, role = ?, salt = ? WHERE id = ?";
			try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
				statement.setString(1, inputLogin);
				statement.setString(2, passHash);
				statement.setString(3, inputRole);
				statement.setString(4, salt);
				statement.setInt(5, id);
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

		String sqlInstruction = "UPDATE operator SET login = ?, role = ? WHERE id = ?";
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			statement.setString(1, inputLogin);
			statement.setString(2, inputRole);
			statement.setInt(3, id);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.getInstance().release(con);
		}
		return false;
	}

	public void delete(int id) {
		String sqlInstruction = "DELETE FROM operator WHERE id = ?";
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.getInstance().release(con);
		}
	}

	public List<Operator> getAll() {
		System.out.println("DaoOperator#getOperators >>"); // |||||||||||||||||||||||||||||||||||||||||||
		List<Operator> operators = new ArrayList<>();
		String sqlInstruction = "SELECT * FROM operator";
		Connection con = Connector.getInstance().getConnection();
		try (PreparedStatement statement = con.prepareStatement(sqlInstruction)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Operator operator = new Operator(resultSet.getInt("id"), resultSet.getString("login"),
						resultSet.getString("role"));
				operators.add(operator);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.getInstance().release(con);
		}
		operators.sort(new Comparator<Operator>() {
			@Override
			public int compare(Operator o1, Operator o2) {
				return o1.getLogin().compareTo(o2.getLogin());
			}
		});
		return operators;
	}
	
}
