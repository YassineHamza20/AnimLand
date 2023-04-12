/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author sinoh
 */
public class ReclamationController implements Initializable {

    @FXML
    private TextField tfobjet;
    @FXML  
    private TextField tfdesciption;
    @FXML
    private TextField tfnom;
    @FXML
    private Button btnenvoyer;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void savereclamation(ActionEvent event) {
        String objet = tfobjet.getText();
        String description = tfdesciption.getText();
        String nom = tfnom.getText();
        
        Reclamation r = new  Reclamation(objet , description, nom);
           ReclamationService ps = new ReclamationService();
           ps.ajouter(r);
           FXMLLoader loader=new FXMLLoader(getClass().getResource("detailswindow.fxml"));
        try {
            Parent root = loader.load();
            DetailswindowController dwc = loader.getController();
            
            dwc.setobjet(r.getObjet());
            dwc.setdescription(r.getDescription());
            dwc.setnom(r.getNom());
            tfobjet.getScene().setRoot(root) ;
          
            tfnom.getScene().setRoot(root);
            
            
            
        } catch (IOException ex) {
             System.out.println(" Error: "+ ex.getMessage());
        }
    }
    
}
