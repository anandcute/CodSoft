package CodSoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRegistration {
    public static void registerCourse(String studentId, String courseCode) {
        String checkSlotsQuery = "SELECT available_slots FROM courses WHERE course_code = ?";
        String updateSlotsQuery = "UPDATE courses SET available_slots = available_slots - 1 WHERE course_code = ?";
        String getRegisteredCoursesQuery = "SELECT registered_courses FROM students WHERE student_id = ?";
        String updateRegisteredCoursesQuery = "UPDATE students SET registered_courses = ? WHERE student_id = ?";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement checkSlotsStmt = conn.prepareStatement(checkSlotsQuery);
             PreparedStatement updateSlotsStmt = conn.prepareStatement(updateSlotsQuery);
             PreparedStatement getCoursesStmt = conn.prepareStatement(getRegisteredCoursesQuery);
             PreparedStatement updateCoursesStmt = conn.prepareStatement(updateRegisteredCoursesQuery)) {
            
            // Check available slots
            checkSlotsStmt.setString(1, courseCode);
            ResultSet rs = checkSlotsStmt.executeQuery();
            if (rs.next()) {
                int availableSlots = rs.getInt("available_slots");
                if (availableSlots > 0) {
                    // Get registered courses
                    getCoursesStmt.setString(1, studentId);
                    rs = getCoursesStmt.executeQuery();
                    String registeredCourses = rs.getString("registered_courses");
                    if (registeredCourses == null) {
                        registeredCourses = "";
                    }
                    if (!registeredCourses.contains(courseCode)) {
                        // Update registered courses
                        registeredCourses = registeredCourses.isEmpty() ? courseCode : registeredCourses + "," + courseCode;
                        updateCoursesStmt.setString(1, registeredCourses);
                        updateCoursesStmt.setString(2, studentId);
                        updateCoursesStmt.executeUpdate();
                        
                        // Update available slots
                        updateSlotsStmt.setString(1, courseCode);
                        updateSlotsStmt.executeUpdate();
                        
                        System.out.println("Course registered successfully!");
                    } else {
                        System.out.println("You are already registered for this course.");
                    }
                } else {
                    System.out.println("No available slots for this course.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
