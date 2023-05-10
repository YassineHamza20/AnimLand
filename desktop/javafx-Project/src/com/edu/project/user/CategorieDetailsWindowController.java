/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.user;

import com.edu.project.entities.CategorieAdoption;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.edu.project.service.CategorieAdoptionService;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CategorieDetailsWindowController implements Initializable {

    @FXML
    private TableView<CategorieAdoption> tableview;
    @FXML
    private TableColumn<CategorieAdoption, Integer> idc;
    @FXML
    private TableColumn<CategorieAdoption, String> catc;
    
    ObservableList<CategorieAdoption> adop = FXCollections.observableArrayList();
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfcat;
    @FXML
    private Button btnsup;
    @FXML
    private TextField tfidsup;

    /**
     * Initializes the controller class.
     */
    public void setTexttype(String message){
        this.tfcat.setText(message);
    }
    public void setTextid(String message){
        this.tfid.setText(message);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // TODO
        CategorieAdoptionService rc = new CategorieAdoptionService();
        List<CategorieAdoption> listR = null;
        listR = rc.ChercherAdoption();
    
        if (!listR.isEmpty()) {
            for (int i = 0; i < listR.size(); i++) {
                adop.add(listR.get(i));
            }
        }
        idc.setCellValueFactory(new PropertyValueFactory<CategorieAdoption, Integer>("id"));
        catc.setCellValueFactory(new PropertyValueFactory<CategorieAdoption, String>("type"));
        tableview.setItems(adop);
    }    

    @FXML
    private void SupprimerCategorie(ActionEvent event) {
        int idsup = Integer.parseInt(tfidsup.getText());
        CategorieAdoptionService rc = new CategorieAdoptionService();
        rc.supprimer(idsup);   
        // Get a reference to the current window
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    
    // Get the URL of the FXML file
    URL fxmlUrl = getClass().getResource("CategorieDetailsWindow.fxml");
    
    // Create a new FXMLLoader
    FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
    
    try {
        // Load the FXML file and create a new scene graph
        Parent root = fxmlLoader.load();
        
        // Create a new scene with the root node
        Scene scene = new Scene(root);
        
        // Set the scene of the stage
        stage.setScene(scene);
        
        // Show the stage
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }
    
}
