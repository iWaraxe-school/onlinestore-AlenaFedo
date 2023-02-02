package by.issoft.store;

import by.IsSoft.XMLHandling.ProductComparator;
import by.IsSoft.XMLHandling.ProductPriceComparator;
import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class Store {
    public Store() {
        categoryList = new ArrayList<>();
    }

    private final List<Category> categoryList;
    public void printAll(){
        for (var category : categoryList) {
            category.printCategory();
        }
    }

    public void fillStore() {
        RandomStorePopulator populator = new RandomStorePopulator();
        Map<Category, Integer> categoryProducts = createProductList();

        for (Map.Entry<Category, Integer> entry : categoryProducts.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                Product product =
                        populator.getProguct(entry.getKey().getName());
                entry.getKey().addProductToCategory(product);
            }

            categoryList.add(entry.getKey());
        }
    }

    public   List<Product> getAllProducts(){

        List<Product> listProducts= new ArrayList<>();

        for (var category : categoryList){
            listProducts.addAll(category.getProductList());
        }

        return listProducts;
    }

    public void printSorted()    {

        List<Product> allProducts = getAllProducts();
        allProducts.sort(new ProductComparator());

        for (var product:allProducts) {
            System.out.println("\t" + product.getName() + "\t" + product.getPrice() + "\t" + product.getRate());

        }

    }


    public void printTheBest() {
        List<Product> allProducts = getAllProducts();
        allProducts.sort(new ProductPriceComparator());

        int i=1;
        for (var product : allProducts) {
            System.out.println("\t" + product.getName() + "\t" + product.getPrice() + "\t" + product.getRate());
            if(i++>4) break;
        }
    }

    private Map<Category, Integer> createProductList() {
        Map<Category, Integer> product = new HashMap<>();

        Reflections reflections = new Reflections("by.issoft.domain.Categories", new SubTypesScanner());

        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> type : subTypes) {
            try {
                Random random = new Random();
                product.put(type.getConstructor().newInstance(), random.nextInt(1, 10));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return product;
    }
}
