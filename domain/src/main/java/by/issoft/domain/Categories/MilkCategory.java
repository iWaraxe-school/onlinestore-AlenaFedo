package by.issoft.domain.Categories;

import by.issoft.domain.Category;
import by.issoft.domain.CategoryExample;

public class MilkCategory extends Category  implements CategoryExample {

    public MilkCategory() {
        super("Milk");
    }

    ;

    public void printName() {
        System.out.println("You have created BikeCategory.");
    }
}
