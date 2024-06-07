package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class barang_masuk_detail {

    private String databaseName = "2210010453_amalia_putri";
    private String username = "root";
    private String password = "";
    private String lokasi = "jdbc:mysql://localhost/" + databaseName;
    public static Connection koneksiDB;

    public barang_masuk_detail() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            koneksiDB = DriverManager.getConnection(lokasi, username, password);
            System.out.println("Database Terkoneksi");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Koneksi Gagal: " + e.getMessage());
        }
    }

    public void simpanBarangMasukDetail(String noMasuk, String kodeBarang, int jumlah, int subTotal) {
        try {
            String SQL = "INSERT INTO barang_masuk_detail (no_masuk, kode_barang, jumlah, sub_total) VALUES (?, ?, ?, ?)";
            PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
            perintah.setString(1, noMasuk);
            perintah.setString(2, kodeBarang);
            perintah.setInt(3, jumlah);
            perintah.setInt(4, subTotal);
            perintah.executeUpdate();
            System.out.println("Data berhasil disimpan");
        } catch (SQLException e) {
            System.err.println("Gagal Menyimpan Data: " + e.getMessage());
        }
    }

    public void ubahBarangMasukDetail(int id, String noMasuk, String kodeBarang, int jumlah, int subTotal) {
        try {
            String SQL = "UPDATE barang_masuk_detail SET no_masuk = ?, kode_barang = ?, jumlah = ?, sub_total = ? WHERE id = ?";
            PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
            perintah.setString(1, noMasuk);
            perintah.setString(2, kodeBarang);
            perintah.setInt(3, jumlah);
            perintah.setInt(4, subTotal);
            perintah.setInt(5, id);
            perintah.executeUpdate();
            System.out.println("Data berhasil diubah");
        } catch (SQLException e) {
            System.err.println("Gagal Mengubah Data: " + e.getMessage());
        }
    }

    public void hapusBarangMasukDetail(int id) {
        try {
            String SQL = "DELETE FROM barang_masuk_detail WHERE id = ?";
            PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
            perintah.setInt(1, id);
            perintah.executeUpdate();
            System.out.println("Data berhasil dihapus");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void cariBarangMasukDetail(int id) {
        try {
            String SQL = "SELECT * FROM barang_masuk_detail WHERE id = ?";
            PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
            perintah.setInt(1, id);
            ResultSet data = perintah.executeQuery();
            while (data.next()) {
                System.out.println("ID: " + data.getInt("id"));
                System.out.println("NO MASUK: " + data.getString("no_masuk"));
                System.out.println("KODE BARANG: " + data.getString("kode_barang"));
                System.out.println("JUMLAH: " + data.getInt("jumlah"));
                System.out.println("SUB TOTAL: " + data.getInt("sub_total"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void dataBarangMasukDetail() {
        try {
            Statement stmt = koneksiDB.createStatement();
            ResultSet baris = stmt.executeQuery("SELECT * FROM barang_masuk_detail ORDER BY id ASC");
            while (baris.next()) {
                System.out.println(baris.getInt("id") + " | " +
                        baris.getString("no_masuk") + " | " +
                        baris.getString("kode_barang") + " | " +
                        baris.getInt("jumlah") + " | " +
                        baris.getInt("sub_total"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
