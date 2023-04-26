/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicespi.entities;

/**
 *
 * @author Mynet
 */

public class Rating {

    private Integer id;
    private Integer user_id;
    private Float rating;
    private Service service;

    public Rating() {}
    public Rating(Integer user_id, Float rating, Service service) {
        this.user_id = user_id;
        this.rating = rating;
        this.service = service;
    }
    public Rating(Integer id, Integer user_id, Float rating, Service service) {
        this.id = id;
        this.user_id = user_id;
        this.rating = rating;
        this.service = service;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service event) {
        this.service = event;
    }
}
