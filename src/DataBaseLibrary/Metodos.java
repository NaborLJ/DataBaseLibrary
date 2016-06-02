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
    /**
     * Permite buscar información en la base de datos mediante consultas con select, solo necesita saber el primary key
     * @param parametro enviaremos aquí el parametro que deseemos seleccionar/buscar
     * @param nomTabla nombre de la tabla en la cual deseamos buscar el parametro
     * @param ID nombre de el campo que contenga la primary key
     * @param primaryKey primary key que posea el campo que deseemos buscar
     */
    public void buscar(String parametro,String nomTabla,String ID,String primaryKey){
        
        
            sql="Select "+parametro+" from "+nomTabla+" where "+ID+"='"+primaryKey+"'";
            
        
    }
    /**
     * Muestra la información guardada en la base de datos, solo debemos pasarle el numero de columnas/campos que posee la tabla de la base
     * @param columna 
     */
    public void showData(int columna){
        String[] data = new String[columna];
        try {
            Statement stm = (Statement) conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
             for(int x=0;x<+data.length;x++) {
                 data[x]=rs.getString(x+1);
                     System.out.println(data[x]);
}
            }
            
    }
           
         catch (SQLException show) {
            System.out.println(show.getMessage());
        }
}
   
}

