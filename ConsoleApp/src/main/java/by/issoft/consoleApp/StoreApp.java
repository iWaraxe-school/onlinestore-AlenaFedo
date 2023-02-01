package by.issoft.consoleApp;


import by.issoft.store.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StoreApp {

    public static void main(String[] args) {

        Store store = new Store();

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
            if (str.equals("sort")) {
                store.printSorted();
            } else {
                if (str.equals("top")) {
                    store.printTheBest();
                }
            }
        }
        while (!str.equals("quit"));
    }
}




