/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Livreur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import services.ServiceLivreur;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ModifierController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private ComboBox<String> list;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField num;
    @FXML
    private TextField email;
    Connection cnx;
    ObservableList options = FXCollections.observableArrayList ();
    @FXML
    private Button modifier;
    @FXML
    private Label affiche;
    
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillCombo();
        list();
    }    

    @FXML
    private void retourner(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/livreur.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root));
    }

     public void fillCombo(){
        try {
            cnx = DBConnection.getInstance().getCon();
            String req = " select * from Livreur ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options.add(rs.getString("IdLivreur"));
                
            }
            list.setItems(options);
        } catch (SQLException ex) {
            Logger.getLogger(SupprimerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void getvalue(ActionEvent event) {
        try {
            int value = Integer.parseInt(list.getValue());
            System.out.println(value);
            String req = "select * from livreur where idLivreur  = " + value ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
            nom.setText(rs.getString("nomLivreur"));
            prenom.setText(rs.getString("prenom"));
            num.setText(rs.getString("numtel"));
            email.setText(rs.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifierlv(ActionEvent event) {
         if (Saisi() == true)
        { 
        ServiceLivreur sp = new ServiceLivreur();
        int value = Integer.parseInt(list.getValue());
        Livreur p = new Livreur(value,nom.getText(),prenom.getText(),Integer.parseInt(num.getText()),email.getText());
         Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
         alert1.setTitle("Confirmation Dialog");
         alert1.setContentText("Vous voulez modifier cette adresse");
         Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK){
        sp.modifier(p);
        list();
        try {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("le livreur a ete modifié" );
        alert.show();
        }
        catch (Exception ee) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.show();
        }
         }
     else {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setContentText("Modification annulle");
    alert.showAndWait();
    }
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
     public static void Alert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    private boolean Saisi() {  

        if (num.getText().isEmpty() || num.getText().isEmpty() || prenom.getText().isEmpty()
                || email.getText().isEmpty()) {
            Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", num.getText())) {
                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Vérifiez le numero de telephone! ");
                return false;
            }

           if (!Pattern.matches("[A-Za-z]*", nom.getText())) {
                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez le nom ! ");
                return false;
            }
          if (!Pattern.matches("[A-Za-z]*", prenom.getText())) {
                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez le prenom ! ");
                return false;
            }
           if (!Pattern.matches("^(.+)@(.+)$", email.getText())) {
                Alert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez l'email ! ");
                return false;
            }
           
        }
        return true;
         
    }
}
