/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Categorie;
import Entites.Panier;
import Entites.Produit;
import Entites.User;
import services.CategorieService;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import services.UserServices;
import services.panierService;
import utils.Mailapi;

/**
 * FXML Controller class
 *
 * @author compu serv
 */
public class ClientCategorieController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private ComboBox<String> list;

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
    ObservableList<String> options=FXCollections.observableArrayList();
     

    @FXML
    void getvalue(ActionEvent event) {
try {
            Connection cnx = DBConnection.getInstance().getCon();
            List<Categorie> categories = new ArrayList<>();
            Categorie c = new Categorie();
            String value = list.getValue();
            System.out.println(value);
            String req = "select * from categorie where nomCateg = " +value ;
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
    Connection cnx = DBConnection.getInstance().getCon();
            String req = " select * from categorie ";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){
                options.add(rs.getString("nomCateg"));
                
            }
            list.setItems(options);
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
                fxmlLoader.setLocation(getClass().getResource("/GUI/afficherFXML1.fxml"));
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
Parent root = FXMLLoader .load(getClass().getResource("/GUI/PanierFXML.fxml"));
    Stage window = (Stage) btnpanier.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("Panier");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillCombo();
        rechaff(produits);
    } 
    @FXML
    void AfficherProduit(ActionEvent event) {
    grid.getChildren().clear();
    rechaff(produits);
    }
    public void rechaff(List<Produit> produits)
    {
        UserServices us=new UserServices();
        User u=us.findById(LoginController.idglobal);
        panierService panierservice=new panierService();
        Panier panier=new Panier();
     int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < produits.size(); i++) {
                panier.setProduit(produits.get(i));
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/AfficherProduitClient.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Button btn = new Button();
                btn.setText("Ajouter au panier");
                btn.setLayoutX(210.0);
                btn.setLayoutY(155.0);
                btn.setPrefHeight(28.0);
                btn.setPrefWidth(175.0);
                btn.setTextFill(Paint.valueOf("#f03535"));
                Font f=new Font("System Bold", 18.0);
                btn.setFont(f);
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    
                    @Override
                    public void handle(ActionEvent event) {
                        Mailapi.send("ichariottest11@gmail.com", "ichariot123456", u.getEmail(), "Nouveau Produit", "Produit ajouter au panier");
                        panierservice.ajouteer(panier);
                    }
                });
                
                
                AfficherProduitClientController itemController= fxmlLoader.getController();
                itemController.setData2(produits.get(i));
                anchorPane.getChildren().add(btn);
               
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
