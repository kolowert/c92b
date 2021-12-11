package fun.kolowert.c92b.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ConnectionPool {
	private DataSource dataSource;
	private List<Connection> connectionPool;
	private List<Connection> usedConnections = new ArrayList<>();
	private static int INITIAL_POOL_SIZE = 8;
	private static int MAX_POOL_SIZE = 32;
	private static int MAX_TIMEOUT = 1860;

	private ConnectionPool(DataSource dataSource, List<Connection> pool) {
		this.dataSource = dataSource;
		connectionPool = pool;
	}

	public static ConnectionPool create(DataSource dataSource) {
		List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
		for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
			Connection nextConnection = null;
			try {
				nextConnection = dataSource.getConnection();
				pool.add(nextConnection);
			} catch (SQLException e) {
				// TODO
				e.printStackTrace();
			}
		}
		return new ConnectionPool(dataSource, pool);
	}
	
	/**
	 * It returns not used connection from pool
	 * If INITIAL_POOL_SIZE is not enough it extends connection pool to MAX_POOL_SIZE
	 * When there are not used extra connections It kills them
	 * @return connection from pool
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		while (!connectionPool.isEmpty()) {
			Connection connection = connectionPool.remove(connectionPool.size() - 1);
			if (connection.isValid(MAX_TIMEOUT)) {
				usedConnections.add(connection);
				return connection;
			}
			if (getSize() >= INITIAL_POOL_SIZE) {
				if (!connection.isClosed())
					connection.close();
				continue;
			}
		}
		if (usedConnections.size() < MAX_POOL_SIZE) {
			connectionPool.add(dataSource.getConnection());
		} else {
			// TODO rewrite
			throw new RuntimeException("Maximum pool size reached, no available connections!");
		}
		Connection connection = connectionPool.remove(connectionPool.size() - 1);
		usedConnections.add(connection);
		return connection;
	}

	public boolean release(Connection connection) {
		connectionPool.add(connection);
		return usedConnections.remove(connection);
	}

	public int getSize() {
		return connectionPool.size() + usedConnections.size();
	}

	public void shutdown() throws SQLException {
		usedConnections.forEach(this::release);
		for (Connection c : connectionPool) {
			c.close();
		}
		connectionPool.clear();
	}

}
