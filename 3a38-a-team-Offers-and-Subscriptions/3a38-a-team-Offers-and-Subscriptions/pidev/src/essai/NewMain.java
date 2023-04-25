/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package essai;
import Utils.MyDB;
import entities.OfferEntities;
import entities.SubscriptionEntities;
import java.sql.SQLException;
import java.time.LocalDate;
import services.OfferServices;
import services.SubscriptionServices;
/**
 *
 * @author PC
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        OfferEntities oe = new OfferEntities("zab", "zab", 1, 2, "ezebi", "nami");
        OfferServices os = new OfferServices();
        os.createOffer(oe);
        
        LocalDate startDate = LocalDate.of(2022, 5, 1);
        LocalDate endDate = LocalDate.of(2022, 5, 31);
        SubscriptionEntities subscription = new SubscriptionEntities(25, startDate, endDate);
        SubscriptionServices subscriptionServices = new SubscriptionServices();
        subscriptionServices.createSubscription(subscription);
        System.out.println("Subscription id before update: " + subscription.getId());
        
}
}
