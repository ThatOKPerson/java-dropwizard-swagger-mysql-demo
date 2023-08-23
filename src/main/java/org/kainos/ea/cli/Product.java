package org.kainos.ea.cli;

public class Product implements Comparable<Product>{
    private int productID;
    private String name;
    private String description;
    private double price;

    public Product(int productID, String name, String description, double price) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    @Override
    public int compareTo(Product product) {
        return Double.compare(this.getPrice(), product.getPrice());
    }

    @Override
    public String toString(){
        return "Product name: " + this.getName() + ", Product price: £" + this.getPrice();
    }
}
