package CRUD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class barang_keluar {

    private String databaseName = "2210010453_amalia_putri";
    private String username = "root";
    private String password = "";
    private String lokasi = "jdbc:mysql://localhost/" + databaseName;
    public static Connection koneksiDB;

    public barang_keluar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            koneksiDB = DriverManager.getConnection(lokasi, username, password);
            System.out.println("Database Terkoneksi");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Koneksi Gagal: " + e.getMessage());
        }
    }

    public void simpanBarangKeluar(String no_keluar, String tanggal, String kode_barang, int jumlah, int total, String tujuan, String username) {
        try {
            String SQL = "INSERT INTO barang_keluar (no_keluar, tanggal, kode_barang, jumlah, total, tujuan, username) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
            perintah.setString(1, no_keluar);
            perintah.setString(2, tanggal);
            perintah.setString(3, kode_barang);
            perintah.setInt(4, jumlah);
            perintah.setDouble(5, total);
            perintah.setString(6, tujuan);
            perintah.setString(7, username);
            perintah.executeUpdate();
            System.out.println("Data berhasil disimpan");
        } catch (SQLException e) {
            System.err.println("Gagal Menyimpan Data: " + e.getMessage());
        }
    }
    
    public void ubahBarangKeluar(String no_keluar, String tanggal, String kode_barang, int jumlah, int total, String tujuan, String username) {
        try {
            String SQL = "UPDATE barang_keluar SET tanggal = ?, kode_barang = ?, jumlah = ?, total = ?, tujuan = ?, username = ? WHERE no_keluar = ?";
            PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
            perintah.setString(1, tanggal);
            perintah.setString(2, kode_barang);
            perintah.setInt(3, jumlah);
            perintah.setDouble(4, total);
            perintah.setString(5, tujuan);
            perintah.setString(6, username);
            perintah.setString(7, no_keluar);
            perintah.executeUpdate();
            System.out.println("Data berhasil diubah");
        } catch (SQLException e) {
            System.err.println("Gagal Mengubah Data: " + e.getMessage());
        }
    }
    
    public void hapusBarangKeluar(String no_keluar) {
        try {
            String SQL = "DELETE FROM barang_keluar WHERE no_keluar = ?";
            PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
            perintah.setString(1, no_keluar);
            perintah.executeUpdate();
            System.out.println("Data berhasil dihapus");
        } catch (SQLException e) {
            System.err.println("Gagal Menghapus Data: " + e.getMessage());
        }
    }
    
    public void cariBarangKeluar(String no_keluar) {
        try {
            String SQL = "SELECT * FROM barang_keluar WHERE no_keluar = ?";
            PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
            perintah.setString(1, no_keluar);
            ResultSet data = perintah.executeQuery();
            while (data.next()) {
                System.out.println("No Keluar: " + data.getString("no_keluar"));
                System.out.println("Tanggal: " + data.getString("tanggal"));
                System.out.println("Kode Barang: " + data.getString("kode_barang"));
                System.out.println("Jumlah: " + data.getInt("jumlah"));
                System.out.println("Total: " + data.getDouble("total"));
                System.out.println("Tujuan: " + data.getString("tujuan"));
                System.out.println("Username: " + data.getString("username"));
            }
        } catch (SQLException e) {
            System.err.println("Gagal Mencari Data: " + e.getMessage());
        }
    }
    
    public void dataBarangKeluar() {
        try {
            Statement stmt = koneksiDB.createStatement();
            ResultSet baris = stmt.executeQuery("SELECT * FROM barang_keluar ORDER BY no_keluar ASC");
            while (baris.next()) {
                System.out.println(baris.getString("no_keluar") + " | " +
                                   baris.getString("tanggal") + " | " +
                                   baris.getString("kode_barang") + " | " +
                                   baris.getInt("jumlah") + " | " +
                                   baris.getDouble("total") + " | " +
                                   baris.getString("tujuan") + " | " +
                                   baris.getString("username"));
            }
        } catch (SQLException e) {
            System.err.println("Gagal Menampilkan Data: " + e.getMessage());
        }
    }
}
