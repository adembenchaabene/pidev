/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Entites.Categorie;
import Entites.Produit;
import Service.CategorieService;
import Service.ProduitService;
import Utils.DBconnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author compu serv
 */
public class ClientController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private ComboBox<?> list;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private TextField textR;

    @FXML
    private Button btnafficher;

    @FXML
    private Button btnpanier;
    ProduitService ps= new ProduitService();
   List<Produit> produits =ps.afficher();
   
     

    @FXML
    void getvalue(ActionEvent event) {
try {
            Connection cnx = DBconnection.getInstance().getCon();
            List<Categorie> categories = new ArrayList<>();
            Categorie c = new Categorie();
            int value = Integer.parseInt((String) list.getValue());
            System.out.println(value);
            String req = "select * from categorie where idCateg = " +value ;
            Statement cs = cnx.createStatement();
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){                      
              c.setNomCateg(rs.getString("nomCateg"));
              c.setDescription(rs.getString("description"));
              categories.add(c);        
              //txtinom1.setText(c.getNomCateg());
              //txtidescription1.setText(c.getDescription());
            }
            
//            Iimage.setText(rs.getString("image
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void fillCombo() {
try {
    Connection cnx = DBconnection.getInstance().getCon();
            String req = " select * from categorie ";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){
           //     options.add(rs.getString("idCateg"));
                
            }
        //    list.setItems(options);
        } catch (SQLException ex) {
            Logger.getLogger(SupprimerCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void list() {
      grid.getChildren().clear();
      CategorieService cs= new CategorieService();
      //affiche.setText(ps.afficher().toString());
          //affiche.setText(bb.afficher().toString());
         List<Categorie> categories = cs.afficher();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < categories.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/afficherFXML1.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                AfficherFXML1Controller itemController = fxmlLoader.getController();
                itemController.setData1(categories.get(i));

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

    @FXML
    void rechProduit(ActionEvent event) {
List<Produit> produits= ps.rechercher(textR.getText());
      grid.getChildren().clear();
      rechaff(produits);
    }

    @FXML
    void redirigerpanier(ActionEvent event) throws IOException {
Parent root = FXMLLoader .load(getClass().getResource("/View/PanierFXML.fxml"));
    Stage window = (Stage) btnpanier.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("Panier");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
    void AfficherProduit(ActionEvent event) {
    grid.getChildren().clear();
    rechaff(produits);
    }
    public void rechaff(List<Produit> produits)
    {
     int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/View/AfficherProduitClient.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                AfficherProduitClientController itemController= fxmlLoader.getController();
                itemController.setData2(produits.get(i));

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
