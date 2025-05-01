package View;

import java.awt.Color; 
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.sql.Date;  // Import sql.Date
import java.text.SimpleDateFormat; // Import SimpleDateFormat
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import Controller.StaffController;
import Controller.StudentRecordController;
import DBConnection.AutoID;
import DBConnection.Checking;
import Model.StaffModel;
import Model.StudentRecordModel;
import com.toedter.calendar.JDateChooser;
public class StudentRecordView extends JFrame {
	private JTextField studentNameField;
	private JDateChooser studentDOBField;
	private JTextField addressField;
	private JTextField phoneNumberField;
	private JTextField emailField;
	private JTextField entryYearField;
	private JComboBox<String> genderComboBox;
	private JComboBox<String> cboRegion;
	private JComboBox<String> cboTownship;
	private JComboBox<String> cboCitizenType;
	private JComboBox<String> searchRegion;
	private JComboBox<String> searchTownship;
	private JComboBox<String> searchCitizenType;
	private JTextField fatherNameField;
	private JTextField guardianPhoneNumberField;
	private JTextField dataEntryDateField;
	private JTextField remarkField;
	private JTextField txtNRCNumber;
	private JTextField searchNRCNumber;
	private JButton btnSave;
	private JLabel lblStudentID, lblstudentID, lblStaffIDAuto, Email,StudentNRC;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnRegister;
	//private JDateChooser StudentDOBField;
	private Date date;

	DefaultTableModel dtm = new DefaultTableModel();
	private JScrollPane scrollPane;
	String studentID = null;
	String staffID = null;
	int r;
	private JTable tblStudent;
//	private JScrollPane scrollPane_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffController staffController = new StaffController();

					StudentRecordView frame = new StudentRecordView(staffController.SelectByName("admin"));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public StudentRecordView(StaffModel staff) {
		setTitle("Student Record");
        setSize(1290, 717);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(255, 255, 204)); // Soft gray background

        JLabel lblNewLabel = new JLabel("Student Hostel Management System");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 22));
        lblNewLabel.setBounds(450, 11, 400, 30);
        lblNewLabel.setForeground(new Color(50, 50, 50));
        getContentPane().add(lblNewLabel);

		lblstudentID = new JLabel("Student ID:");
		lblstudentID.setBounds(14, 70, 157, 28);
		getContentPane().add(lblstudentID);

		lblStudentID = new JLabel();
		lblStudentID.setBounds(172, 74, 77, 21);
		getContentPane().add(lblStudentID);

		JLabel lblStaffId = new JLabel("Staff ID:");
		lblStaffId.setBounds(14, 102, 157, 28);
		getContentPane().add(lblStaffId);

		lblStaffIDAuto = new JLabel("New label");
		lblStaffIDAuto.setBounds(172, 109, 157, 14);
		getContentPane().add(lblStaffIDAuto);
		lblStaffIDAuto.setText(staff.getStaffID());

		JLabel label_2 = new JLabel("Student Name:");
		label_2.setBounds(14, 138, 130, 28);
		getContentPane().add(label_2);

		studentNameField = new JTextField(20);
		studentNameField.setBounds(172, 138, 253, 28);
		getContentPane().add(studentNameField);

		JLabel label_3 = new JLabel("Student DOB:");
		label_3.setBounds(14, 177, 114, 28);
		getContentPane().add(label_3);

		// Initialize the StudentDOBField (JDateChooser)
	    studentDOBField = new JDateChooser();  // Create a new instance of JDateChooser
	    studentDOBField.setDateFormatString("yyyy-MM-dd");  // Set the date format
	    studentDOBField.setBounds(172, 180, 253, 25);  // Set position and size (adjust as needed)
	    
	    // Add the StudentDOBField to the content pane or panel
	    getContentPane().add(studentDOBField);  // Assuming you are using JFrame or a similar container
	    

		JLabel label_4 = new JLabel("Student NRC:");
		label_4.setBounds(14, 216, 86, 28);
		getContentPane().add(label_4);

		JLabel label_5 = new JLabel("Address:");
		label_5.setBounds(14, 255, 150, 28);
		getContentPane().add(label_5);

		addressField = new JTextField(20);
		addressField.setBounds(172, 255, 253, 28);
		getContentPane().add(addressField);

		JLabel label_6 = new JLabel("Phone Number:");
		label_6.setBounds(12, 293, 86, 28);
		getContentPane().add(label_6);

		phoneNumberField = new JTextField(20);
		phoneNumberField.setBounds(172, 293, 253, 28);
		getContentPane().add(phoneNumberField);

		Email = new JLabel("Email:");
		Email.setBounds(14, 333, 65, 28);
		getContentPane().add(Email);

		emailField = new JTextField(20);
		emailField.setBounds(172, 333, 253, 28);
		getContentPane().add(emailField);

		JLabel label_8 = new JLabel("Entry Year:");
		label_8.setBounds(12, 372, 77, 28);
		getContentPane().add(label_8);

		entryYearField = new JTextField(20);
		entryYearField.setBounds(172, 372, 253, 28);
		getContentPane().add(entryYearField);

		JLabel label_9 = new JLabel("Gender:");
		label_9.setBounds(14, 411, 65, 28);
		getContentPane().add(label_9);

		genderComboBox = new JComboBox<>();
		genderComboBox.setBackground(new Color(255, 255, 153));
		genderComboBox.setBounds(172, 411, 253, 28);
		genderComboBox.addItem("Select");
		genderComboBox.addItem("Male");
		genderComboBox.addItem("Female");
		genderComboBox.addItem("Other");
		getContentPane().add(genderComboBox);

		JLabel label_10 = new JLabel("Father Name:");
		label_10.setBounds(12, 450, 86, 28);
		getContentPane().add(label_10);

		fatherNameField = new JTextField(20);
		fatherNameField.setBounds(172, 450, 253, 28);
		getContentPane().add(fatherNameField);

		JLabel label_11 = new JLabel("Guardian Phone Number:");
		label_11.setBounds(14, 487, 130, 28);
		getContentPane().add(label_11);

		guardianPhoneNumberField = new JTextField(20);
		guardianPhoneNumberField.setBounds(172, 487, 253, 28);
		getContentPane().add(guardianPhoneNumberField);

		JLabel label_12 = new JLabel("Data Entry Date:");
		label_12.setBounds(14, 526, 93, 28);
		getContentPane().add(label_12);

		dataEntryDateField = new JTextField(20);
		dataEntryDateField.setBounds(172, 526, 253, 28);
		getContentPane().add(dataEntryDateField);

		JLabel label_13 = new JLabel("Remark:");
		label_13.setBounds(14, 565, 93, 28);
		getContentPane().add(label_13);

		remarkField = new JTextField(20);
		remarkField.setBounds(172, 565, 253, 28);
		getContentPane().add(remarkField);

		btnSave = new JButton("Save");
		btnSave.setBackground(new Color(255, 153, 102));
		btnSave.setBounds(14, 604, 77, 28);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentRecordModel bm = new StudentRecordModel();
				StudentRecordController bc = new StudentRecordController();

				// Basic validation
				if (lblStudentID.getText().trim().equals("") || studentNameField.getText().trim().equals("")
						|| studentDOBField.getDate() == null|| cboRegion.getSelectedItem().equals("Select")
						|| cboTownship.getSelectedItem().equals("Select")
						|| cboCitizenType.getSelectedItem().equals("Select") || txtNRCNumber.getText().trim().equals("")
						|| addressField.getText().trim().equals("") || phoneNumberField.getText().trim().equals("")
						|| emailField.getText().trim().equals("") || entryYearField.getText().trim().equals("")
						|| genderComboBox.getSelectedItem().equals("Select")
						|| fatherNameField.getText().trim().equals("")
						|| guardianPhoneNumberField.getText().trim().equals("")
						|| dataEntryDateField.getText().trim().equals("")) {

					JOptionPane.showMessageDialog(null, "There is a blank field!", "Fail", JOptionPane.ERROR_MESSAGE);
					return; // stop execution
				}

				// Set values from UI to model
				bm.setStudentID(lblStudentID.getText());
				bm.setStaffID(lblStaffIDAuto.getText());
				bm.setStudentName(studentNameField.getText());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				// Convert java.util.Date to java.sql.Date
				java.util.Date utilDate = studentDOBField.getDate(); // Get java.util.Date
				if (utilDate != null) {
				    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());  // Convert to java.sql.Date
				    bm.setStudentDOB(sqlDate); // Pass java.sql.Date
				}

				String region = (String) cboRegion.getSelectedItem();
				String township = (String) cboTownship.getSelectedItem();
				String citizenType = (String) cboCitizenType.getSelectedItem();
				String number = txtNRCNumber.getText().trim();

				// Validate NRC number (must be 6 digits)
				if (!number.matches("\\d{6}")) {
					JOptionPane.showMessageDialog(null, "NRC number must be exactly 6 digits.");
					return;
				}

				// Combine into one NRC string
				String fullNRC = region + "/" + township + citizenType + number;
				bm.setFormattedNRC(fullNRC);
				bm.setStaffID(staff.getStaffID());
				bm.setAddress(addressField.getText());
				bm.setPhoneNumber(phoneNumberField.getText());
				bm.setEmail(emailField.getText());
				bm.setEntryYear(entryYearField.getText());
				bm.setGender(genderComboBox.getSelectedItem().toString());
				bm.setFatherName(fatherNameField.getText());
				bm.setGuardianPhoneNumber(guardianPhoneNumberField.getText());
				bm.setDataEntryDate(dataEntryDateField.getText());
				bm.setRemark(remarkField.getText());
				bm.setStudentNRC(fullNRC);

				try {
					if (bc.isduplicate(bm)) {
						JOptionPane.showMessageDialog(null, "There is a same student name!", "Fail",
								JOptionPane.ERROR_MESSAGE);
						studentNameField.requestFocus();
					} else {
						int rs = bc.insert(bm);
						if (rs == 1) {
							JOptionPane.showMessageDialog(null, "Save Successfully", "Successfully",
									JOptionPane.INFORMATION_MESSAGE);
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

		btnUpdate = new JButton("Update");
		btnUpdate.setBackground(new Color(255, 153, 102));
		btnUpdate.setBounds(122, 604, 89, 28);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentRecordModel bm = new StudentRecordModel();
				StudentRecordController bc = new StudentRecordController();
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to Update?", "Confrim",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					// Basic validation
					if (lblStudentID.getText().trim().equals("") || studentNameField.getText().trim().equals("")
							|| studentDOBField.getDate() == null
							|| cboRegion.getSelectedItem().equals("Select")
							|| cboTownship.getSelectedItem().equals("Select")
							|| cboCitizenType.getSelectedItem().equals("Select")
							|| txtNRCNumber.getText().trim().equals("") || addressField.getText().trim().equals("")
							|| phoneNumberField.getText().trim().equals("") || emailField.getText().trim().equals("")
							|| entryYearField.getText().trim().equals("")
							|| genderComboBox.getSelectedItem().equals("Select")
							|| fatherNameField.getText().trim().equals("")
							|| guardianPhoneNumberField.getText().trim().equals("")
							|| dataEntryDateField.getText().trim().equals(""))
							 {

						JOptionPane.showMessageDialog(null, "There is a blank field!", "Fail",
								JOptionPane.ERROR_MESSAGE);
						return; // stop execution
					} else {
						bm.setStudentID(lblStudentID.getText().toString());
						bm.setStaffID(lblStaffIDAuto.getText().trim());
						bm.setStudentName(studentNameField.getText().trim());
						// Retrieve and validate the Date from JDateChooser (assuming studentDOBField is a JDateChooser)
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						// Convert java.util.Date to java.sql.Date
						java.util.Date utilDate = studentDOBField.getDate(); // Get java.util.Date
						if (utilDate != null) {
						    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());  // Convert to java.sql.Date
						    bm.setStudentDOB(sqlDate); // Pass java.sql.Date
						}

						String region = (String) cboRegion.getSelectedItem();
						String township = (String) cboTownship.getSelectedItem();
						String citizenType = (String) cboCitizenType.getSelectedItem();
						String number = txtNRCNumber.getText().trim();

						// Validate NRC number (must be 6 digits)
						if (!number.matches("\\d{6}")) {
							JOptionPane.showMessageDialog(null, "NRC number must be exactly 6 digits.");
							return;
						}

						// Combine into one NRC string
						String fullNRC = region + "/" + township + citizenType + number;
						bm.setStudentNRC(fullNRC);
						bm.setAddress(addressField.getText().trim());
						bm.setPhoneNumber(phoneNumberField.getText().trim());
						bm.setEmail(emailField.getText().trim());
						bm.setEntryYear(entryYearField.getText().trim());
						bm.setGender(genderComboBox.getSelectedItem().toString());
						bm.setFatherName(fatherNameField.getText().trim());
						bm.setGuardianPhoneNumber(guardianPhoneNumberField.getText().trim());
						bm.setDataEntryDate(dataEntryDateField.getText().trim());
						bm.setRemark(remarkField.getText().trim());

						if (Checking.IsValidName(bm.getStudentName()) || (!Checking.IsAllDigit(bm.getStudentName()))) {
							JOptionPane.showMessageDialog(null, "Invalid Name", "Invalid", JOptionPane.ERROR_MESSAGE);
							studentNameField.requestFocus();
							studentNameField.selectAll();
						} else {
							try {
								int rs = bc.update(bm);
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
		getContentPane().add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(255, 153, 102));
		btnDelete.setBounds(226, 604, 89, 28);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentRecordModel bm = new StudentRecordModel();
				bm.setStudentID(lblStudentID.getText().toString());
				if (!bm.getStudentID().isBlank()) {
					if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Confrim",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						StudentRecordController bc = new StudentRecordController();
						int rs = bc.delete(bm);
						if (rs == 1) {

							try {
								JOptionPane.showMessageDialog(null, "Delete Successfully", "Successfully",
										JOptionPane.INFORMATION_MESSAGE);
								showList();
								AutoID();
								clear();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Delete fails");
						}
					}

				}
			}
		});
		getContentPane().add(btnDelete);


		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(new Color(255, 153, 102));
		btnClear.setBounds(282, 643, 77, 28);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
				AutoID();
			}
		});
		getContentPane().add(btnClear);

		JButton btnClose = new JButton("Close");
		btnClose.setBackground(new Color(255, 153, 102));
		btnClose.setBounds(94, 643, 77, 28);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnClose);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 153, 102));
		panel_1.setBounds(439, 216, 802, 257);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 23, 754, 209);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel_1.add(scrollPane);

		tblStudent = new JTable();

		tblStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				r = tblStudent.getSelectedRow();
				studentID = (String) tblStudent.getValueAt(r, 0);
				lblStudentID.setText(studentID);

				staffID = (String) tblStudent.getValueAt(r, 1);
				lblStaffIDAuto.setText(staffID);
                 
				studentNameField.setText((String) tblStudent.getValueAt(r, 2));
				
				// Your existing code where you're processing the date
				String dateString = (String) tblStudent.getValueAt(r, 3);  // Assuming date is in column 7
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

				try {
				    // Parse the date string into a java.util.Date
				    java.util.Date utilDate = dateFormat.parse(dateString);

				    // Convert java.util.Date to java.sql.Date
				    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

				    // Set the converted sqlDate to the JDateChooser
				    studentDOBField.setDate(sqlDate);

				} catch (ParseException parseException) {  // Fix duplicate 'e'
				    parseException.printStackTrace();  // Handle the parsing error if the date string format is incorrect
				}

				// Parse and set NRC fields
			    String nrc = (String) tblStudent.getValueAt(r, 4);
			    if (nrc != null && !nrc.isEmpty()) {
			        try {
			            // Example NRC: "12/TaKaNa(N)123456"
			            String region = nrc.substring(0, nrc.indexOf('/'));
			            String temp = nrc.substring(nrc.indexOf('/') + 1);
			            String township = temp.substring(0, temp.indexOf('('));
			            String citizenType = temp.substring(temp.indexOf('('), temp.indexOf(')') + 1);
			            String number = temp.substring(temp.indexOf(')') + 1);

			            cboRegion.setSelectedItem(region);
			            cboTownship.setSelectedItem(township);
			            cboCitizenType.setSelectedItem(citizenType);
			            txtNRCNumber.setText(number);
			        } catch (Exception ex) {
			            ex.printStackTrace(); // Optional: show error message
			        }
			    }

				addressField.setText((String) tblStudent.getValueAt(r, 5));
				phoneNumberField.setText((String) tblStudent.getValueAt(r, 6));
				emailField.setText((String) tblStudent.getValueAt(r, 7));
				entryYearField.setText((String) tblStudent.getValueAt(r, 8));
				genderComboBox.setSelectedItem((String) tblStudent.getValueAt(r, 9));
				fatherNameField.setText((String) tblStudent.getValueAt(r, 10));
				guardianPhoneNumberField.setText((String) tblStudent.getValueAt(r, 11));
				dataEntryDateField.setText((String) tblStudent.getValueAt(r, 12));
				remarkField.setText((String) tblStudent.getValueAt(r, 13));

				btnSave.setEnabled(false);
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);

				studentNameField.requestFocus();
				studentNameField.selectAll();
				studentDOBField.requestFocus();
				((JTextField) studentDOBField.getDateEditor().getUiComponent()).selectAll(); // ✅ Correct
				System.out.println(studentID);
				System.out.println(staffID);
			}
		});

		tblStudent.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tblStudent.setBackground(new Color(255, 255, 255));
		tblStudent.setFont(new Font("Pyidaungsu", Font.PLAIN, 15));
		tblStudent.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(tblStudent);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 153, 102));
		panel.setBounds(438, 89, 451, 119);
		getContentPane().add(panel);
		panel.setLayout(null);

		StudentNRC = new JLabel("Student NRC:");
		StudentNRC.setBounds(10, 40, 85, 14);
		panel.add(StudentNRC);
        
		// Region
		String[] regionItems = {"Select", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
		searchRegion = new JComboBox(regionItems);
		searchRegion.setBackground(new Color(255, 153, 102));
		searchRegion.setBounds(119, 33, 65, 28);
		panel.add(searchRegion);

		// Township
		searchTownship = new JComboBox();
		searchTownship.setBackground(new Color(255, 153, 102));
		searchTownship.addItem("Select");
		searchTownship.setSelectedIndex(0);
		searchTownship.setBounds(184, 33, 100, 28);
		panel.add(searchTownship);

		// Citizen Type
		searchCitizenType = new JComboBox();
		searchCitizenType.setBackground(new Color(255, 153, 102));
		String[] types = {"Select", "(N)", "(AC)", "(NC)"};
		for (String t : types) {
		    searchCitizenType.addItem(t);
		}
		searchCitizenType.setBounds(285, 33, 94, 28);
		panel.add(searchCitizenType);


		searchNRCNumber = new JTextField();
		searchNRCNumber.setBounds(378, 33, 65, 28);
		panel.add(searchNRCNumber);

		searchRegion.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String selectedRegion = searchRegion.getSelectedItem().toString();
		        searchTownship.removeAllItems(); // Clear existing items
		        if (selectedRegion.equals("1")) {
		        	String[] townships = {"BaMaNa", "KhaHpaNa", "DaHpaYa", "HaPaNa", "HpaKaNa", "AhGaNa",
                            "KaMaTa", "KaPaTa", "KhaLaHpa", "LaGaNa", "MaKhaBa", "MaSaNa",
                            "MaKaTa", "MaNyaNa", "MaMaNa", "MaKaNa", "MaLaNa", "NaMaNa",
                            "PaWaNa", "PaNaDa", "PaTaAh", "SaDaNa", "YaBaYa", "YaKaNa",
                            "SaBaNa", "SaPaYa", "TaNaNa", "TaSaLa", "WaMaNa"};
		        	for (String township : townships) {
		        		searchTownship.addItem(township);
		            }
		        } else if (selectedRegion.equals("2")) {
		        	String[] townships = {"BaLaKha", "DaMaSa", "HpaSaNa", "HpaYaSa", "LaKaNa", "MaTaNa",
                            "YaTaNa", "YaThaNa"};
		        	for (String township : townships) {
		        		searchTownship.addItem(township);
		            }
		        } else if (selectedRegion.equals("3")) {
		        	String[] townships = {"BaGaLa", "LaBaNa", "BaAhNa", "HpaPaNa", "BaThaSa", "KaMaMa",
                            "KaKaYa", "KaDaNa", "KaSaKa", "KaDaTa", "LaThaNa", "MaWaTa",
                            "PaKaNa", "YaYaTha", "SaKaLa", "ThaTaNa", "ThaTaKa", "WaLaMa",
                             };
		        	for (String township : townships) {
		        		searchTownship.addItem(township);
		            }
		        } else if (selectedRegion.equals("4")) {
		        	String[] townships = {"KaKhaNa", "HpaLaNa", "HaKhaNa", "KaPaLa", "MaTaPa", "MaTaNa",
                            "PaLaWa", "YaZaNa", "YaKhaDa", "SaMaNa", "TaTaNa", "HtaTaLa",
                            "TaZaNa"
                             };
		        	for (String township : townships) {
		        		searchTownship.addItem(township);
		            }
		        }
		        else if (selectedRegion.equals("5")) {
		        	String[] townships = {"AhYaTa", "BaMaNa", "BaTaLa", "KhaOuTa", "KaTaNa", "HaMaLa",
                            "AhTaNa", "KaLaHta", "KaLaWa", "KaBaLa", "KaNaNa", "KaThaNa",
                            "KaLaTa", "KhaOuNa", "KaLaNa", "LaHaNa", "LaYaNa", "MaLaNa",
                            "MaKaNa", "MaYaNa","MaMaNa","MaMaTa","NaYaNa","NgaZaNa","PaLaNa","HpaPaNa"
                            ,"PaLaBa","SaKaNa","SaLaKa","YaBaNa","DaPaYa","TaMaNa","TaSaNa","HtaKaNa","WaLaNa"
                            ,"WaThaNa","YaOuNa","YaMaPa","KaMaNa","KhaPaNa"};
		        	for (String township : townships) {
		        		searchTownship.addItem(township);
		            }	        	
		        }
		        else if (selectedRegion.equals("6")) {
		        	String[] townships = {"BaPaNa", "HtaWaNa", "KaLaAh", "KaThaNa", "KaSaNa", "LaLaNa",
                            "MaMaNa", "PaLaNa", "TaThaYa", "ThaYaKha", "YaPhaNa", "KhaMaNa",
                            "MaTaNa", "PaLaTa", "KaYaYa"};
		        	for (String township : townships) {
		        		searchTownship.addItem(township);
		            }     	
		        }
		        else if (selectedRegion.equals("7")) {
		        	String[] townships = {"DaOuNa", "KaPaKa", "KaWaNa", "KaKaNa", "KaTaKha", "LaPaTa",
                            "MaLaNa", "MaNyaNa", "NaTaLa", "NyaLaPa", "AhHpaNa", "AhTaNa",
                            "PaTaNa", "PaKhaTa", "PaKhaNa", "PaTaTa", "PaNaKa", "HpaMaNa",
                            "PaMaNa", "YaTaNa","YaKaNa","HtaTaPa","TaNgaNa","ThaNaPa","ThaWaTa","ThaKaNa"
                            ,"ThaSaNa","WaMaNa","YaTaYa","ZaKaNa","PaTaSa"};
		        	for (String township : townships) {
		        		searchTownship.addItem(township);
		            }     	
		        }
		        else if (selectedRegion.equals("8")) {
		        	String[] townships = {"AhLaNa", "KhaMaNa", "GaGaNa", "KaMaNa", "MaKaNa", "MaBaNa",
                            "MaTaNa", "MaLaNa", "MaMaNa", "MaHtaNa", "MaThaNa", "NaMaMa",
                            "NgaHpaNa", "PaKhaKa", "PaMaNa", "PaHpaNa", "SaLaNa", "SaMaNa",
                            "SaHpaNa", "SaTaYa","SaPaWa","TaTaKa","ThaYaNa","HtaLaNa","YaNaKha","YaSaKa","KaHtaNa"
                            };
		        	for (String township : townships) {
		        		searchTownship.addItem(township);
		            }     	
		        }
		        else if (selectedRegion.equals("9")) {
		        	String[] townships = {"AhMaYa", "AhMaZa", "KhaAhZa", "KhaMaSa", "KaPaTa", "KaSaNa",
                            "MaTaYa", "MaHaMa", "MaLaNa", "MaHtaLa", "MaKaNa", "MaKhaNa",
                            "MaThaNa", "NaHtaKa", "NgaThaYa", "NgaZaNa", "NyaOuNa", "PaThaKa",
                            "PaBaNa", "PaKaKha","PaOuLa","SaKaNa","ThaPaKa","TaTaOu","TaThaNa","ThaSaNa","WaTaNa"
                            ,"YaMaTha","TaKaTa","MaMaNa","DaKhaTha","LaWaNa","OuTuTha","PaBaTha","PaMaNa","TaKaNa","ZaBaTha","ZaYaTha"
                            };		        	
		        	for (String township : townships) {
		        		searchTownship.addItem(township);
		            }     	
		        }
		        else if (selectedRegion.equals("10")) {
		        	String[] townships = {"BaLaNa", "KhaSaNa", "KhaZaNa", "KaMaYa", "KaHtaNa", "LaMaNa",
                            "MaLaMa", "MaDaNa", "PaMaNa", "ThaHpaYa", "ThaHtaNa", "YaMaNa"
                            };		        	
		        	for (String township : townships) {
		        		searchTownship.addItem(township);
		            }     	
		        }
		        else if (selectedRegion.equals("11")) {
		        	String[] townships = {"AhMaNa", "BaThaTa", "GaMaNa", "KaHpaNa", "KaTaNa", "MaAhTa",
                            "MaTaNa", "MaPaNa", "MaAhNa", "MaOuNa", "MaPaTa", "PaNaTa","YaBaNa",
                            "YaThaTa","SaTaNa","ThaTaNa","TaKaNa","KaTaLa","TaPaWa","BaTaHta"
                            };		        	
		        	for (String township : townships) {
		        		searchTownship.addItem(township);
		            }     	
		        }
		        else if (selectedRegion.equals("12")) {
		        	String[] townships = {"AhLaNa", "BaHaNa", "KaKaKa", "DaGaYa", "DaGaMa", "DaGaSa",
                            "DaGaTa", "DaGaNa", "DaLaNa", "DaPaNa", "LaThaYa", "LaMaNa","LaKaNa",
                            "MaBaNa","HtaTaPa","AhSaNa","KaMaYa","KaMaNa","KhaYaNa","KaKhaKa","KaTaTa",
                            "KaTaNa","KaMaTa","LaMaTa","LaThaNa","MaYaKa","MaGaDa","MaGaTa","OuKaMa","PaBaTa",
                            ""
                            };		        	
		        	for (String township : townships) {
		        		searchTownship.addItem(township);
		            }     	
		        }
		}
		});
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBackground(new Color(255, 255, 153));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        try {
				            // Get search criteria from UI components
				            String region = (String) searchRegion.getSelectedItem();
				            String township = (String) searchTownship.getSelectedItem();
				            String citizenType = (String) searchCitizenType.getSelectedItem();
				            String nrcNumber = searchNRCNumber.getText().trim();

				            // Validate NRC number (you can customize the validation logic)
				            if (!nrcNumber.matches("\\d{6}")) {
				                JOptionPane.showMessageDialog(null, "NRC number must be exactly 6 digits.");
				                return;
				            }

				            // Call the showListone method with the user input
				            showListone(region, township, citizenType, nrcNumber);

				        } catch (Exception ex) {
				            JOptionPane.showMessageDialog(null, "Search failed: " + ex.getMessage());
				            ex.printStackTrace();
				        }
				    }
				});

			}
		});
		btnSearch.setBounds(223, 80, 94, 28);
		panel.add(btnSearch);

		btnRegister = new JButton("Register");
		btnRegister.setBackground(new Color(255, 153, 102));
		btnRegister.setBounds(336, 604, 89, 28);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentRecordModel student = new StudentRecordModel();
				student.setStudentName(studentNameField.getText());
				student.setStudentID(lblStudentID.getText());
				String studentID = lblStudentID.getText();
				// add fields
				dispose();
				RegisterView registerView = new RegisterView(staff, student);
				registerView.setVisible(true);
			}
		});
		getContentPane().add(btnRegister);

		String[] regions = {"Select", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
		cboRegion = new JComboBox(regions);
		cboRegion.setBackground(new Color(255, 255, 153));
		cboRegion.setBounds(172, 216, 65, 28);
		getContentPane().add(cboRegion);

		cboTownship = new JComboBox();
		cboTownship.setBackground(new Color(255, 255, 153));
		cboTownship.addItem("Select");
		cboTownship.setBounds(238, 216, 77, 28);
		getContentPane().add(cboTownship);

		String[] citizenTypes = {"Select", "(N)", "(AC)", "(NC)"};
		cboCitizenType = new JComboBox(citizenTypes);
		cboCitizenType.setBackground(new Color(255, 255, 153));
		cboCitizenType.setBounds(314, 216, 65, 28);
		getContentPane().add(cboCitizenType);

		txtNRCNumber = new JTextField();
		txtNRCNumber.setBounds(376, 216, 49, 28);
		getContentPane().add(txtNRCNumber);
		txtNRCNumber.setColumns(10);
		
				JButton btnShowall = new JButton("ShowAll");
				btnShowall.setBackground(new Color(255, 153, 102));
				btnShowall.setBounds(899, 180, 106, 28);
				getContentPane().add(btnShowall);
				btnShowall.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							showList();
							clear();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
		
		cboRegion.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String selectedRegion = cboRegion.getSelectedItem().toString();
		        cboTownship.removeAllItems(); // Clear existing items
		        if (selectedRegion.equals("1")) {
		        	String[] townships = {"BaMaNa", "KhaHpaNa", "DaHpaYa", "HaPaNa", "HpaKaNa", "AhGaNa",
                            "KaMaTa", "KaPaTa", "KhaLaHpa", "LaGaNa", "MaKhaBa", "MaSaNa",
                            "MaKaTa", "MaNyaNa", "MaMaNa", "MaKaNa", "MaLaNa", "NaMaNa",
                            "PaWaNa", "PaNaDa", "PaTaAh", "SaDaNa", "YaBaYa", "YaKaNa",
                            "SaBaNa", "SaPaYa", "TaNaNa", "TaSaLa", "WaMaNa"};
		        	for (String township : townships) {
		                cboTownship.addItem(township);
		            }
		        } else if (selectedRegion.equals("2")) {
		        	String[] townships = {"BaLaKha", "DaMaSa", "HpaSaNa", "HpaYaSa", "LaKaNa", "MaTaNa",
                            "YaTaNa", "YaThaNa"};
		        	for (String township : townships) {
		                cboTownship.addItem(township);
		            }
		        } else if (selectedRegion.equals("3")) {
		        	String[] townships = {"BaGaLa", "LaBaNa", "BaAhNa", "HpaPaNa", "BaThaSa", "KaMaMa",
                            "KaKaYa", "KaDaNa", "KaSaKa", "KaDaTa", "LaThaNa", "MaWaTa",
                            "PaKaNa", "YaYaTha", "SaKaLa", "ThaTaNa", "ThaTaKa", "WaLaMa",
                             };
		        	for (String township : townships) {
		                cboTownship.addItem(township);
		            }
		        } else if (selectedRegion.equals("4")) {
		        	String[] townships = {"KaKhaNa", "HpaLaNa", "HaKhaNa", "KaPaLa", "MaTaPa", "MaTaNa",
                            "PaLaWa", "YaZaNa", "YaKhaDa", "SaMaNa", "TaTaNa", "HtaTaLa",
                            "TaZaNa"
                             };
		        	for (String township : townships) {
		                cboTownship.addItem(township);
		            }
		        }
		        else if (selectedRegion.equals("5")) {
		        	String[] townships = {"AhYaTa", "BaMaNa", "BaTaLa", "KhaOuTa", "KaTaNa", "HaMaLa",
                            "AhTaNa", "KaLaHta", "KaLaWa", "KaBaLa", "KaNaNa", "KaThaNa",
                            "KaLaTa", "KhaOuNa", "KaLaNa", "LaHaNa", "LaYaNa", "MaLaNa",
                            "MaKaNa", "MaYaNa","MaMaNa","MaMaTa","NaYaNa","NgaZaNa","PaLaNa","HpaPaNa"
                            ,"PaLaBa","SaKaNa","SaLaKa","YaBaNa","DaPaYa","TaMaNa","TaSaNa","HtaKaNa","WaLaNa"
                            ,"WaThaNa","YaOuNa","YaMaPa","KaMaNa","KhaPaNa"};
		        	for (String township : townships) {
		                cboTownship.addItem(township);
		            }	        	
		        }
		        else if (selectedRegion.equals("6")) {
		        	String[] townships = {"BaPaNa", "HtaWaNa", "KaLaAh", "KaThaNa", "KaSaNa", "LaLaNa",
                            "MaMaNa", "PaLaNa", "TaThaYa", "ThaYaKha", "YaPhaNa", "KhaMaNa",
                            "MaTaNa", "PaLaTa", "KaYaYa"};
		        	for (String township : townships) {
		                cboTownship.addItem(township);
		            }     	
		        }
		        else if (selectedRegion.equals("7")) {
		        	String[] townships = {"DaOuNa", "KaPaKa", "KaWaNa", "KaKaNa", "KaTaKha", "LaPaTa",
                            "MaLaNa", "MaNyaNa", "NaTaLa", "NyaLaPa", "AhHpaNa", "AhTaNa",
                            "PaTaNa", "PaKhaTa", "PaKhaNa", "PaTaTa", "PaNaKa", "HpaMaNa",
                            "PaMaNa", "YaTaNa","YaKaNa","HtaTaPa","TaNgaNa","ThaNaPa","ThaWaTa","ThaKaNa"
                            ,"ThaSaNa","WaMaNa","YaTaYa","ZaKaNa","PaTaSa"};
		        	for (String township : townships) {
		                cboTownship.addItem(township);
		            }     	
		        }
		        else if (selectedRegion.equals("8")) {
		        	String[] townships = {"AhLaNa", "KhaMaNa", "GaGaNa", "KaMaNa", "MaKaNa", "MaBaNa",
                            "MaTaNa", "MaLaNa", "MaMaNa", "MaHtaNa", "MaThaNa", "NaMaMa",
                            "NgaHpaNa", "PaKhaKa", "PaMaNa", "PaHpaNa", "SaLaNa", "SaMaNa",
                            "SaHpaNa", "SaTaYa","SaPaWa","TaTaKa","ThaYaNa","HtaLaNa","YaNaKha","YaSaKa","KaHtaNa"
                            };
		        	for (String township : townships) {
		                cboTownship.addItem(township);
		            }     	
		        }
		        else if (selectedRegion.equals("9")) {
		        	String[] townships = {"AhMaYa", "AhMaZa", "KhaAhZa", "KhaMaSa", "KaPaTa", "KaSaNa",
                            "MaTaYa", "MaHaMa", "MaLaNa", "MaHtaLa", "MaKaNa", "MaKhaNa",
                            "MaThaNa", "NaHtaKa", "NgaThaYa", "NgaZaNa", "NyaOuNa", "PaThaKa",
                            "PaBaNa", "PaKaKha","PaOuLa","SaKaNa","ThaPaKa","TaTaOu","TaThaNa","ThaSaNa","WaTaNa"
                            ,"YaMaTha","TaKaTa","MaMaNa","DaKhaTha","LaWaNa","OuTuTha","PaBaTha","PaMaNa","TaKaNa","ZaBaTha","ZaYaTha"
                            };		        	
		        	for (String township : townships) {
		                cboTownship.addItem(township);
		            }     	
		        }
		        else if (selectedRegion.equals("10")) {
		        	String[] townships = {"BaLaNa", "KhaSaNa", "KhaZaNa", "KaMaYa", "KaHtaNa", "LaMaNa",
                            "MaLaMa", "MaDaNa", "PaMaNa", "ThaHpaYa", "ThaHtaNa", "YaMaNa"
                            };		        	
		        	for (String township : townships) {
		                cboTownship.addItem(township);
		            }     	
		        }
		        else if (selectedRegion.equals("11")) {
		        	String[] townships = {"AhMaNa", "BaThaTa", "GaMaNa", "KaHpaNa", "KaTaNa", "MaAhTa",
                            "MaTaNa", "MaPaNa", "MaAhNa", "MaOuNa", "MaPaTa", "PaNaTa","YaBaNa",
                            "YaThaTa","SaTaNa","ThaTaNa","TaKaNa","KaTaLa","TaPaWa","BaTaHta"
                            };		        	
		        	for (String township : townships) {
		                cboTownship.addItem(township);
		            }     	
		        }
		        else if (selectedRegion.equals("12")) {
		        	String[] townships = {"AhLaNa", "BaHaNa", "KaKaKa", "DaGaYa", "DaGaMa", "DaGaSa",
                            "DaGaTa", "DaGaNa", "DaLaNa", "DaPaNa", "LaThaYa", "LaMaNa","LaKaNa",
                            "MaBaNa","HtaTaPa","AhSaNa","KaMaYa","KaMaNa","KhaYaNa","KaKhaKa","KaTaTa",
                            "KaTaNa","KaMaTa","LaMaTa","LaThaNa","MaYaKa","MaGaDa","MaGaTa","OuKaMa","PaBaTa",
                            ""
                            };		        	
		        	for (String township : townships) {
		                cboTownship.addItem(township);
		            }     	
		        }
		}
		});
		AutoID();
		createTable();

		try {
			showList();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void AutoID() {
		try {
			lblStudentID.setText(AutoID.getAutoID("student_ID", "studentrecord", "ST-"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setColoumnWidth(int index, int width) {
		DefaultTableColumnModel tcm = (DefaultTableColumnModel) tblStudent.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}

	public void createTable() {
		dtm.addColumn("StudentID");
		dtm.addColumn("StaffID");
		dtm.addColumn("Name");
		dtm.addColumn("DOB");
		dtm.addColumn("NRC");
		dtm.addColumn("Address");
		dtm.addColumn("PhoneNO");
		dtm.addColumn("Email");
		dtm.addColumn("Entry Year");
		dtm.addColumn("Gender");
		dtm.addColumn("Father Name");
		dtm.addColumn("Gurdian PhoneNumber");
		dtm.addColumn("DataEntryDate");
		dtm.addColumn("Remark");
		tblStudent.setModel(dtm);
		setColoumnWidth(0, 100);
		setColoumnWidth(1, 100);
		setColoumnWidth(2, 100);
		setColoumnWidth(3, 100);
		setColoumnWidth(4, 100);
		setColoumnWidth(5, 100);
		setColoumnWidth(6, 100);
		setColoumnWidth(7, 200);
		setColoumnWidth(8, 100);
		setColoumnWidth(9, 100);
		setColoumnWidth(10, 100);
		setColoumnWidth(11, 100);
		setColoumnWidth(12, 100);
		setColoumnWidth(13, 100);
	}

//	// show all for table start
	
	public void showList() throws SQLException {
	    // Declare SimpleDateFormat at the start
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    // Assuming dtm (DefaultTableModel) is already initialized
	    String[] data = new String[14];

	    // Fetch records using the controller
	    StudentRecordController bc = new StudentRecordController();
	    List<StudentRecordModel> list = bc.selectall();

	    // Debugging: Check if the list is being populated
	    System.out.println("Number of records: " + list.size());

	    dtm.setRowCount(0); // Clear existing rows

	    // Iterate through the list and add rows to the table
	    for (StudentRecordModel bm : list) {
	        data[0] = bm.getStudentID();
	        data[1] = bm.getStaffID();
	        data[2] = bm.getStudentName();

	        // Handle invalid or null dates
	        data[3] = (bm.getStudentDOB() != null) ? dateFormat.format(bm.getStudentDOB()) : "N/A";

	        data[4] = bm.getStudentNRC();
	        data[5] = bm.getAddress();
	        data[6] = bm.getPhoneNumber();
	        data[7] = bm.getEmail();
	        data[8] = bm.getEntryYear();
	        data[9] = bm.getGender();
	        data[10] = bm.getFatherName();
	        data[11] = bm.getGuardianPhoneNumber();
	        data[12] = bm.getDataEntryDate();
	        data[13] = bm.getRemark();

	        // Add row to the table model
	        dtm.addRow(data);
	    }
	}

//
	// show one for table start
	
	public void showListone(String region, String township, String citizenType, String nrcNumber) throws SQLException {
	    String[] data = new String[14];
	    StudentRecordModel b = new StudentRecordModel();

	    // Construct the full NRC number
	    String fullNRC = region + "/" + township + citizenType + nrcNumber;

	    // Set the model's NRC value for search
	    b.setStudentNRC(fullNRC);

	    // Fetch records using the controller
	    StudentRecordController bc = new StudentRecordController();
	    List<StudentRecordModel> list = bc.selectone(b); // assuming selectone() filters by NRC number

	    dtm.setRowCount(0); // Clear the existing rows in the table

	    // Declare and initialize SimpleDateFormat for date formatting
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Define desired date format

	    // Populate the table with fetched data
	    for (StudentRecordModel bm : list) {
	        data[0] = bm.getStudentID();
	        data[1] = bm.getStaffID();
	        data[2] = bm.getStudentName();

	        // Format the studentDOB before adding to the data array
	        // Check if the date is not null before formatting it
	        data[3] = (bm.getStudentDOB() != null) ? dateFormat.format(bm.getStudentDOB()) : null;

	        data[4] = bm.getStudentNRC();
	        data[5] = bm.getAddress();
	        data[6] = bm.getPhoneNumber();
	        data[7] = bm.getEmail();
	        data[8] = bm.getEntryYear();
	        data[9] = bm.getGender();
	        data[10] = bm.getFatherName();
	        data[11] = bm.getGuardianPhoneNumber();
	        data[12] = bm.getDataEntryDate();
	        data[13] = bm.getRemark();

	        // Add the row to the table model
	        dtm.addRow(data);
	    }
	}



	public void displayNRC(String formattedNRC) {
		System.out.println("Formatted NRC: " + formattedNRC);
	}

	public void displayError(String message) {
		System.out.println("Error: " + message);
	}

	public void clear() {
		    btnSave.setEnabled(true);
		    btnUpdate.setEnabled(false);
		    btnDelete.setEnabled(false);

		    studentNameField.setText("");
		    studentDOBField.setDate(null);  // ✅ Clears the date picker
		    studentDOBField.requestFocusInWindow();  // Optional: focuses on the date field
		    if (cboRegion.getItemCount() > 0) cboRegion.setSelectedIndex(0);
		    cboTownship.removeAllItems();            // Clear existing items
		    cboTownship.addItem("Select");           // Add back "Select"
		    cboTownship.setSelectedIndex(0);         // Show "Select"
		    if (cboCitizenType.getItemCount() > 0) cboCitizenType.setSelectedIndex(0);
	
		    txtNRCNumber.setText("");
		    
		    if (searchRegion.getItemCount() > 0) searchRegion.setSelectedIndex(0);
		    searchTownship.removeAllItems();            // Clear existing items
		    searchTownship.addItem("Select");           // Add back "Select"
		    searchTownship.setSelectedIndex(0);         // Show "Select"
		    if (searchCitizenType.getItemCount() > 0) searchCitizenType.setSelectedIndex(0);
	
		    
		    searchNRCNumber.setText("");
		    addressField.setText("");
		    phoneNumberField.setText("");
		    emailField.setText("");
		    entryYearField.setText("");
		    genderComboBox.setSelectedIndex(0);
		    fatherNameField.setText("");
		    guardianPhoneNumberField.setText("");
		    dataEntryDateField.setText("");
		    remarkField.setText("");

		    // Set focus only once — usually the first input field
		    studentNameField.requestFocusInWindow();
		}

}
