/**
 * This is a stock management application for a small stationery company.
 * @author Jody Kearns
 * Date: 15 October 2021
 */
package za.ac.cput.stockmanagementclient.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import za.ac.cput.stockmanagement.clientserver.ClientToServer;
import za.ac.cput.stockmanagement.domain.Admin;
import za.ac.cput.stockmanagement.domain.Customer;
import za.ac.cput.stockmanagement.domain.Inventory;
import za.ac.cput.stockmanagement.domain.Transaction;
import za.ac.cput.stockmanagement.domain.User;

/**
 *
 * @author jodykearns
 */
public class ClientGui extends JFrame implements ActionListener {

    //LOGIN GUI 
    private JFrame loginFrame;
    private JPanel panelLoginCenter, panelLoginSouth;
    private JLabel lblIdLogin, lblPasswordLogin;
    private JTextField txtIdLogin, txtPasswordLogin;
    private JButton btnAdminLogin, btnUserLogin;
    //********************************************************

    //ADMIN GUI
    private JFrame adminFrame;
    private JPanel panelAdminCenter;
    private JButton btnAdminAddProduct, btnAdminAddUser, btnAdminUpdateProduct, btnAdminUpdateUser, btnAdminBack;
    //********************************************************

    //USER GUI
    private JFrame userFrame;
    private JPanel panelUserCenter;
    private JButton btnUserAddCustomer, btnUserCustomerSale, btnUserDisplayProducts, btnUserInvoice, btnUserSalesReport, btnUserBack;
    //********************************************************

    //ADMIN ADD PRODUCT
    private JFrame addProductFrame;
    private JPanel panelAddProductCenter, panelAddProductSouth;
    private JLabel lblBarcode, lblProductName, lblDescription, lblCategory, lblQuantity, lblPrice;
    private JTextField txtBarcode, txtProductName, txtDescription, txtQuantity, txtPrice;
    private JComboBox cmbCategory;
    private JButton btnAddProduct, btnAddProductBack;
    String[] category = {"--Selection--", "Business", "Filing", "Writing"};
    //********************************************************

    //ADMIN ADD USER
    private JFrame addUserFrame;
    private JPanel panelAddUserCenter, panelAddUserSouth;
    private JLabel lblUserID, lblUserFullName, lblUserPassword;
    private JTextField txtUserID, txtUserFullName, txtUserPassword;
    private JButton btnAddUser, btnAddUserBack;
    //********************************************************

    //ADMIN UPDATE PRODUCT
    private JFrame updateProducFrame;
    private JPanel panelUpdateProductCenter, panelProductUserSouth;
    private JLabel lblUpdateBarcodeSelection, lblUpdateProductName, lblUpdateDescription, lblUpdateCategory, lblUpdateQuantity, lblUpdatePrice;
    private JTextField txtUpdateProductName, txtUpdateDescription, txtUpdateQuatity, txtUpdatePrice;
    private JComboBox cmbUpdateBarcodeSelection, cmbUpdateCategory;
    private JButton btnUpdateProductSave, btnUpdateProductBack;
    //********************************************************

    //ADMIN UPDATE USER
    private JFrame updateUserFrame;
    private JPanel panelUpdateUserCenter, panelUpdateUserSouth;
    private JLabel lblUpdateUserIDSelection, lblUpdateUserFullName, lblUpdateUserPassword, lblUpdateUserInactive;
    private JTextField txtUpdateUserFullName, txtUpdateUserPassword;
    private JCheckBox chkUpdateUserInactive;
    private JComboBox cmbUpdateUserIDSelection;
    private JButton btnUpdateUserSave, btnUpdateUserBack;
    //********************************************************

    //USER ADD CUSTOMER
    private JFrame addCustomerFrame;
    private JPanel panelAddCustomerCenter, panelAddCustomerSouth;
    private JLabel lblAddCustomerID, lblAddCustomerFullName, lblAddCustomerPhoneNumber;
    private JTextField txtAddCustomerID, txtAddCustomerFullName, txtAddCustomerPhoneNumber;
    private JButton btnAddCustomer, btnAddCustomerBack;
    //********************************************************

    //USER PROCESS SALE
    private JFrame processSaleFrame;
    private JPanel panelProcessSaleCenter, panelProcessSaleSouth;
    private JLabel lblProcessSaleCustomer, lblProcessSaleProduct, lblProcessSaleBarcode, lblProcessSalePrice, lblProcessSaleCurrentQuantity, lblProcessSaleOrderQuantity;
    private JTextField txtProcessSaleBarcode, txtProcessSalePrice, txtProcessSaleCurrentQuantity, txtProcessSaleOrderQuantity;
    private JComboBox cmbProcessSaleCustomer, cmbProcessSaleProduct;
    private JButton btnProcessSaleSave, btnProcessSaleBack;

    //********************************************************
    //DISPLAY INVENTORY
    private JFrame displayInventoryFrame;
    private JPanel panelDisplayInventoryCenter;
    private JPanel panelDisplayInventorySouth;
    private JTable tblDisplayInventory;
    private DefaultTableModel tableModelInventory;
    private JButton btnsearchInventory;
    private JTextField txtsearchInventory;
    private Vector originalInventoryTable;

    //********************************************************
    //DISPLAY CUSTOMER INVOICE
    private JFrame customerInvoiceFrame;
    private JPanel panelCustomerInvoiceCenter, panelCustomerInvoiceSouth;
    private JLabel lblCustomerInvoiceSelectCustomerId;
    private JComboBox cmbCustomerInvoiceSelectCustomerId;
    private JButton btnCustomerInvoiceGenerate, btnCustomerInvoiceBack;

    //********************************************************
    //DOMAIN CLASS OBJECT
    User user;
    Inventory inventory;
    Customer customer;
    Transaction transaction;
    //********************************************************

    //CLIENTSERVER CLASS OBJECT
    ClientToServer request = new ClientToServer();
    //********************************************************

    //ARRAY LISTS OF OBJECTS
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Admin> admins = new ArrayList<>();
    ArrayList<Inventory> inventorys = new ArrayList<>();
    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<Inventory> inventorysInStock = new ArrayList<>();
    ArrayList<Transaction> transactions = new ArrayList<>();
    ArrayList<Transaction> transactionsByCustomerId = new ArrayList<>();
    //********************************************************

    //ADDITIONAL METHODS CLASS OBJECT
    AdditionalMethods ad = new AdditionalMethods();
    //********************************************************

    private BufferedWriter bufferw;

    public ClientGui() {

        //LOGIN GUI
        Font font1 = new Font("SansSerif", Font.PLAIN, 15);
        loginFrame = new JFrame("Login");
        loginFrame.setPreferredSize(new Dimension(300, 120));
        panelLoginCenter = new JPanel();
        panelLoginSouth = new JPanel();
        lblIdLogin = new JLabel(" Employee Id:");
        lblPasswordLogin = new JLabel(" Password:");
        txtIdLogin = new JTextField();
        txtIdLogin.setFont(font1);
        txtPasswordLogin = new JTextField();
        txtPasswordLogin.setFont(font1);
        btnAdminLogin = new JButton("ADMIN LOGIN");
        btnUserLogin = new JButton("USER LOGIN");
        //********************************************************  

        //ADMIN GUI
        adminFrame = new JFrame("Admin Homepage");
        adminFrame.setPreferredSize(new Dimension(250, 250));
        panelAdminCenter = new JPanel();
        btnAdminAddProduct = new JButton("ADD NEW PRODUCT");
        btnAdminAddUser = new JButton("ADD NEW USER");
        btnAdminUpdateProduct = new JButton("UPDATE PRODUCT");
        btnAdminUpdateUser = new JButton("UPDATE USER");
        btnAdminBack = new JButton("LOGOUT");
        btnAdminBack.setBackground(Color.RED);
        btnAdminBack.setOpaque(true);
        //********************************************************

        //USER GUI
        userFrame = new JFrame("User Homepage");
        userFrame.setPreferredSize(new Dimension(250, 250));
        panelUserCenter = new JPanel();
        btnUserAddCustomer = new JButton("ADD NEW CUSTOMER");
        btnUserCustomerSale = new JButton("PROCESS SALE");
        btnUserDisplayProducts = new JButton("DISPLAY INVENTORY");
        btnUserInvoice = new JButton("GENERATE TRANSACTION INVOICE");
        btnUserSalesReport = new JButton("GENERATE SALES REPORT");
        btnUserBack = new JButton("LOGOUT");
        btnUserBack.setBackground(Color.RED);
        btnUserBack.setOpaque(true);
        //********************************************************

        //ADMIN ADD PRODUCT
        addProductFrame = new JFrame("Add Product");
        addProductFrame.setPreferredSize(new Dimension(400, 250));
        panelAddProductCenter = new JPanel();
        panelAddProductSouth = new JPanel();
        lblBarcode = new JLabel(" Barcode:");
        lblProductName = new JLabel(" Product Name:");
        lblDescription = new JLabel(" Description:");
        lblCategory = new JLabel(" Category:");
        lblQuantity = new JLabel(" Quantity:");
        lblPrice = new JLabel(" Price:");
        txtBarcode = new JTextField();
        txtProductName = new JTextField();
        txtDescription = new JTextField();
        txtQuantity = new JTextField();
        txtPrice = new JTextField();
        cmbCategory = new JComboBox(category);
        btnAddProduct = new JButton("Save");
        btnAddProductBack = new JButton("Back");
        //********************************************************

        //ADMIN ADD USER
        addUserFrame = new JFrame("Add User");
        addUserFrame.setPreferredSize(new Dimension(400, 150));
        panelAddUserCenter = new JPanel();
        panelAddUserSouth = new JPanel();
        lblUserID = new JLabel(" User ID:");
        lblUserFullName = new JLabel(" Full Name:");
        lblUserPassword = new JLabel(" Password:");
        txtUserID = new JTextField();
        txtUserFullName = new JTextField();
        txtUserPassword = new JTextField();
        btnAddUser = new JButton("Save");
        btnAddUserBack = new JButton("Back");
        //********************************************************

        //ADMIN UPDATE PRODUCT
        updateProducFrame = new JFrame("Update Product");
        updateProducFrame.setPreferredSize(new Dimension(400, 300));
        panelUpdateProductCenter = new JPanel();
        panelProductUserSouth = new JPanel();
        lblUpdateBarcodeSelection = new JLabel(" Select Product to Update:");
        lblUpdateProductName = new JLabel(" Product Name:");
        lblUpdateDescription = new JLabel(" Description:");
        lblUpdateCategory = new JLabel(" Category:");
        lblUpdateQuantity = new JLabel(" Quantity:");
        lblUpdatePrice = new JLabel(" Price:");
        txtUpdateProductName = new JTextField();
        txtUpdateDescription = new JTextField();
        txtUpdateQuatity = new JTextField();
        txtUpdatePrice = new JTextField();
        cmbUpdateBarcodeSelection = new JComboBox();
        cmbUpdateCategory = new JComboBox(category);
        btnUpdateProductSave = new JButton("Update");
        btnUpdateProductBack = new JButton("Back");
        //********************************************************

        //ADMIN UPDATE USER
        updateUserFrame = new JFrame("Update User");
        updateUserFrame.setPreferredSize(new Dimension(400, 200));
        panelUpdateUserCenter = new JPanel();
        panelUpdateUserSouth = new JPanel();
        lblUpdateUserIDSelection = new JLabel(" Select User to Update:");
        lblUpdateUserFullName = new JLabel(" Full Name:");
        lblUpdateUserPassword = new JLabel(" Password:");
        lblUpdateUserInactive = new JLabel(" User Inactive:");
        txtUpdateUserFullName = new JTextField();
        txtUpdateUserPassword = new JTextField();
        chkUpdateUserInactive = new JCheckBox();
        cmbUpdateUserIDSelection = new JComboBox();
        btnUpdateUserSave = new JButton("Update");
        btnUpdateUserBack = new JButton("Back");
        //********************************************************

        //USER ADD CUSTOMER
        addCustomerFrame = new JFrame("Add Customer");
        addCustomerFrame.setPreferredSize(new Dimension(400, 150));
        panelAddCustomerCenter = new JPanel();
        panelAddCustomerSouth = new JPanel();
        lblAddCustomerID = new JLabel(" Customer ID:");
        lblAddCustomerFullName = new JLabel(" Full Name:");
        lblAddCustomerPhoneNumber = new JLabel(" Phone Number:");
        txtAddCustomerID = new JTextField();
        txtAddCustomerFullName = new JTextField();
        txtAddCustomerPhoneNumber = new JTextField();
        btnAddCustomer = new JButton("Save");
        btnAddCustomerBack = new JButton("Back");
        //********************************************************

        //USER PROCESS SALE
        processSaleFrame = new JFrame("Process Sale");
        processSaleFrame.setPreferredSize(new Dimension(400, 250));
        panelProcessSaleCenter = new JPanel();
        panelProcessSaleSouth = new JPanel();
        lblProcessSaleCustomer = new JLabel(" Select Customer:");
        lblProcessSaleProduct = new JLabel(" Select Product:");
        lblProcessSaleBarcode = new JLabel(" Product Barcode:");
        lblProcessSaleCurrentQuantity = new JLabel(" Current Quantity In Stock:");
        lblProcessSaleOrderQuantity = new JLabel(" Quantity Ordered/Bought:");
        lblProcessSalePrice = new JLabel(" Product Price:");
        txtProcessSaleBarcode = new JTextField();
        txtProcessSalePrice = new JTextField();
        txtProcessSaleCurrentQuantity = new JTextField();
        txtProcessSaleOrderQuantity = new JTextField();
        cmbProcessSaleCustomer = new JComboBox();
        cmbProcessSaleProduct = new JComboBox();
        btnProcessSaleSave = new JButton("Process Sale");
        btnProcessSaleBack = new JButton("Back");

        //********************************************************
        //DISPLAY INVENTORY
        displayInventoryFrame = new JFrame("Display Inventory");
        panelDisplayInventoryCenter = new JPanel();
//        panelDisplayInventorySouth = new JPanel();
        tableModelInventory = new DefaultTableModel();
        tblDisplayInventory = new JTable(tableModelInventory);
//        btnsearchInventory = new JButton("Search");
//        txtsearchInventory = new JTextField();
        //********************************************************

        //DISPLAY CUSTOMER INVOICE
        customerInvoiceFrame = new JFrame("Generate Invoice");
        panelCustomerInvoiceCenter = new JPanel();
        panelCustomerInvoiceSouth = new JPanel();
        lblCustomerInvoiceSelectCustomerId = new JLabel(" Select customer:");
        cmbCustomerInvoiceSelectCustomerId = new JComboBox();
        btnCustomerInvoiceGenerate = new JButton("Generate Invoice");
        btnCustomerInvoiceBack = new JButton("Back");
        //********************************************************

    }

    public void runClientGui() {

        //LOGIN GUI
        loginFrame.add(panelLoginCenter, BorderLayout.CENTER);
        loginFrame.add(panelLoginSouth, BorderLayout.SOUTH);
        panelLoginCenter.setLayout(new GridLayout(2, 2));
        panelLoginSouth.setLayout(new GridLayout(1, 2));
        panelLoginCenter.add(lblIdLogin);
        panelLoginCenter.add(txtIdLogin);
        panelLoginCenter.add(lblPasswordLogin);
        panelLoginCenter.add(txtPasswordLogin);
        panelLoginSouth.add(btnAdminLogin);
        panelLoginSouth.add(btnUserLogin);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
        btnAdminLogin.addActionListener(this);
        btnUserLogin.addActionListener(this);
        loginFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //********************************************************

        //ADMIN GUI
        adminFrame.add(panelAdminCenter, BorderLayout.CENTER);
        panelAdminCenter.setLayout(new GridLayout(5, 1));
        panelAdminCenter.add(btnAdminAddProduct);
        panelAdminCenter.add(btnAdminAddUser);
        panelAdminCenter.add(btnAdminUpdateProduct);
        panelAdminCenter.add(btnAdminUpdateUser);
        panelAdminCenter.add(btnAdminBack);
        btnAdminAddProduct.addActionListener(this);
        btnAdminAddUser.addActionListener(this);
        btnAdminUpdateProduct.addActionListener(this);
        btnAdminUpdateUser.addActionListener(this);
        btnAdminBack.addActionListener(this);
        adminFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //********************************************************

        //USER GUI
        userFrame.add(panelUserCenter, BorderLayout.CENTER);
        panelUserCenter.setLayout(new GridLayout(6, 1));
        panelUserCenter.add(btnUserAddCustomer);
        panelUserCenter.add(btnUserCustomerSale);
        panelUserCenter.add(btnUserDisplayProducts);
        panelUserCenter.add(btnUserInvoice);
        panelUserCenter.add(btnUserSalesReport);
        panelUserCenter.add(btnUserBack);
        btnUserAddCustomer.addActionListener(this);
        btnUserCustomerSale.addActionListener(this);
        btnUserDisplayProducts.addActionListener(this);
        btnUserInvoice.addActionListener(this);
        btnUserSalesReport.addActionListener(this);
        btnUserBack.addActionListener(this);
        userFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //********************************************************

        //ADMIN ADD PRODUCT
        addProductFrame.add(panelAddProductCenter, BorderLayout.CENTER);
        addProductFrame.add(panelAddProductSouth, BorderLayout.SOUTH);
        panelAddProductCenter.setLayout(new GridLayout(6, 2));
        panelAddProductSouth.setLayout(new GridLayout(1, 2));
        panelAddProductCenter.add(lblBarcode);
        panelAddProductCenter.add(txtBarcode);
        panelAddProductCenter.add(lblProductName);
        panelAddProductCenter.add(txtProductName);
        panelAddProductCenter.add(lblDescription);
        panelAddProductCenter.add(txtDescription);
        panelAddProductCenter.add(lblCategory);
        panelAddProductCenter.add(cmbCategory);
        panelAddProductCenter.add(lblQuantity);
        panelAddProductCenter.add(txtQuantity);
        panelAddProductCenter.add(lblPrice);
        panelAddProductCenter.add(txtPrice);
        panelAddProductSouth.add(btnAddProduct);
        panelAddProductSouth.add(btnAddProductBack);
        btnAddProduct.addActionListener(this);
        btnAddProductBack.addActionListener(this);
        addProductFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //********************************************************

        //ADMIN ADD USER
        addUserFrame.add(panelAddUserCenter, BorderLayout.CENTER);
        addUserFrame.add(panelAddUserSouth, BorderLayout.SOUTH);
        panelAddUserCenter.setLayout(new GridLayout(3, 2));
        panelAddUserSouth.setLayout(new GridLayout(1, 2));
        panelAddUserCenter.add(lblUserID);
        panelAddUserCenter.add(txtUserID);
        panelAddUserCenter.add(lblUserFullName);
        panelAddUserCenter.add(txtUserFullName);
        panelAddUserCenter.add(lblUserPassword);
        panelAddUserCenter.add(txtUserPassword);
        panelAddUserSouth.add(btnAddUser);
        panelAddUserSouth.add(btnAddUserBack);
        btnAddUser.addActionListener(this);
        btnAddUserBack.addActionListener(this);
        addUserFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //********************************************************

        //ADMIN UPDATE PRODUCT
        updateProducFrame.add(panelUpdateProductCenter, BorderLayout.CENTER);
        updateProducFrame.add(panelProductUserSouth, BorderLayout.SOUTH);
        panelUpdateProductCenter.setLayout(new GridLayout(6, 2));
        panelProductUserSouth.setLayout(new GridLayout(1, 2));
        panelUpdateProductCenter.add(lblUpdateBarcodeSelection);
        panelUpdateProductCenter.add(cmbUpdateBarcodeSelection);
        panelUpdateProductCenter.add(lblUpdateProductName);
        panelUpdateProductCenter.add(txtUpdateProductName);
        panelUpdateProductCenter.add(lblUpdateDescription);
        panelUpdateProductCenter.add(txtUpdateDescription);
        panelUpdateProductCenter.add(lblUpdateCategory);
        panelUpdateProductCenter.add(cmbUpdateCategory);
        panelUpdateProductCenter.add(lblUpdateQuantity);
        panelUpdateProductCenter.add(txtUpdateQuatity);
        panelUpdateProductCenter.add(lblUpdatePrice);
        panelUpdateProductCenter.add(txtUpdatePrice);
        panelProductUserSouth.add(btnUpdateProductSave);
        panelProductUserSouth.add(btnUpdateProductBack);
        btnUpdateProductSave.addActionListener(this);
        btnUpdateProductBack.addActionListener(this);
        cmbUpdateBarcodeSelection.addActionListener(this);
        updateProducFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //********************************************************

        //ADMIN UPDATE USER
        updateUserFrame.add(panelUpdateUserCenter, BorderLayout.CENTER);
        updateUserFrame.add(panelUpdateUserSouth, BorderLayout.SOUTH);
        panelUpdateUserCenter.setLayout(new GridLayout(4, 2));
        panelUpdateUserSouth.setLayout(new GridLayout(1, 2));
        panelUpdateUserCenter.add(lblUpdateUserIDSelection);
        panelUpdateUserCenter.add(cmbUpdateUserIDSelection);
        panelUpdateUserCenter.add(lblUpdateUserFullName);
        panelUpdateUserCenter.add(txtUpdateUserFullName);
        panelUpdateUserCenter.add(lblUpdateUserPassword);
        panelUpdateUserCenter.add(txtUpdateUserPassword);
        panelUpdateUserCenter.add(lblUpdateUserInactive);
        panelUpdateUserCenter.add(chkUpdateUserInactive);
        panelUpdateUserSouth.add(btnUpdateUserSave);
        panelUpdateUserSouth.add(btnUpdateUserBack);
        btnUpdateUserSave.addActionListener(this);
        btnUpdateUserBack.addActionListener(this);
        cmbUpdateUserIDSelection.addActionListener(this);
        updateUserFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //********************************************************

        //USER ADD CUSTOMER
        addCustomerFrame.add(panelAddCustomerCenter, BorderLayout.CENTER);
        addCustomerFrame.add(panelAddCustomerSouth, BorderLayout.SOUTH);
        panelAddCustomerCenter.setLayout(new GridLayout(3, 2));
        panelAddCustomerSouth.setLayout(new GridLayout(1, 2));
        panelAddCustomerCenter.add(lblAddCustomerID);
        panelAddCustomerCenter.add(txtAddCustomerID);
        panelAddCustomerCenter.add(lblAddCustomerFullName);
        panelAddCustomerCenter.add(txtAddCustomerFullName);
        panelAddCustomerCenter.add(lblAddCustomerPhoneNumber);
        panelAddCustomerCenter.add(txtAddCustomerPhoneNumber);
        panelAddCustomerSouth.add(btnAddCustomer);
        panelAddCustomerSouth.add(btnAddCustomerBack);
        btnAddCustomer.addActionListener(this);
        btnAddCustomerBack.addActionListener(this);
        addCustomerFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //********************************************************

        //USER PROCESS SALE
        processSaleFrame.add(panelProcessSaleCenter, BorderLayout.CENTER);
        processSaleFrame.add(panelProcessSaleSouth, BorderLayout.SOUTH);
        panelProcessSaleCenter.setLayout(new GridLayout(6, 2));
        panelProcessSaleSouth.setLayout(new GridLayout(1, 2));
        panelProcessSaleCenter.add(lblProcessSaleCustomer);
        panelProcessSaleCenter.add(cmbProcessSaleCustomer);
        panelProcessSaleCenter.add(lblProcessSaleProduct);
        panelProcessSaleCenter.add(cmbProcessSaleProduct);
        panelProcessSaleCenter.add(lblProcessSaleBarcode);
        panelProcessSaleCenter.add(txtProcessSaleBarcode);
        panelProcessSaleCenter.add(lblProcessSalePrice);
        panelProcessSaleCenter.add(txtProcessSalePrice);
        panelProcessSaleCenter.add(lblProcessSaleCurrentQuantity);
        panelProcessSaleCenter.add(txtProcessSaleCurrentQuantity);
        panelProcessSaleCenter.add(lblProcessSaleOrderQuantity);
        panelProcessSaleCenter.add(txtProcessSaleOrderQuantity);
        panelProcessSaleSouth.add(btnProcessSaleSave);
        panelProcessSaleSouth.add(btnProcessSaleBack);
        btnProcessSaleSave.addActionListener(this);
        btnProcessSaleBack.addActionListener(this);
        cmbProcessSaleProduct.addActionListener(this);
        cmbProcessSaleCustomer.addActionListener(this);
        txtProcessSaleBarcode.setEditable(false);
        txtProcessSaleBarcode.setBackground(new Color(235, 235, 235));
        txtProcessSalePrice.setEditable(false);
        txtProcessSalePrice.setBackground(new Color(235, 235, 235));
        txtProcessSaleCurrentQuantity.setEditable(false);
        txtProcessSaleCurrentQuantity.setBackground(new Color(235, 235, 235));
        processSaleFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //********************************************************

        //DISPLAY INVENTORY
        displayInventoryFrame.add(panelDisplayInventoryCenter, BorderLayout.CENTER);
//        displayInventoryFrame.add(panelDisplayInventorySouth, BorderLayout.SOUTH);
//        panelDisplayInventorySouth.setLayout(new GridLayout(1, 2));
//        panelDisplayInventorySouth.add(btnsearchInventory);
//        panelDisplayInventorySouth.add(txtsearchInventory);
        panelDisplayInventoryCenter.add(new JScrollPane(tblDisplayInventory));
        tableModelInventory.addColumn("Barcode");
        tableModelInventory.addColumn("Product Name");
        tableModelInventory.addColumn("In Stock");
        TableColumnModel columnModelInventory = tblDisplayInventory.getColumnModel();
        columnModelInventory.getColumn(0).setPreferredWidth(150);
        columnModelInventory.getColumn(1).setPreferredWidth(150);
        columnModelInventory.getColumn(2).setPreferredWidth(150);
        tblDisplayInventory.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        btnsearchInventory.addActionListener(this);
        //********************************************************

        //DISPLAY CUSTOMER INVOICE
        customerInvoiceFrame.add(panelCustomerInvoiceCenter, BorderLayout.CENTER);
        customerInvoiceFrame.add(panelCustomerInvoiceSouth, BorderLayout.SOUTH);
        panelCustomerInvoiceCenter.setLayout(new GridLayout(1, 2));
        panelCustomerInvoiceSouth.setLayout(new GridLayout(1, 2));
        panelCustomerInvoiceCenter.add(lblCustomerInvoiceSelectCustomerId);
        panelCustomerInvoiceCenter.add(cmbCustomerInvoiceSelectCustomerId);
        panelCustomerInvoiceSouth.add(btnCustomerInvoiceGenerate);
        panelCustomerInvoiceSouth.add(btnCustomerInvoiceBack);
        btnCustomerInvoiceGenerate.addActionListener(this);
        btnCustomerInvoiceBack.addActionListener(this);
        cmbCustomerInvoiceSelectCustomerId.addActionListener(this);
        customerInvoiceFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //********************************************************

    }

    //BUFFER WRITER CUSTOMER INVOICE
    public void customerInvoice(String string) {
        transactionsByCustomerId = request.getTransactionsByCustomer(string);
        if (transactionsByCustomerId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No Sales Records Found For This Customer.");
        } else {
            try {
                bufferw = new BufferedWriter(new FileWriter("Customer Invoice"));
                bufferw.write("\nSTRICTLY STATIONERY\n");
                bufferw.write("CUSTOMER ID: " + transactionsByCustomerId.get(0).getCustomerID() + "\n\n");
                bufferw.write("==================================INVOICE==================================\n");
                bufferw.write(String.format("%-25s%-20s%-20s%-20s\n", "PRODUCT NAME", "BARCODE", "QUANTITY", "PRICE"));
                bufferw.write("===========================================================================\n");
                for (int i = 0; i < transactionsByCustomerId.size(); i++) {
                    bufferw.write(String.format("%-25s%-20s%-20dR%-20.2f\n", transactionsByCustomerId.get(i).getProductName(), transactionsByCustomerId.get(i).getBarcode(),
                            transactionsByCustomerId.get(i).getQuantitySold(), transactionsByCustomerId.get(i).getPrice()));
                }
                JOptionPane.showMessageDialog(null, "Invoice Generated Successfully.");
            } catch (IOException ex) {
                System.out.println("Buffer Writer: " + ex);
            } finally {
                try {
                    bufferw.close();
                } catch (IOException ex) {
                    System.out.println("Buffer writer close: " + ex);
                }
            }
        }

        //PDF CODE FOR CUSTOMER INVOICE******(INVOICE GENERATED BUT COLUMNS DID NOT ALLIGN)
//        System.out.println(transactionsByCustomerIdPdf);
//        Document invoice = new Document();
//        try {
//            
//            PdfWriter writer = PdfWriter.getInstance(invoice, new FileOutputStream("Customer Invoice 1"));
//            invoice.open();
//            invoice.add(new Paragraph("STRICTLY STATIONAIRY"));
//            invoice.add(new Paragraph("Customer ID: " + transactionsByCustomerIdPdf.get(0).getCustomerID()));
//            invoice.add(new Paragraph("================================INVOICE================================"));
//            String columnNames = String.format("%-27s%-27s%-27s%-27s","PRODUCT NAME","BARCODE","QUANTITY","PRICE");
//            invoice.add(new Paragraph(columnNames));
//            invoice.add(new Paragraph("======================================================================="));
//            
//            PdfPTable pdfPTable = new PdfPTable(4);
//            for (int i = 0; i < transactionsByCustomerIdPdf.size(); i++){
//                PdfPCell pdfCell1 = new PdfPCell(new Paragraph(""+transactionsByCustomerIdPdf.get(i).getProductName()));
////                String products = String.format("%-27s%-27s%-27d%-27.2f",transactionsByCustomerIdPdf.get(i).getProductName(),transactionsByCustomerIdPdf.get(i).getBarcode(),transactionsByCustomerIdPdf.get(i).getQuantitySold(),transactionsByCustomerIdPdf.get(i).getPrice());
////                invoice.add(new Paragraph(products));
//                pdfPTable.addCell(pdfCell1);
//            }
//            invoice.add(pdfPTable);
//            
//            invoice.close();
//            writer.close();
//
//        } catch (DocumentException | FileNotFoundException e) {
//            e.getMessage();
//        }
    }
    //********************************************************

    //BUFFER WRITER SALES REPORT
    public void salesReport() {
        transactions = request.getAllTransactions();
        double totalPrice = 0;

        try {
            bufferw = new BufferedWriter(new FileWriter("Sales Report"));
            bufferw.write("\nSTRICTLY STATIONERY\n\n");
            bufferw.write("================================SALES REPORT=================================\n");
            bufferw.write(String.format("%-25s%-20s%-20s%-20s\n", "PRODUCT NAME", "BARCODE", "QUANTITY", "PRICE"));
            bufferw.write("=============================================================================\n");

            for (int i = 0; i < transactions.size(); i++) {
                bufferw.write(String.format("%-25s%-20s%-20dR%-20.2f\n", transactions.get(i).getProductName(), transactions.get(i).getBarcode(),
                        transactions.get(i).getQuantitySold(), transactions.get(i).getPrice()));
                totalPrice = totalPrice + transactions.get(i).getPrice();
            }

            bufferw.write(String.format("\nTOTAL: R%.2f", totalPrice));
            JOptionPane.showMessageDialog(null, "Sales Report Generated Successfully.");
        } catch (IOException ex) {
            System.out.println("Buffer Writer: " + ex);
        } finally {
            try {
                bufferw.close();
            } catch (IOException ex) {
                System.out.println("Buffer writer close: " + ex);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdminLogin) {                                   //Login validation and access (Admin).
            admins = request.getAdmins();
            boolean userLoginMatch = false;
            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).getUserID().equals(txtIdLogin.getText()) && admins.get(i).getPassword().equals(txtPasswordLogin.getText())) {
                    userLoginMatch = true;
                }
            }
            if (userLoginMatch == true) {
                loginFrame.dispose();
                adminFrame.pack();
                adminFrame.setLocationRelativeTo(null);
                adminFrame.setVisible(true);
                txtIdLogin.setText(null);
                txtPasswordLogin.setText(null);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Admin ID or Password!");
            }

        } else if (e.getSource() == btnUserLogin) {                             //Login validation and access (User).
            users = request.getUsers();
            boolean userLoginMatch = false;
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUserID().equals(txtIdLogin.getText()) && users.get(i).getPassword().equals(txtPasswordLogin.getText())) {
                    userLoginMatch = true;
                }
            }
            if (userLoginMatch == true) {
                loginFrame.dispose();
                userFrame.pack();
                userFrame.setLocationRelativeTo(null);
                userFrame.setVisible(true);
                txtIdLogin.setText(null);
                txtPasswordLogin.setText(null);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid User ID or Password!");
            }

        } else if (e.getSource() == btnAdminBack) {
            adminFrame.dispose();
            loginFrame.pack();
            loginFrame.setLocationRelativeTo(null);
            loginFrame.setVisible(true);

        } else if (e.getSource() == btnUserBack) {
            userFrame.dispose();
            loginFrame.pack();
            loginFrame.setLocationRelativeTo(null);
            loginFrame.setVisible(true);

        } else if (e.getSource() == btnAdminAddProduct) {
            adminFrame.dispose();
            addProductFrame.pack();
            addProductFrame.setLocationRelativeTo(null);
            addProductFrame.setVisible(true);

        } else if (e.getSource() == btnAddProduct) {                            //BUTTON TO ADD INVENTORY AND VALIDATE BARCODE.
            if (txtBarcode.getText().isEmpty() || txtProductName.getText().isEmpty() || txtDescription.getText().isEmpty() || (cmbCategory.getSelectedIndex() == 0) || txtQuantity.getText().isEmpty() || txtPrice.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Empty Fields Exist or category not selected\nPlease Populate All Fields and make all selections.");
            } else {
                boolean barcodeExists = request.validateBarcode(txtBarcode.getText());
                if (barcodeExists == false) {
                    String Barcode = txtBarcode.getText();
                    String productName = txtProductName.getText();
                    String description = txtDescription.getText();
                    String category = cmbCategory.getSelectedItem().toString();
                    int quantity = Integer.parseInt(txtQuantity.getText());
                    double price = Double.parseDouble(txtPrice.getText());
                    boolean instock;
                    if (quantity > 0) {
                        instock = true;
                    } else {
                        instock = false;
                    }
                    boolean inactive = false;
                    boolean response;
                    inventory = new Inventory(Barcode, productName, description, category, quantity, price, instock);
                    response = request.addInventory(inventory);
                    if (response == true) {
                        JOptionPane.showMessageDialog(null, "Product Added Successfully.");
                        txtBarcode.setText(null);
                        txtProductName.setText(null);
                        txtDescription.setText(null);
                        cmbCategory.setSelectedItem(0);
                        txtQuantity.setText(null);
                        txtPrice.setText(null);
                    }
                } else if (barcodeExists == true) {
                    JOptionPane.showMessageDialog(null, "Product Barcode already exists!\nEnter new barcode");
                }
            }

        } else if (e.getSource() == btnAddProductBack) {
            txtBarcode.setText(null);
            txtProductName.setText(null);
            txtDescription.setText(null);
            cmbCategory.setSelectedItem(0);
            txtQuantity.setText(null);
            txtPrice.setText(null);
            addProductFrame.dispose();
            adminFrame.pack();
            adminFrame.setLocationRelativeTo(null);
            adminFrame.setVisible(true);

        } else if (e.getSource() == btnAdminAddUser) {
            adminFrame.dispose();
            addUserFrame.pack();
            addUserFrame.setLocationRelativeTo(null);
            addUserFrame.setVisible(true);

        } else if (e.getSource() == btnAddUserBack) {
            txtUserID.setText(null);
            txtUserFullName.setText(null);
            txtUserPassword.setText(null);
            addUserFrame.dispose();
            adminFrame.pack();
            adminFrame.setLocationRelativeTo(null);
            adminFrame.setVisible(true);

        } else if (e.getSource() == btnAdminUpdateProduct) {                    //UPDATE COMBOBOX FOR UPDATE INVENTORY.
            inventorys = request.getInventory();
            cmbUpdateBarcodeSelection.removeAllItems();
            for (int i = 0; i < inventorys.size(); i++) {
                String barcodes = String.valueOf(inventorys.get(i).getBarcode());
                cmbUpdateBarcodeSelection.addItem(barcodes);
            }
            adminFrame.dispose();
            updateProducFrame.pack();
            updateProducFrame.setLocationRelativeTo(null);
            updateProducFrame.setVisible(true);

        } else if (e.getSource() == cmbUpdateBarcodeSelection) {                //Update fields according to combobox selection update inventory. 
            int indexBarcodeSelected = cmbUpdateBarcodeSelection.getSelectedIndex();
            if (indexBarcodeSelected != -1) {
                txtUpdateProductName.setText(inventorys.get(indexBarcodeSelected).getProductName());
                txtUpdateDescription.setText(inventorys.get(indexBarcodeSelected).getDescription());
                cmbUpdateCategory.setSelectedItem(inventorys.get(indexBarcodeSelected).getCategory());
                txtUpdateQuatity.setText(String.valueOf(inventorys.get(indexBarcodeSelected).getQuantity()));
                txtUpdatePrice.setText(Double.toString(inventorys.get(indexBarcodeSelected).getPrice()));
            }

        } else if (e.getSource() == btnUpdateProductBack) {
            updateProducFrame.dispose();
            adminFrame.pack();
            adminFrame.setLocationRelativeTo(null);
            adminFrame.setVisible(true);

        } else if (e.getSource() == btnUpdateProductSave) {                     //Updates data of inventory.
            if (txtUpdateProductName.getText().isEmpty() || txtUpdateProductName.getText().isEmpty() || txtUpdateDescription.getText().isEmpty() || (cmbUpdateCategory.getSelectedIndex() == 0) || txtUpdateQuatity.getText().isEmpty() || txtUpdatePrice.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Empty Fields Exist or category not selected\nPlease Populate All Fields and make all selections.");
            } else {
                String barcode = cmbUpdateBarcodeSelection.getSelectedItem().toString();
                String productName = txtUpdateProductName.getText();
                String description = txtUpdateDescription.getText();
                String category = cmbUpdateCategory.getSelectedItem().toString();
                int quantity = Integer.parseInt(txtUpdateQuatity.getText());
                double price = Double.parseDouble(txtUpdatePrice.getText());
                boolean instock;
                if (quantity > 0) {
                    instock = true;
                } else {
                    instock = false;
                }
                boolean response = false;
                inventory = new Inventory(barcode, productName, description, category, quantity, price, instock);
                response = request.updateInventory(inventory);
                if (response == true) {
                    JOptionPane.showMessageDialog(null, "Inventory Updated Successfully.");
                }
                inventorys = request.getInventory();
                cmbUpdateBarcodeSelection.removeAllItems();
                for (int i = 0; i < inventorys.size(); i++) {
                    String barcodes = String.valueOf(inventorys.get(i).getBarcode());
                    cmbUpdateBarcodeSelection.addItem(barcodes);
                }
            }

        } else if (e.getSource() == btnAdminUpdateUser) {                       //Update combobox for update user.
            users = request.getUsers();
            cmbUpdateUserIDSelection.removeAllItems();
            for (int i = 0; i < users.size(); i++) {
                String userIdNumbers = String.valueOf(users.get(i).getUserID());
                cmbUpdateUserIDSelection.addItem(userIdNumbers);
            }
            adminFrame.dispose();
            updateUserFrame.pack();
            updateUserFrame.setLocationRelativeTo(null);
            updateUserFrame.setVisible(true);

        } else if (e.getSource() == cmbUpdateUserIDSelection) {                 //Update fields according to combobox selection update user.      
            int indexUserSelected = cmbUpdateUserIDSelection.getSelectedIndex();
            if (indexUserSelected != -1) {
                txtUpdateUserFullName.setText(users.get(indexUserSelected).getFullName());
                txtUpdateUserPassword.setText(users.get(indexUserSelected).getPassword());
                chkUpdateUserInactive.setSelected(users.get(indexUserSelected).isInactive());
            }

        } else if (e.getSource() == btnUpdateUserBack) {
            updateUserFrame.dispose();
            adminFrame.pack();
            adminFrame.setLocationRelativeTo(null);
            adminFrame.setVisible(true);

        } else if (e.getSource() == btnUpdateUserSave) {                        //BUTTON TO UPDATE A USER.
            if (txtUpdateUserFullName.getText().isEmpty() || txtUpdateUserPassword.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Empty Fields Exist\nPlease Populate All Fields.");
            } else {
                String userID = cmbUpdateUserIDSelection.getSelectedItem().toString();
                String userFullName = txtUpdateUserFullName.getText();
                String userPassword = txtUpdateUserPassword.getText();
                boolean inactive = chkUpdateUserInactive.isSelected();
                boolean response = false;
                user = new User(userID, userFullName, userPassword, inactive);
                response = request.updateUser(user);
                if (response == true) {
                    JOptionPane.showMessageDialog(null, "User Updated Successfully.");
                }
                users = request.getUsers();
                cmbUpdateUserIDSelection.removeAllItems();
                for (int i = 0; i < users.size(); i++) {
                    String userIdNumbers = String.valueOf(users.get(i).getUserID());
                    cmbUpdateUserIDSelection.addItem(userIdNumbers);
                }
            }

        } else if (e.getSource() == btnAddUser) {                               //BUTTON TO ADD AND VALIDATE USER.
            if (txtUserID.getText().isEmpty() || txtUserFullName.getText().isEmpty() || txtUserPassword.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Empty Fields Exist\nPlease Populate All Fields.");
            } else {
                boolean idExists = request.validateUserId(txtUserID.getText());
                if (idExists == false) {
                    String userID = txtUserID.getText();
                    String userFullName = txtUserFullName.getText();
                    String userPassword = txtUserPassword.getText();
                    boolean inactive = false;
                    boolean response;
                    user = new User(userID, userFullName, userPassword, inactive);
                    response = request.addUser(user);
                    if (response == true) {
                        JOptionPane.showMessageDialog(null, "User Added Successfully.");
                        txtUserID.setText(null);
                        txtUserFullName.setText(null);
                        txtUserPassword.setText(null);
                    }
                } else if (idExists == true) {
                    JOptionPane.showMessageDialog(null, "User ID already exists!\nEnter new User ID");
                }
            }

        } else if (e.getSource() == btnUserAddCustomer) {
            userFrame.dispose();
            addCustomerFrame.pack();
            addCustomerFrame.setLocationRelativeTo(null);
            addCustomerFrame.setVisible(true);

        } else if (e.getSource() == btnAddCustomer) {                           //BUTTON TO ADD CUSTOMER AND VALIDATE CUSTOMER ID.
            if (txtAddCustomerID.getText().isEmpty() || txtAddCustomerFullName.getText().isEmpty() || txtAddCustomerPhoneNumber.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Empty Fields Exist\nPlease Populate All Fields.");
            } else {
                boolean idExists = request.validateCustomerId(txtAddCustomerID.getText());
                if (idExists == false) {
                    String customerID = txtAddCustomerID.getText();
                    String customerFullName = txtAddCustomerFullName.getText();
                    String customerPassword = txtAddCustomerPhoneNumber.getText();
                    boolean response;
                    customer = new Customer(customerID, customerFullName, customerPassword);
                    response = request.addCustomer(customer);
                    if (response == true) {
                        JOptionPane.showMessageDialog(null, "Customer Added Successfully.");
                        txtAddCustomerID.setText(null);
                        txtAddCustomerFullName.setText(null);
                        txtAddCustomerPhoneNumber.setText(null);
                    }
                } else if (idExists == true) {
                    JOptionPane.showMessageDialog(null, "Customer ID already exists!\nEnter new User ID");
                }
            }

        } else if (e.getSource() == btnUserCustomerSale) {                      //Populate comboboxes on process sale frame.
            customers = request.getCustomers();
            inventorysInStock = request.getInventoryInStock();
            cmbProcessSaleCustomer.removeAllItems();
            cmbProcessSaleProduct.removeAllItems();
            for (int i = 0; i < customers.size(); i++) {
                String customerIdNumbers = customers.get(i).getCustomerID();
                cmbProcessSaleCustomer.addItem(customerIdNumbers);
            }
            for (int i = 0; i < inventorysInStock.size(); i++) {
                String productNames = inventorysInStock.get(i).getProductName();
                cmbProcessSaleProduct.addItem(productNames);
            }
            userFrame.dispose();
            processSaleFrame.pack();
            processSaleFrame.setLocationRelativeTo(null);
            processSaleFrame.setVisible(true);

        } else if (e.getSource() == cmbProcessSaleProduct) {                    //Update fields as per combobox on process sale screen.
            int indexProductSelected = cmbProcessSaleProduct.getSelectedIndex();
            if (indexProductSelected != -1) {
                txtProcessSaleBarcode.setText(inventorysInStock.get(indexProductSelected).getBarcode());
                txtProcessSalePrice.setText(String.valueOf(inventorysInStock.get(indexProductSelected).getPrice()));
                txtProcessSaleCurrentQuantity.setText(String.valueOf(inventorysInStock.get(indexProductSelected).getQuantity()));
            }

        } else if (e.getSource() == btnProcessSaleSave) {                       //BUTTON TO PROCESS SALE AND ADD TRANSACTION.
            if (txtProcessSaleOrderQuantity.getText().matches(".*\\d.*")) {
                String saleId = ad.generateRandomID();
                String customerID = cmbProcessSaleCustomer.getSelectedItem().toString();
                String productName = cmbProcessSaleProduct.getSelectedItem().toString();
                String barcode = txtProcessSaleBarcode.getText();
                double price = (Integer.parseInt(txtProcessSaleOrderQuantity.getText())) * (Double.parseDouble(txtProcessSalePrice.getText()));
                int quantitySold = Integer.parseInt(txtProcessSaleOrderQuantity.getText());
                boolean stockFalse = false;
                int inventoryZero = 0;
                boolean responseTransaction;
                boolean responseInventoryUpdate;
                transaction = new Transaction(saleId, customerID, productName, barcode, price, quantitySold);

                if (Integer.parseInt(txtProcessSaleOrderQuantity.getText()) == Integer.parseInt(txtProcessSaleCurrentQuantity.getText())) {
                    responseTransaction = request.addTransaction(transaction);
                    responseInventoryUpdate = request.updateInventoryToZero(inventoryZero, stockFalse, barcode);
                    if (responseTransaction == true && responseInventoryUpdate == true) {
                        JOptionPane.showMessageDialog(null, "Transaction Processed Successfully.");
                        txtProcessSaleOrderQuantity.setText(null);
                    }
                } else if (Integer.parseInt(txtProcessSaleOrderQuantity.getText()) > Integer.parseInt(txtProcessSaleCurrentQuantity.getText())) {
                    JOptionPane.showMessageDialog(null, "Insufficient Stock Available.");

                } else if (Integer.parseInt(txtProcessSaleOrderQuantity.getText()) < Integer.parseInt(txtProcessSaleCurrentQuantity.getText())) {
                    responseTransaction = request.addTransaction(transaction);
                    responseInventoryUpdate = request.updateInventoryAfterSale((Integer.parseInt(txtProcessSaleCurrentQuantity.getText()) - Integer.parseInt(txtProcessSaleOrderQuantity.getText())), barcode);
                    if (responseTransaction == true && responseInventoryUpdate == true) {
                        JOptionPane.showMessageDialog(null, "Transaction Processed Successfully.");
                        txtProcessSaleOrderQuantity.setText(null);
                    }
                }
                inventorysInStock = request.getInventoryInStock();
                cmbProcessSaleProduct.removeAllItems();
                for (int i = 0; i < inventorysInStock.size(); i++) {
                    String productNames = inventorysInStock.get(i).getProductName();
                    cmbProcessSaleProduct.addItem(productNames);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Enter a Valid Quantity");
            }

        } else if (e.getSource() == btnProcessSaleBack) {
            txtProcessSaleOrderQuantity.setText(null);
            processSaleFrame.dispose();
            userFrame.pack();
            userFrame.setLocationRelativeTo(null);
            userFrame.setVisible(true);

        } else if (e.getSource() == btnAddCustomerBack) {
            txtAddCustomerID.setText(null);
            txtAddCustomerFullName.setText(null);
            txtAddCustomerPhoneNumber.setText(null);
            addCustomerFrame.dispose();
            userFrame.pack();
            userFrame.setLocationRelativeTo(null);
            userFrame.setVisible(true);

        } else if (e.getSource() == btnUserDisplayProducts) {                   //DISPLAY PRODUCTS TABLE AND CLONE TABLE.
            inventorys = request.getInventory();
            tblDisplayInventory.setModel(tableModelInventory);
            tableModelInventory = (DefaultTableModel) tblDisplayInventory.getModel();
            tableModelInventory.setRowCount(0);

            for (int i = 0; i < inventorys.size(); i++) {
                String barcode = inventorys.get(i).getBarcode();
                String productName = inventorys.get(i).getProductName();
                boolean inStock = inventorys.get(i).isInStock();

                Object[] inventoryData = {barcode, productName, inStock};
                tableModelInventory.addRow(inventoryData);
                displayInventoryFrame.pack();
                displayInventoryFrame.setLocationRelativeTo(null);
                displayInventoryFrame.setVisible(true);
            }
            originalInventoryTable = (Vector) ((DefaultTableModel) tblDisplayInventory.getModel()).getDataVector().clone();
        } else if (e.getSource() == btnUserInvoice) {
            customers = request.getCustomers();
            cmbCustomerInvoiceSelectCustomerId.removeAllItems();
            for (int i = 0; i < customers.size(); i++) {
                String customerIdNumbers = customers.get(i).getCustomerID();
                cmbCustomerInvoiceSelectCustomerId.addItem(customerIdNumbers);
            }
            userFrame.dispose();
            customerInvoiceFrame.pack();
            customerInvoiceFrame.setLocationRelativeTo(null);
            customerInvoiceFrame.setVisible(true);

        } else if (e.getSource() == btnCustomerInvoiceBack) {
            customerInvoiceFrame.dispose();
            userFrame.pack();
            userFrame.setLocationRelativeTo(null);
            userFrame.setVisible(true);

        } else if (e.getSource() == btnCustomerInvoiceGenerate) {               //GENERATE CUSTOMER INVOICE.
            String customerIdGenerateInvoice = cmbCustomerInvoiceSelectCustomerId.getSelectedItem().toString();
            customerInvoice(customerIdGenerateInvoice);

        } else if (e.getSource() == btnUserSalesReport) {
            salesReport();

        } else if (e.getSource() == btnsearchInventory) {
            searchInventoryTable(txtsearchInventory.getText());
        }
    }

    //METHOD TO SEARCH THE INVENTORY TABLE.
    public void searchInventoryTable(String search) {

        tableModelInventory = (DefaultTableModel) tblDisplayInventory.getModel();
        tableModelInventory.setRowCount(0);
        for (Object rows : originalInventoryTable) {
            Vector rowVector = (Vector) rows;
            for (Object column : rowVector) {
                if (column.toString().toUpperCase().contains(search.toUpperCase())) {
                    tableModelInventory.addRow(rowVector);
                    break;
                }
            }
        }
    }
}
