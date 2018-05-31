package com.codecool.annotation;

public class Testhandler {

    @Webroute(path = "/test")
    public String onTest(){
        return "This is the test site";
    }

    @Webroute
    public String onDefault(){
        return "This is the default site";
    }
}
