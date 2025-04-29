package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;

import Controller.AddHostelController;

import javax.swing.border.LineBorder;

 
import Model.AddHostelModel;
import Model.StaffModel;
import Controller.AddHostelController;

//import DBConnection.AutoID;
import DBConnection.HostelAutoID;
import DBConnection.Checking;
import Model.AddHostelModel;
import Model.StaffModel;

import java.awt.event.ActionEvent;
import java.awt.Window.Type;

import javax.swing.border.AbstractBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;

public class AddHostelView extends JFrame {

    private JPanel contentPane;
    private JTextField txtHostelName;
    private JTextField txtRoomCapacity;
    private JTextField txtHostelCategory;
    private JTextField txtShowAll;
    private JTextField txtHostelAddress;

    
    //private JTable table;
    private JTextField txtSearchHostel;
    private JComboBox<String> comboBox;
    
    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;
    private JButton btnClose;
    private JButton btnShowAll;

    private JLabel lblDateTime;
    private Date currentDate;
    
    
	private JLabel lblHostelId;
	private JTable tblHostel;
	DefaultTableModel dtm = new DefaultTableModel(); 
	int r=0;
	String HostelID=null;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_1;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddHostelView frame = new AddHostelView(new StaffModel());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @throws ClassNotFoundException 
     * @throws SQLException 
     */
    public AddHostelView(StaffModel staff) throws ClassNotFoundException, SQLException {
    	setResizable(false);
        setBackground(new Color(255, 255, 255));
        setFont(new Font("Myanmar3", Font.BOLD, 16));
        setTitle("Add Hostel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 954, 656);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 250, 250));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Student Hostel Management System");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(311, 10, 354, 39);
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("Myanmar3", Font.BOLD, 20));
        contentPane.add(lblNewLabel);

        // Panel with TitledBorder
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(new TitledBorder(
                new LineBorder(new Color(255, 160, 122), 2),
                "Hostel Info",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                new Font("Myanmar3", Font.BOLD, 14),
                new Color(160, 82, 45)
        ));
        
        panel.setBounds(37, 91, 370, 475);
        panel.setLayout(null);
        contentPane.add(panel);

        JLabel lblHostel = new JLabel("Hostel ID : ");
        lblHostel.setBackground(new Color(255, 160, 122));
        lblHostel.setFont(new Font("Myanmar3", Font.BOLD, 12));
        lblHostel.setBounds(20, 92, 137, 33);
        panel.add(lblHostel);

        JLabel lblHostelName = new JLabel("Hostel Name : ");
        lblHostelName.setBackground(new Color(255, 160, 122));
        lblHostelName.setFont(new Font("Myanmar3", Font.BOLD, 12));
        lblHostelName.setBounds(20, 142, 137, 33);
        panel.add(lblHostelName);

        JLabel lblRoomCapacity = new JLabel("Room Capacity : ");
        lblRoomCapacity.setBackground(new Color(255, 160, 122));
        lblRoomCapacity.setToolTipText("Total Room");
        lblRoomCapacity.setFont(new Font("Myanmar3", Font.BOLD, 12));
        lblRoomCapacity.setBounds(20, 195, 137, 33);
        panel.add(lblRoomCapacity);

        JLabel lblHostelCategory = new JLabel("Hostel Category : ");
        lblHostelCategory.setBackground(new Color(255, 160, 122));
        lblHostelCategory.setFont(new Font("Myanmar3", Font.BOLD, 12));
        lblHostelCategory.setBounds(20, 250, 137, 33);
        panel.add(lblHostelCategory);

        txtHostelName = new JTextField();
        txtHostelName.setFont(new Font("Myanmar3", Font.PLAIN, 12));
        txtHostelName.setBounds(160, 142, 175, 33);
        panel.add(txtHostelName);

        txtRoomCapacity = new JTextField();
        txtRoomCapacity.setToolTipText("Enter room capacity(Total Room) as a positive number");

       // txtRoomCapacity.setToolTipText("Total Room");
        txtRoomCapacity.setFont(new Font("Myanmar3", Font.PLAIN, 12));
        txtRoomCapacity.setBounds(160, 195, 175, 33);
        panel.add(txtRoomCapacity);

        comboBox = new JComboBox<>();
        comboBox.setForeground(new Color(0, 0, 0));
        comboBox.setBackground(new Color(245, 245, 220));
        comboBox.setFont(new Font("Myanmar3", Font.PLAIN, 12));
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Select Category", "Male Hostel", "Female Hostel"}));
        comboBox.setSelectedIndex(0);
        comboBox.setBounds(160, 250, 175, 33);
        panel.add(comboBox);
        
        JLabel lblHostelAddress = new JLabel("Hostel Address : ");
        lblHostelAddress.setBackground(new Color(255, 160, 122));
        lblHostelAddress.setFont(new Font("Myanmar3", Font.BOLD, 12));
        lblHostelAddress.setBounds(20, 306, 137, 33);
        panel.add(lblHostelAddress);

        txtHostelAddress = new JTextField();
        txtHostelAddress.setFont(new Font("Myanmar3", Font.PLAIN, 12));
        txtHostelAddress.setBounds(160, 306, 175, 33);
        panel.add(txtHostelAddress);


        btnSave = new JButton("Save");
    

        btnSave.setMnemonic('S');
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddHostelModel ahm = new AddHostelModel();
                AddHostelController cc = new AddHostelController();
                
                // Step 1: Perform validations before proceeding
                if (!validateHostelName()) {
                    return; // Exit the method if validation fails
                }

                if (!validateRoomCapacity(ahm)) {
                    return; // Exit the method if validation fails
                }

                if (!validateHostelCategory()) {
                    return; // Exit the method if validation fails
                }
                
                if (!validateHostelAddress()) {
                    return; // Exit the method if validation fails
                }

                // Step 2: Set the hostel object properties
                ahm.setHostel_id(lblHostelId.getText());
                ahm.setHostel_name(txtHostelName.getText());
                ahm.setRoom_capacity(Integer.parseInt(txtRoomCapacity.getText().trim()));
                ahm.setHostel_category(comboBox.getSelectedItem().toString());
                ahm.setHostel_address(txtHostelAddress.getText().trim());

                // Step 3: Check for duplicate hostel names
                try {
                    if (cc.isduplicate(ahm)) {
                        JOptionPane.showMessageDialog(null, "Hostel with the same name already exists!", "Fail", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Step 4: Insert the new hostel
                    int result = cc.insert(ahm);
                    if (result == 1) {
                        JOptionPane.showMessageDialog(null, "Hostel added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        HostelAutoID();  // Regenerate hostel ID
                        showList(); // Update the list
                        clear(); // Clear fields for the next entry
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }

            // Step 1: Validate Hostel Name
            private boolean validateHostelName() {
                if (txtHostelName.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Hostel Name cannot be blank!", "Fail", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                if (Checking.IsValidName(txtHostelName.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Invalid Hostel Name", "Invalid", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                return true;
            }

            // Step 2: Validate Room Capacity
            private boolean validateRoomCapacity(AddHostelModel ahm) {
                try {
                    int capacity = Integer.parseInt(txtRoomCapacity.getText().trim());
                    if (capacity <= 0) {
                        JOptionPane.showMessageDialog(null, "Room capacity must be greater than 0!", "Validation", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                    ahm.setRoom_capacity(capacity);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Room capacity must be a valid number!", "Validation", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                return true;
            }

            // Step 3: Validate Hostel Category Selection
            private boolean validateHostelCategory() {
                if (comboBox.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Please select a Hostel Category!", "Fail", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                return true;
            }
            
         // Step 4: Validate Hostel Address
            private boolean validateHostelAddress() {
                if (txtHostelAddress.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Hostel Address cannot be blank!", "Fail", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                if (Checking.IsValidName(txtHostelName.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Invalid Address Name", "Invalid", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                return true;
            }

            
            
        });

		btnSave.setBounds(20, 363, 85, 33);

		
        btnSave.setBackground(new Color(255, 160, 122));
       // btnSave.setBorder(new RoundedBorder(30)); // 30 = roundness level
        btnSave.setFont(new Font("Myanmar3", Font.BOLD, 12));
        panel.add(btnSave);


        btnUpdate = new JButton("Update");
        btnUpdate.setMnemonic('U');
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddHostelModel bm = new AddHostelModel();
                AddHostelController bc = new AddHostelController();

                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to update?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                    // Validation
                    if (lblHostelId.getText().trim().equals("") || txtHostelName.getText().trim().equals("") || comboBox.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(null, "Please fill in all required fields!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    bm.setHostel_id(lblHostelId.getText().trim());
                    bm.setHostel_name(txtHostelName.getText().trim());
                    bm.setHostel_category(comboBox.getSelectedItem().toString());
                    bm.setHostel_address(txtHostelAddress.getText().trim());

                    // set Room Capacity correctly
                    try {
                        int capacity = Integer.parseInt(txtRoomCapacity.getText().trim());
                        bm.setRoom_capacity(capacity);
                    } catch (NumberFormatException ex) {
                        bm.setRoom_capacity(0); // default to 0 if something wrong
                    }

                    // Validate name
                    if (Checking.IsValidName(bm.getHostel_name())) {
                        JOptionPane.showMessageDialog(null, "Invalid Hostel Name", "Validation Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        if (bc.isduplicate(bm)) {
                            JOptionPane.showMessageDialog(null, "Hostel with the same name already exists!", "Duplicate", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        int rs = bc.update(bm);
                        if (rs == 1) {
                            JOptionPane.showMessageDialog(null, "Update Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                            HostelAutoID();
                            clear();
                            showList();
                        } else {
                            JOptionPane.showMessageDialog(null, "Update Failed");
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
         
 
        btnUpdate.setEnabled(false);
        btnUpdate.setBounds(139, 363, 85, 33);
        btnUpdate.setBackground(new Color(255, 160, 122));
        btnUpdate.setFont(new Font("Myanmar3", Font.BOLD, 12));
        panel.add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setMnemonic('D');
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				AddHostelModel bm = new AddHostelModel();
				String hostelID = lblHostelId.getText().trim();
				if (hostelID.isEmpty()) {
				    JOptionPane.showMessageDialog(null, "Hostel ID is missing!", "Error", JOptionPane.ERROR_MESSAGE);
				    return;
				}
				 

				bm.setHostel_id(lblHostelId.getText().trim());

				if(!bm.getHostel_id().isBlank()) {
					if(JOptionPane.showConfirmDialog(null,"Are you sure you want to delete?","Confrim",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION) {
						AddHostelController bc = new AddHostelController();
						int rs = bc.delete(bm);
						if(rs==1) {
							
							try {
								JOptionPane.showMessageDialog(null,"Delete Successfully","Successfully", JOptionPane.INFORMATION_MESSAGE);

								try {
									HostelAutoID();
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								showList();
								clear();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}else {
							JOptionPane.showMessageDialog(null,"Delete fails");
						}
					}
		
			}
			}
		});
        
        btnDelete.setEnabled(false);
        btnDelete.setBounds(250, 363, 85, 33);
        btnDelete.setBackground(new Color(255, 160, 122));
        btnDelete.setFont(new Font("Myanmar3", Font.BOLD, 12));
        panel.add(btnDelete);

        btnClear = new JButton("Clear");
        btnClear.setMnemonic('C');
        btnClear.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				clear();
				try {
					HostelAutoID();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        

        btnClear.setBounds(74, 417, 85, 33);
        btnClear.setBackground(new Color(255, 160, 122));
        btnClear.setFont(new Font("Myanmar3", Font.BOLD, 12));
        panel.add(btnClear);

        btnClose = new JButton("Close");
        btnClose.setMnemonic('E');
        btnClose.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
        btnClose.setBounds(190, 417, 85, 33);
        btnClose.setBackground(new Color(255, 160, 122));
        btnClose.setFont(new Font("Myanmar3", Font.BOLD, 12));
        panel.add(btnClose);
        
        lblHostelId = new JLabel("");
        lblHostelId.setFont(new Font("Myanmar3", Font.BOLD, 12));
        lblHostelId.setBounds(160, 92, 175, 33);
        panel.add(lblHostelId);
        
        JLabel lblStaffId = new JLabel("Staff ID : ");
        lblStaffId.setBackground(new Color(255, 160, 122));
        lblStaffId.setFont(new Font("Myanmar3", Font.BOLD, 12));
        lblStaffId.setBounds(20, 45, 137, 33);
        panel.add(lblStaffId);
        
        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setToolTipText("Staff ID");
        lblNewLabel_1.setBounds(160, 45, 175, 33);
        panel.add(lblNewLabel_1);

     // Create the table first
        tblHostel = new JTable();
        tblHostel.setModel(dtm);
        tblHostel.setFont(new Font("Myanmar3", Font.PLAIN, 10));
        tblHostel.getTableHeader().setFont(new Font("Myanmar3", Font.BOLD, 12));
        tblHostel.getTableHeader().setBackground(new Color(255, 160, 122)); // Light coral
        tblHostel.getTableHeader().setForeground(Color.BLACK); // Text color
         
        // Disable auto-resize to allow horizontal scroll
        tblHostel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Wrap the table inside a JScrollPane
        scrollPane = new JScrollPane(tblHostel);
        scrollPane.setBounds(430, 145, 474, 418);

        // Allow horizontal and vertical scroll bars
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(12, Integer.MAX_VALUE));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(Integer.MAX_VALUE, 12));
        contentPane.add(scrollPane);


          
        tblHostel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                r = tblHostel.getSelectedRow();
                
                Object hostelIdValue = tblHostel.getValueAt(r, 0);
                Object hostelNameValue = tblHostel.getValueAt(r, 1);
                Object roomCapacityValue = tblHostel.getValueAt(r, 2);
                Object categoryValue = tblHostel.getValueAt(r, 3);
                Object hostelAddressValue = tblHostel.getValueAt(r, 4);
                // Safely set Hostel ID
                if (hostelIdValue != null) {
                    HostelID = hostelIdValue.toString();
                    lblHostelId.setText(HostelID);
                } else {
                    HostelID = "";
                    lblHostelId.setText("");
                }

                // Safely set Hostel Name
                if (hostelNameValue != null) {
                    txtHostelName.setText(hostelNameValue.toString());
                } else {
                    txtHostelName.setText("");
                }

                // Safely set Room Capacity
                if (roomCapacityValue != null) {
                    txtRoomCapacity.setText(roomCapacityValue.toString());
                } else {
                    txtRoomCapacity.setText("");
                }

                // Safely set Category
                if (categoryValue != null) {
                    comboBox.setSelectedItem(categoryValue.toString());
                } else {
                    comboBox.setSelectedIndex(0); // "Select Category"
                }

                if (hostelAddressValue != null) {
                    txtHostelAddress.setText(hostelAddressValue.toString());
                } else {
                    txtHostelAddress.setText("");
                }

                btnSave.setEnabled(false);
                btnUpdate.setEnabled(true);
                btnDelete.setEnabled(true);
                txtHostelName.requestFocus();
                txtHostelName.selectAll();
                System.out.println(HostelID);
            }
        });

       
        scrollPane.setViewportView(tblHostel);
        
        // Search and table
        JLabel lblSearchHostel = new JLabel("Search Hostel Name : ");
        lblSearchHostel.setFont(new Font("Myanmar3", Font.BOLD, 12));
        lblSearchHostel.setBounds(430, 91, 158, 33);
        contentPane.add(lblSearchHostel);

        txtSearchHostel = new JTextField();
        txtSearchHostel.setFont(new Font("Myanmar3", Font.PLAIN, 12));
        txtSearchHostel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    showOneList();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        txtSearchHostel.setBounds(585, 91, 197, 33);
        contentPane.add(txtSearchHostel);

        btnShowAll = new JButton("Show All");
        btnShowAll.setMnemonic('A');
        btnShowAll.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				try {
					showList();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        btnShowAll.setBounds(805, 91, 99, 33);
        btnShowAll.setBackground(new Color(255, 160, 122));
        btnShowAll.setFont(new Font("Myanmar3", Font.BOLD, 12));
        contentPane.add(btnShowAll);
        
        
        lblDateTime = new JLabel();
        lblDateTime.setFont(new Font("Myanmar3", Font.PLAIN, 10));
        lblDateTime.setForeground(new Color(105, 105, 105)); // Dark gray
        lblDateTime.setBounds(718, 576, 186, 20); // Adjust position and size
        contentPane.add(lblDateTime);

        // Set current datetime
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        lblDateTime.setText("Current DateTime: " + dtf.format(now));

        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblDateTime.setText("Current DateTime: " + dtf.format(LocalDateTime.now()));
            }
        });
        timer.start();

      
		
		HostelAutoID();
		createTable();
		try {
			showList();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	

    public void HostelAutoID() throws ClassNotFoundException, SQLException {
        try {
            lblHostelId.setText(HostelAutoID.getHostelAutoID("hostel_id", "student_hostel.hostel", "HS-"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


     

	public void setColumnWidth(int index,int width) {
		DefaultTableColumnModel tcm =(DefaultTableColumnModel) tblHostel.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}
	
	class RoundedBorder extends AbstractBorder {
	    private int radius;

	    RoundedBorder(int radius) {
	        this.radius = radius;
	    }

	    @Override
	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        g.setColor(Color.GRAY); // optional: border color
	        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
	    }
	}
	
	
	public void createTable() {
		 
		dtm.addColumn("Hostel ID");
		dtm.addColumn("Hostel Name");
		dtm.addColumn("Room Capacity");
		dtm.addColumn("Category");
		dtm.addColumn("Hostel Address");


		tblHostel.setModel(dtm);
		 
		setColumnWidth(0,100);
		setColumnWidth(1,100);
		setColumnWidth(2,100);
		setColumnWidth(3,100);
		setColumnWidth(4,300);
	}
    
	public void clear() {
	    txtHostelName.setText("");
	    txtRoomCapacity.setText("");
	    comboBox.setSelectedIndex(0);
	    txtHostelAddress.setText("");

	    btnSave.setEnabled(true);
	    btnUpdate.setEnabled(false);
	    btnDelete.setEnabled(false);
	}


	public void showList() throws SQLException{
			String data[] = new String[5];
			AddHostelController tc = new AddHostelController();
			
			
			List<AddHostelModel>list = tc.selectall();
			dtm.setRowCount(0);
			for(AddHostelModel tm:list) {
				data[0]=tm.getHostel_id();
				data[1]=tm.getHostel_name();
				data[2] =tm.getRoom_capacity()+"";
				data[3]=tm.getHostel_category();
				data[4] = tm.getHostel_address();

				dtm.addRow(data);
				
				
			}
		}
		
	public void showOneList() throws SQLException {
	    String data[] = new String[5];
	    AddHostelController tc = new AddHostelController();
	    AddHostelModel t = new AddHostelModel();
	    t.setHostel_name(txtSearchHostel.getText().trim()); //  use txtSearchHostel here
	    List<AddHostelModel> list = tc.selectone(t);
	    dtm.setRowCount(0);
	    for (AddHostelModel tm : list) {
	    	data[0] = tm.getHostel_id();
	    	data[1] = tm.getHostel_name();
	    	data[2] = tm.getRoom_capacity() + "";
	    	data[3] = tm.getHostel_category();
	    	data[4] = tm.getHostel_address();

	        dtm.addRow(data);
	    }
	}
}