package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private Connection connection;
    private final String connectionString = "jdbc:mysql://localhost/tododb?user=root&password=1D952tnZ";
    
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    public boolean add(Product product) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("INSERT INTO products (name, stock, img_name) VALUES (?, ?, ?);");
            statement.setString(1, product.getName());
            statement.setInt(2, product.getStock());
            statement.setString(3, product.getImgName());
            
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Product> get() {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products;");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getInt(1),
                                                resultSet.getString(2),
                                                resultSet.getInt(3),
                                                resultSet.getString(4));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public boolean update(Product product) {
        try {
            PreparedStatement statement = null;
            
            if (product.getImgName() == null) {
                statement = connection.prepareStatement("UPDATE products SET name = ?, stock = ? WHERE id = ?;");               
                statement.setString(1, product.getName());
                statement.setInt(2, product.getStock());
                statement.setInt(3, product.getId());
            } else {
                statement = connection.prepareStatement("UPDATE products SET name = ?, stock = ?, img_name = ? WHERE id = ?;");
                statement.setString(1, product.getName());
                statement.setInt(2, product.getStock());
                statement.setString(3, product.getImgName());
                statement.setInt(4, product.getId());
            }            
            return statement.executeUpdate() == 1;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
