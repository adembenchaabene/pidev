/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Livraison;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class Affichilivraison2Controller implements Initializable {

    @FXML
    private Label labeltype;
    @FXML
    private Label labeletat;
    @FXML
    private Label id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public void setData2(Livraison le)
     {  
         id.setText(String.valueOf(le.getIdLivraison()));
         labeltype.setText(le.getType());
         labeletat.setText(String.valueOf(le.getEtat()));
         if (le.getEtat() == 0)
         {
          labeletat.setText("valide");
         }
         else
         {
          labeletat.setText("En cours ");
         }
     }
}
