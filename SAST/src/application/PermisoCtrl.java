package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
//import java.time.LocalDate;
import java.util.ResourceBundle;

import extfx.scene.control.DatePicker;
import application.model.AutoCompleteComboBoxListener;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
//import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PermisoCtrl implements Initializable {
	
	@FXML
	static TextField tf_ficha;
	@FXML
	static TextField tf_trabajador;
	@FXML
	static TextField tf_depto;
	@FXML
	static TextArea ta_categoria;
	@FXML Pane pn_main;
	@FXML Button bt_borrar;
	@FXML
	static Label lbl_dia;
	@FXML
	static Label lbl_fecha2;
	@FXML
	static Label lbl_fecha3;
	@FXML
	static ImageView img_masFechas;
	
	//private LocalDate date;
	private int ficha;
	private String nombre;
	private String clausula;
	
	ComboBox cb_clausula;
	AutoCompleteComboBoxListener aux;
	ObservableList data = FXCollections.observableArrayList(
												"CL-150",
												"CL-143",
												"CL-190"
												);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		final DatePicker datePicker = new DatePicker();
        datePicker.localeProperty().set(Locale.US);
        
		/*	Combobox predictor para las clausulas mas comunes	*/
		cb_clausula = new ComboBox();
		cb_clausula.setLayoutX(565);
		cb_clausula.setLayoutY(245);
		cb_clausula.setPrefHeight(21);
		cb_clausula.setPrefWidth(121);
		cb_clausula.setItems(data);
		
		aux = new AutoCompleteComboBoxListener(cb_clausula);
		cb_clausula.setOnAction(actionButton);
		pn_main.getChildren().add(cb_clausula);
		
		/*	Seleccionador de fechas	*/
        datePicker.setLayoutX(451);
        datePicker.setLayoutY(165);
        pn_main.getChildren().add(datePicker);

	}
	
	private final EventHandler actionButton = new EventHandler() {
		@Override public void handle(Event arg0) {
			// TODO Auto-generated method stub
			System.out.println("Accion del combobox " +cb_clausula.getSelectionModel().selectedItemProperty().getValue());
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
         Main.con.consulta("select t.nombre,d.nombre,t.categoria from trabajadores as t,departamentos as d where t.ficha="+id+" and t.id_depto=d.clave;");
            try {
				Main.con.rs.next();
				tf_trabajador.setText(Main.con.rs.getString(1));
	            tf_depto.setText(Main.con.rs.getString(2));
	            ta_categoria.setText(Main.con.rs.getString(3));
			} catch (SQLException e) {
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
