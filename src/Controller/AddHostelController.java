package Controller;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

import DBConnection.DBConfig;
import Model.AddHostelModel;
 
public class AddHostelController {

    public static Connection con = null;

    static {
        DBConfig dbConfig = new DBConfig();
        try {
            con = dbConfig.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Insert Fail, Internal DB error", "Fail", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public int insert(AddHostelModel dain) {
        int result = 0;
        String sql = "INSERT INTO student_hostel.hostel (hostel_id, hostel_name, hostel_capacity, hostel_category,hostel_address) VALUES (?, ?, ?, ?, ?)";
        try  {
        	PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        	 ps.setString(1, dain.getHostel_id());
            ps.setString(2, dain.getHostel_name());
            ps.setInt(3, dain.getRoom_capacity());
            ps.setString(4, dain.getHostel_category());
            ps.setString(5, dain.getHostel_address());

            result = ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Duplicate entry for hostel ID or name.", "Insert Failed", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Failed to save hostel data.\nPlease check your input or database connection.",
                "Insert Failed", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    public int update(AddHostelModel model) throws SQLException {
        String sql = "UPDATE hostel SET hostel_name=?, hostel_capacity=?, hostel_category=?, hostel_address=? WHERE hostel_id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
        	ps.setString(1, model.getHostel_name());
        	ps.setInt(2, model.getRoom_capacity());
        	ps.setString(3, model.getHostel_category());
        	ps.setString(4, model.getHostel_address());  // FIXED
        	ps.setString(5, model.getHostel_id());

            return ps.executeUpdate();
        }
    }

    public int delete(AddHostelModel dain) {
        int result = 0;
        String sql = "DELETE FROM student_hostel.hostel WHERE hostel_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dain.getHostel_id());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Error: Could not delete hostel.", "Fail", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    public List<AddHostelModel> selectall() throws SQLException {
        List<AddHostelModel> list = new ArrayList<>();
        String sql = "SELECT * FROM student_hostel.hostel ORDER BY hostel_id DESC";
        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AddHostelModel ahm = new AddHostelModel();
                ahm.setHostel_id(rs.getString("hostel_id"));
                ahm.setHostel_name(rs.getString("hostel_name"));
                ahm.setRoom_capacity(rs.getInt("hostel_capacity"));
                ahm.setHostel_category(rs.getString("hostel_category"));
                ahm.setHostel_address(rs.getString("hostel_address"));  // set hostel address properly

                list.add(ahm);
            }
        }
        return list;
    }

    public List<AddHostelModel> selectone(AddHostelModel dain) throws SQLException {
        List<AddHostelModel> list = new ArrayList<>();
        String sql = "SELECT * FROM student_hostel.hostel WHERE hostel_name LIKE ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%"+dain.getHostel_name() + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AddHostelModel ahm = new AddHostelModel();
                    ahm.setHostel_id(rs.getString("hostel_id"));
                    ahm.setHostel_name(rs.getString("hostel_name"));
                    ahm.setRoom_capacity(rs.getInt("hostel_capacity"));
                    ahm.setHostel_category(rs.getString("hostel_category"));
                    ahm.setHostel_address(rs.getString("hostel_address"));  // set hostel address properly

                    list.add(ahm);
                }
            }
        }
        return list;
    }

    public String searchTypeName(AddHostelModel dain) {
        String result = null;
        String sql = "SELECT hostel_name FROM student_hostel.hostel WHERE hostel_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dain.getHostel_id());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = rs.getString("hostel_name");
                } else {
                    System.out.println("This Hostel is not found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String searchTypeId(AddHostelModel dain) {
        String result = null;
        String sql = "SELECT hostel_id FROM student_hostel.hostel WHERE hostel_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dain.getHostel_id());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = rs.getString("hostel_id");
                } else {
                    System.out.println("This Hostel is not found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean isduplicate(AddHostelModel model) throws SQLException {
        String sql = "SELECT COUNT(*) FROM hostel WHERE hostel_name = ? AND hostel_id != ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, model.getHostel_name());
            ps.setString(2, model.getHostel_id());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
