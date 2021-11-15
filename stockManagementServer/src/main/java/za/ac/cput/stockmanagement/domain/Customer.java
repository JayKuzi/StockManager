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
public class Customer implements Serializable{
    
    private String customerID;
    private String fullName;
    private String phoneNumber;

    public Customer(String customerID, String fullName, String phoneNumber) {
        
        this.customerID = customerID;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerID=" + customerID + ", fullName=" + fullName + ", phoneNumber=" + phoneNumber + '}';
    }
    
}
