package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler {

    private static final String URL = "jdbc:mysql://localhost:3306/studentdetails";
    private static final String USER = "root";
    private static final String PASS = "0000";

    public static void insertStudent(String name, String roll, String course, String sem, String email, String phone) {
        String query = "INSERT INTO students (name, roll_number, course, semester, email, phone) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, roll);
            stmt.setString(3, course);
            stmt.setString(4, sem);
            stmt.setString(5, email);
            stmt.setString(6, phone);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Student data inserted into MySQL successfully.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Error inserting student data into MySQL:");
            e.printStackTrace();
        }
    }
}
