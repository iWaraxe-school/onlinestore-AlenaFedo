package by.issoft.http.server;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseHandler {

    public static void handleResponse(HttpExchange httpExchange, String data) throws IOException {
        httpExchange.sendResponseHeaders(200, data.length());
        OutputStream out = httpExchange.getResponseBody();
        out.write(data.getBytes());
        out.close();
    }
}
