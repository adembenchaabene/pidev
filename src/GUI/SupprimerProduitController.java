/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entites.Produit;
import services.ProduitService;
import utils.DBConnection;
import static java.awt.PageAttributes.MediaType.C;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author compu serv
 */
public class SupprimerProduitController implements Initializable {

    
    @FXML
    private Button retour;

    @FXML
    private ComboBox<String> list;
    Connection cnx;
    ObservableList options = FXCollections.observableArrayList ();
    @FXML
    private Button supp;
     @FXML
    private Label affichel;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
       fillCombo();
        list();
    }    
    @FXML
    void Supprimerproduit(ActionEvent event) {
     int value = Integer.parseInt((String) list.getValue());
     
     System.out.println(value);
     ProduitService ps = new ProduitService();
     ps.supprimerIdP(value);
     list();
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("le produit a ete supprime");
     alert.show();
     
    }

    @FXML
    private int getname(ActionEvent event) {
        int value = Integer.parseInt((String) list.getValue());
         System.out.println(value);
        return value;
    }

    @FXML
    void goback(ActionEvent event) throws IOException {
Parent root = FXMLLoader .load(getClass().getResource("/GUI/ProduitFXML.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root));
    }
    
   

    private void fillCombo() {
    try {
           
            cnx = DBConnection.getInstance().getCon();
            String req = " select * from Produit ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options.add(rs.getString("idProduit"));
                
            }
            list.setItems(options);
        } catch (SQLException ex) {
            Logger.getLogger(SupprimerProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void list() {
      grid.getChildren().clear();
      ProduitService ps= new ProduitService();
      //affichel.setText(ps.afficher().toString());
      //affiche.setText(ps.afficher().toString());
         List<Produit> produits = ps.afficher();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/afficherFXML.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                AfficherFXMLController itemController = fxmlLoader.getController();
                itemController.setData(produits.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
