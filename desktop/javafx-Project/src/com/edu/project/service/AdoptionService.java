/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.service;

import com.edu.project.entities.ICRUD;
import com.edu.project.entities.Adoption;
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
;

/**
 *
 * @author Ennou
 */
public class AdoptionService implements ICRUD<Adoption> {

    public Connection conx;
    public Statement stm;

    public AdoptionService() {
        conx = MyConnection.getInstance().getConx();

    }

    @Override
    public void ajouteradop(Adoption a) {
        String req = "INSERT INTO `Adoption`(`nom`, `age`,`description`,`proprietaire`,`race`,`sexe`,categorie_id) VALUES ('" + a.getNom() + "','" + a.getAge() + "','" +a.getDescription()+ "','" +a.getProprietaire()+ "','" +a.getRace()+ "','" +a.getSexe()+ "','" +a.getC_id()+ "')";
        try {
            stm = conx.createStatement();
            stm.executeUpdate(req);
            System.out.println("adoption est bien ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
/*
    @Override
    public void ajouterr(Adoption p) {
        String req = "INSERT INTO `Adoption`(`nom`, `prenom`) "
                + "VALUES (?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.executeUpdate();
            System.out.println("Adoption ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/

    @Override
    public List<Adoption> afficherListe() throws SQLException {
        String req = "SELECT * FROM `Adoption`";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        List<Adoption> Adoptions = new ArrayList<Adoption>();
        while (rs.next()) {
            Adoption p = new Adoption(rs.getInt(1),//or rst.getInt(1)
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getInt(8));
            Adoptions.add(p);
        }

        return Adoptions;
    }
public void supprimer(int id) {
        try {
            String requete = "DELETE FROM Adoption WHERE id=?";
                  PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("adoption supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

public void modifieradop(Adoption a) {

        try {
            String requete = "UPDATE Adoption SET nom=?,age=?,description=?,proprietaire=?,sexe=?,race=? WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(requete);

            // Demander à l'utilisateur les nouvelles valeurs
            pst.setString(1, a.getNom());
            pst.setString(2, a.getAge());
            pst.setString(3, a.getDescription());
            pst.setString(4, a.getProprietaire());
             pst.setString(5, a.getSexe());
             pst.setString(6, a.getRace());
             pst.setInt(7, a.getId());

            // Passer les nouvelles valeurs comme paramètres à la requête SQL
            pst.executeUpdate();
            System.out.println("Adoption modifiée !");

            System.out.println("La réponse a été mise à jour avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour de la reponse : " + ex.getMessage());
        }
    }


    public void modifier(Adoption p,int id) {
        try {
            String requete = "UPDATE Adoption SET nom=?,age=?,description=?,proprietaire=?,sexe=?,race=? WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(requete);
            
            pst.setString(1, p.getNom());
            pst.setString(2, p.getAge());
            pst.setString(3, p.getDescription());
            pst.setString(4, p.getProprietaire());
             pst.setString(5, p.getSexe());
             pst.setString(6, p.getRace());
             pst.setInt(7, id);
            pst.executeUpdate();
            System.out.println("Adoption modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public List<Adoption> ChercherAdoption() {
       
        List<Adoption> Adoptions = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Adoption ";
            ResultSet rs;
            PreparedStatement ste;
            ste = conx.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Adoption p = new Adoption();
                p.setId(rs.getInt("id"));
                p.setAge(rs.getString("age"));
                p.setDescription(rs.getString("description"));
                p.setNom(rs.getString("nom"));
                p.setProprietaire(rs.getString("proprietaire"));
                p.setRace(rs.getString("race"));
                p.setSexe(rs.getString("sexe"));
                p.setC_id(rs.getInt("categorie_id"));
                Adoptions.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        System.out.println("------> " + Adoptions.size());
        return Adoptions;
    }

    @Override
    public void ajouterr(Adoption p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter(Adoption p, String photo) {
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
    public void ajouterrec(Adoption p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterCategorie(Adoption c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierCategorie(Adoption c, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerCategorie(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Adoption> afficherListeCategorie() throws SQLException {
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
