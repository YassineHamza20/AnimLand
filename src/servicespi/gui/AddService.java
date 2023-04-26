/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicespi.gui;

import java.text.SimpleDateFormat;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import servicespi.controller.ServiceController;
import servicespi.entities.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 *
 * @author Mynet
 */
public class AddService {
    
    ServiceController serviceController = new ServiceController();
    
    @FXML private Button AddingButton;
    @FXML private TextField nomAdd;
    @FXML private TextField prixAdd;
    @FXML private TextField dateAdd;
    @FXML private TextField descAdd;
    @FXML private TextField idAdd;
    
    @FXML public void initialize() {}
    
    @FXML private void asyncAddService() {
       
         Stage stage = (Stage) AddingButton.getScene().getWindow();
         stage.close();
        String nom = nomAdd.getText();
        String desc = descAdd.getText();
        String date = dateAdd.getText();
        if (nom.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "le champ nom est obligatoire!");
    return;
            
    }
        else if ( desc.isEmpty()) {
            JOptionPane.showMessageDialog(null, "le champ description est obligatoire!");
    return;
            
    }
     else if (date.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir votre Date!");
    return;
            
    }else {
         
      serviceController.addService(new Service(
                Double.parseDouble(prixAdd.getText()),
                nomAdd.getText(),
                descAdd.getText(),
                dateAdd.getText(),
                Integer.parseInt(idAdd.getText()),
                0));
     }
    } 

}
