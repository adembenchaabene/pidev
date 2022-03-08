/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Panier;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import services.panierService;

/**
 * FXML Controller class
 *
 * @author compu serv
 */
public class PanierFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btnafficher;

    @FXML
    private Button btnsupprimer;

    
    @FXML
    private Button retour;
    @FXML
    private GridPane pane;
    panierService ps=new panierService();
    List<Panier> paniers=ps.afficher();
    @FXML
    void AfficherProduit(ActionEvent event) {
        pane.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < paniers.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/AfficherFXML.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                AfficherFXMLController itemController= fxmlLoader.getController();
                itemController.setData(paniers.get(i).getProduit());

                if (column == 1) {
                    column = 0;
                    row++;
                }

                pane.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                pane.setMinWidth(Region.USE_COMPUTED_SIZE);
                pane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                pane.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                pane.setMinHeight(Region.USE_COMPUTED_SIZE);
                pane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                pane.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void rechaff(){
        
    }
    @FXML
    void redirigersupp(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/SupprimerProduit.fxml"));
    Stage window = (Stage) btnsupprimer.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("supprimer");
    }

    
    @FXML
    void retourner(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/DashboardClient.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
