/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TugasBesar;

import TugasBesar.connection.ConnectionDb;
import TugasBesar.controllers.FormTiket;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author lenovo
 */
class DataKursi {
    String id_kursi;
    String no_kursi;
    DataKursi(String id_kursi,String no_kursi) {
        this.id_kursi = id_kursi;
        this.no_kursi = no_kursi;
    }
}

public class FormPetaKursi extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form Button
     */
    private int totalBtn = 40;
    private int index = 5;
    public String jam, id_film, tanggal, id_jadwal, id_studio;
    private ConnectionDb db;
    private JButton[] seatButton = new JButton[40];
    public int harga_tiket = 0;
    public List<String> kursi = new ArrayList<String>();
    

    public FormPetaKursi(DataMovie dm, ConnectionDb db) {
        this.jam = dm.jam;
        this.tanggal = dm.tanggal;
        this.id_jadwal = dm.id_jadwal;
        this.id_studio = dm.id_studio;
        this.id_film = dm.id_film;
        this.harga_tiket = harga_tiket;
        this.db = db;
        this.kursi = kursi;
        initComponents();
        setLocationRelativeTo(null);
        initialize();
    }
    private DataKursi[] dataKursi() {
        DataKursi[] output = new DataKursi[40];
        try {
            db.rs = db.perintah.executeQuery("SELECT *,DAYNAME(date) as nama_hari from"
                    + " studio RIGHT JOIN jadwal on studio.id_studio=jadwal.id_studio RIGHT JOIN peta_kursi ON studio.id_studio=peta_kursi.id_studio"
                    + " RIGHT JOIN waktu ON jadwal.id_waktu=waktu.id_waktu RIGHT JOIN harga ON harga.id_hari=jadwal.id_hari "
                    + "WHERE id_film='" + id_film + "' and date='" + tanggal + "' and jam_mulai='" + jam + "' AND studio.id_studio='" +id_studio+ "'");
            int index = 0;
            while (db.rs.next()) {
                output[index] = new DataKursi(db.rs.getString("id_kursi"),db.rs.getString("no_kursi"));
                harga_tiket = db.rs.getInt("harga");
                index++;
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.err.println("SQL err: Data Kursi");
        }
        return output;
    }

    private void initialize() {
        for (int i = 0; i < totalBtn; i++) {
            if ((i + 1) == index) {
                gamePAN.add(new JPanel()); // Empty panel for gap
                index += 4;
            }
            DataKursi[] kursi = dataKursi();
            seatButton[i] = new JButton(kursi[i].no_kursi);
            seatButton[i].addActionListener(this);
            seatButton[i].setCursor(new Cursor(HAND_CURSOR));
            seatButton[i].setBackground(Color.LIGHT_GRAY);
            gamePAN.add(seatButton[i]);
        }
        tgl.setText(tanggal + ", " + jam + "  |  " + id_studio);
        dataWarnaKursi();
    }

    private void dataWarnaKursi() {
        try {
            List<String> btnTerisi = new ArrayList<String>();
            db.rs = db.perintah.executeQuery("SELECT * from jadwal RIGHT JOIN pembelian_tiket ON"
                    + " jadwal.id_jadwal=pembelian_tiket.id_jadwal RIGHT JOIN peta_kursi ON pembelian_tiket.id_kursi=peta_kursi.id_kursi "
                    + "WHERE jadwal.id_studio='" + id_studio + "' and jadwal.id_jadwal='" + id_jadwal + "' and jadwal.date='" + tanggal + "' and jadwal.id_film='" + id_film + "'");
            while (db.rs.next()) {
                btnTerisi.add(db.rs.getString("no_kursi"));
            }
            for (int i = 0; i < totalBtn; i++) {
                if (btnTerisi.contains(seatButton[i].getText())) {
                    seatButton[i].setBackground(Color.red);
                }
            }
        } catch (SQLException e) {
            System.err.println("Sius");
            System.err.println(e);
        }
    }

    private List<DataKursi> checkWarnaBtn(Color warna, List<DataKursi> warnaArr){
        int i = 0;
        DataKursi[] data = dataKursi();
        while (i < totalBtn) {
            if (seatButton[i].getBackground() == warna) {
                warnaArr.add(new DataKursi(data[i].id_kursi,data[i].no_kursi));
            } 
            i++;
        }
        return warnaArr;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        List<DataKursi> hijauTotal = new ArrayList<DataKursi>();
//        int index = 0;
        for (int r = 0; r < totalBtn; r++) {
            if (ae.getSource() == seatButton[r]) {
//                    hijauTotal = checkWarnaBtn(Color.GREEN, hijauTotal);
                if (seatButton[r].getBackground() == Color.LIGHT_GRAY) {
                    seatButton[r].setBackground(Color.GREEN);
                    hijauTotal = checkWarnaBtn(Color.GREEN, hijauTotal);
                    if (hijauTotal.size() > 0) {
                        bayar.setEnabled(true);
                    }
                     jmlhTkt.setText("Jumlah Tiket : " + Integer.toString(hijauTotal.size()));
                    harga.setText("Harga : Rp. " + Integer.toString(harga_tiket *hijauTotal.size()));
                } else if (seatButton[r].getBackground() == Color.GREEN) {
                     seatButton[r].setBackground(Color.LIGHT_GRAY);
                    hijauTotal = checkWarnaBtn(Color.GREEN, hijauTotal);
//                    hijau.remove(index);
//                    index++;
                    if (hijauTotal.size() == 0) {
                        bayar.setEnabled(false);
                    }
                    jmlhTkt.setText("Jumlah Tiket : " + Integer.toString(hijauTotal.size()));
                    harga.setText("Harga : Rp. " + Integer.toString(harga_tiket * hijauTotal.size()));
                } else {
                    JOptionPane.showConfirmDialog(this,
                            "Kursi " + seatButton[r].getText() + " Sudah Terisi", "Alert", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_OPTION);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        gamePAN = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tgl = new javax.swing.JLabel();
        bayar = new javax.swing.JButton();
        harga = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        jmlhTkt = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PETA KURSI");

        gamePAN.setLayout(new java.awt.GridLayout(5, 9, 5, 5));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Layar Bioskop");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(304, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(292, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tgl.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tgl.setText("Tangggal");

        bayar.setText("Bayar");
        bayar.setEnabled(false);
        bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarActionPerformed(evt);
            }
        });

        harga.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        harga.setText("Harga  : Rp.0");

        jButton1.setBackground(new java.awt.Color(0, 255, 0));

        jButton2.setBackground(new java.awt.Color(255, 0, 0));

        jLabel2.setText("Tidak Tersedia");

        jLabel3.setText("Dipilih");

        jLabel4.setText("Tersedia");

        back.setText("<");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        jmlhTkt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jmlhTkt.setText("Jumlah Tiket :  0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jmlhTkt, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bayar))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gamePAN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tgl, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(168, 168, 168)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(38, 38, 38)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(42, 42, 42)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(back)
                            .addComponent(tgl))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gamePAN, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bayar)
                            .addComponent(harga)
                            .addComponent(jmlhTkt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void beliKursi(String tanggal,String id_jadwal, String id_kursi) {
        try {
            db.rs = db.perintah.executeQuery("SELECT COUNT(*) as total from pembelian_tiket");
            if(db.rs.next())
            db.perintah.execute("INSERT INTO pembelian_tiket VALUES ("
                    + (db.rs.getInt("total") +1) + ",'"+tanggal+"','"+id_jadwal+"','"+id_kursi+"','Terisi')" );
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    private void bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarActionPerformed
        List<DataKursi> hijau = new ArrayList<DataKursi>();
        hijau = checkWarnaBtn(Color.GREEN, hijau);
        for(int i=0;i < hijau.size();i++) {
            kursi.add(hijau.get(i).id_kursi);
        }
        int pilihan = JOptionPane.showConfirmDialog(this, 
                "Anda Yakin Ingin Memesan "+ kursi.size() +" tiket" 
                        + " dengan total Harga Rp. " + Integer.toString(harga_tiket * kursi.size()), "Confirm",
                JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(pilihan == 0) {
            dispose();
            for(String id: kursi) {
            beliKursi(tanggal, id_jadwal, id);            
            }
            DataMovie data = new DataMovie(id_jadwal,id_studio,id_film,tanggal,jam);
            new FormTiket(new javax.swing.JFrame(), true, new FormPetaKursi(data, db),kursi.size(),kursi,db).setVisible(true);
        }  else {
            kursi.clear();
        }
    }//GEN-LAST:event_bayarActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        dispose();
        new FormJadwal(id_film, db).setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton bayar;
    private javax.swing.JPanel gamePAN;
    private javax.swing.JLabel harga;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jmlhTkt;
    private javax.swing.JLabel tgl;
    // End of variables declaration//GEN-END:variables

}
