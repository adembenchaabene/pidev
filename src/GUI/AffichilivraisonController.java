/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Livraison;
import Entites.Livreur;
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
public class AffichilivraisonController implements Initializable {

    @FXML
    private Label type;
    @FXML
    private Label etat;
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
    
    public void setData2(Livraison l)
     {
         id.setText(String.valueOf(l.getIdLivraison()));
         type.setText(l.getType());
         if (l.getEtat() == 0)
         {
          etat.setText("valide");
         }
         
         
                 
     }
}
