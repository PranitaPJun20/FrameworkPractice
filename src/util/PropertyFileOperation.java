package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileOperation {
	private Properties prop;
	//constructor banake fie ka nam puchhenge
	public PropertyFileOperation(String filePath) throws IOException {
		File file = new File(filePath);
		FileInputStream inStream = new FileInputStream(file);
		prop=new Properties();
		prop.load(inStream);
	}
	//to get value from property file
	public String getValue(String key) {
		String value = prop.getProperty(key);
		if(value==null)
			throw new NullPointerException("No such property available in property file");
		return value;
	}

}
