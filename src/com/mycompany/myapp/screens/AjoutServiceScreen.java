/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.screens;

import com.codename1.ui.Button;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entity.ServiceV;
import com.mycompany.myapp.serviceV.ServiceService;
import com.codename1.ui.Form;
import com.mycompany.myapp.entity.CategorieV;
import com.mycompany.myapp.serviceV.CatergoryService;
import com.mycompany.myapp.screens.ServiceScreens;
import com.mycompany.myapp.helper.ServiceURL;

/**
 *
 * @author Mynet
 */
public class AjoutServiceScreen extends Form {
    private MyApplication app;
    ServiceService es=ServiceService.getInstance();
    private TextField serviceNameField;

    public AjoutServiceScreen(MyApplication main) {
        super("ADD SERVICE", BoxLayout.y());
        this.app = main;
        app.addDrawerMenu(this);

        Label label = new Label("SERVICE VETERINAIRE");
        add(label);

        serviceNameField = new TextField();
        add(serviceNameField);
        serviceNameField = new TextField();
        add(serviceNameField);
       serviceNameField = new TextField();
        add(serviceNameField);
        serviceNameField = new TextField();
        add(serviceNameField);
        serviceNameField = new TextField();
        add(serviceNameField);
       serviceNameField = new TextField();
        add(serviceNameField);
        
        
        Button addButton = new Button("Add");
        addButton.addActionListener(e -> addService());
        add(addButton);
    }
     private void addService() {
        String ServiceName = serviceNameField.getText();
       
        if (!ServiceName.isEmpty()) {
            ServiceV service = new ServiceV();
            es.addService(service);
            // Optionally, you can perform any additional actions here
            // after adding the category
            serviceNameField.clear();
        }
    }

   
}
