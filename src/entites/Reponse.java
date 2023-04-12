/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author Yassine
 */
public class Reponse {
    
    private int id;
    private String objet,description,nom;
 private int reclamation_id;
 
    public Reponse() {
    }

    public Reponse(String objet, String description,String nom) {
         this.objet = objet;
           this.description = description;
        this.nom = nom;
   
    }

    public Reponse(int id, String objet, String description,String nom) {
        this.id = id;
        this.objet = objet;
         this.description = description;
        this.nom = nom;
      
    }

    public Reponse(int id, String objet, String description, String nom, int reclamation_id) {
        this.id = id;
        this.objet = objet;
        this.description = description;
        this.nom = nom;
        this.reclamation_id = reclamation_id;
    }

    public Reponse(String objet, String description, String nom, int reclamation_id) {
        this.objet = objet;
        this.description = description;
        this.nom = nom;
        this.reclamation_id = reclamation_id;
    }

     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }


   public String getDescription() {
        return description;
    }

   
    public int getReclamation_id() {
        return reclamation_id;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", objet=" + objet + ", description=" + description + ", nom=" + nom + ", reclamation_id=" + reclamation_id + '}';
    }

    public void setReclamation_id(int reclamation_id) {
        this.reclamation_id = reclamation_id;
    }
 
    
 
}
