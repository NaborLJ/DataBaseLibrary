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
     * Muestra la información buscada mediante el metodo Buscar(), solo debemos pasarle el numero de columnas/campos que posee la tabla de la base
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
    /**
     * Insiere el/los dato/datos que deseemos en la tabla.
     * @param tabla marcamos cual es la tabla en la cual deseamos insertar los datos
     * @param nomColumna pasamos las columnas que posee dicha tabla
     * @param valores damos los valores a las columnas respetando el orden de como hayamos puesto las columnas anteriormente
     */
      public void Insertar(String tabla,String nomColumna,String valores){
      
    java.sql.PreparedStatement ps;
        try {
            ps = conn.prepareStatement("Insert into "+tabla+"("+nomColumna+") values ("+valores+")");
            ps.execute();
            System.out.println("Exito en la insercción.");
            
        } catch (Exception insertar) {
            System.out.println(insertar.getMessage());
        }
    }
      
      /**
       * Borra un registro de la tabla de la base de datos
       * @param nomTabla marcamos el nombre de la tabla de la cual deseamos eliminar 
       * @param primary_Key enviamos el nombre de la columna que sea la primary key
       * @param id enviamos la primary key del registro que deseemos borrar
       */
      public void Delete(String nomTabla,String primary_Key,int id){
      
        try {
            com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("Delete from "+nomTabla+" where "+primary_Key+"='"+id+"'");
            ps.executeUpdate();
            showData(6);
            System.out.println("Borrado exitoso");
        } catch (SQLException delete) {
            System.out.println(delete.getMessage());
        }
      }
      
      /**
       * Actualiza los valores de un registro
       * @param tabla pasamos el nombre de la tabla de la cual queremos hacer la modificacion
       * @param parametrosActualizar seleccionamos la columna/as que deseemos modificar/actualizar
       * @param datosNuevos enviamos los nuevos valores de dicha/as columnas
       * @param primary_Key marcamos el nombre de la columna que contenga el primary key
       * @param id enviamos la primary key del registro que querramos modificar
       */
      public void Actualize(String tabla,String parametrosActualizar,String datosNuevos,String primary_Key,int id){
           try {
            com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("Update "+tabla+" set "+parametrosActualizar+" = '"+datosNuevos+"' where "+primary_Key+"='"+id+"'");
            ps.executeUpdate();
            showData(6);
               System.out.println("Actualización exitosa");
        } catch (SQLException actualize) {
               System.out.println(actualize.getMessage());
        }
      }
     /**
      * Cierra la conexion con la base de datos
      */
      public void CloseConnection(){
            try {
            conn.close();
        } catch (SQLException closeConnection) {
            System.out.println(closeConnection.getMessage());
        }
      }
      
}

