/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import database.Koneksi;
import javax.swing.*;
import java.sql.*;



public class FormTransaksi extends JFrame {
    JComboBox<String> cmbPelanggan, cmbMenu, cmbKasir;
    JTextField txtJumlah, txtTotal;
    JButton btnSimpan;

    public FormTransaksi() {
        setTitle("Form Transaksi");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cmbPelanggan = new JComboBox<>(); loadCombo(cmbPelanggan, "pelanggan", "id_pelanggan", "nama");
        cmbMenu = new JComboBox<>(); loadCombo(cmbMenu, "menu", "id_menu", "nama_menu");
        cmbKasir = new JComboBox<>(); loadCombo(cmbKasir, "kasir", "id_kasir", "nama_kasir");

        add(new JLabel("Pelanggan")).setBounds(20, 20, 100, 25);
        cmbPelanggan.setBounds(140, 20, 200, 25); add(cmbPelanggan);

        add(new JLabel("Menu")).setBounds(20, 60, 100, 25);
        cmbMenu.setBounds(140, 60, 200, 25); add(cmbMenu);

        add(new JLabel("Kasir")).setBounds(20, 100, 100, 25);
        cmbKasir.setBounds(140, 100, 200, 25); add(cmbKasir);

        add(new JLabel("Jumlah")).setBounds(20, 140, 100, 25);
        txtJumlah = new JTextField(); txtJumlah.setBounds(140, 140, 200, 25); add(txtJumlah);
        
        
        txtJumlah.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
        public void insertUpdate(javax.swing.event.DocumentEvent e) { hitungTotal();}
        public void removeUpdate(javax.swing.event.DocumentEvent e) { hitungTotal();}
        public void changedUpdate(javax.swing.event.DocumentEvent e) {}
    });
        
        

        add(new JLabel("Total")).setBounds(20, 180, 100, 25);
        txtTotal = new JTextField(); txtTotal.setBounds(140, 180, 200, 25); txtTotal.setEditable(false); add(txtTotal);

        btnSimpan = new JButton("Simpan"); btnSimpan.setBounds(140, 220, 100, 30); add(btnSimpan);
        btnSimpan.addActionListener(e -> simpan());

        cmbMenu.addActionListener(e -> hitungTotal());

        setVisible(true);
    }

    private void loadCombo(JComboBox<String> combo, String table, String idCol, String nameCol) {
        try (Connection conn = Koneksi.getKoneksi()) {
            String sql = "SELECT * FROM " + table;
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                combo.addItem(rs.getString(idCol) + " - " + rs.getString(nameCol));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getHargaMenu() {
        try (Connection conn = Koneksi.getKoneksi()) {
            String[] split = cmbMenu.getSelectedItem().toString().split(" - ");
            String sql = "SELECT harga FROM menu WHERE id_menu = " + split[0];
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (rs.next()) return rs.getInt("harga");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void hitungTotal() {
        try {
            int jumlah = Integer.parseInt(txtJumlah.getText());
            int total = jumlah * getHargaMenu();
            txtTotal.setText(String.valueOf(total));
        } catch (Exception e) {
            txtTotal.setText("0");
        }
    }

    private void simpan() {
    // Validasi input sebelum menyimpan
    if (cmbPelanggan.getSelectedItem() == null ||
        cmbMenu.getSelectedItem() == null ||
        cmbKasir.getSelectedItem() == null ||
        txtJumlah.getText().trim().isEmpty()) {
        
        JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
        return;
    }

    try (Connection conn = Koneksi.getKoneksi()) {
        String[] p = cmbPelanggan.getSelectedItem().toString().split(" - ");
        String[] m = cmbMenu.getSelectedItem().toString().split(" - ");
        String[] k = cmbKasir.getSelectedItem().toString().split(" - ");

        int jumlah = Integer.parseInt(txtJumlah.getText());
        int total = jumlah * getHargaMenu();  // Bisa juga pakai Integer.parseInt(txtTotal.getText())

        String sql = "INSERT INTO transaksi (id_pelanggan, id_menu, id_kasir, jumlah, total_harga) VALUES (?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, Integer.parseInt(p[0]));
        ps.setInt(2, Integer.parseInt(m[0]));
        ps.setInt(3, Integer.parseInt(k[0]));
        ps.setInt(4, jumlah);
        ps.setInt(5, total);
        ps.executeUpdate();

        JOptionPane.showMessageDialog(this, "Transaksi disimpan.");
        txtJumlah.setText(""); 
        txtTotal.setText("");
    } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka.");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}

