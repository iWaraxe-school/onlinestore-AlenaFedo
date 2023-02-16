package by.issoft.domain;

import by.issoft.domain.Categories.BikeCategory;
import by.issoft.domain.Categories.MilkCategory;
import by.issoft.domain.Categories.PhoneCategory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CategoryFactory {

    private static final Map<String, Supplier<Category>> Category_Map = new HashMap<String, Supplier<Category>>() {{

        put("BikeCategory", BikeCategory::new);
        put("MilkCategory", MilkCategory::new);
        put("PhoneCategory", PhoneCategory::new);
    }};

    public Category createCategory(String categoryName) {
        return Category_Map.getOrDefault(categoryName, null).get();
    }
}
