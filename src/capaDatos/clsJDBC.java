package capaDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;

public class clsJDBC {
    
    private String driver,url,user,password;
    private Connection con;
    private Statement sent=null;
    private PreparedStatement ps;
    
    public clsJDBC () {       
        this.driver = "org.postgresql.Driver";
        this.url = "jdbc:postgresql://localhost:5432/bdBillar";
        this.user = "postgres";
        this.password = "123456789";
        this.con = null;
    }
    
    //Conectándose a la BD
    
    public void conectar() throws Exception {       
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
        }catch (ClassNotFoundException | SQLException ex){
            throw new Exception ("Error al conectarse a la base de datos" + ex.getMessage());
        }
    }
    
    //Desconectándose de la BD.
    
    public void desconectar() throws Exception {
        try{
           con.close(); 
        } catch (SQLException ex){
            throw new Exception ("Error al desconectarse de la base de datos." + ex.getMessage());
        }
    }
    
    //Ejecutar una consulta SELECT
    
    public ResultSet consultarBD (String strSQL) throws Exception {
        ResultSet rs=null;
        try{
            conectar ();
            sent = con.createStatement();
            rs = sent.executeQuery(strSQL);
            return rs;
        }catch (Exception e){
            throw new Exception ("Error al ejecutar una consulta" + e.getMessage());
        }finally{
            if(con != null){
                desconectar();
            }
        }
    }
    
    //Ejecutar una consulta INSERT,UPDATE,DELETE
    
    public void ejecutarBD (String strSQL) throws Exception {
        ResultSet rs=null;
        try{
            conectar ();
            sent = con.createStatement();
            sent.executeUpdate(strSQL);
        }catch (Exception e){
            throw new Exception ("Error al ejecutar instrucción" + e.getMessage());
        }finally{
            if(con != null){
                desconectar();
            }
        }
    }
    
    public Connection getCon(){
        return con;
    }
    
    public void ejecutarBDTransacciones(ArrayList arregloConsulta) throws Exception{
        
        try {
            conectar();
            con.setAutoCommit(false);//Iniciar la transaccion
            sent = con.createStatement();
            for (Object consulta:arregloConsulta) {
                sent.executeUpdate((String)consulta);
                
            }
            //todas las sentencias de la transaccion
            con.commit();
            con.setAutoCommit(true); //Finaliza o desactiva el manejo de transaccion

        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al ejecutar la transaccion... ");

        }finally {
            if (con != null) {
                desconectar();
            }

        }
    }
    
}
