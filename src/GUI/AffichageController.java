/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Produit;
import Services.ServicesProduit;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sarra Kachouandi
 */
public class AffichageController implements Initializable {

    @FXML
    private Label prixlab;
    @FXML
    private VBox labelsContainer;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Hyperlink aff;
    @FXML
    private VBox rt;

    @FXML
    private Pane pane;

    @FXML
    private HBox root;
    @FXML
    private TableColumn<Produit, String> Photocolumn;

    @FXML
    private TableColumn<?, ?> categoriecolumn;

    
    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceProduit = new ServicesProduit();

    }
    private ServicesProduit serviceProduit;

//    @FXML
//    void aff(ActionEvent event) {
//        try {
//            List<Produit> produits = serviceProduit.getAllNoms();
//            System.out.println("Number of products: " + produits.size());
//
//            Node node = pane.getChildren().get(0);
//            if (node instanceof HBox) {
//                HBox hbox = (HBox) node;
//                // use hbox here
//            } else {
//                // handle the case where the node is not an HBox
//            }
//
//            for (Produit p : produits) {
//                Label nomLabel = new Label(p.getNom()); // create a label for the product name
//                Label prixlab = new Label(Float.toString(p.getPrix())); // create a label for the product price
//                nomLabel.setStyle("-fx-padding: 10px;"); // add some padding to the label
//                prixlab.setStyle("-fx-padding: 10px;"); // add some padding to the label
//                HBox productBox = new HBox(nomLabel, prixlab); // create a new HBox container for the product labels
//                productBox.setAlignment(Pos.CENTER); // center the HBox
//                root.getChildren().add(productBox); // add the HBox to the root container
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @FXML
void aff(ActionEvent event) {
    try {
        List<Produit> produits = serviceProduit.getAllNoms();
        System.out.println("Number of products: " + produits.size());

        for (Produit p : produits) {
            Label nomLabel = new Label(p.getNom()); // create a label for the product name
            Label prixlab = new Label(Float.toString(p.getPrix())); // create a label for the product price
            byte[] imageBytes;
            String photoPath = p.getPhoto(); // get the photo path from the produit object
            if (photoPath.startsWith("file:")) {
                // if the photo path starts with "file:", use the file path directly
                File file = new File(photoPath);
                Image image = new Image(file.toURI().toString());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(100); // set the width of the image view
                imageView.setFitHeight(100); // set the height of the image view
                imageView.setPreserveRatio(true);
                HBox productBox = new HBox(nomLabel, prixlab, imageView); // create a new HBox container for the product labels and image
                productBox.setAlignment(Pos.CENTER); // center the HBox
                root.getChildren().add(productBox); // add the HBox to the root container
            } else {
                // if the photo path does not start with "file:", assume it is a relative path and prepend it with "file:"
                File file = new File("file:" + photoPath);
                Image image = new Image(file.toURI().toString());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(100); // set the width of the image view
                imageView.setFitHeight(100); // set the height of the image view
                imageView.setPreserveRatio(true);
                HBox productBox = new HBox(nomLabel, prixlab, imageView); // create a new HBox container for the product labels and image
                productBox.setAlignment(Pos.CENTER); // center the HBox
                root.getChildren().add(productBox); // add the HBox to the root container
            }
            nomLabel.setStyle("-fx-padding: 10px;"); // add some padding to the label
            prixlab.setStyle("-fx-padding: 10px;"); // add some padding to the label
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }

          
               
               
            }
          

}
