package com.hrblizz.utilities;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class CustomLogFilter implements Filter {

    public static StringBuilder apiLog = new StringBuilder();

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        CustomLogFilter.apiLog.setLength(0);

        CustomLogFilter.apiLog.append("\n=============== API REQUEST ===============\n");
        CustomLogFilter.apiLog.append("URI: ").append(requestSpec.getURI()).append("\n");
        CustomLogFilter.apiLog.append("Method: ").append(requestSpec.getMethod()).append("\n");
        CustomLogFilter.apiLog.append("Headers: ").append(requestSpec.getHeaders()).append("\n");

        Object bodyObject = requestSpec.getBody();
        String requestBody = (bodyObject instanceof String) ? (String) bodyObject : String.valueOf(bodyObject);
        CustomLogFilter.apiLog.append("Body: ").append(requestBody).append("\n");

        Response response = ctx.next(requestSpec, responseSpec);

        CustomLogFilter.apiLog.append("\n=============== API RESPONSE ===============\n");
        CustomLogFilter.apiLog.append("Status Code: ").append(response.getStatusCode()).append("\n");
        CustomLogFilter.apiLog.append("Headers: ").append(response.getHeaders()).append("\n");
        CustomLogFilter.apiLog.append("Body: ").append(response.getBody().asPrettyString()).append("\n");
        CustomLogFilter.apiLog.append("============================================\n");

        return response;
    
    }}