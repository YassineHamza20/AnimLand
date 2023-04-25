package services;

import Interfaces.OfferInterface;
import entities.OfferEntities;
import Utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OfferServices implements OfferInterface{
  Connection connexion;

    public OfferServices() {
        connexion = MyDB.getInstance().getConnexion();
    }
    @Override
    public void createOffer(OfferEntities offer) throws SQLException {
        String query = "INSERT INTO offer (name, description, price , duration, payment_link , offer_key) VALUES ( ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connexion.prepareStatement(query);
        statement.setString(1, offer.getName());
        statement.setString(2, offer.getDescription());
        statement.setInt(3, offer.getPrice());
        statement.setInt(4, offer.getDuration());
        statement.setString(5, offer.getPaymentLink());
        statement.setString(6, offer.getOffer_key());
        statement.executeUpdate();
        System.out.println("okito");
    }
    @Override
    public void updateOffer(OfferEntities offer) throws SQLException {
        String query = "UPDATE offer SET duration=?, price=?, name=?, description=?, payment_link=? WHERE id=?";
        PreparedStatement statement = connexion.prepareStatement(query);
        statement.setInt(1, offer.getDuration());
        statement.setInt(2, offer.getPrice());
        statement.setString(3, offer.getName());
        statement.setString(4, offer.getDescription());
        statement.setString(5, offer.getPaymentLink());
        statement.setInt(6, offer.getId());
        statement.executeUpdate();
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
@Override
public int deleteOffer(int id) throws SQLException {
    String query = "DELETE FROM offer WHERE id=?";
    PreparedStatement statement = connexion.prepareStatement(query);
    statement.setInt(1, id);
    int rowsDeleted = statement.executeUpdate();
    return rowsDeleted;
}

    
    @Override
    public List<OfferEntities> getAllOffers() throws SQLException {
        List<OfferEntities> offers = new ArrayList<>();
        String query = "SELECT * FROM offer";
        Statement statement = connexion.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            OfferEntities offer = new OfferEntities();
            offer.setId(resultSet.getInt("id"));
            offer.setDuration(resultSet.getInt("duration"));
            offer.setPrice(resultSet.getInt("price"));
            offer.setName(resultSet.getString("name"));
            offer.setDescription(resultSet.getString("description"));
            offer.setPaymentLink(resultSet.getString("payment_link"));
            offers.add(offer);
        }
        return offers;
    }
  @Override
  public OfferEntities getOfferByName(String name) throws SQLException {
    String query = "SELECT * FROM offer WHERE name=?";
    PreparedStatement statement = connexion.prepareStatement(query);
    statement.setString(1, name);
    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
        OfferEntities offer = new OfferEntities();
        offer.setId(resultSet.getInt("id"));
        offer.setDuration(resultSet.getInt("duration"));
        offer.setPrice(resultSet.getInt("price"));
        offer.setName(resultSet.getString("name"));
        offer.setDescription(resultSet.getString("description"));
        offer.setPaymentLink(resultSet.getString("payment_link"));
        return offer;
    } else {
        return null;
    }
}
    @Override
    public ObservableList showOffers() throws SQLException {
       ObservableList<OfferEntities> offers = FXCollections.observableArrayList();
   //  ObservableList<EvenementType> EvenementTypeList = FXCollections.observableArrayList();
    String req = "SELECT *  FROM offer ";
               
    Statement stm = connexion.createStatement();
    ResultSet rst = stm.executeQuery(req);

    while (rst.next()) {
        OfferEntities p = new OfferEntities(
            rst.getInt("id"),
            rst.getString("name"),
            rst.getString("description"),
            rst.getInt("price"),
            rst.getInt("duration"),
            rst.getString("payment_link"),
            rst.getString("offer_key")
      
                
        );
        
        offers.add(p);
    }
      
    
    return offers;
    
    }
    
    @Override
    public List<String> showOfferNames() throws SQLException {
    List<String> offerNames = new ArrayList<>();
    String query = "SELECT name FROM offer";
    Statement statement = connexion.createStatement();
    ResultSet resultSet = statement.executeQuery(query);
    while (resultSet.next()) {
        offerNames.add(resultSet.getString("name"));
    }
    return offerNames;
}
}
