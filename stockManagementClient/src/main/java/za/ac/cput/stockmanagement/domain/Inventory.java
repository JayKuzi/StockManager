/**
 * This is a stock management application for a small stationery company.
 * @author Jody Kearns
 * Date: 15 October 2021
 */
package za.ac.cput.stockmanagement.domain;

import java.io.Serializable;

/**
 *
 * @author jodykearns
 */
public class Inventory implements Serializable{
    
    private String barcode;
    private String productName;
    private String description;
    private String category;
    private int quantity;
    private double price;
    private boolean inStock;

    public Inventory(String barcode, String productName, String description, String category, int quantity, double price, boolean inStock) {
        this.barcode = barcode;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.inStock = inStock;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    @Override
    public String toString() {
        return "Inventory{" + "barcode=" + barcode + ", productName=" + productName + ", description=" + description + ", category=" + category + ", quantity=" + quantity + ", price=" + price + ", inStock=" + inStock + '}';
    }
    
    
}
