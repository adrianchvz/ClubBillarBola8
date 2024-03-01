package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;

public class clsVenta {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con;
    Statement sent;

    public Integer generarCodigoVenta() throws Exception {
        strSQL = "select coalesce (max(idventa),0)+1 as codigo from venta";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código de venta - " + e.getMessage());
        }
        return 0;
    }

    public void registrar(Integer idVenta, Double total, String cliente,
            String usuario, JTable tblDetalle) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getCon();
            con.setAutoCommit(false);

            sent = con.createStatement();
            int usuarioID = obtenerIDUsuario(usuario);
            int clienteID = obtenerIDCliente(cliente);
            strSQL = "insert into venta (idventa, fechaHoraVenta, totalVenta, estadopago, idcliente, "
                    + "idusuario) \n"
                    + "values (" + idVenta + ",current_timestamp," + total + ",false,"
                    + "" + clienteID + "," + usuarioID + ");";
            sent.executeUpdate(strSQL);

            //Enviar a que se graben los productos seleccionados:
            int ctd = tblDetalle.getRowCount();
            for (int i = 0; i < ctd; i++) {
                //Agregando producto en el pedido:
                strSQL = "insert into detalle_venta values (" + idVenta + ","
                        + //idPedido
                        tblDetalle.getValueAt(i, 0).toString() + ","
                        + //idproducto
                        tblDetalle.getValueAt(i, 3).toString() + ","
                        + //cantidad
                        tblDetalle.getValueAt(i, 4).toString() + ");"; //Nuevo precio
                sent.executeUpdate(strSQL);

                //Modificando stock del producto comprado:
                strSQL = "update producto set stock = stock - "
                        + Integer.parseInt(tblDetalle.getValueAt(i, 3).toString())
                        + "WHERE idproducto ="
                        + Integer.parseInt(tblDetalle.getValueAt(i, 0).toString()) + ";";
                sent.executeUpdate(strSQL);
            }
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al guardar venta - " + e.getMessage());
        } finally {
            objConectar.desconectar();
        }
    }


    public ResultSet listarVentas() throws Exception {

        strSQL = "SELECT idventa, fechahoraventa, totalventa, estadopago, nombrescliente, nombresusuario "
                + "FROM VENTA v INNER JOIN CLIENTE c ON v.idcliente=c.idcliente INNER JOIN "
                + "USUARIO u ON v.idusuario=u.idusuario where estadopago=false ORDER BY idventa ASC";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar las ventas - " + e.getMessage());
        }
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

    public int obtenerIDCliente(String nombreCliente) throws Exception {
        int nomCli = -1; // Valor inicial por defecto
        String strSQL = "SELECT idcliente FROM CLIENTE WHERE nombrescliente ='" + nombreCliente + "'";
        try {
            ResultSet rs = objConectar.consultarBD(strSQL); // Suponiendo que objMantenimiento es tu objeto para ejecutar comandos SQL
            if (rs.next()) {
                nomCli = rs.getInt("idcliente");
            }
        } catch (Exception e) {
            // Manejar la excepción
            throw new Exception("Error al obtener el ID del cliente." + e.getMessage());
        }
        return nomCli;
    }
    
    public ResultSet obtenerDetalleVenta(int idVenta) throws Exception {
        strSQL = "SELECT pr.idproducto, nombresproducto, precioproducto, cantidad, (precioproducto * cantidad) AS nuevoprecio "
                + "FROM detalle_venta d INNER JOIN producto pr ON d.idproducto = pr.idproducto "
                + "WHERE d.idventa = " + idVenta + "ORDER BY pr.idproducto ASC";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al obtener el detalle de la venta - " + e.getMessage());
        }
    }

    public void actualizarEstadoPago(int idVenta) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getCon();
            con.setAutoCommit(false);

            sent = con.createStatement();
            strSQL = "UPDATE venta SET estadopago = true  WHERE idventa = " + idVenta;
            sent.executeUpdate(strSQL);

            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al actualizar el estado de pago de la venta - " + e.getMessage());
        } finally {
            objConectar.desconectar();
        }
    }
}