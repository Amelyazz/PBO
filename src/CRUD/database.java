package CRUD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class database {

    private String databaseName = "2210010453_amalia_putri";
    private String username = "root";
    private String password = "";
    private String lokasi = "jdbc:mysql://localhost/" + databaseName;
    public static Connection koneksiDB;

    public database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            koneksiDB = DriverManager.getConnection(lokasi, username, password);
            System.out.println("Database Terkoneksi");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Koneksi Gagal: " + e.getMessage());
        }
    }

    public void simpanuji(String nik, String nama, String no_telp, String alamat) {
        try {
            String SQL = "INSERT INTO UJI (nik, nama, no_telp, alamat) VALUES (?, ?, ?, ?)";
            PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
            perintah.setString(1, nik);
            perintah.setString(2, nama);
            perintah.setString(3, no_telp);
            perintah.setString(4, alamat);
            perintah.executeUpdate();
            System.out.println("Data berhasil disimpan");
        } catch (SQLException e) {
            System.err.println("Gagal Menyimpan Data: " + e.getMessage());
        }
    }
    
        public void ubahuji(String nik, String nama, String no_telp, String alamat) {
        try {
            String SQL = "UPDATE UJI SET nama = ?, no_telp = ?, alamat = ? WHERE nik = ?";
            PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
            perintah.setString(1, nama);
            perintah.setString(2, no_telp);
            perintah.setString(3, alamat);
            perintah.setString(4, nik);
            perintah.executeUpdate();
            System.out.println("Data berhasil diubah");
        } catch (SQLException e) {
            System.err.println("Gagal Menyimpan Data: " + e.getMessage());
        }
        
    }
        public void hapusuji(String nik){
            try {
                String SQL = "DELETE FROM UJI WHERE nik=?";
                PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
                perintah.setString(1, nik);
                perintah.executeUpdate();
                System.out.println("Data berhasil dihapus");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        
        public void cariuji(String nik){
            try {
                String SQL="SELECT*FROM uji WHERE nik=?";
                PreparedStatement perintah = koneksiDB.prepareStatement(SQL);
                perintah.setString(1, nik);
                ResultSet data = perintah.executeQuery();
                while(data.next()){
                    System.out.println("NIK :"+data.getString("nik"));
                    System.out.println("NAMA :"+data.getString("nama"));
                    System.out.println("NO_TELPON :"+data.getString("no_telp"));
                    System.out.println("ALAMAT :"+data.getString("alamat"));
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        
        public void datauji(){
            try {
                Statement stmt = koneksiDB.createStatement();
                ResultSet baris = stmt.executeQuery("SELECT*FROM uji ORDER BY nik ASC");
                while (baris.next()){
                    System.out.println(baris.getString("nik")+" | "+
                            baris.getString("nama")+" | "+
                            baris.getString("no_telp")+" | "+
                            baris.getString("alamat"));
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
}