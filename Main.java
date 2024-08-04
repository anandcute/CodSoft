package CodSoft;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

// Main classs

public class Main {
    public static void main(String[] args) {
        DatabaseSetup.createTables();

        // Add courses
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute("INSERT INTO courses VALUES ('CS101', 'Intro to CS', 'Basics of computer science', 30, 'Mon/Wed 10-11:30', 30)");
            stmt.execute("INSERT INTO courses VALUES ('CS102', 'Data Structures', 'Study of data structures', 30, 'Tue/Thu 1-2:30', 30)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Add students
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute("INSERT INTO students VALUES ('S001', 'Alice', '')");
            stmt.execute("INSERT INTO students VALUES ('S002', 'Bob', '')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Register for a course
        StudentRegistration.registerCourse("S001", "CS101");

        // Drop a course
        CourseRemoval.dropCourse("S001", "CS101");

        // List courses
        CourseListing.listCourses();
    }
}
