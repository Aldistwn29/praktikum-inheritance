import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Insert {
    static final String jdbc = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/retail";
    static String username = "root";
    static String password = "";

    static Connection con;
    static PreparedStatement ps;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input data untuk tabel customers
        System.out.print("ID Customer: ");
        int customer_id = scanner.nextInt();
        System.out.print("Nama Customer: ");
        String nama = scanner.next();
        System.out.print("Email Pembeli: ");
        String email = scanner.next();
        System.out.print("Nomor Pembeli: ");
        String phone_number = scanner.next();

        // Input data untuk tabel orders
        System.out.print("ID Order: ");
        int order_id = scanner.nextInt();
        System.out.print("Tanggal Order (YYYY-MM-DD): ");
        String order_date = scanner.next();
        System.out.print("Total Amount: ");
        double jumlah_total = scanner.nextDouble();

        // Input data untuk tabel orderitems
        System.out.print("ID Item: ");
        int item_id = scanner.nextInt();
        System.out.print("ID Produk: ");
        int product_id = scanner.nextInt();
        System.out.print("Kuantitas: ");
        int quantity = scanner.nextInt();
        System.out.print("Harga: ");
        double price = scanner.nextDouble();

        // Query untuk tabel customers
        String queryCustomers = "INSERT INTO customers (customer_id, nama, email, phone_number) VALUES (?, ?, ?, ?)";

        // Query untuk tabel orders
        String queryOrders = "INSERT INTO orders (order_id, customer_id, order_date, jumlah_total) VALUES (?, ?, ?, ?)";

        // Query untuk tabel orderitems
        String queryOrderItems = "INSERT INTO orderitems (item_id, order_id, product_id, quantity, price) VALUES (?, ?, ?, ?, ?)";

        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, username, password);

            // Insert data ke tabel customers
            ps = con.prepareStatement(queryCustomers);
            ps.setInt(1, customer_id);
            ps.setString(2, nama);
            ps.setString(3, email);
            ps.setString(4, phone_number);
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data pelanggan berhasil ditambahkan!");
            } else {
                System.out.println("Gagal menambahkan data pelanggan!");
            }
            ps.close();

            // Insert data ke tabel orders
            ps = con.prepareStatement(queryOrders);
            ps.setInt(1, order_id);
            ps.setInt(2, customer_id); // Kunci asing
            ps.setString(3, order_date);
            ps.setDouble(4, jumlah_total);
            rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data order berhasil ditambahkan!");
            } else {
                System.out.println("Gagal menambahkan data order!");
            }
            ps.close();

            // Insert data ke tabel orderitems
            ps = con.prepareStatement(queryOrderItems);
            ps.setInt(1, item_id);
            ps.setInt(2, order_id); // Kunci asing
            ps.setInt(3, product_id);
            ps.setInt(4, quantity);
            ps.setDouble(5, price);
            rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data order item berhasil ditambahkan!");
            } else {
                System.out.println("Gagal menambahkan data order item!");
            }
            ps.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
