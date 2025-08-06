import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class RailwayServer {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/api/viewTrains", new ViewTrainsHandler());
        server.createContext("/api/bookTicket", new BookTicketHandler());
        server.createContext("/api/cancelTicket", new CancelTicketHandler());
        server.createContext("/api/viewBookings", new ViewBookingsHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("Server running on http://localhost:8080/");
    }

    static class ViewTrainsHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String response = "[\"Train A\", \"Train B\", \"Train C\"]";
            sendResponse(exchange, response);
        }
    }

    static class BookTicketHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            sendResponse(exchange, "{\"message\": \"Ticket booked successfully\"}");
        }
    }

    static class CancelTicketHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            sendResponse(exchange, "{\"message\": \"Ticket cancelled\"}");
        }
    }

    static class ViewBookingsHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String response = "[\"Booking 1\", \"Booking 2\"]";
            sendResponse(exchange, response);
        }
    }

    private static void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
