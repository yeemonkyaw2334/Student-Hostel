package Controller;

import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import DBConnection.DBConfig;
import Model.StaffModel;
import Model.StudentRecordModel;
public class StudentRecordController {
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
	public int insert(StudentRecordModel dain) {
		int result =0;
		String sql = "insert into student_hostel.studentrecord (student_ID,staff_ID,student_name,student_NRC,student_DOB,student_address,student_phonenumber,student_email,student_entryyear,student_gender,student_fathername,student_guardianphonenumber,student_dataentrydate,student_remark) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getStudentID());
			ps.setString(2, dain.getStaffID());
			ps.setString(3, dain.getStudentName());
			ps.setString(4, dain.getStudentNRC());
			ps.setDate(5, new java.sql.Date(dain.getStudentDOB().getTime()));
			ps.setString(6, dain.getAddress());
			ps.setString(7, dain.getPhoneNumber());
			ps.setString(8, dain.getEmail());
			ps.setString(9, dain.getEntryYear());
			ps.setString(10, dain.getGender());
			ps.setString(11, dain.getFatherName());
			ps.setString(12, dain.getGuardianPhoneNumber());
			ps.setString(13, dain.getDataEntryDate());
			ps.setString(14, dain.getRemark());
			
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Fail insert,Inter Error","Fail",JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
	public int update(StudentRecordModel dain) {
		int result =0;
		String sql = "update student_hostel.studentrecord set student_name=?, student_NRC=?, student_DOB=?, student_address=?, student_phonenumber=?, student_email=?, student_entryyear=?, student_gender=?, student_fathername=?, student_guardianphonenumber=?, student_dataentrydate = ?, student_remark=? where student_ID=?";
		//
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, dain.getStudentName());
			ps.setString(2, dain.getStudentNRC());
			ps.setDate(3, new java.sql.Date(dain.getStudentDOB().getTime()));
			ps.setString(4, dain.getAddress());
			ps.setString(5, dain.getPhoneNumber());
			ps.setString(6, dain.getEmail());
			ps.setString(7, dain.getEntryYear());
			ps.setString(8, dain.getGender());
			ps.setString(9, dain.getFatherName());
			ps.setString(10, dain.getGuardianPhoneNumber());
			ps.setString(11, dain.getDataEntryDate());
			ps.setString(12, dain.getRemark());
			ps.setString(13, dain.getStudentID());
			System.out.println(dain.getStudentName() + " " + dain.getStudentID());
			System.out.println(dain.getStaffID());
			result = ps.executeUpdate();
			System.out.println("===" + result);
		}catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Fail update,Inter Error","Fail",JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	//DELETR METHOD START
		public int delete(StudentRecordModel dain) {
		    int result = 0;
		    String sql = "DELETE FROM student_hostel.studentrecord WHERE student_ID = ?";
		    try {
		        PreparedStatement ps = con.prepareStatement(sql);
		        System.out.println("Deleting student ID: " + dain.getStudentID()); // Debug
		        ps.setString(1, dain.getStudentID().trim());
		        
		        result = ps.executeUpdate();
		        System.out.println("Delete result: " + result); // Should be 1 if successful
		    } catch (SQLException e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Fail delete, Internal Error", "Fail", JOptionPane.ERROR_MESSAGE);
		    }
		    return result;
		}
		//DELETR METHOD END
		
		
		public List<StudentRecordModel> selectall() throws SQLException {
		    List<StudentRecordModel> list = new ArrayList<StudentRecordModel>();
		    String sql = "select * from student_hostel.studentrecord order by student_ID desc";
		    PreparedStatement ps = con.prepareStatement(sql);
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		        StudentRecordModel bm = new StudentRecordModel();
		        bm.setStudentID(rs.getString("student_ID"));
		        bm.setStaffID(rs.getString("staff_ID"));
		        bm.setStudentName(rs.getString("student_name"));
		        
		        // Get the student DOB as a java.sql.Date
		        bm.setStudentDOB(rs.getDate("student_DOB"));  // This is a java.sql.Date
		        
		        bm.setStudentNRC(rs.getString("student_NRC"));
		        bm.setAddress(rs.getString("student_address"));
		        bm.setPhoneNumber(rs.getString("student_phonenumber"));
		        bm.setEmail(rs.getString("student_email"));
		        bm.setEntryYear(rs.getString("student_entryyear"));
		        bm.setGender(rs.getString("student_gender"));
		        bm.setFatherName(rs.getString("student_fathername"));
		        bm.setGuardianPhoneNumber(rs.getString("student_guardianphonenumber"));
		        bm.setDataEntryDate(rs.getString("student_dataentrydate"));
		        bm.setRemark(rs.getString("student_remark"));
		        
		        list.add(bm);
		    }
		    return list;
		}


	
	public List<StudentRecordModel> selectone(StudentRecordModel dain) throws SQLException{
		List<StudentRecordModel> list = new ArrayList<StudentRecordModel>();
		String sql = "select * from student_hostel.studentrecord where student_NRC like ? order by student_ID desc";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, dain.getStudentNRC()+"%");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			StudentRecordModel bm = new StudentRecordModel();
			bm.setStudentID(rs.getString("student_ID"));
			bm.setStaffID(rs.getString("staff_ID"));
		    bm.setStudentName(rs.getString("student_name"));
		    
		    Date dob = rs.getDate("student_DOB");
		    bm.setStudentDOB(dob);
		    
		    bm.setStudentNRC(rs.getString("student_NRC"));
		    bm.setAddress(rs.getString("student_address"));
		    bm.setPhoneNumber(rs.getString("student_phonenumber"));
		    bm.setEmail(rs.getString("student_email"));
		    bm.setEntryYear(rs.getString("student_entryyear"));
		    bm.setGender(rs.getString("student_gender"));
		    bm.setFatherName(rs.getString("student_fathername"));
		    bm.setGuardianPhoneNumber(rs.getString("student_guardianphonenumber"));
		    bm.setDataEntryDate(rs.getString("student_dataentrydate"));
		    bm.setRemark(rs.getString("student_remark"));
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
	public boolean isduplicate(StudentRecordModel dain) throws SQLException{
		boolean duplicate = false;
		String sql = "select * from student_hostel.studentrecord where student_name=?";
		PreparedStatement ps =(PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, dain.getStudentName());
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
