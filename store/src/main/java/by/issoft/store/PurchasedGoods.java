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

    public synchronized void  addPurchasedGood(Product product) {
        listPurchasedGoods.add(product);
    }

    public void printPurchasedGoods() {
        System.out.println("PurchasedGoods:");
        for (var product : listPurchasedGoods) {
            System.out.println("\t" + product.getName() + "\t" + product.getPrice() + "\t" + product.getRate());

        }
    }

    public String getLastEnteredProduct() {
        if (listPurchasedGoods.size() == 0)
            return null;
        Product product = listPurchasedGoods.get(listPurchasedGoods.size() - 1);

        StringBuilder sb = new StringBuilder();

        sb.append("\t").append(product.getName()).append("\t").append(product.getPrice()).append("\t").append(product.getRate()).append("\n");
        return sb.toString();
    }


    public synchronized void cleanPurchasedGoods() {
        listPurchasedGoods.clear();
        System.out.println("Orders were deleted");
    }
}
