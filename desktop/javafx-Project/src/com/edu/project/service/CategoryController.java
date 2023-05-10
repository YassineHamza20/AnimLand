/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.edu.project.entities.Category;

import DBCnx.MyConnection;
import com.edu.project.entities.ICRUD;
import com.edu.project.entities.Produit;
import com.edu.project.entities.Reclamation;
import com.edu.project.entities.Service;
import java.sql.Statement;

public class CategoryController implements ICRUD<Category> {
  public Connection conx;
    public Statement stm;
    
    /**
     *
     */
    public CategoryController() {
        conx = MyConnection.getInstance().getConx();

    }
    
    private List<Category> list;
   
   

    @Override
    public List<Category> getAllCategories() {
        list = new ArrayList<Category>();
        try {
            PreparedStatement ps = conx.prepareStatement("SELECT * FROM categorie_v");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Category getCategoryById(Integer id) {
        try {
            PreparedStatement ps = conx.prepareStatement("SELECT * FROM categorie_v WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Category(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addCategory(Category associationCategory) {
        try {
            PreparedStatement ps = conx.prepareStatement("INSERT INTO categorie_v (id, type) VALUES (null, ?)");
            ps.setString(1, associationCategory.getType());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategory(Category associationCategory) {
        try {
            PreparedStatement ps = conx.prepareStatement("UPDATE categorie_v SET type = ? WHERE id = ?");
            ps.setString(1, associationCategory.getType());
            ps.setInt(2, associationCategory.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(Integer id) {
        try {
            PreparedStatement ps = conx.prepareStatement("DELETE FROM categorie_v WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Category> Search() {
       
        List<Category> Category = new ArrayList<>();

        try {
            String sql = "SELECT * FROM categorie_v ";
            ResultSet rs;
            PreparedStatement ste;
            ste = conx.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Category p = new Category();
                p.setId(rs.getInt("id"));
                p.setType(rs.getString("type"));
                Category.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        System.out.println("------> " + Category.size());
        return Category;
    } 

    @Override
    public void ajouter(Category p, String photo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Produit p, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Reclamation p, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterrec(Category p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterCategorie(Category c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierCategorie(Category c, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerCategorie(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> afficherListeCategorie() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> afficherListe() throws SQLException {
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
    public void ajouteradop(Category p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterr(Category p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> ChercherAdoption() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
