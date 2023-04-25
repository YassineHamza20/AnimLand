/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entities.SubscriptionEntities;

import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

public interface SubscriptionInterface {
   void createSubscription(SubscriptionEntities subscription) throws SQLException;

   public void activateSubscription(SubscriptionEntities subscription) throws SQLException;
   
   public void deactivateSubscription(SubscriptionEntities subscription) throws SQLException;


   public ObservableList<SubscriptionEntities> showSubscriptions() throws SQLException;

}

