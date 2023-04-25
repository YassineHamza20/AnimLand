package Interfaces;

import entities.OfferEntities;

import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

public interface OfferInterface {
    void createOffer(OfferEntities offer) throws SQLException;

    void updateOffer(OfferEntities offer) throws SQLException;

    int deleteOffer(int id) throws SQLException;

    List<OfferEntities> getAllOffers() throws SQLException;

    OfferEntities getOfferByName(String name) throws SQLException;
    
   public ObservableList<OfferEntities> showOffers() throws SQLException;
   
   public List<String> showOfferNames() throws SQLException;
}
