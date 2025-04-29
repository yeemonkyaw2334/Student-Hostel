package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import DBConnection.DBConfig;
import Model.StaffModel;

public class StaffController {
	private static Connection conn;
	static {
		DBConfig dbConfig = new DBConfig();
		try {
				conn = dbConfig.getConnection();
			
		}catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, " database operation failed because of connection .", "database error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public StaffModel SelectByName(String name) throws SQLException {
		StaffModel staff = new StaffModel();
		String sql = "select * from student_hostel.staff where staff_name = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			staff.setStaffID(rs.getString("staff_ID"));
			staff.setStaffName(rs.getString("staff_name"));
			staff.setStaffNRC(rs.getString("staff_NRC"));
			staff.setStaffDOB(rs.getString("staff_DOB"));
			staff.setStaffPosition(rs.getString("staff_position"));
			staff.setStaffAddress(rs.getString("staff_address"));
			staff.setStaffPhoneNumber(rs.getString("staff_phonenumber"));
			staff.setStaffGender(rs.getString("staff_gender"));
			staff.setStaffPassword(rs.getString("staff_password"));
		}
		return staff;
	}

//	public String searchStaffName(StaffModel dain) {
//		String result=null;
//		String sql = "select staff_name from student_hostel.staff where staff_id =?";
//		PreparedStatement ps ;
//		try {
//			ps = (PreparedStatement) conn.prepareStatement(sql);
//			ps.setString(1, dain.getStaffID());
//			ResultSet rs = ps.executeQuery();
//			if(rs.next()) {
//				result = rs.getString("staff_name");
//			}else{
//				System.out.println("This Staff name is not found");
//			}
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//		return result;
//	}
}
