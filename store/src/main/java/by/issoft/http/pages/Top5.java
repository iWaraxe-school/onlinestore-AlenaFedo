package by.issoft.http.pages;

import by.issoft.http.server.ResponseHandler;
import by.issoft.store.Store;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class Top5  implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {
        Store store = Store.getInstance("Store1");
        String topProducts = store.storeTopToString();
        ResponseHandler.handleResponse(exchange, "Top5 list: \n" + topProducts);
        System.out.println("http: Top5 list: \n" + topProducts);
    }
}
