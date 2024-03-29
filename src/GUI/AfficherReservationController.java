/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Reservation;
import Entites.Salle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import services.SalleService;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class AfficherReservationController{
@FXML
    private Label idCap;

    @FXML
    private Label idImg;

    @FXML
    private Label idNom;

    @FXML
    private Label idPrix;

    @FXML
    private Label iddate;

    @FXML
    private Label nbrp;
    @FXML
    private Label idRes;
    @FXML
    private AnchorPane anchorPane2;
    @FXML
    private Label labelnomsalle;
    /**
     * Initializes the controller class.
     */
SalleService ss=new SalleService();
    public void setData1(Reservation r)
    {
 
        idRes.setText(String.valueOf(r.getIdReservation()));
        nbrp.setText(String.valueOf(r.getNbrP()));
        iddate.setText(r.getDate().toString());
        labelnomsalle.setText(ss.findById(r.getId_salle()).getNom());
        
    }
    
}
