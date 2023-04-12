/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ennou
 */
public interface ICRUD<T> {
    
    public void ajouter(T p);
    public List<T> afficherListe() throws SQLException ;
    
}
