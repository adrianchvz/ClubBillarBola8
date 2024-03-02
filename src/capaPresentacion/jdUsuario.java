package capaPresentacion;

import capaInterfaz.componentes.ScrollBar;
import capaInterfaz.componentes.Table;
import capaNegocio.clsUsuario;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class jdUsuario extends javax.swing.JDialog {

    clsUsuario objUsuario = new clsUsuario();

    public jdUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tblUsuarios = new Table();
        jScrollPane1.setViewportView(tblUsuarios);
        jScrollPane1.setVerticalScrollBar(new ScrollBar());

        // Agregar MouseListener a la tabla para manejar el evento de clic
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });

        txtID.setVisible(false);
    }

    private void listarUsuarios() {
        ResultSet rsUsuarios = null;
        Vector registro;

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("USUARIO");
        modelo.addColumn("NOMBRES");
        modelo.addColumn("CARGO");

        try {
            rsUsuarios = objUsuario.listarUsuarios();
            while (rsUsuarios.next()) {
                registro = new Vector();
                registro.add(0, rsUsuarios.getInt("idusuario"));
                registro.add(1, rsUsuarios.getString("usuario"));
                registro.add(2, rsUsuarios.getString("nombresUsuario"));
                registro.add(3, rsUsuarios.getString("cargoUsuario"));
                modelo.addRow(registro);
            }
            tblUsuarios.setModel(modelo);
            tblUsuarios.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblUsuarios.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblUsuarios.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblUsuarios.getColumnModel().getColumn(3).setPreferredWidth(100);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void limpiarControles() {
        txtID.setText("");
        txtUsuario.setText("");
        txtContraseña.setText("");
        txtNombres.setText("");
        cboCargo.setSelectedIndex(-1);
        txtID.requestFocus();
        tblUsuarios.clearSelection();
    }

    private void buscarUsuarios() {
        ResultSet datos = null;
        try {
            if (txtID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el id que desea buscar.",
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                datos = objUsuario.buscarDatos(Integer.parseInt(txtID.getText()));
                if (datos.next()) {
                    txtUsuario.setText(datos.getString("usuario"));
                    txtContraseña.setText(datos.getString("contraseña"));
                    txtNombres.setText(datos.getString("nombresusuario"));
                    cboCargo.setSelectedItem(datos.getString("cargousuario"));
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
        txtContraseña = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtID = new javax.swing.JLabel();
        cboCargo = new capaInterfaz.componentes.Combobox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(":: FORMULARIO DE USUARIO ::");
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

        jPanel1.setBackground(new java.awt.Color(255, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtContraseña.setBackground(new java.awt.Color(255, 255, 255));
        txtContraseña.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtContraseña.setForeground(new java.awt.Color(0, 0, 0));
        txtContraseña.setBorder(null);
        txtContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseñaActionPerformed(evt);
            }
        });
        jPanel1.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 94, 90, -1));

        txtNombres.setBackground(new java.awt.Color(255, 255, 255));
        txtNombres.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNombres.setForeground(new java.awt.Color(0, 0, 0));
        txtNombres.setBorder(null);
        jPanel1.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 141, 220, -1));

        txtUsuario.setBackground(new java.awt.Color(255, 255, 255));
        txtUsuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(0, 0, 0));
        txtUsuario.setBorder(null);
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 93, 80, -1));

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
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 233, -1, -1));

        btnModificar.setBorder(null);
        btnModificar.setContentAreaFilled(false);
        btnModificar.setFocusable(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 130, 40));

        btnEliminar.setBorder(null);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setFocusable(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 290, 130, 40));

        btnLimpiar.setBorder(null);
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.setFocusable(false);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 120, 40));

        txtID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtID.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 30, 20));

        cboCargo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ADMINISTRADOR", "VENDEDOR" }));
        cboCargo.setSelectedIndex(-1);
        cboCargo.setFocusable(false);
        jPanel1.add(cboCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 190, 250, -1));

        jScrollPane1.setBorder(new javax.swing.border.MatteBorder(null));

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 35, 360, 290));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/frmUsuario.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, -1));

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

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            if (btnRegistrar.getText().equals("REGISTRAR")) {
                btnRegistrar.setText("GUARDAR");
                limpiarControles();
                txtID.setText(objUsuario.generarCodigoUsuario().toString());
                // Bloquea el código
                txtUsuario.requestFocus();

            } else { // Guardar
                // Validar que los campos no estén vacíos
                if (txtUsuario.getText().isEmpty() || txtContraseña.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Los campos de usuario y contraseña no pueden estar vacíos.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    btnRegistrar.setText("REGISTRAR");
                } else {
                    // Validar si ya existe un usuario con las mismas credenciales
                    if (objUsuario.verificarUsuariosIguales(txtUsuario.getText(), txtContraseña.getText())) {
                        JOptionPane.showMessageDialog(this, "Ya existe un usuario con estas credenciales.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        limpiarControles();
                        btnRegistrar.setText("REGISTRAR");
                        txtUsuario.requestFocusInWindow();
                        return;
                    }
                    
                    btnRegistrar.setText("REGISTRAR");
                    objUsuario.registrarUsuario(Integer.parseInt(txtID.getText()), txtUsuario.getText(), txtContraseña.getText(), txtNombres.getText(), cboCargo.getSelectedItem().toString());
                    limpiarControles();
                    listarUsuarios();
                    JOptionPane.showMessageDialog(this, "Usuario registrado(a) correctamente.",
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        try {
            if (txtID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un id para modificar el usuario");
            } else {
                String idUsuario = txtID.getText().trim();
                int id = Integer.parseInt(idUsuario);
                if (objUsuario.existenciaUsuario(id)) {
                    Object[] options = {"Si", "No"};
                    int opcion = JOptionPane.showOptionDialog(this,
                            "¿Está seguro de modificar este usuario?",
                            "Modificar usuario",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (opcion == JOptionPane.YES_OPTION) {
                        objUsuario.modificarUsuario(Integer.parseInt(txtID.getText()), txtUsuario.getText(), txtContraseña.getText(), txtNombres.getText(), cboCargo.getSelectedItem().toString());
                        limpiarControles();
                        listarUsuarios();
                        JOptionPane.showMessageDialog(this, "Usuario modificado(a) correctamente.",
                                "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        btnRegistrar.setText("REGISTRAR");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede modificar al usuario debido a que no se encuentra registrado(a).",
                            "Modificar usuario",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar el usuario.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            String idUsuario = txtID.getText().trim();
            int id = Integer.parseInt(idUsuario);
            if (objUsuario.existenciaUsuario(id)) {
                Object[] options = {"Si", "No"};
                int opcion = JOptionPane.showOptionDialog(this,
                        "¿Está seguro de eliminar a este usuario?",
                        "Eliminar usuario",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (opcion == JOptionPane.YES_OPTION) {
                    objUsuario.eliminarUsuario(id);
                    // Limpiar los controles y actualizar la lista de categorías
                    limpiarControles();
                    listarUsuarios();
                    JOptionPane.showMessageDialog(this, "Usuario eliminado(a) correctamente.",
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    btnRegistrar.setText("REGISTRAR");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se puede eliminar al usuario debido a que no se encuentra registrado(a).",
                        "Eliminar usuario",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar al usuario.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarControles();
        listarUsuarios();
        btnRegistrar.setText("REGISTRAR");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        txtID.setText(String.valueOf(tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 0)));
        buscarUsuarios();
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        listarUsuarios();
    }//GEN-LAST:event_formWindowOpened

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        tblUsuarios.clearSelection();
    }//GEN-LAST:event_formMouseClicked

    private void txtContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraseñaActionPerformed

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("recursos/icono.png"));
        return retValue;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private capaInterfaz.componentes.Combobox cboCargo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JLabel txtID;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
