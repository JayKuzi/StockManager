/**
 * This is a stock management application for a small stationery company.
 * @author Jody Kearns
 * Date: 15 October 2021
 */
package za.ac.cput.stockmanagementserver.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jodykearns
 */
public class DBConnection {
    
    public static Connection derbyConnection(){
        String url = "jdbc:derby://localhost:1527/StockManagementDB";
        String user = "username";
        String password = "password";
        Connection stockManagementConnection = null;
        try {
            stockManagementConnection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.print("Connection Error");
        }
        return stockManagementConnection;
    }
    
}
