package agenda;

import javafx.beans.property.SimpleStringProperty;

public class Transitorio {
	 private final SimpleStringProperty nombre;
     private final SimpleStringProperty ficha;
     
     private Transitorio(String nombre, String ficha) {
         this.nombre = new SimpleStringProperty(nombre);
         this.ficha = new SimpleStringProperty(ficha);
     }

	public SimpleStringProperty getNombre() {
		return nombre;
	}

	public SimpleStringProperty getFicha() {
		return ficha;
	}

}
