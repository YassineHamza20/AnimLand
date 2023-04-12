/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.ICRUD;
import entites.Reponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author Yassine
 */
public class  ReponseService {
 private Statement st;
  private ResultSet rs;
    public Connection conx;
    public Statement stm;

    public ReponseService() {
        conx = MyDB.getInstance().getConx();

    }
                                                                                                                                                                                                   
   
    public void ajouter(Reponse p) {
        String req = "INSERT INTO `Reponse`(`objet`,`description`,`nom`,`reclamation_id`) VALUES ('" + p.getObjet() + "','" +  p.getDescription() + "','" + p.getNom()+"','" +p.getReclamation_id()+"')";
        try {
            stm = conx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Reponse est bien ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  
    public List<Reponse> afficherListe() throws SQLException {
        String req = "SELECT * FROM Reponse";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        List<Reponse> Reponses = new ArrayList<Reponse>();
        while (rs.next()) {
            Reponse p = new Reponse(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
//or rst.getInt(1)  
            Reponses.add(p);
        }

        return Reponses;
    }

    public void supprimer(int id) {
        try {
            String requete = "DELETE FROM Reponse WHERE id=?";
                  PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Reponse supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }}

    public void modifier(Reponse p,int id) {
        try {
            String requete = "UPDATE Reponse SET objet=?,description=?,nom=? WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(requete);
       
            pst.setString(1, p.getObjet());
            pst.setString(2, p.getDescription());
            pst.setString(3, p.getNom());
             pst.setInt(4, id);
            pst.executeUpdate();
            System.out.println("Reponse modifiée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public List<Reponse> ChercherReponse() {
       
        List<Reponse> Reponses = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Reponse ";
            ResultSet rs;
            PreparedStatement ste;
            ste = conx.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Reponse p = new Reponse();
                p.setId(rs.getInt("id"));
                p.setObjet(rs.getString("objet"));
                p.setDescription(rs.getString("Description"));
                p.setNom(rs.getString("nom"));
                  p.setReclamation_id(rs.getInt("reclamation_id"));
                Reponses.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        System.out.println("------> " + Reponses.size());
        return Reponses;
    }
    
    
}
