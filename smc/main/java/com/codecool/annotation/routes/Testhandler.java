package com.codecool.annotation.routes;

import com.codecool.annotation.Webroute;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

@Webroute(path = "/test")
public class Testhandler {


    public void onTest(HttpExchange t) throws IOException {
        String response = "This is the test";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
