/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author macbook
 */
public class Tsti extends Application {
    
   @Override
   public void start(Stage primaryStage) throws Exception{
        //Parent root  = FXMLLoader.load(getClass().getResource("/GUI/ArticFXML.fxml"));
        Parent root  = FXMLLoader.load(getClass().getResource("/GUI/commentaires.fxml"));
         //Parent root  = FXMLLoader.load(getClass().getResource("/GUI/stats.fxml"));
        primaryStage.setTitle("I_chariot");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
