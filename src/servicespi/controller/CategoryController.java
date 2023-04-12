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
import servicespi.entities.Category;
import servicespi.interfaces.CategoryInterface;
import servicespi.utils.SingleToneConnection;

public class CategoryController implements CategoryInterface {

    private List<Category> list;
    Connection connection = SingleToneConnection.getConnection();

    public CategoryController() {
    }

    @Override
    public List<Category> getAllCategories() {
        list = new ArrayList<Category>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM categorie_v");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Category getCategoryById(Integer id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM categorie_v WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Category(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addCategory(Category associationCategory) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO categorie_v (id, type) VALUES (null, ?)");
            ps.setString(1, associationCategory.getType());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategory(Category associationCategory) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE categorie_v SET type = ? WHERE id = ?");
            ps.setString(1, associationCategory.getType());
            ps.setInt(2, associationCategory.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(Integer id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM categorie_v WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
