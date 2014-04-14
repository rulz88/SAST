package agenda;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class LaborSocialCtrl implements Initializable {
	
	@FXML TextArea ta_descripcion;
	
	@FXML TextField tf_ficha;
	@FXML TextField tf_nombre;
	
	@FXML Button btn_agregar;
	@FXML Button btn_limpiar;
	
	@FXML TableColumn<Transitorio, Integer> tc_num;
	@FXML TableColumn<Transitorio, String> tc_ficha;
	@FXML TableColumn<Transitorio, String> tc_nombre;
	
	@FXML TableView<Transitorio> tv_asistencia;
	
	private final ObservableList<Transitorio> data = FXCollections.observableArrayList();
	public int numero = 0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tc_num.setCellValueFactory(new PropertyValueFactory<Transitorio, Integer>("no"));
		tc_ficha.setCellValueFactory(new PropertyValueFactory<Transitorio, String>("ficha"));
        tc_nombre.setCellValueFactory(new PropertyValueFactory<Transitorio, String>("nombre"));
        
        tv_asistencia.setItems(data);

	}
	
	@FXML public void btnAgregar(MouseEvent me) {
		numero++;
		Transitorio t = new Transitorio(
				new Integer(numero),
				tf_ficha.getText(),
				tf_nombre.getText()
		);
		data.add(t);
		
		tf_ficha.clear();
		tf_nombre.clear();
		btn_agregar.setDisable(true);
		System.out.println(t.getNumero());
		
	}
	
	@FXML public void btnLimpiar(MouseEvent me) {
		System.out.println("limpiar");
		tf_ficha.clear();
		tf_nombre.clear();
		btn_agregar.setDisable(true);
	}
	
	@FXML public String tf_fichaAction(ActionEvent ae) {
		String id = tf_ficha.getText();
        Main.con.consulta("select * from transitorios where ficha = " + id + " ;");
           try {
				Main.con.rs.next();
				tf_nombre.setText(Main.con.rs.getString(2));
				btn_agregar.setDisable(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
           return id;
	}
	
	@FXML public String tf_NombreAction(ActionEvent ae) {
		String id = tf_nombre.getText();
        Main.con.consulta("select * from transitorios where nombre = '" + id + "' ;");
           try {
				Main.con.rs.next();
				tf_ficha.setText(Main.con.rs.getString(1));
			} catch (SQLException e) {
				e.printStackTrace();
			}
           return id;
	}

}
