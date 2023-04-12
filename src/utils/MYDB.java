/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sarra Kachouandi
 */
public class MYDB {
    final String url="jdbc:mysql://localhost:3306/animaland";
    final String username="root";
    final String pwd="";
    
    
    private Connection conx;
    
    public static MYDB instance;
    
    
    public static MYDB getInstance(){
        if (instance == null)
           instance = new MYDB();
        return instance;
        
    }
    private MYDB(){
        
        try {
            conx = DriverManager.getConnection(url, username, pwd);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
    }

    public Connection getConx() {
        return conx;
    }
    
    
}

