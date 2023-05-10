package com.eshop.gui.reclamation;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.eshop.entities.*;
import com.eshop.services.*;
import com.eshop.services.ReclamationService;
import com.eshop.gui.reclamation.ShowAll;




public class Manage extends Form {
     Form previous; 
     //add  GUI
    public Manage (Form previous) {        
            super("Ajouter reclamation", new BoxLayout(BoxLayout.Y_AXIS));           
             super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      
           
            TextField objet = new TextField("", "objet");
            TextField description = new TextField("", "description");
            
            objet.setConstraint(TextField.ANY);
            description.setConstraint(TextField.ANY);
          
        Button btnValider = new Button("Ajouter ");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form f2 = new Form(BoxLayout.y());
                Label lobjet=new Label("objet: "+objet.getText());
                Label ldescription=new Label("description: "+description.getText());
             
                f2.add(lobjet);
                f2.add(ldescription);
                              
                f2.show();
                if ((objet.getText().length()==0)&&(description.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else{ 
                             Reclamation t = new Reclamation(objet.getText(),description.getText());
                            if( ReclamationService.getInstance().addTask(t))
                            {
                               Dialog.show("Success","reclamation ajoutee",new Command("OK"));
                               new ShowAll(previous).show();
                            }else
                                Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
              
                return;
    
            }
        });
        
        addAll(objet, description, btnValider);
//    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
    
    
}