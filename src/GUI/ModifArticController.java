/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Article;
import Entites.Etat;
import services.ArticleService;
import utils.DBConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author chaab
 */
public class ModifArticController implements Initializable {

  
    @FXML
    private ComboBox<String> list;
    Connection cnx;
    ObservableList options = FXCollections.observableArrayList ();

    @FXML
    private Button modifier;

    @FXML
    private Button retour;

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
    private AnchorPane anchorPane;

    

    @FXML
    void getvalue(ActionEvent event) {
        
         try {
            int value = Integer.parseInt(list.getValue());
            System.out.println(value);
            String req = "select * from article where idArticle  = " + value ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
            tfcontenu.setText(rs.getString("contenu"));
            tftitre.setText(rs.getString("titre"));
            tfimage.setText(rs.getString("image"));
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModifArticController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    

    @FXML
    void goback(ActionEvent event) throws IOException {
Parent root = FXMLLoader .load(getClass().getResource("/GUI/ArticFXML.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root));
    }

    @FXML
    void modifierlv(ActionEvent event) {
        
          ArticleService bb = new ArticleService();
         int value = Integer.parseInt(list.getValue());
         
        Article az =new Article(value,tfcontenu.getText(),3,tftitre.getText(),Etat.desarchive,tfimage.getText(),0);
        bb.modifer(az);
        list();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("le article a ete modifié" );
        alert.show();
        Node nul1 = null;
        Notifications notificationBuilder = Notifications.create()
                .title("Alert").text("Article modifie avec succès").graphic(nul1).hideAfter(javafx.util.Duration.seconds(10))
                .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("clicked ON ");
                    }
                });
       
        notificationBuilder.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillCombo();
        list();
    }    
     public void list(){
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
         public void fillCombo(){
       try {
            cnx = DBConnection.getInstance().getCon();
            String req = " select * from article where `Etat`='" +Etat.desarchive+ "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options.add(rs.getString("idArticle"));
                
            }
            list.setItems(options);
        } catch (SQLException ex) {
            Logger.getLogger(SupprimArtiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
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
}