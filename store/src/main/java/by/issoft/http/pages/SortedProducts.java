package by.issoft.http.pages;

import by.issoft.http.server.ResponseHandler;
import by.issoft.store.Store;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class SortedProducts implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {
        Store store = Store.getInstance("Store1");
        String sortedProducts = store.storeSortedToString();
        ResponseHandler.handleResponse(exchange, "Sorted list: \n" + sortedProducts);
        System.out.println("http: Sorted list: \n" + sortedProducts);
    }
}
