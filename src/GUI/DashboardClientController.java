/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class DashboardClientController implements Initializable {

    @FXML
    private AnchorPane root1;
    @FXML
    private Pane context;
    @FXML
    private JFXButton dtnDashBoard;

    /**
     * Initializes the controller class.
     */
    private void setUi(String location) {
        try {
            context.getChildren().clear();
            context.getChildren().add(FXMLLoader.load(this.getClass().
                    getResource("/GUI/" + location + ".fxml")));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DashBoardOnAction();
            new FadeIn(context).play();
        // TODO
    }    
    
    @FXML
    private void DashBoardOnAction() {
        setUi("Reservation");
        new FadeIn(context).play();
    }
    @FXML
    private void btnLogOut(ActionEvent event) {
        Stage stageclose = (Stage)((Node) event.getSource()).getScene().getWindow();
            stageclose.close();
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/GUI/Login.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            
            stage.show();
    }

    

    @FXML
    private void gotoReservation() {
        setUi("Reservation");
        new FadeIn(context).play();
    }

    @FXML
    private void gotoCategorie() {
        setUi("ClientCategory");
        new FadeIn(context).play();
    }

    @FXML
    private void gotoArticle() {
        setUi("commentaires");
        new FadeIn(context).play();
    }

    
}
