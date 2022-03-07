/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Client;
import Entites.User;
import Entites.Roles;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.UserServices;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class FXMLsignupController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField pfpassword;
    @FXML
    private Label erreur_mail;
    @FXML
    private Label erreur_prenom;
    @FXML
    private Label erreur_password;
    @FXML
    private Label erreur_nom;
    @FXML
    private ImageView emailCM;
    @FXML
    private ImageView nomCM;
    @FXML
    private ImageView prenomCM;
    @FXML
    private ImageView passwordCM;
    
     private boolean verificationUserpasword;
     private boolean verificationUserEmail;
     private boolean verificationUsernom;
    private boolean verificationUserPrenom;
    UserServices us=new UserServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void testnom(KeyEvent event) {
        int nbNonChar = 0;
            for (int i = 1; i < tfnom.getText().trim().length(); i++) {
                char ch = tfnom.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && tfnom.getText().trim().length() >=3) {
            nomCM.setImage(new Image("Images/checkMark.png"));
            erreur_nom.setText("Nom valide");
            erreur_nom.setTextFill(Color.web("#15cf67"));
            verificationUsernom = true;
            } else {
              nomCM.setImage(new Image("Images/erreurcheckMark.png"));
              erreur_nom.setText("Il faut au moins 3 caracteres");
              erreur_nom.setTextFill(Color.web("#ff2121"));
              verificationUsernom = false;

            }
    }

    @FXML
    private void testprenom(KeyEvent event) {
        int nbNonChar = 0;
            for (int i = 1; i < tfprenom.getText().trim().length(); i++) {
                char ch = tfprenom.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && tfprenom.getText().trim().length() >=3) {
            prenomCM.setImage(new Image("Images/checkMark.png"));
            erreur_prenom.setText("Prenom valide");
            erreur_prenom.setTextFill(Color.web("#15cf67"));
            
            verificationUserPrenom = true;
            } else {
                prenomCM.setImage(new Image("Images/erreurcheckMark.png"));
                erreur_prenom.setText("Il faut au moins 3 caracteres");
                erreur_prenom.setTextFill(Color.web("#ff2121"));
                verificationUserPrenom = false;

            }
    }

    @FXML
    private void testmail(KeyEvent event) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (tfemail.getText() == null) {
            verificationUserEmail= false;
        }

        if (pat.matcher(tfemail.getText()).matches() == false) {
            verificationUserEmail = false;
            emailCM.setImage(new Image("Images/erreurcheckMark.png"));
            erreur_mail.setText("Veuillez verifier la forme ***@**.**");
            erreur_mail.setTextFill(Color.web("#ff2121"));
            verificationUserEmail= false;
//            

        } else {
            emailCM.setImage(new Image("Images/checkMark.png"));
             erreur_mail.setText("Mail valide");
             erreur_mail.setTextFill(Color.web("#15cf67"));
             
             verificationUserEmail = true;
        }
        
    
    }

    @FXML
    private void gotologin(ActionEvent event) {
        try {
            
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
            Stage stage =new Stage();
            
            Scene scene = new Scene(root);
            
            stage.setTitle("Fitness");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLsignupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    Client u=new Client();
    
    @FXML
    private void signin(ActionEvent event) {
        if (  verificationUserEmail 
                && verificationUserpasword  && verificationUsernom
                && verificationUserPrenom ) 
        {
            
            u.setNom(tfnom.getText());
            u.setPrenom(tfprenom.getText());
            u.setEmail(tfemail.getText());
            u.setPassword(pfpassword.getText());
            u.setRole(Roles.Client);
            us.addUser(u);
            /*
            TrayNotification tray=new TrayNotification();
            tray.setAnimationType(AnimationType.POPUP);
            tray.setTitle("User ajouté");
            tray.setMessage("Bienvenue !");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2000));*/
            
            try {
            
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();

                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
                Stage stage =new Stage();

                Scene scene = new Scene(root);

                stage.setTitle("signup");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLsignupController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }

    @FXML
    private void testpassword(KeyEvent event) {
        String PAS = pfpassword.getText().trim();

        if (PAS.length() >= 6) {
            passwordCM.setImage(new Image("Images/checkMark.png"));
            erreur_password.setText("Longeur juste");
            
             erreur_password.setTextFill(Color.web("#15cf67"));
            verificationUserpasword = true;
        }else{
        passwordCM.setImage(new Image("Images/erreurcheckMark.png"));
        verificationUserpasword = false;
        erreur_password.setTextFill(Color.web("#ff2121"));
            erreur_password.setText("Utilisez au moins six caractères");
            
        }
    
    }
    
}
