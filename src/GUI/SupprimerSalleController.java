/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Salle;
import services.SalleService;
import utils.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author mariem
 */
public class SupprimerSalleController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       fillComboBox();
       lists();
    }   
    @FXML
    private ComboBox<String>listId;
    @FXML
    private Button retour;   
    Connection cnx;
    ObservableList options = FXCollections.observableArrayList ();
    @FXML
    private Button suppS;
    @FXML
    private Label affiche;
    @FXML
    private GridPane grid;
    @FXML
    private ScrollPane scroll;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void goback(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/Dashboard.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root));
    }
    
    
    public void fillComboBox(){
        try {
            cnx = DBConnection.getInstance().getCon();
            String req = " select * from Salle";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options.add(rs.getString("idSalle"));    
            }
            listId.setItems(options);
        }catch (SQLException ex) {
            Logger.getLogger(SupprimerSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    @FXML
    private int getIdS(ActionEvent event) {
        int value = Integer.parseInt(listId.getValue());
        System.out.println(value);
        return value;
    }

    @FXML
    private void supprimerSalle(ActionEvent event) {
     Alert alert1 = new Alert(AlertType.CONFIRMATION);
      alert1.setTitle("Supprimer Salle");
      alert1.setHeaderText("Voulez vous supprimer cette salle ?");
      alert1.setContentText("Salle sera supprime");

      Optional<ButtonType> option = alert1.showAndWait();

     if (option.isPresent() && option.get() == ButtonType.OK)
        
     {
     int value = Integer.parseInt(listId.getValue());
     System.out.println(value);
     SalleService sp = new SalleService();
     sp.supprimerIdS(value);
     lists();
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("La salle a ete supprime");
     alert.show();
     }
    }

     @FXML
     public void lists(){
       SalleService sp= new SalleService();
       //affiche.setText(sp.afficher().toString());
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
     


}
