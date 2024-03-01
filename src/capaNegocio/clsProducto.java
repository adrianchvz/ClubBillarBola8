package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class clsProducto {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con;
    Statement sent;

    public ResultSet listarProductos() throws Exception {

        strSQL = "SELECT idproducto, nombresproducto, precioproducto, stock, estadoproducto, material, tamaño, sabor, \n"
                + "m.nombremarca, c.nombrecategoria FROM PRODUCTO p INNER JOIN MARCA m \n"
                + "ON p.idmarca=m.idmarca INNER JOIN CATEGORIA c ON p.idcategoria=c.idcategoria ORDER BY idproducto ASC";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar productos - " + e.getMessage());
        }
    }

    public Integer generarCodigoProducto() throws Exception {
        strSQL = "Select coalesce (max(idproducto),0)+1 as codigo from producto";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código de producto -" + e.getMessage());
        }
        return 0;
    }

    public void registrarProducto(Integer id, String nom, Double pre, Integer stk, Boolean est,
            String mat, String tam, String sab, Integer idmar, Integer idcat) throws Exception {
        strSQL = "Insert into producto (idproducto,nombresproducto,precioproducto,stock,estadoproducto,"
                + "material,tamaño,sabor, idmarca, idcategoria)"
                + "values (" + id + ",'" + nom + "'," + pre + "," + stk + ",'" + est + "','" + mat + "',"
                + "'" + tam + "','" + sab + "'," + idmar + "," + idcat + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar el producto - " + e.getMessage());
        }
    }

    public ResultSet buscarProductos(Integer id) throws Exception {

        strSQL = "SELECT idproducto, nombresproducto, precioproducto, stock, estadoproducto, material, \n"
                + "tamaño, sabor, m.nombremarca, c.nombrecategoria FROM PRODUCTO p INNER JOIN MARCA m ON \n"
                + "p.idmarca=m.idmarca INNER JOIN CATEGORIA c ON p.idcategoria=c.idcategoria where idproducto =" + id;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar productos - " + e.getMessage());
        }
    }

    public void eliminarProducto(Integer id) throws Exception {
        strSQL = "delete from producto where idproducto=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar producto" + e.getMessage());
        }
    }

    public void darBajaProducto(Integer id) throws Exception {
        strSQL = "update producto set estadoProducto=false where idproducto=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al dar de baja producto" + e.getMessage());
        }
    }

    public void modificarProducto(Integer id, String nom, Double pre, Integer stk, Boolean est,
            String mat, String tam, String sab, Integer idmar, Integer idcat) throws Exception {
        strSQL = "update producto set nombresproducto='" + nom + "', precioproducto=" + pre + ", "
                + "stock=" + stk + ", estadoproducto=" + est + ",material = '" + mat + "', tamaño = '"
                + tam + "', sabor='" + sab + "', idmarca= " + idmar + ", idcategoria= " + idcat + " where "
                + "idproducto=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar el producto - " + e.getMessage());
        }
    }

    public ResultSet filtrar(String nom) throws Exception {

        strSQL = "SELECT P.*, m.nombremarca, c.nombrecategoria FROM (SELECT * FROM producto where UPPER(nombresproducto) \n"
                + "LIKE UPPER('%" + nom + "%') and estadoproducto=true) as P inner join marca as M on P.idmarca = M.idmarca \n"
                + "inner join categoria as C on P.idcategoria = c.idcategoria order by idproducto asc;";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al filtrar productos -" + e.getMessage());
        }
    }

    public int getStock(Integer id) throws Exception {
        strSQL = "select stock from producto where idproducto=" + id + ";";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("stock");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el stock - " + e.getMessage());
        }
        return 0;
    }

    public void incrementarStock(int idProducto, int cantidadIncremento) throws Exception {
        strSQL = "UPDATE producto SET stock = stock + " + cantidadIncremento + " WHERE idproducto = " + idProducto;
        
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al incrementar el stock del producto - " + e.getMessage());
        } 
    }
    
    public void actualizarStockProducto(int idProducto, int nuevoStock) throws Exception {
        strSQL = "UPDATE PRODUCTO SET stock = "+ nuevoStock + "WHERE idProducto = " + idProducto;
        
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al actualizar el stock producto - " + e.getMessage());
        }
    }
    

    public boolean existenciaProducto(int idProducto) throws Exception {
        strSQL = "SELECT COUNT(*) FROM producto WHERE idproducto = " + idProducto;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar la existencia del producto - " + e.getMessage());
        } finally {
            // Asegúrate de cerrar el ResultSet y cualquier otro recurso aquí
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }
    
    
}
