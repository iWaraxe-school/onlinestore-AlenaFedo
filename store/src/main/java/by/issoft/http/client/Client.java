package by.issoft.http.client;

import by.issoft.domain.Product;
import by.issoft.store.Store;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class Client {
    public void ClientOrder(){

        Store store = Store.getInstance("Store1");
        Product product = store.getRandomProduct();

        Gson g = new Gson();
        String productInGson = g.toJson(product);

        RestAssured.baseURI = "http://localhost:8088";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application-/json");
        request.auth().basic("admin", "admin");
        request.body(productInGson);

        Response response = request.post("/order");
        System.out.println("Status: " + response.statusLine());
        System.out.println("body: " + response.getBody().asString());
    }
}
