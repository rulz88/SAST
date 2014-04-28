package application;
//:)
import dataBase.Connector;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class Main extends Application{
	
	static Stage stg;
	static Stage permisos;
	public static Connector con;
	static Stage laborSocial;
	
    @Override 
    public void start(Stage stage) throws Exception {
    	stg = stage;
    	stg.setResizable(false);
    	stg.initStyle(StageStyle.UTILITY);
    	con = new Connector();
    	Parent root = FXMLLoader.load(getClass().getResource("Main_alt.fxml"));
        Scene scene = new Scene(root);
        stg.setScene(scene);
        stg.setTitle("Sistema de Asistencia de Secretaria de Trabajo");
        stg.getIcons().add(new Image("icons/Autocomplete16.png"));
        
        root.setOpacity(0);
        stg.show();
        
        FadeTransition ft = new FadeTransition(Duration.millis(3000), root);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
        
        permisos = new Stage();
        permisos.setResizable(false);
    	Parent rootp = FXMLLoader.load(getClass().getResource("permiso.fxml"));
        Scene scenep = new Scene(rootp);
        permisos.setScene(scenep);
        permisos.setTitle("Permiso");
        
        laborSocial = new Stage();
        laborSocial.setResizable(false);
    	Parent labor = FXMLLoader.load(getClass().getResource("..\\agenda\\Labor_social.fxml"));
        Scene scenelab = new Scene(labor);
        laborSocial.setScene(scenelab);
        laborSocial.setTitle("Labor Social");
        
        
    }
 
    public static void main(String[] args) {
        launch(args);
    }

    public Stage getStage() {
		// TODO Auto-generated method stub
		return stg; 
	}
}