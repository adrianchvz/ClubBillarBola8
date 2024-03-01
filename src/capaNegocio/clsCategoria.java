package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.ResultSet;

public class clsCategoria {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public ResultSet listarCategorias() throws Exception {
        strSQL = "select * from categoria ORDER BY idcategoria ASC";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar categoría");
        }
    }

    public ResultSet listarCategoriasPorNombre() throws Exception {
        strSQL = "select * from categoria ORDER BY nombrecategoria ASC";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar categoría");
        }
    }

    public Integer generarCodigoCategoria() throws Exception {

        strSQL = "select coalesce(max(idcategoria),0)+1 as codigo from categoria";

        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar el id de la categoría");
        }

        return 0;
    }

    public void registrarCategoria(Integer id, String nom) throws Exception {
        strSQL = "insert into categoria (idcategoria, nombrecategoria) values (" + id + ",'" + nom + "')";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar la categoría");
        }
    }

    public ResultSet buscarDatos(Integer id) throws Exception {
        strSQL = "select nombrecategoria from categoria where idcategoria=" + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al mostrar los datos de la categoría - " + e.getMessage());
        }
    }

    public void modificarCategoria(Integer id, String nom) throws Exception {
        strSQL = "update categoria set nombrecategoria='" + nom + "' where idcategoria=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar la categoría");
        }
    }

    public void eliminarCategoria(Integer id) throws Exception {

        strSQL = "delete from categoria where idcategoria=" + id;

        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar la categoria");
        }
    }

    public Integer obtenerIDCategoria(String nombre) throws Exception {

        strSQL = "select idcategoria from categoria where nombrecategoria='" + nombre + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idcategoria");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el id de categoría - " + e.getMessage());
        }
        return 0;
    }

    public boolean existenciaCategoria(int idCategoria) throws Exception {
        strSQL = "SELECT COUNT(*) FROM categoria WHERE idcategoria = " + idCategoria;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar la existencia de la categoría - " + e.getMessage());
        } finally {
            // Asegúrate de cerrar el ResultSet y cualquier otro recurso aquí
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }

}
