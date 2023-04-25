/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Reclamation;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import services.ReclamationService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author sinoh
 */
public class ChartpieController implements Initializable {

    @FXML
    private PieChart chart;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
MyDB myDB = MyDB.getInstance();
    Connection conx = myDB.getConx();

String sql = "SELECT objet, COUNT(*) as count FROM Reclamation GROUP BY objet";
    try {
        Statement stmt = conx.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<PieChart.Data> pieChartData = new ArrayList<>();
        int total = 0;

        while (rs.next()) {
            String domaine = rs.getString("objet");
            int count = rs.getInt("count");
            total += count;
            PieChart.Data data = new PieChart.Data(domaine + " (" + count + ")", count);
            pieChartData.add(data);
        }

        for (PieChart.Data data : pieChartData) {
            double percentage = (data.getPieValue() / total) * 100;
            data.setName(data.getName() + " - " + String.format("%.2f", percentage) + "%");
        }

        chart.setData(FXCollections.observableArrayList(pieChartData));

    } catch (SQLException e) {
        e.printStackTrace();
    }  
    
    }
    
}
