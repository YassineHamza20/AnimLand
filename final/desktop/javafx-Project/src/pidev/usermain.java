/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author ScaareCr0w
 */
public class usermain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
      /*   Parent root =FXMLLoader.load(getClass().getResource("../com/edu/project/user/Main.fxml"));
           //Parent root =FXMLLoader.load(getClass().getResource("../gui/frontOffice/home/home.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Administration");
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show(); */
              Parent root = FXMLLoader.load(getClass().getResource("../com/edu/project/user/Main.fxml"));        
        Scene scene = new Scene(root);
        primaryStage.setTitle("Administration");
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
