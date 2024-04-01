/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TugasBesar.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lenovo
 */
public class ConnectionDb {
    public Connection koneksi;
    public Statement perintah;
    public ResultSet rs;
    public ConnectionDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/bioskop_afung";
            String user = "root";
            String password = "";
            koneksi = DriverManager.getConnection(url, user, password);
            perintah = koneksi.createStatement();
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver tidak ditemukan");
        } catch (SQLException e) {
            System.err.println("Gagal koneksi atau menciptakan objek statement");
        }
    }
}
