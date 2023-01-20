package by.issoft.store;


import by.issoft.domain.Product;
import com.github.javafaker.Faker;


public class RandomStorePopulator {

    public RandomStorePopulator() {
    }

    Faker faker = new Faker();

    public Product getProguct(String categoryName) {
        return new Product(
                makeProduct(categoryName),
                getPrice(),
                getRate()
        );
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