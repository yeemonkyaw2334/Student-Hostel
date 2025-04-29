package View;

import DBConnection.DBConfig;
import Model.StaffModel;
import Controller.StaffController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class LogInView extends JFrame {

    private StaffModel staff = new StaffModel();
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel imageLabel;

    public LogInView() {
        setTitle("Staff Login");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        // ========= Left Image Panel =========
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BorderLayout());

        imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/login 1.jpg"));
        Image img = icon.getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(imageLabel, BorderLayout.CENTER);

        // ========= Right Form Panel =========
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(245, 245, 245));
        rightPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Staff Login");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setBounds(100, 30, 200, 30);
        rightPanel.add(titleLabel);

        JLabel nameLabel = new JLabel("Staff Name:");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        nameLabel.setBounds(50, 90, 100, 25);
        rightPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(160, 90, 200, 30);
        rightPanel.add(nameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        passwordLabel.setBounds(50, 140, 100, 25);
        rightPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 140, 200, 30);
        rightPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBounds(160, 200, 200, 35);
        rightPanel.add(loginButton);

        JLabel footer = new JLabel("Student Hostel Management System");
        footer.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        footer.setBounds(50, 280, 300, 30);
        rightPanel.add(footer);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);

        // ========= Login Button Action =========
        loginButton.addActionListener((ActionEvent e) -> {
            try {
                login();
            } catch (HeadlessException | SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void login() throws HeadlessException, SQLException {
        String name = nameField.getText().trim();
        String password = new String(passwordField.getPassword());

        DBConfig dbConfig = new DBConfig();

        if (validateLogIn(name, password)) {
            JOptionPane.showMessageDialog(this, "✅ Login successful!");
            dispose(); // Close the login window
            new StaffHomePage(staff);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Invalid name or password.");
        }
    }

    private boolean validateLogIn(String name, String password) throws SQLException {
        StaffController sc = new StaffController();
        staff = sc.SelectByName(name);

        if (staff == null) {
            JOptionPane.showMessageDialog(this, "Staff does not exist or name is incorrect.",
                    "Name Error", JOptionPane.ERROR_MESSAGE);
            nameField.requestFocus();
            nameField.selectAll();
        } else {
            if (password.equals(staff.getStaffPassword())) {
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Password is incorrect.",
                        "Password Error", JOptionPane.ERROR_MESSAGE);
                passwordField.requestFocus();
                passwordField.selectAll();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LogInView());
    }
}
