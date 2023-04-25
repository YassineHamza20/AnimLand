/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.util.Base64;

/**
 *
 * @author Sarra Kachouandi
 */
public class Produit{
    private int id;
    private String nom,photo;
    
      private float Prix;
    private int categoriePId;

    public Produit() {
    }

    public Produit(String nom, String photo, float Prix, int categoriePId) {
        this.nom = nom;
        this.photo = photo;
        this.Prix = Prix;
        this.categoriePId = categoriePId;
    }

    public Produit(int id, String nom, String photo, float Prix, int categoriePId) {
        this.id = id;
        this.nom = nom;
        this.photo = photo;
        this.Prix = Prix;
        this.categoriePId = categoriePId;
    }

    public Produit(String nom, String photo) {
        this.nom = nom;
        this.photo = photo;
    }

    public Produit(String nom, String photo, int categoriePId) {
                this.nom = nom;
                     this.photo = photo;
                            this.categoriePId = categoriePId;
    }

    public Produit(String nom, int categoriePId, float Prix) {
        this.nom = nom;
        this.categoriePId = categoriePId;
             this.Prix = Prix;
        
    }

    public Produit(String nom) {
     this.nom = nom;
    }

    public Produit(String nom, float prix) {
        this.nom = nom;
        this.Prix = Prix;
    }

  public Produit(String nom, float prix, byte[] photo) {
    this.nom = nom;
    this.Prix = prix;
    this.photo = Base64.getEncoder().encodeToString(photo);
}
    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPhoto() {
        return photo;
    }

    public float getPrix() {
        return Prix;
    }

    public int getCategoriePId() {
        return categoriePId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    public void setCategoriePId(int categoriePId) {
        this.categoriePId = categoriePId;
    }

    @Override
    public String toString() {
        return "Produits:"+ " " +"Nom:" + nom + " //Photo: " + photo + " //Prix: " + Prix + " //CategoriePId: " + categoriePId ;
    }

}
