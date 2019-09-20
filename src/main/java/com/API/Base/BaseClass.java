package com.API.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
	
	public int Response_Status_Code_200 =200;
	public int Response_Status_Code_500 =500;
	public int Response_Status_Code_401 =401;
	public int Response_Status_Code_201 =201;
	public int Response_Status_Code_501 =501;

public Properties prop;	
	//Constructor to read config.properties file
public BaseClass(){
	
	try {
	prop =new Properties();
	FileInputStream ip = new FileInputStream(System.getProperty("user.dir") +"/src/main/java/com/API/Configs/config.properties");
	prop.load(ip);
	
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
}

}
