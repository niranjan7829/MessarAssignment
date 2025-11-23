package com.hrblizz.base;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.annotations.BeforeClass;

import com.hrblizz.utilities.CustomLogFilter;



public class ApiBase  {
	  protected static Properties properties;
	  
	  
	  
	
	@BeforeClass
    public void setup() throws IOException {
		
		//.filters(Filter...) version explicitly:
		RestAssured.filters((io.restassured.filter.Filter) new CustomLogFilter());
		
		
		properties = new Properties();
		 FileInputStream fis = new FileInputStream("src/test/resources/apiConfig.properties");
         properties = new Properties();
         properties.load(fis);
        RestAssured.baseURI = properties.getProperty("baseUrl");
      //  String Auth = properties.getProperty("authorization");
        System.out.println( RestAssured.baseURI );
    }
	
	//it is to return value and fetch in another class
	 public static String getProperty(String key) {
	        return properties.getProperty(key);
	    }
	 
	 
	 
	 public static Response post(String endpoint, String body, Map<String, String> headers) {
			return given()
	                .headers(headers)
	                .body(body)
	                .when()
	                .post(endpoint)
	                .then()
	                .extract()
	                .response();
		}
	
}
