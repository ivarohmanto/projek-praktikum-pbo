package view;

import controller.LoginController;
import javax.swing.*;
import java.awt.event.*;

public class LoginForm extends JFrame {

    JLabel luser = new JLabel("Username");
    JLabel lpass = new JLabel("Password");

    JTextField tuser = new JTextField();
    JPasswordField tpass = new JPasswordField();

    JButton blogin = new JButton("Login");

    public LoginForm() {

        setTitle("Login");
        setSize(350, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Username
        luser.setBounds(30, 30, 100, 30);
        add(luser);

        tuser.setBounds(120, 30, 150, 30);
        add(tuser);

        // Password
        lpass.setBounds(30, 80, 100, 30);
        add(lpass);

        tpass.setBounds(120, 80, 150, 30);
        add(tpass);

        // Tombol Login
        blogin.setBounds(120, 140, 100, 30);
        add(blogin);

        // Event tombol login MEMANGGIL CONTROLLER
        blogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String username = tuser.getText();
                String password = new String(tpass.getPassword());

                // PANGGIL CONTROLLER
                LoginController controller = new LoginController();

                if (controller.login(username, password)) {

                    JOptionPane.showMessageDialog(null, "Login Berhasil");

                    Dashboard d = new Dashboard();
                    d.setVisible(true);

                    dispose(); // Tutup form login

                } else {

                    JOptionPane.showMessageDialog(null, "Username atau Password Salah");
                    
                    // Kosongkan field password biar user bisa coba lagi
                    tpass.setText("");
                }
            }
        });
    }
}