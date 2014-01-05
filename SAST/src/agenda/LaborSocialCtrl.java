package agenda;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;


public class LaborSocialCtrl implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	@FXML public void btnAgregar(MouseEvent me) {
		System.out.println("agregar");
		
	}
	
	@FXML public void btnLimpiar(MouseEvent me) {
		System.out.println("limpiar");
		
	}

}
