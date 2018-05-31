package com.codecool.annotation;


import java.net.InetSocketAddress;
import com.codecool.annotation.handler.MainHandler;
import com.sun.net.httpserver.HttpServer;


public class Setup {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new MainHandler());
        server.start();

    }
}