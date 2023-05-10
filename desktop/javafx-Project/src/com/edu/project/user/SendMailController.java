/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.user;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.edu.project.service.GeneralServices;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class SendMailController implements Initializable {

    @FXML
    private TextField email_user;
    private VBox vbox;
    private Parent fxml;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    



    @FXML
    private void send_code(MouseEvent event) {
         GeneralServices GS = new GeneralServices();
       String mail=email_user.getText();
        try {
            Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            GS.sendMail(email_user.getText(), GS.Token_Mdp_Oublie(email_user.getText().toString()));
            
            FXMLLoader loader = new FXMLLoader();
            VerifTokenController verif_tok_con = loader.getController();
            verif_tok_con.mail_passe=mail;
            Parent root = FXMLLoader.load(getClass().getResource("VerifToken.fxml"));
                Scene scene = new Scene(root);
                Stage newStage = new Stage();
                newStage.setScene(scene);
                // Set the previous stage as the owner of the new stage
            newStage.initOwner(previousStage);

            // Show the new stage and hide the previous stage
            
            previousStage.hide();
            newStage.show();
          
        } catch (Exception ex) {
            Logger.getLogger(SendMailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
