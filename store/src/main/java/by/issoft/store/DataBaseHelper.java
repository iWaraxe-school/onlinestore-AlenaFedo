package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Map;

public class DataBaseHelper {

    Store store;
    public DataBaseHelper(Store store) throws SQLException {
        this.store = store;
        connectToDb();
        clearDB();
        createCategoryTable();
        createProductTable();
        fillDBStore();
        printDBData();
    }
static Statement STATEMENT;
static  final String URL = "jdbc:h2:~/OnlineStore";//"jdbc:h2://localhost:8082/OnlineStore";//
static final  String USER = "sa";
public void connectToDb() throws SQLException {
    try {
        Class.forName("org.h2.Driver");
        var connection = DriverManager.getConnection(URL, USER, "");
        System.out.println("Connect succesfull!!");
        STATEMENT = connection.createStatement();
    } catch (SQLException | ClassNotFoundException e) {
        throw new RuntimeException(e);
    }

}

public void clearDB(){
    String dropCategories = "DROP TABLE IF EXISTS CATEGORIES";
    String dropProducts = "DROP TABLE IF EXISTS PRODUCTS";
    try {
        STATEMENT.executeUpdate(dropProducts);
        STATEMENT.executeUpdate(dropCategories);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

public void createCategoryTable(){
    String createCategories = "CREATE TABLE IF NOT EXISTS CATEGORIES(" +
          "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, NAME VARCHAR(255) NOT NULL)";
    try {
        STATEMENT.executeUpdate(createCategories);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

public void createProductTable(){
    String createProducts = "CREATE TABLE IF NOT EXISTS PRODUCTS(" +
            "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, "+
            "CATEGORY_ID INT NOT NULL," +
            "NAME VARCHAR(255) NOT NULL, "+
            "RATE DECIMAL(10,1 ) NOT NULL,  "+
            "PRICE DECIMAL(10,1 ) NOT NULL, "+
            "FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORIES(ID))";
    try {
        STATEMENT.executeUpdate(createProducts);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

public void fillDBStore() {
    RandomStorePopulator populator = new RandomStorePopulator();
    Map<Category, Integer> categoryProducts = store.createProductList();
    int id = 1;
    for (Map.Entry<Category, Integer> entry : categoryProducts.entrySet()) {
        String sql1 = "INSERT INTO CATEGORIES (NAME) VALUES('" + entry.getKey().getName() + "')";
        try {
            STATEMENT.executeUpdate(sql1);

            for (int i = 0; i < entry.getValue(); i++) {
                Product product =
                        populator.getProguct(entry.getKey().getName());

                String sql2 = "INSERT INTO PRODUCTS(CATEGORY_ID, NAME, RATE, PRICE) VALUES(" +
                        id + ",'" + product.getName() + "'," + product.getRate() + "," + product.getPrice() + ")";
                STATEMENT.executeUpdate(sql2);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        id++;
    }
}

    public void printDBData() {

        String sql = "SELECT CATEGORIES.NAME as CATEGORIES_name, PRODUCTS.NAME, PRODUCTS.RATE, PRODUCTS.PRICE  FROM CATEGORIES inner join PRODUCTS on categories.ID=products.category_id";

        try {
            var rs1 = STATEMENT.executeQuery(sql);
            printData(rs1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void printData(ResultSet rs) throws SQLException {
        while (rs.next()) {
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                System.out.print(rs.getString(i) + "   ");
            }
            System.out.println();
        }
    }
}
