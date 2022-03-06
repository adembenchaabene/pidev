package GUI;

import Entites.Article;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ArticaffichiController {
  /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
    }    
        @FXML
    private AnchorPane anchorPane;

       @FXML
    private ImageView pic;
       


    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;
      @FXML
    void click(MouseEvent event) {

    }

     public void setData(Article article)
     {      
         nameLabel.setText(article.getContenu());
         priceLable.setText(article.getTitre());
         File f = new File("C:/Users/chaab/Desktop/3eme/java/tsti/src/GUI/design/" + article.getImage());
         Image img = new Image(f.toURI().toString());
         pic.setImage(img);
         
          
                 }
    
}
