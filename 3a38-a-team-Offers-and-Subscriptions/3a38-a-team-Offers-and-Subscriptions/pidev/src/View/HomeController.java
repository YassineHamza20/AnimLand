/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class HomeController implements Initializable {

    @FXML
    private Button btnproduits;
    @FXML
    private Button btncat;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
 @FXML
    private Button btndetail;
    
    @FXML
    private TableView<?> tvproduit;
    @FXML
    private TableColumn<?, ?> colid;
    @FXML
    private TableColumn<?, ?> coltype;
    @FXML
    private Label lbtype;
    @FXML
    private TextField tfnom;
    @FXML
    private Button btnadd;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void showProduits(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/View/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showCategorieProduit(ActionEvent event) throws IOException {
             Parent root = FXMLLoader.load(getClass().getResource("CategorieProduit.fxml"));
        Scene scene = new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showDetailCategorie(ActionEvent event) {
    }

    @FXML
    private void addNewCategory(ActionEvent event) {
    }
    
}
