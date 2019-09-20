package com.qa.Client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// A. Get Method without Header parametes
	// HttpClients class
	public CloseableHttpResponse Get(String URL) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault(); // CreateDefault method is creating conneciton
		HttpGet httpget = new HttpGet(URL); // get http request
		CloseableHttpResponse Response = httpClient.execute(httpget);
		return Response;
	}

	// Get Method with headers
	//Method overloading is achieved here, same method name different parameters

	public CloseableHttpResponse Get(String URL, HashMap<String, String> headerMap)throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault(); // CreateDefault method is creating conneciton
		HttpGet httpget = new HttpGet(URL); // get http request

		for (Entry<String, String> entry : headerMap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse Response = httpClient.execute(httpget);
		return Response;
	}
	
	
	//3. Post Method with header
	
	public CloseableHttpResponse Post(String URL,String entityString ,HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // create client
		HttpPost httpPost =new HttpPost(URL);  // htttp post request.
		httpPost.setEntity(new StringEntity(entityString));  // Passing Payload json
		
		//for header
		for (Entry<String, String> entry : headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse Response =httpClient.execute(httpPost);
		return Response;
	}

}
