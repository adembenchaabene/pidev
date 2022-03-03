/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Modules.User;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserServices;
import utils.Mailapi;

/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class FXMLforgotpasswordController implements Initializable {

    @FXML
    private TextField tfemail_tel;
    @FXML
    private PasswordField pfnew_password;
    @FXML
    private PasswordField pfconfirm;
    @FXML
    private Button btupdate;
    @FXML
    private Button btsearch;
    @FXML
    private TextField tfverificationcode;
    UserServices us=new UserServices();
    int n;
    User u =new User();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btupdate.setVisible(false);
        pfconfirm.setVisible(false);
        pfnew_password.setVisible(false);
        tfverificationcode.setVisible(false);
        Random rand =new Random();
        n=rand.nextInt(99999);
    }    

    @FXML
    private void update(ActionEvent event) {
        System.out.println(u);
        if(tfverificationcode.getText().equals(String.valueOf(n)) && pfconfirm.getText().equals(pfnew_password.getText())){
            u.setPassword(pfnew_password.getText());
            us.updateUser(u.getIdUser(), u);
            try {
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("/controller/Login.fxml"));
            Stage stage =new Stage();
            
            Scene scene = new Scene(root);
            
            stage.setTitle("signup");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLforgotpasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }


    @FXML
    private void search(ActionEvent event) {
        
        if(us.findByMail(tfemail_tel.getText())!=null){
            u=us.findByMail(tfemail_tel.getText());
            Mailapi.send("ichariot.chariot@gmail.com", "ichariot2022", u.getEmail(), "Forgot password", "This is your code for updating your password: "+n);
            tfemail_tel.setVisible(false);
            btsearch.setVisible(false);
            btupdate.setVisible(true);
            pfconfirm.setVisible(true);
            pfnew_password.setVisible(true);
            tfverificationcode.setVisible(true);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid email");
            alert.setContentText("Email doesn't exist");
            alert.showAndWait();
        }
        
        
    }
    
}
