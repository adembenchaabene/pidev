/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Modules.User;
import Utils.Enums.Roles;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import services.UserServices;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class FXMLGSTuserController implements Initializable {

    @FXML
    private ListView<User> listviewuser;
    @FXML
    private TextField tfrecherche;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private ComboBox<Roles> comborole;
    @FXML
    private PasswordField pfpassword;
    ObservableList<User> data=FXCollections.observableArrayList();
    UserServices service=UserServices.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comborole.getItems().setAll(Roles.values());
        refreshlist();
        recherche_avance();
    }    

    @FXML
    private void fillforum(MouseEvent event) {
        if(listviewuser.getSelectionModel().getSelectedItem()!=null){
            User u=service.findByMail(listviewuser.getSelectionModel().getSelectedItem().getEmail());
            tfnom.setText(u.getNom());
            tfprenom.setText(u.getPrenom());
            tfemail.setText(u.getEmail());
            comborole.setValue(u.getRole());
        }
        recherche_avance();
    }

    @FXML
    private void ajouter(ActionEvent event) {
        User u=new User();
        String errors="";
        if(tfnom.getText().trim().isEmpty()){
            errors+=("- Please enter a First Name\n");
        }
        if(tfprenom.getText().trim().isEmpty()){
           
            errors+=("- Please enter a Last Name\n");
        }
        if(tfemail.getText().trim().isEmpty()){
            errors+=("- Please enter a Email\n");
        }
        
        if(pfpassword.getText().trim().isEmpty()){
            errors+=("- Please enter a Password\n");
        }
        if(comborole.getValue()==null){
            errors+=("- Please enter a Role\n");
        }
        if(errors.length()>0){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors);
            alert.showAndWait();
        }
        else{
            u.setEmail(tfemail.getText());
        u.setNom(tfnom.getText());
        u.setPrenom(tfprenom.getText());
        u.setPassword(pfpassword.getText());
        u.setRole(comborole.getValue());
        service.addUser(u);
        tfnom.setText("");
            tfprenom.setText("");
            tfemail.setText("");
            pfpassword.setText("");
        refreshlist();
        recherche_avance();
        TrayNotification tray=new TrayNotification();
            tray.setAnimationType(AnimationType.POPUP);
            tray.setTitle("Ajout avec succes");
            tray.setMessage("User a ete ajouter");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2000));
        }
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        User u=new User();
        String errors="";
        if(tfnom.getText().trim().isEmpty()){
            errors+=("- Please enter a First Name\n");
        }
        if(tfprenom.getText().trim().isEmpty()){
           
            errors+=("- Please enter a Last Name\n");
        }
        if(tfemail.getText().trim().isEmpty()){
            errors+=("- Please enter a Email\n");
        }
        
        if(pfpassword.getText().trim().isEmpty()){
            errors+=("- Please enter a Password\n");
        }
        if(comborole.getValue()==null){
            errors+=("- Please enter a Role\n");
        }
        if(errors.length()>0){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors);
            alert.showAndWait();
        }
        else{
            u.setEmail(tfemail.getText());
        u.setNom(tfnom.getText());
        u.setPrenom(tfprenom.getText());
        u.setPassword(pfpassword.getText());
        u.setRole(comborole.getValue());
        if(listviewuser.getSelectionModel().getSelectedItem()!=null){
            service.updateUser(listviewuser.getSelectionModel().getSelectedItem().getIdUser(),u);
        }
        
        refreshlist();
        recherche_avance();
        TrayNotification tray=new TrayNotification();
            tray.setAnimationType(AnimationType.POPUP);
            tray.setTitle("Modification avec succes");
            tray.setMessage("User a ete bien modifier");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2000));
        }
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        if(listviewuser.getSelectionModel().getSelectedItem()!=null){
            User u=service.findByMail(listviewuser.getSelectionModel().getSelectedItem().getEmail());
            service.deleteUser(u);
            refreshlist();
        }
        recherche_avance();
        TrayNotification tray=new TrayNotification();
            tray.setAnimationType(AnimationType.POPUP);
            tray.setTitle("supprission avec succes");
            tray.setMessage("User a ete supprimer");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2000));
    }

    @FXML
    private void tri(ActionEvent event) {
        try {
            data=FXCollections.observableArrayList(service.sortedbyId());
            listviewuser.setItems(data);
            recherche_avance();
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLGSTuserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void refreshlist(){
        data=FXCollections.observableArrayList(service.getAll());
        listviewuser.setItems(data);
        
    }
    public void recherche_avance(){
        FilteredList<User> filtereddata=new FilteredList<>(data,b->true);
        tfrecherche.textProperty().addListener((observable,oldvalue,newValue) -> {
            filtereddata.setPredicate(user->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String lowercasefilter=newValue.toLowerCase();
                if(user.getNom().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(user.getPrenom().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                else if(user.getEmail().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                else if(user.getRole().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                
                else{
                    return false;
                }
                
            });
        });
        
        listviewuser.setItems(filtereddata);
        
    }
   
    
}
