/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.user;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ChangePwdController implements Initializable {

    @FXML
    private PasswordField nv_mdp1;
    @FXML
    private PasswordField nv_mdp2;
    @FXML
    private Button sign_in_btn;
      public static String mail_passing ;
    private VBox vbox;    
    private Parent fxml;
    @FXML
    private TextField notif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

  

    @FXML
    private void new_mdp(MouseEvent event) {
        if(nv_mdp1.getText().toString().equals(nv_mdp2.getText().toString()))
        {
            userController cs  = new  userController();
            cs.update_mdp(nv_mdp1.getText().toString(),mail_passing);
                  try {
                       Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
                Scene scene = new Scene(root);
                Stage newStage = new Stage();
                newStage.setScene(scene);
                 // Set the previous stage as the owner of the new stage
            newStage.initOwner(previousStage);

            // Show the new stage and hide the previous stage
            
            previousStage.hide();
                newStage.show();
                      System.out.println("cbn yekhdem");
            } catch (IOException ex) {

            }
        }
        else 
        {
            System.out.println(nv_mdp1.getText().toString());    
            System.out.println(nv_mdp2.getText().toString());
            notif.setText("les 2 mots de passe ne sont pas identiques");
        }
    }
    }

  


    
