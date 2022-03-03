

package View;

import Entites.Categorie;
import Entites.Produit;
import Service.CategorieService;
import Service.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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


 
public class CategorieFXMLController implements Initializable {
  @FXML
    private TextField txtnom;

    @FXML
    private TextArea txtdescription;

    @FXML
    private Button btnajouter;

    @FXML
    private Button btnmodifier;

    @FXML
    private Button btnsupprimer;

    @FXML
    private Button btnafficher;

    @FXML
    private TextField textR;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

   
   CategorieService cs= new CategorieService();
   List<Categorie> categories =cs.afficher();
    

     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    void AjouterCategorie(ActionEvent event) {
 CategorieService cs= new CategorieService();
        Categorie m = new Categorie(txtnom.getText(),txtdescription.getText());
        cs.ajouteer(m);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Produit ajoute");
        alert.show();
        txtnom.setText("");
        grid.getChildren().clear();
        rechaff(cs.afficher());
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

        
    }
    @FXML
    private void rechCategorie(ActionEvent event) throws IOException {
       CategorieService cs = new CategorieService();
      List<Categorie> categories= cs.Rechercher(textR.getText());
      grid.getChildren().clear();
      rechaff(categories);
        
    } 
    @FXML
    void redirigersupp(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/View/SupprimerCategorie.fxml"));
    Stage window = (Stage) btnsupprimer.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("supprimer");
       
    }

    @FXML
    void redirigermod(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/View/ModifierCategorie.fxml"));
    Stage window = (Stage) btnmodifier.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("modifier");
    }
@FXML
    void AfficherCategorie(ActionEvent event) {
    grid.getChildren().clear();
    rechaff(categories);
    }
    public void rechaff(List<Categorie> categories)
    {
     int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < categories.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/View/AfficherFXML1.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                AfficherFXML1Controller itemController= fxmlLoader.getController();
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
