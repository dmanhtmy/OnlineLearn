/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quang
 */
public class DBContext {

    public Connection connection;

    public DBContext() {
        try {
            String user = "root";
            String pass = "vanh1108";
            String url = "jdbc:mysql://localhost:3306/onlinelearn?allowPublicKeyRetrieval=true&useSSL=false";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DBContext dBContext = new DBContext();
        if (dBContext.connection != null) {
            System.out.println("Successful");
        } else {
            System.out.println("Fail");
        }
    }
}