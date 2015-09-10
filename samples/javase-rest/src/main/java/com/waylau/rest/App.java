package com.waylau.rest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.io.IOException;
import java.net.URI;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.ext.RuntimeDelegate;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainer;
import org.glassfish.jersey.media.sse.SseFeature;
import org.glassfish.jersey.message.internal.ReaderWriter;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.http.util.HttpStatus;
/**
 * Main class.
 *
 */
public class App {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://192.168.11.125:8081/webapi/";

    public static final String APP_PATH = "/aggregator/";
    public static final String API_PATH = "/aggregator-api/";
    public static final String WEB_ROOT = "/webroot";
    
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), new RestApplication());
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        final ServerConfiguration config = server.getServerConfiguration();
        // add handler for serving static content
        config.addHttpHandler(new StaticContentHandler(""),
                APP_PATH);

    }
    
    /**
     * Simple HttpHandler for serving static content included in web root
     * directory of this application.
     */
    private static class StaticContentHandler extends HttpHandler {
        private static final HashMap<String, String> EXTENSION_TO_MEDIA_TYPE;

        static {
            EXTENSION_TO_MEDIA_TYPE = new HashMap<String, String>();

            EXTENSION_TO_MEDIA_TYPE.put("html", "text/html");
            EXTENSION_TO_MEDIA_TYPE.put("js", "application/javascript");
            EXTENSION_TO_MEDIA_TYPE.put("css", "text/css");
            EXTENSION_TO_MEDIA_TYPE.put("png", "image/png");
            EXTENSION_TO_MEDIA_TYPE.put("ico", "image/png");
        }

        private final String webRootPath;

        StaticContentHandler(String webRootPath) {
            this.webRootPath = webRootPath;
        }

        @Override
        public void service(Request request, Response response) throws Exception {
            String uri = request.getRequestURI();

            int pos = uri.lastIndexOf('.');
            String extension = uri.substring(pos + 1);
            String mediaType = EXTENSION_TO_MEDIA_TYPE.get(extension);

            if (uri.contains("..") || mediaType == null) {
                response.sendError(HttpStatus.NOT_FOUND_404.getStatusCode());
                return;
            }

            final String resourcesContextPath = request.getContextPath();
            if (resourcesContextPath != null && !resourcesContextPath.isEmpty()) {
                if (!uri.startsWith(resourcesContextPath)) {
                    response.sendError(HttpStatus.NOT_FOUND_404.getStatusCode());
                    return;
                }

                uri = uri.substring(resourcesContextPath.length());
            }

            InputStream fileStream;

            try {
                fileStream = webRootPath == null ?
                        App.class.getResourceAsStream(WEB_ROOT + uri) :
                        new FileInputStream(webRootPath + uri);
            } catch (IOException e) {
                fileStream = null;
            }

            if (fileStream == null) {
                response.sendError(HttpStatus.NOT_FOUND_404.getStatusCode());
            } else {
                response.setStatus(HttpStatus.OK_200);
                response.setContentType(mediaType);
                ReaderWriter.writeTo(fileStream, response.getOutputStream());
            }
        }
    }
}

