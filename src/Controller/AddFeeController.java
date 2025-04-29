package Controller;

import java.util.*;
import java.sql.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import DBConnection.DBConfig;
import Model.AddFeeModel;
public class AddFeeController {
	public static Connection con = null;
	static {
		DBConfig cls = new DBConfig();
		try {
			con = (Connection) cls.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public int insert(AddFeeModel fm) throws SQLException {
	    String sql = "INSERT INTO fee (fee_ID, staff_ID, fee_type, amount) VALUES (?, ?, ?, ?)";
	    PreparedStatement ps = con.prepareStatement(sql);
	    
	    ps.setString(1, fm.getFeeID());  // First parameter
	    ps.setString(2, fm.getStaffID());  // Second parameter
	    ps.setString(3, fm.getFeeType());  // Third parameter
	    ps.setInt(4, fm.getAmount());  // Fourth parameter

	    // Make sure no fifth parameter is being set accidentally
	    return ps.executeUpdate();  // Returns 1 if successful
	}

	public int update(AddFeeModel fm) throws SQLException {
	    String sql = "UPDATE fee SET staff_ID = ?, fee_type = ?, amount = ? WHERE fee_ID = ?";

	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, fm.getStaffID()); // staff_ID
	        ps.setString(2, fm.getFeeType()); // fee_type
	        ps.setInt(3, fm.getAmount());     // amount
	        ps.setString(4, fm.getFeeID());   // fee_ID (this is the condition to identify the row to update)

	        int rowsUpdated = ps.executeUpdate();
	        return rowsUpdated;
	    }
	}

		
	public List<AddFeeModel> selectall()throws SQLException{
		List<AddFeeModel> list = new ArrayList<AddFeeModel>();
		String sql = "select * from student_hostel.fee order by fee_ID desc";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			AddFeeModel FM = new AddFeeModel();
			FM.setFeeID(rs.getString("fee_ID"));
			FM.setStaffID(rs.getString("staff_ID"));
			FM.setFeeType(rs.getString("fee_type"));        
		    FM.setAmount(rs.getInt("amount"));
		    list.add(FM);
		}
		return list;
	}
	
//	public List<AddFeeModel> selectone(AddFeeModel dain) throws SQLException{
//		List<AddFeeModel> list = new ArrayList<AddFeeModel>();
//		String sql = "select * from student_hostel.fee where student_Name like ? order by registration_ID desc";
//		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
//		ps.setString(1, dain.getStudentName()+"%");
//		ResultSet rs = ps.executeQuery();
//		while(rs.next()) {
//			AddFeeModel FM = new AddFeeModel();
//			
//			FM.setFeeID(rs.getString("registration_ID"));
//			FM.setStaffID(rs.getString("staff_ID"));
//			FM.setFeeType(rs.getString("hostel_type"));        
//		    FM.setAmount(rs.getInt("amount"));
//		    list.add(FM);
//		}
//		return list;
//	}
	
//	//SEARCH NAME START
//	public String searchSearchName(AddFeeModel dain) {
//		String result=null;
//		String sql = "select student_name from student_hostel.fee where fee_ID =?";
//		PreparedStatement ps ;
//		try {
//			ps = (PreparedStatement) con.prepareStatement(sql);
//			ps.setString(1, dain.getFeeID());
//			ResultSet rs = ps.executeQuery();
//			if(rs.next()) {
//				result = rs.getString("student_name");
//			}else{
//				System.out.println("This student is not found");
//			}
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//		return result;
//	}
//	//SEARCH NAME END
	
	//SEARCH STUDENTID START
//	public String searchStudentId(StudentRecordModel dain) {
//		String result = null;
//		String sql = "select student_ID from student_hostel.studentrecord where student_name=?";
//		try {
//			PreparedStatement ps =(PreparedStatement) con.prepareStatement(sql);
//			ps.setString(1, dain.getStudentName());
//			ResultSet rs = ps.executeQuery();
//			if(rs.next()) {
//				result = rs.getString("studentID") ;
//			}
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//	    return result;
//	}
//	//SEARCH STUDENTID END
	
	//DUPLICATE METHOD START
	public boolean isDuplicate(AddFeeModel dain) throws SQLException{
		boolean duplicate = false;
		String sql = "select * from student_hostel.fee where fee_ID=?";
		PreparedStatement ps =(PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, dain.getFeeID());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			duplicate = true;
		}else {
			duplicate = false;
		}
		return duplicate;
	}

}
//DUPLICATE METHOD END
