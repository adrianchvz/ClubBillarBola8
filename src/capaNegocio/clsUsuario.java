package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.ResultSet;

public class clsUsuario {
    
    clsJDBC objConectar = new clsJDBC(); //Creamos un objeto para conectar a la BD.
    String strSQL;
    ResultSet rs = null;
    
    public String login (String usu, String con) throws Exception{
        
        strSQL = "select nombresusuario from usuario where usuario='" + usu + "' and contraseña='" + con + "'";
        
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()){
                return rs.getString("nombresusuario");
            }           
        }catch (Exception e){
            throw new Exception ("Error al iniciar sesión" + e.getMessage());
        }
        return "";    
    }
    
    public ResultSet buscarDatos(Integer id) throws Exception {
        strSQL = "select usuario, contraseña, nombresusuario, cargousuario from usuario where idusuario=" + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al mostrar los datos del usuario - " + e.getMessage());
        }
    }

    public ResultSet listarUsuarios () throws Exception{
        strSQL = "select * from usuario ORDER BY idusuario ASC";
        try{
            rs = objConectar.consultarBD(strSQL);
            return rs;
        }catch (Exception e){
            throw new Exception("Error al buscar usuario");
        }
    }
    
    public Integer generarCodigoUsuario() throws Exception{
        strSQL = "select coalesce (max(idusuario),0)+1 as codigo from usuario";
        try{
            rs=objConectar.consultarBD(strSQL);
            while (rs.next()){
                return rs.getInt("codigo");
            }
        }catch (Exception e){
            throw new Exception ("Error al generar código de usuario");
        }
        return 0;
    }
    
    public void registrarUsuario (Integer id, String usu, String con, String nom, String car) throws Exception{
        strSQL = "insert into usuario (usuario,contraseña,nombresusuario,cargousuario) values "
                + "('" + usu +"','" + con +"','" + nom +"','" + car +"')";
        try{
            objConectar.ejecutarBD(strSQL);
        }catch (Exception e){
            throw new Exception ("Error al registrar el usuario");
        }
    }
    
    public void modificarUsuario (Integer id, String usu, String con, String nom, String car) throws Exception{
        strSQL= "update usuario set usuario='" + usu + "',contraseña = '" + con +"',nombresusuario='"+ nom +"',cargousuario='"+ car +"' where idusuario=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        }catch (Exception e){
            throw new Exception ("Error al modificar el usuario");
        }        
    }   
    public void eliminarUsuario (Integer id) throws Exception{
        
        strSQL = "delete from usuario where idusuario=" + id;
        
        try {
            objConectar.ejecutarBD(strSQL);
        }catch (Exception e){
            throw new Exception ("Error al eliminar el usuario");
        }
    }
    
    public String obtenerCargoUsuario(String usuario) throws Exception {
        String cargoUsuario = "";
        
        try {
            strSQL = "SELECT cargousuario FROM usuario WHERE usuario = '" + usuario + "'";
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                cargoUsuario = rs.getString("cargousuario");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el cargo del usuario desde la base de datos - " + e.getMessage());
        } finally {
            // Cerrar ResultSet si está abierto
            if (rs != null) {
                rs.close();
            }
        }     
        return cargoUsuario;
    }
         
    public boolean existenciaUsuario(int idUsuario) throws Exception {
        strSQL = "SELECT COUNT(*) FROM usuario WHERE idusuario = " + idUsuario;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar la existencia del usuario - " + e.getMessage());
        } finally {
            // Asegúrate de cerrar el ResultSet y cualquier otro recurso aquí
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }
    
}
    
    

