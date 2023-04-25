/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.CategorieP;
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
public class ServiceCategorie implements ICRUD<CategorieP> {
    public Connection conx;
    public Statement stm;
    
    /**
     *
     */
    public ServiceCategorie() {
        conx = MYDB.getInstance().getConx();

    }
    
 


    @Override
    public void ajouter(CategorieP c, String photo) {
    
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
    public List<CategorieP> afficherListe() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterCategorie(CategorieP c) {
           String req = "INSERT INTO `categorie_p`(`type`) VALUES ('" + c.getType() + "')";

        try {
            stm = conx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Categorie est bien ajoutée");
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
    public void modifierCategorie(CategorieP c, int id) {
        try {
            String requete = "UPDATE categorie_p SET type=? WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(requete);
            
            pst.setString(1, c.getType());
           pst.setInt(2, id);
            pst.executeUpdate();
            System.out.println("Categorie modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }

    @Override
    public List<CategorieP> afficherListeCategorie() throws SQLException {
         String req = "SELECT * FROM `categorie_p`";
    stm = conx.createStatement();
    ResultSet rs = stm.executeQuery(req);
    List<CategorieP> categories = new ArrayList<CategorieP>();
    while (rs.next()) {
       int id = Integer.parseInt(rs.getString("id"));

        String type = rs.getString("type");
        

        CategorieP c = new CategorieP(id,type);
        categories.add(c);
    }
    return categories;

    }

    @Override
    public void supprimerCategorie(int id) {
         try {
            String requete = "DELETE FROM categorie_p WHERE id=?";
                  PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Categorie supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
