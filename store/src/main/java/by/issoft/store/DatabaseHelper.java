package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.sql.*;
import java.util.Map;

public class DatabaseHelper {

    private String url ;
    private String user;
    private String password;

    /**
     * Constructs a new DatabaseHelper with the specified database URL, username, and password.
     * @param url the URL of the database to connect to
     * @param user the username to use when connecting to the database
     * @param password the password to use when connecting to the database
     */
    public DatabaseHelper(String url, String user, String password) throws SQLException {
        this.url = url;
        this.user= user;
        this.password = password;

        Store store = Store.getInstance("Store1");

        clearDatabase();
        createCategoryTable();
        createProductTable();
        fillDatabase(store);
        printDBData();
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Clears the database by dropping the categories and products tables if they exist.
     * @throws SQLException if an error occurs while executing the SQL statements
     */
    private void clearDatabase() throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS PRODUCTS");
            statement.executeUpdate("DROP TABLE IF EXISTS CATEGORIES");
        }
    }

    /**
     * Creates the categories table if it does not already exist.
     * @throws SQLException if an error occurs while executing the SQL statement
     */
    private void createCategoryTable() throws SQLException {
    try (Connection connection = getConnection();
         Statement statement = connection.createStatement()) {

        statement.executeUpdate( "CREATE TABLE IF NOT EXISTS CATEGORIES(" +
                "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, NAME VARCHAR(255) NOT NULL)");
    }
}

    /**
     * Creates the products table if it does not already exist.
     * @throws SQLException if an error occurs while executing the SQL statement
     */
private void createProductTable()throws SQLException {
    try (Connection connection = getConnection();
         Statement statement = connection.createStatement()) {

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS PRODUCTS(" +
                "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                "CATEGORY_ID INT NOT NULL," +
                "NAME VARCHAR(255) NOT NULL, " +
                "RATE DECIMAL(10,1 ) NOT NULL,  " +
                "PRICE DECIMAL(10,1 ) NOT NULL, " +
                "FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORIES(ID))");
    }
}


    /**
     * Fills the database with data from the specified store.
     * @param store the store to get the data from
     * @throws SQLException if an error occurs while executing the SQL statements
     */
    public void fillDatabase(Store store) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement categoryStatement = connection.prepareStatement(
                     "INSERT INTO CATEGORIES (NAME) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
             PreparedStatement productStatement = connection.prepareStatement(
                     "INSERT INTO PRODUCTS (CATEGORY_ID, NAME, RATE, PRICE) VALUES (?, ?, ?, ?)")) {
            RandomStorePopulator populator = new RandomStorePopulator();
            Map<Category, Integer> categoryProducts = store.createProductList();
            for (Map.Entry<Category, Integer> entry : categoryProducts.entrySet()) {
                Category category = entry.getKey();
                int numProducts = entry.getValue();
                categoryStatement.setString(1, category.getName());
                categoryStatement.executeUpdate();
                try (ResultSet keys = categoryStatement.getGeneratedKeys()) {
                    if (keys.next()) {
                        int categoryId = keys.getInt(1);
                        for (int i = 0; i < numProducts; i++) {
                            Product product = populator.getProduct(category.getName());
                            productStatement.setInt(1, categoryId);
                            productStatement.setString(2, product.getName());
                            productStatement.setDouble(3, product.getRate());
                            productStatement.setDouble(4, product.getPrice());
                            productStatement.executeUpdate();
                        }
                    } else {
                        throw new SQLException("Failed to insert category: " + category);
                    }
                }
            }
        }
    }

    private void printDBData()throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "SELECT CATEGORIES.NAME AS CATEGORY_NAME, PRODUCTS.NAME, PRODUCTS.RATE, PRODUCTS.PRICE "
                    + "FROM CATEGORIES INNER JOIN PRODUCTS ON CATEGORIES.ID = PRODUCTS.CATEGORY_ID";
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String categoryName = resultSet.getString("CATEGORY_NAME");
                    String productName = resultSet.getString("NAME");
                    double rate = resultSet.getDouble("RATE");
                    double price = resultSet.getDouble("PRICE");
                    System.out.printf("%s: %s, rate=%.1f, price=%.1f%n", categoryName, productName, rate, price);
                }
            }
        }
    }

}
