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

    public ChangePasswordView() {
        setTitle("Change Password");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== Header Panel =====
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(33, 150, 243));
        JLabel headerLabel = new JLabel("🔒 Change Password", JLabel.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // ===== Form Panel =====
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(245, 245, 245));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        usernameLabel.setBounds(60, 50, 120, 30);
        formPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(200, 50, 200, 30);
        formPanel.add(usernameField);

        JLabel oldPasswordLabel = new JLabel("Old Password:");
        oldPasswordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        oldPasswordLabel.setBounds(60, 100, 120, 30);
        formPanel.add(oldPasswordLabel);

        oldPasswordField = new JPasswordField();
        oldPasswordField.setBounds(200, 100, 200, 30);
        formPanel.add(oldPasswordField);

        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        newPasswordLabel.setBounds(60, 150, 120, 30);
        formPanel.add(newPasswordLabel);

        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(200, 150, 200, 30);
        formPanel.add(newPasswordField);

        changeButton = new JButton("Update Password");
        changeButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        changeButton.setBackground(new Color(0, 123, 255));
        changeButton.setForeground(Color.WHITE);
        changeButton.setBounds(150, 220, 200, 40);
        changeButton.setFocusPainted(false);
        formPanel.add(changeButton);

        add(formPanel, BorderLayout.CENTER);

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
                    JOptionPane.showMessageDialog(this, "✅ Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "⚠ Failed to update password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "❌ Invalid username or old password.", "Error", JOptionPane.ERROR_MESSAGE);
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
