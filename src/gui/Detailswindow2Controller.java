/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Reponse;
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
import services.ReponseService;
 
/**
 * FXML Controller class
 *
 * @author sinoh
 */
public class Detailswindow2Controller implements Initializable {

    @FXML
    private TextField tfobjetr;
    @FXML
    private TextField tfdescriptionr;
    @FXML
    private TextField tfnomr;
    @FXML
    private TextField tfrecidr;
    @FXML
    private TableView<Reponse> tableview;
      
    @FXML
    private TableColumn<Reponse, Integer> idr;
    @FXML
    private TableColumn<Reponse, String> objetr;
    @FXML
    private TableColumn<Reponse, String> descriptionr;
    @FXML
    private TableColumn<Reponse, String> nomr;
    @FXML
    private TableColumn<Reponse, Integer> rec_idr;
    
    ObservableList<Reponse> repo = FXCollections.observableArrayList();
    
    @FXML
    private Button supidbtn;
    @FXML
    private TextField supid;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        // Récupération des Reponses
        ReponseService rc = new ReponseService();
        List<Reponse> listR = null;
        listR = rc.ChercherReponse();

        if (!listR.isEmpty()) {
            for (int i = 0; i < listR.size(); i++) {
                repo.add(listR.get(i));
            }
    
        }

        //while
       
        idr.setCellValueFactory(new PropertyValueFactory<Reponse, Integer>("id"));
        objetr.setCellValueFactory(new PropertyValueFactory<Reponse, String>("objet"));
        descriptionr.setCellValueFactory(new PropertyValueFactory<Reponse, String>("description"));
        nomr.setCellValueFactory(new PropertyValueFactory<Reponse, String>("nom"));
          rec_idr.setCellValueFactory(new PropertyValueFactory<Reponse, Integer>("reclamation_id"));
        tableview.setItems(repo);
        // ListR = ReclamationCrud
    
        
    }    
    
    public void setobjet(String message) {
        this.tfobjetr.setText(message);
    }

    public void setdescription(String message) {
        this.tfdescriptionr.setText(message);
    }
 
       public void setnom(String message) {
        this.tfnomr.setText(message);
        
    }
         public void setreclamation_id(String message) {
        this.rec_idr.setText(message);
    }
        
       @FXML
    private void  delete(ActionEvent event) {
        int idsup = Integer.parseInt(supid.getText());
        ReponseService rc = new ReponseService();
        rc.supprimer(idsup);   
        // Get a reference to the current window
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    
    // Get the URL of the FXML file
    URL fxmlUrl = getClass().getResource("detailswindow2.fxml");
    
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
  
}}
