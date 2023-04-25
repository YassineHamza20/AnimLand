package entities;

import java.time.LocalDate;
import entities.OfferEntities;

public class SubscriptionEntities {
    private int id,offer_id;

    public int getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(int offer_id) {
        this.offer_id = offer_id;
    }
    private LocalDate start_date;
    private LocalDate end_date;
    private int is_active;
    private OfferEntities name;

    public SubscriptionEntities(int id, OfferEntities name, LocalDate start_date, LocalDate end_date, int is_active) {
        this.id = id;
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.is_active = is_active;
    }
    
       public SubscriptionEntities(int id, LocalDate start_date, LocalDate end_date, int is_active,OfferEntities name) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.is_active = is_active;
        this.name = name;

    }
       
    public SubscriptionEntities(int id, LocalDate start_date, LocalDate end_date, int is_active) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.is_active = is_active;

    }
    
    public SubscriptionEntities(LocalDate start_date, LocalDate end_date,OfferEntities name) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.name = name;
    }
       public SubscriptionEntities(int offer_id, LocalDate start_date, LocalDate end_date) {
        this.offer_id = offer_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public SubscriptionEntities(LocalDate start_date, LocalDate end_date, int is_active,OfferEntities name) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.is_active = is_active;
        this.name = name;
    }

    public SubscriptionEntities() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public int getIsActive() {
        return is_active;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public OfferEntities getName() {
        return name;
    }

    public void setNaame(OfferEntities name) {
        this.name = name;
    }

    public void setIsActive(int is_active) {
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "SubscriptionEntities{" +
                "id=" + id +
                ", name=" + name +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", is_active=" + is_active +
                '}';
    }
}
