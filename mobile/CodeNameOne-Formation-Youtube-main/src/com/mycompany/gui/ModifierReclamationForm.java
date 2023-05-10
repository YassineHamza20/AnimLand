/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Produit;
import com.mycompany.services.ServiceProduit;

/**
 *
 * @author Lenovo
 */
public class ModifierReclamationForm extends BaseForm {
    
    Form current;
    public ModifierReclamationForm(Resources res , Produit r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Produit");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField Nom = new TextField(r.getNom() , "Nom" , 20 , TextField.ANY);
//        TextField Prix = new TextField(r.getPrix() , "Prix" , 20 , TextField.ANY);
               TextField prix = new TextField(String.valueOf(r.getPrix()) , "prix" , 20 , TextField.ANY);
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
//        ComboBox etatCombo = new ComboBox();
//        
//        etatCombo.addItem("alimeentation");
//        
//        etatCombo.addItem("Hygiene");
//        
      
        
        
        
        
        Nom.setUIID("NewsTopLine");
        prix.setUIID("NewsTopLine");
//        etat.setUIID("NewsTopLine");
        
        Nom.setSingleLineTextArea(true);
        prix.setSingleLineTextArea(true);
//        etat.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNom(Nom.getText());
          float prixx = Float.parseFloat(prix.getText());
r.setPrix(prixx);
          
           
       
       
       //appel fonction modfier reclamation men service
       
       if(ServiceProduit.getInstance().modifierReclamation(r)) { // if true
           new ListReclamationForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListReclamationForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(Nom),
                createLineSeparator(),
                new FloatingHint(prix),
//                createLineSeparator(),
//                etatCombo,
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}
