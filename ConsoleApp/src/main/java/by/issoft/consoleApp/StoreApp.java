package by.issoft.consoleApp;


import by.issoft.store.CleanAllOrders;
import by.issoft.store.CreateOrder;
import by.issoft.store.DataBaseHelper;
import by.issoft.store.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public final class StoreApp {

    public static void main(String[] args) throws SQLException {

        Store store = Store.getInstance("Store1");

        store.fillStore();

        DataBaseHelper dbHelper = new DataBaseHelper(store);

        //store.printAll();
        new CleanAllOrders().start();


        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
        String str;
        System.out.println("Type sort/top/order to work with Product's list.");
        System.out.println("Type 'quit' to finish.");
        do {
            try {
                str = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            switch (str) {
                case "sort":
                    store.printSorted();
                    break;
                case "top":
                    store.printTheBest();
                    break;
                case "order":
                    new CreateOrder().start();
                    break;
            }

        }
        while (!str.equals("quit"));
    }
}




