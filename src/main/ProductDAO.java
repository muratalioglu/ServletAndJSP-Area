package main;

import java.util.List;

public interface ProductDAO {
    boolean add(Product product);
    List<Product> get();
}
