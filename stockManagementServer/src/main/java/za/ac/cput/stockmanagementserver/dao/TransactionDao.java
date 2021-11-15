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
import za.ac.cput.stockmanagement.domain.Transaction;
import za.ac.cput.stockmanagementserver.connection.DBConnection;

/**
 *
 * @author jodykearns
 */
public class TransactionDao {

    private Connection con;
    private PreparedStatement ps;
    ResultSet rs;

    public TransactionDao() {
        this.con = DBConnection.derbyConnection();
    }

    //ADD INVENTORY DAO
    public boolean addTransaction(Transaction transaction) {
        int response = 0;
        String addTransactionSQL = "INSERT INTO sale_transaction (sale_id, customer_id, product_name, barcode, price, quantity_sold)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(addTransactionSQL);
            ps.setString(1, transaction.getSaleId());
            ps.setString(2, transaction.getCustomerID());
            ps.setString(3, transaction.getProductName());
            ps.setString(4, transaction.getBarcode());
            ps.setDouble(5, transaction.getPrice());
            ps.setInt(6, transaction.getQuantitySold());
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
                System.out.println("SQL Exception: " + ex.getMessage());
            }
        }
        return response == 1;
    }
    //********************************************************

    //GET TRANSACTIONS BY CLIENT ID DAO
    public ArrayList<Transaction> getTransactionsByCustomerId(String string) {
        ArrayList<Transaction> transactionByCutsomerList = new ArrayList<>();
        String getTransactionsByCustomerIdSQL = "SELECT * FROM sale_transaction WHERE customer_id = ?";
        try {
            ps = con.prepareStatement(getTransactionsByCustomerIdSQL);
            ps.setString(1, string);
            rs = ps.executeQuery();

            while (rs.next()) {
                transactionByCutsomerList.add(new Transaction(rs.getString("sale_id"), rs.getString("customer_id"), rs.getString("product_name"), rs.getString("barcode"), rs.getDouble("price"), rs.getInt("quantity_sold")));
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
        System.out.println("transactionByCutsomerList");
        return transactionByCutsomerList;
    }
    //********************************************************

    //GET ALL TRANSACTIONS DAO
    public ArrayList<Transaction> getAllTransactions() {
        ArrayList<Transaction> allTransactions = new ArrayList<>();
        String getAllTransactionsSQL = "SELECT * FROM sale_transaction";
        try {
            ps = con.prepareStatement(getAllTransactionsSQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                allTransactions.add(new Transaction(rs.getString("sale_id"), rs.getString("customer_id"), rs.getString("product_name"), rs.getString("barcode"), rs.getDouble("price"), rs.getInt("quantity_sold")));
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
        System.out.println("All transactions");
        return allTransactions;
    }
    //********************************************************
}
