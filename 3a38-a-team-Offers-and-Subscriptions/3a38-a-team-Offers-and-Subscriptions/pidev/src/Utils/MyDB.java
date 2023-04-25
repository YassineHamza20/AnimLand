/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class MyDB {
     final String url="jdbc:mysql://localhost:3306/pidevv";
    final String pwd="";
    final String login="root";
    private  static MyDB instance;
    Connection connexion;
    private MyDB(){
        try{
             connexion =  (Connection) DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            
        }
    }
      public static MyDB getInstance(){
    if (instance == null)
        instance = new MyDB();
    return instance;
    }
      public Connection getConnexion() {
        return connexion;
    }
}
