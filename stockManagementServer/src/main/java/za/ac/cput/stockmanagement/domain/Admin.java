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
public class Admin implements Serializable{
    
    private String userID;
    private String fullName;
    private String password;

    public Admin(String userID, String fullName, String password) {
        
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" + "userID=" + userID + ", fullName=" + fullName + ", password=" + password + '}';
    }
    
}
