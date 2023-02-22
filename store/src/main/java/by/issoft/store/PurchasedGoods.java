package by.issoft.store;

import by.issoft.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class PurchasedGoods {
    private static PurchasedGoods instance;

    private PurchasedGoods() {

    }

    public static PurchasedGoods getInstance() {
        if (instance == null) {
            instance = new PurchasedGoods();
        }
        return instance;
    }

    private final List<Product> listPurchasedGoods = new ArrayList<>();

    public void addPurchasedGood(Product product) {
        listPurchasedGoods.add(product);
    }

    public void printPurchasedGoods() {
        System.out.println("PurchasedGoods:");
        for (var product : listPurchasedGoods) {
            System.out.println("\t" + product.getName() + "\t" + product.getPrice() + "\t" + product.getRate());

        }
    }

    public void cleanPurchasedGoods() {
        listPurchasedGoods.clear();
        System.out.println("Orders were deleted");
    }
}
