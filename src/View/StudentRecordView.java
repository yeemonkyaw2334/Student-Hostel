package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
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

import Controller.StaffController;
import Controller.StudentRecordController;
import DBConnection.AutoID;
import DBConnection.Checking;
import Model.StaffModel;
import Model.StudentRecordModel;

public class StudentRecordView extends JFrame {
	private JTextField studentNameField;
	private JTextField studentDOBField;
	private JTextField addressField;
	private JTextField phoneNumberField;
	private JTextField emailField;
	private JTextField entryYearField;
	private JComboBox<String> genderComboBox;
	private JComboBox<String> cboRegion;
	private JComboBox<String> cboTownship;
	private JComboBox<String> cboCitizenType;
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
        getContentPane().setBackground(new Color(242, 242, 242)); // Soft gray background

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

		studentDOBField = new JTextField(20);
		studentDOBField.setBounds(172, 177, 253, 28);
		getContentPane().add(studentDOBField);

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
		fatherNameField.setBounds(176, 450, 249, 28);
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
		btnSave.setBounds(14, 604, 77, 28);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentRecordModel bm = new StudentRecordModel();
				StudentRecordController bc = new StudentRecordController();

				// Basic validation
				if (lblStudentID.getText().trim().equals("") || studentNameField.getText().trim().equals("")
						|| studentDOBField.getText().trim().equals("") || cboRegion.getSelectedItem().equals("Select")
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
				bm.setStudentDOB(studentDOBField.getText());

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
		btnUpdate.setBounds(122, 604, 89, 28);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentRecordModel bm = new StudentRecordModel();
				StudentRecordController bc = new StudentRecordController();
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to Update?", "Confrim",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					// Basic validation
					if (lblStudentID.getText().trim().equals("") || studentNameField.getText().trim().equals("")
							|| studentDOBField.getText().trim().equals("")
							|| cboRegion.getSelectedItem().equals("Select")
							|| cboTownship.getSelectedItem().equals("Select")
							|| cboCitizenType.getSelectedItem().equals("Select")
							|| txtNRCNumber.getText().trim().equals("") || addressField.getText().trim().equals("")
							|| phoneNumberField.getText().trim().equals("") || emailField.getText().trim().equals("")
							|| entryYearField.getText().trim().equals("")
							|| genderComboBox.getSelectedItem().equals("Select")
							|| fatherNameField.getText().trim().equals("")
							|| guardianPhoneNumberField.getText().trim().equals("")
							|| dataEntryDateField.getText().trim().equals("")
							|| remarkField.getText().trim().equals("")) {

						JOptionPane.showMessageDialog(null, "There is a blank field!", "Fail",
								JOptionPane.ERROR_MESSAGE);
						return; // stop execution
					} else {
						bm.setStudentID(lblStudentID.getText().toString());
						bm.setStaffID(lblStaffIDAuto.getText().trim());
						bm.setStudentName(studentNameField.getText().trim());
						bm.setStudentDOB(studentDOBField.getText().trim());
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
		btnClear.setBounds(176, 661, 77, 28);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
				AutoID();
			}
		});
		getContentPane().add(btnClear);

		JButton btnClose = new JButton("Close");
		btnClose.setBounds(87, 661, 77, 28);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnClose);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(444, 171, 802, 435);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 23, 754, 388);
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
				studentDOBField.setText((String) tblStudent.getValueAt(r, 3));
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

				btnSave.setEnabled(true);
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);

				studentNameField.requestFocus();
				studentNameField.selectAll();
				studentDOBField.requestFocus();
				studentDOBField.selectAll();
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
		panel.setBounds(514, 47, 436, 119);
		getContentPane().add(panel);
		panel.setLayout(null);

		StudentNRC = new JLabel("Student NRC:");
		StudentNRC.setBounds(57, 40, 104, 14);
		panel.add(StudentNRC);
        
		JComboBox<String> searchRegion = new JComboBox<>();
		for (int i = 1; i <= 14; i++) {
		    searchRegion.addItem(String.valueOf(i));
		}
		searchRegion.setBounds(187, 33, 50, 28);
		panel.add(searchRegion);

		searchTownship = new JComboBox<>();
		String[] townships = { "PaKaNa", "MaKaNa", "KaLaNa", "BaLaNa", "DaGaNa" };
		for (String t : townships) {
		    searchTownship.addItem(t);
		}
		searchTownship.setBounds(233, 33, 70, 28);
		panel.add(searchTownship);

		searchCitizenType = new JComboBox<>();
		String[] types = { "(N)", "(AC)", "(NC)" };
		for (String t : types) {
		    searchCitizenType.addItem(t);
		}
		searchCitizenType.setBounds(302, 33, 60, 28);
		panel.add(searchCitizenType);

		searchNRCNumber = new JTextField();
		searchNRCNumber.setBounds(361, 33, 65, 28);
		panel.add(searchNRCNumber);

		
		JButton btnSearch = new JButton("Search");
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
		btnSearch.setBounds(47, 80, 94, 28);
		panel.add(btnSearch);

		JButton btnShowall = new JButton("ShowAll");
		btnShowall.setBounds(137, 80, 106, 28);
		panel.add(btnShowall);

		btnRegister = new JButton("Register");
		btnRegister.setBounds(122, 646, 89, 25);
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

		cboRegion = new JComboBox();
		for (int i = 1; i <= 14; i++) {
			cboRegion.addItem(String.valueOf(i));
		}
		cboRegion.setBounds(172, 216, 45, 28);
		getContentPane().add(cboRegion);

		cboTownship = new JComboBox();
		
		// Assuming you want exactly 422 townships:
		String[] townshipList = new String[422];
		String[] townshiplist = { "PaKaNa", "MaKaNa", "KaLaNa", "BaLaNa", "DaGaNa" }; // You can expand this list
		
		// Fill the array with sample townships
		for (int i = 0; i < 422; i++) {
		    townshipList[i] = "Township " + (i + 1); // Or you can use actual names
		}

		// Populate the JComboBox with these items
		for (String township : townshipList) {
		    cboTownship.addItem(township);
	}
		cboTownship.setBounds(221, 216, 70, 28);
		getContentPane().add(cboTownship);

		cboCitizenType = new JComboBox();
		types = new String[]{ "(N)", "(AC)", "(NC)" };
		for (String t : types) {
			cboCitizenType.addItem(t);
		}
		cboCitizenType.setBounds(294, 216, 65, 28);
		getContentPane().add(cboCitizenType);

		txtNRCNumber = new JTextField();
		txtNRCNumber.setBounds(360, 216, 65, 28);
		getContentPane().add(txtNRCNumber);
		txtNRCNumber.setColumns(10);
		
		btnShowall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showList();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
		setColoumnWidth(5, 50);
		setColoumnWidth(6, 50);
		setColoumnWidth(7, 50);
		setColoumnWidth(8, 50);
		setColoumnWidth(9, 100);
		setColoumnWidth(10, 100);
		setColoumnWidth(11, 100);
		setColoumnWidth(12, 100);
		setColoumnWidth(13, 100);
	}

//	// show all for table start
	public void showList() throws SQLException {
		String[] data = new String[14];
		StudentRecordController bc = new StudentRecordController();
		List<StudentRecordModel> list = bc.selectall();
		dtm.setRowCount(0);
		for (StudentRecordModel bm : list) {
			data[0] = bm.getStudentID();
			data[1] = bm.getStaffID();
			data[2] = bm.getStudentName();
			data[3] = bm.getStudentDOB();
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

			dtm.addRow(data);
		}
	}// show all for table end
//
//	// show one for table start
	public void showListone(String region, String township, String citizenType, String nrcNumber) throws SQLException {
	    String data[] = new String[14];
	    StudentRecordModel b = new StudentRecordModel();

	    // Construct the full NRC number
	    String fullNRC = region + "/" + township + citizenType + nrcNumber;

	    // Set the model's NRC value for search
	    b.setStudentNRC(fullNRC);

	    // Fetch records using the controller
	    StudentRecordController bc = new StudentRecordController();
	    List<StudentRecordModel> list = bc.selectone(b); // assuming selectone() filters by NRC number

	    dtm.setRowCount(0); // Clear the existing rows in the table

	    // Populate the table with fetched data
	    for (StudentRecordModel bm : list) {
	        data[0] = bm.getStudentID();
	        data[1] = bm.getStaffID();
	        data[2] = bm.getStudentName();
	        data[3] = bm.getStudentDOB();
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
		studentNameField.requestFocus(true);

		studentDOBField.setText("");
		studentDOBField.requestFocus(true);

		cboRegion.setSelectedIndex(0);
		cboRegion.requestFocus(true);

		cboTownship.setSelectedIndex(0);
		cboTownship.requestFocus(true);

		cboCitizenType.setSelectedIndex(0);
		cboCitizenType.requestFocus(true);

		txtNRCNumber.setText("");
		txtNRCNumber.requestFocus(true);

		addressField.setText("");
		addressField.requestFocus(true);

		phoneNumberField.setText("");
		phoneNumberField.requestFocus(true);

		emailField.setText("");
		emailField.requestFocus(true);

		entryYearField.setText("");
		entryYearField.requestFocus(true);

		genderComboBox.setSelectedIndex(0);
		genderComboBox.requestFocus(true);

		fatherNameField.setText("");
		fatherNameField.requestFocus(true);

		guardianPhoneNumberField.setText("");
		guardianPhoneNumberField.requestFocus(true);

		dataEntryDateField.setText("");
		dataEntryDateField.requestFocus(true);

		remarkField.setText("");
		remarkField.requestFocus(true);
	}
}
