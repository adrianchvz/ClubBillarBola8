/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaPresentacion;

import capaInterfaz.componentes.ScrollBar;
import capaInterfaz.componentes.Table;
import capaNegocio.clsCliente;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class jdCliente extends javax.swing.JDialog {

    clsCliente objCliente = new clsCliente();

    public jdCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tblCliente = new Table();
        jScrollPane1.setViewportView(tblCliente);
        jScrollPane1.setVerticalScrollBar(new ScrollBar());

        // Agregar MouseListener a la tabla para manejar el evento de clic
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });

        txtID.setVisible(false);
    }

    private void listarClientes() {
        ResultSet rsClientes = null;
        Vector registro;

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("CLIENTE");
        modelo.addColumn("NOMBRE");

        try {
            rsClientes = objCliente.listarClientes();
            while (rsClientes.next()) {
                registro = new Vector();
                registro.add(0, rsClientes.getInt("idcliente"));
                registro.add(1, rsClientes.getString("nombrescliente"));
                modelo.addRow(registro);
            }
            tblCliente.setModel(modelo);

            tblCliente.getColumnModel().getColumn(0).setPreferredWidth(15);
            tblCliente.getColumnModel().getColumn(1).setPreferredWidth(150);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void limpiarControles() {
        txtID.setText("");
        txtNombre.setText("");
        txtID.requestFocus();
        tblCliente.clearSelection();
    }

    private void buscarCliente() {
        ResultSet datos = null;
        try {
            if (txtID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el id que desea buscar.",
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                datos = objCliente.buscarDatos(Integer.parseInt(txtID.getText()));
                if (datos.next()) {
                    txtNombre.setText(datos.getString("nombrescliente"));
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

        jPanel1 = new javax.swing.JPanel();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        txtID = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(":: FORMULARIO DE CLIENTE ::");
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

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEliminar.setBorder(null);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setFocusable(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 110, 40));

        btnLimpiar.setBorder(null);
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.setFocusable(false);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 120, 40));

        btnModificar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modificar2.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.setBorder(null);
        btnModificar.setContentAreaFilled(false);
        btnModificar.setFocusable(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, -1, -1));

        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cliente1.png"))); // NOI18N
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.setBorder(null);
        btnRegistrar.setContentAreaFilled(false);
        btnRegistrar.setFocusable(false);
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 130, -1, -1));

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre.setBorder(null);
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 89, 210, -1));

        txtID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtID.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 20));

        jScrollPane1.setBorder(new javax.swing.border.MatteBorder(null));

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCliente);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 32, 310, 190));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/frmCliente.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 250));

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

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {

            String idCliente = txtID.getText().trim();
            int id = Integer.parseInt(idCliente);

            if (objCliente.existenciaCliente(id)) {
                Object[] options = {"Si", "No"};
                int opcion = JOptionPane.showOptionDialog(this,
                        "¿Está seguro de eliminar a este cliente?",
                        "Eliminar cliente",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (opcion == JOptionPane.YES_OPTION) {
                    objCliente.eliminarCliente(id);
                    // Limpiar los controles y actualizar la lista de categorías
                    limpiarControles();
                    listarClientes();
                    JOptionPane.showMessageDialog(this, "Cliente eliminado(a) correctamente.",
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    btnRegistrar.setText("REGISTRAR");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se puede eliminar al cliente debido a que no se encuentra registrado(a).",
                        "Eliminar cliente",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el cliente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarControles();
        listarClientes();
        btnRegistrar.setText("REGISTRAR");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            if (txtID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un id para modificar el cliente.",
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String idCliente = txtID.getText().trim();
                int id = Integer.parseInt(idCliente);
                if (objCliente.existenciaCliente(id)) {
                    Object[] options = {"Si", "No"};
                    int opcion = JOptionPane.showOptionDialog(this,
                            "¿Está seguro de modificar este cliente?",
                            "Modificar cliente",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (opcion == JOptionPane.YES_OPTION) {
                        objCliente.modificarCliente(Integer.parseInt(txtID.getText()), txtNombre.getText());
                        limpiarControles();
                        listarClientes();
                        JOptionPane.showMessageDialog(this, "Cliente modificado(a) correctamente.",
                                "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        btnRegistrar.setText("REGISTRAR");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede modificar al cliente debido a que no se encuentra registrado(a).",
                            "Modificar cliente",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar al cliente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            if (btnRegistrar.getText().equals("REGISTRAR")) {
                btnRegistrar.setText("GUARDAR");
                limpiarControles();
                txtID.setText(objCliente.generarCodigoCliente().toString());
                // Bloquea el código
                txtNombre.requestFocus();

            } else { // Guardar
                // Validar que los campos no estén vacíos
                if (txtNombre.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El campo 'Nombre' no puede estar vacío.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    btnRegistrar.setText("REGISTRAR");
                } else {
                    btnRegistrar.setText("REGISTRAR");
                    objCliente.registrarCliente(Integer.parseInt(txtID.getText()), txtNombre.getText());
                    limpiarControles();
                    listarClientes();
                    JOptionPane.showMessageDialog(this, "Cliente registrado(a) correctamente.",
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE); 
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void tblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteMouseClicked
        txtID.setText(String.valueOf(tblCliente.getValueAt(tblCliente.getSelectedRow(), 0)));
        buscarCliente();
    }//GEN-LAST:event_tblClienteMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        listarClientes();
    }//GEN-LAST:event_formWindowOpened

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        tblCliente.clearSelection();
    }//GEN-LAST:event_formMouseClicked

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("recursos/icono.png"));
        return retValue;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCliente;
    private javax.swing.JLabel txtID;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
