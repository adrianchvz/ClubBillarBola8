package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class clsSesion {

    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs = null;

    public ResultSet listarSesiones(int idMesa) throws Exception {
        strSQL = "select idsesion, m.idmesa, preciosesion, s.estado, fechainicio,horainicio,fechafin,horafin,duracion, montototalsesion\n"
                + "from sesion s inner join mesa m ON s.idmesa=m.idmesa where s.idmesa = " + idMesa + "ORDER BY idsesion DESC";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar sesion");
        }
    }

    public Integer generarCodigoSesion() throws Exception {

        strSQL = "select coalesce(max(idsesion),0)+1 as codigo from sesion";

        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar el id de la sesion");
        }

        return 0;
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

    public void iniciarSesion(Integer id, Integer prc, Integer idMesa, Boolean est) throws Exception {
        strSQL = "insert into sesion (idsesion, preciosesion, estado, fechaInicio, horaInicio, idmesa) values  "
                + "(" + id + "," + prc + "," + est + ",current_date, current_time, " + idMesa + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar la sesión");
        }
    }

    public void terminarSesion(Integer id) throws Exception {
        // Primero actualizamos la fechaFin y horaFin
        String updateFechaHoraSQL = "UPDATE sesion SET estado=false, fechaFin = current_date, horaFin = current_time "
                + "WHERE idsesion = " + id;
        try {
            objConectar.ejecutarBD(updateFechaHoraSQL);
        } catch (Exception e) {
            throw new Exception("Error al actualizar la fecha y hora de fin de sesión");
        }

        // Luego calculamos la duración y actualizamos la sesión con la duración calculada
        String updateDuracionSQL = "UPDATE sesion \n"
                + "SET duracion = \n"
                + "    CASE\n"
                + "        WHEN horaFin >= horaInicio THEN\n"
                + "            ROUND(EXTRACT(EPOCH FROM (fechaFin + horaFin - (fechaInicio + horaInicio))) / 60)\n"
                + "        ELSE\n"
                + "            ROUND(EXTRACT(EPOCH FROM ((fechaFin) + horaFin - (fechaInicio + horaInicio))) / 60)\n"
                + "    END\n"
                + "WHERE idsesion = " + id;
        try {
            objConectar.ejecutarBD(updateDuracionSQL);
        } catch (Exception e) {
            throw new Exception("Error al calcular y actualizar la duración de la sesión");
        }

    }

    public void actualizarDuracion(Integer id) throws Exception {
        String duracionFinal = obtenerDuracion(id);

        // Actualizamos el monto total en la tabla sesion
        String updateDuracionFinalSQL = "UPDATE sesion SET duracion = '" + duracionFinal + "' WHERE idsesion = " + id;

        try {
            objConectar.ejecutarBD(updateDuracionFinalSQL);
        } catch (Exception e) {
            throw new Exception("Error al calcular la duración final de la sesión" + e.getMessage());
        }
    }

    public void actualizarMontoTotal(Integer id) throws Exception {
        // Luego obtenemos la duración en minutos
        int duracionMinutos = obtenerDuracionEnMinutos(id);
        double precio = obtenerPrecio(id);

        // Calculamos el monto total de la sesión
        double montoTotal = calcularMontoTotal(duracionMinutos, precio);

        // Actualizamos el monto total en la tabla sesion
        String updateMontoSQL = "UPDATE sesion SET montototalsesion = " + montoTotal + " WHERE idsesion = " + id;

        try {
            objConectar.ejecutarBD(updateMontoSQL);
        } catch (Exception e) {
            throw new Exception("Error al calcular el monto total de la sesión");
        }
    }

    public double calcularMontoTotal(int duracion, double costoPor60Minutos) {

        // Calculamos el costo por minuto
        double costoPorMinuto = costoPor60Minutos / 60;

        // Calculamos el costo total basado en la duración proporcionada
        double costoTotal = costoPorMinuto * duracion;

        // Redondeamos el resultado al múltiplo de 0.05 más cercano
        double montoTotalRedondeado = Math.ceil(costoTotal);
        return montoTotalRedondeado;
    }

    public double obtenerPrecio(int id) throws Exception {

        strSQL = "SELECT preciosesion FROM sesion where idsesion = " + id;

        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("preciosesion");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el precio de la sesión");
        }

        return 0;
    }

    public Integer obtenerDuracionEnMinutos(Integer id) throws Exception {
        strSQL = "SELECT \n"
                + "       (CAST(SPLIT_PART(duracion, ' horas ', 1) AS INTEGER) * 60 +\n"
                + "        CAST(SPLIT_PART(SPLIT_PART(duracion, ' horas ', 2), ' minutos', 1) AS INTEGER)) AS duracion\n"
                + "FROM sesion\n"
                + "WHERE idsesion=" + id; // Obtener la duración como string desde la base de datos
        try {
            rs = objConectar.consultarBD(strSQL);
            while (rs.next()) {
                return rs.getInt("duracion");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener la duración de la sesión");
        }

        return 0;
    }

    public String obtenerDuracion(Integer id) throws Exception {
        strSQL = "SELECT ((duracion::numeric / 60)::integer || ' horas ' || (duracion::numeric % 60)::integer || "
                + "' minutos')::character varying AS duracion\n"
                + "FROM sesion\n"
                + "WHERE idsesion = " + id;

        try {
            rs = objConectar.consultarBD(strSQL);
            if (rs.next()) {
                return rs.getString("duracion");
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener la duración de la sesión");
        }

        return null; // Si no se encuentra ninguna duración
    }

    public boolean existeSesionEnJuego(Integer idMesa) throws Exception {
        boolean sesionEnJuego = false;

        // Consulta SQL para verificar si hay una sesión en juego para la mesa especificada
        String strSQL = "SELECT COUNT(*) AS cantidad "
                + "FROM sesion "
                + "WHERE estado = true AND idmesa = " + idMesa;

        try {

            rs = objConectar.consultarBD(strSQL);

            // Verificar si se encontró alguna sesión en juego para la mesa
            if (rs.next()) {
                int cantidadSesiones = rs.getInt("cantidad");
                sesionEnJuego = (cantidadSesiones > 0); // Verdadero si hay al menos una sesión en juego
            }
        } catch (Exception e) {
            throw new Exception("Error al verificar si existe una sesión en juego para esta mesa: " + e.getMessage());
        }

        return sesionEnJuego;
    }

    public ResultSet listarSesionesPorMesa(int idMesa) throws Exception {
        strSQL = "select idsesion, idmesa, estado from sesion where idmesa= " + idMesa + " "
                + "ORDER BY idsesion DESC";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar sesion");
        }
    }

}
