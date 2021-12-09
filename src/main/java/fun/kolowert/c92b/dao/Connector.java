package fun.kolowert.c92b.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * This singleton uses once created connection pool
 */
public class Connector {
	
	private static Connector INSTANCE;
	
	private ConnectionPool connectionPool = null;
	
	private Connector() {
		DataSource datasource = DataSourceMaster.get();
		connectionPool = ConnectionPool.create(datasource);
	}
	
	public static Connector getInstance() {

		System.out.println("Connector#getInstance >> Connector: " + INSTANCE); // ||||||||||||||||||||||

		if (INSTANCE == null) {
			INSTANCE = new Connector();
			System.out.println("Connector#getInstance >> ~~~ NEW ~~~"); // |||||||||||||||||||||||||||
		}
		return INSTANCE;
	}
	
	/**
	 * two attempts to connect and null if can'n
	 * @return java.sql.Connection or null
	 */
	public Connection getConnection() {
		try {
			return connectionPool.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return connectionPool.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
