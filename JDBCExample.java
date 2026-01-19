import java.sql.*;

public class JDBCExample {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/harshalpatil";  // Change to your database name
        String user = "username"; // Change to your MySQL username
        String password = "Password@"; // Change to your MySQL password

        try {
            // 1. Load the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Registered Successfully!");

            // 2. Establish Connection
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");

            // 3. Create Statement
            Statement stmt = con.createStatement();

            // 4. Execute Query
            String query = "SELECT * FROM EMP"; // Change table name as needed
            ResultSet rs = stmt.executeQuery(query);

            // 5. Process and Display Results
            System.out.println("EMP_ID\tEMP_NAME\tEMP_SALARY\tEMP_DEPT");
            System.out.println("------------------------------------------------------");
            while (rs.next()) {
                System.out.println(rs.getInt("EMP_ID") + "\t" +
                rs.getString("EMP_NAME") + "\t\t" +
                rs.getDouble("EMP_SALARY") + "\t\t" +
                rs.getString("EMP_DEPT"));
                System.out.println("------------------------------------------------------");
            }

            // 6. Close Resources
            rs.close();
            stmt.close();
            con.close();
            System.out.println("Connection Closed.");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver Not Found: " + e);
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e);
        }
    }
}
