/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Article;
import Entites.Etat;
import services.ArticleService;
import static java.awt.SystemColor.window;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author chaab
 */
public class ArticFXMLController implements Initializable {
    
   
    @FXML
    private Button modifi;

    @FXML
    private Button supprime;

       @FXML
    private TextArea tfcontenu;
  
    @FXML
    private TextField tfimage;

    @FXML
    private TextField tftitre;
    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;
    
    @FXML
    private TextField textR;
    @FXML
    private Button stat;
    @FXML
    private AnchorPane anchorPane;

    
    
 
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        recherche_avance();
    }    
      @FXML
    void addArticle(ActionEvent event) {
        ArticleService bb = new ArticleService();
        //Personne p = new Personne(lnom.getText(),lprenom.getText());
        Article ah =new Article(tfcontenu.getText(),LoginController.idglobal,tftitre.getText(),Etat.desarchive,tfimage.getText(),0);
        if(("".equals(tfcontenu.getText()))||("".equals(tftitre.getText()))||("".equals(tfimage.getText())))
        {
        Alert alert1 = new Alert(Alert.AlertType.WARNING);
        alert1.setTitle("Warning");
        alert1.setContentText("Please add informations");
        alert1.show();
        }
    
        else {
       
         bb.ajouteer(ah);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Article is added successfully!");
        alert.show();
        tfcontenu.setText("");
        tftitre.setText("");
        tfimage.setText("");
        
        
        
        
        }
 
    }
       @FXML
    void afficherl(ActionEvent event) {
          grid.getChildren().clear();
          ArticleService bb = new ArticleService();
          //affiche.setText(bb.afficher().toString());
         List<Article> articles = bb.afficher();  
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < articles.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/articaffichi.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ArticaffichiController itemController = fxmlLoader.getController();
                itemController.setData(articles.get(i));

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
    void pagesupp(ActionEvent event) throws IOException  {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/supprimArti.fxml"));
    Stage window = (Stage) supprime.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("supprimer article");

    }
      @FXML
    void pagemodif(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/modifArtic.fxml"));
    Stage window = (Stage) modifi.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("Modifier Article");
 
        
    }
        @FXML
    void rechArticle(ActionEvent event) {
        grid.getChildren().clear();
        ArticleService bb = new ArticleService();
        String t= textR.getText();
        //affiche.setText(bb.recherche(t).toString()); 
        //sp.rechercher(t);
        //System.out.println(bb.recherche(t));
          List<Article> articles = bb.recherche(t);  
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < articles.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/articaffichi.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ArticaffichiController itemController = fxmlLoader.getController();
                itemController.setData(articles.get(i));

                if (column == 3) {
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
    private void stati(ActionEvent event) throws IOException {
        Parent root = FXMLLoader .load(getClass().getResource("/GUI/stats.fxml"));
        Stage window = (Stage) stat.getScene().getWindow();
        window.setScene(new Scene(root));
        window.setTitle("Modifier Article");
 
    }

    @FXML
    private void addimage(ActionEvent event) {
    
   FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
Stage stage = (Stage)anchorPane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
 if(file != null){
            tfimage.setText(file.getName());
        }
    }
    public void recherche_avance(){
        
        ArticleService bb = new ArticleService();
        ObservableList<Article> list=FXCollections.observableArrayList(bb.afficher());
        FilteredList<Article> filtereddata=new FilteredList<>(list,b->true);
        int column1 = 0;
        int row1 = 1;
        try {
            for (int i = 0; i < filtereddata.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/articaffichi.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ArticaffichiController itemController = fxmlLoader.getController();
                itemController.setData(filtereddata.get(i));

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
            filtereddata.setPredicate(article->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String lowercasefilter=newValue.toLowerCase();
                if(article.getContenu().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(article.getTitre().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                else if(article.getImage().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                
                else if(String.valueOf(article.getNbrreact()).toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                else{
                    return false;
                }
                
            });
            grid.getChildren().clear();
          
          //affiche.setText(bb.afficher().toString());
         
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < filtereddata.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/articaffichi.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ArticaffichiController itemController = fxmlLoader.getController();
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
   
}
   


