import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Scanner;
import java.sql.Statement;
import java.sql.ResultSet;

 public class Main {
     private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
     private static final String username = "root";
     private static final String password = "Devbhai55$$";

     private static void resRoom(Connection con , Scanner sc){
         try{
             System.out.println("Enter Guest name : ");
             String guestName = sc.next();
             sc.nextLine();
             System.out.println("Enter Room Number : ");
             int roomNo = sc.nextInt();
             System.out.println("Enter Contact Number");
             String contactno = sc.next();

             String query = "INSERT INTO reservations (guest_name,room_no,contact_no)"+
                     "VALUES ('" + guestName + "' , " + roomNo + ", '" + contactno + "') ";
             try (Statement st = con.createStatement()){
                 int affect = st.executeUpdate(query);

                 if (affect > 0){
                     System.out.println("Reservation Successfull");
                 }else{
                     System.out.println("Reservation Failed");
                 }
             }
         }catch (SQLException e){
             e.printStackTrace();
         }
     }
     private static void viewRes(Connection con) throws SQLException{
         String query = "SELECT res_id , guest_name , room_no , contact_no , res_date FROM reservations";
         try (Statement st = con.createStatement();
              ResultSet rs = st.executeQuery(query)) {

             System.out.println("Current Reservations:");
             System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
             System.out.println("| Reservation ID | Guest           | Room Number   | Contact Number      | Reservation Date        |");
             System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");

             while (rs.next()) {
                 int reservationId = rs.getInt("res_id");
                 String guestName = rs.getString("guest_name");
                 int roomNumber = rs.getInt("room_no");
                 String contactNumber = rs.getString("contact_no");
                 String reservationDate = rs.getTimestamp("res_date").toString();

                 // Format and display the reservation data in a table-like format
                 System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s   |\n",
                         reservationId, guestName, roomNumber, contactNumber, reservationDate);
             }

             System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
         }
     }
     private static void getRoomno(Connection con , Scanner sc){
         try {
             System.out.print("Enter reservation ID: ");
             int reservationId = sc.nextInt();
             System.out.print("Enter guest name: ");
             String guestName = sc.next();

             String sql = "SELECT room_no FROM reservations " +
                     "WHERE res_id = " + reservationId +
                     " AND guest_name = '" + guestName + "'";

             try (Statement statement = con.createStatement();
                  ResultSet resultSet = statement.executeQuery(sql)) {

                 if (resultSet.next()) {
                     int roomNumber = resultSet.getInt("room_no");
                     System.out.println("Room number for Reservation ID " + reservationId +
                             " and Guest " + guestName + " is: " + roomNumber);
                 } else {
                     System.out.println("Reservation not found for the given ID and guest name.");
                 }
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
     private static void upRes(Connection con , Scanner sc){
         try {
             System.out.println("Enter Reservation ID to update : ");
             int resid = sc.nextInt();
             sc.nextLine();

             if (!resexists(con, resid)) {
                 System.out.println("Reservation not found for the given ID.");
                 return;
             }

             System.out.println("Enter new Guest name : ");
             String newguestname = sc.nextLine();
             System.out.println("Enter new Room Number : ");
             int newroomno = sc.nextInt();
             System.out.println("Enter new Contact no : ");
             String newcontno = sc.next();

             String query = "UPDATE reservations SET guest_name = '" + newguestname + "', " +
                     "room_no = " + newroomno + ", " +
                     "contact_no = '" + newcontno + "' " +
                     "WHERE res_id = " + resid;

             try(Statement st = con.createStatement()){
                 int affect = st.executeUpdate(query);

                 if (affect > 0 ){
                     System.out.println("Reservation Updated Successfully ! ");
                 }else{
                     System.out.println("Reservation Update Failed ! ");
                 }
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
     private static boolean resexists(Connection connection, int reservationId) {
         try {
             String sql = "SELECT res_id FROM reservations WHERE res_id = " + reservationId;

             try (Statement statement = connection.createStatement();
                  ResultSet resultSet = statement.executeQuery(sql)) {

                 return resultSet.next(); // If there's a result, the reservation exists
             }
         } catch (SQLException e) {
             e.printStackTrace();
             return false; // Handle database errors as needed
         }
     }
     private static void delRes(Connection connection, Scanner scanner) {
         try {
             System.out.print("Enter reservation ID to delete: ");
             int reservationId = scanner.nextInt();

             if (!resexists(connection, reservationId)) {
                 System.out.println("Reservation not found for the given ID.");
                 return;
             }

             String sql = "DELETE FROM reservations WHERE res_id = " + reservationId;

             try (Statement statement = connection.createStatement()) {
                 int affectedRows = statement.executeUpdate(sql);

                 if (affectedRows > 0) {
                     System.out.println("Reservation deleted successfully!");
                 } else {
                     System.out.println("Reservation deletion failed.");
                 }
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
     public static void exit() throws Exception {
         System.out.print("Exiting System");
         int i = 5;
         while(i!=0){
             System.out.print(".");
             Thread.sleep(500);
             i--;
         }
         System.out.println();
         System.out.println("ThankYou For Using Hotel Reservation System!!!");
     }
    public static void main(String[] args) throws ClassNotFoundException,SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con = DriverManager.getConnection(url,username,password);
            while (true){
                System.out.println();
                System.out.println("HOTEL MANAGEMENT SYSTEM");
                Scanner sc = new Scanner(System.in);
                System.out.println("1. Reserve a room");
                System.out.println("2. View Reservation");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservations");
                System.out.println("5. Delete Resevations");
                System.out.println("6. Exit");
                System.out.println("Choose an Option : ");
                int choice = sc.nextInt();
                switch (choice){
                    case 1:
                        resRoom(con , sc);
                        break;
                    case 2:
                        viewRes(con);
                        break;
                    case 3:
                        getRoomno(con , sc);
                        break;
                    case 4:
                        upRes(con , sc);
                        break;
                    case 5:
                        delRes(con , sc);
                        break;
                    case 6:
                        exit();
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid Choice Please Try Again");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}