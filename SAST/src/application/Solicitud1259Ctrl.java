package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Solicitud1259Ctrl implements Initializable {
	
	@FXML private TextField tf_ficha;
	@FXML private TextField tf_fichat;
	@FXML private TextField tf_nombre;
	@FXML private TextField tf_nombret;
	@FXML private TextField tf_ausencia;
	@FXML private TextField tf_plazaP;
	@FXML private TextField tf_plazaT;
	@FXML private TextField tf_jornada;
	@FXML private TextField tf_descanzo;
	@FXML private TextField tf_categoria;
	@FXML private TextField tf_clasificacion;
	
	@FXML private ComboBox<String> cb_depto;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Para meter un list en el ComboBox con los deptos
		Main.con.consulta("select clave,nombre from departamento;");
		ObservableList<String> rows = FXCollections.observableArrayList();
		try {
			while(Main.con.rs.next()){
	             rows.add(Main.con.rs.getString(2));
			}
			cb_depto.setItems(rows);
			cb_depto.getSelectionModel().select(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String id = PermisoCtrl.ficha(null);
		System.out.print(id);
		Main.con.consulta("select t.nombre,p.plaza,c.nombre,p.jornada,c.clasificacion from trabajador as t, plaza as p, categoria as c where t.ficha="+id+" and t.id_plaza=p.id and t.id_categoria=c.id");
		try {
			Main.con.rs.next();
			tf_ficha.setText(id);
			tf_nombre.setText(Main.con.rs.getString(1));
			tf_plazaP.setText(Main.con.rs.getString(2));
			tf_categoria.setText(Main.con.rs.getString(3));
			tf_jornada.setText(Main.con.rs.getString(4));
			tf_clasificacion.setText(Main.con.rs.getString(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	/*Este metodo es para editar los datos del trabajador de planta*/
	@FXML public void btnEditar(ActionEvent ae){
		tf_ficha.setEditable(true);
		tf_nombre.setEditable(true);
		tf_ausencia.setEditable(true);
		tf_categoria.setEditable(true);
		tf_descanzo.setEditable(true);
		tf_jornada.setEditable(true);
		tf_clasificacion.setEditable(true);
		tf_plazaP.setEditable(true);
	}
	
	/*Este m�todo es para mostrar los datos del trabajador de planta en formato pdf*/
	@FXML public void btnPdf(ActionEvent ae){
		
	}
	
	/*inxe juana!!*/
}
