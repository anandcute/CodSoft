package CodSoft;
//Class removal
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRemoval {
    public static void dropCourse(String studentId, String courseCode) {
        String getRegisteredCoursesQuery = "SELECT registered_courses FROM students WHERE student_id = ?";
        String updateRegisteredCoursesQuery = "UPDATE students SET registered_courses = ? WHERE student_id = ?";
        String updateSlotsQuery = "UPDATE courses SET available_slots = available_slots + 1 WHERE course_code = ?";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement getCoursesStmt = conn.prepareStatement(getRegisteredCoursesQuery);
             PreparedStatement updateCoursesStmt = conn.prepareStatement(updateRegisteredCoursesQuery);
             PreparedStatement updateSlotsStmt = conn.prepareStatement(updateSlotsQuery)) {
            
            // Get registered courses
            getCoursesStmt.setString(1, studentId);
            ResultSet rs = getCoursesStmt.executeQuery();
            if (rs.next()) {
                String registeredCourses = rs.getString("registered_courses");
                if (registeredCourses != null && registeredCourses.contains(courseCode)) {
                    // Update registered courses
                    registeredCourses = registeredCourses.replace(courseCode, "").replace(",,", ",").replaceAll("^,|,$", "");
                    updateCoursesStmt.setString(1, registeredCourses);
                    updateCoursesStmt.setString(2, studentId);
                    updateCoursesStmt.executeUpdate();
                    
                    // Update available slots
                    updateSlotsStmt.setString(1, courseCode);
                    updateSlotsStmt.executeUpdate();
                    
                    System.out.println("Course dropped successfully!");
                } else {
                    System.out.println("You are not registered for this course.");
                }
            } else {
                System.out.println("No courses to drop.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
