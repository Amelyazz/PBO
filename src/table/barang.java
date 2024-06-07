package CRUD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class barang {

    private String databaseName = "2210010453_amalia_putri";
    private String username = "root";
    private String password = "";
    private String lokasi = "jdbc:mysql://localhost/" + databaseName;
    public static Connection koneksiDB;

    public barang() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            koneksiDB = DriverManager.getConnection(lokasi, username, password);
            System.out.println("Database Terkoneksi");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Koneksi Gagal: " + e.getMessage());
        }
    }

    public void simpanBarang(String kode_barang, String nama_barang, int harga_beli, int harga, int jumlah, String kode_jenis) {
        try {
            String SQL = "INSERT INTO barang (kode_barang, nama_barang, harga_beli, harga, jumlah, kode_jenis) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
            perintah.setString(1, kode_barang);
            perintah.setString(2, nama_barang);
            perintah.setDouble(3, harga_beli);
            perintah.setDouble(4, harga);
            perintah.setInt(5, jumlah);
            perintah.setString(6, kode_jenis);
            perintah.executeUpdate();
            System.out.println("Data berhasil disimpan");
        } catch (SQLException e) {
            System.err.println("Gagal Menyimpan Data: " + e.getMessage());
        }
    }
    
    public void ubahBarang(String kode_barang, String nama_barang, double harga_beli, double harga, int jumlah, String kode_jenis) {
        try {
            String SQL = "UPDATE barang SET nama_barang = ?, harga_beli = ?, harga = ?, jumlah = ?, kode_jenis = ? WHERE kode_barang = ?";
            PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
            perintah.setString(1, nama_barang);
            perintah.setDouble(2, harga_beli);
            perintah.setDouble(3, harga);
            perintah.setInt(4, jumlah);
            perintah.setString(5, kode_jenis);
            perintah.setString(6, kode_barang);
            perintah.executeUpdate();
            System.out.println("Data berhasil diubah");
        } catch (SQLException e) {
            System.err.println("Gagal Menyimpan Data: " + e.getMessage());
        }
    }
    
    public void hapusBarang(String kode_barang) {
        try {
            String SQL = "DELETE FROM barang WHERE kode_barang = ?";
            PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
            perintah.setString(1, kode_barang);
            perintah.executeUpdate();
            System.out.println("Data berhasil dihapus");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void cariBarang(String kode_barang) {
        try {
            String SQL = "SELECT * FROM barang WHERE kode_barang = ?";
            PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
            perintah.setString(1, kode_barang);
            ResultSet data = perintah.executeQuery();
            while (data.next()) {
                System.out.println("KODE BARANG : " + data.getString("kode_barang"));
                System.out.println("NAMA BARANG : " + data.getString("nama_barang"));
                System.out.println("HARGA BELI : " + data.getDouble("harga_beli"));
                System.out.println("HARGA : " + data.getDouble("harga"));
                System.out.println("JUMLAH : " + data.getInt("jumlah"));
                System.out.println("KODE JENIS : " + data.getString("kode_jenis"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void dataBarang() {
        try {
            Statement stmt = koneksiDB.createStatement();
            ResultSet baris = stmt.executeQuery("SELECT * FROM barang ORDER BY kode_barang ASC");
            while (baris.next()) {
                System.out.println(baris.getString("kode_barang") + " | " +
                        baris.getString("nama_barang") + " | " +
                        baris.getDouble("harga_beli") + " | " +
                        baris.getDouble("harga") + " | " +
                        baris.getInt("jumlah") + " | " +
                        baris.getString("kode_jenis"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
