/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.ICRUD;
import Entites.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

import utils.MYDB;

/**
 *
 * @author Sarra Kachouandi
 */
public class ServicesProduit implements ICRUD<Produit> {
       
    public Connection conx;
    public Statement stm;
    
    
 public ServicesProduit() {
        conx = MYDB.getInstance().getConx();

    }
    
  
    @Override
    public void ajouter(Produit p, String photo) {
   String req = "INSERT INTO `produit`(`nom`, `photo`, `prix`, `categorie_p_id`) VALUES ('" + p.getNom() + "','" + photo + "'," + p.getPrix() + "," + p.getCategoriePId() + ")";

        try {
            stm = conx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Produit est bien ajoutée");
       } catch (SQLException e) {
            // Show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
//            alert.setHeaderText(null);
            alert.setContentText("Failed to add product.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public List<Produit> afficherListe() throws SQLException {
    String req = "SELECT * FROM `produit`";
    stm = conx.createStatement();
    ResultSet rs = stm.executeQuery(req);
    List<Produit> produits = new ArrayList<Produit>();
    while (rs.next()) {
        int id = rs.getInt("id");
        String nom = rs.getString("nom");
        String photo = rs.getString("photo");
        float prix = rs.getFloat("prix");
        int categoriePId = rs.getInt("categorie_p_id");

        Produit p = new Produit(id, nom, photo, prix, categoriePId);
        produits.add(p);
    }
    return produits;
}

    @Override
    public void modifier(Produit p, int id) {
        try {
            String requete = "UPDATE produit SET nom=?,photo=?,categorie_p_id=?,prix=? WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(requete);
            
            pst.setString(1, p.getNom());
            pst.setString(2, p.getPhoto());
            pst.setInt(3, p.getCategoriePId());
            pst.setFloat(4, p.getPrix());
             pst.setInt(5, id);
            pst.executeUpdate();
            System.out.println("Utilisateur modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }

    @Override
    public void supprimer(int id) {
       try {
            String requete = "DELETE FROM produit WHERE id=?";
                  PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("produit supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }

  
}

    

 

