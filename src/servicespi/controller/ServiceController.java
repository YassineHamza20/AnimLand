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
import java.util.ArrayList;
import java.util.List;
import servicespi.entities.Service;
import servicespi.interfaces.ServiceInterface;
import servicespi.utils.SingleToneConnection;

public class ServiceController implements ServiceInterface {

    private List<Service> list;
    Connection connection = SingleToneConnection.getConnection();

    public ServiceController() {
    }

    @Override
    public List<Service> getAllServices() {
        list = new ArrayList<Service>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM service_v");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new Service(rs.getInt(1), rs.getDouble(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Service getServiceById(Integer id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM service_v WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Service(rs.getInt(1), rs.getDouble(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addService(Service associationCategory) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO service_v (id,prix,nom,description,date,category_id,view_count) VALUES (null, ?, ?, ?, ?, ?, ?)");
            ps.setDouble(1, associationCategory.getPrix());
            ps.setString(2, associationCategory.getNom());
            ps.setString(3, associationCategory.getDescription());
            ps.setString(4, associationCategory.getDate());
            ps.setInt(5, associationCategory.getCategory());
            ps.setInt(6, associationCategory.getViewCount());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateService(Service associationCategory) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE service_v SET prix = ?,nom = ?,description = ?,date = ?,category_id = ? WHERE id = ?");
           ps.setDouble(1, associationCategory.getPrix());
             ps.setString(2, associationCategory.getNom());
              ps.setString(3, associationCategory.getDescription());
               ps.setString(4, associationCategory.getDate());
               ps.setInt(5, associationCategory.getCategory());
               ps.setInt(6, associationCategory.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteService(Integer id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM service_v WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
