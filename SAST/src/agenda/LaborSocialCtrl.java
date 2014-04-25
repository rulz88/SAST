package agenda;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Transitorio;
import application.Main;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;


public class LaborSocialCtrl implements Initializable {
	
	@FXML TextArea ta_descripcion;
	
	@FXML TextField tf_ficha;
	@FXML TextField tf_nombre;
	
	@FXML Button btn_agregar;
	@FXML Button btn_limpiar;
	
	@FXML TableColumn<Transitorio, String> tc_num;
	@FXML TableColumn<Transitorio, String> tc_ficha;
	@FXML TableColumn<Transitorio, String> tc_nombre;
	
	@FXML TableView<Transitorio> tv_asistencia;
	
	private final ObservableList<Transitorio> data = FXCollections.observableArrayList();
	public int numero = 0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tc_num.setCellValueFactory(new PropertyValueFactory<Transitorio, String>("num"));
        tc_nombre.setCellValueFactory(new PropertyValueFactory<Transitorio, String>("nombre"));
        tc_ficha.setCellValueFactory(new PropertyValueFactory<Transitorio, String>("ficha"));
        
        tv_asistencia.setItems(data);

	}
	
	@FXML public void btnAgregar(MouseEvent me) {
		numero++;
		Integer no = new Integer(numero);
		Transitorio t = new Transitorio(
				no.toString(),
				tf_nombre.getText(),
				tf_ficha.getText()
		);
		data.add(t);
		
		tf_nombre.clear();
		tf_ficha.clear();
		btn_agregar.setDisable(true);
		System.out.println(t.getNum());
		
	}
	
	@FXML public void btnLimpiar(MouseEvent me) {
		System.out.println("limpiar");
		tf_ficha.clear();
		tf_nombre.clear();
		btn_agregar.setDisable(true);
	}
	
	@FXML public String tf_fichaAction(ActionEvent ae) {
		String id = tf_ficha.getText();
		String sql = "select * from transitorio where ficha = " + id + " ;";
		System.out.println(sql);
        Main.con.consulta(sql);
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
		// ++++++++  Implementar con 'like' ++++++++
		String id = tf_nombre.getText();
		String sql = "select * from transitorio where nombre = '" + id + "' ;";
		System.out.println(sql);
        Main.con.consulta(sql);
           try {
				Main.con.rs.next();
				tf_ficha.setText(Main.con.rs.getString(1));
			} catch (SQLException e) {
				e.printStackTrace();
			}
           return id;
	}
	
	@FXML public void generar(ActionEvent ae) {
		try {
            // 1) Load Docx file by filling Velocity template engine and cache
            // it to the registry
            InputStream in = LaborSocialCtrl.class.getResourceAsStream( "PlantillaAsistenciaLaborSocial.docx" );
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport( in, TemplateEngineKind.Velocity );

            // 2) Create fields metadata to manage lazy loop (#foreach velocity) for table row.
            FieldsMetadata metadata = report.createFieldsMetadata();
            //metadata.load( "tr", Asistencia.class, true );
            metadata.load( "tr", Transitorio.class, true );

            // 3) Create context Java model
            IContext context = report.createContext();
            context.put( "actividad", ta_descripcion.getText() );
            context.put( "tr", data );
            context.put( "secretario", "SR. MARTIN ESTEVES CASAS" );
            
            // 3.1) Create abd show a File Chooser instance to get file path
            FileChooser fileChooser = new FileChooser();
            
            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Documento de Word (*.docx)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            
            //Show save file dialog
            File file = fileChooser.showSaveDialog(new Stage());
            System.out.println(file.getPath());
            /*if(file != null){
                
            }*/

            // 4) Generate report by merging Java model with the Docx
            OutputStream out = new FileOutputStream( new File( file.getPath() + ".docx" ) );
            //OutputStream out = new FileOutputStream( file );
            report.process( context, out );

        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
        catch ( XDocReportException e ) {
            e.printStackTrace();
        }
	}

}
