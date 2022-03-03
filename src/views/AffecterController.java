/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Livraison;
import entities.Livreur;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.ServiceLivraison;
import services.ServiceLivreur;
import utils.MyDB;

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
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retourner(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/views/livreur.fxml"));
    Stage window = (Stage)retourr.getScene().getWindow();
    window.setScene(new Scene(root));
    }
      public void listerliv(){
        try {
            cnx = MyDB.getInstance().getConnection();
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
      ServiceLivreur sp= new ServiceLivreur();
      affliv.setText(sp.afficher().toString());
    } 
  public void selectlivraison(){
        try {
            cnx = MyDB.getInstance().getConnection();
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
       
  
      List<Livraison> list = new ArrayList<>();
      try {
            cnx = MyDB.getInstance().getConnection();
            String req = " select * from livraison where etat = " + 0;
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                Livraison p = new Livraison();
                p.setIdLivraison(rs.getInt(1));
                p.setType(rs.getString("type"));
                p.setIdAdresse(rs.getInt("adresse"));
                p.setIdproduit(rs.getInt("id_produit"));
                p.setIdlivreur(rs.getInt("id_livreur"));
                p.setEtat(rs.getInt("etat"));
                list.add(p);
            }
             affichelivraison.setText(list.toString());
             
        } catch (SQLException ex) {
            Logger.getLogger(SupprimerController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
   
} 


}
