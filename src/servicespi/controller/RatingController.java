/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicespi.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import servicespi.entities.Rating;
import servicespi.interfaces.IRating;
import servicespi.utils.SingleToneConnection;

/**
 *
 * @author Mynet
 */
public class RatingController implements IRating{
    List<Rating> list;
    Connection connection = SingleToneConnection.getConnection();

    @Override
    public void addRating(Rating r) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rating (user_id_id, rating, service_id_id) VALUES (?, ?, ?)");
            ps.setInt(1, r.getUser_id());
            ps.setFloat(2, r.getRating());
            ps.setInt(3, r.getService().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Float averageRating(int service_id) {
        // average rating per user
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT AVG(rating) FROM event_rating WHERE service_id_id = ?");
            ps.setInt(1, service_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
