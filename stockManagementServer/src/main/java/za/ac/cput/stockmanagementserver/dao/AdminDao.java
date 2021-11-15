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
import za.ac.cput.stockmanagement.domain.Admin;
import za.ac.cput.stockmanagementserver.connection.DBConnection;

/**
 *
 * @author jodykearns
 */
public class AdminDao {

    private Connection con;
    private PreparedStatement ps;
    ResultSet rs;

    public AdminDao() {
        this.con = DBConnection.derbyConnection();
    }
    
    //GET ADMIN DAO
    public ArrayList<Admin> getAllAdmins() {
        ArrayList<Admin> adminList = new ArrayList<>();
        String getAllAdminSQL = "SELECT * FROM admin_login";
        try {
            ps = con.prepareStatement(getAllAdminSQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                adminList.add(new Admin(rs.getString("user_id"), rs.getString("full_name"), rs.getString("password")));
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
        return adminList;
    }
    //********************************************************

}
