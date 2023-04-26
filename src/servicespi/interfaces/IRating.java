/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicespi.interfaces;

import servicespi.entities.Rating;

/**
 *
 * @author Mynet
 */
public interface IRating {
      public void addRating(Rating r);
    public Float averageRating(int event_id);
}
