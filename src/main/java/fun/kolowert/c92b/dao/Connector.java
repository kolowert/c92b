package fun.kolowert.c92b.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This singleton uses once created connection pool
 */
public class Connector {
	
	private static final Logger logger = LogManager.getLogger("Connector");
	
	private static Connector INSTANCE;
	
	private ConnectionPool connectionPool = null;
	
	private Connector() {
		DataSource datasource = DataSourceMaster.get();
		connectionPool = ConnectionPool.create(datasource);
	}
	
	public static Connector getInstance() {

		logger.debug("Connector: " + INSTANCE);

		if (INSTANCE == null) {
			INSTANCE = new Connector();
			logger.debug("Connector#getInstance >> ~~~ NEW ~~~");
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
			logger.error("First try to connect" + e);
		}
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			logger.error("exception" + e);
		}
		try {
			return connectionPool.getConnection();
		} catch (SQLException e) {
			logger.error("Second try to connect" + e);
		}
		return null;
	}
	
	public void release(Connection connection) {
		connectionPool.release(connection);
	}
}
