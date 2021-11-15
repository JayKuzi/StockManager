/**
 * This is a stock management application for a small stationery company.
 * @author Jody Kearns
 * Date: 15 October 2021
 */
package za.ac.cput.stockmanagement.clientserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import za.ac.cput.stockmanagement.domain.Admin;
import za.ac.cput.stockmanagement.domain.Customer;
import za.ac.cput.stockmanagement.domain.Inventory;
import za.ac.cput.stockmanagement.domain.Transaction;
import za.ac.cput.stockmanagement.domain.User;

/**
 *
 * @author jodykearns
 */
public class ClientToServer {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    //SERVER CONNECTION FROM CLIENT.
    public ClientToServer() {
        try {
            socket = new Socket("127.0.0.1", 5454);
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    //********************************************************

    //GET USERS CLIENT METHOD
    public ArrayList<User> getUsers() {
        try {
            System.out.println("ClientServer getUsers method");
            out.writeObject("getUsers");
            out.flush();
            System.out.println("ClientServer getUsers method: completed");
            return (ArrayList<User>) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ClientServer getUsers method: " + ex);
            return new ArrayList<>();
        }
    }
    //********************************************************

    //ADD USER CLIENT METHOD
    public boolean addUser(User user) {
        try {
            out.writeObject("addUser");
            out.flush();
            out.writeObject(user);
            out.flush();
            System.out.println("ClientServer addUser method: completed");
            return in.readBoolean();
        } catch (IOException ioe) {
            System.out.println("ClientServer addUser Method: " + ioe);
            return false;
        }
    }
    //********************************************************

    //VALIDATE USERID CLIENT METHOD
    public boolean validateUserId(String string) {
        try {
            out.writeObject("validateUserId");
            out.flush();
            out.writeObject(string);
            out.flush();
            System.out.println("ClientServer validateUserId method: completed");
            return in.readBoolean();
        } catch (IOException ex) {
            System.out.println("CLientServer validatedUserID Method: " + ex);
            return true;
        }
    }
    //********************************************************

    //GET ADMINS CLIENT METHOD
    public ArrayList<Admin> getAdmins() {
        try {
            System.out.println("ClientServer getAdmins method");
            out.writeObject("getAdmins");
            out.flush();
            System.out.println("ClientServer getAdmins method: completed");
            return (ArrayList<Admin>) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ClientServer getAdmins method:" + ex);
            return new ArrayList<>();
        }
    }
    //********************************************************

    //GET ADMINS CLIENT METHOD
    public boolean addInventory(Inventory inventory) {
        try {
            out.writeObject("addInventory");
            out.flush();
            out.writeObject(inventory);
            out.flush();
            System.out.println("ClientServer addInventory method: completed");
            return in.readBoolean();
        } catch (IOException ioe) {
            System.out.println("ClientServer addInventory Method: " + ioe);
            return false;
        }
    }
    //********************************************************

    //VALIDATE USERID CLIENT METHOD
    public boolean validateBarcode(String string) {
        try {
            out.writeObject("validateBarcode");
            out.flush();
            out.writeObject(string);
            out.flush();
            System.out.println("ClientServer validateBarcode method: completed");
            return in.readBoolean();
        } catch (IOException ex) {
            System.out.println("CLientServer validatedUserID Method: " + ex);
            return true;
        }
    }
    //********************************************************

    //GET INVENTORY CLIENT METHOD
    public ArrayList<Inventory> getInventory() {
        try {
            System.out.println("ClientServer getInventory method");
            out.writeObject("getInventory");
            out.flush();
            System.out.println("ClientServer getInventory method: completed");
            return (ArrayList<Inventory>) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ClientServer getInventory method:" + ex);
            return new ArrayList<>();
        }
    }
    //********************************************************

    //GET INVENTORY CLIENT METHOD
    public boolean updateUser(User user) {
        try {
            out.writeObject("updateUser");
            out.flush();
            out.writeObject(user);
            out.flush();
            System.out.println("ClientServer updateUser method: completed");
            return in.readBoolean();
        } catch (IOException ioe) {
            System.out.println("ClientServer updateUser Method: " + ioe);
            return false;
        }
    }
    //********************************************************

    //GET INVENTORY CLIENT METHOD
    public boolean updateInventory(Inventory inventory) {
        try {
            out.writeObject("updateInventory");
            out.flush();
            out.writeObject(inventory);
            out.flush();
            System.out.println("ClientServer updateInventory method: completed");
            return in.readBoolean();
        } catch (IOException ioe) {
            System.out.println("ClientServer updateInventory Method: " + ioe);
            return false;
        }
    }
    //********************************************************

    //ADD USER CLIENT METHOD
    public boolean addCustomer(Customer customer) {
        try {
            out.writeObject("addCustomer");
            out.flush();
            out.writeObject(customer);
            out.flush();
            System.out.println("ClientServer addCustomer method: completed");
            return in.readBoolean();
        } catch (IOException ioe) {
            System.out.println("ClientServer addCustomer Method: " + ioe);
            return false;
        }
    }
    //********************************************************

    //VALIDATE CUSTOMER CLIENT METHOD
    public boolean validateCustomerId(String string) {
        try {
            out.writeObject("validateCustomerId");
            out.flush();
            out.writeObject(string);
            out.flush();
            System.out.println("ClientServer validateCustomerId method: completed");
            return in.readBoolean();
        } catch (IOException ex) {
            System.out.println("CLientServer validatedCustomerID Method: " + ex);
            return true;
        }
    }
    //********************************************************

    //GET INVENTORY CLIENT METHOD
    public ArrayList<Inventory> getInventoryInStock() {
        try {
            System.out.println("ClientServer getInventoryInStock method");
            out.writeObject("getInventoryInStock");
            out.flush();
            System.out.println("ClientServer getInventoryInStock method: completed");
            return (ArrayList<Inventory>) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ClientServer getInventoryInStock method:" + ex);
            return new ArrayList<>();
        }
    }
    //********************************************************

    //GET USERS CLIENT METHOD
    public ArrayList<Customer> getCustomers() {
        try {
            System.out.println("ClientServer getCustomers method");
            out.writeObject("getCustomers");
            out.flush();
            System.out.println("ClientServer getCustomers method: completed");
            return (ArrayList<Customer>) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ClientServer getCustomers method: " + ex);
            return new ArrayList<>();
        }
    }
    //********************************************************

    //ADD USER CLIENT METHOD
    public boolean addTransaction(Transaction transaction) {
        try {
            System.out.println("ClientServer addTransaction method");
            out.writeObject("addTransaction");
            out.flush();
            out.writeObject(transaction);
            out.flush();
            System.out.println("ClientServer addTransaction method: completed");
            return in.readBoolean();
        } catch (IOException ioe) {
            System.out.println("ClientServer addTransaction Method: " + ioe);
            return false;
        }
    }
    //********************************************************

    //UPDATE TO ZERO CLIENT METHOD
    public boolean updateInventoryToZero(int quantity, boolean inStock, String barcode) {
        try {
            System.out.println("ClientServer updateInventoryToZero method");
            out.writeObject("updateToZero");
            out.flush();
            out.writeObject(quantity);
            out.flush();
            out.writeObject(inStock);
            out.flush();
            out.writeObject(barcode);
            out.flush();
            System.out.println("ClientServer updateInventoryToZero method: completed");
            return in.readBoolean();
        } catch (IOException ioe) {
            System.out.println("ClientServer updateInventoryToZero Method: " + ioe);
            return false;
        }
    }
    //********************************************************

    //UPDATE AFTER SALE CLIENT METHOD
    public boolean updateInventoryAfterSale(int quantity, String barcode) {
        try {
            System.out.println("ClientServer updateInventoryAfterSale method");
            out.writeObject("updateInventoryAfterSale");
            out.flush();
            out.writeObject(quantity);
            out.flush();
            out.writeObject(barcode);
            out.flush();
            System.out.println("ClientServer updateInventoryAfterSale method: completed");
            return in.readBoolean();
        } catch (IOException ex) {
            System.out.println("ClientServer updateInventoryAfterSale Method: " + ex);
            return false;
        }
    }
    //********************************************************

    //GET TRANSACTIONS BY CLIENT ID CLIENT METHOD
    public ArrayList<Transaction> getTransactionsByCustomer(String string) {
        try {
            System.out.println("ClientServer getTransactionsByCustomer method");
            out.writeObject("getTransactionsByCustomer");
            out.flush();
            out.writeObject(string);
            out.flush();
            System.out.println("ClientServer getTransactionsByCustomer method: completed");
            return (ArrayList<Transaction>) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ClientServer getTransactionsByCustomer method: " + ex);
            return new ArrayList<>();
        }
    }
    //********************************************************

    //GET ALL TRANSACTIONS CLIENT METHOD
    public ArrayList<Transaction> getAllTransactions() {
        try {
            System.out.println("ClientServer getTransactions method");
            out.writeObject("getTransactions");
            out.flush();
            System.out.println("ClientServer getTransactions method: completed");
            return (ArrayList<Transaction>) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ClientServer getTransactions method: " + ex);
            return new ArrayList<>();
        }
    }
    //********************************************************
}
