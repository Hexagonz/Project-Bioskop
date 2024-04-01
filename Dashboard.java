/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TugasBesar;

import TugasBesar.connection.ConnectionDb;
import java.awt.Color;
import java.awt.Cursor;
import static java.awt.Frame.HAND_CURSOR;
import static java.awt.Frame.TEXT_CURSOR;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author lenovo
 */
class DataFilm {

    String judul;
    byte[] image;
}

public class Dashboard extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form Dashboard
     */
    private int size = 3;
    private JButton[][] butArray = new JButton[size][size];
    private JLabel[][] lblArray = new JLabel[size][size];
    private JLabel[][] imgArray = new JLabel[size][size];
    private JPanel[][] conMovie = new JPanel[size][size];
    private ConnectionDb db = new ConnectionDb();

    public Dashboard() {
        initComponents();
        setLocationRelativeTo(null);
        this.db = db;
        intialize();
    }

    public DataFilm[] dbToArray(String name, String image) {
        DataFilm[] data = new DataFilm[size * size];
        try {
            db.rs = db.perintah.executeQuery("SELECT * FROM film");
            int i = 0;
            byte[] img;
            while (db.rs.next()) {
                img = db.rs.getBytes("image");
                data[i] = new DataFilm();
                data[i].judul = db.rs.getString(name);
                data[i].image = img;
                i++;
            }
        } catch (SQLException e) {
            System.out.println("SQL " + name + " : Error ");
        }
        return data;
    }

    // Grid Layot Container
    private void intialize() {
        int index = 0;
        DataFilm[] db = dbToArray("nama_film", "image");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    cardsContainer(i, j, db[index].judul, db[index].image);
                } catch (NullPointerException e) {
                }
                index++;
            }
        }
        validate();
    }

    // Container Card List Film (JPanel)
    private void cardsContainer(int i, int j, String nm_movie, byte[] img) {
        conMovie[i][j] = new JPanel();
        conMovie[i][j].setBackground(new Color(0, 102, 102));
        conMovie[i][j].setSize(400, 600);
        conMovie[i][j].setLayout(null);
        gamePAN.add(conMovie[i][j]);
        namesCard(i, j, nm_movie);
        imgsCard(i, j, img);
        buttonsCard(i, j);
    }

    // Movie Name (JLabel)
    private void namesCard(int i, int j, String nm_movie) {
        lblArray[i][j] = new JLabel(nm_movie, JLabel.CENTER);
        lblArray[i][j].setForeground(Color.white);
        lblArray[i][j].setBounds(0, 0, 210, 30);
        lblArray[i][j].setCursor(new Cursor(TEXT_CURSOR));
        conMovie[i][j].add(lblArray[i][j]);
    }

    //  Troli Button (JButton)
    private void buttonsCard(int i, int j) {
        butArray[i][j] = new JButton();
        butArray[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/TugasBesar/src/troli.png"))); // NOI18N  
        butArray[i][j].setBackground(Color.WHITE);
        butArray[i][j].setBounds(-4, 135, 212, 30);
        butArray[i][j].setCursor(new Cursor(HAND_CURSOR));
        butArray[i][j].addActionListener(this);
        conMovie[i][j].add(butArray[i][j]);
    }

    private void imgsCard(int i, int j, byte[] img) {
        try {
            InputStream inputStream = new ByteArrayInputStream(img);
            BufferedImage images = ImageIO.read(inputStream);
            imgArray[i][j] = new JLabel(new ImageIcon(images));
            imgArray[i][j].setBounds(60, 26, 90, 110);
            Icon icn = imgArray[i][j].getIcon();
            ImageIcon icon = (ImageIcon) icn;
            Image image = icon.getImage().getScaledInstance(imgArray[i][j].getWidth(), imgArray[i][j].getHeight(), Image.SCALE_SMOOTH);
            imgArray[i][j].setIcon(new ImageIcon(image));
            conMovie[i][j].add(imgArray[i][j]);
        } catch (IOException e) {
            System.err.println("ieie");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int index = 0;
        DataFilm[] db = dbToArray("id_film", "image");
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (ae.getSource() == butArray[r][c]) {
                    try {
                        this.db.rs = this.db.perintah.executeQuery("SELECT * FROM jadwal WHERE id_film='" + db[index].judul + "'");
                        if (this.db.rs.next()) {                 
                            dispose();
                            FormJadwal fj = new FormJadwal(db[index].judul, this.db);
                            fj.setVisible(true);
                        } else {
                            JOptionPane.showConfirmDialog(this, "Film Belum Tayang", "Alert", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_OPTION);
                        }
                    } catch (SQLException ex) {
                        System.err.println("SQL: Btn Film");
                    }
                }
                index++;
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

        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        gamePAN = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DAFTAR FILM");

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TugasBesar/src/cinema.png"))); // NOI18N
        jLabel1.setText("AFUNG TZY");

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TugasBesar/src/borad.png"))); // NOI18N
        jLabel2.setText("Now Playing");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel2);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TugasBesar/src/plus.png"))); // NOI18N
        jLabel4.setText("Admin");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel4);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        gamePAN.setBackground(new java.awt.Color(204, 204, 204));
        gamePAN.setLayout(new java.awt.GridLayout(3, 3, 10, 10));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(gamePAN, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(gamePAN, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(560, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setForeground(Color.red);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        new FormAdmin(new javax.swing.JFrame(),false ).setVisible(true);
    }//GEN-LAST:event_jLabel4MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel gamePAN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables

}
