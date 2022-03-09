/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Produit;
import services.ProduitService;
import utils.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author compu serv
 */
public class ModifierProduitController implements Initializable {

     @Override
     public void initialize(URL url, ResourceBundle rb) {
         fillCombo();
         list();
    }   
    
   /**
     * Initializes the controller class.
     */
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private TextField Iimage1;
  
     @FXML
    private ComboBox<String> list;
     Connection cnx;
    ObservableList options = FXCollections.observableArrayList ();

  

    @FXML
    private TextField txtiquantite;

    @FXML
    private TextField txtiprix;

    @FXML
    private Button modifier;

    @FXML
    private Button retour;
    @FXML
    private TextField txtdescription;


    @FXML
    private TextField txtinom;

    Produit p=new Produit();
    List<Produit> produits = new ArrayList<>();
    @FXML
    void getvalue(ActionEvent event) {
        try {
            Connection cnx = DBConnection.getInstance().getCon();
            
            Produit p = new Produit();
            int value = Integer.parseInt(list.getValue());
            System.out.println(value);
            String req = "select * from produit where idProduit = " +value ;
            Statement ps = cnx.createStatement();
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){                      
              p.setNomProduit(rs.getString("nomProduit"));
              p.setQuantite(rs.getInt("quantite"));
              p.setPrix(rs.getInt("prix"));
              p.setDescription(rs.getString("description"));
              p.setImage(rs.getString("image"));
              p.setId_categorie(rs.getInt("id_categ"));
              produits.add(p);        
              txtinom.setText(p.getNomProduit());
              txtiquantite.setText(String.valueOf(p.getQuantite()));
            txtiprix.setText(String.valueOf(p.getPrix()));
            txtdescription.setText(p.getDescription());
            Iimage1.setText(p.getImage());
           
            }
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void modifierProduit(ActionEvent event) {
        ProduitService ps = new ProduitService();
        
        int value = Integer.parseInt((String) list.getValue());
        Produit pg = new Produit (value,txtinom.getText(),Integer.parseInt(txtiquantite.getText()),Float.parseFloat(txtiprix.getText()),produits.get(0).getId_categorie(),txtdescription.getText(),Iimage1.getText());
        
        ps.modifer(pg);
        
        list();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("le produit a ete modifié" );
        alert.show();
    }

    @FXML
    void retourner(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/Dashboard.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root));
    }
    

    private void fillCombo() {
try {
    Connection cnx = DBConnection.getInstance().getCon();
            String req = " select * from produit ";
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
      //affiche.setText(ps.afficher().toString());
          //affiche.setText(bb.afficher().toString());
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
