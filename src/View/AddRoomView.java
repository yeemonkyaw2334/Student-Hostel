package View;

import Controller.AddRoomController;
import DAO.HostelDAO;
import DAO.RoomDAO;
import DBConnection.DBConfig;
import Model.AddRoomModel;
import View.ViewRoomView;
 

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class AddRoomView extends JFrame {
    private JPanel contentPane;
    private JComboBox<String> cmbHostel, cmbFloor;
    private JTextField txtHostelId, txtNumRooms, txtPeoplePerRoom;
    private JLabel lblCapacityValue;
    private JTable tblFloors, tblRooms;
    private JButton btnAddFloor, btnGenerate, btnSave, btnShowAll;

    private DefaultTableModel floorModel, roomModel;

    private Map<String, String> hostelMap = new LinkedHashMap<>();
    private String selectedHostelId = "";
    private int hostelCapacity = 0;

    private List<AddRoomModel> generatedRooms = new ArrayList<>();

    public AddRoomView() {
        setTitle("Student Hostel Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 800);
        setLocationRelativeTo(null);

        contentPane = new JPanel(null);
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Student Hostel Management System", SwingConstants.CENTER);
        lblTitle.setOpaque(true);
       
        lblTitle.setFont(new Font("Myanmar3", Font.BOLD, 12));
        lblTitle.setBounds(200, 10, 800, 40);
        contentPane.add(lblTitle);

        JPanel staffPanel = new JPanel(null);
        staffPanel.setBounds(15, 60, 350, 60);
        staffPanel.setBorder(BorderFactory.createTitledBorder("Staff Info"));

        JLabel lblManagedBy = new JLabel("Managed by:");
        lblManagedBy.setOpaque(true);
         
        lblManagedBy.setFont(new Font("Myanmar3", Font.BOLD, 12));
        lblManagedBy.setBounds(10, 15, 100, 25);
        staffPanel.add(lblManagedBy);

        JLabel lblManagerName = new JLabel("Mr. Aung Aung");
        lblManagerName.setOpaque(true);
        
        lblManagerName.setFont(new Font("Myanmar3", Font.BOLD, 12));
        lblManagerName.setBounds(120, 15, 200, 25);
        staffPanel.add(lblManagerName);

        contentPane.add(staffPanel);

        JPanel inputPanel = new JPanel(null);
        inputPanel.setBounds(15, 129, 350, 293);
        inputPanel.setBorder(BorderFactory.createTitledBorder("Room Details"));
        contentPane.add(inputPanel);

        JComboBox<String> cmbHostelBox = new JComboBox<>();
        cmbHostel = cmbHostelBox;
        cmbHostel.setBounds(170, 20, 150, 25);
        inputPanel.add(cmbHostel);

        txtHostelId = new JTextField();
        txtHostelId.setEditable(false);
        txtHostelId.setBounds(170, 60, 150, 25);
        inputPanel.add(txtHostelId);

        lblCapacityValue = new JLabel("-");
        lblCapacityValue.setOpaque(true);
         
        lblCapacityValue.setFont(new Font("Myanmar3", Font.BOLD, 12));
        lblCapacityValue.setBounds(170, 100, 150, 25);
        inputPanel.add(lblCapacityValue);

        JComboBox<String> cmbFloorBox = new JComboBox<>();
        cmbFloor = cmbFloorBox;
        cmbFloor.addItem("1st Floor");
        cmbFloor.addItem("2nd Floor");
        cmbFloor.addItem("3rd Floor");
        cmbFloor.setBounds(170, 140, 150, 25);
        inputPanel.add(cmbFloor);

        txtNumRooms = new JTextField();
        txtNumRooms.setBounds(170, 180, 150, 25);
        inputPanel.add(txtNumRooms);

        txtPeoplePerRoom = new JTextField();
        txtPeoplePerRoom.setBounds(170, 220, 150, 25);
        inputPanel.add(txtPeoplePerRoom);

        btnAddFloor = new JButton("Add Floor");
        styleButton(btnAddFloor);
        btnAddFloor.setBounds(206, 255, 114, 28);
        inputPanel.add(btnAddFloor);

        JLabel lblSelectHostelName = new JLabel("Select Hostel Name:");
        lblSelectHostelName.setOpaque(true);
       
        lblSelectHostelName.setFont(new Font("Myanmar3", Font.BOLD, 12));
        lblSelectHostelName.setBounds(10, 20, 150, 25);
        inputPanel.add(lblSelectHostelName);

        JLabel lblHostelID = new JLabel("Hostel ID:");
        lblHostelID.setOpaque(true);
         
        lblHostelID.setFont(new Font("Myanmar3", Font.BOLD, 12));
        lblHostelID.setBounds(10, 60, 150, 25);
        inputPanel.add(lblHostelID);

        JLabel lblTotalRoomCapacity = new JLabel("Total Room Capacity:");
        lblTotalRoomCapacity.setOpaque(true);
       
        lblTotalRoomCapacity.setFont(new Font("Myanmar3", Font.BOLD, 12));
        lblTotalRoomCapacity.setBounds(10, 100, 150, 25);
        inputPanel.add(lblTotalRoomCapacity);

        JLabel lblSelectFloor = new JLabel("Select Floor:");
        lblSelectFloor.setOpaque(true);
        lblSelectFloor.setFont(new Font("Myanmar3", Font.BOLD, 12));
        lblSelectFloor.setBounds(10, 140, 150, 25);
        inputPanel.add(lblSelectFloor);

        JLabel lblNoOfRooms = new JLabel("No. of Rooms:");
        lblNoOfRooms.setOpaque(true);
        lblNoOfRooms.setFont(new Font("Myanmar3", Font.BOLD, 12));
        lblNoOfRooms.setBounds(10, 180, 150, 25);
        inputPanel.add(lblNoOfRooms);

        JLabel lblPeoplePerRoom = new JLabel("People per Room:");
        lblPeoplePerRoom.setOpaque(true);
        lblPeoplePerRoom.setFont(new Font("Myanmar3", Font.BOLD, 12));
        lblPeoplePerRoom.setBounds(10, 220, 150, 25);
        inputPanel.add(lblPeoplePerRoom);

        floorModel = new DefaultTableModel(new Object[]{"Hostel Name", "Floor", "Rooms", "Capacity"}, 0);
        tblFloors = new JTable(floorModel);
        JScrollPane floorScroll = new JScrollPane(tblFloors);

        JPanel floorPanel = new JPanel(null);
        floorPanel.setBounds(385, 60, 750, 320);
        floorPanel.setBorder(BorderFactory.createTitledBorder("Added Floor Details"));
        floorScroll.setBounds(10, 20, 730, 160);
        floorPanel.add(floorScroll);

        JButton btnRemove = new JButton("Remove Selected");
        JButton btnGenerate = new JButton("Generate Rooms");
        styleButton(btnRemove);
        styleButton(btnGenerate);
        btnRemove.setBounds(502, 190, 114, 41);
        btnGenerate.setBounds(626, 190, 114, 41);
        floorPanel.add(btnRemove);
        floorPanel.add(btnGenerate);
        contentPane.add(floorPanel);

        roomModel = new DefaultTableModel(new Object[]{"Room ID", "Room Name", "Floor", "Capacity", "Status"}, 0);
        tblRooms = new JTable(roomModel);
        JScrollPane roomScroll = new JScrollPane(tblRooms);

        JPanel roomPanel = new JPanel(null);
        roomPanel.setBounds(15, 432, 1120, 310);
        roomPanel.setBorder(BorderFactory.createTitledBorder("Generated Rooms"));
        roomScroll.setBounds(10, 20, 1100, 219);
        roomPanel.add(roomScroll);

        btnSave = new JButton("Save Rooms");
        btnShowAll = new JButton("Show All Rooms");
        styleButton(btnSave);
        styleButton(btnShowAll);
        btnSave.setBounds(872, 249, 114, 41);
        btnShowAll.setBounds(996, 249, 114, 41);
        roomPanel.add(btnSave);
        roomPanel.add(btnShowAll);
        contentPane.add(roomPanel);

        try {
            HostelDAO dao = new HostelDAO();
            hostelMap = dao.getHostelNameIdMap();
            cmbHostel.removeAllItems();
            for (String name : hostelMap.keySet()) {
                cmbHostel.addItem(name);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading hostel list: " + e.getMessage());
        }

        cmbHostel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = (String) cmbHostel.getSelectedItem();
                if (name != null) {
                    selectedHostelId = hostelMap.get(name);
                    txtHostelId.setText(selectedHostelId);
                    try {
                        HostelDAO dao = new HostelDAO();
                        hostelCapacity = dao.getHostelRoomCapacity(selectedHostelId);
                        lblCapacityValue.setText("Total: " + hostelCapacity + " Rooms");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "DB Error: " + ex.getMessage());
                    }
                }
            }
        });

        btnAddFloor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = (String) cmbHostel.getSelectedItem();
                String floorStr = (String) cmbFloor.getSelectedItem();
                String rStr = txtNumRooms.getText().trim();
                String cStr = txtPeoplePerRoom.getText().trim();

                if (name == null || rStr.isEmpty() || cStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields.");
                    return;
                }

                try {
                    int floor = getFloorNumber(floorStr);
                    int rooms = Integer.parseInt(rStr);
                    int cap = Integer.parseInt(cStr);

                    if (rooms <= 0 || cap <= 0) {
                        JOptionPane.showMessageDialog(null, "Rooms and capacity must be > 0");
                        return;
                    }
                    floorModel.addRow(new Object[]{name, floor, rooms, cap});
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid number format");
                }
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tblFloors.getSelectedRow();
                if (row != -1) floorModel.removeRow(row);
                else JOptionPane.showMessageDialog(null, "Select row to remove");
            }
        });

        btnGenerate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (floorModel.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Add floor data first.");
                    return;
                }
                List<Integer> floors = new ArrayList<>();
                List<Integer> rooms = new ArrayList<>();
                List<Integer> caps = new ArrayList<>();
                int total = 0;
                for (int i = 0; i < floorModel.getRowCount(); i++) {
                    try {
                        int f = Integer.parseInt(floorModel.getValueAt(i, 1).toString());
                        int r = Integer.parseInt(floorModel.getValueAt(i, 2).toString());
                        int c = Integer.parseInt(floorModel.getValueAt(i, 3).toString());
                        if (r <= 0 || c <= 0) throw new NumberFormatException();

                        floors.add(f);
                        rooms.add(r);
                        caps.add(c);
                        total += r;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Invalid data in row " + (i + 1));
                        return;
                    }
                }
                if (total != hostelCapacity) {
                    JOptionPane.showMessageDialog(null, "Total rooms " + total + " != Capacity " + hostelCapacity);
                    return;
                }
                AddRoomController ctrl = new AddRoomController();
                generatedRooms = ctrl.generateRooms(selectedHostelId, floors, rooms, caps);
                roomModel.setRowCount(0);
                for (AddRoomModel rm : generatedRooms) {
                    roomModel.addRow(new Object[]{
                            rm.getRoomId(), rm.getRoomName(), rm.getFloor(), rm.getCapacity(), rm.getStatus()
                    });
                }
            }
        });
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (generatedRooms == null || generatedRooms.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No generated rooms to save.");
                    return;
                }
                try {
                    Connection con = new DBConfig().getConnection(); // Get connection from your config
                    RoomDAO dao = new RoomDAO(con);                  // Pass it to the DAO

                    for (AddRoomModel room : generatedRooms) {
                        dao.insertRoom(room);
                    }
                    JOptionPane.showMessageDialog(null, "Rooms saved successfully.");
                    generatedRooms.clear();
                    roomModel.setRowCount(0);
                    floorModel.setRowCount(0);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Save failed: " + ex.getMessage());
                }
            }
        });


        btnShowAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewRoomView view = new ViewRoomView();
                view.setVisible(true);
            }
        });
    }

    private void styleButton(JButton b) {
        b.setFont(new Font("SansSerif", Font.BOLD, 14));
        b.setBackground(new Color(0, 153, 255));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
    }

    private int getFloorNumber(String s) {
        if (s.contains("1")) return 1;
        if (s.contains("2")) return 2;
        if (s.contains("3")) return 3;
        return 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddRoomView().setVisible(true));
    }
}
