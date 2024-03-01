
package capaPresentacion;

public class PryBillar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Crear una instancia del jdInicioSesion y mostrarlo
        jdInicioSesion ini = new jdInicioSesion(null, true); // Puedes pasar null como el componente padre
        ini.setVisible(true);

        // Después de cerrar jdInicioSesion, verifica si el inicio de sesión fue exitoso
        if (ini.nombreusuario != null && !ini.nombreusuario.isEmpty()) {
            // Si el inicio de sesión fue exitoso, crea y muestra el frmMenuPrincipal
            frmMenuPrincipal frm = new frmMenuPrincipal();
            frm.setVisible(true);
        }
    }

}
