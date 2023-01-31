package by.IsSoft.XMLHandling;

import by.issoft.domain.Product;

import java.util.Comparator;

public class ProductRateComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return Double.compare(o1.getRate(), o2.getRate());
    }
}
