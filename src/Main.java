import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import java.awt.*;

public class Main {

    static JFrame frame;
    static JPanel loginPanel;
    static JPanel ownerPanel;
    static JPanel productsPanel;
    static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::auth);
    }
    

    public static void auth() {

        AuthManager authManager = new AuthManager();
    
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(new Color(245, 247, 250));
    
        JPanel container = new JPanel(null);
        frame.setContentPane(container);
    
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    


        // ---------------------- LOGIN PANEL ----------------------
        loginPanel = new JPanel(new BorderLayout());
        loginPanel.setBackground(new Color(245, 247, 250));
        loginPanel.setBounds(0, 0, screen.width, screen.height);
    
        JLabel title = new JLabel("Login");
        title.setFont(new Font("SansSerif", Font.BOLD, 48));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
        loginPanel.add(title, BorderLayout.NORTH);
    
        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        Font labelFont = new Font("SansSerif", Font.PLAIN, 24);
        Dimension fieldSize = new Dimension(280, 45);
    
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(labelFont);
        center.add(userLabel, gbc);
    
        gbc.gridx = 1;
        JTextField userField = new JTextField();
        userField.setPreferredSize(fieldSize);
        userField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        userField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        center.add(userField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(labelFont);
        center.add(passLabel, gbc);
    
        gbc.gridx = 1;
        JPasswordField passField = new JPasswordField();
        passField.setPreferredSize(fieldSize);
        passField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        passField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        center.add(passField, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 2;
        JButton loginBtn = new JButton("Login");
        loginBtn.setPreferredSize(new Dimension(200, 60));
        loginBtn.setFont(new Font("SansSerif", Font.BOLD, 26));
        loginBtn.setBackground(new Color(186, 255, 200));
        loginBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        loginBtn.setFocusPainted(false);
        center.add(loginBtn, gbc);
    
        loginPanel.add(center, BorderLayout.CENTER);
    
        // ---------------------- OWNER PANEL (MOVED OUT) ----------------------
        ownerPanel = createOwnerPanel(screen);
        productsPanel = productsPanel();
    
        // ---------------------- LOGIN ACTION ----------------------
        loginBtn.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword());
    
            if (authManager.validateLogin(username, password)) {
    
                if (username.equals("admin")) {
                    loginPanel.setVisible(false);
                    ownerPanel.setVisible(true);
                }
    
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Invalid username or password",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
                passField.setText("");
            }
        });
    
        container.add(loginPanel);
        container.add(ownerPanel);
        container.add(productsPanel);

        //---------------------Testing-----------------------
        loginPanel.setVisible(false);
        ownerPanel.setVisible(true);
        

        frame.setVisible(true);
    }

    public static JPanel createOwnerPanel(Dimension screen) {

        JPanel ownerPanel = new JPanel(null);
        ownerPanel.setBackground(new Color(245, 245, 245));
        ownerPanel.setBounds(0, 0, screen.width, screen.height);
        ownerPanel.setVisible(false);
    
        // ---------------- TITLE ----------------
        JLabel title = new JLabel("Owner's Dashboard");
        title.setFont(new Font("SansSerif", Font.BOLD, 36));
        title.setBounds((screen.width / 2) - 200, 40, 400, 50);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        ownerPanel.add(title);
    
        // ---------------- LOGOUT BUTTON (TOP RIGHT) ----------------
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(screen.width - 180, 40, 120, 45);
        logoutBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        logoutBtn.setBackground(new Color(255, 120, 120));
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    
        logoutBtn.addActionListener(e -> {
            ownerPanel.setVisible(false);
            loginPanel.setVisible(true);
        });
    
        ownerPanel.add(logoutBtn);
    
        // ---------------- BUTTON STYLE ----------------
        Font btnFont = new Font("SansSerif", Font.BOLD, 20);
        Dimension btnSize = new Dimension(200, 80);
        Color btnColor = Color.white;
    
        // ---------------- INVENTORY ----------------
        JButton inventoryBtn = new JButton("Inventory");
        inventoryBtn.setFont(btnFont);
        inventoryBtn.setBounds(250, 180, btnSize.width, btnSize.height);
        inventoryBtn.setBackground(btnColor);
        ownerPanel.add(inventoryBtn);
    
        // ---------------- STAFF ----------------
        JButton staffBtn = new JButton("Staff");
        staffBtn.setFont(btnFont);
        staffBtn.setBounds(520, 180, btnSize.width, btnSize.height);
        staffBtn.setBackground(btnColor);
        ownerPanel.add(staffBtn);
    
        // ---------------- SHIFTS ----------------
        JButton shiftsBtn = new JButton("Shifts");
        shiftsBtn.setFont(btnFont);
        shiftsBtn.setBounds(790, 180, btnSize.width, btnSize.height);
        shiftsBtn.setBackground(btnColor);
        ownerPanel.add(shiftsBtn);
    
        // ---------------- PRODUCTS ----------------
        JButton productsBtn = new JButton("Products");
        productsBtn.setFont(btnFont);
        productsBtn.setBounds(250, 300, btnSize.width, btnSize.height);
        productsBtn.setBackground(btnColor);
        ownerPanel.add(productsBtn);
    
        // ---------------- SALES ----------------
        JButton salesBtn = new JButton("Sales");
        salesBtn.setFont(btnFont);
        salesBtn.setBounds(790, 300, btnSize.width, btnSize.height);
        salesBtn.setBackground(btnColor);
        ownerPanel.add(salesBtn);
    

        // ---------------- BUTTON ACTIONS ----------------
        productsBtn.addActionListener(e -> {
            ownerPanel.setVisible(false);
            productsPanel.setVisible(true);
        });


        return ownerPanel;
    }

    public static JPanel productsPanel() {
        JPanel productsPanel = new JPanel(null);
        productsPanel.setBackground(new Color(245, 245, 245));
        productsPanel.setBounds(0, 0, screen.width, screen.height);
        productsPanel.setVisible(false);

        //---------------- TITLE ----------------
        JLabel title = new JLabel("Products Management");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setBounds((screen.width / 2) - 200, 5, 400, 35);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        productsPanel.add(title);

        // ---------------- BACK BUTTON ----------------
        JButton backBtn = new JButton("Back");
        backBtn.setBounds(40, 80, 100, 35);
        backBtn.addActionListener(e -> {
            productsPanel.setVisible(false);
            ownerPanel.setVisible(true);
        });
        productsPanel.add(backBtn);

        // ---------------- HEADER COMPONENTS ----------------
        JTextField searchField = new JTextField();
        searchField.setBounds((screen.width - 775), 80, 250, 35);
        productsPanel.add(searchField);

        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds((screen.width - 525), 80, 100, 35);
        productsPanel.add(searchBtn);

        String[] sortStrings = {"Name", "Category", "Price"};
        JComboBox<String> filterCombo = new JComboBox<>(sortStrings);
        filterCombo.setBounds((screen.width - 400), 80, 150, 35);
        productsPanel.add(filterCombo);

        JButton addProductBtn = new JButton("Add Product");
        addProductBtn.setBounds((screen.width - 225), 80, 150, 35);
        productsPanel.add(addProductBtn);

        // ---------------- PRODUCTS TABLE ----------------
        
        String[] columnNames = {"Name", "Category", "Buy Price", "Sell Price", "Active", "Actions"};
        JTable productsTable = new JTable(new Object[][]{
                {"Product A", "Category 1", "$10.00", "$15.00", "Yes", "Edit/Delete"},
                {"Product B", "Category 2", "$15.00", "$20.00", "No", "Edit/Delete"},
                {"Product d", "Category 1", "$20.00", "$25.00", "Yes", "Edit/Delete"},
                {"Product e", "Category 1", "$20.00", "$25.00", "Yes", "Edit/Delete"},
                {"Product g", "Category 1", "$20.00", "$25.00", "Yes", "Edit/Delete"},
                {"Product h", "Category 1", "$20.00", "$25.00", "Yes", "Edit/Delete"},
                {"Product i", "Category 1", "$20.00", "$25.00", "Yes", "Edit/Delete"},
                {"Product j", "Category 1", "$20.00", "$25.00", "Yes", "Edit/Delete"},
                {"Product k", "Category 1", "$20.00", "$25.00", "Yes", "Edit/Delete"},
                {"Product l", "Category 1", "$20.00", "$25.00", "Yes", "Edit/Delete"},
                {"Product m", "Category 1", "$20.00", "$25.00", "Yes", "Edit/Delete"},
                {"Product n", "Category 1", "$20.00", "$25.00", "Yes", "Edit/Delete"},
                {"Product o", "Category 1", "$20.00", "$25.00", "Yes", "Edit/Delete"},
        }, columnNames);
        

        JTableHeader header = productsTable.getTableHeader();
        header.setBackground(new Color(160, 202, 222));
        header.setForeground(Color.BLACK);
        header.setFont(new Font("SansSerif", Font.BOLD, 16));

        productsTable.setDefaultRenderer(Object.class,  new DefaultTableCellRenderer() {

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        if (!isSelected) {
            if (row % 2 == 0) {
                c.setBackground(new Color(245, 245, 245)); // light
            } else {
                c.setBackground(new Color(230, 230, 230)); // darker
            }
        }

        return c;
    }
});

        JScrollPane tableScroll = new JScrollPane(productsTable);
        tableScroll.setBounds(40, 130, screen.width - 100, screen.height - 250);
        productsPanel.add(tableScroll);



        
        return productsPanel;
    }
    
}

