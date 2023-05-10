/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.user;

import com.edu.project.service.GeneralServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class VerifTokenController implements Initializable {

    @FXML
    private TextField token;
    public static String mail_passe ;

    private VBox vbox;    
     
    private Parent fxml;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    


   
       
    

    @FXML
    private void verif_tok(MouseEvent event) {
    System.out.println(mail_passe);
        GeneralServices Gs = new GeneralServices();
        if( Gs.VerifyToken(mail_passe,Integer.parseInt(token.getText())))
        {
     try {
          Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            ChangePwdController CC = loader.getController();
            CC.mail_passing=mail_passe;     
              Parent root = FXMLLoader.load(getClass().getResource("ChangePwd.fxml"));
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
        else {
            System.out.println("erooooooooooooooor");
        }
    }}
    

