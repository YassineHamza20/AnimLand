/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Sarra Kachouandi
 */
public class Produit {
    
    private int id;
    private String nom;
    private String photo;
    private float prix;

    public Produit() {
    }
    
    

    public Produit(int id, String nom, String photo, float prix) {
        this.id = id;
        this.nom = nom;
        this.photo = photo;
        this.prix = prix;
    }

    public Produit(String nom, String photo, float prix) {
        this.nom = nom;
        this.photo = photo;
        this.prix = prix;
    }

    public Produit(String toString, String toString0, String format, int i, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + '}';
    }
    
    
    
    
    
}
