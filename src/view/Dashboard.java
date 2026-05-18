package view;

import javax.swing.*;

public class Dashboard extends JFrame {

    JLabel judul = new JLabel("DASHBOARD ADMIN");

    JButton bpegawai = new JButton("Data Pegawai");
    JButton bkeluar = new JButton("Logout");

    public Dashboard() {

        setTitle("Dashboard");
        setSize(500,400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        judul.setBounds(160,30,200,30);
        add(judul);

        bpegawai.setBounds(140,120,200,40);
        add(bpegawai);

        bkeluar.setBounds(140,200,200,40);
        add(bkeluar);

        // Tombol Data Pegawai
        bpegawai.addActionListener(e -> {

            FormPegawai fp = new FormPegawai();
            fp.setVisible(true);

        });

        // Tombol Logout
        bkeluar.addActionListener(e -> {

            dispose();

            LoginForm login = new LoginForm();
            login.setVisible(true);

        });
    }
}