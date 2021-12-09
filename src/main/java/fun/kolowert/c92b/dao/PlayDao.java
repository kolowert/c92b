package fun.kolowert.c92b.dao;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fun.kolowert.c92b.utility.PathFinder;
import fun.kolowert.c92b.utility.Utils;

public class PlayDao {
	
	private static final String propertiesFileName = "mysqldb.properties";
	private static final String propertiesSourceFile = "resources/" + propertiesFileName;

	public static void main(String[] args) throws SQLException, URISyntaxException {
		
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
		
		PlayDao playDao = new PlayDao();
		playDao.pathPlay();
		System.out.println("~ ~ ~ ~ ~ ~ ~ ~");
		PathFinder pathFinder = new PathFinder();
		String absolutePath = pathFinder.getAbsolutePath(propertiesFileName);
		System.out.println("absolutePath: @ @ @ " + absolutePath);
		
	}
	
	private void pathPlay() throws URISyntaxException {
		URL res = getClass().getClassLoader().getResource("mysqldb.properties");
		File file2 = Paths.get(res.toURI()).toFile();
		String absolutePath = file2.getAbsolutePath();
		System.out.println("absolutePath: # @ # " + absolutePath);
	}
	
	private static void playOperator(String sqlInstruction) throws SQLException {
		Connection con = Connector.getInstance().getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		statement = con.prepareStatement(sqlInstruction);
		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			System.out.println(
					resultSet.getInt("id") + "^" + resultSet.getString("login") + "^" + resultSet.getString("role"));
		}
	}

	private static void playStore(String sqlInstruction) throws SQLException {
		Connection con = Connector.getInstance().getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		statement = con.prepareStatement(sqlInstruction);
		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.getInt("item_id") + "^" + resultSet.getString("name") + "^"
					+ resultSet.getString("measure_unit") + "^" + resultSet.getString("quantity") + "^"
					+ resultSet.getString("price"));
		}
	}

	private static void playReceipt(String sqlInstruction) throws SQLException {
		Connection con = Connector.getInstance().getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		statement = con.prepareStatement(sqlInstruction);
		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			long openTime = resultSet.getLong("opentime");
			long closeTime = resultSet.getLong("closetime");
			System.out.println(resultSet.getInt("id") + " ^ " + openTime + " ^ " + closeTime + " ^ "
					+ resultSet.getInt("operator_id") + " ^ " + resultSet.getDouble("sum") + " ^^ "
					+ Utils.unixTimeToTimeStamp(openTime) + " ^^ " + Utils.unixTimeToTimeStamp(closeTime));
		}
	}
}
