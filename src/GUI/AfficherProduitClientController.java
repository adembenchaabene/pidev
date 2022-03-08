/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Panier;
import Entites.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import services.panierService;

/**
 * FXML Controller class
 *
 * @author compu serv
 */
public class AfficherProduitClientController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label labelnom;

    @FXML
    private Label labeldescription;

    @FXML
    private Label labelquantite;

    @FXML
    private Label labelimage;

    @FXML
    private Label labelprix;

    
    
    public void setData2(Produit p)
   {
       labelnom.setText(p.getNomProduit());
       labeldescription.setText(p.getDescription());
       labelquantite.setText(String.valueOf(p.getQuantite()));
       labelimage.setText(p.getImage());
       labelprix.setText(String.valueOf(p.getPrix()));      
               
   }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    
   
    
}
