package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
	@FXML private TextField tf_fecha1;
	@FXML private TextField tf_fecha2;
	
	@FXML private Button bt_editar;
	@FXML private TextField tf_depto;
	
	public boolean ban;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String id = PermisoCtrl.ficha(null);
		Main.con.consulta("select t.nombre,p.plaza,c.nombre,p.jornada,c.clasificacion,d.nombre from departamento as d, trabajador as t, plaza as p, categoria as c where t.ficha="+id+" and t.id_plaza=p.id and t.id_categoria=c.id and t.clave_depto = d.clave");
		try {
			Main.con.rs.next();
			tf_ficha.setText(id);
			tf_nombre.setText(Main.con.rs.getString(1));
			tf_plazaP.setText(Main.con.rs.getString(2));
			tf_categoria.setText(Main.con.rs.getString(3));
			tf_jornada.setText(Main.con.rs.getString(4));
			tf_clasificacion.setText(Main.con.rs.getString(5));
			tf_depto.setText(Main.con.rs.getString(6));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String cla = PermisoCtrl.clausula;
		String clausula = cla.substring(cla.indexOf("-")+1);
		System.out.println(clausula);
		Main.con.consulta("select numero,descripcion from clausulas_cct where numero ="+clausula);
		try {
			Main.con.rs.next();
			tf_ausencia.setText(cla+" "+(Main.con.rs.getString(2)));
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*Este metodo es para editar los datos del trabajador de planta*/
	@FXML public void btnEditar(ActionEvent ae){
		if(ban ==  false){
			tf_ficha.setEditable(true);
			tf_nombre.setEditable(true);
			tf_ausencia.setEditable(true);
			tf_categoria.setEditable(true);
			tf_descanzo.setEditable(true);
			tf_jornada.setEditable(true);
			tf_clasificacion.setEditable(true);
			tf_plazaP.setEditable(true);
			tf_fecha1.setEditable(true);
			tf_fecha2.setEditable(true);
			bt_editar.setText("Terminar");
			ban = true;
		}
			else
			{
				ban = true;
				tf_ficha.setEditable(false);
				tf_nombre.setEditable(false);
				tf_ausencia.setEditable(false);
				tf_categoria.setEditable(false);
				tf_descanzo.setEditable(false);
				tf_jornada.setEditable(false);
				tf_clasificacion.setEditable(false);
				tf_plazaP.setEditable(false);
				tf_fecha1.setEditable(false);
				tf_fecha2.setEditable(false);
				bt_editar.setText("Editar");
				ban = false;
		}
		
	}
	
	/*Este m�todo es para mostrar los datos del trabajador de planta en formato pdf*/
	@FXML public void btnWord(ActionEvent ae){
		
	}
	
	/*inxe juana!!*/
}
