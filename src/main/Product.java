package main;

public class Product {
    private int id;
    private String name;
    private int stock;
    private String imgName;
    
    public Product(String name, int stock, String imgName) {
        this.name = name;
        this.stock = stock;
        this.imgName = imgName;
    }
    
    public Product(int id, String name, int stock, String imgName) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.imgName = imgName;
    }   

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }    
}
