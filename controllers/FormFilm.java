/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TugasBesar.controllers;
 
import TugasBesar.connection.ConnectionDb;
import java.sql.PreparedStatement;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lenovo
 */
public class FormFilm extends javax.swing.JDialog {

    DefaultTableModel model;
    Boolean modesimpan, dataditemukan;
    Connection koneksi;
    ConnectionDb db;
    

    public FormFilm() {
        initComponents();
        setLocationRelativeTo(null);
        model = (DefaultTableModel) jTable1.getModel();
        modesimpan = true;
        dataditemukan = false;
        db = new ConnectionDb();
    }

    private void tampilData() {
        try {
            db.rs = db.perintah.executeQuery("SELECT * FROM film");
            model.setRowCount(0);
            model.fireTableDataChanged();
            while (db.rs.next()) {
                model.addRow(
                        new Object[]{
                            db.rs.getString("id_film"),
                            db.rs.getString("nama_film"),
                            db.rs.getString("durasi_menit"),
                            db.rs.getString("desc_film")

                        }
                );
            }
        } catch (SQLException e) {
            System.err.println("Query Error");
        }
    }

    private void clear() {
        textIdFilm.setText("");
        textDurasi.setText("");
        textNamaFilm.setText("");
        textDesc.setText("");
        gambar.setText("");
        textImg.setText("");

    }

    private boolean cariData(String data) {
        if (!textIdFilm.getText().equals("")) {
            try {
                db.rs = db.perintah.executeQuery("SELECT * FROM film WHERE id_film = '" + data + "'");
                if (db.rs.next()) { //data ditemukan
                    return true;
                } else { // data tidak ditemukan
                    return false;
                }
            } catch (SQLException e) {
                System.err.println("Query tak bisa");
            }
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        gambar = new javax.swing.JLabel();
        kirim = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textDesc = new javax.swing.JTextArea();
        textIdFilm = new javax.swing.JTextField();
        textNamaFilm = new javax.swing.JTextField();
        textDurasi = new javax.swing.JTextField();
        ubah = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        baru = new javax.swing.JButton();
        textImg = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id film", "nama film", "durasi menit", "desc film"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FORM FILM");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(318, 318, 318)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel2.setText("ID Film         :");

        jLabel3.setText("Nama Film   :");

        jLabel4.setText("Durasi          :");

        jLabel5.setText("Deskripsi      :");

        gambar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        kirim.setText("Import gambar");
        kirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kirimActionPerformed(evt);
            }
        });

        textDesc.setColumns(20);
        textDesc.setRows(5);
        textDesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textDescKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(textDesc);

        textIdFilm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textIdFilmKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textIdFilmKeyTyped(evt);
            }
        });

        textNamaFilm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textNamaFilmKeyTyped(evt);
            }
        });

        textDurasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textDurasiKeyTyped(evt);
            }
        });

        ubah.setText("Ubah");
        ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahActionPerformed(evt);
            }
        });

        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        batal.setText("Batal");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        simpan.setText("Simpan");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        baru.setText("Baru");
        baru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baruActionPerformed(evt);
            }
        });

        textImg.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textIdFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(baru)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(simpan))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textNamaFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ubah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(batal)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(gambar, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textImg))
                        .addGap(18, 18, 18)
                        .addComponent(kirim)
                        .addGap(54, 54, 54))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textIdFilm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ubah)
                    .addComponent(hapus)
                    .addComponent(batal)
                    .addComponent(simpan)
                    .addComponent(baru))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(textNamaFilm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(textDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(gambar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kirim)
                            .addComponent(textImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
        textIdFilm.setEnabled(false);
        textNamaFilm.setEnabled(true);
        textNamaFilm.requestFocus();
        textDurasi.setEnabled(true);
        textDurasi.requestFocus();
        textDesc.setEnabled(true);
        textDesc.requestFocus();
        baru.setEnabled(false);
        simpan.setEnabled(true);
        ubah.setEnabled(false);
        hapus.setEnabled(false);
        batal.setEnabled(true);
        kirim.setEnabled(true);
        tampilGambar();
        modesimpan = false;
    }//GEN-LAST:event_ubahActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // pengecekan apakah jTextField1 atau kode prodi belum terisi
        if (textIdFilm.getText().equals("")) {
            // menampilkan objek JOptionPane dalam bentuk message dialog jika
            // jTextField1 atau kode prodi berlum terisi
            JOptionPane.showMessageDialog(
                    this, // parent, biasanya this
                    "Silahkan lengkapi data id film!", // pesannya
                    "Informasi", // judul pesan
                    JOptionPane.INFORMATION_MESSAGE // tipe pesan
            );
            textIdFilm.requestFocus();
        } else if (textNamaFilm.getText().equals("")) {
            JOptionPane.showMessageDialog(
                    this, // parent, biasanya this
                    "Silahkan lengkapi data keterangan!", // pesannya
                    "Informasi", // judul pesan
                    JOptionPane.INFORMATION_MESSAGE // tipe pesan
            );
            textNamaFilm.requestFocus();
        } else if (textDurasi.getText().equals("")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Silahkan Lengkapi durasi",
                    "Informasi",
                    JOptionPane.INFORMATION_MESSAGE
            );
            textDurasi.requestFocus();

        } else if (textDesc.getText().equals("")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Silahkan Lengkapi deskripsi",
                    "Informasi",
                    JOptionPane.INFORMATION_MESSAGE
            );
            textDesc.requestFocus();

        } else { // jika jTextField1 dan jTextField2 telah terisi
            if (modesimpan == true) { // mode insert
                dataditemukan = cariData(textIdFilm.getText());
                if (dataditemukan == true) {
                    JOptionPane.showMessageDialog(
                            this, // parent, biasanya this
                            "Penyimpanan data film gagal dilakukan, "
                            + "karena id film " + textIdFilm.getText()
                            + " telah ada di tabel", // pesannya
                            "Informasi", // judul pesan
                            JOptionPane.INFORMATION_MESSAGE // tipe pesan
                    );
                } else {
                    try {
                        InputStream image = new FileInputStream(new File(textImg.getText()));
                        PreparedStatement pst = koneksi.prepareStatement("INSERT INTO film VALUES ( ?,?,?,?,?)");
                        pst.setString(1, textIdFilm.getText());
                        pst.setString(2, textNamaFilm.getText());
                        pst.setInt(3, Integer.parseInt(textDurasi.getText()));
                        pst.setString(4, textDesc.getText());
                        pst.setBlob(5, image);
                        pst.execute();
                        tampilData();
                    } catch (SQLException e) {
                        System.out.println(e);
                        JOptionPane.showMessageDialog(
                                this,
                                "Penyimpanan data film gagal dilakukan",
                                "Informasi",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } catch (FileNotFoundException ex) {
                        System.err.println("siuuuuuuuuu");
                    }
                }

            } else { // modeSimpan = false (mode update)
                // pengecekan apakah tidak ada baris data di jTable1 yang terseleksi
                // atau apakah nilai variabel dataDitemukan adalah false
                if ((jTable1.getSelectedRow() == -1) && (dataditemukan == false)) {
                    JOptionPane.showMessageDialog(
                            this, // parent, biasanya this
                            "Silahkan pilih baris yang akan diubah!", // pesannya
                            "Informasi", // judul pesan
                            JOptionPane.INFORMATION_MESSAGE // tipe pesan
                    );
                } else {
                    // update data
                    try {
                        db.perintah.execute("UPDATE film SET nama_film = '" + textNamaFilm.getText()
                                + "', durasi_film = " + Integer.parseInt(textDurasi.getText())
                                + ", deskripsi_film = '" + textDesc.getText()
                                + "', image = ? WHERE id_film = '" + textIdFilm.getText() + "'"
                        );
                        tampilData();
                    } catch (SQLException e) {

                        System.err.println("Query Error");
                    }
                }
            }
            clear();
            textIdFilm.setEnabled(false);
            textNamaFilm.setEnabled(false);
            textDurasi.setEnabled(false);
            textDesc.setEnabled(false);
            gambar.setIcon(null);
            baru.setEnabled(true);
            baru.requestFocus();
            hapus.setEnabled(false);
            ubah.setEnabled(false);
            batal.setEnabled(false);
            kirim.setEnabled(false);
            simpan.setEnabled(false);

        }
    }//GEN-LAST:event_simpanActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        if ((jTable1.getSelectedRow() == -1) && (dataditemukan == false)) {
            JOptionPane.showMessageDialog(this, "Silahkan pilih baris data yang akan dihapus", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int pilihan;
            pilihan = JOptionPane.showConfirmDialog(this, "Apakah anda yakin akan menghapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (pilihan == 0) {
                try {
                    db.perintah.execute("DELETE FROM film WHERE id_film = '" + textIdFilm.getText() + "'");
                    tampilData();

                } catch (SQLException e) {
                    System.err.println("Query Error");
                }
                clear();
                textIdFilm.setEnabled(false);
                textDurasi.setEnabled(false);
                textDesc.setEnabled(false);
                textNamaFilm.setEnabled(false);
                baru.setEnabled(true);
                baru.requestFocus();
                simpan.setEnabled(false);
                hapus.setEnabled(false);
                batal.setEnabled(false);
                ubah.setEnabled(false);
                kirim.setEnabled(false);
                gambar.setIcon(null);
            }
        }
    }//GEN-LAST:event_hapusActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        textIdFilm.setEnabled(false);
        textDurasi.setEnabled(false);
        textDesc.setEnabled(false);
        textNamaFilm.setEnabled(false);
        batal.setEnabled(false);
        gambar.setEnabled(false);
        textImg.setEnabled(false);
        baru.setEnabled(true);
        baru.requestFocus();
        simpan.setEnabled(false);
        hapus.setEnabled(false);
        ubah.setEnabled(false);
        kirim.setEnabled(false);
        clear();
    }//GEN-LAST:event_batalActionPerformed

    private void kirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kirimActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            textImg.setText(path);

            try {
                //konversi blob gambar ke ImageIcon
                byte[] img = Files.readAllBytes(selectedFile.toPath());
                ImageIcon imageIcon = new ImageIcon(img);
                int imageWidth = imageIcon.getIconWidth();
                int imageHeight = imageIcon.getIconHeight();

                Image scaleImage = imageIcon.getImage().getScaledInstance((int) (imageWidth), (int) (imageHeight), Image.SCALE_SMOOTH);
                gambar.setIcon(new ImageIcon(scaleImage));

            } catch (Exception e) {
                System.err.println("errroo");
            }
        }

    }//GEN-LAST:event_kirimActionPerformed

    private void textIdFilmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textIdFilmKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dataditemukan = cariData(textIdFilm.getText());
            if (dataditemukan == true) {
                int pilihan;
                pilihan = JOptionPane.showConfirmDialog(
                        this,
                        "ID film " + textIdFilm.getText() + " sudah ada! "
                        + "Apakah anda ingin mengubah/menghapus data?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION
                );
                if (pilihan == 0) { // yes
                    try {
                        textNamaFilm.setText(db.rs.getString("nama_film"));
                        textDurasi.setText(db.rs.getString("durasi_film"));
                        textDesc.setText(db.rs.getString("desc_film"));
                        textIdFilm.setEnabled(false);
                        textNamaFilm.setEnabled(true);
                        textDurasi.setEnabled(true);
                        textDesc.setEnabled(true);
                        baru.setEnabled(false);
                        simpan.setEnabled(true);
                        ubah.setEnabled(false);
                        hapus.setEnabled(true);
                        batal.setEnabled(true);
                        kirim.setEnabled(true);

                    } catch (SQLException e) {

                        System.err.println("Query error");
                    }
                } else { // no
                    textIdFilm.setText("");
                    textIdFilm.requestFocus();
                }
            } else { // data tidak ditemukan
                textNamaFilm.setEnabled(true);
                textNamaFilm.requestFocus();
                textDurasi.setEnabled(true);
                textDesc.setEnabled(true);
                textImg.setEnabled(true);
                simpan.setEnabled(true);
                ubah.setEnabled(false);
                hapus.setEnabled(true);
                batal.setEnabled(true);
                kirim.setEnabled(true);
            }
        }
    }//GEN-LAST:event_textIdFilmKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        clear();
        tampilData();
        textIdFilm.setEnabled(false);
        textNamaFilm.setEnabled(false);
        textDurasi.setEnabled(false);
        textDesc.setEnabled(false);
        textImg.setEnabled(false);
        baru.setEnabled(true);
        baru.requestFocus();
        simpan.setEnabled(false);
        kirim.setEnabled(false);
        ubah.setEnabled(false);
        hapus.setEnabled(false);
        batal.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

    private void baruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baruActionPerformed
        textIdFilm.setEnabled(true);
        textIdFilm.requestFocus();
        textNamaFilm.setEnabled(false);
        textDurasi.setEnabled(false);
        textDesc.setEnabled(false);
        baru.setEnabled(false);
        simpan.setEnabled(false);
        ubah.setEnabled(false);
        hapus.setEnabled(false);
        batal.setEnabled(false);
        kirim.setEnabled(false);
        modesimpan = true;
    }//GEN-LAST:event_baruActionPerformed

    private void textIdFilmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textIdFilmKeyTyped
        if (textIdFilm.getText().length() >= 5) {
            evt.consume();
    }//GEN-LAST:event_textIdFilmKeyTyped
    }
    private void textNamaFilmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNamaFilmKeyTyped
        if (textNamaFilm.getText().length() >= 50) {
            evt.consume();
        }
    }//GEN-LAST:event_textNamaFilmKeyTyped

    private void textDurasiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textDurasiKeyTyped
        if (textDurasi.getText().length() >= 3) {
            evt.consume();
        }

    }//GEN-LAST:event_textDurasiKeyTyped

    private void textDescKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textDescKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textDescKeyReleased
    private void tampilGambar() {
        try {
            db.rs = db.perintah.executeQuery("SELECT * FROM film WHERE id_film='" + model.getValueAt(jTable1.getSelectedRow(), 0).toString() + "'");
            if (db.rs.next()) {
                byte[] img = db.rs.getBytes("image");
                ImageIcon format = new ImageIcon(img);
                Image images = format.getImage();
                Image imag = images.getScaledInstance(format.getIconWidth(), format.getIconHeight(), Image.SCALE_SMOOTH);
                gambar.setIcon(new ImageIcon(imag));
            }
        } catch (SQLException ex) {
            System.err.println("Err: Tampil Gambar");
        }
    }
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (jTable1.getSelectedRow() >= 0) {
            textIdFilm.setText(model.getValueAt(jTable1.getSelectedRow(), 0).toString());
            textNamaFilm.setText(model.getValueAt(jTable1.getSelectedRow(), 1).toString());
            textDurasi.setText(model.getValueAt(jTable1.getSelectedRow(), 2).toString());
            textDesc.setText(model.getValueAt(jTable1.getSelectedRow(), 3).toString());
            tampilGambar();
            textIdFilm.setEnabled(false);
            textNamaFilm.setEnabled(false);
            textDurasi.setEnabled(false);
            textDesc.setEnabled(false);
            baru.setEnabled(false);
            simpan.setEnabled(false);
            ubah.setEnabled(true);
            ubah.requestFocus();
            hapus.setEnabled(true);
            batal.setEnabled(true);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FormFilm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FormFilm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FormFilm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FormFilm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormFilm().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton baru;
    private javax.swing.JButton batal;
    private javax.swing.JLabel gambar;
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton kirim;
    private javax.swing.JButton simpan;
    private javax.swing.JTextArea textDesc;
    private javax.swing.JTextField textDurasi;
    private javax.swing.JTextField textIdFilm;
    private javax.swing.JTextField textImg;
    private javax.swing.JTextField textNamaFilm;
    private javax.swing.JButton ubah;
    // End of variables declaration//GEN-END:variables
}
