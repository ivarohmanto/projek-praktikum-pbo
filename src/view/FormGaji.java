package view;

import interfacepegawai.PegawaiInterface;
import model.PegawaiKontrak;
import model.PegawaiTetap;
import javax.swing.*;

public class FormGaji extends JFrame {

    JTextArea area = new JTextArea();
    JButton bcetak = new JButton("Cetak Slip");

    public FormGaji(String nama, String jabatan, String tipe, double gajiKotor) {
        setTitle("Slip Gaji");
        setSize(400, 450);
        setLayout(null);
        setLocationRelativeTo(null);

        JScrollPane sp = new JScrollPane(area);
        sp.setBounds(20, 20, 340, 300);
        add(sp);
        bcetak.setBounds(120, 340, 120, 35);
        add(bcetak);

        PegawaiInterface pegawai;
        double gajiBersih;
        String keterangan;

        if (tipe.equalsIgnoreCase("Tetap")) {
            double bonus = gajiKotor * 0.1;
            pegawai = new PegawaiTetap(0, nama, jabatan, gajiKotor, bonus);
            gajiBersih = pegawai.hitungGaji();
            keterangan = "Bonus (10%)    : " + formatRupiah(bonus);
        } else {
            double potongan = gajiKotor * 0.05;
            pegawai = new PegawaiKontrak(0, nama, jabatan, gajiKotor, potongan);
            gajiBersih = pegawai.hitungGaji();
            keterangan = "Potongan (5%)  : " + formatRupiah(potongan);
        }

        area.setText(
            "========== SLIP GAJI ==========\n\n" +
            "Nama        : " + nama + "\n" +
            "Jabatan     : " + jabatan + "\n" +
            "Tipe        : " + tipe + "\n" +
            "-------------------------------\n" +
            "Gaji Pokok  : " + formatRupiah(gajiKotor) + "\n" +
            keterangan + "\n" +
            "-------------------------------\n" +
            "TOTAL GAJI  : " + formatRupiah(gajiBersih) + "\n" +
            "===============================\n\n" +
            "\n" +
            " "
        );

        bcetak.addActionListener(e -> {
            try {
                area.print();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });
    }

    private String formatRupiah(double angka) {
        return String.format("Rp %,.0f", angka).replace(",", ".");
    }
}