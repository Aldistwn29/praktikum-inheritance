import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Delete {
    static final String jdbc = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/retail";
    static String username = "root";
    static String password = "";

    static Connection con;
    static PreparedStatement ps;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pilih tabel yang ingin dihapus datanya:");
        System.out.println("1. Customers");
        System.out.println("2. Orders");
        System.out.println("3. OrderItems");
        int choice = scanner.nextInt();

        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(url, username, password);

            switch (choice) {
                case 1:
                    System.out.print("Isikan ID Customer yang akan dihapus: ");
                    int customer_id = scanner.nextInt();

                    // Hapus data dari tabel orderitems yang terkait dengan orders dari customer
                    String deleteOrderItems = "DELETE FROM orderitems WHERE order_id IN (SELECT order_id FROM orders WHERE customer_id = ?)";
                    ps = con.prepareStatement(deleteOrderItems);
                    ps.setInt(1, customer_id);
                    ps.executeUpdate();

                    // Hapus data dari tabel orders yang terkait dengan customer
                    String deleteOrders = "DELETE FROM orders WHERE customer_id = ?";
                    ps = con.prepareStatement(deleteOrders);
                    ps.setInt(1, customer_id);
                    ps.executeUpdate();

                    // Hapus data dari tabel customers
                    String queryCustomers = "DELETE FROM customers WHERE customer_id = ?";
                    ps = con.prepareStatement(queryCustomers);
                    ps.setInt(1, customer_id);
                    break;
                case 2:
                    System.out.print("Isikan ID Order yang akan dihapus: ");
                    int order_id = scanner.nextInt();

                    // Hapus data dari tabel orderitems yang terkait dengan order
                    String deleteOrderItemsByOrder = "DELETE FROM orderitems WHERE order_id = ?";
                    ps = con.prepareStatement(deleteOrderItemsByOrder);
                    ps.setInt(1, order_id);
                    ps.executeUpdate();

                    // Hapus data dari tabel orders
                    String queryOrders = "DELETE FROM orders WHERE order_id = ?";
                    ps = con.prepareStatement(queryOrders);
                    ps.setInt(1, order_id);
                    break;
                case 3:
                    System.out.print("Isikan ID Order Item yang akan dihapus: ");
                    int item_id = scanner.nextInt();

                    // Hapus data dari tabel orderitems
                    String queryOrderItems = "DELETE FROM orderitems WHERE item_id = ?";
                    ps = con.prepareStatement(queryOrderItems);
                    ps.setInt(1, item_id);
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
                    return;
            }

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Proses Penghapusan Berhasil");
            } else {
                System.out.println("Data dengan ID tersebut tidak ditemukan");
            }
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
