package com.API.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.API.Base.BaseClass;
import com.API.util.Test_Util;
import com.qa.Client.RestClient;

public class getAPI extends BaseClass {
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

	@Test(priority = 1)
	public void getApiTest() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		Response = restclient.Get(url);

		// Status Code

		int StatusCode = Response.getStatusLine().getStatusCode();
		System.out.println("Status code is " + StatusCode);
		Assert.assertEquals(StatusCode, Response_Status_Code_200, "code is not correct");

		// b. json string
		String ResponseString = EntityUtils.toString(Response.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(ResponseString);
		System.out.println(responseJson);

		// Parsing of jsonObject
		String PerPage = Test_Util.getValueByJpath(responseJson, "/per_page");
		System.out.println("Value of PerPAge is " + PerPage);
		Assert.assertEquals(Integer.parseInt(PerPage), 6);

		String total = Test_Util.getValueByJpath(responseJson, "/total");
		System.out.println("Value of Total is " + total);
		Assert.assertEquals(Integer.parseInt(total), 12);

		// Parsing of jsonArray

		String id = Test_Util.getValueByJpath(responseJson, "data[0]/id");
		String avatar = Test_Util.getValueByJpath(responseJson, "data[0]/avatar");
		String first_name = Test_Util.getValueByJpath(responseJson, "data[0]/first_name");
		String last_name = Test_Util.getValueByJpath(responseJson, "data[0]/last_name");
		String email = Test_Util.getValueByJpath(responseJson, "data[0]/email");

		System.out.println(id);
		System.out.println(avatar);
		System.out.println(first_name);
		System.out.println(last_name);
		System.out.println(email);

		String id1 = Test_Util.getValueByJpath(responseJson, "data[1]/id");
		String avatar1 = Test_Util.getValueByJpath(responseJson, "data[1]/avatar");
		String first_name1 = Test_Util.getValueByJpath(responseJson, "data[1]/first_name");
		String last_name1 = Test_Util.getValueByJpath(responseJson, "data[1]/last_name");
		String email1 = Test_Util.getValueByJpath(responseJson, "data[1]/email");

		System.out.println(id1);
		System.out.println(avatar1);
		System.out.println(first_name1);
		System.out.println(last_name1);
		System.out.println(email1);

		// c. All headers
		Header[] headerArray = Response.getAllHeaders();

		HashMap<String, String> Headerhashmap = new HashMap<String, String>();
		for (Header header : headerArray) {
			Headerhashmap.put(header.getName(), header.getValue());

		}
		System.out.println("Header details " + Headerhashmap);
	}

	@Test(priority = 2)
	public void getApiTestWithHeader() throws ClientProtocolException, IOException {
		restclient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		Response = restclient.Get(url, headerMap);

		// Status Code

		int StatusCode = Response.getStatusLine().getStatusCode();
		System.out.println("Status code is " + StatusCode);
		Assert.assertEquals(StatusCode, Response_Status_Code_200, "code is not correct");

		// b. json string
		String ResponseString = EntityUtils.toString(Response.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(ResponseString);
		System.out.println(responseJson);

		// Parsing of jsonObject
		String PerPage = Test_Util.getValueByJpath(responseJson, "/per_page");
		System.out.println("Value of PerPAge is " + PerPage);
		Assert.assertEquals(Integer.parseInt(PerPage), 6);

		String total = Test_Util.getValueByJpath(responseJson, "/total");
		System.out.println("Value of Total is " + total);
		Assert.assertEquals(Integer.parseInt(total), 12);

		// Parsing of jsonArray

		String id = Test_Util.getValueByJpath(responseJson, "data[0]/id");
		String avatar = Test_Util.getValueByJpath(responseJson, "data[0]/avatar");
		String first_name = Test_Util.getValueByJpath(responseJson, "data[0]/first_name");
		String last_name = Test_Util.getValueByJpath(responseJson, "data[0]/last_name");
		String email = Test_Util.getValueByJpath(responseJson, "data[0]/email");

		System.out.println(id);
		System.out.println(avatar);
		System.out.println(first_name);
		System.out.println(last_name);
		System.out.println(email);

		String id1 = Test_Util.getValueByJpath(responseJson, "data[1]/id");
		String avatar1 = Test_Util.getValueByJpath(responseJson, "data[1]/avatar");
		String first_name1 = Test_Util.getValueByJpath(responseJson, "data[1]/first_name");
		String last_name1 = Test_Util.getValueByJpath(responseJson, "data[1]/last_name");
		String email1 = Test_Util.getValueByJpath(responseJson, "data[1]/email");

		System.out.println(id1);
		System.out.println(avatar1);
		System.out.println(first_name1);
		System.out.println(last_name1);
		System.out.println(email1);

		// c. All headers
		Header[] headerArray = Response.getAllHeaders();

		HashMap<String, String> Headerhashmap = new HashMap<String, String>();
		for (Header header : headerArray) {
			Headerhashmap.put(header.getName(), header.getValue());

		}
		System.out.println("Header details " + Headerhashmap);
	}

}
