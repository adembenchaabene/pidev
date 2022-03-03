/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Utils.Enums.Roles;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.UserServices;

/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class LoginController implements Initializable {

    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField usernameTextFiled;
    @FXML
    private PasswordField enterPasswordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button signupButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private Label loginMessageLabel1;
    UserServices us=new UserServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginButtonOnAction(ActionEvent event) {
        if(us.checklogin(usernameTextFiled.getText(), enterPasswordField.getText())){
            if(us.findByMail(usernameTextFiled.getText()).getRole().equals(Roles.Admin)){
                try {
                    Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
                    stageclose.close();
                    Parent root=FXMLLoader.load(getClass().getResource("/controller/FXMLGSTuser.fxml"));
                    Stage stage =new Stage();
                    Scene scene = new Scene(root);
                    stage.setTitle("signup");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                System.out.println("interface client");
            }
            
        }
    }

    @FXML
    private void signupButttonOnAction(ActionEvent event) {
        try {
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/controller/FXMLsignup.fxml"));
                Stage stage =new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("signup");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void forgotpassword(ActionEvent event) {
        try {
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/controller/FXMLforgotpassword.fxml"));
                Stage stage =new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("signup");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
