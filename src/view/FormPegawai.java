package view;

import dao.PegawaiDAO;
import javax.swing.*;

public class FormPegawai extends JFrame {

    JLabel lnama = new JLabel("Nama");
    JLabel ljabatan = new JLabel("Jabatan");
    JLabel ltipe = new JLabel("Tipe");
    JLabel lgaji = new JLabel("Gaji");
    JLabel lcari = new JLabel("Cari");

    JTextField tnama = new JTextField();
    JTextField tjabatan = new JTextField();
    JTextField tgaji = new JTextField();
    JTextField tcari = new JTextField();

    JComboBox<String> ctipe = new JComboBox<>(new String[]{"Tetap", "Kontrak"});

    JButton btambah = new JButton("Tambah");
    JButton bedit = new JButton("Edit");
    JButton bhapus = new JButton("Hapus");
    JButton bbersih = new JButton("Bersihkan");
    JButton bslip = new JButton("Slip Gaji");
    JButton bcari = new JButton("Cari");
    JButton brefresh = new JButton("Refresh");

    JTable table = new JTable();
    PegawaiDAO dao = new PegawaiDAO();
    int idTerpilih = -1;

    public FormPegawai() {
        setTitle("Form Pegawai");
        setSize(900, 500);
        setLayout(null);
        setLocationRelativeTo(null);

        // Form Input
        lnama.setBounds(20, 20, 100, 30);
        add(lnama);
        tnama.setBounds(120, 20, 200, 30);
        add(tnama);

        ljabatan.setBounds(20, 70, 100, 30);
        add(ljabatan);
        tjabatan.setBounds(120, 70, 200, 30);
        add(tjabatan);

        ltipe.setBounds(20, 120, 100, 30);
        add(ltipe);
        ctipe.setBounds(120, 120, 200, 30);
        add(ctipe);

        lgaji.setBounds(20, 170, 100, 30);
        add(lgaji);
        tgaji.setBounds(120, 170, 200, 30);
        add(tgaji);

        // Tombol
        btambah.setBounds(20, 230, 90, 35);
        add(btambah);
        bedit.setBounds(115, 230, 90, 35);
        add(bedit);
        bhapus.setBounds(210, 230, 90, 35);
        add(bhapus);
        bbersih.setBounds(20, 280, 130, 35);
        add(bbersih);
        bslip.setBounds(160, 280, 130, 35);
        add(bslip);

        // Pencarian
        lcari.setBounds(370, 400, 50, 30);
        add(lcari);
        tcari.setBounds(410, 400, 200, 30);
        add(tcari);
        bcari.setBounds(620, 400, 80, 30);
        add(bcari);
        brefresh.setBounds(710, 400, 80, 30);
        add(brefresh);

        // Tabel
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(370, 20, 490, 360);
        add(sp);

        // Event Tambah
        btambah.addActionListener(e -> {
            if (validasi()) {
                dao.tambahPegawai(tnama.getText(), tjabatan.getText(), 
                    ctipe.getSelectedItem().toString(), Double.parseDouble(tgaji.getText()));
                JOptionPane.showMessageDialog(null, "Data berhasil ditambah");
                tampilData("");
                bersih();
            }
        });

        // Event Edit
        bedit.addActionListener(e -> {
            if (idTerpilih == -1) {
                JOptionPane.showMessageDialog(null, "Pilih data yang akan diedit!");
                return;
            }
            if (validasi()) {
                dao.editPegawai(idTerpilih, tnama.getText(), tjabatan.getText(),
                    ctipe.getSelectedItem().toString(), Double.parseDouble(tgaji.getText()));
                JOptionPane.showMessageDialog(null, "Data berhasil diedit");
                tampilData("");
                bersih();
                idTerpilih = -1;
            }
        });

        // Event Hapus
        bhapus.addActionListener(e -> {
            int baris = table.getSelectedRow();
            if (baris >= 0) {
                int confirm = JOptionPane.showConfirmDialog(null, "Yakin hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    int id = Integer.parseInt(table.getValueAt(baris, 0).toString());
                    dao.hapusPegawai(id);
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                    tampilData("");
                    bersih();
                    idTerpilih = -1;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pilih data yang akan dihapus!");
            }
        });

        // Event Bersih
        bbersih.addActionListener(e -> {
            bersih();
            idTerpilih = -1;
        });

        // Event Slip Gaji (dengan interface)
        bslip.addActionListener(e -> {
            int baris = table.getSelectedRow();
            if (baris >= 0) {
                String nama = table.getValueAt(baris, 1).toString();
                String jabatan = table.getValueAt(baris, 2).toString();
                String tipe = table.getValueAt(baris, 3).toString();
                double gaji = Double.parseDouble(table.getValueAt(baris, 4).toString());
                
                FormGaji fg = new FormGaji(nama, jabatan, tipe, gaji);
                fg.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Pilih pegawai dulu!");
            }
        });

        // Event Cari
        bcari.addActionListener(e -> {
            tampilData(tcari.getText());
        });

        // Event Refresh
        brefresh.addActionListener(e -> {
            tcari.setText("");
            tampilData("");
        });

        // Klik tabel -> isi form
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int baris = table.getSelectedRow();
                if (baris >= 0) {
                    idTerpilih = Integer.parseInt(table.getValueAt(baris, 0).toString());
                    tnama.setText(table.getValueAt(baris, 1).toString());
                    tjabatan.setText(table.getValueAt(baris, 2).toString());
                    String tipe = table.getValueAt(baris, 3).toString();
                    ctipe.setSelectedItem(tipe);
                    tgaji.setText(table.getValueAt(baris, 4).toString());
                }
            }
        });

        tampilData("");
    }

    private boolean validasi() {
        if (tnama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong!");
            return false;
        }
        if (tjabatan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Jabatan tidak boleh kosong!");
            return false;
        }
        if (tgaji.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Gaji tidak boleh kosong!");
            return false;
        }
        try {
            double gaji = Double.parseDouble(tgaji.getText());
            if (gaji <= 0) {
                JOptionPane.showMessageDialog(null, "Gaji harus lebih dari 0!");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Gaji harus berupa angka!");
            return false;
        }
        return true;
    }

    public void tampilData(String keyword) {
        dao.tampilData(table, keyword);
    }

    public void bersih() {
        tnama.setText("");
        tjabatan.setText("");
        tgaji.setText("");
        ctipe.setSelectedIndex(0);
        table.clearSelection();
    }
}