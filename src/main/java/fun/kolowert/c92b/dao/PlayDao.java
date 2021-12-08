package fun.kolowert.c92b.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class PlayDao {

	public static void main(String[] args) throws SQLException {

		playOperator("SELECT * FROM operator");
		System.out.println("~ ~ ~ ~ ~ ~ ~ ~");

		playStore("SELECT * FROM store");
		System.out.println("~ ~ ~ ~ ~ ~ ~ ~");

	}

	private static void playOperator(String sqlInstruction) throws SQLException {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		DataSource datasource = DataSourceMaster.get();
		ConnectionPool connectionpool = ConnectionPool.create(datasource);

		con = connectionpool.getConnection();
		statement = con.prepareStatement(sqlInstruction);
		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			System.out.println(
					resultSet.getInt("id") + "^" + resultSet.getString("login") + "^" + resultSet.getString("role"));
		}
		connectionpool.release(con);
		connectionpool.shutdown();
	}

	private static void playStore(String sqlInstruction) throws SQLException {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		DataSource datasource = DataSourceMaster.get();
		ConnectionPool connectionpool = ConnectionPool.create(datasource);

		con = connectionpool.getConnection();
		statement = con.prepareStatement(sqlInstruction);
		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.getInt("item_id") + "^" + resultSet.getString("name") + "^"
					+ resultSet.getString("measure_unit") + "^" + resultSet.getString("quantity") + "^"
					+ resultSet.getString("price"));
		}
		connectionpool.release(con);
		connectionpool.shutdown();
	}

}
