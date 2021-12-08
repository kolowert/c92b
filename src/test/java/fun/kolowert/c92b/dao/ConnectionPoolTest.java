package fun.kolowert.c92b.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

public class ConnectionPoolTest {
	
	private static final String propertiesSourceFile = "resources/mysqldb_test.properties";
	
	@Test
	public void whenCalledgetConnection_thenCorrect() throws SQLException {
		DataSource datasource = DataSourceMaster.get(propertiesSourceFile);
	    ConnectionPool connectionPool = ConnectionPool.create(datasource);
	    assertTrue(connectionPool.getConnection().isValid(1));
	}
	
}
