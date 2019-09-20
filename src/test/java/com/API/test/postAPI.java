package com.API.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.API.Base.BaseClass;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Client.RestClient;
import com.qa.data.Users;

public class postAPI extends BaseClass{

	BaseClass baseclass;
	String ServiceURL;
	String apiURL;
	String url;
	RestClient restclient;
	CloseableHttpResponse Response;
	
	
	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException {

		baseclass = new BaseClass();
		ServiceURL = prop.getProperty("URL");
		apiURL = prop.getProperty("Service");
		url = ServiceURL + apiURL;

	}

	@Test
	public void postAPI() throws JsonGenerationException, JsonMappingException, IOException {
		
		RestClient restClient =new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		//jackson APi to convert java object to json
		//objectMapper is class
		ObjectMapper mapper = new ObjectMapper();
		Users user = new Users("Tajamul" ,"Leader"); // creating object of users class . Expected value
		
		//Object to json File conversion is called marshling
		mapper.writeValue(new File("C:\\MyPersonalWorkSpace\\com.Api.AutiomationAndParsing\\src\\main\\java\\com\\qa\\data\\Data.json"), user);
		
		//Object to json in string.
		 String userjsonString= mapper.writeValueAsString(user);
		 System.out.println(userjsonString);
		 
		 Response = restClient.Post(url, userjsonString, headerMap);
		 
		 //Status Code.
		 int statusCode =Response.getStatusLine().getStatusCode();
		 Assert.assertEquals(statusCode, baseclass.Response_Status_Code_201 );
		 
		 //jsonString 
		 String responseString =EntityUtils.toString(Response.getEntity(),"UTF-8");
		 
			JSONObject responseJson = new JSONObject(responseString);
		   
		 System.out.println("The response from Json"  + responseJson);
		 
		
		//json to java object is called unmarshalling
		 
		Users usersResponObjec =mapper.readValue(responseString, Users.class); // Actual value created
		System.out.println(usersResponObjec);
		
		//Assert.assertTrue(user.getName().equals(usersResponObjec));
		//Assert.assertTrue(user.getName().equals(usersResponObjec));
	}
	
	
}
