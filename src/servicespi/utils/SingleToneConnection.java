/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicespi.utils;

/**
 *
 * @author Mynet
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mynet
 */
public class SingleToneConnection {
    private static Connection cnx =null;
    
   static {
       try{
           Class.forName("com.mysql.jdbc.Driver");
           cnx=DriverManager.getConnection("jdbc:mysql://localhost:3306/animaland","root","");
          }
       catch(ClassNotFoundException | SQLException e)
       {
           e.printStackTrace();
       } 
   }
   public static Connection getConnection ()
   {
           return cnx;
}
}

