package view;

import javax.swing.*;

public class FormGaji extends JFrame {

    JTextArea area = new JTextArea();

    JButton bcetak = new JButton("Cetak Slip");

    public FormGaji(String nama,
            String jabatan,
            String tipe,
            String gaji) {

        setTitle("Slip Gaji");
        setSize(400,400);
        setLayout(null);
        setLocationRelativeTo(null);

        JScrollPane sp = new JScrollPane(area);
        sp.setBounds(20,20,340,250);
        add(sp);

        bcetak.setBounds(120,300,120,35);
        add(bcetak);

        area.setText(
                "===== SLIP GAJI =====\n\n" +
                "Nama : " + nama + "\n" +
                "Jabatan : " + jabatan + "\n" +
                "Tipe : " + tipe + "\n" +
                "Gaji : " + gaji
        );

        bcetak.addActionListener(e -> {

            try {

                area.print();

            } catch(Exception ex) {

                System.out.println(ex);
            }
        });
    }
}