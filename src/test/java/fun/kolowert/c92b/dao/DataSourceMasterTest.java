package fun.kolowert.c92b.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

public class DataSourceMasterTest {

	private static final String propertiesSourceFile = "resources/mysqldb_test.properties";
	
	@Test
	public void shouldMadeDataSourceFromPropertiesFile() throws SQLException {
		DataSource datasource = DataSourceMaster.get(propertiesSourceFile);
		String expected = "com.mysql.cj.jdbc.MysqlDataSource@934b6cb";
		assertEquals(expected, datasource.toString());
	}
	
}
