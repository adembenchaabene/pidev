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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
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
    public static int id_panier_produit;
    @FXML
    private Button btnafficher;

    @FXML
    private Button btnsupprimer;

    
    @FXML
    private Button retour;
    @FXML
    private GridPane pane;
    panierService ps=new panierService();
    
    @FXML
    void AfficherProduit(ActionEvent event) {
        rechaff();
    }
    public void rechaff(){
        panierService panierservice=new panierService();
        List<Panier> paniers=ps.afficher();
        pane.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < paniers.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/AfficherFXML.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Button btn = new Button();
                btn.setText("supprimer");
                btn.setLayoutX(210.0);
                btn.setLayoutY(155.0);
                btn.setPrefHeight(28.0);
                btn.setPrefWidth(175.0);
                btn.setTextFill(Paint.valueOf("#f03535"));
                Font f=new Font("System Bold", 18.0);
                btn.setFont(f);
                Panier p=paniers.get(i);
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
            
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    
                    @Override
                    public void handle(ActionEvent event) {
                        
                        panierservice.supprimer(p.getIdPanier());
                        rechaff();
                    }
                });
                Button btn2 = new Button();
                btn2.setText("livraison");
                btn2.setLayoutX(50.0);
                btn2.setLayoutY(200.0);
                btn2.setPrefHeight(28.0);
                btn2.setPrefWidth(175.0);
                btn2.setTextFill(Paint.valueOf("#f03535"));
                Font f2=new Font("System Bold", 18.0);
                btn2.setFont(f2);
                id_panier_produit=paniers.get(i).getProduit().getIdProduit();
                btn2.setOnAction(new EventHandler<ActionEvent>() {
                    
                    @Override
                    public void handle(ActionEvent event) {
                        
                        try {
                            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();

                            stageclose.close();
                            Parent root=FXMLLoader.load(getClass().getResource("/GUI/ClientAdresse.fxml"));
                            Stage stage =new Stage();

                            Scene scene = new Scene(root);

                            stage.setTitle("Adresse");
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                });
                
                
                
                anchorPane.getChildren().addAll(btn,btn2);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
                
    }
    @FXML
    void redirigersupp(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/SupprimerProduitPanier.fxml"));
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
        rechaff();
    }
    
}
