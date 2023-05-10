/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Mynet
 */
public class ServiceV {
 
    private int id;
    private double prix;
    private String nom;
    private String description;
    private String date;
    private CategorieV category;
    private int viewCount;
  
 public ServiceV(){      
}
 public ServiceV(int id,double prix,String nom,String description,String date,CategorieV category,int viewCount){ 
 
     this.id=id;
     this.prix=prix;
     this.nom=nom;
     this.description=description;
     this.date=date;
     this.category=category;  
     this.viewCount=viewCount;
}
public ServiceV(double prix,String nom,String description,String date,CategorieV category,int viewCount){ 
 
     this.prix=prix;
     this.nom=nom;
     this.description=description;
     this.date=date;
     this.category=category;  
     this.viewCount=viewCount;
}
public ServiceV(double prix,String nom,String description,String date,CategorieV category){ 
 
     this.prix=prix;
     this.nom=nom;
     this.description=description;
     this.date=date;
     this.category=category;  

}

    public int getViewCount() {
        return viewCount;
    }

    public double getPrix() {
        return prix;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public CategorieV getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCategory(CategorieV category) {
        this.category = category;
    }

}
