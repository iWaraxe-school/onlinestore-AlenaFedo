package by.issoft.http.server;

import by.issoft.http.pages.Order;
import by.issoft.http.pages.SortedProducts;
import by.issoft.http.pages.Top5;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    private static HttpServer server;
    public static final String HTTP_METHOD_GET = "get";
    public static final String TOP5_CONTEXT = "/top";
    public static final String SORTED_CONTEXT = "/sorted";
    public static final String ORDER_CONTEXT = "/order";
    public static void startServer(){

        try {
            server = HttpServer.create();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            server.bind(new InetSocketAddress(8088),0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        HttpContext contextSortedProducts = server.createContext(SORTED_CONTEXT,new SortedProducts());
        HttpContext contextTop5 = server.createContext(TOP5_CONTEXT,new Top5());
        HttpContext contextOrder = server.createContext(ORDER_CONTEXT,new Order());

        contextSortedProducts.setAuthenticator(new Auth(HTTP_METHOD_GET) );
        contextTop5.setAuthenticator(new Auth(HTTP_METHOD_GET) );
        contextOrder.setAuthenticator(new Auth(HTTP_METHOD_GET));

        server.setExecutor(null);
        server.start();
    }

    public static void stopServer(){
        server.stop(0);
    }
}
