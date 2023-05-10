/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.user;

import static DBCnx.MyConnection.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class SignInController implements Initializable {

    @FXML
    private Button loginbtn;
    @FXML
    private TextField emailtf;
    @FXML
    private PasswordField passwordtf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private boolean validateFields() {
        if (emailtf.getText().isEmpty()) {
            showAlert("Error", "Email field is required.");
            return false;
        } else {
            String emaile = emailtf.getText().trim();
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!emaile.matches(emailRegex)) {
                showAlert("Error", "Please enter a valid email address.");
                return false;
            }
        }

        if (passwordtf.getText().isEmpty()) {
            showAlert("Error", "Password is required.");
            return false;
        } 

    
        return true;
    }
        private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
        private ResultSet executeQuery(String query, boolean isDataManipulation) {
    Connection conn = MyConnection();
    Statement st;
    ResultSet rs = null;
    try {
        st = conn.createStatement();
        if (isDataManipulation) {
            st.executeUpdate(query);
        } else {
            rs = st.executeQuery(query);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return rs;
} 
@FXML
private void login(ActionEvent event) throws SQLException {
    String email = emailtf.getText();
    String password = passwordtf.getText();

    try {
        // Execute SELECT query to retrieve the user with the given email
        String query = "SELECT * FROM user WHERE email='" + email + "'";
        ResultSet rs = executeQuery(query, false);

        // If query returns a result, user with the given email exists
        if (rs.next()) {
            String storedHash = rs.getString("password");
            int blocked = rs.getInt("Blocked");
            if (blocked == 1) {
            // If user is blocked, show an alert indicating that the user is blocked
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Authentication Error");
            alert.setHeaderText(null);
            alert.setContentText("Votre compte est bloqué. Veuillez contacter un administrateur.");
            alert.showAndWait();
        } else if (BCrypt.checkpw(password, storedHash)) {
            // Close the current window
            Stage stage = (Stage) loginbtn.getScene().getWindow();
            stage.close();

            // Use BCrypt to check if entered password matches stored hash
         
                // Close the current window
              

                // Load the new FXML file and show it in a new window
                Parent root = FXMLLoader.load(getClass().getResource("../sidebar/Administration.fxml"));
                Scene scene = new Scene(root);
                Stage newStage = new Stage();
                newStage.setScene(scene);
                newStage.setWidth(900);
newStage.setHeight(700);
                newStage.show();
            } else {
                // If entered password does not match stored hash, show an alert indicating that authentication failed
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Authentication Error");
                alert.setHeaderText(null);
                alert.setContentText("Email ou mot de passe incorrect. Veuillez réessayer.");
                alert.showAndWait();
            }
        } else {
            // If no result is returned, show an alert indicating that the user with the given email does not exist
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Authentication Error");
            alert.setHeaderText(null);
            alert.setContentText("L'utilisateur avec l'email donné n'existe pas. Veuillez réessayer.");
            alert.showAndWait();
        }

    } catch (IOException ex) {
        ex.printStackTrace();
    }
}

@FXML
    private void mdp_oublie_button(MouseEvent event) {
        try {
            // Get the previous stage
            Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Load the new FXML file
            Parent root = FXMLLoader.load(getClass().getResource("SendMail.fxml"));
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);

            // Set the previous stage as the owner of the new stage
            newStage.initOwner(previousStage);

            // Show the new stage and hide the previous stage
            
            previousStage.hide();
            newStage.show();

        } catch (IOException ex) {
            // Handle the exception
        }
    }


}
