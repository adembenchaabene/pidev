/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 *
 * @author DELL
 */
public class I_chariot extends Application {
    
 
    Parent parent;
    Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        try {
            
            //parent = FXMLLoader.load(getClass().getResource("/GUI/CategorieFXML.fxml"));
          // parent = FXMLLoader.load(getClass().getResource("/GUI/StatistiqueProduit.fxml"));
          //parent = FXMLLoader.load(getClass().getResource("/GUI/ProduitFXML.fxml"));
      parent = FXMLLoader.load(getClass().getResource("/GUI/ClientAdresse.fxml"));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Ajouter categ");
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
       
      
    }
    
    
}
