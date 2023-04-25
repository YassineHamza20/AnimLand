/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


/**
 *
 * @author Admin
 */
public class OfferEntities {
    private int id,duration,price;
    private String name,description,paymentLink,offer_key;

    public String getOffer_key() {
        return offer_key;
    }

    public void setOffer_key(String offer_key) {
        this.offer_key = offer_key;
    }

    public OfferEntities() {
    }

    public OfferEntities(String name , String description , int price ,int duration , String paymentLink,String offer_key) {
        
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
        this.paymentLink = paymentLink;
        this.offer_key= offer_key;
    }

    public OfferEntities( int id, String name, String description, int price, int duration, String paymentLink) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.paymentLink = paymentLink;
    }
    
        public OfferEntities( int id, String name, String description, int price, int duration, String paymentLink, String offer_key) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.paymentLink = paymentLink;
        this.offer_key = offer_key;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentLink() {
        return paymentLink;
    }

    public void setPaymentLink(String paymentLink) {
        this.paymentLink = paymentLink;
    }

    @Override
    public String toString() {
        return "OfferEntities{" + "id=" + id +  ", name=" + name + ", description=" +  description + ", price=" + price + ", duration=" + duration +  ", paymentLink=" + paymentLink + ", offer_key=" + offer_key + '}';
    }


    



  


    
    
    
 
}


