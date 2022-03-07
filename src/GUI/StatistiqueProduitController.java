/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Util.DBConnection;
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

/**
 * FXML Controller class
 *
 * @author compu serv
 */
public class StatistiqueProduitController implements Initializable {

    @FXML
    private PieChart piechart1;
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
     @FXML
    private Button retour;

    Connection con;
    private Statement st;
    private ResultSet rs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       statproduit();
    }    
    private void statproduit()
    {
          Connection con = DBConnection.getInstance().getCon();
          
      try {
           
          String query = "select quantite As Qtn,nomProduit As nom from produit group by nomProduit" ;
       
         PreparedStatement PreparedStatement = con.prepareStatement(query);
          ResultSet rs = PreparedStatement.executeQuery();
             
                     
            while (rs.next()){  
               
               data.add(new PieChart.Data(rs.getString("nom"),rs.getInt("Qtn")));
            }     
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        piechart1.setTitle("*Statistique des produits selon quantite*");
        piechart1.setLegendSide(Side.LEFT);
        piechart1.setData(data);
    
    }
    @FXML
    void retourner(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/ProduitFXML.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root));
    }
}
