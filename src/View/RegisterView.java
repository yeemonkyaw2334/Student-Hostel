package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import Model.RegisterModel;
import Model.StaffModel;
import Model.StudentRecordModel;
import Controller.RegisterController;
//import Controller.StudentRecordController;
import DBConnection.AutoID;
import DBConnection.Checking;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RegisterView extends JFrame {
    private JComboBox<String> hostelComboBox;
    private JButton btnSave;
    private JLabel lblRegistrationID, RegistrationID, lblStaffIDAuto;
    private JLabel lblStudentID;
    private JLabel lblStatus;
    private JButton btnUpdate;
    private JButton btnDelete;
    
    DefaultTableModel dtm = new DefaultTableModel();
    private JScrollPane scrollPane;
    
    String registrationID = null;
    String studentID = null;
    String staffID = null;
    String studentName = null;
    String hosteltype = null;
    String registrationdate = null;
    String registratonstatus = null;
    int r;
    private JTable tblRegistration;
    private JScrollPane scrollPane_1;
    private JLabel lblRegistrationDate;
    private Date currentDate;
    private JLabel lblNewLabel_1;
    private JLabel lblStudentName;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegisterView frame = new RegisterView(new StaffModel(), new StudentRecordModel());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public RegisterView(StaffModel staff, StudentRecordModel student) {
        setTitle("Register Form");
        setSize(911, 642);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Gradient background for the frame
        getContentPane().setBackground(new Color(255, 255, 153));
        getContentPane().setLayout(null);
        
        // Title Label with a modern font
        JLabel lblNewLabel = new JLabel("Student Hostel Register Form");
        lblNewLabel.setBounds(341, 31, 347, 28);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 21));
        lblNewLabel.setForeground(new Color(36, 47, 65));  // Dark color
        getContentPane().add(lblNewLabel);
        
        // Registration ID Label
        RegistrationID = new JLabel("Registration ID:");
        RegistrationID.setBounds(22, 115, 98, 28);
        getContentPane().add(RegistrationID);
        
        lblRegistrationID = new JLabel();
        lblRegistrationID.setBounds(179, 119, 109, 21);
        getContentPane().add(lblRegistrationID);
        
        // Staff ID Label
        JLabel StaffID = new JLabel("Staff ID:");
        StaffID.setBounds(22, 166, 77, 28);
        getContentPane().add(StaffID);
        
        lblStaffIDAuto = new JLabel("New label");
        lblStaffIDAuto.setBounds(179, 173, 109, 14);
        getContentPane().add(lblStaffIDAuto);
        lblStaffIDAuto.setText(staff.getStaffID());
        
        // Student Name Label
        JLabel StudentName = new JLabel("Student Name");
        StudentName.setBounds(22, 272, 130, 28);
        getContentPane().add(StudentName);
        
        // Hostel Type Label
        JLabel HostelType = new JLabel("Hostel Type");
        HostelType.setBounds(23, 331, 97, 28);
        getContentPane().add(HostelType);
        
        hostelComboBox = new JComboBox<>();
        hostelComboBox.setBackground(new Color(255, 255, 153));
        hostelComboBox.setBounds(176, 331, 112, 28);
        hostelComboBox.addItem("Select");
        hostelComboBox.addItem("MaleHostel");
        hostelComboBox.addItem("FemaleHostel");
        getContentPane().add(hostelComboBox);
        
        // Registration Date Label
        JLabel RegistrationDate = new JLabel("Registration Date");
        RegistrationDate.setBounds(22, 380, 98, 28);
        getContentPane().add(RegistrationDate);
        
        // Registration Status Label
        JLabel lblRegistrationStatus = new JLabel("Registration Status");
        lblRegistrationStatus.setBounds(22, 439, 119, 28);
        getContentPane().add(lblRegistrationStatus);
        
        // Save Button with style
        btnSave = new JButton("Save");
        btnSave.setBounds(23, 495, 77, 28);
        btnSave.setBackground(new Color(255, 153, 102));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnSave.setFont(new Font("Arial", Font.BOLD, 14));
        btnSave.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2, true));
        btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterModel bm = new RegisterModel();
                RegisterController bc = new RegisterController();
                
                if (lblRegistrationID.getText().trim().equals("") ||
                        lblStaffIDAuto.getText().trim().equals("") ||
                        lblStudentID.getText().trim().equals("") ||
                        lblStudentName.getText().trim().equals("") ||
                        hostelComboBox.getSelectedItem().equals("Select") ||
                        lblRegistrationDate.getText().trim().equals("") ||
                        lblStatus.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "There is a blank field!", "Fail", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                bm.setRegistrationID(lblRegistrationID.getText());
                bm.setStaffID(lblStaffIDAuto.getText());
                bm.setStudentID(lblStudentID.getText());
                bm.setStudentName(lblStudentName.getText());
                bm.setHostelType(hostelComboBox.getSelectedItem().toString());
                bm.setRegistrationDate(Date.valueOf(LocalDate.parse(lblRegistrationDate.getText())));
                bm.setRegistrationStatus(lblStatus.getText());
                
                try {
                    if (bc.isDuplicate(bm)) {
                        JOptionPane.showMessageDialog(null, "There is a same student ID!", "Fail", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int rs = bc.insert(bm);
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
        btnUpdate.setBounds(120, 495, 89, 28);
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RegisterModel RM = new RegisterModel();
				RegisterController RC = new RegisterController();
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to Update?", "Confrim",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					// Basic validation
					if (lblRegistrationID.getText().trim().equals("") || lblStaffIDAuto.getText().trim().equals("")
							|| lblStudentID.getText().trim().equals("")
							|| lblStudentName.getText().trim().equals("")
							|| hostelComboBox.getSelectedItem().equals("Select") 
							|| lblRegistrationDate.getText().trim().equals("") 
							|| lblStatus.getText().trim().equals("")) {

						JOptionPane.showMessageDialog(null, "There is a blank field!", "Fail",
								JOptionPane.ERROR_MESSAGE);
						return; // stop execution
					} else {
						RM.setRegistrationID(lblRegistrationID.getText().toString());
						RM.setStaffID(lblStaffIDAuto.getText().trim());                                                                                                                    
						RM.setStudentID(lblStudentID.getText().trim());
						RM.setStudentName(lblStudentName.getText().trim());
						RM.setHostelType(hostelComboBox.getSelectedItem().toString());
						// Fix registration date conversion
						String dateStr = lblRegistrationDate.getText().trim();
						LocalDate localDate = LocalDate.parse(dateStr);
						Date sqlDate = Date.valueOf(localDate);
						RM.setRegistrationDate(sqlDate);

						RM.setRegistrationStatus(lblStatus.getText().trim());
						if (Checking.IsValidName(RM.getStudentName()) || (!Checking.IsAllDigit(RM.getStudentName()))) {
							JOptionPane.showMessageDialog(null, "Invalid Name", "Invalid", JOptionPane.ERROR_MESSAGE);
						} else {
							try {
								int rs = RC.update(RM);
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
			}
        });
        btnUpdate.setBackground(new Color(255, 153, 102));
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 14));
        btnUpdate.setFocusPainted(false);
        getContentPane().add(btnUpdate);
        
        // Delete Button with custom style
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(219, 495, 89, 28);
        		btnDelete.addActionListener(new ActionListener() {
        		    public void actionPerformed(ActionEvent e) {
        		        String regID = lblRegistrationID.getText().trim();
        		        if (!regID.isEmpty()) {
        		            if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Confirm",
        		                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

        		                RegisterModel RM = new RegisterModel();
        		                RM.setRegistrationID(regID);

        		                RegisterController RC = new RegisterController();
        		                int rs = RC.delete(RM);

        		                if (rs == 1) {
        		                    JOptionPane.showMessageDialog(null, "Delete Successfully", "Successfully",
        		                            JOptionPane.INFORMATION_MESSAGE);
        		                    try {
        		                        showList();
        		                        AutoID();
        		                        clear();
        		                    } catch (SQLException e1) {
        		                        e1.printStackTrace();
        		                    }
        		                } else {
        		                    JOptionPane.showMessageDialog(null, "Delete failed");
        		                }
        		            }
        		        } else {
        		            JOptionPane.showMessageDialog(null, "No record selected to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
        		        }
        		    }
        		});
        btnDelete.setBackground(new Color(255, 153, 102));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
        btnDelete.setFocusPainted(false);
        getContentPane().add(btnDelete);
        
        // Clear Button
        JButton btnClear = new JButton("Clear");
        btnClear.setBackground(new Color(255, 153, 102));
        btnClear.setBounds(66, 539, 77, 28);
        btnClear.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		clear();
        	}
        });
        getContentPane().add(btnClear);
        
        // Close Button
        JButton btnClose = new JButton("Back");
        btnClose.setBackground(new Color(255, 153, 102));
        btnClose.setBounds(179, 539, 77, 28);
        getContentPane().add(btnClose);
        
        // Add action listener to close the form
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new StaffHomePage(staff);
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
        panel_1.setBackground(new Color(255, 153, 102));
        panel_1.setBounds(339, 177, 507, 182);
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        // Scroll Pane for Table
        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 21, 464, 136);
        panel_1.add(scrollPane);
        
        tblRegistration = new JTable();
        tblRegistration.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		r = tblRegistration.getSelectedRow();
				registrationID = (String) tblRegistration.getValueAt(r, 0);
				lblRegistrationID.setText(registrationID);

				staffID = (String) tblRegistration.getValueAt(r, 1);
				lblStaffIDAuto.setText(staffID);

				lblStudentID.setText((String) tblRegistration.getValueAt(r, 2));
				lblStudentName.setText((String) tblRegistration.getValueAt(r, 3));
				hostelComboBox.setSelectedItem((String) tblRegistration.getValueAt(r, 4));
				lblRegistrationDate.setText((String) tblRegistration.getValueAt(r, 5));
				lblStatus.setText((String) tblRegistration.getValueAt(r, 6));
        	}
        });
        tblRegistration.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblRegistration.setBackground(new Color(255, 255, 255));
        tblRegistration.setForeground(new Color(36, 47, 65));  // Dark color
        tblRegistration.setGridColor(new Color(211, 211, 211));
        tblRegistration.setSelectionBackground(new Color(0, 123, 255));  // Highlight color on row selection
        scrollPane.setViewportView(tblRegistration);
        
        lblRegistrationDate = new JLabel("");
        lblRegistrationDate.setBounds(176, 384, 112, 21);
        getContentPane().add(lblRegistrationDate);
        LocalDate currentDate = LocalDate.now();  // Get the current date
        lblRegistrationDate.setText(currentDate.format(DateTimeFormatter.ISO_DATE));
        
        AutoID();
        createTable();
        
        // Student ID and Name Labels
        JLabel StudentID = new JLabel("Student ID");
        StudentID.setBounds(22, 218, 130, 28);
        getContentPane().add(StudentID);
        
        lblStudentID = new JLabel("");
        lblStudentID.setBounds(176, 225, 112, 14);
        getContentPane().add(lblStudentID);
        lblStudentID.setText(student.getStudentID());
        
        lblStudentName = new JLabel("");
        lblStudentName.setBounds(179, 279, 112, 14);
        getContentPane().add(lblStudentName);
        lblStudentName.setText(student.getStudentName());
        
        // Show All Button
        JButton btnShowall = new JButton("ShowAll");
        btnShowall.setBounds(342, 143, 106, 28);
        getContentPane().add(btnShowall);
        
        lblStatus = new JLabel("Get");  // Set default text to "Get"
        lblStatus.setBounds(179, 446, 109, 14);  // Adjust the position as needed
        getContentPane().add(lblStatus);
                
        btnShowall.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    showList();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        
        try {
            showList();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    
    public void AutoID() {
        try {
            lblRegistrationID.setText(AutoID.getAutoID("registration_ID", "registration", "R-"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setColumnWidth(int index, int width) {
        DefaultTableColumnModel tcm = (DefaultTableColumnModel) tblRegistration.getColumnModel();
        TableColumn tc = tcm.getColumn(index);
        tc.setPreferredWidth(width);
    }

    public void createTable() {
        dtm.addColumn("RegistrationID");
        dtm.addColumn("StaffID");
        dtm.addColumn("StudentID");
        dtm.addColumn("StudentName");
        dtm.addColumn("HostelType");
        dtm.addColumn("RegistrationDate");
        dtm.addColumn("RegistrationStatus");
        tblRegistration.setModel(dtm);
        
        // Set column widths after creating the table
        setColumnWidth(0, 120);
        setColumnWidth(1, 100);
        setColumnWidth(2, 100);
        setColumnWidth(3, 150);
        setColumnWidth(4, 100);
        setColumnWidth(5, 120);
        setColumnWidth(6, 130);
    }

    public void showList() throws SQLException {
        String[] data = new String[7];
        RegisterController bc = new RegisterController();
        List<RegisterModel> list = bc.selectall();
        dtm.setRowCount(0);
        
        // Set the current date to the label
        currentDate = new Date(System.currentTimeMillis());  // Get the current date
        lblRegistrationDate.setText(currentDate.toString()); // Set the current date as text for the label
        
        for (RegisterModel bm : list) {
            data[0] = bm.getRegistrationID();       // RegistrationID
            data[1] = bm.getStaffID();              // StaffID
            data[2] = bm.getStudentID();            // StudentID
            data[3] = bm.getStudentName();          // StudentName
            data[4] = bm.getHostelType();           // HostelType
            data[5] = bm.getRegistrationDate().toString(); // RegistrationDate
            data[6] = bm.getRegistrationStatus();   // RegistrationStatus
            dtm.addRow(data);
        }
    }
    	private void clear() {
    	    hostelComboBox.setSelectedIndex(0);
    	    hostelComboBox.requestFocus();

    	    // Clear or reset labels
    	    try {
    	        lblRegistrationID.setText(AutoID.getAutoID("registration_ID", "registration", "R-"));
    	    } catch (ClassNotFoundException e) {
    	        e.printStackTrace();
    	    }

    	    // Reset registration date to current date
    	    LocalDate currentDate = LocalDate.now();
    	    lblRegistrationDate.setText(currentDate.format(DateTimeFormatter.ISO_DATE));

    	    
    	

    }
}
