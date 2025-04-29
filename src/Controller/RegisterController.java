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
import Model.RegisterModel;
import Model.StudentRecordModel;
public class RegisterController {
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
	public int insert(RegisterModel dain) {
		int result =0;
		String sql = "insert into student_hostel.registration (registration_ID,staff_ID,student_ID,student_Name,hostel_type,registration_date,registration_status) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getRegistrationID());
			ps.setString(2, dain.getStaffID());			
			ps.setString(3, dain.getStudentID());
			ps.setString(4, dain.getStudentName());
			ps.setString(5, dain.getHostelType());
			ps.setDate(6,dain.getRegistrationDate());
			ps.setString(7, dain.getRegistrationStatus());
			
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Fail insert,Inter Error","Fail",JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
	public int update(RegisterModel dain) {
	    int result = 0;
	    String sql = "update student_hostel.registration set registration_ID=?, staff_ID=?, student_ID=?, hostel_type=?, registration_date=?, registration_status=? where registration_ID=?";
	    
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        
	        // Set all the required parameters
	        ps.setString(1, dain.getRegistrationID()); // registration_ID (new)
	        ps.setString(2, dain.getStaffID());         // staff_ID
	        ps.setString(3, dain.getStudentID());       // student_ID
	        ps.setString(4, dain.getHostelType());      // hostel_type
	        ps.setDate(5, dain.getRegistrationDate());  // registration_date
	        ps.setString(6, dain.getRegistrationStatus()); // registration_status
	        
	        // Add the missing 7th parameter: the old registration_ID (for WHERE clause)
	        ps.setString(7, dain.getRegistrationID());  // registration_ID (old)

	        System.out.println(dain.getStaffID());
	        
	        // Execute the update
	        result = ps.executeUpdate();
	        System.out.println("Update result: " + result);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Fail update, Internal Error", "Fail", JOptionPane.ERROR_MESSAGE);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e);
	    }
	    return result;
	}

	
	//DELETR METHOD START
		public int delete(RegisterModel dain) {
		    int result = 0;
		    String sql = "DELETE FROM student_hostel.registration WHERE registration_ID = ?";
		    try {
		        PreparedStatement ps = con.prepareStatement(sql);
		        System.out.println("Deleting registration ID: " + dain.getRegistrationID()); // Debug
		        ps.setString(1, dain.getRegistrationID().trim());
		        
		        result = ps.executeUpdate();
		        System.out.println("Delete result: " + result); // Should be 1 if successful
		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Fail delete, Internal Error", "Fail", JOptionPane.ERROR_MESSAGE);
		    }
		    return result;
		}
		//DELETR METHOD END
		
		
	public List<RegisterModel> selectall()throws SQLException{
		List<RegisterModel> list = new ArrayList<RegisterModel>();
		String sql = "select * from student_hostel.registration order by registration_ID desc";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			RegisterModel bm = new RegisterModel();
			bm.setRegistrationID(rs.getString("registration_ID"));
			bm.setStaffID(rs.getString("staff_ID"));
			bm.setStudentID(rs.getString("student_ID"));
			bm.setStudentName(rs.getString("student_Name"));
			bm.setHostelType(rs.getString("hostel_type"));
			// Convert String to LocalDate for registration_date
            String registrationDateString = rs.getString("registration_date");

//            if (registrationDateString != null && !registrationDateString.isEmpty()) {
//                // Adjust the format as per your database date format (e.g., yyyy-MM-dd)
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                LocalDate registrationDate = LocalDate.parse(registrationDateString, formatter);
//                bm.setRegistrationDate(null);
//            } else {
//                bm.setRegistrationDate(null); // If no date, set it as null
//            }
            bm.setRegistrationDate(rs.getDate("registration_date"));
            
		    bm.setRegistrationStatus(rs.getString("registration_status"));
		    list.add(bm);
		}
		return list;
	}
	
	public List<RegisterModel> selectone(RegisterModel dain) throws SQLException{
		List<RegisterModel> list = new ArrayList<RegisterModel>();
		String sql = "select * from student_hostel.registration where student_Name like ? order by registration_ID desc";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, dain.getStudentName()+"%");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			RegisterModel bm = new RegisterModel();
			
			bm.setRegistrationID(rs.getString("registration_ID"));
			bm.setStudentID(rs.getString("student_ID"));
			bm.setStaffID(rs.getString("staff_ID"));
		    bm.setHostelType(rs.getString("hostel_type"));
		 // Convert String to LocalDate for registration_date
            String registrationDateString = rs.getString("registration_date");

//            if (registrationDateString != null && !registrationDateString.isEmpty()) {
//                // Adjust the format as per your database date format (e.g., yyyy-MM-dd)
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                LocalDate registrationDate = LocalDate.parse(registrationDateString, formatter);
//                bm.setRegistrationDate(registrationDate);
//            } else {
//                bm.setRegistrationDate(null); // If no date, set it as null
//            }
            bm.setRegistrationDate(rs.getDate("registration_date"));
            
		    bm.setRegistrationStatus(rs.getString("registration_status"));
			list.add(bm);
		}
		return list;
	}
	
	//SEARCH NAME START
	public String searchSearchName(StudentRecordModel dain) {
		String result=null;
		String sql = "select student_name from student_hostel.studentrecord where student_ID =?";
		PreparedStatement ps ;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getStudentID());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getString("student_name");
			}else{
				System.out.println("This student is not found");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	//SEARCH NAME END
	
	//SEARCH STUDENTID START
	public String searchStudentId(StudentRecordModel dain) {
		String result = null;
		String sql = "select student_ID from student_hostel.studentrecord where student_name=?";
		try {
			PreparedStatement ps =(PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getStudentName());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getString("studentID") ;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	    return result;
	}
	//SEARCH STUDENTID END
	
	//DUPLICATE METHOD START
	public boolean isDuplicate(RegisterModel dain) throws SQLException{
		boolean duplicate = false;
		String sql = "select * from student_hostel.registration where student_ID=?";
		PreparedStatement ps =(PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, dain.getStudentID());
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
