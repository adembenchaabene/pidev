/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Livreur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AffichiController implements Initializable {
    

    
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label num;
    @FXML
    private Label email;
    @FXML
    private Label id;
    
    @FXML
    void click(MouseEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setData(Livreur l)
     {
         id.setText(String.valueOf(l.getId()));
         prenom.setText(l.getPrenom());
         nom.setText(l.getNom());
         email.setText(l.getEmail());
         num.setText(String.valueOf(l.getNumtel()));
                 
     }
}
