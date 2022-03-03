/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Entites.Categorie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author compu serv
 */
public class AfficherFXML1Controller {

    /**
     * Initializes the controller class.
     */
     @FXML
    private AnchorPane anchorPane1;

    @FXML
    private Label labelnom1;

    @FXML
    private Label labeldescription1;
    
    
  
    
    public void setData1(Categorie c)
   {
       labelnom1.setText(c.getNomCateg());
       labeldescription1.setText(c.getDescription());
            
               
   }
   
    
}
