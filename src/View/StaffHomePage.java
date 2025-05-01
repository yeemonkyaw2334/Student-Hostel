package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Model.StaffModel;
import View.StudentRecordView;
import View.AddHostelView;
import DBConnection.DBConfig;
public class StaffHomePage extends JFrame {

    private StaffModel staff;
    private JPanel mainPanel,panel;
    private JLabel lblNoOfHostel;
    private JLabel lblhostel;

    public StaffHomePage(StaffModel staff) {
        this.staff = staff;
        setTitle("Staff Home Page");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // ===== Sidebar =====
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(30, 144, 255));
        sidebar.setPreferredSize(new Dimension(220, getHeight()));
        sidebar.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Add buttons
        sidebar.add(createNavButton("Register", null));
        sidebar.add(createNavButton("Student Record", null));
        sidebar.add(createNavButton("Hostel", null));
        sidebar.add(createNavButton("Room", null));
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
        welcomePanel.setLayout(null);
        JLabel welcomeLabel = new JLabel("Welcome, " + staff.getStaffName(), JLabel.CENTER);
        welcomeLabel.setBounds(249, 5, 266, 36);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        welcomePanel.add(welcomeLabel);

        // Other Panels
        JPanel registerPanel = new JPanel();
        registerPanel.add(new JLabel("Register Section"));

        JPanel studentRecordPanel = new JPanel();
        studentRecordPanel.add(new JLabel("Student Record Section"));

        // Add panels to mainPanel
        mainPanel.add(welcomePanel, "Welcome");
        
        panel = new JPanel();
        panel.setBounds(108, 66, 137, 127);
        welcomePanel.add(panel);
        panel.setLayout(null);
        
        lblhostel = new JLabel("Hostel");
        lblhostel.setBounds(13, 5, 110, 49);
        panel.add(lblhostel);
        lblhostel.setFont(new Font("Tahoma", Font.PLAIN, 40));
        
        lblNoOfHostel = new JLabel("");
        lblNoOfHostel.setFont(new Font("Tahoma", Font.PLAIN, 47));
        lblNoOfHostel.setBounds(51, 65, 37, 51);
        panel.add(lblNoOfHostel);
        
        

        
        mainPanel.add(registerPanel, "Register");
        mainPanel.add(studentRecordPanel, "Student Record");

        // Default panel
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Welcome");

        // Add to frame
        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
     // ✅ Call your method to show the hostel count
        try {
            total_count();
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        setVisible(true);
    }

    private JButton createNavButton(String text, Icon icon) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(41, 57, 85));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
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
                    dispose();
                } else if (text.equals("Fee")) {
                    new AddFeeView(staff).setVisible(true);
                    dispose();
                 
                } else if (text.equals("Hostel")) {
                    try {
						new AddHostelView(staff).setVisible(true);
						dispose();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  
                } else if (text.equals("Change Password")) {
                    new ChangePasswordView();
                } else if (text.equals("Logout")) {
                    int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        new LogInView();
                        dispose();
              
        }
                }
            }
        
        });
        return button;
            }
    public void total_count() throws SQLException {
        lblNoOfHostel.setText(DBConfig.totalCount("hostel"));
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
