package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;

public class clsPedido {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;
    Connection con;
    Statement sent;

    public Integer generarCodigoPedido() throws Exception {
        strSQL = "select coalesce (max(idpedido),0)+1 as codigo from pedido";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código de pedido - " + e.getMessage());
        }
        return 0;
    }

    public Integer generarCodigoDetallePedido() throws Exception {
        strSQL = "select coalesce (max(iddetalle),0)+1 as codigo from detalle_pedido";
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código de pedido - " + e.getMessage());
        }
        return 0;
    }

    public void registrarPedido(Integer idPedido, Double total, Integer sesion, String usuario,
            String cliente, JTable tblDetalle) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getCon();
            con.setAutoCommit(false);

            sent = con.createStatement();
            int usuarioID = obtenerIDUsuario(usuario);
            int clienteID = obtenerIDCliente(cliente);
            strSQL = "insert into pedido (idpedido, totalpedido, estadopago, idsesion, idusuario, idcliente) \n"
                    + "values (" + idPedido + "," + total + ",false," + sesion + ","
                    + "" + usuarioID + "," + clienteID + ");";
            sent.executeUpdate(strSQL);

            //Enviar a que se graben los productos seleccionados:
            int ctd = tblDetalle.getRowCount();
            for (int i = 0; i < ctd; i++) {
                int idProducto = Integer.parseInt(tblDetalle.getValueAt(i, 0).toString());
                int cantidad = Integer.parseInt(tblDetalle.getValueAt(i, 3).toString());

                // Verificar el stock disponible
                int stockActual = obtenerStockProducto(idProducto);
                if (cantidad > stockActual) {
                    throw new Exception("Stock insuficiente para el producto con ID: " + idProducto);
                }

                // Agregar el detalle del pedido
                strSQL = "insert into detalle_pedido values (" + idPedido + ","
                        + idProducto + ","
                        + cantidad + ","
                        + tblDetalle.getValueAt(i, 4).toString() + ");"; //Nuevo precio
                sent.executeUpdate(strSQL);

                // Actualizar el stock del producto
                strSQL = "update producto set stock = stock - " + cantidad
                        + " where idproducto = " + idProducto;
                sent.executeUpdate(strSQL);
            }
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al guardar el pedido - " + e.getMessage());
        } finally {
            objConectar.desconectar();
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

    public ResultSet listarPedidos() throws Exception {

        strSQL = "select idpedido, p.idsesion, idmesa, totalpedido, estadopago, nombresusuario, nombrescliente from pedido p \n"
                + "inner join usuario u on p.idusuario=u.idusuario inner join cliente c \n"
                + "ON p.idcliente=c.idcliente inner join sesion s ON p.idsesion=s.idsesion where estadopago=false "
                + "ORDER BY idpedido ASC";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar los pedidos - " + e.getMessage());
        }
    }

    public ResultSet obtenerDetallePedido(int idPedido) throws Exception {
        strSQL = "SELECT pr.idproducto, nombresproducto, precioproducto, cantidad, (precioproducto * cantidad) AS nuevoprecio "
                + "FROM detalle_pedido d INNER JOIN producto pr ON d.idproducto = pr.idproducto "
                + "WHERE d.idpedido = " + idPedido + "ORDER BY pr.idproducto ASC";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al obtener el detalle del pedido - " + e.getMessage());
        }
    }

    public void actualizarPedido(Integer idPedido, Double total, Integer sesion, String usuario,
            String cliente, JTable tblDetalle) throws Exception {
        try {
            objConectar.conectar();
            con = objConectar.getCon();
            con.setAutoCommit(false);

            sent = con.createStatement();
            int usuarioID = obtenerIDUsuario(usuario);
            int clienteID = obtenerIDCliente(cliente);

            // Actualizar los datos del pedido
            strSQL = "UPDATE pedido SET totalpedido = " + total + ", estadopago=false, "
                    + "idsesion = " + sesion + ", idusuario = " + usuarioID + ", "
                    + "idcliente = " + clienteID + " "
                    + "WHERE idpedido = " + idPedido;
            sent.executeUpdate(strSQL);

            // Eliminar el detalle actual del pedido
            strSQL = "DELETE FROM detalle_pedido WHERE idpedido = " + idPedido;
            sent.executeUpdate(strSQL);

            // Agregar los nuevos detalles del pedido
            int ctd = tblDetalle.getRowCount();
            for (int i = 0; i < ctd; i++) {
                // Agregando producto en el detalle del pedido
                int idProducto = Integer.parseInt(tblDetalle.getValueAt(i, 0).toString());
                int cantidadNueva = Integer.parseInt(tblDetalle.getValueAt(i, 3).toString());

                // Obtener la cantidad anterior del producto en el pedido
                int cantidadAnterior = obtenerCantidadAnterior(idProducto, idPedido);

                // Calcular la diferencia entre la cantidad anterior y la nueva cantidad
                int diferenciaCantidad = cantidadNueva - cantidadAnterior;

                // Actualizar el stock del producto
                int nuevoStock = obtenerStockProducto(idProducto) - diferenciaCantidad;
                if (nuevoStock < 0) {
                    throw new Exception("No hay suficiente stock disponible para el producto con ID: " + idProducto);
                }

                // Actualizar el detalle del pedido
                strSQL = "INSERT INTO detalle_pedido VALUES (" + idPedido + ", "
                        + idProducto + ", " + cantidadNueva + ", "
                        + tblDetalle.getValueAt(i, 4).toString() + ");";
                sent.executeUpdate(strSQL);

                // Actualizar el stock del producto en la base de datos
                strSQL = "UPDATE producto SET stock = " + nuevoStock + " WHERE idProducto = " + idProducto;
                sent.executeUpdate(strSQL);
            }

            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al actualizar el pedido - " + e.getMessage());
        } finally {
            objConectar.desconectar();
        }
    }

    public int obtenerStockProducto(int idProducto) throws Exception {
        strSQL = "SELECT stock FROM producto WHERE idProducto = " + idProducto;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getInt("stock");
            } else {
                throw new Exception("No se encontró el producto con ID: " + idProducto);
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el stock del producto - " + e.getMessage());
        }
    }

    public int obtenerCantidadAnterior(int idProducto, int idPedido) throws Exception {
        int cantidadAnterior = 0;
        String strSQL = "SELECT cantidad FROM detalle_pedido WHERE idPedido = " + idPedido + " AND idProducto = " + idProducto;
        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                cantidadAnterior = rs.getInt("cantidad");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener la cantidad anterior del producto en el pedido - " + e.getMessage());
        }
        return cantidadAnterior;
    }

    public void actualizarEstadoPago(int idPedido, boolean estadoPago) throws Exception {
        strSQL = "UPDATE pedido SET estadopago = " + estadoPago + " WHERE idpedido = " + idPedido;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Error al actualizar el estado de pago del pedido - " + e.getMessage());
        }
    }

}
