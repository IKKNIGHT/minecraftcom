package org.example.ikknight.templatep.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WebServer {
    static Server server;
    static String message = "PRE INIT";
    static MessageHandler messageHandler = new MessageHandler(message);

    public static void setMessage(String newMessage) throws InterruptedException {
        message = newMessage;
        if (isServerOn()) {
            //System.out.println("Changing handler for message: " + newMessage);
            if (server.getHandler() instanceof MessageHandler) {
                ((MessageHandler) server.getHandler()).setMessage(newMessage);
            } else {
                server.setHandler(new MessageHandler(newMessage));
            }
        }
    }

    public static boolean isServerOn() {
        return server != null && server.isStarted();
    }

    public static void runServer() throws Exception {

        server = new Server(8080); // Create Jetty server on port 8080
        server.setHandler(messageHandler); // Set handler for incoming requests with the provided message
        server.start(); // Start the server
        System.out.println("Server started on port 8080");
        server.join(); // Wait for the server to finish
        setMessage("INIT");

    }

    public static void stopServer() throws Exception {
        if (isServerOn()) {
            server.stop(); // Stop the server
            System.out.println("Server stopped");
        }else{
            System.out.println("Server was never on");
        }
    }

    public static class MessageHandler extends AbstractHandler {
        private String message;

        public MessageHandler(String message) {
            this.message = message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            if ("/message".equals(target)) {
                // Load HTML file using a File object
                File htmlFile = new File("utils/Server.html"); // Corrected file path
                if (htmlFile.exists()) {
                    System.out.println("HTML FILE EXISTS");
                    try (BufferedReader reader = new BufferedReader(new FileReader(htmlFile))) {
                        // Serve the HTML content
                        StringBuilder content = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        response.setContentType("text/html;charset=utf-8");
                        response.setStatus(HttpServletResponse.SC_OK);
                        baseRequest.setHandled(true);
                        response.getWriter().println(content.toString());
                        System.out.println("SERVING HTML CONTENT");
                    }
                } else {
                    System.out.println("HTML FILE DOES NOT EXIST");
                    // HTML file not found
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    baseRequest.setHandled(true);
                }
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
