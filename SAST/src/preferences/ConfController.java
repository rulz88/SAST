package preferences;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class ConfController implements Initializable {
	
	/*variables*/
    @FXML
    TextField nombre;
    @FXML
    TextField rfc;
    @FXML
    TextField ficha;
    @FXML
    Button editar;
    @FXML
    Image foto;
    @FXML
    Button guardar;
    @FXML
    Button cancelar;
    @FXML
    TableView tabla;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
