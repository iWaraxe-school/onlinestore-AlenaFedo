package by.issoft.store;

import by.IsSoft.XMLHandling.ProductComparator;
import by.IsSoft.XMLHandling.ProductPriceComparator;
import by.issoft.domain.Category;
import by.issoft.domain.CategoryFactory;
import by.issoft.domain.Product;

import java.util.*;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public final class Store {

    private static Store instance;
    public String storeName;

    private Store(String name) {
        this.storeName = name;
    }

    public static Store getInstance(String name) {
        if (instance == null) {
            instance = new Store(name);
        }
        return instance;
    }

    private final List<Category> categoryList = new ArrayList<>();


    public void printAll() {
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

    public List<Product> getAllProducts() {

        List<Product> listProducts = new ArrayList<>();

        for (var category : categoryList) {
            listProducts.addAll(category.getProductList());
        }

        return listProducts;
    }

    public void printSorted() {

        List<Product> allProducts = getAllProducts();
        allProducts.sort(new ProductComparator());

        for (var product : allProducts) {
            System.out.println("\t" + product.getName() + "\t" + product.getPrice() + "\t" + product.getRate());

        }

    }


    public Product getRandomProduct()  {
        List<Product> allProducts = getAllProducts();

        var random = new Random();
        return allProducts.get(random.nextInt(allProducts.size()));
    }

    public void printTheBest() {
        List<Product> allProducts = getAllProducts();
        allProducts.sort(new ProductPriceComparator());

        int i = 1;
        for (var product : allProducts) {
            System.out.println("\t" + product.getName() + "\t" + product.getPrice() + "\t" + product.getRate());
            if (i++ > 4) break;
        }
    }

    public Map<Category, Integer> createProductList() {
        Map<Category, Integer> product = new HashMap<>();

        Reflections reflections = new Reflections("by.issoft.domain.Categories", new SubTypesScanner());
        CategoryFactory categoryFactory = new CategoryFactory();

        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> type : subTypes) {
            Random random = new Random();
            String simpleName = type.getSimpleName();
            Category category = categoryFactory.createCategory(simpleName);
            product.put(category, random.nextInt(1, 10));
        }
        return product;
    }
}
