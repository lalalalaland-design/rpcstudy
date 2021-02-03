package com.luban.framework.protocol;

import com.alibaba.fastjson.JSONObject;
import com.luban.framework.Invocation;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class HttpClient {
    public String send(String hostname, Integer port, Invocation invocation) {

        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI("http", null, hostname, port, "/", null, ""))
                    .POST(HttpRequest.BodyPublishers.ofString(JSONObject.toJSONString(invocation)))
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        var client = java.net.http.HttpClient.newHttpClient();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = response.body();
        return result;


    }
}
