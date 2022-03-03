/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Entites.Produit;
import Service.ProduitService;
import doryan.windowsnotificationapi.fr.Notification;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author compu serv
 */
public class ProduitFXMLController implements Initializable {

   @FXML
    private TextField txtnom;

    @FXML
    private TextField txtprix;

    @FXML
    private Button btnajouter;

    @FXML
    private TextField Iimage;

    @FXML
    private TextArea txtdescription;
    
    @FXML
    private TextField txtquantite;
   
    @FXML
    private Button btnsupprimer;

    @FXML
    private Button btnmodifier;
    @FXML
    private TextField affiche;
   @FXML
    private TextField textR;
   @FXML
    private Button btnpanier;
     @FXML
    private Button btnafficher;
       @FXML
    private GridPane grid;
       @FXML
    private ScrollPane scroll;

   @FXML
    private Button btnstat;

       
   ProduitService ps= new ProduitService();
   List<Produit> produits =ps.afficher();
   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    void AjouterProduit(ActionEvent event) {
            if (controleDeSaisi() == true)
    {
 ProduitService ps= new ProduitService();
 grid.getChildren().clear();
        Produit p = new Produit(txtnom.getText(),Integer.parseInt(txtquantite.getText()),Float.parseFloat(txtprix.getText()),5,txtdescription.getText(),Iimage.getText());
        ps.ajouteer(p);
        txtnom.setText("");
        txtquantite.setText("");
        txtprix.setText("");
        txtdescription.setText("");
        Iimage.setText("");
        Node nul1 = null;
        Notifications notificationBuilder = Notifications.create()
                .title("Alert").text("produit ajouté avec succès").graphic(nul1).hideAfter(javafx.util.Duration.seconds(10))
                .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("clicked ON ");
                    }
                });
       
        notificationBuilder.show();
        try {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("le livreur a ete ajoute");
        alert.show();
        
        }
        catch (Exception ee) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.show();
        }
        
        

    }
}
    private boolean controleDeSaisi() {  

        if (txtquantite.getText().isEmpty() || txtnom.getText().isEmpty() || txtprix.getText().isEmpty()
                || txtdescription.getText().isEmpty()) {
            popAlert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9]", txtquantite.getText())) {
                popAlert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Vérifiez la quantite ");
                return false;
            }

           if (!Pattern.matches("[A-Za-z]*", txtnom.getText())) {
                popAlert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez le nom ! ");
                return false;
            }
         
        }
        return true;
         
    }
    public static void popAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    @FXML
    void redirigersupp(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/View/SupprimerProduit.fxml"));
    grid.getChildren().clear();
    Stage window = (Stage) btnsupprimer.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("supprimer");
       
    }

    @FXML
    void redirigermod(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/View/ModifierProduit.fxml"));
    grid.getChildren().clear();
    Stage window = (Stage) btnmodifier.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("modifier");
    }
     @FXML
    void RedirigerStatProduit(ActionEvent event) throws IOException {
   Parent root = FXMLLoader .load(getClass().getResource("/View/StatistiqueProduit.fxml"));
    grid.getChildren().clear();
    Stage window = (Stage) btnstat.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("Statistique");
    }
   @FXML
    private void rechProduit(ActionEvent event) throws IOException {
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
                fxmlLoader.setLocation(getClass().getResource("/View/AfficherFXML.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                AfficherFXMLController itemController= fxmlLoader.getController();
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
