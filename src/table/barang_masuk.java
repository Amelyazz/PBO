package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class barang_masuk {

    private String databaseName = "2210010453_amalia_putri";
    private String username = "root";
    private String password = "";
    private String lokasi = "jdbc:mysql://localhost/" + databaseName;
    public static Connection koneksiDB;

    public barang_masuk() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            koneksiDB = DriverManager.getConnection(lokasi, username, password);
            System.out.println("Database Terkoneksi");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Koneksi Gagal: " + e.getMessage());
        }
    }

    public void simpanBarangMasuk(String no_masuk, String tanggal, String kode_barang, int jumlah, int total, String kode_supplier, String username) {
        try {
            String SQL = "INSERT INTO barang_masuk (no_masuk, tanggal, kode_barang, jumlah, total, kode_supplier, username) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
            perintah.setString(1, no_masuk);
            perintah.setString(2, tanggal);
            perintah.setString(3, kode_barang);
            perintah.setInt(4, jumlah);
            perintah.setDouble(5, total);
            perintah.setString(6, kode_supplier);
            perintah.setString(7, username);
            perintah.executeUpdate();
            System.out.println("Data berhasil disimpan");
        } catch (SQLException e) {
            System.err.println("Gagal Menyimpan Data: " + e.getMessage());
        }
    }

    public void ubahBarangMasuk(String no_masuk, String tanggal, String kode_barang, int jumlah, int total, String kode_supplier, String username) {
        try {
            String SQL = "UPDATE barang_masuk SET tanggal = ?, kode_barang = ?, jumlah = ?, total = ?, kode_supplier = ?, username = ? WHERE no_masuk = ?";
            PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
            perintah.setString(1, tanggal);
            perintah.setString(2, kode_barang);
            perintah.setInt(3, jumlah);
            perintah.setDouble(4, total);
            perintah.setString(5, kode_supplier);
            perintah.setString(6, username);
            perintah.setString(7, no_masuk);
            perintah.executeUpdate();
            System.out.println("Data berhasil diubah");
        } catch (SQLException e) {
            System.err.println("Gagal Mengubah Data: " + e.getMessage());
        }
    }

    public void hapusBarangMasuk(String no_masuk) {
        try {
            String SQL = "DELETE FROM barang_masuk WHERE no_masuk=?";
            PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
            perintah.setString(1, no_masuk);
            perintah.executeUpdate();
            System.out.println("Data berhasil dihapus");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void cariBarangMasuk(String no_masuk) {
        try {
            String SQL = "SELECT * FROM barang_masuk WHERE no_masuk=?";
            PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
            perintah.setString(1, no_masuk);
            ResultSet data = perintah.executeQuery();
            while (data.next()) {
                System.out.println("NO MASUK: " + data.getString("no_masuk"));
                System.out.println("TANGGAL: " + data.getString("tanggal"));
                System.out.println("KODE BARANG: " + data.getString("kode_barang"));
                System.out.println("JUMLAH: " + data.getInt("jumlah"));
                System.out.println("TOTAL: " + data.getDouble("total"));
                System.out.println("KODE SUPPLIER: " + data.getString("kode_supplier"));
                System.out.println("USERNAME: " + data.getString("username"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void dataBarangMasuk() {
        try {
            Statement stmt = koneksiDB.createStatement();
            ResultSet baris = stmt.executeQuery("SELECT * FROM barang_masuk ORDER BY no_masuk ASC");
            while (baris.next()) {
                System.out.println(baris.getString("no_masuk") + " | " +
                        baris.getString("tanggal") + " | " +
                        baris.getString("kode_barang") + " | " +
                        baris.getInt("jumlah") + " | " +
                        baris.getDouble("total") + " | " +
                        baris.getString("kode_supplier") + " | " +
                        baris.getString("username"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}