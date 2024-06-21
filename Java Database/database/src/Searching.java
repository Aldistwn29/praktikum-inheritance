import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Searching {
    static final String jdbc = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/retail";
    static String username = "root";
    static String password = "";

    static Connection con;
    static ResultSet res;
    static PreparedStatement ps;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Cari ID Customer: ");
        int customer_id = scanner.nextInt();
        System.out.print("Cari Nama Pembeli: ");
        String nama = scanner.next();

        String query = "SELECT c.customer_id, c.nama, c.email, c.phone_number, o.order_id, o.order_date, o.jumlah_total, oi.item_id, oi.product_id, oi.quantity, oi.price " +
                       "FROM customers c " +
                       "LEFT JOIN orders o ON c.customer_id = o.customer_id " +
                       "LEFT JOIN orderitems oi ON o.order_id = oi.order_id " +
                       "WHERE c.nama LIKE ? OR c.customer_id = ?";

        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, username, password);
            ps = con.prepareStatement(query);

            ps.setString(1, "%" + nama + "%");
            ps.setInt(2, customer_id);
            res = ps.executeQuery();

            int number = 0;
            while (res.next()) {
                number++;
                System.out.println("Nomor: " + number);
                System.out.println("Customer ID: " + res.getInt("customer_id"));
                System.out.println("Nama Pembeli: " + res.getString("nama"));
                System.out.println("Email Pembeli: " + res.getString("email"));
                System.out.println("Nomor Handphone: " + res.getString("phone_number"));
                System.out.println("Order ID: " + res.getInt("order_id"));
                System.out.println("Tanggal Order: " + res.getDate("order_date"));
                System.out.println("Total Amount: " + res.getDouble("jumlah_total"));
                System.out.println("Item ID: " + res.getInt("item_id"));
                System.out.println("Product ID: " + res.getInt("product_id"));
                System.out.println("Quantity: " + res.getInt("quantity"));
                System.out.println("Price: " + res.getDouble("price"));
                System.out.println("=========================================");
            }

            System.out.println("Total Pelanggan: " + number);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (res != null) res.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
