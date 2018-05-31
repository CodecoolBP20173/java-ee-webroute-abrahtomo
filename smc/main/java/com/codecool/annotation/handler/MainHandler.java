package com.codecool.annotation.handler;

import com.codecool.annotation.Webroute;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.reflections.Reflections;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class MainHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange t) throws IOException{
        Reflections reflection = new Reflections("com.codecool.annotation.routes");
        Set<Class<?>> classes = reflection.getTypesAnnotatedWith(Webroute.class);
        for (Class<?> clas : classes) {
            Annotation annotation = clas.getAnnotation(Webroute.class);
            if (annotation instanceof Webroute && ((Webroute) (annotation)).path().equals(t.getRequestURI().getPath())){
                Method[] methods = clas.getMethods();
                for (Method method : methods) {
                    try {
                        method.invoke(clas.newInstance(), t);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        String response = "This is the response";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
