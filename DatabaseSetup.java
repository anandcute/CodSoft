//Creation table
package CodSoft;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    public static void createTables() {
        String createCoursesTable = "CREATE TABLE IF NOT EXISTS courses ("
                + " course_code TEXT PRIMARY KEY,"
                + " title TEXT,"
                + " description TEXT,"
                + " capacity INTEGER,"
                + " schedule TEXT,"
                + " available_slots INTEGER"
                + ");";

        String createStudentsTable = "CREATE TABLE IF NOT EXISTS students ("
                + " student_id TEXT PRIMARY KEY,"
                + " name TEXT,"
                + " registered_courses TEXT"
                + ");";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createCoursesTable);
            stmt.execute(createStudentsTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
