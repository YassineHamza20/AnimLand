/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import entities.SubscriptionEntities;
import Interfaces.SubscriptionInterface;
import services.SubscriptionServices;
import services.OfferServices;
import entities.OfferEntities;
import Interfaces.OfferInterface;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.time.temporal.TemporalQueries.localDate;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import Utils.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class SubscriptionController implements Initializable {

    @FXML
    private Label lbtype;
    @FXML
    private DatePicker start_date;
    @FXML
    private DatePicker end_date;
    @FXML
    private TextField is_active;
    @FXML
    private Button btnadd2;
    @FXML
    private TableView<?> tv2;
    @FXML
    private TableColumn<?, ?> idd1;
    @FXML
    private TableColumn<?, ?> offer_idd;
    @FXML
    private TableColumn<?, ?> start_datee;
    @FXML
    private TableColumn<?, ?> end_datee;
    @FXML
    private TableColumn<?, ?> is_activee;
    @FXML
    private Button deactivate;
    @FXML
    private Button activate;
    @FXML
    private ComboBox<String> offer_nami;

    /**
     * Initializes the controller class.
     */
    
     private OfferServices OfferServices;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        try {
            Connection cnx = MyDB.getInstance().getConnexion();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("select name from offer");

            while (rs.next()) {
                offer_nami.getItems().add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @FXML
    private void addNewSubscription(ActionEvent event) {
        Alert alert;
    try {
        if (is_active.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
        }else {
            
            SubscriptionEntities Oe = new SubscriptionEntities();
            Oe.setStart_date(start_date.getValue());
            Oe.setEnd_date(end_date.getValue());
            Oe.setIsActive(Integer.parseInt(is_active.getText()));
              
              String nomCat = offer_nami.getSelectionModel().getSelectedItem();
              System.out.println(nomCat);
              
                    OfferEntities catp = new OfferEntities();
                    OfferServices cps=new OfferServices();
                    catp=cps.getOfferByName(nomCat);
              
                    
                    
                     Oe.setNaame(catp);
                     Oe.setOffer_id(catp.getId());
                     System.out.println(catp);
                     
                     
                     
                     
                     
                                          

                
                    
            SubscriptionServices od = new SubscriptionServices();       
            od.createSubscription(Oe);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Added!");
            alert.showAndWait();
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } catch (NumberFormatException ex) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Invalid activation choice ");
        alert.showAndWait();
    }
    }

    @FXML
    private void isntactive(ActionEvent event) {
    }

    @FXML
    private void isactive(ActionEvent event) {
    }
    
}
