/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Categorie;
import Entites.Produit;
import services.CategorieService;
import services.ProduitService;
import utils.DBConnection;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class SupprimerCategorieController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    

    @FXML
    private Button supp;

    @FXML
    private Label affichel;

    @FXML
    private Button retour;
    @FXML
    private ComboBox<String> list1;
    Connection cnx;
    ObservableList options = FXCollections.observableArrayList ();

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
    void Supprimercategorie(ActionEvent event) {
     int value = Integer.parseInt((String) list1.getValue());
     
     System.out.println(value);
     CategorieService cs = new CategorieService();
     cs.supprimerIdC(value);
     list();
     list1.getItems().clear();
     fillCombo();
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("la categorie a ete supprime");
     alert.show();
     
    }

    @FXML
    private int getname(ActionEvent event) {
        int value = Integer.parseInt((String) list1.getValue());
         System.out.println(value);
        return value;
    }

    @FXML
    void goback(ActionEvent event) throws IOException {
Parent root = FXMLLoader .load(getClass().getResource("/GUI/CategorieFXML.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root));
    }
    
   

    private void fillCombo() {
    try {
           
        cnx = DBConnection.getInstance().getCon();
            String req = " select * from Categorie ";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){
                options.add(rs.getString("idCateg"));
                
            }
            list1.setItems(options);
        } catch (SQLException ex) {
            Logger.getLogger(SupprimerCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void list() {
      grid.getChildren().clear();
      CategorieService cs= new CategorieService();
      //affichel.setText(ps.afficher().toString());
      //affiche.setText(ps.afficher().toString());
         List<Categorie> categories = cs.afficher();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < categories.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/AfficherFXML1.fxml"));
                AnchorPane anchorPane1 = fxmlLoader.load();

                AfficherFXML1Controller itemController = fxmlLoader.getController();
                itemController.setData1(categories.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane1, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane1, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
