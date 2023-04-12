/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Produit;
import Services.ServicesProduit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
/**
 *
 * @author Sarra Kachouandi
 */
public class FXMLDocumentController implements Initializable {
  int myIndex;
  int id;
    @FXML
    private Button btAjout;

    @FXML
    private TextField ftnom;
 @FXML
    private TableView<Produit> table;

    @FXML
    private TextField ftphoto;
 @FXML
    private TextField categorie;
    @FXML
    private Label label;
    @FXML
    private Button btnAJOUTER;
@FXML
    private TableColumn<Produit, Integer> IDcolumn;
  @FXML
    private TextField ftprix;

    @FXML
    private TableColumn<Produit, String> nomcolumn;

    @FXML
    private TableColumn<Produit, String> Photocolumn;

    @FXML
    private TableColumn<Produit, Integer>  categoriecolumn;

    @FXML
    private TableColumn<Produit, Float> Prixcolumn;
  @FXML
    private Button btnSuppr;

    @FXML
    private Button btnModifier;
     @FXML
    private ImageView ImagePreview;

    @FXML
    private Button onBtnImageClicked;
private String photo;
 @FXML 
    private Label labelImage;
    //MODIFIER PRODUit
 @FXML
    void ModifierProduit(ActionEvent event) {
   String nom = ftnom.getText();
    String photo = ftphoto.getText();
    float prixFP = Float.parseFloat(ftprix.getText());
    int categoriePId = Integer.parseInt(categorie.getText());
    Produit p = new Produit(nom, photo, prixFP , categoriePId);
    ServicesProduit servicesProduit = new ServicesProduit();

    int id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
    servicesProduit.modifier(p, id);
    }
    
    
  /// AJOUT PRODUIT 
    @FXML
    void AddProduit(ActionEvent event) {
   String photot = photo;
    System.out.println("AddProduit method called.");
    String nom = ftnom.getText();
//    String photo = ftphoto.getText();
    String prixFP = ftprix.getText();
    String categorieId = categorie.getText();
    
    // Check if any of the fields are empty
    if (nom.isEmpty() || prixFP.isEmpty() || categorieId.isEmpty()) {
        // Show an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please fill in all fields.");
        alert.showAndWait();
        return;
    }

    // Check if the CategorieID field is a valid integer
    int categoriePId;
    try {
        categoriePId = Integer.parseInt(categorieId);
    } catch (NumberFormatException e) {
        // Show an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Invalid CategoriePId.");
        alert.showAndWait();
        return;
    }

    // Check if the Prix field is a valid integer
    int Prix;
    try {
        Prix = Integer.parseInt(prixFP);
    } catch (NumberFormatException e) {
        // Show an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Invalid Prix.");
        alert.showAndWait();
        return;
    }

   Produit p = new Produit(nom, categoriePId, Prix);
ServicesProduit newProd = new ServicesProduit();
newProd.ajouter(p, photot);
}
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        try {
            TableColumn<Produit, Integer> idColumn = new TableColumn<>("ID");
            IDcolumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
            TableColumn<Produit, String> nomColumn = new TableColumn<>("Nom");
            nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
            
            TableColumn<Produit, String> photoColumn = new TableColumn<>("Photo");
            Photocolumn.setCellValueFactory(new PropertyValueFactory<>("photo"));
            
            TableColumn<Produit, Float> prixColumn = new TableColumn<>("Prix");
            Prixcolumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
            
            TableColumn<Produit, Integer> CategoriePId = new TableColumn<>("categorie_p_id");
            categoriecolumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getCategoriePId()));
            
            
              table.setRowFactory( tv -> {
     TableRow<Produit> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
            myIndex =  table.getSelectionModel().getSelectedIndex();
           id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
           ftnom.setText(table.getItems().get(myIndex).getNom());
           ftphoto.setText(table.getItems().get(myIndex).getPhoto());
          ftprix.setText(Float.toString(table.getItems().get(myIndex).getPrix()));
            categorie.setText(Integer.toString(table.getItems().get(myIndex).getCategoriePId()));
                         
                        
        }                
        
     });
        return myRow;
                   });
           
            
            // Retrieve the data from the database and add it to the table
            ServicesProduit servicesProduit = new ServicesProduit();
            List<Produit> produits = servicesProduit.afficherListe();
            ObservableList<Produit> observableProduits = FXCollections.observableArrayList(produits);
            table.setItems(observableProduits);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    @FXML
    void SupprimerProduit(ActionEvent event) {

        ServicesProduit newProd = new ServicesProduit();

        newProd.supprimer(id);
    }
    
      @FXML
    void onBtnImageClicked(ActionEvent event)  {
    
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg, *.png)", "*.jpg", "*.png");
    fileChooser.getExtensionFilters().add(extFilter);
    File file = fileChooser.showOpenDialog(null); // Show file chooser dialog

    if (file != null) {
        try {
            // Load image from selected file
            FileInputStream fileInputStream = new FileInputStream(file);
            Image photot = new Image(fileInputStream);
            ImagePreview.setImage(photot);

            photo = file.toURI().toString(); // Update the photo variable with the file path
            labelImage.setText(file.getName()); // Update the label with the file name
        } catch (IOException e) {
        }
    }
    

}

//@FXML
//private void onBtnUploadClicked(ActionEvent event) throws IOException {
//    // Call uploadImage method with photo
//    uploadImage(photo);
//}
//
//private void uploadImage(String photo) throws IOException {
//     File file = new File(photo);
//       byte[] imageData = Files.readAllBytes(file.toPath());
//}

}
