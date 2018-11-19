package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private Connection connection;
    
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost/tododb?user=root&password=1D952tnZ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    @Override
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

    @Override
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
}
