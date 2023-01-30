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

    public void addProductToCategory(Product product) {
        this.productList.add(product);
    }

    public void printCategory() {

        System.out.println("Category " + name + ":");

        for (Product product : productList) {
            System.out.println("\t" + product.getName() + "\t" + product.getPrice() + "\t" + product.getRate());
        }
    }

    public void printSorted() {
        System.out.println("Sorted Category " + name + ":");
/*  still not implemented
        Set<Product> prSet = new TreeSet<>(
                new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                       return o1.compareTo(o2);
                    }
                }
        );
*/

        }

    public void printTheBest() {
        System.out.println("5 Top products in Category " + name + ":");

        //still not implemented
    }
}


