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
import za.ac.cput.stockmanagement.domain.User;
import za.ac.cput.stockmanagementserver.connection.DBConnection;

/**
 *
 * @author jodykearns
 */
public class UserDao {

    private Connection con;
    private PreparedStatement ps;
    ResultSet rs;

    public UserDao() {
        this.con = DBConnection.derbyConnection();
    }

    //ADD USER DAO
    public boolean addUser(User user) {
        int response = 0;
        String addUserSQL = "INSERT INTO user_login (user_id, full_name, password, inactive)"
                + "VALUES (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(addUserSQL);
            ps.setString(1, user.getUserID());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getPassword());
            ps.setBoolean(4, user.isInactive());
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

    //VALIDATE USER ID DAO
    public boolean validateUserId(String userId) {
        boolean exists = false;
        String validateUserIdSQL = "SELECT * FROM user_login WHERE user_id = ?";
        try {
            ps = con.prepareStatement(validateUserIdSQL);
            ps.setString(1, userId);
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

    //GET USERS DAO
    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        String getAllUsersSQL = "SELECT * FROM user_login";
        try {
            ps = con.prepareStatement(getAllUsersSQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                userList.add(new User(rs.getString("user_id"), rs.getString("full_name"), rs.getString("password"), rs.getBoolean("inactive")));
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
        return userList;
    }
    //********************************************************

    //UPDATE USERS DAO
    public boolean updateUser(User user) {
        String updateUserSQL = "UPDATE user_login SET full_name = ?, password = ?, inactive = ? WHERE user_id = ?";
        int response = 0;
        try {
            ps = this.con.prepareStatement(updateUserSQL);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getPassword());
            ps.setBoolean(3, user.isInactive());
            ps.setString(4, user.getUserID());

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
