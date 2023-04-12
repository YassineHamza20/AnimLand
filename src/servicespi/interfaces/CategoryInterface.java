/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicespi.interfaces;

import java.util.List;
import servicespi.entities.Category;

/**
 *
 * @author Mynet
 */
public interface CategoryInterface {
    
    public void addCategory(Category c);
    public void updateCategory(Category c);
    public void deleteCategory(Integer id);
    public List<Category> getAllCategories();
    public Category  getCategoryById(Integer id);
}
