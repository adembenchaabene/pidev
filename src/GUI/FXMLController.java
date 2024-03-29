/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Article;
import Entites.Salle;
import java.sql.SQLException;
import services.SalleService;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import static javafx.application.ConditionalFeature.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeRegExp.test;
import org.controlsfx.control.Notifications;
import services.ArticleService;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class FXMLController implements Initializable {

    @FXML
    private Button statS;
    @FXML
    private TextField lnom;
    @FXML
    private TextField lcapacite;
    @FXML
    private TextField limage;
    @FXML
    private TextField lprix;
    @FXML
    private Button modifierS;
    @FXML
    private Button supprimerS;
    @FXML
    private GridPane grid;
    @FXML
    private ImageView myImageView;
    
    @FXML
    private Button searchS;
    @FXML
    private TextField textR;
     SalleService sp= new SalleService();
       
     
    @FXML
    private ScrollPane scroll;
    @FXML
    private AnchorPane anchorPane;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        recherche_avance();
    }    
    public static void popAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    private boolean controleDeSaisi() {  

        if (lnom.getText().isEmpty() || lprix.getText().isEmpty() || limage.getText().isEmpty()
                || lcapacite.getText().isEmpty()) {
            popAlert(Alert.AlertType.ERROR, "Données invalides", "Verifier !", "Veuillez bien remplir tous les champs !");
            return false;
        } 
       /* else 
        {
           
         if (!Pattern.matches("[A-Za-z]*", lnom.getText())) {
                popAlert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez le nom de la salle! ");
                return false;
            }
        
        }
    */
        return true;
         
} 
   @FXML
    private void AddSalle(ActionEvent event) {
        
   
        if (controleDeSaisi() == true)
       {
            SalleService sp= new SalleService();
            Salle s = new Salle(lnom.getText(),Float.parseFloat(lprix.getText()),limage.getText(),Integer.parseInt(lcapacite.getText()));
        
            sp.ajouteer(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Salle is added successfully!");
            alert.show();
            lnom.setText("");
            lcapacite.setText("");
            lprix.setText("");
            limage.setText("");
            Node nul1 = null;
        Notifications notificationBuilder = Notifications.create()
                .title("Alert").text("Salle ajoutée avec succès").graphic(nul1).hideAfter(javafx.util.Duration.seconds(10))
                .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("clicked ON ");
                    }
                });
        rechaff();
       
        notificationBuilder.show();
        } 
        
        }
    @FXML
    private void rechSalle(ActionEvent event) throws IOException {
      List<Salle> salles= sp.rechercher(textR.getText());
      grid.getChildren().clear();
      rechaff();
    } 
    
    @FXML
    void AfficherSalle(ActionEvent event) {
    grid.getChildren().clear();
    rechaff();
    }
    
    public void rechaff()
    {
        List<Salle> salles = sp.afficher();
     int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < salles.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/AfficheSalle.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                AfficheSalleController itemController= fxmlLoader.getController();
                itemController.setData(salles.get(i));

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
    private void suppSalle(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/supprimerSalle.fxml"));
    Stage window = (Stage) supprimerS.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("supprimer salle"); 
    
     
        } 
        
    
    
    @FXML
    private void modSalle(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/ModifierSalle.fxml"));
    Stage window = (Stage) modifierS.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("modifier salle");
    }
    
    
    @FXML
    void statSalle(ActionEvent event)throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/statistiqueSalle.fxml"));
    Stage window = (Stage) statS.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("Statistique salle");
    }
    public void recherche_avance(){
        
        
        ObservableList<Salle> list=FXCollections.observableArrayList(sp.afficher());
        FilteredList<Salle> filtereddata=new FilteredList<>(list,b->true);
        int column1 = 0;
        int row1 = 1;
        try {
            for (int i = 0; i < sp.afficher().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/AfficheSalle.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                AfficheSalleController itemController= fxmlLoader.getController();
                itemController.setData(sp.afficher().get(i));

                if (column1 == 1) {
                    column1 = 0;
                    row1++;
                }

                grid.add(anchorPane, column1++, row1); //(child,column,row)
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
        
        
        textR.textProperty().addListener((observable,oldvalue,newValue) -> {
            filtereddata.setPredicate(salle->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String lowercasefilter=newValue.toLowerCase();
                if(salle.getNom().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(String.valueOf(salle.getPrix()).toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                
                
                
                else if(String.valueOf(salle.getCapacite()).toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                else{
                    return false;
                }
                
            });
            grid.getChildren().clear();
          
          //affiche.setText(bb.afficher().toString());
         
        try {
            int column = 0;
        int row = 1;
            for (int i = 0; i < filtereddata.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/AfficheSalle.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                AfficheSalleController itemController= fxmlLoader.getController();
                itemController.setData(filtereddata.get(i));

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
        
        });
        
        
        
        
    }

    @FXML
    private void addimg(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
Stage stage = (Stage)anchorPane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
 if(file != null){
            limage.setText(file.getName());
        }
    }
    
    
}
