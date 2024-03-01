package capaPresentacion;

import capaInterfaz.componentes.ScrollBar;
import capaInterfaz.componentes.Table;
import capaNegocio.clsCategoria;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class jdCategoria extends javax.swing.JDialog {

    clsCategoria objCategoria = new clsCategoria();

    public jdCategoria(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        // Configurar la tabla tblCategoria con Table y TableHeader
        tblCategoria = new Table(); // Utilizamos la instancia existente de tblCategoria
        jScrollPane1.setViewportView(tblCategoria);
        jScrollPane1.setVerticalScrollBar(new ScrollBar());

        // Agregar MouseListener a la tabla para manejar el evento de clic
        tblCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoriaMouseClicked(evt);
            }
        });

        // Ocultar el campo txtID
        txtID.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtID = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategoria = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(":: FORMULARIO DE CATEGORÍA ::");
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

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre.setBorder(null);
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 190, -1));

        btnEliminar.setBorder(null);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setFocusable(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 110, 50));

        btnLimpiar.setBorder(null);
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.setFocusable(false);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 120, 50));

        txtID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtID.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 20, 20));

        jScrollPane1.setBorder(new javax.swing.border.MatteBorder(null));

        tblCategoria.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCategoria.getTableHeader().setReorderingAllowed(false);
        tblCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCategoria);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 35, 310, 185));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/frmCategoria.png"))); // NOI18N
        jLabel1.setText("NOMBRE:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listarCategorias() {
        ResultSet rsCategorias = null;
        Vector registro;

        // Crear un modelo de tabla no editable
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que todas las celdas no sean editables
            }
        };

        modelo.addColumn("CATEGORÍA");
        modelo.addColumn("NOMBRE");

        try {
            rsCategorias = objCategoria.listarCategorias();
            while (rsCategorias.next()) {
                registro = new Vector();
                registro.add(0, rsCategorias.getInt("idcategoria"));
                registro.add(1, rsCategorias.getString("nombrecategoria"));
                modelo.addRow(registro);
            }
            tblCategoria.setModel(modelo);

            tblCategoria.getColumnModel().getColumn(0).setPreferredWidth(15);
            tblCategoria.getColumnModel().getColumn(1).setPreferredWidth(150);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void limpiarControles() {
        txtID.setText("");
        txtNombre.setText("");
        txtID.requestFocus();
        tblCategoria.clearSelection();
    }

    private void buscarCategorias() {
        ResultSet datos = null;
        try {
            if (txtID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el id que desea buscar.",
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                datos = objCategoria.buscarDatos(Integer.parseInt(txtID.getText()));
                if (datos.next()) {
                    txtNombre.setText(datos.getString("nombrecategoria"));
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


    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        try {
            if (txtID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un id para modificar la categoría.",
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String idCategoria = txtID.getText().trim();
                int id = Integer.parseInt(idCategoria);
                if (objCategoria.existenciaCategoria(id)) {
                    Object[] options = {"Si", "No"};
                    int opcion = JOptionPane.showOptionDialog(this,
                            "¿Está seguro de modificar esta categoría?",
                            "Modificar categoría",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (opcion == JOptionPane.YES_OPTION) {
                        objCategoria.modificarCategoria(Integer.parseInt(txtID.getText()), txtNombre.getText());
                        limpiarControles();
                        listarCategorias();
                        JOptionPane.showMessageDialog(this, "Categoría modificada correctamente.",
                                "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        btnRegistrar.setText("REGISTRAR");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede modificar la categoría debido a que no se encuentra registrada.",
                            "Modificar categoría",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar la categoría", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            if (btnRegistrar.getText().equals("REGISTRAR")) {
                btnRegistrar.setText("GUARDAR");
                limpiarControles();
                txtID.setText(objCategoria.generarCodigoCategoria().toString());
                // Bloquea el código
                txtNombre.requestFocus();

            } else { // Guardar
                // Validar que los campos no estén vacíos
                if (txtNombre.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El campo 'Nombre' no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                    btnRegistrar.setText("REGISTRAR");
                } else {
                    btnRegistrar.setText("REGISTRAR");
                    objCategoria.registrarCategoria(Integer.parseInt(txtID.getText()), txtNombre.getText());
                    limpiarControles();
                    listarCategorias();
                    JOptionPane.showMessageDialog(this, "Categoría registrada correctamente.",
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar la categoría: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            // Obtener el ID ingresado como texto
            String idCategoria = txtID.getText().trim();

            // Convertir el ID a entero para eliminar la categoría
            int id = Integer.parseInt(idCategoria);

            // Validar si la categoría con el ID especificado existe en la tabla
            if (objCategoria.existenciaCategoria(id)) {
                Object[] options = {"Si", "No"};
                int opcion = JOptionPane.showOptionDialog(this,
                        "¿Está seguro de eliminar esta categoría?",
                        "Eliminar categoría",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (opcion == JOptionPane.YES_OPTION) {
                    // Eliminar la categoría
                    objCategoria.eliminarCategoria(id);
                    // Limpiar los controles y actualizar la lista de categorías
                    limpiarControles();
                    listarCategorias();
                    JOptionPane.showMessageDialog(this, "Categoría eliminada correctamente.",
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    btnRegistrar.setText("REGISTRAR");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se puede eliminar la categoría debido a que no se encuentra registrada.",
                        "Eliminar categoría",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar la categoría.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarControles();
        listarCategorias();
        btnRegistrar.setText("REGISTRAR");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        listarCategorias();
    }//GEN-LAST:event_formWindowOpened

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        tblCategoria.clearSelection();
    }//GEN-LAST:event_formMouseClicked

    private void tblCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoriaMouseClicked
        txtID.setText(String.valueOf(tblCategoria.getValueAt(tblCategoria.getSelectedRow(), 0)));
        buscarCategorias();
    }//GEN-LAST:event_tblCategoriaMouseClicked

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
    private javax.swing.JTable tblCategoria;
    private javax.swing.JLabel txtID;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
