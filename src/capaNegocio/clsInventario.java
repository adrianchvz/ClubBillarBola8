package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;

public class clsInventario {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con;
    Statement sent;

    public ResultSet listarInventario() throws Exception {
        strSQL = "select idinventario, fecha, hora, nombresusuario from inventario i \n"
                + "inner join usuario u ON i.idusuario = u.idusuario\n"
                + "order by i.idinventario asc";
        ;
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar inventario");
        }
    }

    public Integer generarCodigoInventario() throws Exception {

        strSQL = "select coalesce(max(idinventario),0)+1 as codigo from inventario";

        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar el id del inventario");
        }

        return 0;
    }

    public void registrarInventario(Integer idInventario, String idUsuario, JTable tblDetalle)
            throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getCon();
            con.setAutoCommit(false);

            sent = con.createStatement();
            int usuarioID = obtenerIDUsuario(idUsuario);
            strSQL = "INSERT INTO inventario (idinventario, fecha, hora, idusuario) VALUES (" + idInventario + ", CURRENT_DATE, CURRENT_TIME, " + usuarioID + ")";
            sent.executeUpdate(strSQL);

            //Enviar a que se graben los productos seleccionados:
            int ctd = tblDetalle.getRowCount();
            for (int i = 0; i < ctd; i++) {
                int idProducto = Integer.parseInt(tblDetalle.getValueAt(i, 0).toString());
                int agregado = Integer.parseInt(tblDetalle.getValueAt(i, 2).toString());
                int dañado = Integer.parseInt(tblDetalle.getValueAt(i, 3).toString());
                int perdido = Integer.parseInt(tblDetalle.getValueAt(i, 4).toString());

                // Agregar la cantidad agregada al stock
                strSQL = "UPDATE producto SET stock = stock + " + agregado + " WHERE idproducto = " + idProducto;
                sent.executeUpdate(strSQL);

                // Restar la cantidad dañada y perdida al stock
                strSQL = "UPDATE producto SET stock = stock - " + (dañado + perdido) + " WHERE idproducto = " + idProducto;
                sent.executeUpdate(strSQL);

                // Agregar producto en el detalle de inventario
                strSQL = "INSERT INTO detalle_inventario VALUES (" + idInventario + ", " + idProducto + ", " + agregado + ", " + dañado + ", " + perdido + ")";
                sent.executeUpdate(strSQL);
            }
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al guardar inventario - " + e.getMessage());
        } finally {
            objConectar.desconectar();
        }
    }

    public void modificarInventario(Integer id, String fec) throws Exception {
        strSQL = "update inventario set fecha='" + fec + "' where idinventario=" + id;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar un inventario");
        }
    }

    public void eliminarInventario(Integer id) throws Exception {

        strSQL = "delete from inventario where idinventario=" + id;

        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar un inventario");
        }
    }

    public Integer obtenerIDInventario(String fec) throws Exception {

        strSQL = "select idinventario from inventario where fecha='" + fec + "'";
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("idbase");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el id de un inventario - " + e.getMessage());
        }
        return 0;
    }

    public int obtenerIDUsuario(String nombreUsuario) throws Exception {
        int nomUsu = -1; // Valor inicial por defecto
        String strSQL = "SELECT idusuario FROM USUARIO WHERE nombresusuario ='" + nombreUsuario + "'";
        try {
            ResultSet rs = objConectar.consultarBD(strSQL); // Suponiendo que objMantenimiento es tu objeto para ejecutar comandos SQL
            if (rs.next()) {
                nomUsu = rs.getInt("idusuario");
            }
        } catch (Exception e) {
            // Manejar la excepción
            throw new Exception("Error al obtener el ID del usuario." + e.getMessage());
        }
        return nomUsu;
    }

    public ResultSet obtenerDetalleInventario(int idInventario) throws Exception {
        strSQL = "SELECT d.idproducto, nombresproducto, agregado, dañado, perdido\n"
                + "FROM detalle_inventario d INNER JOIN producto pr ON d.idproducto = pr.idproducto\n"
                + "WHERE d.idinventario = " + idInventario + "ORDER BY pr.idproducto ASC";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al obtener el detalle de inventario - " + e.getMessage());
        }
    }

}
