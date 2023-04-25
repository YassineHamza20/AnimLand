package services;

import Interfaces.SubscriptionInterface;
import entities.SubscriptionEntities;
import Utils.MyDB;
import entities.OfferEntities;

import java.sql.*;
import static java.time.Clock.system;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SubscriptionServices implements SubscriptionInterface {
    Connection connexion;

    public SubscriptionServices() {
        connexion = MyDB.getInstance().getConnexion();
    }

@Override
public void createSubscription(SubscriptionEntities subscription) throws SQLException {
    String query = "INSERT INTO subscription (offer_id, start_date, end_date, is_active) VALUES (?, ?, ?, ?)";
    PreparedStatement statement = connexion.prepareStatement(query);
    statement.setInt(1, subscription.getOffer_id());
    statement.setDate(2, java.sql.Date.valueOf(subscription.getStart_date()));
    statement.setDate(3, java.sql.Date.valueOf(subscription.getEnd_date()));
    statement.setInt(4, 5);
    statement.executeUpdate();
}


    @Override
    public void activateSubscription(SubscriptionEntities subscription) throws SQLException {
        String query = "UPDATE subscription SET is_active=1 WHERE id=?";
        PreparedStatement statement = connexion.prepareStatement(query);
        statement.setInt(1, subscription.getId());
        statement.executeUpdate();
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }
    
    @Override
    public void deactivateSubscription(SubscriptionEntities subscription) throws SQLException {
        String query = "UPDATE subscription SET is_active=0 WHERE id=?";
        PreparedStatement statement = connexion.prepareStatement(query);
        statement.setInt(1, subscription.getId());
        statement.executeUpdate();
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaab");
    }

     @Override
    public ObservableList showSubscriptions() throws SQLException {
       ObservableList<SubscriptionEntities> subscriptions = FXCollections.observableArrayList();
   //  ObservableList<EvenementType> EvenementTypeList = FXCollections.observableArrayList();
    String req = "SELECT p.id, p.start_date, p.end_date, p.is_active, p.prix, c.type "
                + "FROM subscription p "
                + "JOIN offer c ON p.offer_id = c.id";
               
    Statement stm = connexion.createStatement();
    ResultSet rst = stm.executeQuery(req);

    while (rst.next()) {
        SubscriptionEntities p = new SubscriptionEntities(
            rst.getInt("id"),
            rst.getDate("start_date").toLocalDate(),
            rst.getDate("end_date").toLocalDate(),
            rst.getInt("is_active")
      
                
        );
        OfferEntities c = new OfferEntities();
        p.setNaame(c);
        
        subscriptions.add(p);
    }
      return subscriptions;
    
    }
}
