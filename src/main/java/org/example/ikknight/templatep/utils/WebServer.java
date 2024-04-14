package org.example.ikknight.templatep.utils;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WebServer {
    static Server server;
    static String message = "PRE INIT";
    public static void setMessage(String newMessage) throws InterruptedException {
        message = newMessage;
        if (isServerOn()) {
            System.out.println("Changing handler for message: " + newMessage);
            server.setHandler(new MessageHandler(newMessage));
            
        }
    }

    public static boolean isServerOn(){
        return server!=null&&server.isStarted();
    }
    public static void runServer() throws Exception {
        server = new Server(8080); // Create Jetty server on port 8080
        server.setHandler(new MessageHandler(message)); // Set handler for incoming requests with the provided message
        server.start(); // Start the server
        System.out.println("Server started on port 8080");
        server.join(); // Wait for the server to finish
        setMessage("INIT");
    }
    public static void stopServer() throws Exception {
        if (isServerOn()) {
            server.stop(); // Stop the server
            System.out.println("Server stopped");
        }
    }
    public static class MessageHandler extends AbstractHandler {
        private String message;

        public MessageHandler(String message) {
            this.message = message;
        }

        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            if ("/message".equals(target)) {
                // Serve the HTML file
                File htmlFile = new File("server.html"); // Use relative path to the HTML file
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                baseRequest.setHandled(true);
                Files.copy(htmlFile.toPath(), response.getOutputStream());
            } else {
                // Respond with the message
                response.setContentType("text/plain;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                baseRequest.setHandled(true);
                response.getWriter().println(message);
            }
        }
    }

}
