/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produit;

/**
 *
 * @author Sarra Kachouandi
 */
public class Location {
     private double latitude;
    private double longitude;
    private String description;
    private String name;

    public Location(double latitude, double longitude, String description, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

