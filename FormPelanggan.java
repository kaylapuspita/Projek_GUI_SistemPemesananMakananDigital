/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import database.Koneksi;
import javax.swing.*;
import java.sql.*;



public class FormPelanggan extends JFrame {
    JTextField txtNama, txtNoHp;
    JButton btnSimpan;

    public FormPelanggan() {
        setTitle("Form Pelanggan");
        setSize(350, 200);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new JLabel("Nama")).setBounds(20, 20, 80, 25);
        txtNama = new JTextField(); txtNama.setBounds(120, 20, 180, 25); add(txtNama);

        add(new JLabel("No HP")).setBounds(20, 60, 80, 25);
        txtNoHp = new JTextField(); txtNoHp.setBounds(120, 60, 180, 25); add(txtNoHp);

        btnSimpan = new JButton("Simpan"); btnSimpan.setBounds(120, 100, 100, 30); add(btnSimpan);
        btnSimpan.addActionListener(e -> simpan());

        setVisible(true);
    }

    private void simpan() {
        try (Connection conn = Koneksi.getKoneksi()) {
            String sql = "INSERT INTO pelanggan (nama, no_hp) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtNama.getText());
            ps.setString(2, txtNoHp.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data pelanggan disimpan.");
            txtNama.setText(""); txtNoHp.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
