package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class mainCtrl implements Initializable {
	
	@FXML public static Pane pn_content;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	@FXML public void btnTareas(ActionEvent ae) {
    	Main.permisos.show();
    	Main.stg.toBack();
    	Main.permisos.toFront();
    }
	
	@FXML public void btnSalir(ActionEvent ae) {
		Platform.exit();
	}
	
	static void loadPane(final String source) {
		// TODO Auto-generated method stub
		System.out.println(source);
		final DoubleProperty opacity = pn_content.opacityProperty();

        if (!pn_content.getChildren().isEmpty()) {    //if there is more than one screen
            Timeline fade = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                    new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                	pn_content.getChildren().clear();	//remove the displayed screen
                	try {
						pn_content.getChildren().add(0, (Node)FXMLLoader.load(getClass().getResource(source)));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}     //add the screen
                    Timeline fadeIn = new Timeline(
                            new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                            new KeyFrame(new Duration(500), new KeyValue(opacity, 1.0)));
                    fadeIn.play();
                }
            }, new KeyValue(opacity, 0.0)));
            fade.play();
         }
	}

}
