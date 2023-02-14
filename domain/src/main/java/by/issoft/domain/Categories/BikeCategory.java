package by.issoft.domain.Categories;

import by.issoft.domain.Category;
import by.issoft.domain.CategoryExample;

public class BikeCategory extends Category implements CategoryExample {

    public BikeCategory() {
        super("Bike");
    }

    public void printName() {
        System.out.println("You have created BikeCategory.");
    }
}
