package by.issoft.store;


import by.issoft.domain.Product;
import com.github.javafaker.Faker;


public class RandomStorePopulator {

    public RandomStorePopulator() {
    }

    Faker faker = new Faker();

    public Product getProduct(String categoryName) {
        var product = new Product(
                makeProduct(categoryName));
        product.setPrice(getPrice());
        product.setRate(getRate());
        return product;
    }

    private String makeProduct(String categoryName) {
        return switch (categoryName) {
            case "Bike" -> faker.princessBride().character();
            case "Milk" -> faker.cat().name();
            case "Phone" -> faker.animal().name();
            default -> null;
        };
    }

    private double getPrice() {
        return faker.number().randomDouble(2, 0, 100);
    }

    private double getRate() {
        return faker.number().randomDouble(1, 1, 4);
    }
}