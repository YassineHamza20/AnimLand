package com.eshop.gui.reclamation;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.eshop.entities.Reclamation;
import com.eshop.services.ReclamationService;

public class Edit  extends Form {
    
    //Modif
      Form previous; 
    public Edit (Form previous, Reclamation r ) {
        
            super("Modifier reclamation", new BoxLayout(BoxLayout.Y_AXIS));           
            super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      
            TextField objet = new TextField(r.getObjet(), "objet");
            TextField description = new TextField(r.getDescription(), "description");          
            
        
         Button btnModifier = new Button("Edit");
         Button btnAnnuler = new Button("Annuler");
         btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form f2 = new Form(BoxLayout.y());
                Label lobjet=new Label("objet: "+objet.getText());
                Label ldescription=new Label("description: "+description.getText());
                
                f2.add(lobjet);
                f2.add(ldescription);
                                        
                f2.show();
                      
              r.setObjet(objet.getText());
              r.setDescription(description.getText()); 
              
             /* if(ReclamationService.getInstance().modifier(r)) { 
                new ShowAll(previous).show();
              } */
      
             try {
            if(ReclamationService.getInstance().modifier(r)) { 
                new ShowAll(previous).show();
            } else {
                Dialog.show("Error", "Unable to update reclamation", new Command("OK"));
            }
        } catch(Exception e) {
            Dialog.show("Error", "Unable to update reclamation: " + e.getMessage(), new Command("OK"));
        }     
             
            }
        });
        addAll(objet,description,btnAnnuler,btnModifier);
      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       
         btnAnnuler.addActionListener(e -> {
           new ShowAll(previous).show();
         });
    }
  
}
