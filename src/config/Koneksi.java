package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {

    private static Connection koneksi;

    public static Connection getKoneksi() {

        try {
            String url = "jdbc:mysql://localhost/db_pegawai";
            String user = "root";
            String pass = "";

            DriverManager.registerDriver(
                new com.mysql.cj.jdbc.Driver()
            );

            koneksi = DriverManager.getConnection(url,user,pass);

            System.out.println("Koneksi Berhasil");

        } catch (Exception e) {
            System.out.println("Koneksi Gagal");
        }

        return koneksi;
    }
}