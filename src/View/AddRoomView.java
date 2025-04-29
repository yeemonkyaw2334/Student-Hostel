package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.SystemColor;

public class AddRoomView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRoomView frame = new AddRoomView();
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
	public AddRoomView() {
		setForeground(new Color(0, 0, 0));
		setFont(new Font("Myanmar3", Font.BOLD, 14));
		setAlwaysOnTop(true);
		setTitle("AddRoomView");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 559);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Hostel Management System");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Myanmar3", Font.BOLD, 20));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setBounds(105, 10, 354, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Room ID :");
		lblNewLabel_1.setFont(new Font("Myanmar3", Font.BOLD, 14));
		lblNewLabel_1.setBounds(58, 106, 106, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Room No : ");
		lblNewLabel_1_1.setFont(new Font("Myanmar3", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(58, 216, 106, 32);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Floor :");
		lblNewLabel_1_1_1.setFont(new Font("Myanmar3", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(58, 270, 106, 32);
		contentPane.add(lblNewLabel_1_1_1);
		
		textField = new JTextField();
		textField.setBounds(214, 216, 135, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(214, 271, 135, 32);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Hostel Name : ");
		lblNewLabel_2.setFont(new Font("Myanmar3", Font.BOLD, 14));
		lblNewLabel_2.setBounds(58, 161, 106, 32);
		contentPane.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(176, 224, 230));
		comboBox.setBounds(214, 161, 135, 32);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setForeground(new Color(255, 250, 250));
		btnNewButton.setBackground(new Color(47, 79, 79));
		btnNewButton.setMnemonic('A');
		btnNewButton.setFont(new Font("Myanmar3", Font.PLAIN, 14));
		btnNewButton.setBounds(58, 335, 72, 32);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.setForeground(new Color(255, 250, 250));
		btnNewButton_1.setBackground(new Color(47, 79, 79));
		btnNewButton_1.setMnemonic('E');
		btnNewButton_1.setFont(new Font("Myanmar3", Font.PLAIN, 14));
		btnNewButton_1.setBounds(172, 335, 72, 32);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.setForeground(new Color(255, 250, 250));
		btnNewButton_2.setBackground(new Color(47, 79, 79));
		btnNewButton_2.setMnemonic('C');
		btnNewButton_2.setFont(new Font("Myanmar3", Font.PLAIN, 14));
		btnNewButton_2.setBounds(277, 335, 72, 32);
		contentPane.add(btnNewButton_2);
	}

}
