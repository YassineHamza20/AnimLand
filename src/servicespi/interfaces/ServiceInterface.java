/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicespi.interfaces;

import java.util.List;
import servicespi.entities.Service;

/**
 *
 * @author Mynet
 */
public interface ServiceInterface {
   public void addService(Service s);
   public void updateService(Service s);
   public void deleteService(Integer id);
   public List<Service> getAllServices();
   public Service getServiceById(Integer id); //override/polymorphism
   public List<Service> search(String keyword);
}


