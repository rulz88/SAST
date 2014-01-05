package agenda;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Transitorio {
	
	private final SimpleIntegerProperty no;
	private final SimpleStringProperty ficha;
	private final SimpleStringProperty nombre;
    
     
    public Transitorio(Integer no, String ficha, String nombre) {
         this.nombre = new SimpleStringProperty(nombre);
         this.ficha = new SimpleStringProperty(ficha);
         this.no = new SimpleIntegerProperty(no);
    }
    
    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    
    public String getFicha() {
        return ficha.get();
    }

    public void setFicha(String ficha) {
        this.ficha.set(ficha);
    }
    
    public Integer getNumero() {
        return no.get();
    }

    public void setNumero(Integer num) {
        no.set(num);
    }

    
}
