/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ichariot_view;


import Ichariot_model.Commentaire;
import Ichariot_service.MyListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author chaab
 */
public class GraphcommentController  {

    @FXML
    private AnchorPane anchorPane2;
    @FXML
    private Label contenuCommentaire;
    @FXML
    private Label datepub;
    @FXML
    private Label getCmtid;
    
      private Commentaire commentaire;
    private MyListener myListener;
     
    

    /**
     * Initializes the controller class.
     */
      
    public void setData3(Commentaire commentaire,MyListener myListener)
     {      
         this.commentaire=commentaire;
         this.myListener=myListener;
         
         contenuCommentaire.setText(commentaire.getContenu_c());
         datepub.setText(commentaire.getDatepub().toString());
         getCmtid.setText(String.valueOf(commentaire.getIdCommentaire()));
         
          
                 }
        @FXML
    void selectedCmt(MouseEvent event) {
        myListener.onClickListener2(commentaire);

    }

    
}
