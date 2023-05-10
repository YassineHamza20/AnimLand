/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.user;

import static DBCnx.MyConnection.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DefaultStringConverter;
import org.mindrot.jbcrypt.BCrypt;
/**
 * FXML Controller class
 *
 * @author pc
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    
    @FXML
    private ComboBox roletf;
    ObservableList<String> list1 = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    private Button signup;
    userController obj = new userController();
    @FXML
    private PasswordField passwordtf;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        list.add("[\"ROLE_USER\"]");
        list.add("[\"ROLE_ADMIN\"]");
        list1.add("Guest");
        list1.add("Admin");
        roletf.setItems(list1);
        
           
    }

    private boolean validateFields() {
        if (email.getText().isEmpty()) {
            showAlert("Error", "Email field is required.");
            return false;
        } else {
            String emaile = email.getText().trim();
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!emaile.matches(emailRegex)) {
                showAlert("Error", "Please enter a valid email address.");
                return false;
            }
        }

        if (lastName.getText().isEmpty()) {
            showAlert("Error", "Tel field is required.");
            return false;
        }
        if (passwordtf.getText().isEmpty()) {
            showAlert("Error", "Password is required.");
            return false;
        } else if (passwordtf.getText().length() < 8) {
            showAlert("Error", "Password must be at least 8 characters long.");
            return false;
        }

        if (username.getText().isEmpty()) {
            showAlert("Error", "respo field is required.");
            return false;
        }
        if (firstName.getText().isEmpty()) {
            showAlert("Error", "respo field is required.");
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

    @FXML
    private void signup(ActionEvent event) {
        
        if (event.getSource() == signup) {
            if (validateFields()) {
                insert();
                obj.showusers();
            }
        }
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



private void insert() {
    String emailtf = email.getText();
    String usernametf = username.getText();
    //cryptage b biblioteque exterene jbycrypte
    
    String pwd = BCrypt.hashpw(passwordtf.getText(), BCrypt.gensalt()); ;
    String first = firstName.getText();
    String last = lastName.getText();
    String role = list.get(list1.indexOf(roletf.getValue()));
    
    validateFields();
    
    // Check if email already exists
    String queryEmail = "SELECT * FROM `user` WHERE `email` = '" + emailtf + "'";
    ResultSet rs = executeQuery(queryEmail, false);
    try {
        if (rs.next()) {
            // Email already exists, show alert and return
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sign Up Failed");
            alert.setHeaderText(null);
            alert.setContentText("L'email existe déjà. Veuillez utiliser une autre adresse e-mail.");
            alert.showAndWait();
            return;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
    // Insert new user
    String queryInsert = "INSERT INTO `user`(`email`, `username`, `first_name`, `last_name`, `roles`, `password`,`Blocked`) VALUES('" + emailtf + "','" + usernametf + "','" + first + "','" + last + "','" + role + "','" + pwd + "',false)";
    executeQuery(queryInsert, true);
    
    // Show success message
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Sign Up Successful");
    alert.setHeaderText(null);
    alert.setContentText("Inscription réussie. Veuillez vous connecter.");
    alert.showAndWait();
}


}
