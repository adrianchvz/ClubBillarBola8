/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaPresentacion;

import capaInterfaz.componentes.ScrollBar;
import capaInterfaz.componentes.Table;
import capaNegocio.clsMesa;
import capaNegocio.clsSesion;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adrian
 */
public class jdSesion extends javax.swing.JDialog {

    clsSesion objSesion = new clsSesion();
    clsMesa objMesa = new clsMesa();
    private Timer timer;
    private boolean estadoMesa;
    private int idMesa;

    public jdSesion(java.awt.Frame parent, boolean modal, int idMesa, boolean estadoMesa) {
        super(parent, modal);
        initComponents();
        mostrarNumSesion();
        this.estadoMesa = estadoMesa;
        this.idMesa = idMesa;
        btnTerminar.setEnabled(false);
        // Inicializar el Timer para actualizar la fecha y hora cada segundo
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarFechaHora();
            }
        });
        timer.start(); // Iniciar el Timer

        // Seleccionar checkbox basado en el estado de la mesa
        chkEstado.setSelected(estadoMesa);
        lblMesa.setText(String.valueOf(idMesa));
        
        tblSesiones = new Table(); 
        jScrollPane1.setViewportView(tblSesiones);
        jScrollPane1.setVerticalScrollBar(new ScrollBar());

        // Agregar MouseListener a la tabla para manejar el evento de clic
        tblSesiones.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSesionesMouseClicked(evt);
            }
        });
    }

    private void actualizarFechaHora() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date fechaHoraActual = new Date();
        lblFecha.setText(dateFormat.format(fechaHoraActual));
        lblHora.setText(timeFormat.format(fechaHoraActual));
    }

    private void mostrarNumSesion() {
        try {
            lblSesion.setText(String.valueOf(objSesion.generarCodigoSesion()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void listarSesiones() {
        ResultSet rsSesiones = null;
        Vector registro;
        String estado = "";

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        modelo.addColumn("SESIÓN");
        modelo.addColumn("MESA");
        modelo.addColumn("PRECIO");
        modelo.addColumn("ESTADO");
        modelo.addColumn("FECHA INICIO");
        modelo.addColumn("HORA INICIO");
        modelo.addColumn("FECHA FIN");
        modelo.addColumn("HORA FIN");
        modelo.addColumn("DURACIÓN");
        modelo.addColumn("MONTO TOTAL");

        try {
            rsSesiones = objSesion.listarSesiones(idMesa);
            while (rsSesiones.next()) {
                String fechaInicioString = rsSesiones.getString("fechaInicio");
                String fechaFinString = rsSesiones.getString("fechaFin");
                String horaInicioString = rsSesiones.getString("horaInicio");
                String horaFinString = rsSesiones.getString("horaFin");

                SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");
                SimpleDateFormat sdfOutput = new SimpleDateFormat("dd-MM-yyyy");

                Date fechaInicio = sdfInput.parse(fechaInicioString);
                Date fechaFin = (fechaFinString != null) ? sdfInput.parse(fechaFinString) : null;
                Date horaInicio = sdfHora.parse(horaInicioString);
                Date horaFin = (horaFinString != null) ? sdfHora.parse(horaFinString) : null;

                String fechaInicioFormateada = sdfOutput.format(fechaInicio);
                String fechaFinFormateada = (fechaFin != null) ? sdfOutput.format(fechaFin) : "";
                String horaInicioFormateada = sdfHora.format(horaInicio);
                String horaFinFormateada = (horaFin != null) ? sdfHora.format(horaFin) : "";

                registro = new Vector();
                registro.add(0, rsSesiones.getInt("idsesion"));
                registro.add(1, rsSesiones.getString("idmesa"));
                if (rsSesiones.getString("estado").equals("t")) {
                    estado = "En juego";
                } else {
                    estado = "Terminado";
                }     
                registro.add(2, rsSesiones.getDouble("preciosesion"));
                registro.add(3, estado);
                registro.add(4, fechaInicioFormateada);
                registro.add(5, horaInicioFormateada);
                registro.add(6, fechaFinFormateada);
                registro.add(7, horaFinFormateada);
                registro.add(8, rsSesiones.getString("duracion"));
                registro.add(9, rsSesiones.getString("montototalsesion"));

                modelo.addRow(registro);
            }
            tblSesiones.setModel(modelo);

            tblSesiones.getColumnModel().getColumn(0).setPreferredWidth(40); 
            tblSesiones.getColumnModel().getColumn(1).setPreferredWidth(20);
            tblSesiones.getColumnModel().getColumn(2).setPreferredWidth(30); 
            tblSesiones.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblSesiones.getColumnModel().getColumn(4).setPreferredWidth(70); 
            tblSesiones.getColumnModel().getColumn(5).setPreferredWidth(70);
            tblSesiones.getColumnModel().getColumn(6).setPreferredWidth(50); 
            tblSesiones.getColumnModel().getColumn(7).setPreferredWidth(50);
            tblSesiones.getColumnModel().getColumn(8).setPreferredWidth(100); 
            tblSesiones.getColumnModel().getColumn(9).setPreferredWidth(100);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnIniciar = new javax.swing.JButton();
        btnTerminar = new javax.swing.JButton();
        btnPedidos = new javax.swing.JButton();
        chkEstado = new javax.swing.JCheckBox();
        lblSesion = new javax.swing.JLabel();
        lblMesa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSesiones = new javax.swing.JTable();
        txtPrecio = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(":: ÁREA DE SESIONES DE JUEGO ::");
        setIconImage(getIconImage());
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iniciarMesa.png"))); // NOI18N
        btnIniciar.setBorder(null);
        btnIniciar.setContentAreaFilled(false);
        btnIniciar.setFocusable(false);
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        jPanel2.add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(965, 100, 150, 60));

        btnTerminar.setBackground(new java.awt.Color(31, 30, 24));
        btnTerminar.setForeground(new java.awt.Color(102, 102, 102));
        btnTerminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/terminarMesa.png"))); // NOI18N
        btnTerminar.setBorder(null);
        btnTerminar.setContentAreaFilled(false);
        btnTerminar.setFocusable(false);
        btnTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnTerminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(965, 170, 150, 60));

        btnPedidos.setBorder(null);
        btnPedidos.setContentAreaFilled(false);
        btnPedidos.setFocusable(false);
        btnPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPedidosMouseClicked(evt);
            }
        });
        jPanel2.add(btnPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 240, 160, 50));

        chkEstado.setBackground(new java.awt.Color(0, 51, 0));
        chkEstado.setForeground(new java.awt.Color(0, 102, 0));
        jPanel2.add(chkEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(848, 32, 20, 20));

        lblSesion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSesion.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(lblSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 31, 30, 20));

        lblMesa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMesa.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(lblMesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 30, 20));

        jScrollPane1.setBorder(new javax.swing.border.MatteBorder(null));

        tblSesiones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSesiones.setFocusable(false);
        tblSesiones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSesionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSesiones);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 886, 228));

        txtPrecio.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(0, 0, 0));
        txtPrecio.setText("5");
        txtPrecio.setBorder(null);
        jPanel2.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 33, 15, -1));

        lblFecha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(403, 31, 70, 20));

        lblHora.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHora.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(lblHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(573, 31, 60, 20));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/frmSesion.png"))); // NOI18N
        jLabel6.setText(" ");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, -1));

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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        listarSesiones();
    }//GEN-LAST:event_formWindowOpened


    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        try {
            // Verificar si el campo de precio está vacío
            if (txtPrecio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un precio.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si el campo de precio está vacío
            }

            // Verificar si ya existe una sesión en juego con la misma mesa
            int idMesa = Integer.parseInt(lblMesa.getText());
            if (objSesion.existeSesionEnJuego(idMesa)) {
                JOptionPane.showMessageDialog(this, "Ya hay una sesión en juego en esta mesa.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si ya existe una sesión en juego
            }

            lblSesion.setText(objSesion.generarCodigoSesion().toString());
            // Bloquea el código
            lblSesion.requestFocus();
            objSesion.iniciarSesion(Integer.parseInt(lblSesion.getText()), Integer.parseInt(txtPrecio.getText()),
                    Integer.parseInt(lblMesa.getText()), chkEstado.isSelected());
            listarSesiones();
            JOptionPane.showMessageDialog(this, "Sesión registrada correctamente.");
            // Desactivar el botón de iniciar mesa
            btnIniciar.setEnabled(false);
            // Cambiar el estado de la mesa a "Ocupado"
            objMesa.darBajaMesa(Integer.parseInt(lblMesa.getText()));
            chkEstado.setSelected(false);
            txtPrecio.setText("5");
            mostrarNumSesion();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el precio.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar la sesión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Imprime el rastreo de la pila para depuración
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarActionPerformed
        try {
            // Obtener el ID de sesión y el ID de la mesa
            int idSesion = Integer.parseInt(lblSesion.getText());
            int idMesa = Integer.parseInt(lblMesa.getText());

            // Terminar la sesión
            objSesion.terminarSesion(idSesion);
            objSesion.actualizarDuracion(idSesion);
            objSesion.actualizarMontoTotal(idSesion);

            // Actualizar el estado de la mesa a "Libre"
            objMesa.darAltaMesa(idMesa); // Suponiendo que darAltaMesa actualiza el estado a "Libre"

            // Actualizar la interfaz de usuario u otras acciones necesarias
            chkEstado.setSelected(true);
            mostrarNumSesion();
            listarSesiones();
            JOptionPane.showMessageDialog(this, "Sesión terminada correctamente.");
            btnIniciar.setEnabled(true);
            btnTerminar.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al terminar la sesión: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTerminarActionPerformed

    private void btnPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPedidosMouseClicked
        int codigoSesion = 0;
        int codigoMesa = 0;
        String nombreCliente = "";
        jdPedido objPed = new jdPedido((Frame) SwingUtilities.getWindowAncestor(this),
                true, codigoSesion, codigoMesa, nombreCliente);
        objPed.setLocationRelativeTo(this);
        objPed.setVisible(true);
    }//GEN-LAST:event_btnPedidosMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        btnIniciar.setEnabled(true);
        btnTerminar.setEnabled(false);
        tblSesiones.clearSelection();
    }//GEN-LAST:event_formMouseClicked

    private void tblSesionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSesionesMouseClicked
        lblSesion.setText(String.valueOf(tblSesiones.getValueAt(tblSesiones.getSelectedRow(), 0)));
        btnIniciar.setEnabled(false);
        btnTerminar.setEnabled(true);
    }//GEN-LAST:event_tblSesionesMouseClicked

    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("recursos/icono.png"));  
        return retValue;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnTerminar;
    private javax.swing.JCheckBox chkEstado;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblMesa;
    private javax.swing.JLabel lblSesion;
    private javax.swing.JTable tblSesiones;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
