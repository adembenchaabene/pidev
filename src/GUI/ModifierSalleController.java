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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mariem
 */
public class ModifierSalleController implements Initializable {
    @FXML
    private TextField cap;

    @FXML
    private TextField img;
    Connection cnx;
    ObservableList options = FXCollections.observableArrayList ();
    @FXML
    private TextField nomSalle;

    @FXML
    private TextField prix;
    @FXML
    private ComboBox<String> listS;
     @FXML
    private Button retourM;
     @FXML
    private Label affiche;
    @FXML
    private GridPane grid;
     @FXML
    private ScrollPane scroll;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillCombo();
         list();
         //SalleService sp= new SalleService();
       
    
    
    }
    
       @FXML
    private void retourner(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/Dashboard.fxml"));
    Stage window = (Stage) retourM.getScene().getWindow();
    window.setScene(new Scene(root));
    }

     public void fillCombo(){
        try {
            cnx = DBConnection.getInstance().getCon();
            String req = " select * from Salle ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options.add(rs.getString("IdSalle"));  
            }
            listS.setItems(options);
        } catch (SQLException ex) {
            Logger.getLogger(SupprimerSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void getvalue(ActionEvent event)  {
        try {
            int value = Integer.parseInt(listS.getValue());
            System.out.println(value);
            String req = "select * from Salle where idSalle  = " + value ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
            img.setText(rs.getString("image"));
            nomSalle.setText(rs.getString("nom"));
            cap.setText(rs.getString("capacite"));
            prix.setText(rs.getString("prixSalle"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModifierSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifierSalle(ActionEvent event) {
      SalleService sp = new SalleService();
        int value = Integer.parseInt(listS.getValue());
        Salle sa = new Salle(value,nomSalle.getText(),Float.parseFloat(prix.getText()),img.getText(),Integer.parseInt(cap.getText()));
        sp.modifer(sa);
        list();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("la salle a ete modifié" );
        alert.show();
    }
    
     public void list(){
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
