package com.eshop.gui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.eshop.gui.reclamation.ShowAll;

public class Accueil extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    Label label;

    public Accueil() {
        super("Menu", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
    }

    private void addGUIs() {
        label = new Label("Accueil");
        label.setUIID("links");

        Container userContainer = new Container(new BorderLayout());
        userContainer.setUIID("userContainer");
        userContainer.add(BorderLayout.CENTER, label);

        Container menuContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        menuContainer.addAll(
                userContainer,
                makeReclamationsButton()
                
        );

        this.add(menuContainer);
    }
    
    private Button makeReclamationsButton() {
        Button button = new Button("Afficher tous les reclamation");
        button.setUIID("buttonMenu");
        button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new ShowAll(this).show());
        return button;
    }
    
}