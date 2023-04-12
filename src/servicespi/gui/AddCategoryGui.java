/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicespi.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import servicespi.controller.CategoryController;
import servicespi.entities.Category;

/**
 *
 * @author Mynet
 */
public class AddCategoryGui {
    
    CategoryController categoryController = new CategoryController();
    
    @FXML private TextField category;
    @FXML private Button ajouter;
    
    @FXML public void initialize() {}
    
    @FXML private void addCategory() {
        categoryController.addCategory(new Category(category.getText()));
        Stage stage = (Stage) ajouter.getScene().getWindow();
        stage.close();
    } 
}
