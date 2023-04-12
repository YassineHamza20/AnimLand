/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

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
   public List<T> afficherListe() throws SQLException ;
}
