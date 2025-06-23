/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import database.Koneksi;
import javax.swing.*;
import java.sql.*;



public class FormKasir extends JFrame {
    JTextField txtNamaKasir;
    JButton btnSimpan;

    public FormKasir() {
        setTitle("Form Kasir");
        setSize(350, 150);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new JLabel("Nama Kasir")).setBounds(20, 20, 100, 25);
        txtNamaKasir = new JTextField(); txtNamaKasir.setBounds(120, 20, 180, 25); add(txtNamaKasir);

        btnSimpan = new JButton("Simpan"); btnSimpan.setBounds(120, 60, 100, 30); add(btnSimpan);
        btnSimpan.addActionListener(e -> simpan());

        setVisible(true);
    }

    private void simpan() {
        try (Connection conn = Koneksi.getKoneksi()) {
            String sql = "INSERT INTO kasir (nama_kasir) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtNamaKasir.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Kasir disimpan.");
            txtNamaKasir.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

