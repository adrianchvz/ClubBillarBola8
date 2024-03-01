package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.ResultSet;

public class clsMesa {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public ResultSet listarMesas() throws Exception {
        strSQL = "select * from MESA ORDER BY idmesa ASC";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar mesa");
        }
    }

    public Integer generarCodigoMesa() throws Exception {

        strSQL = "select coalesce(max(idmesa),0)+1 as codigo from mesa";

        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar el id de la mesa");
        }

        return 0;
    }

    public void registrarMesa(Integer id, String nom, Boolean est) throws Exception {
        strSQL = "insert into mesa (idmesa, nombremesa, estado) values (" + id + ",'" + nom + "', "
                + "" + est + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar la mesa");
        }
    }

    public ResultSet buscarDatos(Integer id) throws Exception {
        strSQL = "select nombremesa, estado from mesa where idmesa=" + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al mostrar los datos de la mesa - " + e.getMessage());
        }
    }

    public void modificarMesa(Integer id, String nom, Boolean est) throws Exception {
        strSQL = "update mesa set nombremesa='" + nom + "', estado = " + est + " "
                + "where idmesa=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar la categoría");
        }
    }

    public void eliminarMesa(Integer id) throws Exception {

        strSQL = "delete from mesa where idmesa=" + id;

        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar la mesa");
        }
    }

    public Integer obtenerIDMesa(String nombre) throws Exception {

        strSQL = "select idmesa from mesa where nombremesa='" + nombre + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idmesa");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el id de la mesa - " + e.getMessage());
        }
        return 0;
    }

    public void darBajaMesa(Integer cod) throws Exception {
        strSQL = "Update mesa set estado=false where idmesa=" + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al dar de baja la mesa - " + e.getMessage());
        }

    }
    
     public void darAltaMesa(Integer cod) throws Exception {
        strSQL = "Update mesa set estado=true where idmesa=" + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al dar de alta la mesa - " + e.getMessage());
        }

    }

    public boolean obtenerEstadoMesa(int idMesa) throws Exception {
        strSQL = "SELECT estado FROM mesa WHERE idmesa = " + idMesa;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getBoolean("estado");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el estado de la mesa - " + e.getMessage());
        }
        return false; // Por ejemplo, suponiendo que una mesa no encontrada está en estado "falso"
    }
    
    public boolean existenciaMesa(int idMesa) throws Exception {
        strSQL = "SELECT COUNT(*) FROM mesa WHERE idmesa = " + idMesa;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar la existencia de la mesa - " + e.getMessage());
        } finally {
            // Asegúrate de cerrar el ResultSet y cualquier otro recurso aquí
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }
}
