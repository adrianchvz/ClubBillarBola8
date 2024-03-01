
package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.ResultSet;

public class clsCliente {
    
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    
    public ResultSet listarClientes() throws Exception {
        strSQL = "select * from cliente ORDER BY idcliente ASC";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar el cliente");
        }
    }
    
     public Integer generarCodigoCliente() throws Exception {

        strSQL = "select coalesce(max(idcliente),0)+1 as codigo from cliente";

        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar el id del cliente");
        }

        return 0;
    }

    public void registrarCliente(Integer id, String nom) throws Exception {
        strSQL = "insert into cliente (idcliente, nombrescliente) values (" + id + ",'" + nom + "')";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar el cliente");
        }
    }

    public ResultSet buscarDatos(Integer id) throws Exception {
        strSQL = "select nombrescliente from cliente where idcliente=" + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al mostrar los datos del cliente - " + e.getMessage());
        }
    }

    public void modificarCliente(Integer id, String nom) throws Exception {
        strSQL = "update cliente set nombrescliente='" + nom + "' where idcliente=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar el cliente");
        }
    }

    public void eliminarCliente(Integer id) throws Exception {

        strSQL = "delete from cliente where idcliente=" + id;

        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar el cliente");
        }
    }

    public Integer obtenerIDCliente(String nombre) throws Exception {

        strSQL = "select idcliente from cliente where nombrescliente='" + nombre + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idcliente");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el id del cliente - " + e.getMessage());
        }
        return 0;
    }
    
    public ResultSet filtrarClientes(String nom) throws Exception {

        strSQL = "SELECT * FROM (SELECT * FROM cliente where UPPER(nombrescliente) "
                + "LIKE UPPER('%" + nom + "%')) as C";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al filtrar productos -" + e.getMessage());
        }
    }
    
    public boolean existenciaCliente(int idCliente) throws Exception {
        strSQL = "SELECT COUNT(*) FROM cliente WHERE idcliente = " + idCliente;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar la existencia del cliente - " + e.getMessage());
        } finally {
            // Asegúrate de cerrar el ResultSet y cualquier otro recurso aquí
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }
}
