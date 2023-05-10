/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.user;


import com.edu.project.entities.Produit;
import com.edu.project.service.ServicesProduit;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Sarra Kachouandi
 */
public class BarchartController implements Initializable {

    @FXML
    private BarChart<String, Integer> chart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServicesProduit servicesProduit = new ServicesProduit();
            List<Produit> produits = servicesProduit.afficherListe();
            // Create chart data
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            series.setName("Nombres des produits selon PRix");
            
            // Group products by price
            Map<Float, Long> groupedByPrice = produits.stream()
                    .collect(Collectors.groupingBy(Produit::getPrix, Collectors.counting()));
            
            // Add grouped data to the chart series
            for (Map.Entry<Float, Long> entry : groupedByPrice.entrySet()) {
                series.getData().add(new XYChart.Data<>(Float.toString(entry.getKey()), entry.getValue().intValue()));
            }
            
            // Configure chart
            chart.getData().add(series);
            chart.getXAxis().setLabel("Prix");
            chart.getYAxis().setLabel("Nombre des produits");
        } catch (SQLException ex) {
            Logger.getLogger(BarchartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
public void setData(List<Produit> produits) {
    // Create chart data
    XYChart.Series<String, Integer> series = new XYChart.Series<>();
    series.setName("Nombr");

    // Group products by price
    

   
}
}