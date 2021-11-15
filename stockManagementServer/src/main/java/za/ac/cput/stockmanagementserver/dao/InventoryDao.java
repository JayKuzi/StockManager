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
import za.ac.cput.stockmanagement.domain.Inventory;
import za.ac.cput.stockmanagementserver.connection.DBConnection;

/**
 *
 * @author jodykearns
 */
public class InventoryDao {

    private Connection con;
    private PreparedStatement ps;
    ResultSet rs;

    public InventoryDao() {
        this.con = DBConnection.derbyConnection();
    }

    //ADD INVENTORY DAO
    public boolean addInventory(Inventory inventory) {
        int response = 0;
        String addInventorySQL = "INSERT INTO inventory (barcode, product_name, description, category, quantity, price, instock)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(addInventorySQL);
            ps.setString(1, inventory.getBarcode());
            ps.setString(2, inventory.getProductName());
            ps.setString(3, inventory.getDescription());
            ps.setString(4, inventory.getCategory());
            ps.setInt(5, inventory.getQuantity());
            ps.setDouble(6, inventory.getPrice());
            ps.setBoolean(7, inventory.isInStock());
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

    //VALIDATE USERID DAO
    public boolean validateBarcode(String barcode) {
        boolean exists = false;
        String validateBarcodeIdSQL = "SELECT * FROM inventory WHERE barcode = ?";
        try {
            ps = con.prepareStatement(validateBarcodeIdSQL);
            ps.setString(1, barcode);
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
    public ArrayList<Inventory> getAllInventory() {
        ArrayList<Inventory> inventoryList = new ArrayList<>();
        String getAllInventorySQL = "SELECT * FROM inventory ORDER BY product_name ASC";
        try {
            ps = con.prepareStatement(getAllInventorySQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                inventoryList.add(new Inventory(rs.getString("barcode"), rs.getString("product_name"), rs.getString("description"), rs.getString("category"), rs.getInt("quantity"), rs.getDouble("price"), rs.getBoolean("instock")));
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
        return inventoryList;
    }
    //********************************************************

    //UPDATE INVENTORY DAO
    public boolean updateInventory(Inventory inventory) {
        String updateInventorySQL = "UPDATE inventory SET product_name = ?, description = ?, category = ?, quantity = ?, price = ?, instock = ? WHERE barcode = ?";
        int response = 0;
        try {
            ps = this.con.prepareStatement(updateInventorySQL);
            ps.setString(1, inventory.getProductName());
            ps.setString(2, inventory.getDescription());
            ps.setString(3, inventory.getCategory());
            ps.setInt(4, inventory.getQuantity());
            ps.setDouble(5, inventory.getPrice());
            ps.setBoolean(6, inventory.isInStock());
            ps.setString(7, inventory.getBarcode());
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

    //GET INVENTORY DAO
    public ArrayList<Inventory> getAllInventoryInStock() {
        ArrayList<Inventory> inventoryList = new ArrayList<>();
        String getAllInventorySQL = "SELECT * FROM inventory WHERE instock = true ORDER BY product_name ASC";
        try {
            ps = con.prepareStatement(getAllInventorySQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                inventoryList.add(new Inventory(rs.getString("barcode"), rs.getString("product_name"), rs.getString("description"), rs.getString("category"), rs.getInt("quantity"), rs.getDouble("price"), rs.getBoolean("instock")));
            }
        } catch (SQLException ex) {
            System.out.println("Line 158 SQL Exception: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    con.close();
                    rs.close();
//                    System.out.println(inventoryList);
                }
            } catch (SQLException ex) {
                System.out.println("SQL Exception: " + ex);
            }
        }
        return inventoryList;
    }
    //********************************************************

    //UPDATE TO ZERO DAO
    public boolean updateInventoryToZero(int quantity, boolean inStock, String barcode) {
        String updateInventoryToZeroSQL = "UPDATE inventory SET quantity = ?, instock = ? WHERE barcode = ?";
        int response = 0;
        try {
            ps = this.con.prepareStatement(updateInventoryToZeroSQL);
            ps.setInt(1, quantity);
            ps.setBoolean(2, inStock);
            ps.setString(3, barcode);
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

    //UPDATE Inventory AFTER SALE DAO
    public boolean updateInventoryAfterSale(int quantity, String barcode) {
        String updateInventoryAfterSaleSQL = "UPDATE inventory SET quantity = ? WHERE barcode = ?";
        int response = 0;
        try {
            ps = this.con.prepareStatement(updateInventoryAfterSaleSQL);
            ps.setInt(1, quantity);
            ps.setString(2, barcode);
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
}