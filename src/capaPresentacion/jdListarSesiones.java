package capaPresentacion;

import capaInterfaz.componentes.ScrollBar;
import capaInterfaz.componentes.Table;
import capaNegocio.clsMesa;
import capaNegocio.clsSesion;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class jdListarSesiones extends javax.swing.JDialog {

    clsSesion objSesion = new clsSesion();
    clsMesa objMesa = new clsMesa();
    private int ses = 0; //Corresponde al código de la sesión.
    private int mes = 0; //Corresponde al código de la mesa.

    public jdListarSesiones(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tblSesiones = new Table(); 
        jScrollPane1.setViewportView(tblSesiones);
        jScrollPane1.setVerticalScrollBar(new ScrollBar());

        tblSesiones.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSesionesMouseClicked(evt);
            }
        });
    }

    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("recursos/icono.png"));  
        return retValue;
    }
    
    private void listarMesas() {

        ResultSet rsMesas = null;
        DefaultComboBoxModel modeloMesas = new DefaultComboBoxModel();
        cboMesas.setModel(modeloMesas);

        try {
            rsMesas = objMesa.listarMesas();
            modeloMesas.addElement("                     ");
            while (rsMesas.next()) {
                modeloMesas.addElement(rsMesas.getString("nombremesa"));
            }
        } catch (Exception e) {
        }
    }

    private void listarSesionesPorMesaSeleccionada() {

        ResultSet rsSesiones = null;
        Vector registro;
        String estado = "";
        String nombreMesaSeleccionada = (String) cboMesas.getSelectedItem();

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        modelo.addColumn("SESIÓN");
        modelo.addColumn("MESA");
        modelo.addColumn("ESTADO");

        try {
            int idMesaSeleccionada = objMesa.obtenerIDMesa(nombreMesaSeleccionada);
            rsSesiones = objSesion.listarSesionesPorMesa(idMesaSeleccionada);

            while (rsSesiones.next()) {

                registro = new Vector();
                registro.add(0, rsSesiones.getInt("idsesion"));
                registro.add(1, rsSesiones.getString("idmesa"));
                if (rsSesiones.getString("estado").equals("t")) {
                    estado = "En juego";
                } else {
                    estado = "Terminado";
                }
                registro.add(2, estado);
                modelo.addRow(registro);
            }
            tblSesiones.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void pasarDatos(int cod, int cod2) {
        try {
            ses = cod;
            mes = cod2;
            
            // Mostrar cuadro de diálogo de confirmación
            Object[] options = {"Si", "No"};
            int opcion = JOptionPane.showOptionDialog(this,
                    "¿Estás seguro de agregar pedidos "
                    + "a la sesión "+ ses + " de la mesa "+ mes +"?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (opcion == JOptionPane.YES_OPTION) {
                // Obtener una referencia al formulario jdPedido existente
                jdPedido objPedido = obtenerFormularioPedidoAbierto();
                if (objPedido != null) {
                    // Si el formulario jdPedido ya está abierto, actualizar la sesión en ese formulario
                    objPedido.actualizarSesion(ses, mes);
                } else {
                    
                    
                }
            }
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

// Método para obtener una referencia al formulario jdPedido abierto
    private jdPedido obtenerFormularioPedidoAbierto() {
        for (Window window : Window.getWindows()) {
            if (window instanceof jdPedido && window.isShowing()) {
                return (jdPedido) window;
            }
        }
        return null;
    }

    public int getSes() {
        return ses;
    }

    public int getMes() {
        return mes;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cboMesas = new capaInterfaz.componentes.Combobox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSesiones = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(":: SELECCIONAR MESA ::");
        setIconImage(getIconImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboMesas.setFocusable(false);
        cboMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMesasActionPerformed(evt);
            }
        });
        jPanel1.add(cboMesas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 170, -1));

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
        tblSesiones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSesionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSesiones);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 317, 170));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/frmSesiones.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        listarMesas();   
    }//GEN-LAST:event_formWindowOpened

    private void tblSesionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSesionesMouseClicked
        int cod = Integer.parseInt(String.valueOf(tblSesiones.getValueAt
        (tblSesiones.getSelectedRow(), 0)));
        int cod2 = Integer.parseInt(String.valueOf(tblSesiones.getValueAt(tblSesiones.getSelectedRow(), 1)));
        
        pasarDatos(cod, cod2);
    }//GEN-LAST:event_tblSesionesMouseClicked

    private void cboMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMesasActionPerformed
        listarSesionesPorMesaSeleccionada();
    }//GEN-LAST:event_cboMesasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private capaInterfaz.componentes.Combobox cboMesas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSesiones;
    // End of variables declaration//GEN-END:variables
}
