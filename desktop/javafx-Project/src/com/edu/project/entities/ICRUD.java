/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.entities;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Sarra Kachouandi
 */
public interface ICRUD<T> {
    
    public void ajouter(T p, String photo);
      public void modifier(Produit p,int id);
         public void supprimer(int id);
         
          public void modifier( Reclamation p,int id);
 //   public void modifier( Reponse p,int id);

     public void ajouterrec(T p);
         public void ajouterCategorie(T c);
      public void modifierCategorie(T c,int id);
        public void supprimerCategorie(int id);
        public List<T> afficherListeCategorie() throws SQLException ;
   public List<T> afficherListe() throws SQLException ;
   
    
    public void addCategory(Category c);
    public void updateCategory(Category c);
    public void deleteCategory(Integer id);
    public List<Category> getAllCategories();
    public Category  getCategoryById(Integer id);
    
    
    public void addService(Service s);
   public void updateService(Service s);
   public void deleteService(Integer id);
   public List<Service> getAllServices();
   public Service getServiceById(Integer id); //override/polymorphism
   public List<Service> search(String keyword);
   
   
     public void ajouteradop(T p);
    public void ajouterr(T p);
    List<T> ChercherAdoption();
   
   
}
