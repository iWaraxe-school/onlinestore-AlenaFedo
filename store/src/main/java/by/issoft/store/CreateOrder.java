package by.issoft.store;

import by.issoft.domain.Product;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CreateOrder extends Thread {

    private final Store store = Store.getInstance("store");

    private final PurchasedGoods purchasedGoods = PurchasedGoods.getInstance();

    public void run() {
        System.out.println("Start Thread " + Thread.currentThread().getName());
        Product product = store.getRandomProduct();

        System.out.println("Ordered product: " + product.getName());

        purchasedGoods.addPurchasedGood(product);
        purchasedGoods.printPurchasedGoods();

        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(0, 30));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Finish thread " + Thread.currentThread().getName());
    }
}
