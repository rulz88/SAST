package model;

import javafx.beans.property.SimpleStringProperty;

public class Transitorio {
	
	private final SimpleStringProperty num;
	private final SimpleStringProperty nombre;
	private final SimpleStringProperty ficha;
    
     
    public Transitorio(String no, String nombre, String ficha) {
    	 this.num = new SimpleStringProperty(no);
         this.nombre = new SimpleStringProperty(nombre);
         this.ficha = new SimpleStringProperty(ficha);
         
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
    
    public String getNum() {
        return this.num.get();
    }

    public void setNum(String num) {
        this.num.set(num);
    }

    
}
