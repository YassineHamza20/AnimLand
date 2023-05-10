package com.mycomany.entities;

import java.util.Date;
import com.mycomany.utils.*;

public class Reclamation {
     private int id;
       private String objet,description;

    /*public Reclamation(int id, String email, String password) {
        this.id = id;
        
        this.email = email;
        this.password = password;
        
    }*/

    public Reclamation(){}
    public Reclamation(int id, String objet, String description) {
        this.id=id;
        this.objet = objet;
        this.description = description;
    }
     public Reclamation(String objet, String description) {
        this.objet = objet;
        this.description = description;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjet(){
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }
    
    public String getDescription() {
        return description;
    }
       public void setDescription(String description) {
        this.description = description;
    }
  
    @Override
    public String toString() {
        return "reclamation{" + "id=" + id + " objet=" + objet + ", description=" + description +  '}';
    }

    
    
    
    




    

   
    
    
    
}

    
    
    




    

   
    
    
    
