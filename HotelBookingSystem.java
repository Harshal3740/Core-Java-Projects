import java.sql.*;
import java.util.Scanner;

public class HotelBookingSystem {

    static final String DB_URL = "jdbc:mysql://localhost:3306/hotel";
    static final String USER = "MYDB"; // change to your DB user
    static final String PASS = "harshal13804@"; // change to your DB password

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);

            while (true) {
                System.out.println("\n=== Hotel Booking System ===");
                System.out.println("1. Book Room");
                System.out.println("2. View Bookings");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        bookRoom(con, sc);
                        break;
                    case 2:
                        viewBookings(con);
                        break;
                    case 3:
                        con.close();
                        System.out.println("Thank you for using the system!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void bookRoom(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Customer Name: ");
        sc.nextLine(); // clear buffer
        String name = sc.nextLine();
        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();

        String sql = "INSERT INTO bookings (customer_name, room_number) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, roomNo);

        int result = ps.executeUpdate();
        if (result > 0) {
            System.out.println("Room booked successfully!");
        } else {
            System.out.println("Booking failed.");
        }
    }

    static void viewBookings(Connection con) throws SQLException {
        String sql = "SELECT * FROM bookings";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        System.out.println("\n--- All Bookings ---");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") +
                    ", Name: " + rs.getString("customer_name") +
                    ", Room: " + rs.getInt("room_number"));
        }
    }
}
