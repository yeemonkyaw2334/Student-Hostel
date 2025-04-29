package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import Model.AddFeeModel;
import Model.RegisterModel;
import Model.StaffModel;
import Model.StudentRecordModel;
import Controller.RegisterController;
import Controller.AddFeeController;
import DBConnection.AutoID;
import DBConnection.Checking;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AddFeeView extends JFrame {
    private JComboBox<String> FeeTypeComboBox;
    private JTextField txtAmount;
    private JButton btnSave;
    private JLabel lblFeeID,feeID,  lblStaffIDAuto;
   // private JLabel lblStudentID;
    private JButton btnUpdate;
    //private JButton btnDelete;
    
    DefaultTableModel dtm = new DefaultTableModel();
    private JScrollPane scrollPane;
    
    String FeeID = null;
    
    String staffID = null;
    
    String hosteltype = null;
    
    int Amount = 0;
    int r;
    private JTable feetable;
    private JScrollPane scrollPane_1;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddFeeView frame = new AddFeeView(new StaffModel());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public AddFeeView(StaffModel staff) {
        setTitle("Fee Form");
        setSize(871, 549);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        // Gradient background for the frame
        getContentPane().setBackground(new Color(245, 255, 250));
        
        // Title Label with a modern font
        JLabel lblNewLabel = new JLabel("Fee Management Form");
        lblNewLabel.setBounds(341, 31, 347, 28);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 21));
        lblNewLabel.setForeground(new Color(36, 47, 65));  // Dark color
        getContentPane().add(lblNewLabel);
        
        // Fee ID Label
        feeID = new JLabel("Fee ID:");
        feeID.setBounds(22, 115, 98, 28);
        getContentPane().add(feeID);
        
        lblFeeID = new JLabel();
        lblFeeID.setBounds(179, 119, 109, 21);
        getContentPane().add(lblFeeID);
        
        // Staff ID Label
        JLabel StaffID = new JLabel("Staff ID:");
        StaffID.setBounds(22, 166, 77, 28);
        getContentPane().add(StaffID);
        
        lblStaffIDAuto = new JLabel("New label");
        lblStaffIDAuto.setBounds(179, 173, 109, 14);
        getContentPane().add(lblStaffIDAuto);
        lblStaffIDAuto.setText(staff.getStaffID());
        
        
        // Hostel Type Label
        JLabel FeeType = new JLabel("Fee Type");
        FeeType.setBounds(23, 214, 97, 28);
        getContentPane().add(FeeType);
        
        FeeTypeComboBox = new JComboBox<>();
        FeeTypeComboBox.setBounds(176, 214, 112, 28);
        FeeTypeComboBox.addItem("Select");
        FeeTypeComboBox.addItem("Hostel Fee");
        FeeTypeComboBox.addItem("Mess Fee");
        getContentPane().add(FeeTypeComboBox);
        
        // Fee Status Label
        JLabel lblAmount = new JLabel("Amount");
        lblAmount.setBounds(24, 275, 119, 28);
        getContentPane().add(lblAmount);
        
        // Status TextField
        txtAmount = new JTextField(20);
        txtAmount.setBounds(179, 275, 119, 28);
        getContentPane().add(txtAmount);
        
        // Save Button with style
        btnSave = new JButton("Save");
        btnSave.setBounds(43, 354, 77, 28);
        btnSave.setBackground(new Color(0, 123, 255));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnSave.setFont(new Font("Arial", Font.BOLD, 14));
        btnSave.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2, true));
        btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddFeeModel fm = new AddFeeModel();
                AddFeeController bc = new AddFeeController();
                
                
                if (lblFeeID.getText().trim().equals("") ||
                        lblStaffIDAuto.getText().trim().equals("") ||                    
                        FeeTypeComboBox.getSelectedItem().equals("Select") ||
                        txtAmount.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "There is a blank field!", "Fail", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                fm.setFeeID(lblFeeID.getText());
                fm.setStaffID(lblStaffIDAuto.getText());
                fm.setFeeType(FeeTypeComboBox.getSelectedItem().toString());
                int amount = Integer.parseInt(txtAmount.getText().trim());
                fm.setAmount(amount);
                
                try {
                    if (bc.isDuplicate(fm)) {
                        JOptionPane.showMessageDialog(null, "There is a same student ID!", "Fail", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int rs = bc.insert(fm);
                        if (rs == 1) {
                            JOptionPane.showMessageDialog(null, "Save Successfully", "Successfully", JOptionPane.INFORMATION_MESSAGE);
                            AutoID();
                            showList();
                            clear();
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        getContentPane().add(btnSave);
        
        // Update Button with custom style
        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AddFeeModel FM = new AddFeeModel();
				AddFeeController FC = new AddFeeController();
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to Update?", "Confrim",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					// Basic validation
					if (lblFeeID.getText().trim().equals("") || lblStaffIDAuto.getText().trim().equals("")
							|| FeeTypeComboBox.getSelectedItem().equals("Select") 
							|| txtAmount.getText().trim().equals("")) {

						JOptionPane.showMessageDialog(null, "There is a blank field!", "Fail",
								JOptionPane.ERROR_MESSAGE);
						return; // stop execution
					} else {
						FM.setFeeID(lblFeeID.getText().toString());
						FM.setStaffID(lblStaffIDAuto.getText().trim());                                                                                                                    
						FM.setFeeType(FeeTypeComboBox.getSelectedItem().toString());
						try {
						    int amount = Integer.parseInt(txtAmount.getText().trim());
						    FM.setAmount(amount);  // Set the amount as integer
						} catch (NumberFormatException ex) {
						    JOptionPane.showMessageDialog(null, "Amount must be a number!", "Input Error", JOptionPane.ERROR_MESSAGE);
						    return; // Exit early if invalid input
						}						
							try {
								int rs = FC.update(FM);
								System.out.println(rs);
								if (rs == 1) {
									JOptionPane.showMessageDialog(null, "Update Successfully", "Successfully",
											JOptionPane.INFORMATION_MESSAGE);
									AutoID();
									clear();
									showList();
								} else {
									JOptionPane.showMessageDialog(null, "Update fails");
				
							}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			
        });
        btnUpdate.setBounds(155, 354, 89, 28);
        btnUpdate.setBackground(new Color(255, 193, 7));
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 14));
        btnUpdate.setFocusPainted(false);
        getContentPane().add(btnUpdate);
        
        // Clear Button
        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		clear();
        	}
        });
        btnClear.setBounds(43, 407, 77, 28);
        getContentPane().add(btnClear);
        
        // Close Button
        JButton btnClose = new JButton("Close");
        btnClose.setBounds(167, 407, 77, 28);
        getContentPane().add(btnClose);
        
        // Add action listener to close the form
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // This closes the current frame/window
            }
        });
        
        // Table Panel with a gradient background
        JPanel panel_1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 255, 255), 0, getHeight(), new Color(240, 240, 240));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel_1.setBounds(339, 176, 505, 267);
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        // Scroll Pane for Table
        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 21, 464, 224);
        panel_1.add(scrollPane);
        
        feetable = new JTable();
        feetable.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		r = feetable.getSelectedRow();
				FeeID = (String) feetable.getValueAt(r, 0);
				lblFeeID.setText(FeeID);

				staffID = (String) feetable.getValueAt(r, 1);
				lblStaffIDAuto.setText(staffID);
				FeeTypeComboBox.setSelectedItem((String) feetable.getValueAt(r, 2));
				txtAmount.setText((String) feetable.getValueAt(r, 3));
        	}
        });
        feetable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        feetable.setBackground(new Color(255, 255, 255));
        feetable.setForeground(new Color(36, 47, 65));  // Dark color
        feetable.setGridColor(new Color(211, 211, 211));
        feetable.setSelectionBackground(new Color(0, 123, 255));  // Highlight color on row selection
        scrollPane.setViewportView(feetable);
        
        AutoID();
        createTable();
        try {
            showList();  // Load data into the table when the form is opened
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void AutoID() {
        try {
            lblFeeID.setText(AutoID.getAutoID("fee_ID", "fee", "F-"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setColumnWidth(int index, int width) {
        DefaultTableColumnModel tcm = (DefaultTableColumnModel) feetable.getColumnModel();
        TableColumn tc = tcm.getColumn(index);
        tc.setPreferredWidth(width);
    }

    public void createTable() {
        dtm.addColumn("FeeID");
        dtm.addColumn("StaffID");
        dtm.addColumn("FeeType");
        dtm.addColumn("Amount");
        feetable.setModel(dtm);
        
        // Set column widths after creating the table
        setColumnWidth(0, 120);
        setColumnWidth(1, 100);
        setColumnWidth(2, 100);
        setColumnWidth(3, 150);
    }

    public void showList() throws SQLException {
        String[] data = new String[4];
        AddFeeController fc = new AddFeeController();
        List<AddFeeModel> list = fc.selectall();  // Ensure this returns the latest data from DB
        System.out.println("Number of rows: " + list.size()); // Debugging line
        dtm.setRowCount(0);  // Clear the existing data in the table

        // Add the new data to the table
        for (AddFeeModel fm : list) {
            data[0] = fm.getFeeID();        // FeeID
            data[1] = fm.getStaffID();      // StaffID
            data[2] = fm.getFeeType();      // FeeType
            data[3] = String.valueOf(fm.getAmount());  // Amount
            dtm.addRow(data);
        }
        // Make sure the data has been added to the table
        System.out.println("Table updated with data.");
    }

    
    	private void clear() {
    	    FeeTypeComboBox.setSelectedIndex(0);
    	    FeeTypeComboBox.requestFocus();
    	    txtAmount.setText("");
    	    
    	

    }
}
