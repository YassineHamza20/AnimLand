/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.service;

import com.edu.project.entities.ICRUD;
import com.edu.project.entities.CategorieAdoption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DBCnx.MyConnection;
import com.edu.project.entities.Category;
import com.edu.project.entities.Produit;
import com.edu.project.entities.Reclamation;
import com.edu.project.entities.Service;
/**
 *
 * @author User
 */
public class CategorieAdoptionService  implements ICRUD<CategorieAdoption> {
    public Connection conx;
    public Statement stm;
    
    public CategorieAdoptionService() {
        conx = MyConnection.getInstance().getConx();

    }
    @Override
    public void ajouteradop(CategorieAdoption a) {
        String req = "INSERT INTO `categorie_adop`(`type`) VALUES ('" + a.getType()+ "')";
        try {
            stm = conx.createStatement();
            stm.executeUpdate(req);
            System.out.println("categorieAdoption est bien ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public List<CategorieAdoption> afficherListe() throws SQLException {
        String req = "SELECT * FROM `Categorie_Adop`";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        List<CategorieAdoption> CategorieAdoptions = new ArrayList<CategorieAdoption>();
        while (rs.next()) {
            CategorieAdoption p = new CategorieAdoption(rs.getInt(1),rs.getString(2));
            CategorieAdoptions.add(p);
        }

        return CategorieAdoptions;
    }
    
    public void supprimer(int id) {
        try {
            String requete = "DELETE FROM categorie_adop WHERE id=?";
                  PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("categorieAdoption supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void modifier(CategorieAdoption p,int id) {
        try {
            String requete = "UPDATE categorie_adop SET type=? WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(requete);
            
            pst.setString(1, p.getType());
             pst.setInt(2, id);
            pst.executeUpdate();
            System.out.println("Adoption modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void ajouterr(CategorieAdoption p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   @Override
    public List<CategorieAdoption> ChercherAdoption() {
       
        List<CategorieAdoption> CategorieAdoptions = new ArrayList<>();

        try {
            String sql = "SELECT * FROM categorie_adop ";
            ResultSet rs;
            PreparedStatement ste;
            ste = conx.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                CategorieAdoption p = new CategorieAdoption();
                p.setId(rs.getInt("id"));
                p.setType(rs.getString("type"));
                CategorieAdoptions.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        System.out.println("------> " + CategorieAdoptions.size());
        return CategorieAdoptions;
    }

    @Override
    public void ajouter(CategorieAdoption p, String photo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Produit p, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Reclamation p, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterrec(CategorieAdoption p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterCategorie(CategorieAdoption c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierCategorie(CategorieAdoption c, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerCategorie(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CategorieAdoption> afficherListeCategorie() throws SQLException {
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
}
