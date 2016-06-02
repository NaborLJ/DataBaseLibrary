/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBaseLibrary;

import java.sql.*;

/**
 *
 * @author Nabor
 */

public class Metodos {
    String sql ="";
    String columna="";
   com.mysql.jdbc.Connection conn=null;
   
    /**
     * Conecta la base de datos con el programa
     * @param servidor pasamos el nombre del host donde tenemos nuestra base de datos
     * @param base el nombre de nuestra base de datos 
     * @param usuario con el que accedemos a el servidor donde esta nuestra base de datos
     * @param contraseña contraseña para dicho usuario
     * @return 
     */
    public Connection Conectar(String servidor,String base,String usuario,String contraseña){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn =(com.mysql.jdbc.Connection) DriverManager.getConnection(servidor+"/"+base+"?"+usuario+"&"+contraseña);
        }catch(Exception conectar){
            System.out.println(conectar.getMessage());
        }
        return conn; 
    }
}

