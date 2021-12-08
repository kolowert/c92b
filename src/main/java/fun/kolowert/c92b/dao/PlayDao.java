package fun.kolowert.c92b.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import fun.kolowert.c92b.utility.Utils;

public class PlayDao {

	private static final String propertiesSourceFile = "resources/mysqldb_test.properties";

	public static void main(String[] args) throws SQLException {
		
		System.out.println("propertiesSourceFile: " + propertiesSourceFile);
		
		System.out.println("SELECT * FROM operator");
		playOperator("SELECT * FROM operator");
		System.out.println("~ ~ ~ ~ ~ ~ ~ ~");

		System.out.println("SELECT * FROM store");
		playStore("SELECT * FROM store");
		System.out.println("~ ~ ~ ~ ~ ~ ~ ~");

		System.out.println("SELECT * FROM receipt");
		playReceipt("SELECT * FROM receipt");
		System.out.println("~ ~ ~ ~ ~ ~ ~ ~");

	}

	private static void playOperator(String sqlInstruction) throws SQLException {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		DataSource datasource = DataSourceMaster.get(propertiesSourceFile);
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

		DataSource datasource = DataSourceMaster.get(propertiesSourceFile);
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

	private static void playReceipt(String sqlInstruction) throws SQLException {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		DataSource datasource = DataSourceMaster.get(propertiesSourceFile);
		ConnectionPool connectionpool = ConnectionPool.create(datasource);

		con = connectionpool.getConnection();
		statement = con.prepareStatement(sqlInstruction);
		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			long openTime = resultSet.getLong("opentime");
			long closeTime = resultSet.getLong("closetime");
			System.out.println(resultSet.getInt("id") + " ^ " + openTime + " ^ " + closeTime + " ^ "
					+ resultSet.getInt("operator_id") + " ^ " + resultSet.getDouble("sum") + " ^^ "
					+ Utils.unixTimeToTimeStamp(openTime) + " ^^ " + Utils.unixTimeToTimeStamp(closeTime));
		}
		connectionpool.release(con);
		connectionpool.shutdown();
	}
}
