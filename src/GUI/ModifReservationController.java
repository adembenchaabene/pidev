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
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ModifReservationController implements Initializable {
 @FXML
    private GridPane grid;

    @FXML
    private ComboBox<?> listIdR;


    @FXML
    private ScrollPane scroll;
    Connection cnx;
    ObservableList options = FXCollections.observableArrayList ();
     ReservationService rs =new ReservationService();
       
     List<Reservation> res = rs.afficherres(LoginController.idglobal);
    @FXML
    private Button btnmodif;
    @FXML
    private TextField nbrp;
    @FXML
    private TextField dateR;
    @FXML
    private Button retour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillCombo();
        list(res);
    }
 public void fillCombo(){
        try {
            cnx = DBConnection.getInstance().getCon();
            String req = " select * from Reservation ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options.add(rs.getString("IdReservation"));  
            }
            listIdR.setItems(options);
        } catch (SQLException ex) {
            Logger.getLogger(ModifReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

       
    public void list(List<Reservation> res){
     grid.getChildren().clear();

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < res.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/AfficherReservation.fxml"));
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
@FXML
    private void getvalue(ActionEvent event)  {
        try {
            int value = Integer.parseInt((String) listIdR.getValue());
            System.out.println(value);
            String req = "select * from Reservation where idReservation  = " + value ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
            dateR.setText(rs.getDate("date").toString());
            
            nbrp.setText(rs.getString("nbrP"));
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModifierSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modif(ActionEvent event) {
        ReservationService rs =new ReservationService();
        int value = Integer.parseInt((String) listIdR.getValue());
        Reservation sa=rs.findById(value);
     sa = new Reservation(value, Date.valueOf(dateR.getText()), LoginController.idglobal, sa.getId_salle(), Integer.parseInt(nbrp.getText()));
        rs.modifer(sa);
        list(res);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("la salle a ete modifié" );
        alert.show();
    }

    @FXML
    private void goback(ActionEvent event)throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/AfficheRes.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root));
    }
    

    
}
