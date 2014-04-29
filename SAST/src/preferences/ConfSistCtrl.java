package preferences;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Peniel on 29/04/2014.
 */
public class ConfSistCtrl {
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }

    @FXML
    public void cancelarClicked(MouseEvent me) {
        Main.config.hide();
        Main.stg.toFront();
    }
}
