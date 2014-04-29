package application;
//:)
import dataBase.Connector;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class Main extends Application{

	public static Stage stg;
	static Stage permisos;
    public static Stage config;
	public static Connector con;
	static Stage laborSocial;

    private double xOffset = 0.0;
    private double yOffset = 0.0;


    @Override 
    public void start(Stage stage) throws Exception {
    	stg = stage;
    	stg.setResizable(false);
    	stg.initStyle(StageStyle.UNDECORATED);
    	con = new Connector();
    	Parent root = FXMLLoader.load(getClass().getResource("Main_alt.fxml"));
        Scene scene = new Scene(root);
        stg.setScene(scene);
        stg.setTitle("Sistema de Asistencia de Secretaria de Trabajo");
        stg.getIcons().add(new Image("icons/Autocomplete16.png"));

        // Listeners to handle mouse dragging
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stg.setX(event.getScreenX() - xOffset);
                stg.setY(event.getScreenY() - yOffset);
            }
        });

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

        config = new Stage();
        config.setResizable(false);
        Parent conf = FXMLLoader.load(getClass().getResource("..\\preferences\\ConfSistema.fxml"));
        Scene sceneconf = new Scene(conf);
        config.setScene(sceneconf);
        config.setTitle("Preferencias");
        
        
    }
 
    public static void main(String[] args) {
        launch(args);
    }

    public Stage getStage() {
		// TODO Auto-generated method stub
		return stg; 
	}
}