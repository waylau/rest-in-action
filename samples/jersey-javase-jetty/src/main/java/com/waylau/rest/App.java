package com.waylau.rest;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * 说明：主程序启动类
 *
 * @author <a href="http://www.waylau.com">waylau.com</a> 2015年9月7日 
 */
public class App {

	/**
	 * 
	 */
	public App() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
		Server server = JettyHttpContainerFactory.createServer(baseUri, new RestApplication());
	}

}
