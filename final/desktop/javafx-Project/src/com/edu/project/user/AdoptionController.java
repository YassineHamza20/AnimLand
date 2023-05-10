/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.user;
//
//import static changemakers.utils.SMS.ACCOUNT_SID;
//import static changemakers.utils.SMS.AUTH_TOKEN;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.edu.project.entities.Adoption;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.edu.project.service.AdoptionService;
import static com.twilio.example.Example.ACCOUNT_SID;
import static com.twilio.example.Example.AUTH_TOKEN;
import javafx.scene.control.Hyperlink;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AdoptionController implements Initializable {
        @FXML
    private Button switchButton;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfProprietaire;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfRace;
    @FXML
    private TextField tfSexe;
    @FXML
    private TextField tfCategorie;
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SaveAdoption(ActionEvent event) {
        String nom = tfNom.getText();
        String proprietaire = tfProprietaire.getText();
        String description = tfDescription.getText();
        String sexe = tfSexe.getText();
        String race = tfRace.getText();
        String age = tfAge.getText();
        Integer c_id = Integer.parseInt(tfCategorie.getText());
        if (nom.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "veuillez remplir le champ nom");
            alert.showAndWait();
        } else if (proprietaire.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "veulliez remplir le champ proprietaire");
            alert.showAndWait();
        } else if ((race.isEmpty())) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "veuillez remplir le champ race");
            alert.showAndWait();
        } else if ((description.isEmpty()) || (description.length() <= 3)) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "La description ne peut être vide et doit plus de trois lettres et des chiffres.");
            alert.showAndWait();
         } else if ((sexe.isEmpty())) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "veuillez remplir le champ sexe");
            alert.showAndWait();
        }else if ((age.isEmpty())) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "veuillez remplir le champ age");
            alert.showAndWait();
        }else if ((c_id == 0)) {
            // afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(Alert.AlertType.ERROR, "veuillez remplir le champ cathegorie ");
            alert.showAndWait();
        }else{
        Adoption a = new Adoption(nom, proprietaire, age, description, sexe, race,c_id);
        AdoptionService ps = new AdoptionService();
        ps.ajouteradop(a);
//           Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        com.twilio.rest.api.v2010.account.Message message;
//        message = com.twilio.rest.api.v2010.account.Message
//                .creator(
//                        new PhoneNumber("+21693149809"),
//                        new PhoneNumber("+16318886632"),
//                        "Votre adoption à été ajouté"
//                )
//                .create();

//        System.out.println(message.getSid());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsWindow_1.fxml"));
        try {
            Parent root = loader.load();
            DetailsWindowController_1 dwc = loader.getController();
            dwc.setTextnom(a.getNom());
            dwc.setTextproprietaire(a.getProprietaire());
            dwc.setTextdescription(a.getDescription());
            dwc.setTextage(a.getAge());
            dwc.setTextsexe(a.getSexe());
            dwc.setTextrace(a.getRace());
            dwc.setTextcategorie(""+a.getC_id());
            tfNom.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("error:"+ex.getMessage());
        }
        }
    }
@FXML
public void handleSwitch() throws IOException {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsWindow_1.fxml"));
        Parent root = loader.load();

        DetailsWindowController_1 controller = loader.getController(); // retrieve the controller instance
        // call any methods on the controller instance if needed

        Scene scene = new Scene(root);
        Stage stage = (Stage) switchButton.getScene().getWindow();
        stage.setScene(scene);
    } catch (IOException ex) {
        System.out.println("error:"+ex.getMessage());
    }
}

    @FXML
    private Hyperlink categorieADOP;

    @FXML
    void OnclickAdop(ActionEvent event) {
try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCategorie.fxml"));
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
