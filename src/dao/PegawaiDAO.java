package dao;

import config.Koneksi;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PegawaiDAO {

    Connection conn = Koneksi.getKoneksi();

    // TAMBAH DATA
    public void tambahPegawai(
            String nama,
            String jabatan,
            String tipe,
            double gaji) {

        try {

            String sql =
            "INSERT INTO pegawai VALUES(NULL,?,?,?,?)";

            PreparedStatement pst =
                    conn.prepareStatement(sql);

            pst.setString(1, nama);
            pst.setString(2, jabatan);
            pst.setString(3, tipe);
            pst.setDouble(4, gaji);

            pst.executeUpdate();

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    // HAPUS DATA
    public void hapusPegawai(int id) {

        try {

            String sql =
            "DELETE FROM pegawai WHERE id=?";

            PreparedStatement pst =
                    conn.prepareStatement(sql);

            pst.setInt(1, id);

            pst.executeUpdate();

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    // TAMPIL DATA
    public void tampilData(JTable table) {

        DefaultTableModel model =
                new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Jabatan");
        model.addColumn("Tipe");
        model.addColumn("Gaji");

        try {

            String sql =
                    "SELECT * FROM pegawai";

            Statement st =
                    conn.createStatement();

            ResultSet rs =
                    st.executeQuery(sql);

            while(rs.next()) {

                model.addRow(new Object[]{

                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("jabatan"),
                    rs.getString("tipe"),
                    rs.getDouble("gaji")

                });
            }

            table.setModel(model);

        } catch (Exception e) {

            System.out.println(e);
        }
    }
}