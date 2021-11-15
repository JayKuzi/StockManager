/**
 * This is a stock management application for a small stationery company.
 * @author Jody Kearns
 * Date: 15 October 2021
 */
package za.ac.cput.stockmanagementserver.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import za.ac.cput.stockmanagement.domain.Admin;
import za.ac.cput.stockmanagement.domain.Customer;
import za.ac.cput.stockmanagement.domain.Inventory;
import za.ac.cput.stockmanagement.domain.Transaction;
import za.ac.cput.stockmanagement.domain.User;
import za.ac.cput.stockmanagementserver.dao.AdminDao;
import za.ac.cput.stockmanagementserver.dao.CustomerDao;
import za.ac.cput.stockmanagementserver.dao.InventoryDao;
import za.ac.cput.stockmanagementserver.dao.TransactionDao;
import za.ac.cput.stockmanagementserver.dao.UserDao;
/**
 *
 * @author jodykearns
 */
public class Server {

    ServerSocket listener;
    Socket client;

    ObjectInputStream in;
    ObjectOutputStream out;

    UserDao userDao;
    AdminDao adminDao;
    InventoryDao inventoryDao;
    CustomerDao customerDao;
    TransactionDao transactionDao;
    
    String clientRequest;

    ArrayList<User> users = new ArrayList<>();
    ArrayList<Admin> admins = new ArrayList<>();
    ArrayList<Inventory> inventorys = new ArrayList<>();
    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<Transaction> transactionsByCustomerID = new ArrayList<>();
    ArrayList<Transaction> transactions = new ArrayList<>();
    
    public Server() {
        System.out.println("Server running");
        startServer();
        listen();
        createStreams();
        processClient();
    }

    public void startServer() {
        try {
            listener = new ServerSocket(5454, 1);
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }

    public void listen() {
        try {
            client = listener.accept();
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }

    public void createStreams() {
        try {
            out = new ObjectOutputStream(client.getOutputStream());
            out.flush();
            in = new ObjectInputStream(client.getInputStream());
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }

    public void processClient() {

        try {
            do {
                clientRequest = (String) in.readObject();

                if (clientRequest.equalsIgnoreCase("addUser")) {
                    System.out.println("Request Add User");
                    User user = (User) in.readObject();
                    userDao = new UserDao();
                    boolean response = userDao.addUser(user);
                    System.out.println("User added: " + response);
                    out.writeBoolean(response);
                    out.flush();

                } else if (clientRequest.equalsIgnoreCase("validateUserId")) {
                    System.out.println("Request Validate User ID");
                    String userID = (String) in.readObject();
                    userDao = new UserDao();
                    boolean response = userDao.validateUserId(userID);
                    System.out.println("UserID Validated: " + response);
                    out.writeBoolean(response);
                    out.flush();

                } else if (clientRequest.equalsIgnoreCase("getUsers")) {
                    System.out.println("Request Get All Users");
                    userDao = new UserDao();
                    users = userDao.getAllUsers();
                    out.writeObject(users);

                } else if (clientRequest.equalsIgnoreCase("getAdmins")) {
                    System.out.println("Request Get All Admins");
                    adminDao = new AdminDao();
                    admins = adminDao.getAllAdmins();
                    out.writeObject(admins);

                } else if (clientRequest.equalsIgnoreCase("addInventory")) {
                    System.out.println("Request Add Inventory");
                    Inventory inventory = (Inventory) in.readObject();
                    inventoryDao = new InventoryDao();
                    boolean response = inventoryDao.addInventory(inventory);
                    System.out.println("Inventory added: " + response);
                    out.writeBoolean(response);
                    out.flush();

                } else if (clientRequest.equalsIgnoreCase("validateBarcode")) {
                    System.out.println("Request Validate Inventory Barcode");
                    String barcode = (String) in.readObject();
                    inventoryDao = new InventoryDao();
                    boolean response = inventoryDao.validateBarcode(barcode);
                    System.out.println("UserID Validated: " + response);
                    out.writeBoolean(response);
                    out.flush();

                } else if (clientRequest.equalsIgnoreCase("getInventory")) {
                    System.out.println("Request Get All Inventory");
                    inventoryDao = new InventoryDao();
                    inventorys = inventoryDao.getAllInventory();
                    out.writeObject(inventorys);

                } else if (clientRequest.equalsIgnoreCase("updateUser")) {
                    System.out.println("Request Update user");
                    User user = (User) in.readObject();
                    userDao = new UserDao();
                    boolean response = userDao.updateUser(user);
                    System.out.println("User updated: " + response);
                    out.writeBoolean(response);
                    out.flush();

                } else if (clientRequest.equalsIgnoreCase("updateInventory")) {
                    System.out.println("Request Update inventory");
                    Inventory inventory = (Inventory) in.readObject();
                    inventoryDao = new InventoryDao();
                    boolean response = inventoryDao.updateInventory(inventory);
                    System.out.println("Inventory updated: " + response);
                    out.writeBoolean(response);
                    out.flush();

                } else if (clientRequest.equalsIgnoreCase("addCustomer")) {
                    System.out.println("Request Add Customer");
                    Customer customer = (Customer) in.readObject();
                    customerDao = new CustomerDao();
                    boolean response = customerDao.addCustomer(customer);
                    System.out.println("Customer added: " + response);
                    out.writeBoolean(response);
                    out.flush();
                    
                } else if (clientRequest.equalsIgnoreCase("validateCustomerId")) {
                    System.out.println("Request Validate Customer ID");
                    String customerID = (String) in.readObject();
                    customerDao = new CustomerDao();
                    boolean response = customerDao.validateCustomerId(customerID);
                    System.out.println("CustomerID Validated: " + response);
                    out.writeBoolean(response);
                    out.flush();
                    
                } else if (clientRequest.equalsIgnoreCase("getInventoryInStock")){
                    System.out.println("Request Get All Inventory In Stock");
                    inventoryDao = new InventoryDao();
                    inventorys = inventoryDao.getAllInventoryInStock();
                    out.writeObject(inventorys);
                    
                } else if (clientRequest.equalsIgnoreCase("getCustomers")){
                    System.out.println("Request Get All Customers");
                    customerDao = new CustomerDao();
                    customers = customerDao.getAllCustomer();
                    out.writeObject(customers);
                    
                } else if (clientRequest.equalsIgnoreCase("addTransaction")){
                    System.out.println("Request Add Transaction");
                    Transaction transaction = (Transaction) in.readObject();
                    transactionDao = new TransactionDao();
                    boolean response = transactionDao.addTransaction(transaction);
                    System.out.println("Transaction added: " + response);
                    out.writeBoolean(response);
                    out.flush();
                    
                } else if (clientRequest.equalsIgnoreCase("updateToZero")){
                    System.out.println("Request Update to zero");
                    int quantity = (int) in.readObject();
                    boolean inStock = (boolean) in.readObject();
                    String barcode = (String) in.readObject();
                    inventoryDao = new InventoryDao();
                    boolean response = inventoryDao.updateInventoryToZero(quantity, inStock, barcode);
                    System.out.println("Inventory Updated to Zero: " + response);
                    out.writeBoolean(response);
                    out.flush();
                    
                } else if (clientRequest.equalsIgnoreCase("updateInventoryAfterSale")){
                    System.out.println("Request Update Quantity After Sale");
                    int quantity = (int) in.readObject();
                    String barcode = (String) in.readObject();
                    inventoryDao = new InventoryDao();
                    boolean response = inventoryDao.updateInventoryAfterSale(quantity, barcode);
                    System.out.println("Inventory Updated After Sale" + response);
                    out.writeBoolean(response);
                    out.flush();
                    
                } else if (clientRequest.equalsIgnoreCase("getTransactionsByCustomer")){
                    System.out.println("Request Get All Transactions By Customer");
                    String customerID = (String) in.readObject();
                    transactionDao = new TransactionDao();
                    transactionsByCustomerID = transactionDao.getTransactionsByCustomerId(customerID);
                    out.writeObject(transactionsByCustomerID);
                    
                } else if (clientRequest.equalsIgnoreCase("getTransactions")){
                    System.out.println("Request Get All Transactions");
                    transactionDao = new TransactionDao();
                    transactions = transactionDao.getAllTransactions();
                    out.writeObject(transactions);
                }

            } while (!clientRequest.equalsIgnoreCase("terminate"));

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Eception: " + e);
        }
    }
}
//getTransactions