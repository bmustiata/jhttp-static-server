package com.ciplogic.web.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.resource.Resource;

import java.io.IOException;

public class HttpStaticServer {
    private Server server;

    public HttpStaticServer(String host, int port, String folder) {
        try {
            createServer(host, port,  folder);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    private void createServer(String host, int port, String folder) throws IOException {
        Server server = new Server();

        server.addConnector(createConnector(host, port));
        server.setHandler(createResourceHandler(folder));

        this.server = server;
    }

    private SelectChannelConnector createConnector(String host, int port) {
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setHost(host);
        connector.setPort(port);
        return connector;
    }

    private ResourceHandler createResourceHandler(String folder) throws IOException {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setBaseResource(Resource.newResource(folder));
        resourceHandler.setDirectoriesListed(true);

        DefaultHandler _404Handler = new DefaultHandler();
        _404Handler.setShowContexts(false);
        _404Handler.setServeIcon(false);

        HandlerList handlerList = new HandlerList();
        handlerList.addHandler(resourceHandler);
        handlerList.addHandler(_404Handler);
        return resourceHandler;
    }

    public void start() {
        try {
            server.start();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }
}
