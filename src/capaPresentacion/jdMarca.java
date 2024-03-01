/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaPresentacion;

import capaInterfaz.componentes.ScrollBar;
import capaInterfaz.componentes.Table;
import capaNegocio.clsMarca;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class jdMarca extends javax.swing.JDialog {

    clsMarca objMarca = new clsMarca();

    public jdMarca(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tblMarca = new Table();
        jScrollPane1.setViewportView(tblMarca);
        jScrollPane1.setVerticalScrollBar(new ScrollBar());

        tblMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMarcaMouseClicked(evt);
            }
        });
        // Ocultar el campo txtID
        txtID.setVisible(false);
    }

    private void listarMarcas() {
        ResultSet rsMarcas = null;
        Vector registro;

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelo.addColumn("MARCA");
        modelo.addColumn("NOMBRE");

        try {
            rsMarcas = objMarca.listarMarcas();
            while (rsMarcas.next()) {
                registro = new Vector();
                registro.add(0, rsMarcas.getInt("idmarca"));
                registro.add(1, rsMarcas.getString("nombremarca"));
                modelo.addRow(registro);
            }
            tblMarca.setModel(modelo);

            tblMarca.getColumnModel().getColumn(0).setPreferredWidth(15);
            tblMarca.getColumnModel().getColumn(1).setPreferredWidth(150);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void buscarMarca() {
        ResultSet datos = null;
        try {
            if (txtID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el id que desea buscar.",
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                datos = objMarca.buscarDatos(Integer.parseInt(txtID.getText()));

                if (datos.next()) {
                    txtNombre.setText(datos.getString("nombremarca"));
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

    private void limpiarControles() {
        txtID.setText("");
        txtNombre.setText("");
        txtID.requestFocus();
        tblMarca.clearSelection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        txtID = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMarca = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(":: FORMULARIO DE MARCA ::");
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

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre.setBorder(null);
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 87, 200, 20));

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setBorder(null);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setFocusable(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 120, 40));

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setBorder(null);
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.setFocusable(false);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 185, 120, 40));

        btnModificar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modificar1.png"))); // NOI18N
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
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        txtID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtID.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 20));

        jScrollPane1.setBorder(new javax.swing.border.MatteBorder(null));

        tblMarca.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMarcaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMarca);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 32, 310, 190));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/frmMarca.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 250));

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
            String idMarca = txtID.getText().trim();
            int id = Integer.parseInt(idMarca);

            if (objMarca.existenciaMarca(id)) {
                Object[] options = {"Si", "No"};
                int opcion = JOptionPane.showOptionDialog(this,
                        "¿Está seguro de eliminar esta marca?",
                        "Eliminar marca",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (opcion == JOptionPane.YES_OPTION) {
                    objMarca.eliminarMarca(id);
                    // Limpiar los controles y actualizar la lista de categorías
                    limpiarControles();
                    listarMarcas();
                    JOptionPane.showMessageDialog(this, "Marca eliminada correctamente.",
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    btnRegistrar.setText("REGISTRAR");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se puede eliminar la marca debido a que no se encuentra registrada.",
                        "Eliminar marca",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar la marca.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarControles();
        listarMarcas();
        btnRegistrar.setText("REGISTRAR");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            if (txtID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un id para modificar la marca.",
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String idMarca = txtID.getText().trim();
                int id = Integer.parseInt(idMarca);

                if (objMarca.existenciaMarca(id)) {
                    Object[] options = {"Si", "No"};
                    int opcion = JOptionPane.showOptionDialog(this,
                            "¿Está seguro de modificar esta marca?",
                            "Modificar marca",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (opcion == JOptionPane.YES_OPTION) {
                        objMarca.modificarMarca(Integer.parseInt(txtID.getText()), txtNombre.getText());
                        limpiarControles();
                        listarMarcas();
                        JOptionPane.showMessageDialog(this, "Marca modificada correctamente.",
                                "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        btnRegistrar.setText("REGISTRAR");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede modificar la marca debido a que no se encuentra registrada.",
                            "Modificar marca",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar la marca.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            if (btnRegistrar.getText().equals("REGISTRAR")) {
                btnRegistrar.setText("GUARDAR");
                limpiarControles();
                txtID.setText(objMarca.generarCodigoMarca().toString());
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
                    objMarca.registrarMarca(Integer.parseInt(txtID.getText()), txtNombre.getText());
                    limpiarControles();
                    listarMarcas();
                    JOptionPane.showMessageDialog(this, "Marca registrada correctamente.",
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar la marca: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void tblMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMarcaMouseClicked
        txtID.setText(String.valueOf(tblMarca.getValueAt(tblMarca.getSelectedRow(), 0)));
        buscarMarca();
    }//GEN-LAST:event_tblMarcaMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        listarMarcas();
    }//GEN-LAST:event_formWindowOpened

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        tblMarca.clearSelection();
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMarca;
    private javax.swing.JLabel txtID;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
