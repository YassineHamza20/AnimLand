/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Reclamation;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.ReclamationService;
import static sun.management.GcInfoCompositeData.getId;
 
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
    private TextField tfobjet1;
    @FXML
    private TextField tfdescription1;
    @FXML
    private TextField tfnom1;
 @FXML
    private Hyperlink affichbarchart;
  @FXML
    private Hyperlink reponse;

    @FXML
    public TableView<Reclamation> tableview;
    @FXML
    private TableColumn<Reclamation, Integer> id;
    @FXML
    private TableColumn<Reclamation, String> objet;
    @FXML
    private TableColumn<Reclamation, String> description;
    @FXML
    private TableColumn<Reclamation, String> nom;
   
    private Button btnenvoyer;

    
    ObservableList<Reclamation> reclam = FXCollections.observableArrayList();
    int myIndex;
    int idindex;
    @FXML
    private Button supidbtn;
    @FXML
    private TextField supid;
   @FXML
    private TextField searchField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ReclamationService servicesProduit = new ReclamationService();
            List<Reclamation> reclamations = servicesProduit.afficherListe();
            ObservableList<Reclamation> observableProduits = FXCollections.observableArrayList(reclamations);
            tableview.setItems(observableProduits);
            
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                String keyword = newValue.toLowerCase();
                List<Reclamation> filteredList = reclamations.stream()
                        .filter(Reclamation -> Reclamation.getNom().toLowerCase().contains(keyword)
                                || Reclamation.getObjet().toLowerCase().contains(keyword))
                        .collect(Collectors.toList());
                tableview.setItems(FXCollections.observableArrayList(filteredList));
            });
            
            
            
            /*
            search.textProperty().addListener((observable, oldValue, newValue) -> {
            ReclamationService pdao = ReclamationService.getInstance();
            List<Reclamation> r = pdao.rechercher(newValue);
            ObservableList<Reclamation> reclamationsList = FXCollections.observableArrayList(r);
            tableview.setStyle("-fx-font-family: Arial; -fx-font-size: 14px; -fx-font-weight: bold;");
            tableview.setItems(reclamationsList);
            
            tableview.setCellFactory(param -> new ListCell<Reclamation>() {
            @Override
            protected void updateItem(Reclamation item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
            setText(null);
            } else {
            setText(item.toString()); // or any other method that returns a string representation of the Reclamation object
            setFont(Font.font("Arial", FontWeight.BOLD, 24));
            }
            }
            });
            
            
            });*/
            
            
            
            TableColumn<Reclamation, Integer> idColumn = new TableColumn<>("id");
            id.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
            
            TableColumn<Reclamation, String> nomColumn = new TableColumn<>("objet");
            objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
            
            TableColumn<Reclamation, String> des = new TableColumn<>("description");
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            
            TableColumn<Reclamation, String> dess = new TableColumn<>("nom");
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            
            
            
            ReclamationService ReclamationService = new ReclamationService();
            
            tableview.setRowFactory( tv -> {
                TableRow<Reclamation> myRow = new TableRow<>();
                myRow.setOnMouseClicked (event ->
                {
                    if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                    {
                        myIndex =  tableview.getSelectionModel().getSelectedIndex();
                        idindex = Integer.parseInt(String.valueOf(tableview.getItems().get(myIndex).getId()));
                        tfobjet.setText(tableview.getItems().get(myIndex).getObjet());
                        tfdescription.setText(tableview.getItems().get(myIndex).getDescription());
                        tfnom.setText(tableview.getItems().get(myIndex).getNom());
                    }
                });
                return myRow;
            });
            
            
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
        } catch (SQLException ex) {
            Logger.getLogger(DetailswindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    private void savereclamation(ActionEvent event) {
        String objet = tfobjet1.getText();
        String description = tfdescription1.getText();
        String nom = tfnom1.getText();
        
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
            tfobjet1.getScene().setRoot(root) ;
 
            
        } catch (IOException ex) {
             System.out.println(" Error: "+ ex.getMessage());
        }
    }}
       
       
       
       
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
    
    @FXML
    void ModifierReclamation(ActionEvent event) {
   String objet = tfobjet.getText();
    String description = tfdescription.getText();
      String nom = tfnom.getText();
    
Reclamation p = new Reclamation(objet, description, nom);
    ReclamationService Reclamation = new ReclamationService();
   
    int id = Integer.parseInt(String.valueOf(tableview.getItems().get(myIndex).getId()));
    Reclamation.modifier(p, id);
 
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
//alert.setTitle("Student Registationn");
 
alert.setHeaderText("Reclamation modifié avec Succès");
//alert.setContentText("Reclamation modifiée avec Succé");
 
alert.showAndWait();
     initialize(null, null);
     
      
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
    }}

@FXML
    void affichbarchart(ActionEvent event) {
try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chartpie.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    void reponse(ActionEvent event) {
try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("detailswindow2.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
  
    
 
    


