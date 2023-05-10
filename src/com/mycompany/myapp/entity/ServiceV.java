/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entity;

/**
 *
 * @author Mynet
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    private int category;
    private int viewCount;
    private int likes;
  
 public ServiceV(){      
}
 public ServiceV(int id,double prix,String nom,String description,String date,int category,int viewCount,int likes){ 
 
     this.id=id;
     this.prix=prix;
     this.nom=nom;
     this.description=description;
     this.date=date;
     this.category=category;  
     this.viewCount=viewCount;
     this.likes = likes;
}
public ServiceV(double prix,String nom,String description,String date,int category,int viewCount,int likes){ 
 
     this.prix=prix;
     this.nom=nom;
     this.description=description;
     this.date=date;
     this.category=category;  
     this.viewCount=viewCount;
     this.likes = likes;
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

    public int getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }
    public int getLikes() {
        return likes;
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

    public void setCategory(int category) {
        this.category = category;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }

}
