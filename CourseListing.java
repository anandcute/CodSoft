//Course listing

package CodSoft;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CourseListing {
    public static void listCourses() {
        String query = "SELECT * FROM courses";
        
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                System.out.println("Course Code: " + rs.getString("course_code") +
                                   ", Title: " + rs.getString("title") +
                                   ", Description: " + rs.getString("description") +
                                   ", Capacity: " + rs.getInt("capacity") +
                                   ", Schedule: " + rs.getString("schedule") +
                                   ", Available Slots: " + rs.getInt("available_slots"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
