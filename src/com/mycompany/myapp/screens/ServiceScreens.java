/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.screens;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entity.ServiceV;
import com.mycompany.myapp.serviceV.ServiceService;

/**
 *
 * @author Mynet
 */
public class ServiceScreens extends Form {
    
  private MyApplication app;
    ServiceService es=ServiceService.getInstance();
    public ServiceScreens(MyApplication main){ 
       super("SERVICE", BoxLayout.y());
        this.app = main;
        app.addDrawerMenu(this);
        Label label = new Label("SERVICE");
        add(label); 
        
        
    for (ServiceV item: es.getAllServices())
      {
            Container card = new Container(BoxLayout.y());
            card.getAllStyles().setMarginBottom(10);
            card.setUIID("card");

            // event info
         card.add(new Label(String.valueOf(item.getId())));
         card.add(new Label(String.valueOf(item.getPrix())));
         card.add(new Label(item.getNom()));
         card.add(new Label(item.getDescription()));
         card.add(new Label(item.getDate()));

            
            
            // delete button
            Button deleteButton = new Button("Delete");
            deleteButton.addActionListener(e -> {
                es.deleteService(item.getId());
                new ServiceScreens(app).show();
            });
            card.add(deleteButton);

            add(card);
            //modifier button
        

            
            
        }  
    }
      
}
    
    

