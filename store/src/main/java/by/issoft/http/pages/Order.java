package by.issoft.http.pages;

import by.issoft.http.server.ResponseHandler;
import by.issoft.store.PurchasedGoods;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;


public class Order implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {

        PurchasedGoods purchasedGoods = PurchasedGoods.getInstance();

        String product = purchasedGoods.getLastEnteredProduct();
        ResponseHandler.handleResponse(exchange, "Last Ordered Product: \n" + product);
        System.out.println("http: Last Ordered Product: \n" + product);
    }
}
