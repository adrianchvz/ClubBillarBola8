package capaPresentacion;

import capaNegocio.clsReporte;
import capaNegocio.clsUsuario;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.swing.JRViewer;

public class jdReporteIngresosVentas extends javax.swing.JDialog {

    clsUsuario objUsuario = new clsUsuario();

    public jdReporteIngresosVentas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.vistaReporte.setVisible(false);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        fechaFin = new com.toedter.calendar.JDateChooser();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        btnReporte = new javax.swing.JButton();
        vistaReporte = new javax.swing.JDesktopPane();
        cboUsuario = new capaInterfaz.componentes.Combobox();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(":: REPORTE DE INGRESOS EN VENTAS ::");
        setIconImage(getIconImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fechaFin.setBackground(new java.awt.Color(255, 255, 255));
        fechaFin.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(fechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 107, 120, -1));
        jPanel2.add(fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 107, 120, -1));

        btnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/verReporte1.png"))); // NOI18N
        btnReporte.setBorder(null);
        btnReporte.setContentAreaFilled(false);
        btnReporte.setFocusable(false);
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });
        jPanel2.add(btnReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 95, -1, 60));

        javax.swing.GroupLayout vistaReporteLayout = new javax.swing.GroupLayout(vistaReporte);
        vistaReporte.setLayout(vistaReporteLayout);
        vistaReporteLayout.setHorizontalGroup(
            vistaReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
        );
        vistaReporteLayout.setVerticalGroup(
            vistaReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jPanel2.add(vistaReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 770, 300));

        cboUsuario.setFocusable(false);
        jPanel2.add(cboUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 105, 170, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/frmRpIngresosVentas.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 520));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        try {
            Container contenedor = this.vistaReporte;
            contenedor.setLayout(new BorderLayout());

            // Obtener fechas del JDateChooser
            Date fechaInicioDate = fechaInicio.getDate();
            Date fechaFinDate = fechaFin.getDate();

            // Verificar si se han seleccionado las fechas
            if (fechaInicioDate == null || fechaFinDate == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione la fecha de inicio y la fecha de fin.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; // Salir del método si no se han seleccionado ambas fechas
            }

            // Formatear fechas a cadenas de texto en el formato "yyyy/mm/dd"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaInicioString = sdf.format(fechaInicioDate);
            String fechaFinString = sdf.format(fechaFinDate);

            // Verificar si se ha seleccionado un usuario
            if (cboUsuario.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; // Salir del método si no se ha seleccionado un usuario
            }

            // Manejo de los parámetros
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("fechaInicio", fechaInicioString);
            parametros.put("fechaFin", fechaFinString);
            parametros.put("nombreUsuario", cboUsuario.getSelectedItem().toString());

            JRViewer objReporte = new clsReporte().reporteInterno("rpIngresosVentas.jasper", parametros);
            contenedor.add(objReporte);

            this.vistaReporte.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage() + "Error en reporte",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnReporteActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        listarUsuarios();
    }//GEN-LAST:event_formWindowOpened

    private void listarUsuarios() {

        ResultSet rsUsuarios = null;
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        cboUsuario.setModel(modelo);

        try {
            rsUsuarios = objUsuario.listarUsuarios();
            while (rsUsuarios.next()) {
                modelo.addElement(rsUsuarios.getString("nombresusuario"));
            }
        } catch (Exception e) {
        }
    }

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("recursos/icono.png"));
        return retValue;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReporte;
    private capaInterfaz.componentes.Combobox cboUsuario;
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JDesktopPane vistaReporte;
    // End of variables declaration//GEN-END:variables
}
