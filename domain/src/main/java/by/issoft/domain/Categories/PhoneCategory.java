package by.issoft.domain.Categories;

import by.issoft.domain.Category;
import by.issoft.domain.CategoryExample;

public class PhoneCategory extends Category  implements CategoryExample {
    public PhoneCategory() {
        super("Phone");
    }

    ;

    public void printName() {
        System.out.println("You have created BikeCategory.");
    }
}
