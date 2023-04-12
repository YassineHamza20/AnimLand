/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Reclamation;
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
import services.ReclamationService;
 
/**
 * FXML Controller class
 *
 * @author sinoh
 */
public class DetailswindowController implements Initializable {
 
    @FXML
    private TextField tfobjet;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfnom;

    @FXML
    private TableView<Reclamation> tableview;
    @FXML
    private TableColumn<Reclamation, Integer> id;
    @FXML
    private TableColumn<Reclamation, String> objet;
    @FXML
    private TableColumn<Reclamation, String> description;
    @FXML
    private TableColumn<Reclamation, String> nom;
 
    ObservableList<Reclamation> reclam = FXCollections.observableArrayList();
    
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
        
        // Récupération des réclamations
        ReclamationService rc = new ReclamationService();
        List<Reclamation> listR = null;
        listR = rc.ChercherReclamation();

        if (!listR.isEmpty()) {
            for (int i = 0; i < listR.size(); i++) {
                reclam.add(listR.get(i));
            }
    
        }

        //while
        id.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id"));
        objet.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("objet"));
        description.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));
        nom.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("nom"));
        tableview.setItems(reclam);
        // ListR = ReclamationCrud
     
        
    }    
    
    public void setobjet(String message) {
        this.tfobjet.setText(message);
    }

    public void setdescription(String message) {
        this.tfdescription.setText(message);
    }
 
       public void setnom(String message) {
        this.tfnom.setText(message);
        
    }
        
       @FXML
    private void  delete(ActionEvent event) {
        int idsup = Integer.parseInt(supid.getText());
        ReclamationService rc = new ReclamationService();
        rc.supprimer(idsup);   
        // Get a reference to the current window
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    
    // Get the URL of the FXML file
    URL fxmlUrl = getClass().getResource("DetailsWindow.fxml");
    
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
