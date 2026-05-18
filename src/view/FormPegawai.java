package view;

import dao.PegawaiDAO;
import javax.swing.*;

public class FormPegawai extends JFrame {

    JLabel lnama = new JLabel("Nama");
    JLabel ljabatan = new JLabel("Jabatan");
    JLabel ltipe = new JLabel("Tipe");
    JLabel lgaji = new JLabel("Gaji");

    JTextField tnama = new JTextField();
    JTextField tjabatan = new JTextField();
    JTextField tgaji = new JTextField();

    JComboBox<String> ctipe
            = new JComboBox<>(
                    new String[]{"Tetap", "Kontrak"}
            );

    JButton btambah = new JButton("Tambah");
    JButton bhapus = new JButton("Hapus");
    JButton bbersih = new JButton("Bersihkan");
    JButton bslip = new JButton("Slip Gaji");

    JTable table = new JTable();

    PegawaiDAO dao = new PegawaiDAO();

    public FormPegawai() {

        setTitle("Form Pegawai");
        setSize(850, 500);
        setLayout(null);
        setLocationRelativeTo(null);

        // Nama
        lnama.setBounds(20, 20, 100, 30);
        add(lnama);

        tnama.setBounds(120, 20, 200, 30);
        add(tnama);

        // Jabatan
        ljabatan.setBounds(20, 70, 100, 30);
        add(ljabatan);

        tjabatan.setBounds(120, 70, 200, 30);
        add(tjabatan);

        // Tipe
        ltipe.setBounds(20, 120, 100, 30);
        add(ltipe);

        ctipe.setBounds(120, 120, 200, 30);
        add(ctipe);

        // Gaji
        lgaji.setBounds(20, 170, 100, 30);
        add(lgaji);

        tgaji.setBounds(120, 170, 200, 30);
        add(tgaji);

        // Tombol
        btambah.setBounds(20, 240, 100, 35);
        add(btambah);

        bhapus.setBounds(130, 240, 100, 35);
        add(bhapus);

        bbersih.setBounds(240, 240, 100, 35);
        add(bbersih);

        bslip.setBounds(90, 300, 170, 35);
        add(bslip);

        // Tabel
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(370, 20, 430, 350);
        add(sp);

        tampilData();

        // TAMBAH
        btambah.addActionListener(e -> {

            dao.tambahPegawai(
                    tnama.getText(),
                    tjabatan.getText(),
                    ctipe.getSelectedItem().toString(),
                    Double.parseDouble(
                            tgaji.getText()
                                    .replace("Rp", "")
                                    .replace("rp", "")
                                    .replace(".", "")
                                    .replace(",", "")
                                    .replace(" ", "")
                                    .trim()
                    )
            );

            JOptionPane.showMessageDialog(
                    null,
                    "Data berhasil ditambah"
            );

            tampilData();

            bersih();
        });

        // HAPUS
        bhapus.addActionListener(e -> {

            int baris = table.getSelectedRow();

            if (baris >= 0) {

                int id = Integer.parseInt(
                        table.getValueAt(
                                baris,
                                0
                        ).toString()
                );

                dao.hapusPegawai(id);

                JOptionPane.showMessageDialog(
                        null,
                        "Data berhasil dihapus"
                );

                tampilData();

            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "Pilih data dulu"
                );
            }
        });

        // BERSIHKAN
        bbersih.addActionListener(e -> {

            bersih();

        });

        // SLIP GAJI
        bslip.addActionListener(e -> {

            int baris = table.getSelectedRow();

            if (baris >= 0) {

                String nama
                        = table.getValueAt(
                                baris,
                                1
                        ).toString();

                String jabatan
                        = table.getValueAt(
                                baris,
                                2
                        ).toString();

                String tipe
                        = table.getValueAt(
                                baris,
                                3
                        ).toString();

                String gaji
                        = table.getValueAt(
                                baris,
                                4
                        ).toString();

                FormGaji fg
                        = new FormGaji(
                                nama,
                                jabatan,
                                tipe,
                                gaji
                        );

                fg.setVisible(true);

            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "Pilih pegawai dulu"
                );
            }
        });
    }

    // METHOD TAMPIL DATA
    public void tampilData() {

        dao.tampilData(table);

    }

    // METHOD BERSIH
    public void bersih() {

        tnama.setText("");
        tjabatan.setText("");
        tgaji.setText("");

        ctipe.setSelectedIndex(0);
    }
}
