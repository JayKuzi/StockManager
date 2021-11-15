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
public class User implements Serializable{
    
    private String userID;
    private String fullName;
    private String password;
    private boolean inactive;

    public User(String userID, String fullName, String password, boolean inactive) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.inactive = inactive;
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

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", fullName=" + fullName + ", password=" + password + ", inactive=" + inactive + '}';
    }
    
}
