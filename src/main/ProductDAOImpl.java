package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
