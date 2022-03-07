/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.activation.DataSource;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class StaticController implements Initializable {
    Connection cnx;
    private Statement st;
    private ResultSet rs;
    @FXML
    private PieChart piechart;
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
    @FXML
    private Button retour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       stat();
    }    
    private void stat()
    {
          
           Connection cnx = DBConnection.getInstance().getCon();
      try {
           
          String query = "select count(*) As value,ville As ville1 from adresse group by ville" ;
       
         PreparedStatement PreparedStatement = cnx.prepareStatement(query);
          ResultSet rs = PreparedStatement.executeQuery();
            
                     
            while (rs.next()){  
               
               data.add(new PieChart.Data(rs.getString("ville1"),rs.getInt("value")));
            }     
        } catch (SQLException ex) {
            Logger.getLogger(StaticController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        piechart.setTitle("**Statistiques nombres des clients par ville**");
        piechart.setLegendSide(Side.LEFT);
        piechart.setData(data);
    
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/livreur.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root));
    }
}
