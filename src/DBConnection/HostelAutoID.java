package DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HostelAutoID {

    public static String getHostelAutoID(String field, String table, String prefix) throws ClassNotFoundException, SQLException {
        Connection con = new DBConfig().getConnection();
        String query = "SELECT " + field + " FROM " + table + " ORDER BY " + field + " DESC LIMIT 1";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        String newID;
        
        if (rs.next()) {
            String lastID = rs.getString(1); // example: HS-00001
            int number = Integer.parseInt(lastID.substring(prefix.length())); // cut "HS-" part and get number
            number++; // increase 1
            newID = prefix + String.format("%05d", number); // add leading zeros
        } else {
            newID = prefix + "00001"; // if no hostel, start HS-00001
        }
        
        rs.close();
        stmt.close();
        return newID;
    }
}
