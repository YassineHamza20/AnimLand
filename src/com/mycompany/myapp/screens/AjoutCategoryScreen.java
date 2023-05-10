/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.screens;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.serviceV.CatergoryService;
import com.mycompany.myapp.entity.CategorieV;

public class AjoutCategoryScreen extends Form {
    private MyApplication app;
    CatergoryService es=CatergoryService.getInstance();
    private TextField categoryNameField;

    public AjoutCategoryScreen(MyApplication main) {
        super("ADD CATEGORY", BoxLayout.y());
        this.app = main;
        app.addDrawerMenu(this);

        Label label = new Label("CATEGORY");
        add(label);

        categoryNameField = new TextField();
        add(categoryNameField);

        Button addButton = new Button("Add");
        addButton.addActionListener(e -> addCategory());
        add(addButton);
    }
     private void addCategory() {
        String categoryName = categoryNameField.getText();
        if (!categoryName.isEmpty()) {
            CategorieV category = new CategorieV(categoryName);
            es.addCategory(category);
            // Optionally, you can perform any additional actions here
            // after adding the category
            categoryNameField.clear();
        }
    }

   
}


