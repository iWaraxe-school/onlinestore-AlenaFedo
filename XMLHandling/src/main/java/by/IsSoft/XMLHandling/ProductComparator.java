package by.IsSoft.XMLHandling;


import by.issoft.domain.Product;

import java.util.Comparator;

public class ProductComparator implements Comparator <Product> {

    @Override
    public int compare(Product o1, Product o2) {
        var sortOrder = XMLParser.GetSortingOrder();

        for (var item: sortOrder.entrySet()) {

            if(item.getKey().equals("name")) {

                int r = item.getValue() == Enum.SortingType.asc ? o1.getName().compareToIgnoreCase(o2.getName()) :
                        o2.getName().compareToIgnoreCase(o1.getName());

                if (r != 0) {
                    return r;
                }
            }

            if(item.getKey().equals("price")) {

                int r = (int) ((item.getValue() == Enum.SortingType.asc) ? Double.compare(o1.getPrice(),o2.getPrice()) :
                                       Double.compare(o2.getPrice(),o1.getPrice()));

                if (r != 0) {
                    return r;
                }
            }

            if(item.getKey().equals("rate")) {

                int r = (int) (item.getValue() == Enum.SortingType.asc ? Double.compare(o1.getRate(), o2.getRate()) :
                                       Double.compare(o2.getRate(), o1.getRate()));

                if (r != 0) {
                    return r;
                }
            }

        }


        return 0;
    }


}



