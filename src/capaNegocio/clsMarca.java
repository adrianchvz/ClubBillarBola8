package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.ResultSet;

public class clsMarca {
    
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    
    public ResultSet listarMarcas() throws Exception {
        strSQL = "select * from marca ORDER BY idmarca ASC";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar marca");
        }
    }
    
    public ResultSet listarMarcasPorNombre() throws Exception {
        strSQL = "select * from marca ORDER BY nombremarca ASC";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar marca");
        }
    }
    
     public Integer generarCodigoMarca() throws Exception {

        strSQL = "select coalesce(max(idmarca),0)+1 as codigo from marca";

        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar el id de la marca");
        }

        return 0;
    }

    public void registrarMarca(Integer id, String nom) throws Exception {
        strSQL = "insert into marca (idmarca, nombremarca) values (" + id + ",'" + nom + "')";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar la marca");
        }
    }

    public ResultSet buscarDatos(Integer id) throws Exception {
        strSQL = "select nombremarca from marca where idmarca=" + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al mostrar los datos de la marca - " + e.getMessage());
        }
    }

    public void modificarMarca(Integer id, String nom) throws Exception {
        strSQL = "update marca set nombremarca='" + nom + "' where idmarca=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar la marca");
        }
    }

    public void eliminarMarca(Integer id) throws Exception {

        strSQL = "delete from marca where idmarca=" + id;

        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar la marca");
        }
    }

    public Integer obtenerIDMarca(String nombre) throws Exception {

        strSQL = "select idmarca from marca where nombremarca='" + nombre + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idmarca");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el id de la marca - " + e.getMessage());
        }
        return 0;
    }
    
    public boolean existenciaMarca(int idMarca) throws Exception {
        strSQL = "SELECT COUNT(*) FROM marca WHERE idmarca = " + idMarca;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar la existencia de la marca - " + e.getMessage());
        } finally {
            // Asegúrate de cerrar el ResultSet y cualquier otro recurso aquí
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }
}
