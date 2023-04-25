/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Reclamation;
import entites.Reponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.ReclamationService;
import services.ReponseService;
import utils.MyDB;

/**
 *
 * @author yassine
 */
 
public class FirstWindow extends Application {
    public static void main(String[] args) {
    launch(args);
//        Reclamation p = new Reclamation("phone","broken","yyyyyyyy");
//        ReclamationService ps = new ReclamationService();
//    // ps.ajouter(p);
//     //  ps.supprimer(10);
//    //   ps.modifier(p,63);
//       try {
//           System.out.println(ps.afficherListe());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
// ///////////////////////////////////
//      Reponse re     =  new Reponse("phone","broken","swwwww",8);
//      ReponseService rs = new ReponseService();
//       // rs.ajouter(re);
//         // rs.supprimer(15);
//         // rs.modifier(re,2);
//    /*  try { 
//             System.out.println(rs.afficherListe());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }*/
//        
    }
    
    
    
    
    
    @Override
    public void start(Stage primaryStage) {
      Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("detailswindow.fxml"));
            Scene scene = new Scene(root,700,700);
             primaryStage.setTitle("Espace Reclamation ");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
              System.out.println(ex.getMessage());
        }
   
    }

    /**
     * @param args the command line arguments
     */
    
    
}
