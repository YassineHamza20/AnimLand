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
import com.mycompany.myapp.entity.CategorieV;
import com.mycompany.myapp.serviceV.CatergoryService;


/**
 *
 * @author Mynet
 */
public class CategorieScreeen extends Form{
    private MyApplication app;
    CatergoryService es=CatergoryService.getInstance();
    public CategorieScreeen(MyApplication main){ 
       super("CATEGORY", BoxLayout.y());
        this.app = main;
        app.addDrawerMenu(this);
        Label label = new Label("CATEGORY");
        add(label);
        
      for (CategorieV item: es.getAllCategories())
      {
            Container card = new Container(BoxLayout.y());
            card.getAllStyles().setMarginBottom(10);
            card.setUIID("card");

            // event info
            card.add(new Label(item.getId()+ ""));
            card.add(new Label(item.getType()));

            // delete button
            Button deleteButton = new Button("Delete");
            deleteButton.addActionListener(e -> {
                es.deleteCategory(item.getId());
                new CategorieScreeen(app).show();
            });
            card.add(deleteButton);

            add(card);
            //modifier button
            
            Button modifyButton = new Button("Edit");
            modifyButton.addActionListener(e -> {
            es.editCategory(item.getType());
            new CategorieScreeen(app).show();
            });
            card.add(modifyButton);

            
            
        }  
    }
      
}