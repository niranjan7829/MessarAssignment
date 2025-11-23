package com.hrblizz.testcase;



import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.hrblizz.base.ApiBase;



import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;


public class TC_01_Api_Login_Case extends ApiBase  {
	
	
	
	 @Test
	    public void loginTest() {
		 
		 String body = "{\r\n" +
			        "\"loginUsername\":\"" + ApiBase.getProperty("loginUsername") + "\",\r\n" +
			        "\"loginPassword\":\"" + ApiBase.getProperty("loginPassword") + "\",\r\n" +
			        "\"unblockToken\":" + ApiBase.getProperty("unblockToken") + "\r\n" +
			        "}";

	        Map<String, String> headers = new HashMap<>();
	        headers.put("Accept", "application/json, text/plain, */*");
	        headers.put("Accept-Language", "en-US,en-IN;q=0.9,en;q=0.8");
	        headers.put("Authorization", ApiBase.getProperty("authorization"));
	        headers.put("Content-Type", "application/json");
	        headers.put(" Content-Length", "3");
	        headers.put("Origin", "https://access.hrblizz.dev");

	        Response response = ApiBase.post("", body, headers);
	       
	      
	       
	    }
}
