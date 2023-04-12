/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Reponse;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ReponseService;

/**
 * FXML Controller class
 *
 * @author sinoh
 */
public class ReponseController implements Initializable {

    @FXML
    private TextField tfobjetr;
    @FXML  
    private TextField tfdesciptionr;
    @FXML
    private TextField tfnomr;
   @FXML
    private TextField tfrecidr;
    @FXML
    private Button btnenvoyerr;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void savereponse(ActionEvent event) {
        String objet = tfobjetr.getText();
        String description = tfdesciptionr.getText();
        String nom = tfnomr.getText();
          int reclamation_id = Integer.parseInt(tfrecidr.getText());
          
          if (objet.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "veuillez remplir le champ objet");
            alert.showAndWait();
        } else if (description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "veulliez remplir le champ description");
            alert.showAndWait();
        } else if ((nom.isEmpty())) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "veuillez remplir le champ nom");
            alert.showAndWait();
  
        }else{
        
        Reponse r = new  Reponse(objet , description, nom,reclamation_id);
           ReponseService ps = new ReponseService();
           ps.ajouter(r);
           FXMLLoader loader=new FXMLLoader(getClass().getResource("detailswindow2.fxml"));
          
        try {
            Parent root = loader.load();
            Detailswindow2Controller dwc2 = loader.getController();
            
            dwc2.setobjet(r.getObjet());
            dwc2.setdescription(r.getDescription());
            dwc2.setnom(r.getNom());
            dwc2.setreclamation_id(""+r.getReclamation_id());
            tfobjetr.getScene().setRoot(root) ;
          
             //tfnom.getScene().setRoot(root);
            
      
        } catch (IOException ex) {
             System.out.println(" Error: "+ ex.getMessage());
        }
    }
    
    }}
