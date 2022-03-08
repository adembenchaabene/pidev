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
import java.sql.Statement;
import java.util.ArrayList;
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
public class ModifierCategorieController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillCombo();
         list();
    }    
    @FXML
    private ComboBox<String> list;
    Connection cnx;
    ObservableList options = FXCollections.observableArrayList ();
    @FXML
    private Button modifier;

    @FXML
    private Button retour;

    @FXML
    private TextField txtinom1;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private TextField txtidescription1;
    
    
    @FXML
    void getvalue(ActionEvent event) {
        try {
            Connection cnx = DBConnection.getInstance().getCon();
            List<Categorie> categories = new ArrayList<>();
            Categorie c = new Categorie();
            int value = Integer.parseInt(list.getValue());
            System.out.println(value);
            String req = "select * from categorie where idCateg = " +value ;
            Statement cs = cnx.createStatement();
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){                      
              c.setNomCateg(rs.getString("nomCateg"));
              c.setDescription(rs.getString("description"));
              categories.add(c);        
              txtinom1.setText(c.getNomCateg());
              txtidescription1.setText(c.getDescription());
            }
            
//            Iimage.setText(rs.getString("image
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    void modifierCategorie(ActionEvent event) {
        CategorieService cs = new CategorieService();
        int value = Integer.parseInt((String) list.getValue());
        Categorie cg = new Categorie (value,txtinom1.getText(),txtidescription1.getText());
        cs.modifer(cg);
        list();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("la categorie a ete modifié" );
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
            String req = " select * from categorie ";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){
                options.add(rs.getString("idCateg"));
                
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
    
}
