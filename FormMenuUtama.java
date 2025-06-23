/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import javax.swing.*;



public class FormMenuUtama extends JFrame {
    JButton btnPelanggan, btnMenu, btnKasir, btnTransaksi;

    public FormMenuUtama() {
        setTitle("Aplikasi Warung - Menu Utama");
        setSize(300, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnPelanggan = new JButton("Form Pelanggan");
        btnPelanggan.setBounds(60, 30, 160, 40); add(btnPelanggan);
        btnPelanggan.addActionListener(e -> new FormPelanggan());

        btnMenu = new JButton("Form Menu");
        btnMenu.setBounds(60, 80, 160, 40); add(btnMenu);
        btnMenu.addActionListener(e -> new FormMenu());

        btnKasir = new JButton("Form Kasir");
        btnKasir.setBounds(60, 130, 160, 40); add(btnKasir);
        btnKasir.addActionListener(e -> new FormKasir());

        btnTransaksi = new JButton("Form Transaksi");
        btnTransaksi.setBounds(60, 180, 160, 40); add(btnTransaksi);
        btnTransaksi.addActionListener(e -> new FormTransaksi());

        setVisible(true);
    }
}

