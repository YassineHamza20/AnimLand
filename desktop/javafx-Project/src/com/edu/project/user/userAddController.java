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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class userAddController implements Initializable {

    @FXML
    private Button btnajouter;

    userController obj = new userController();
    @FXML
    private TextField emailtf;
    @FXML
    private TextField lasttf;
    @FXML
    private TextField pwdtf;
    @FXML
    private TextField usernametf;
    @FXML
    private TextField firsttf;
    @FXML
    private ComboBox roletf;
    ObservableList<String> list1 = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list.add("[\"ROLE_ADMIN\"]");
        list.add("[\"ROLE_USER\"]");
        list1.add("Admin");
        list1.add("User");

        roletf.setItems(list1);
    }

    private boolean validateFields() {
        if (emailtf.getText().isEmpty()) {
            showAlert("Error", "Email field is required.");
            return false;
        } else {
            String email = emailtf.getText().trim();
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!email.matches(emailRegex)) {
                showAlert("Error", "Please enter a valid email address.");
                return false;
            }
        }

        if (lasttf.getText().isEmpty()) {
            showAlert("Error", "Tel field is required.");
            return false;
        }
        if (pwdtf.getText().isEmpty()) {
            showAlert("Error", "Password is required.");
            return false;
        } else if (pwdtf.getText().length() < 8) {
            showAlert("Error", "Password must be at least 8 characters long.");
            return false;
        }

        if (usernametf.getText().isEmpty()) {
            showAlert("Error", "respo field is required.");
            return false;
        }
        if (firsttf.getText().isEmpty()) {
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
    private void OnCreate(ActionEvent event) {
        if (event.getSource() == btnajouter) {
            if (validateFields()) {
                insert();
                obj.showusers();
            }
        }

    }

    private void executeQuery(String query) {
        Connection conn = MyConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    private void insert() {
        String email = emailtf.getText();
        String username = usernametf.getText();
        String pwd = pwdtf.getText();
        String first = firsttf.getText();
        String last = lasttf.getText();
        String role = list.get(list1.indexOf(roletf.getValue()));
        validateFields();
        String query = "INSERT INTO `user`(`email`, `username`, `first_name`, `last_name`, `roles`, `password`) VALUES('" + email + "','" + username + "','" + first + "','" + last + "','" + role + "','" + pwd + "')";
        executeQuery(query);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("user ");
        alert.setHeaderText(null);
        alert.setContentText("added  succesfuly");
        alert.showAndWait();
    }

}
