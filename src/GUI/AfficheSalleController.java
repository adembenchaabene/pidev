/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Salle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
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

    private Label idImg;

    @FXML
    private Label idNom;

    @FXML
    private Label idPrix;
    @FXML
    private ImageView img;
    
    /**
     * Initializes the controller class.
    **/
    
     public void setData(Salle s)
    {
        //this.Salle=s;
        idPrix.setText(String.valueOf(s.getPrix()));  
        idNom.setText(s.getNom());
        idCap.setText(String.valueOf(s.getCapacite()));  
        
        File f = new File("C:/Users/mariem/Documents/NetBeansProjects/IchariotCopie/src/imgs/" + s.getImage());
         Image img1 = new Image(f.toURI().toString());
         img.setImage(img1);
    }
    
}
