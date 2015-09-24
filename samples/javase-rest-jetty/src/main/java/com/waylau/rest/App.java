package com.waylau.rest;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;

/**
 * Main class.
 *
 */
public class App {
	// HTTP server 所要监听的 uri
	public static final String BASE_URI = "http://192.168.11.125:8081/";
    private static Logger logger = Logger.getLogger(App.class.toString());
	/**
	 * Main method.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
        WebAppContext context = new WebAppContext();
        context.setDescriptor("./webapp/WEB-INF/web.xml");
        context.setParentLoaderPriority(true);
        context.setResourceBase("./webapp");
        context.setContextPath("/");
        
		// 使用 Jetty Http Server
		Server server = JettyHttpContainerFactory.createServer(URI.create(BASE_URI),
				new RestApplication());
        server.setHandler(context);
        logger.info("server start");
	}
}
