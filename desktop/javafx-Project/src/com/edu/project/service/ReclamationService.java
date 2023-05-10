/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.service;

import com.edu.project.entities.ICRUD;
import com.edu.project.entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import DBCnx.MyConnection;
import com.edu.project.entities.Category;
import com.edu.project.entities.Produit;
import com.edu.project.entities.Service;
//import static utils.MyDB.instance;

/**
 *
 * @author Yassine
 */
public class ReclamationService implements ICRUD <Reclamation> {
 private Statement st;
  private ResultSet rs;
    public Connection conx;
    public Statement stm;
  private static ReclamationService instance;
    public ReclamationService() {
        conx = MyConnection.getInstance().getConx();

    }
     
    public static ReclamationService getInstance(){
        if(instance==null) 
            instance=new ReclamationService();
        return instance;
    }
    
    
    @Override
    public void ajouterrec(Reclamation p) {
        String req = "INSERT INTO `reclamation`(`objet`,`description`,`nom`) VALUES ('" + p.getObjet() + "','" + p.getDescription() + "','" + p.getNom()+"')";
        try {
            stm = conx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Reclamation est bien ajoutée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
@Override
    public List<Reclamation> afficherListe() throws SQLException {
        String req = "SELECT * FROM reclamation";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        List<Reclamation> Reclamations = new ArrayList<Reclamation>();
        while (rs.next()) {
            Reclamation p = new Reclamation(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4));
//or rst.getInt(1)  
            Reclamations.add(p);
        }

        return Reclamations;
    }

    public void supprimer(int id) {
        try {
            String requete = "DELETE FROM reclamation WHERE id=?";
                  PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Reclamation supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }}

    public void modifier(Reclamation p,int id) {
        try {
            String requete = "UPDATE reclamation SET objet=?,description=?,nom=? WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(requete);
       
            pst.setString(1, p.getObjet());
            pst.setString(2, p.getDescription());
            pst.setString(3, p.getNom());
             pst.setInt(4, id);
            pst.executeUpdate();
            System.out.println("Reclamation modifiée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    
     
    public List<Reclamation> ChercherReclamation() {
       
        List<Reclamation> Reclamations = new ArrayList<>();

        try {
            String sql = "SELECT * FROM reclamation ";
            ResultSet rs;
            PreparedStatement ste;
            ste = conx.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Reclamation p = new Reclamation();
                p.setId(rs.getInt("id"));
                p.setObjet(rs.getString("objet"));
                p.setDescription(rs.getString("Description"));
                p.setNom(rs.getString("nom"));
                Reclamations.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        System.out.println("------> " + Reclamations.size());
        return Reclamations;
    }
    
    
      public List<Reclamation> rechercher(String recherche) throws SQLException {
    List<Reclamation> produits = afficherListe().stream()
            .filter(x -> 
                x.getObjet().contains(recherche) ||
                x.getDescription().contains(recherche) ||
                x.getNom().contains(recherche))
            .collect(Collectors.toList());
    return produits;       
}

    @Override
    public void ajouter(Reclamation p, String photo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Produit p, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public void ajouterCategorie(Reclamation c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierCategorie(Reclamation c, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerCategorie(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> afficherListeCategorie() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCategory(Category c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCategory(Category c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCategory(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> getAllCategories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category getCategoryById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addService(Service s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateService(Service s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteService(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Service> getAllServices() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Service getServiceById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Service> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouteradop(Reclamation p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterr(Reclamation p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> ChercherAdoption() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
