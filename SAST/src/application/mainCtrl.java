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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import static application.Main.stg;


public class mainCtrl implements Initializable {

	@FXML public static Pane pn_content;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * 	Botones de la Barra de Herramientas
	 * */


    @FXML public void btnInicio(MouseEvent me) {
        mainCtrl.loadPane("_home.fxml");
    }
	@FXML public void btnTareas(ActionEvent ae) {
    	Main.permisos.show();
    	stg.toBack();
    	Main.permisos.toFront();
    }
	
	@FXML public void btnAgenda(ActionEvent ae) {
    	Main.laborSocial.show();
    	stg.toBack();
    	Main.laborSocial.toFront();
    }

    @FXML public void btnPreferencias(ActionEvent ae) {
        Main.config.show();
        stg.toBack();
        Main.config.toFront();
    }
	
	@FXML public void btnSalir(ActionEvent ae) { Platform.exit(); }

    @FXML public void minimize(MouseEvent me) { stg.setIconified(true); }

    @FXML public void rightClickMenu(MouseEvent me) {
        if(me.getButton() == MouseButton.SECONDARY) {
            System.out.println(me.getButton().name());

            final ContextMenu cm = new ContextMenu();
            MenuItem cmItem1 = new MenuItem("Expedir Permiso");
            cmItem1.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    btnTareas(new ActionEvent());
                }
            });

            MenuItem cmItem2 = new MenuItem("Forma 12 59");
            cmItem2.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    mainCtrl.loadPane("Solicitud_trabajadores.fxml");
                    //Main.permisos.hide();
                    //Main.stg.toFront();
                }
            });

            //cm.getItems().add(cmItem1);
            cm.getItems().add(cmItem2);
            cm.show(pn_content,me.getScreenX(),me.getScreenY());

        }
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
