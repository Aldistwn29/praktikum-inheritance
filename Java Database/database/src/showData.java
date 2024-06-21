import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class showData {
    static final String jdbc = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/retail";
    static String username = "root";
    static String password = "";

    static Connection con;
    static ResultSet res;
    static Statement state;

    public static void main(String[] args) {
        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, username, password);
            
            // Query untuk mengambil data dari tabel Customers
            String queryCustomers = "SELECT * FROM customers";
            state = con.createStatement();
            res = state.executeQuery(queryCustomers);
            
            int number = 0;
            System.out.println("==== Data Customers =====");
            while (res.next()) {
                number++;
                System.out.println("nomer: " + number);
                System.out.println("Customer ID: " + res.getInt("customer_id"));
                System.out.println("Nama Pembeli: " + res.getString("nama"));
                System.out.println("Email Pembeli: " + res.getString("email"));  // Perbaiki tipe data
                System.out.println("Nomer Handphone: " + res.getString("phone_number"));  // Perbaiki tipe data
                System.out.println("==========================================================");
            }
            res.close();

            // Query untuk mengambil data dari tabel Orders
            String queryOrders = "SELECT * FROM orders";
            res = state.executeQuery(queryOrders);
            
            number = 0;
            System.out.println("==== Data Orders =====");
            while (res.next()) {
                number++;
                System.out.println("nomer: " + number);
                System.out.println("Order ID: " + res.getInt("order_id"));
                System.out.println("Customer ID: " + res.getInt("customer_id"));
                System.out.println("Order Date: " + res.getDate("order_date"));
                System.out.println("Total Amount: " + res.getDouble("jumlah_total"));
                System.out.println("==========================================================");
            }
            res.close();

            // Query untuk mengambil data dari tabel OrderItems
            String queryOrderItems = "SELECT * FROM orderitems";  // Nama tabel harus konsisten
            res = state.executeQuery(queryOrderItems);
            
            number = 0;
            System.out.println("==== Data Order Items =====");
            while (res.next()) {
                number++;
                System.out.println("nomer: " + number);
                System.out.println("Item ID: " + res.getInt("item_id"));
                System.out.println("Order ID: " + res.getInt("order_id"));
                System.out.println("Quantity: " + res.getInt("quantity"));
                System.out.println("Price: " + res.getDouble("price"));
                System.out.println("==========================================================");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (res != null) res.close();
                if (state != null) state.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
