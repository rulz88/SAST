package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
//import java.time.LocalDate;
import java.util.ResourceBundle;

import extfx.scene.control.DatePicker;
import application.model.AutoCompleteComboBoxListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextArea;
//import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PermisoCtrl implements Initializable {
	
	@FXML static TextField tf_ficha;
	@FXML static TextField tf_trabajador;
	@FXML static TextField tf_depto;
	
	@FXML static TextArea ta_categoria;
	
	@FXML Pane pn_main;
	
	@FXML Button bt_borrar;
	
	@FXML static Label lbl_dia;
	@FXML static Label lbl_fecha2;
	@FXML static Label lbl_fecha3;
	@FXML static ImageView img_masFechas;
	
	static String clausula;
	
	static ComboBox<String> cb_clausula;
	AutoCompleteComboBoxListener aux;
	ObservableList<String> data = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		final DatePicker datePicker = new DatePicker();
        datePicker.localeProperty().set(Locale.US);
        
		/*	Combobox predictor para las clausulas mas comunes	*/
		cb_clausula = new ComboBox<String>();
		cb_clausula.setLayoutX(565);
		cb_clausula.setLayoutY(245);
		cb_clausula.setPrefHeight(21);
		cb_clausula.setPrefWidth(121);
		
		aux = new AutoCompleteComboBoxListener(cb_clausula);
		cb_clausula.setOnAction(actionButton);
		pn_main.getChildren().add(cb_clausula);
		
		/*	Seleccionador de fechas	*/
        datePicker.setLayoutX(451);
        datePicker.setLayoutY(165);
        pn_main.getChildren().add(datePicker);
        
        Main.con.consulta("select numero,descripcion from clausulas_cct;");
		try {
			while(Main.con.rs.next()){
	             data.add("CL-"+Main.con.rs.getString(1));
			}
			cb_clausula.setItems(data);
			cb_clausula.getSelectionModel().select(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private final EventHandler<ActionEvent> actionButton = new EventHandler<ActionEvent>() {
		
		@Override public void handle(ActionEvent arg0) {
			clausula = cb_clausula.getSelectionModel().selectedItemProperty().getValue();
			System.out.println("Accion del combobox " + clausula);
			
		}
	};
	
	@FXML public void generar1259(ActionEvent ae) {
		mainCtrl.loadPane("Solicitud_trabajadores.fxml");
		Main.permisos.hide();
		Main.stg.toFront();
	}
	
	@FXML public void btnCancelar(ActionEvent ae) {
		Main.permisos.hide();
		Main.stg.toFront();
	}
	
	@FXML public static String ficha(ActionEvent ae){
		String id = tf_ficha.getText();
		String clave_depto = null;
		String idCategoria = null;
		Main.con.consulta("select nombre,clave_depto, id_categoria from trabajador where ficha ="+id);
            try {
				Main.con.rs.next();
				tf_trabajador.setText(Main.con.rs.getString(1));
				clave_depto = Main.con.rs.getString(2);
	            idCategoria = Main.con.rs.getString(3);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        Main.con.consulta("select nombre from departamento where clave ="+clave_depto);
        try{
        	Main.con.rs.next();
        	tf_depto.setText(Main.con.rs.getString(1));
        }catch(SQLException e){
        	e.printStackTrace();
        }
        Main.con.consulta("select nombre from categoria where id ="+idCategoria);
        try{
        	Main.con.rs.next();
        	ta_categoria.setText(Main.con.rs.getString(1));
        }catch(SQLException e){
        	e.printStackTrace();
        }
            return id;
	}
	
	@FXML public void borrar(ActionEvent ae){
		tf_ficha.setText("");
		tf_trabajador.setText("");
		tf_depto.setText("");
		ta_categoria.setText("");
	}

}
