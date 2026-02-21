package com.live_naukari.qa.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Data_Utility {
    public String Data_info(String keys) throws IOException {

	FileInputStream fis = new FileInputStream(
		"C:\\Users\\MADHU E\\eclipse-workspace\\test\\LIVE_NAUKARI_MADHU\\src\\main\\java\\com\\live_naukari\\qa\\config\\files_path");

	Properties pobj = new Properties();
	pobj.load(fis);
	return pobj.getProperty(keys);
    }
}
