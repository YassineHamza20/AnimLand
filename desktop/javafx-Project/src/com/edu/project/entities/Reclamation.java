/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.entities;

/**
 *
 * @author Yassine
 */
public class Reclamation {
    
    private int id;
    private String objet,description,nom;

    public Reclamation() {
    }

    public Reclamation(String objet, String description,String nom) {
         this.objet = objet;
           this.description = description;
        this.nom = nom;
       
    }

    public Reclamation(int id, String objet, String description,String nom) {
        this.id = id;
        this.objet = objet;
         this.description = description;
        this.nom = nom;
        
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

    public void setDescription(String description) {
        this.description = description;
    }
 
    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", nom=" + nom + ", objet=" + objet +   " ,description= " +description+ '}';
    }
 
}
