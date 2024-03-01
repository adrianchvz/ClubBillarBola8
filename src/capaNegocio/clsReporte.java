
package capaNegocio;

import capaDatos.clsJDBC;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class clsReporte {
    
    public final String RUTA_REPORTES = "/reportes/";

    public JRViewer reporteInterno(String archivoReporte, Map<String,Object> parametros) throws Exception{
    try {
        clsJDBC objConexion = new clsJDBC();
        objConexion.conectar();
        InputStream inputStream = getClass().getResourceAsStream(RUTA_REPORTES + archivoReporte);
        if (inputStream == null) {
            throw new FileNotFoundException("El archivo de reporte no se encuentra: " + archivoReporte);
        }
        JasperPrint reporte = JasperFillManager.fillReport(inputStream, parametros, objConexion.getCon());
        JRViewer visor = new JRViewer(reporte);
        return visor;
    } catch (JRException | FileNotFoundException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
        System.out.println(e.getMessage());
    }
    return null;
}

    
    public JasperPrint reporteExterno(String archivoReporte, Map<String,Object> parametros) throws Exception{
        try 
        {            
            clsJDBC objConexion = new clsJDBC();
            objConexion.conectar();
            JasperPrint reporte = 
                    JasperFillManager.fillReport(this.RUTA_REPORTES + archivoReporte, 
                    parametros, 
                    objConexion.getCon()
            );
            
            return reporte;
        } catch (JRException e ) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getMessage());
        }
        
        return null;
        
    }
}
