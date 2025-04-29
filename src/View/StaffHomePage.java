package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.StaffModel;
import View.StudentRecordView;

public class StaffHomePage extends JFrame {

    private StaffModel staff;
    private JPanel mainPanel;

    public StaffHomePage(StaffModel staff) {
        this.staff = staff;
        setTitle("Staff Home Page");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== Sidebar =====
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(32, 42, 68));
        sidebar.setPreferredSize(new Dimension(220, getHeight()));
        sidebar.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Add buttons
        sidebar.add(createNavButton("Register", null));
        sidebar.add(createNavButton("Student Record", null));
        sidebar.add(createNavButton("Hostel", null));
        sidebar.add(createNavButton("Fee", null));
        sidebar.add(createNavButton("Payment", null));
        sidebar.add(createNavButton("Change Password", null));
        sidebar.add(createNavButton("Logout", null));

        // ===== Main Content Area =====
        mainPanel = new JPanel(new CardLayout());
        mainPanel.setBackground(Color.WHITE);

        // Welcome Panel
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.WHITE);
        JLabel welcomeLabel = new JLabel("Welcome, " + staff.getStaffName(), JLabel.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        welcomePanel.add(welcomeLabel);

        // Other Panels
        JPanel registerPanel = new JPanel();
        registerPanel.add(new JLabel("Register Section"));

        JPanel studentRecordPanel = new JPanel();
        studentRecordPanel.add(new JLabel("Student Record Section"));

        // Add panels to mainPanel
        mainPanel.add(welcomePanel, "Welcome");
        mainPanel.add(registerPanel, "Register");
        mainPanel.add(studentRecordPanel, "Student Record");

        // Default panel
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Welcome");

        // Add to frame
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createNavButton(String text, Icon icon) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(41, 57, 85));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(180, 40));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setIcon(icon);
        button.setIconTextGap(10);

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(52, 73, 94));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(41, 57, 85));
            }
        });

        // Action listener
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();

                if (text.equals("Register")) {
                    cl.show(mainPanel, "Register");
                } else if (text.equals("Student Record")) {
                    new StudentRecordView(staff).setVisible(true);
                } else if (text.equals("Fee")) {
                    new AddFeeView(staff).setVisible(true);
                } else if (text.equals("Change Password")) {
                    new ChangePasswordView();
                } else if (text.equals("Logout")) {
                    dispose();
                }
            }
        });

        return button;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new StaffHomePage(new StaffModel());
        });
    }
}
