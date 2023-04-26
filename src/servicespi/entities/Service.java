/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicespi.entities;

/**
 *
 * @author Mynet
 */
public class Service {
 
    private int id;
    private double prix;
    private String nom;
    private String description;
    private String date;
    private int category;
    private int viewCount;
    private int likes;
  
 public Service(){      
}
 public Service(int id,double prix,String nom,String description,String date,int category,int viewCount){ 
 
     this.id=id;
     this.prix=prix;
     this.nom=nom;
     this.description=description;
     this.date=date;
     this.category=category;  
     this.viewCount=viewCount;
     this.likes = likes;
}
public Service(double prix,String nom,String description,String date,int category,int viewCount){ 
 
     this.prix=prix;
     this.nom=nom;
     this.description=description;
     this.date=date;
     this.category=category;  
     this.viewCount=viewCount;
     this.likes = likes;
}
public Service(double prix,String nom,String description,String date,int category){ 
 
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
