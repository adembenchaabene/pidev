/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void DashBoardOnAction(ActionEvent event) {
    }

    @FXML
    private void gotoGestionUtilisateur(ActionEvent event) {
    }

    @FXML
    private void gotoGestionArticle(ActionEvent event) {
    }

    @FXML
    private void gotoGestionSalle(ActionEvent event) {
    }

    @FXML
    private void gotoGestionProduit(ActionEvent event) {
    }

    @FXML
    private void gotoGestionLireur(ActionEvent event) {
    }

    @FXML
    private void gotoGestionCategorie(ActionEvent event) {
    }

    @FXML
    private void btnLogOut(ActionEvent event) {
    }
    
}
