package by.issoft.store;
import java.util.concurrent.TimeUnit;

public class CleanAllOrders extends Thread {
    private final PurchasedGoods purchasedGoods = PurchasedGoods.getInstance();

    {
        setDaemon(true);
    }
    public void run() {
        while (true) {
            try {
                TimeUnit.MINUTES.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Start Thread " + Thread.currentThread().getName());
            purchasedGoods.cleanPurchasedGoods();
            purchasedGoods.printPurchasedGoods();
            System.out.println("Finish thread " + Thread.currentThread().getName());
        }
    }
}
