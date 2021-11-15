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
public class Transaction implements Serializable{
    
    private String saleId;
    private String customerID;
    private String productName;
    private String barcode;
    private double price;
    private int quantitySold;

    public Transaction(String saleId, String customerID, String productName, String barcode, double price, int quantitySold) {
        this.saleId = saleId;
        this.customerID = customerID;
        this.productName = productName;
        this.barcode = barcode;
        this.price = price;
        this.quantitySold = quantitySold;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    @Override
    public String toString() {
        return "Transaction{" + "saleId=" + saleId + ", customerID=" + customerID + ", productName=" + productName + ", barcode=" + barcode + ", price=" + price + ", quantitySold=" + quantitySold + '}';
    }

}
