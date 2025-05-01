package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
//import Controller.StayRecordController;
//import Model.StayRecordModel;

public class StayRecordView extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public StayRecordView() {
        setTitle("Student Stay Record");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        // Gradient background panel
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(245, 245, 255), 0, getHeight(), new Color(230, 240, 255));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setBounds(0, 0, 784, 461);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Stay Record Details");
        titleLabel.setBounds(270, 20, 300, 30);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(33, 37, 41));
        panel.add(titleLabel);

        // Table setup
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Student Name", "Room No", "Start Date", "End Date"});
        
        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(24);
        table.setSelectionBackground(new Color(102, 153, 255));
        table.setSelectionForeground(Color.WHITE);
        table.setGridColor(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(88, 111, 632, 289);
        panel.add(scrollPane);

        JButton btnClose = new JButton("Close");
        btnClose.setBounds(340, 410, 100, 30);
        btnClose.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnClose.setBackground(new Color(220, 53, 69));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFocusPainted(false);
        btnClose.addActionListener(e -> dispose());
        panel.add(btnClose);
        
        JLabel lblStaffID = new JLabel("Staff ID");
        lblStaffID.setBounds(88, 67, 63, 14);
        panel.add(lblStaffID);
        
        JLabel lblStaffAutoID = new JLabel("New label");
        lblStaffAutoID.setBounds(180, 67, 84, 14);
        panel.add(lblStaffAutoID);
        
        JLabel lblStaffName = new JLabel("Staff Name");
        lblStaffName.setBounds(88, 92, 63, 14);
        panel.add(lblStaffName);
        
        JLabel StaffName = new JLabel("New label");
        StaffName.setBounds(180, 92, 84, 14);
        panel.add(StaffName);

        // Load data into table
        try {
            loadStayRecords();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to load data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadStayRecords() throws SQLException {
       // StayRecordController controller = new StayRecordController();
       // List<StayRecordModel> records = controller.getAllStayRecords();
     
        tableModel.setRowCount(0); // Clear table
        
       // for (StayRecordModel record : records) {
//            tableModel.addRow(new Object[]{
//                record.getStudentName(),
//                record.getRoomNo(),
//                record.getStartDate().toString(),
//                record.getEndDate().toString()
            //});
        }
    }
//}
