package fun.kolowert.c92b.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.jdbc.MysqlDataSource;

import fun.kolowert.c92b.utility.PathFinder;

public class DataSourceMaster {

	private static final Logger logger = LogManager.getLogger("DataSourceMaster");
	
	private static final String PROPERTIES_FILE_NAME = "mysqldb.properties";

	public static DataSource get() {
		PathFinder pathFinder = new PathFinder();
		String absolutePath = pathFinder.getAbsolutePath(PROPERTIES_FILE_NAME);
		return get(absolutePath);
	}

	public static DataSource get(String absolutePropertiesFilePath) {
		Properties properties = new Properties();
		FileInputStream fis = null;
		MysqlDataSource mysqlDataSourse = null;
		try {
			fis = new FileInputStream(absolutePropertiesFilePath);
			properties.load(fis);
			mysqlDataSourse = new MysqlDataSource();
			mysqlDataSourse.setURL(properties.getProperty("URL"));
			mysqlDataSourse.setUser(properties.getProperty("USERNAME"));
			mysqlDataSourse.setPassword(properties.getProperty("PASSWORD"));
		} catch (IOException e) {
			logger.fatal("~ DataSource not created ~" + e);
		}
		System.out.println("DataSourceMaster >> DataSource created successfully " + mysqlDataSourse.toString());
		return mysqlDataSourse;
	}
}
