package by.issoft.domain;

public class Product implements  BuilderProduct{

    private final String name;
    private double price;
    private double rate;


    public String getName() {
        return name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }


    public double getPrice() {
        return price;
    }

    public double getRate() {
        return rate;
    }

    public Product(String name) {
        this.name = name;
    }
}
