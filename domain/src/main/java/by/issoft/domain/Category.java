package by.issoft.domain;


import java.util.*;

public abstract class Category {
    private final String name;
    private final List<Product> productList;

    public Category(String name) {
        this.name = name;
        this.productList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addProductToCategory(Product product) {
        this.productList.add(product);
    }

    public void printCategory() {

        System.out.println("Category " + name + ":");

        for (Product product : productList) {
            System.out.println("\t" + product.getName() + "\t" + product.getPrice() + "\t" + product.getRate());
        }
    }


}


