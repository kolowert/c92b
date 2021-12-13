package fun.kolowert.c92b.utility;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFinder {
	
	private static final Logger logger = LogManager.getLogger("PathFinder");
	
	public String getAbsolutePath(String fileName) {
		File file = null;
		try {
			URL res = getClass().getClassLoader().getResource(fileName);
			file = Paths.get(res.toURI()).toFile();
			return file.getAbsolutePath();
		} catch (URISyntaxException e) {
			logger.error("exception" + e);
		}
		return fileName;
	}
	
}
