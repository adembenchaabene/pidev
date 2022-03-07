/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Adresse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AfficheadresseController implements Initializable {

    @FXML
    private Label rue;
    @FXML
    private Label num;
    @FXML
    private Label ville;
    @FXML
    private Label id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(MouseEvent event) {
    }
    
      public void setData(Adresse l)
     {   id.setText(String.valueOf(l.getId_adresse()));
         ville.setText(l.getVille());
         rue.setText(l.getRue());
         num.setText(String.valueOf(l.getNumMaison()));
         
                 
     }
}
