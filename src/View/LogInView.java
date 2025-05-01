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
        setSize(1000, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        // ========== Left Image Panel ==========
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 600, 520); // Expanded to the right
        leftPanel.setBackground(new Color(25, 118, 210));
        leftPanel.setLayout(new BorderLayout());

        imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/login 1.jpg"));
        Image img = icon.getImage().getScaledInstance(600, 520, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(imageLabel, BorderLayout.CENTER);

        getContentPane().add(leftPanel);

        // ========== Right Form Panel ==========
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(600, 0, 400, 520); // Adjusted for new left panel width
        rightPanel.setBackground(new Color(255, 255, 204));
        rightPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Staff Login Portal");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        titleLabel.setForeground(new Color(33, 33, 33));
        titleLabel.setBounds(66, 60, 300, 40);
        rightPanel.add(titleLabel);

        JLabel nameLabel = new JLabel("Username:");
        nameLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        nameLabel.setBounds(40, 173, 106, 25);
        rightPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setFont(new Font("Verdana", Font.PLAIN, 15));
        nameField.setBounds(150, 169, 200, 35);
        nameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        rightPanel.add(nameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        passwordLabel.setBounds(40, 262, 100, 25);
        rightPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Verdana", Font.PLAIN, 15));
        passwordField.setBounds(150, 258, 200, 35);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        rightPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Verdana", Font.BOLD, 16));
        loginButton.setBackground(new Color(204, 51, 0));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBounds(151, 331, 100, 40);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        rightPanel.add(loginButton);

        JLabel footer = new JLabel("\u00A9 2025 Student Hostel Management System");
        footer.setFont(new Font("Verdana", Font.ITALIC, 12));
        footer.setForeground(Color.GRAY);
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        footer.setBounds(25, 430, 350, 30);
        rightPanel.add(footer);

        getContentPane().add(rightPanel);

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

        if (validateLogIn(name, password)) {
            JOptionPane.showMessageDialog(this, "\u2705 Login successful!");
            dispose();
            new StaffHomePage(staff);
        } else {
            JOptionPane.showMessageDialog(this, "\u274C Invalid name or password.");
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
