/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import utils.DBConnection;
import java.io.IOException;
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

/**
 * FXML Controller class
 *
 * @author chaab
 */
public class StatsController implements Initializable {

    @FXML
    private PieChart piechart;
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
    @FXML
    private Button statik;

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
          
           Connection con = DBConnection.getInstance().getCon();
      try {
           
          String query = "select nbrreact As nbr,titre As titreArticle from article " ;
       
         PreparedStatement PreparedStatement = con.prepareStatement(query);
          ResultSet rs = PreparedStatement.executeQuery();
            
                     
            while (rs.next()){  
               
               data.add(new PieChart.Data(rs.getString("titreArticle"),rs.getInt("nbr")));
            }     
        } catch (SQLException ex) {
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        piechart.setTitle("Statistiques nombres des reactions par Articles");
        piechart.setLegendSide(Side.LEFT);
        piechart.setData(data);
    
    }

    @FXML
    private void retourstat(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/ArticFXML.fxml"));
    Stage window = (Stage) statik.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("I_chariot");
    }

}
