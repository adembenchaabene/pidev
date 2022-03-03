/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Livreur;
import java.io.IOException;
import javax.mail.Authenticator;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceLivreur;
import java.util.Properties;  
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * FXML Controller class
 *
 * @author DELL
 */
public class LivreurController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField num;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private Label affiche;
    @FXML
    private Button supprime;
    
    Stage stage;
    Scene scene;
    @FXML
    private Button modifier;
    @FXML
    private Button affecterl;
    @FXML
    private Label errornom;
    @FXML
    private Label errorprenom;
    @FXML
    private Label errornum;
    @FXML
    private Label erroremail;
    @FXML
    private Button stat;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public static void popAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    private boolean controleDeSaisi() {  

        if (num.getText().isEmpty() || num.getText().isEmpty() || prenom.getText().isEmpty()
                || email.getText().isEmpty()) {
            popAlert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", num.getText())) {
                popAlert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Vérifiez le numero de telephone! ");
                return false;
            }

           if (!Pattern.matches("[A-Za-z]*", nom.getText())) {
                popAlert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez le nom ! ");
                return false;
            }
          if (!Pattern.matches("[A-Za-z]*", prenom.getText())) {
                popAlert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez le prenom ! ");
                return false;
            }
           if (!Pattern.matches("[A-Za-z]@[A-Za-z]$]*", email.getText())) {
                popAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez l'email ! ");
                return false;
            }
           
        }
        return true;
         
    }
    
    @FXML
    private void ajouterl(ActionEvent event) {
        if (controleDeSaisi() == true)
        {    
        ServiceLivreur sp = new ServiceLivreur();
        Livreur p = new Livreur(nom.getText(),prenom.getText(),Integer.parseInt(num.getText()),email.getText());
        sp.ajout(p);
        try {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("le livreur a ete ajoute");
        alert.show();
        }
        catch (Exception ee) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.show();
        }
    /////////email    
         // Recipient's email ID needs to be mentioned.
      String to = email.getText();//change accordingly

      // Sender's email ID needs to be mentioned
      String from = "ichariottest11@gmail.com";//change accordingly
      final String username = "ichariot test";//change accordingly
      final String password = "ichariot123456";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");
     
      // Get the Session object.
 Session session = Session.getInstance(props, new javax.mail.Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(from, password);
    }
});
      try {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
         InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("Bienvenue");

         // Now set the actual message
         message.setText("Salut et bienvenue a notre mall I-chariot "
            + "on vous souhaite que vous serez heureux avec nous ");

         // Send message
         Transport.send(message);

         System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
            throw new RuntimeException(e);
      }
        nom.setText("");
        prenom.setText("");
        num.setText(String.valueOf(""));
        email.setText("");
        }
       
    }

  
    @FXML
    private void afficherl(ActionEvent event) {
         ServiceLivreur sp= new ServiceLivreur();
        
            affiche.setText(sp.afficher().toString());
       
    }

    @FXML
    private void rederigersupp(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/views/supprimer.fxml"));
    Stage window = (Stage) supprime.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("supprimer");
       
    }

    @FXML
    private void rederigermod(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/views/modifier.fxml"));
    Stage window = (Stage) supprime.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("modifier");
    }

    @FXML
    private void redirigeraff(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/views/affecter.fxml"));
    Stage window = (Stage) supprime.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("affecter livreur");
        }

    @FXML
    private void consulterstat(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/views/static.fxml"));
    Stage window = (Stage) supprime.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("Statistique");
    }
    

           }