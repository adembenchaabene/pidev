/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ichariot_view;

import Ichariot_model.Salle;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;




/**
 * FXML Controller class
 *
 * @author mariem
 */
public class AfficheSalleController  {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label idCap;

    @FXML
    private Label idImg;

    @FXML
    private Label idNom;

    @FXML
    private Label idPrix;
    
    /**
     * Initializes the controller class.
    **/
    
     public void setData(Salle s)
    {
        //this.Salle=s;
        idPrix.setText(String.valueOf(s.getPrix()));  
        idNom.setText(s.getNom());
        idCap.setText(String.valueOf(s.getCapacite()));  
        idImg.setText(s.getImage()); 
        /*File f = new File("C:/Users/mariem/Documents/NetBeansProjects/IchariotCopie/src/imgs" + Salle.);
         Image img = new Image(f.toURI().toString());
         idImg.setImage(img);*/
    }
    
}
