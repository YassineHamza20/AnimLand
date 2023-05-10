/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.user;


import com.edu.project.entities.CategorieAdoption;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.edu.project.service.AdoptionService;
import com.edu.project.service.CategorieAdoptionService;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AjouterCategorieController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfcat;
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterCategorie(ActionEvent event) {
        String categorie = tfcat.getText();
        int id = Integer.parseInt(tfid.getText());
        
        CategorieAdoption a = new CategorieAdoption(id, categorie);
        CategorieAdoptionService ps = new CategorieAdoptionService();
        ps.ajouteradop(a);
        FXMLLoader loader =new FXMLLoader(getClass().getResource("CategorieDetailsWindow.fxml"));
        try {
            Parent root = loader.load();
            CategorieDetailsWindowController dwc = loader.getController();
            dwc.setTexttype(a.getType());
            dwc.setTextid(""+a.getId());
            tfid.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("error:"+ex.getMessage());
        }
    }
    
}
