/**
 * This is a stock management application for a small stationery company.
 * @author Jody Kearns
 * Date: 15 October 2021
 */
package za.ac.cput.stockmanagementserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import za.ac.cput.stockmanagement.domain.Customer;
import za.ac.cput.stockmanagementserver.connection.DBConnection;

/**
 *
 * @author jodykearns
 */
public class CustomerDao {

    private Connection con;
    private PreparedStatement ps;
    ResultSet rs;

    public CustomerDao() {
        this.con = DBConnection.derbyConnection();
    }

    //ADD CUSTOMER DAO
    public boolean addCustomer(Customer customer) {
        int response = 0;
        String addCustomerSQL = "INSERT INTO customer (customer_id, full_name, phone_number)"
                + "VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(addCustomerSQL);
            ps.setString(1, customer.getCustomerID());
            ps.setString(2, customer.getFullName());
            ps.setString(3, customer.getPhoneNumber());
            response = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQL Exception: " + ex);
            }
        }
        return response == 1;
    }
    //********************************************************

    //VALIDATE CUSTOMER ID DAO
    public boolean validateCustomerId(String customerId) {
        boolean exists = false;
        String validatCustomerIdSQL = "SELECT * FROM customer WHERE customer_id = ?";
        try {
            ps = con.prepareStatement(validatCustomerIdSQL);
            ps.setString(1, customerId);
            rs = ps.executeQuery();
            exists = rs.next();
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQL Exception" + ex);
            }
        }
        return exists;
    }
    //********************************************************

    //GET INVENTORY DAO
    public ArrayList<Customer> getAllCustomer() {
        ArrayList<Customer> customerList = new ArrayList<>();
        String getAllCustomersSQL = "SELECT * FROM customer";
        try {
            ps = con.prepareStatement(getAllCustomersSQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                customerList.add(new Customer(rs.getString("customer_id"), rs.getString("full_name"), rs.getString("phone_number")));
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    con.close();
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQL Exception: " + ex);
            }
        }
        return customerList;
    }
    //********************************************************

}
