/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataBase;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Peniel
 */
public class Connector {
	
	private static Connection conexion = null;
	public static ResultSet rs = null;

    //Constructor de la clase
    public Connector(){
        Conexion();
    }


    public boolean Conexion(){
        try {
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SAST", "postgres", "pandorasbox88");
            System.out.println("Conexion Creada");
            return true;
        } catch(SQLException e){
            System.out.println("SQLException: Error al conectar con la BD. " + e);
        } catch(Exception e){
        	System.out.println("Error al conectar con la BD. " + e);
        }

        return false;
    }
     
    public boolean Desconexion(){
        try {
            conexion.close();
            System.out.println("Conexion Cerrada");
            return true;
        } catch(SQLException e){
            System.out.println("SQLException: Error al desconectar con la BD. " + e);
        } catch(Exception e){
        	System.out.println("Error al desconectar con la BD. " + e);
        }

        return false;
    }
     
    public void consulta(String consultasql){
        Statement sentenciaSQL;
        try {
        	sentenciaSQL=conexion.createStatement();
            rs = sentenciaSQL.executeQuery(consultasql);
        }
        catch (SQLException sqle){
        	//System.out.println("SQLException: Error al acceder a la BD. " + sqle);
        }
        catch (Exception e){
        	//System.out.println("Error al accder a la BD. " + e);
        }
        
    }


	
    public static Connection getConexion() {
		return conexion;
	}


	private static void setConexion(Connection conexion) {
		Connector.conexion = conexion;
	}
    
}
