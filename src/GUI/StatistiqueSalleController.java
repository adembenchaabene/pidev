/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import utils.DBConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class StatistiqueSalleController implements Initializable {
    @FXML
    private PieChart idStatSalle;
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        stat();
    }    
    private void stat()
    {
          
           Connection cnx = DBConnection.getInstance().getCon();
      try {
           
          String query = "select capacite As valueCapacite,nom As nomSalle from Salle " ;
       
         PreparedStatement PreparedStatement = cnx.prepareStatement(query);
          ResultSet rs = PreparedStatement.executeQuery();
            
                     
            while (rs.next()){  
               
               data.add(new PieChart.Data(rs.getString("nomSalle"),rs.getInt("valueCapacite")));
            }     
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        idStatSalle.setTitle("*Statistiques salles par capacité");
        idStatSalle.setLegendSide(Side.LEFT);
        idStatSalle.setData(data);
    
    }
}
