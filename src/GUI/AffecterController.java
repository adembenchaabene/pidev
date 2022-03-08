/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Livraison;
import Entites.Livreur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import services.ServiceLivraison;
import services.ServiceLivreur;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AffecterController implements Initializable {

    @FXML
    private Label affichelivraison;
    @FXML
    private ComboBox<String> livraison;
    @FXML
    private ComboBox<String> livreur;
    @FXML
    private Button affecter;
    @FXML
    private Button retourr;
    Connection cnx;
    ObservableList options = FXCollections.observableArrayList ();
    ObservableList options1 = FXCollections.observableArrayList ();
    @FXML
    private Label affliv;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private ScrollPane scroll1;
    @FXML
    private GridPane grid1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       listerliv();
       list();
       selectlivraison();
       listlivraison();
    }    

    @FXML
    private void affecterliv(ActionEvent event) {
       int idlivraison = Integer.parseInt(livraison.getValue());
       int idlivreur = Integer.parseInt(livreur.getValue());
        try {
            String req = "update livraison set id_livreur = ? , etat = ? where idLivraison = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,idlivreur); 
            ps.setInt(2,1);
            ps.setInt(3,idlivraison);
            ps.executeUpdate();
            listerliv();
            list();
            selectlivraison();
            listlivraison();
            livraison.setValue("");
            livreur.setValue("");
            Node nul1 = null;
        Notifications notificationBuilder = Notifications.create()
                .title("Success").text("ce livreur a ete affecter a cette livraison avec succès").graphic(nul1).hideAfter(javafx.util.Duration.seconds(10))
                .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("clicked ON ");
                    }
                });
       
        notificationBuilder.show();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retourner(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/Dashboard.fxml"));
    Stage window = (Stage)retourr.getScene().getWindow();
    window.setScene(new Scene(root));
    }
      public void listerliv(){
        try {
            cnx = DBConnection.getInstance().getCon();
            String req = " select * from Livreur ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
            options.add(rs.getString("IdLivreur"));
                
            }
            livreur.setItems(options);
        } catch (SQLException ex) {
            Logger.getLogger(SupprimerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public void list(){
        grid.getChildren().clear();
         ServiceLivreur sp= new ServiceLivreur();
         List<Livreur> livreurs = sp.afficher();  
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < livreurs.size(); i++) {
               FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/affichi.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                AffichiController itemController = fxmlLoader.getController();
                itemController.setData(livreurs.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid1.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid1.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid1.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid1.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid1.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid1.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid1.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
  public void selectlivraison(){
        try {
            cnx = DBConnection.getInstance().getCon();
            String req = " select * from livraison where etat = " + 0;
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options1.add(rs.getString("IdLivraison"));
            }
            livraison.setItems(options1);
        } catch (SQLException ex) {
            Logger.getLogger(SupprimerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
   public void listlivraison(){
       
   grid.getChildren().clear();
            ServiceLivraison sp= new ServiceLivraison();
         List<Livraison> livraisons = sp.afficheretat();  
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < livraisons.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/affichlivraison.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                AffichilivraisonController itemController = fxmlLoader.getController();
                itemController.setData2(livraisons.get(i));

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
