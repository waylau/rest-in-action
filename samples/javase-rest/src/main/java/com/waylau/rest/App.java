package com.waylau.rest;

//import org.glassfish.grizzly.http.server.HttpServer;
//import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
//import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
//import org.glassfish.jersey.simple.SimpleContainerFactory;

import java.io.IOException;
import java.net.URI;
 
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
 
/**
 * Main class.
 *
 */
public class App {
    // HTTP server 所要监听的 uri
    public static final String BASE_URI = "http://192.168.11.125:8081/";
    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
    	
    	
    	// JdkHttpServerFactory.createHttpServer(URI.create(BASE_URI), new RestApplication());
    	
    	// GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), new RestApplication());
        
        // SimpleContainerFactory.create(URI.create(BASE_URI), new RestApplication());
        
         JettyHttpContainerFactory.createServer(URI.create(BASE_URI), new RestApplication());
 
 
    }
 
}

