package DBConnection;

import java.sql.*;

public class AutoID {
    static DBConfig connect = new DBConfig();

    // Method to generate auto ID with a prefix
    public static String getAutoID(String field, String table, String prefix) throws ClassNotFoundException {
        String autoID = "";

        try {
            if (table.equals("registration")|| table.equals("fee")) {           	
                autoID = connect.getPrimaryKey(field, table, prefix); // Assuming this returns the next ID
            } else {
                autoID = connect.getPrimaryKey2(field, table, prefix); // Fallback for other tables
            }
        } catch (Exception e) {
            // Handle any other exceptions
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }

        return autoID; // Return generated ID
    }
}
