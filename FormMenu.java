/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import database.Koneksi;
import javax.swing.*;
import java.sql.*;



public class FormMenu extends JFrame {
    JTextField txtNamaMenu, txtHarga;
    JButton btnSimpan;

    public FormMenu() {
        setTitle("Form Menu");
        setSize(350, 200);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new JLabel("Nama Menu")).setBounds(20, 20, 100, 25);
        txtNamaMenu = new JTextField(); txtNamaMenu.setBounds(120, 20, 180, 25); add(txtNamaMenu);

        add(new JLabel("Harga")).setBounds(20, 60, 100, 25);
        txtHarga = new JTextField(); txtHarga.setBounds(120, 60, 180, 25); add(txtHarga);

        btnSimpan = new JButton("Simpan"); btnSimpan.setBounds(120, 100, 100, 30); add(btnSimpan);
        btnSimpan.addActionListener(e -> simpan());

        setVisible(true);
    }

    private void simpan() {
        try (Connection conn = Koneksi.getKoneksi()) {
            String sql = "INSERT INTO menu (nama_menu, harga) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtNamaMenu.getText());
            ps.setInt(2, Integer.parseInt(txtHarga.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Menu disimpan.");
            txtNamaMenu.setText(""); txtHarga.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

