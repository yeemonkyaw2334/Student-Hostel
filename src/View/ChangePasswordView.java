package View;

import DBConnection.DBConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ChangePasswordView extends JFrame {

    private JTextField usernameField;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JButton changeButton;
    private JLabel imageLabel;

    public ChangePasswordView() {
        setTitle("Secure Password Update");
        setSize(860, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(255, 204, 102));

        // ===== Header Panel =====
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 255, 153));
        headerPanel.setBounds(0, 0, 860, 60);
        headerPanel.setLayout(new BorderLayout());
        JLabel headerLabel = new JLabel("Secure Password Update", JLabel.LEFT);
        headerLabel.setBackground(new Color(255, 153, 51));
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(new Color(0, 0, 0));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));
        headerPanel.add(headerLabel);
        getContentPane().add(headerPanel);

        // ===== Left Image Panel =====
        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(0, 60, 400, 440);
        imagePanel.setBackground(new Color(187, 222, 251));
        imagePanel.setLayout(new BorderLayout());

        imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/securelock.jpg"));
        Image img = icon.getImage().getScaledInstance(400, 440, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        getContentPane().add(imagePanel);

        // ===== Right Form Panel =====
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(255, 255, 153));
        formPanel.setBounds(410, 90, 430, 320);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createMatteBorder(1, 1, 5, 1, new Color(224, 224, 224))
        ));
        formPanel.setOpaque(true);
        getContentPane().add(formPanel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        usernameLabel.setBounds(30, 50, 120, 30);
        formPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(188, 51, 220, 30);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        formPanel.add(usernameField);

        JLabel oldPasswordLabel = new JLabel("Old Password:");
        oldPasswordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        oldPasswordLabel.setBounds(30, 106, 120, 30);
        formPanel.add(oldPasswordLabel);

        oldPasswordField = new JPasswordField();
        oldPasswordField.setBounds(188, 107, 220, 30);
        oldPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        oldPasswordField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        formPanel.add(oldPasswordField);

        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        newPasswordLabel.setBounds(30, 170, 120, 30);
        formPanel.add(newPasswordLabel);

        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(188, 171, 220, 30);
        newPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        newPasswordField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        formPanel.add(newPasswordField);

        changeButton = new JButton("Change Password");
        changeButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        changeButton.setBackground(new Color(255, 153, 51));
        changeButton.setForeground(new Color(0, 0, 0));
        changeButton.setBounds(143, 228, 180, 40);
        changeButton.setFocusPainted(false);
        changeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        changeButton.setBorder(BorderFactory.createLineBorder(new Color(30, 136, 229), 1, true));
        formPanel.add(changeButton);

        // ===== Button Action =====
        changeButton.addActionListener(e -> handleChangePassword());

        setVisible(true);
    }

    private void handleChangePassword() {
        String username = usernameField.getText();
        String oldPassword = new String(oldPasswordField.getPassword());
        String newPassword = new String(newPasswordField.getPassword());

        if (username.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement updateStmt = null;
        ResultSet rs = null;

        try {
            DBConfig db = new DBConfig();
            conn = db.getConnection();

            String checkSql = "SELECT * FROM staff WHERE staff_name = ? AND staff_password = ?";
            checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, username);
            checkStmt.setString(2, oldPassword);
            rs = checkStmt.executeQuery();

            if (rs.next()) {
                String updateSql = "UPDATE staff SET staff_password = ? WHERE staff_name = ?";
                updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setString(1, newPassword);
                updateStmt.setString(2, username);
                int updated = updateStmt.executeUpdate();

                if (updated > 0) {
                    JOptionPane.showMessageDialog(this, "\u2705 Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "\u26A0 Failed to update password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "\u274C Invalid username or old password.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (checkStmt != null) checkStmt.close(); } catch (Exception ignored) {}
            try { if (updateStmt != null) updateStmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChangePasswordView::new);
    }
}
