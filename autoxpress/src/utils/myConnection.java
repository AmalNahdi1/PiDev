/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amal
 */
public class myConnection {
    
             private String url = "jdbc:mysql://localhost:3306/esprit3a18";
    private String login = "root";
    private String pwd = "";
    private Connection cnx ;
    private static myConnection instance;

    public  myConnection() {

        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion etablie! ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    public Connection getCnx() {
        return cnx;
    }
    public static myConnection getInstance(){
        if (instance == null){
            instance =new myConnection ();
        }
        return instance ;
    }

}
