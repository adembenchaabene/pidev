/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Reservation;
import Entites.Salle;
import services.ReservationService;
import services.SalleService;
import utils.DBConnection;
import static GUI.FXMLController.popAlert;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class ReservationController implements Initializable {
    @FXML
    private GridPane grid;
    
    @FXML
    private ScrollPane scroll;
    @FXML
    private DatePicker ldate;
    @FXML
    private Button Res;
    @FXML
    private TextField nbrP;
    Connection cnx;
    ObservableList options = FXCollections.observableArrayList ();
    @FXML
    private ComboBox<String> comboSalle;
    SalleService sp= new SalleService();
     
        List<Salle> salles = sp.afficher();
    
    @FXML
    private Button afficheR;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       list(salles);
        fillCombo();
    }
    public void list(List<Salle> salles){
      
       int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < salles.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/AfficheSalle.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                AfficheSalleController itemController= fxmlLoader.getController();
                itemController.setData(salles.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
 
    @FXML
    private void AddRes(ActionEvent event) {
         
        ReservationService Res = new ReservationService();
      
        Reservation rp = new Reservation (Date.valueOf(ldate.getValue()),1,1,Integer.parseInt(nbrP.getText()));
        //if(Integer.parseInt(nbrP.getText())>s.getCapacite())
        {
            Res.ajouteer(rp);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Salle is added successfully!");
            alert.show();
            nbrP.setText("");
            ldate.getValue();
            //System.out.println(ldate.getValue());
        
       }
  
        } 
     public void fillCombo(){
        try {
            cnx = DBConnection.getInstance().getCon();
            String req = " select * from Salle ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options.add(rs.getString("nom"));  
            }
            comboSalle.setItems(options);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
      @FXML
      public void getNomSalle(ActionEvent event) {  
          
     
            List<Salle> newsalle =loadDataByname();
            grid.getChildren().clear();
            list(newsalle);
            
            
           
            
        }
      public List<Salle>  loadDataByname(){
          SalleService sp= new SalleService();
   
          String value = comboSalle.getValue();
            List<Salle> salles = sp.getSalleByName(value);
            
            System.out.println(salles);
            return salles;
          
          
      }

    @FXML
    private void rediriger(ActionEvent event)throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/AfficheRes.fxml"));
    Stage window = (Stage) afficheR.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("afficher reservation");
    }
      
      
}
        
        
        
    
    

