package by.issoft.consoleApp;


import by.issoft.store.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class StoreApp {

    public static void main(String[] args) {

        Store store = Store.getInstance("Store1");

        store.fillStore();

        //store.printAll();

        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
        String str;
        System.out.println("Type sort/top to work with Product's list.");
        System.out.println("Type 'quit' to finish.");
        do {
            try {
                str = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            switch (str)
            {
                case "sort":
                    store.printSorted();
                    break;
                case "top":
                    store.printTheBest();
                    break;
            }

        }
        while (!str.equals("quit"));
    }
}




