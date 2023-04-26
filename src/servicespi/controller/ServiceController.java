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

    public static void updateEvent(Service service) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
            PreparedStatement ps = connection.prepareStatement("UPDATE service_v SET prix = ?,nom = ?,description = ?,date = ?,view_count = ?,category_id = ? WHERE id = ?");
           ps.setDouble(1, associationCategory.getPrix());
             ps.setString(2, associationCategory.getNom());
              ps.setString(3, associationCategory.getDescription());
               ps.setString(4, associationCategory.getDate());
               ps.setInt(5, associationCategory.getViewCount());
               ps.setInt(6, associationCategory.getCategory());
               ps.setInt(7, associationCategory.getId());
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
    
     public List<Service> Search() {
       
        List<Service> Service = new ArrayList<>();

        try {
            String sql = "SELECT * FROM service_v ";
            ResultSet rs;
            PreparedStatement ste;
            ste = connection.prepareStatement(sql);
            rs = ste.executeQuery();
            while (rs.next()) {
                Service S = new Service();
                S.setId(rs.getInt("id"));
                S.setPrix(rs.getDouble("Prix"));
                S.setNom(rs.getString("nom"));
                S.setDescription(rs.getString("description"));
                S.setDate(rs.getString("date")); 
                S.setCategory(rs.getInt("category_id")); 
                Service.add(S);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        System.out.println("------> " + Service.size());
        return Service;
    } 
      @Override
       public List<Service> search(String keyword) {
        List<Service> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM service_v WHERE nom LIKE ? OR description LIKE ?");
            String likeKeyword = "%" + keyword + "%";
            ps.setString(1, likeKeyword);
            ps.setString(2, likeKeyword);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
    
                Service S = new Service();
                S.setId(rs.getInt("id"));
                S.setPrix(rs.getDouble("Prix"));
                S.setNom(rs.getString("nom"));
                S.setDescription(rs.getString("description"));
                S.setDate(rs.getString("date")); 
                S.setCategory(rs.getInt("category_id")); 
                list.add(S);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
