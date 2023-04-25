/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Reclamation;
import entites.Reponse;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ReclamationService;
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
    private Button btnenvoyerr;

    @FXML
    private TextField tfobjetr1;
    @FXML
    private TextField tfdescriptionr1;
    @FXML
    private TextField tfnomr1;
       @FXML
    private TextField tfrecidr1;
    
   
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
    int myIndex;
    int idindex;
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
      TableColumn<Reponse, Integer> idColumn = new TableColumn<>("id");
                idr.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
                
                TableColumn<Reclamation, String> nomColumn = new TableColumn<>("objet");
                objetr.setCellValueFactory(new PropertyValueFactory<>("objet"));
                
                 TableColumn<Reclamation, String> des = new TableColumn<>("description");
                descriptionr.setCellValueFactory(new PropertyValueFactory<>("description"));
                
                 TableColumn<Reclamation, String> dess = new TableColumn<>("nom");
                nomr.setCellValueFactory(new PropertyValueFactory<>("nom"));
                
                 
 
            ReponseService ReponseService = new ReponseService();
                 
                tableview.setRowFactory( tv -> {
                    TableRow<Reponse> myRow = new TableRow<>();
                    myRow.setOnMouseClicked (event ->
                    {
                        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                        {
                            myIndex =  tableview.getSelectionModel().getSelectedIndex();
                            idindex = Integer.parseInt(String.valueOf(tableview.getItems().get(myIndex).getId()));
                              tfobjetr.setText(tableview.getItems().get(myIndex).getObjet());
                             tfdescriptionr.setText(tableview.getItems().get(myIndex).getDescription());
                             tfnomr.setText(tableview.getItems().get(myIndex).getNom());
                        } 
                    });
                    return myRow;
                });
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
    private void savereponse(ActionEvent event) {
        String objet = tfobjetr1.getText();
        String description = tfdescriptionr1.getText();
        String nom = tfnomr1.getText();
          int reclamation_id = Integer.parseInt(tfrecidr1.getText());
          
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
            dwc2.setreclamation_id(" reclamation_id");
            tfobjetr.getScene().setRoot(root) ;
          
             //tfnom.getScene().setRoot(root);
            
      
        } catch (IOException ex) {
             System.out.println(" Error: "+ ex.getMessage());
        }
    }
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
    }
    
    @FXML
    void ModifierReponse(ActionEvent event) {
   String objet = tfobjetr.getText();
    String description = tfdescriptionr.getText();
      String nom = tfnomr.getText();
    
Reponse p = new Reponse(objet, description, nom);
    ReponseService Reponse = new ReponseService();
   
    int id = Integer.parseInt(String.valueOf(tableview.getItems().get(myIndex).getId()));
    Reponse.modifier(p, id);
 
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
//alert.setTitle("Student Registationn");
 
alert.setHeaderText("Reponse modifié avec Succès");
//alert.setContentText("Reponse modifiée avec Succé");
 
alert.showAndWait();
     initialize(null, null);
     
      
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    
    // Get the URL of the FXML file
    URL fxmlUrl = getClass().getResource("DetailsWindow2.fxml");
    
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
