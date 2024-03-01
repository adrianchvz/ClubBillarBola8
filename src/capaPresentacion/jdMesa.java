/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaPresentacion;

import capaInterfaz.componentes.ScrollBar;
import capaInterfaz.componentes.Table;
import capaNegocio.clsMesa;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adrian
 */
public class jdMesa extends javax.swing.JDialog {

    clsMesa objMesa = new clsMesa();

    public jdMesa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tblMesa = new Table();
        jScrollPane1.setViewportView(tblMesa);
        jScrollPane1.setVerticalScrollBar(new ScrollBar());

        // Agregar MouseListener a la tabla para manejar el evento de clic
        tblMesa.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMesaMouseClicked(evt);
            }
        });

        txtID.setVisible(false);
    }

    private void listarMesas() {
        ResultSet rsMesas = null;
        String estado = "";
        Vector registro;

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelo.addColumn("MESA");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("ESTADO");

        try {
            rsMesas = objMesa.listarMesas();
            while (rsMesas.next()) {
                registro = new Vector();
                registro.add(0, rsMesas.getInt("idmesa"));
                registro.add(1, rsMesas.getString("nombremesa"));
                if (rsMesas.getString("estado").equals("t")) {
                    estado = "Disponible";
                } else {
                    estado = "Ocupado";
                }
                registro.add(2, estado);
                modelo.addRow(registro);
            }
            tblMesa.setModel(modelo);
            tblMesa.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblMesa.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblMesa.getColumnModel().getColumn(2).setPreferredWidth(100);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void limpiarControles() {
        txtID.setText("");
        txtNombre.setText("");
        chkEstado.setSelected(false);
        txtID.requestFocus();
        tblMesa.clearSelection();
    }

    private void buscarMesas() {
        ResultSet datos = null;
        try {
            if (txtID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el id que desea buscar.",
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                datos = objMesa.buscarDatos(Integer.parseInt(txtID.getText()));
                if (datos.next()) {
                    txtNombre.setText(datos.getString("nombremesa"));
                    chkEstado.setSelected(datos.getBoolean("estado"));
                    datos.close();
                } else {
                    JOptionPane.showMessageDialog(this, "ID no registrado...");
                    limpiarControles();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        chkEstado = new javax.swing.JCheckBox();
        btnDarBaja = new javax.swing.JButton();
        txtID = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMesa = new javax.swing.JTable();
        fondo = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(":: FORMULARIO DE MESA ::");
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

        jPanel1.setBackground(new java.awt.Color(255, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre.setBorder(null);
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 88, 210, -1));

        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/registrar1.png"))); // NOI18N
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.setBorder(null);
        btnRegistrar.setContentAreaFilled(false);
        btnRegistrar.setFocusable(false);
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, -1, -1));

        btnModificar.setBorder(null);
        btnModificar.setContentAreaFilled(false);
        btnModificar.setFocusable(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 200, 130, 40));

        btnEliminar.setBorder(null);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setFocusable(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 307, 130, 40));

        btnLimpiar.setBorder(null);
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.setFocusable(false);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 360, 120, 40));

        chkEstado.setForeground(new java.awt.Color(19, 52, 18));
        chkEstado.setBorder(null);
        chkEstado.setContentAreaFilled(false);
        jPanel1.add(chkEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(324, 89, -1, -1));

        btnDarBaja.setBorder(null);
        btnDarBaja.setContentAreaFilled(false);
        btnDarBaja.setFocusable(false);
        btnDarBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDarBajaActionPerformed(evt);
            }
        });
        jPanel1.add(btnDarBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, 140, 40));

        txtID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtID.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 30, 20));

        jScrollPane1.setBorder(new javax.swing.border.MatteBorder(null));

        tblMesa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tblMesa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMesaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMesa);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 138, 333, 271));

        fondo.setForeground(new java.awt.Color(0, 0, 0));
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/frmMesa.png"))); // NOI18N
        jPanel1.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 531, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblMesaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMesaMouseClicked
        txtID.setText(String.valueOf(tblMesa.getValueAt(tblMesa.getSelectedRow(), 0)));
        buscarMesas();
    }//GEN-LAST:event_tblMesaMouseClicked

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            if (btnRegistrar.getText().equals("REGISTRAR")) {
                btnRegistrar.setText("GUARDAR");
                limpiarControles();
                txtID.setText(objMesa.generarCodigoMesa().toString());
                // Bloquea el código
                txtNombre.requestFocus();
                chkEstado.setSelected(true);

            } else { // Guardar
                // Validar que los campos no estén vacíos
                if (txtNombre.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El campo 'Nombre' no puede estar vacío.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    btnRegistrar.setText("REGISTRAR");
                } else {
                    btnRegistrar.setText("REGISTRAR");
                    objMesa.registrarMesa(Integer.parseInt(txtID.getText()), txtNombre.getText(),
                            this.chkEstado.isSelected());
                    limpiarControles();
                    listarMesas();
                    JOptionPane.showMessageDialog(this, "Mesa registrada correctamente.",
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar la mesa: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            if (txtID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un id para modificar la mesa",
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String idMesa = txtID.getText().trim();
                int id = Integer.parseInt(idMesa);

                if (objMesa.existenciaMesa(id)) {
                    Object[] options = {"Si", "No"};
                    int opcion = JOptionPane.showOptionDialog(this,
                            "¿Está seguro de modificar esta mesa?",
                            "Modificar mesa",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (opcion == JOptionPane.YES_OPTION) {
                        objMesa.modificarMesa(Integer.parseInt(txtID.getText()), txtNombre.getText(),
                                chkEstado.isSelected());
                        limpiarControles();
                        listarMesas();
                        JOptionPane.showMessageDialog(this, "Mesa modificada correctamente.",
                                "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        btnRegistrar.setText("REGISTRAR");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede modificar la mesa debido a que no se encuentra registrada.",
                            "Modificar mesa",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar la mesa.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {

            String idMesa = txtID.getText().trim();
            int id = Integer.parseInt(idMesa);

            if (objMesa.existenciaMesa(id)) {
                Object[] options = {"Si", "No"};
                int opcion = JOptionPane.showOptionDialog(this,
                        "¿Está seguro de eliminar esta mesa?",
                        "Eliminar mesa",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (opcion == JOptionPane.YES_OPTION) {
                    objMesa.eliminarMesa(id);
                    limpiarControles();
                    listarMesas();
                    JOptionPane.showMessageDialog(this, "Mesa eliminada correctamente.",
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    btnRegistrar.setText("REGISTRAR");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se puede eliminar la mesa debido a que no se encuentra registrada.",
                        "Eliminar mesa",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar la mesa.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarControles();
        listarMesas();
        btnRegistrar.setText("REGISTRAR");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        listarMesas();
    }//GEN-LAST:event_formWindowOpened

    private void btnDarBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDarBajaActionPerformed
        try {
            if (txtID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un id para dar de baja a la mesa.",
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String idMesa = txtID.getText().trim();
                int id = Integer.parseInt(idMesa);

                if (objMesa.existenciaMesa(id)) {
                    Object[] options = {"Si", "No"};
                    int opcion = JOptionPane.showOptionDialog(this,
                            "¿Está seguro de dar de baja a esta mesa?",
                            "Dar de Baja Mesa",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (opcion == JOptionPane.YES_OPTION) {
                        objMesa.darBajaMesa(Integer.parseInt(txtID.getText()));
                        limpiarControles();
                        listarMesas();
                        JOptionPane.showMessageDialog(this, "Mesa dada de baja correctamente.",
                                "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        btnRegistrar.setText("REGISTRAR");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede dar de baja a la mesa debido a que no se encuentra registrada.",
                            "Dar de Baja Mesa",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al dar de baja a la mesa.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnDarBajaActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        tblMesa.clearSelection();
    }//GEN-LAST:event_formMouseClicked

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("recursos/icono.png"));
        return retValue;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDarBaja;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JCheckBox chkEstado;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMesa;
    private javax.swing.JLabel txtID;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
