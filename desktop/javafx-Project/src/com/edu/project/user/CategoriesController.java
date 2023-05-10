/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.user;

import com.edu.project.entities.CategorieP;
import com.edu.project.entities.Produit;
import com.edu.project.service.ServiceCategorie;
import com.edu.project.service.ServicesProduit;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author Sarra Kachouandi
 */
public class CategoriesController implements Initializable {
    
     int myIndex;
  int id;
 @FXML
    private TextField ftType;

    @FXML
    private Button btnAjout;
@FXML
    private ListView<CategorieP> CatListe;
    @FXML
    void Showlist(MouseEvent event) {

    }
    
      @FXML
    private Button btnSupr;

  @FXML
    private Button sortAscButton;

    
    @FXML
    private Button sortNom;


    
    @FXML
    void AddCategorie(ActionEvent event) {
 
    String type = ftType.getText();
    CategorieP c = new CategorieP(type);
    ServiceCategorie newProd = new ServiceCategorie();
    newProd.ajouterCategorie(c);
    
    // Check if any of the fields are empty
    if (type.isEmpty()) {
        // Show an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Remplir tout les champs!!");
        alert.showAndWait();
        return;
    }
 
    CatListe.getItems().add(c);
 
    // Show a success message
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("Categorie ajoutée avec Succès");
    alert.showAndWait();
 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try {
        ServiceCategorie serviceCategorie = new ServiceCategorie();
        List<CategorieP> categories = serviceCategorie.afficherListeCategorie();
        ObservableList<CategorieP> observableCategories = FXCollections.observableArrayList(categories);
        CatListe.setItems(observableCategories);
        
        CatListe.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends CategorieP> observable, CategorieP oldValue, CategorieP newValue) -> {
            if (newValue != null) {
                ftType.setText(newValue.getType());
            }
        });
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

 
    }
     @FXML
    void UpdateCategorie(ActionEvent event) {
 String type = ftType.getText();
    CategorieP c = new CategorieP(type);
    ServiceCategorie ServiceCategorie = new ServiceCategorie();
    int myIndex = CatListe.getSelectionModel().getSelectedIndex();
    int id = CatListe.getItems().get(myIndex).getId();
    ServiceCategorie.modifierCategorie(c, id);
    CatListe.getItems().set(myIndex, c);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("Categorie modifié avec Succès");
    alert.showAndWait();
    }
 @FXML
    void SupprimerCategorie(ActionEvent event) {
  // Get the selected item's id
    int id = CatListe.getSelectionModel().getSelectedItem().getId();

    // Remove the selected item from the ListView
    CatListe.getItems().remove(CatListe.getSelectionModel().getSelectedIndex());

    // Delete the category from the database
    ServiceCategorie newProd = new ServiceCategorie();
    newProd.supprimerCategorie(id);

    // Refresh the ListView
    try {
        ServiceCategorie serviceCategorie = new ServiceCategorie();
        List<CategorieP> categories = serviceCategorie.afficherListeCategorie();
        ObservableList<CategorieP> observableCategories = FXCollections.observableArrayList(categories);
        CatListe.setItems(observableCategories);
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    }
    
    


private boolean isNom = true;
private void toggleNomOrder() {
    isNom = !isNom;
    ObservableList<CategorieP> currentItems = CatListe.getItems();

    System.out.println("Sorting by Nom in " + (isNom ? "ascending" : "descending") + " order:");

    for (CategorieP p : currentItems) { // changed to CategorieP
        System.out.println(p.getType());
    }

    FXCollections.sort(currentItems, (p1, p2) -> isNom ? p1.getType().compareToIgnoreCase(p2.getType()) : p2.getType().compareToIgnoreCase(p1.getType()));

    for (CategorieP p : currentItems) { // changed to CategorieP

    }

    CatListe.setItems(currentItems);
}

@FXML
void SORTNOM(ActionEvent event) {
    toggleNomOrder();
}
}