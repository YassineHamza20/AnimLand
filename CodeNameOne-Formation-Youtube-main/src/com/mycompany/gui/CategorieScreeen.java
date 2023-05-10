/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.pidevv.MyApplication;
import com.mycomany.entities.CategorieV;
import com.mycompany.services.CatergoryService;


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

    Container categoryContainer = new Container(BoxLayout.y()); // create container to hold categories

    for (CategorieV item: es.getAll())
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

        categoryContainer.add(card); // add card container to category container
    }  

    add(categoryContainer); // add category container to form

      

      
      
    }
      public void showCategoryServiceCode() {
        CatergoryService categoryService = new CatergoryService();
        String categoryServiceCode = categoryService.toString(); // or categoryService.getAll().toString();
        System.out.println(categoryServiceCode); // or display the code in a text field or dialog
    }
}