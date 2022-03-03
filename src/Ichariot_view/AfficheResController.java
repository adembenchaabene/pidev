/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ichariot_view;

import Ichariot_model.Reservation;
import Ichariot_model.Salle;
import Ichariot_service.ReservationService;
import Ichariot_service.SalleService;
import Ichariot_utils.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class AfficheResController implements Initializable {
    @FXML
    private ComboBox<String>listIdR;
    Connection cnx;
    ObservableList options = FXCollections.observableArrayList ();
    
    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;
    @FXML
    private Button anuulRes;
    @FXML
    private Label dateR;
    @FXML
    private Label nbrp;
    ReservationService rs =new ReservationService();
       
     List<Reservation> res = rs.afficherres();
    @FXML
    private Button retour;
    @FXML
    private Button modifRes1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        list(res);
        fillComboBox();
    } 
    public void list(List<Reservation> res){
     grid.getChildren().clear();

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < res.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Ichariot_view/AfficherReservation.fxml"));
                AnchorPane anchorPane2 = fxmlLoader.load();

                AfficherReservationController itemController = fxmlLoader.getController();
                itemController.setData1(res.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane2, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane2, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
     public void fillComboBox(){
        try {
            cnx = DBConnection.getInstance().getCon();
            String req = " select * from Reservation";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options.add(rs.getString("idReservation"));    
            }
            listIdR.setItems(options);
        }catch (SQLException ex) {
            Logger.getLogger(AfficheResController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    
    @FXML
    private void supprimerSalle(ActionEvent event) {
     Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
      alert1.setTitle("Supprimer Salle");
      alert1.setHeaderText("Voulez vous supprimer cette salle ?");
      alert1.setContentText("Salle sera supprime");

      Optional<ButtonType> option = alert1.showAndWait();

     if (option.isPresent() && option.get() == ButtonType.OK)
        
     {
     int value = Integer.parseInt(listIdR.getValue());
     System.out.println(value);
     ReservationService sp = new ReservationService();
     sp.supprimerId(value);
     list(res);
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("La salle a ete supprime");
     alert.show();
     }
    }
   @FXML
    void goback(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/Ichariot_view/Reservation.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root));
    }
    @FXML
    void modifRes(ActionEvent event)  throws IOException{
    Parent root = FXMLLoader .load(getClass().getResource("/Ichariot_view/ModifReservation.fxml"));
    Stage window = (Stage) modifRes1.getScene().getWindow();
    window.setScene(new Scene(root));
    }
    
    
    
    }

    

