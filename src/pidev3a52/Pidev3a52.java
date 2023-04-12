/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev3a52;
import entites.Reclamation;
import entites.Reponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ReclamationService;
import services.ReponseService;
import utils.MyDB;
/**
 * @author Ennou
 */
public class Pidev3a52 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        MyDB db = new MyDB();
//        MyDB db1 = new MyDB();
        MyDB db2 = MyDB.getInstance();
        MyDB db3 = MyDB.getInstance();
        Reclamation p = new Reclamation("phone","broken","yyyyyyyy");
        ReclamationService ps = new ReclamationService();
    // ps.ajouter(p);
     //  ps.supprimer(10);
    //   ps.modifier(p,63);
      /* try {
           System.out.println(ps.afficherListe());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
 ///////////////////////////////////
      Reponse re     =  new Reponse("phone","broken","swwwww",2);
      ReponseService rs = new ReponseService();
      //   rs.ajouter(re);
         // rs.supprimer(15);
         // rs.modifier(re,2);
    /*  try { 
             System.out.println(rs.afficherListe());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
        
    }}