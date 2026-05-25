package dao;

import config.Koneksi;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PegawaiDAO {

    Connection conn = Koneksi.getKoneksi();

    // TAMBAH DATA
    public void tambahPegawai(String nama, String jabatan, String tipe, double gaji) {
        try {
            String sql = "INSERT INTO pegawai VALUES(NULL,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nama);
            pst.setString(2, jabatan);
            pst.setString(3, tipe);
            pst.setDouble(4, gaji);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error tambah: " + e);
        }
    }

    // EDIT DATA
    public void editPegawai(int id, String nama, String jabatan, String tipe, double gaji) {
        try {
            String sql = "UPDATE pegawai SET nama=?, jabatan=?, tipe=?, gaji=? WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nama);
            pst.setString(2, jabatan);
            pst.setString(3, tipe);
            pst.setDouble(4, gaji);
            pst.setInt(5, id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error edit: " + e);
        }
    }

    // HAPUS DATA
    public void hapusPegawai(int id) {
        try {
            String sql = "DELETE FROM pegawai WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error hapus: " + e);
        }
    }

    public void tampilData(JTable table, String keyword) {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID");
    model.addColumn("Nama");
    model.addColumn("Jabatan");
    model.addColumn("Tipe");
    model.addColumn("Gaji");

    try {
        String sql;
        PreparedStatement pst;

        if (keyword == null || keyword.trim().isEmpty()) {
            sql = "SELECT * FROM pegawai";
            pst = conn.prepareStatement(sql);
        } else {
            sql = "SELECT * FROM pegawai WHERE nama LIKE ? OR jabatan LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");
        }

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("nama"),
                rs.getString("jabatan"),
                rs.getString("tipe"),
                String.format("Rp %,d", (long) rs.getDouble("gaji"))  // <-- SUDAH DI FORMAT
            });
        }
        table.setModel(model);
    } catch (Exception e) {
        System.out.println("Error tampil: " + e);
    }
}
}