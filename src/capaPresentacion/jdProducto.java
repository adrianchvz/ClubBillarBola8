package capaPresentacion;

import capaInterfaz.componentes.ScrollBar;
import capaInterfaz.componentes.Table;
import capaNegocio.clsCategoria;
import capaNegocio.clsMarca;
import capaNegocio.clsProducto;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class jdProducto extends javax.swing.JDialog {

    clsProducto objProducto = new clsProducto();
    clsMarca objMarca = new clsMarca();
    clsCategoria objCategoria = new clsCategoria();

    public jdProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tblProductos = new Table();
        jScrollPane1.setViewportView(tblProductos);
        jScrollPane1.setVerticalScrollBar(new ScrollBar());

        // Agregar MouseListener a la tabla para manejar el evento de clic
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });

        txtID.setVisible(false);
    }

    private void listarProductos() {

        ResultSet rsProductos = null;
        String vigencia = "";
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Vector registro;

        modelo.addColumn("PRODUCTO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("PRECIO");
        modelo.addColumn("STOCK");
        modelo.addColumn("ESTADO");

        try {
            rsProductos = objProducto.listarProductos();
            while (rsProductos.next()) {

                registro = new Vector();

                registro.add(0, rsProductos.getInt("idproducto"));
                registro.add(1, rsProductos.getString("nombresproducto"));
                registro.add(2, rsProductos.getString("precioproducto"));
                registro.add(3, rsProductos.getString("stock"));
                //vigencia es booleana True / False
                if (rsProductos.getString("estadoproducto").equals("t")) {
                    vigencia = "Disponible";
                } else {
                    vigencia = "Agotado";
                }
                registro.add(4, vigencia);
                modelo.addRow(registro);
            }

            tblProductos.setModel(modelo);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblProductos.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(60);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(60);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar productos - " + e.getMessage());
        }
    }

    private void limpiarControles() {
        txtID.setText("");
        txtNombres.setText("");
        txtPrecio.setText("");
        spnStock.setValue(0);
        chkEstado.setSelected(false);
        cboMaterial.setSelectedIndex(-1);
        cboTamaño.setSelectedIndex(-1);
        cboSabor.setSelectedIndex(-1);
        cboMarca.setSelectedIndex(-1);
        cboCategoria.setSelectedIndex(-1);
        txtID.requestFocus();
        tblProductos.clearSelection();
    }

    private void listarMarcas() {

        ResultSet rsMarcas = null;
        DefaultComboBoxModel modeloMarcas = new DefaultComboBoxModel();
        cboMarca.setModel(modeloMarcas);

        try {
            rsMarcas = objMarca.listarMarcasPorNombre();
            modeloMarcas.addElement("");
            while (rsMarcas.next()) {
                modeloMarcas.addElement(rsMarcas.getString("nombremarca"));
            }
        } catch (Exception e) {
        }
    }

    private void listarCategorias() {

        ResultSet rsCategorias = null;
        DefaultComboBoxModel modeloCategorias = new DefaultComboBoxModel();
        cboCategoria.setModel(modeloCategorias);

        try {
            rsCategorias = objCategoria.listarCategoriasPorNombre();
            modeloCategorias.addElement("");
            while (rsCategorias.next()) {
                modeloCategorias.addElement(rsCategorias.getString("nombrecategoria"));
            }
        } catch (Exception e) {
        }
    }

    private String generarNombreProducto() {
        StringBuilder nombreProducto = new StringBuilder();

        // Agregar el nombre de la categoría seleccionada
        if (cboCategoria.getSelectedItem() != null
                && !cboCategoria.getSelectedItem().toString().equals("")) {
            nombreProducto.append(cboCategoria.getSelectedItem().toString()).append(" ");
        }

        // Agregar el nombre del tamaño seleccionado
        if (cboMarca.getSelectedItem() != null
                && !cboMarca.getSelectedItem().toString().equals("")) {
            nombreProducto.append(cboMarca.getSelectedItem().toString()).append(" ");
        }

        // Agregar el nombre del tamaño seleccionado
        if (cboTamaño.getSelectedItem() != null
                && !cboTamaño.getSelectedItem().toString().equals("----------")) {
            nombreProducto.append(cboTamaño.getSelectedItem().toString()).append(" ");
        }

        // Agregar el nombre del tamaño seleccionado
        if (cboMaterial.getSelectedItem() != null
                && !cboMaterial.getSelectedItem().toString().equals("----------")) {
            nombreProducto.append(" DE ").append(cboMaterial.getSelectedItem().toString());
        }

        // Agregar el nombre del tamaño seleccionado
        if (cboSabor.getSelectedItem() != null
                && !cboSabor.getSelectedItem().toString().equals("----------")) {
            nombreProducto.append(" DE ").append(cboSabor.getSelectedItem().toString());
        }

        return nombreProducto.toString().trim();
    }

    private void buscarProductos() {
        ResultSet rsProducto = null;
        try {
            if (txtID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un id de producto a buscar.",
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                rsProducto = objProducto.buscarProductos(Integer.parseInt(txtID.getText()));
                if (rsProducto.next()) {
                    txtNombres.setText(rsProducto.getString("nombresproducto"));
                    txtPrecio.setText(rsProducto.getString("precioproducto"));
                    spnStock.setValue(rsProducto.getObject("stock"));
                    chkEstado.setSelected(rsProducto.getBoolean("estadoproducto"));
                    cboMaterial.setSelectedItem(rsProducto.getString("material"));
                    cboTamaño.setSelectedItem(rsProducto.getString("tamaño"));
                    cboSabor.setSelectedItem(rsProducto.getString("sabor"));
                    cboMarca.setSelectedItem(rsProducto.getString("nombremarca"));
                    cboCategoria.setSelectedItem(rsProducto.getString("nombrecategoria"));
                    rsProducto.close();
                } else {
                    JOptionPane.showMessageDialog(this, "El código de producto no se encuentra registrado.",
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    limpiarControles();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar producto" + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnDarBaja = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtPrecio = new javax.swing.JTextField();
        spnStock = new javax.swing.JSpinner();
        chkEstado = new javax.swing.JCheckBox();
        cboTamaño = new capaInterfaz.componentes.Combobox();
        txtNombres = new javax.swing.JLabel();
        cboSabor = new capaInterfaz.componentes.Combobox();
        txtID = new javax.swing.JLabel();
        cboMaterial = new capaInterfaz.componentes.Combobox();
        cboMarca = new capaInterfaz.componentes.Combobox();
        cboCategoria = new capaInterfaz.componentes.Combobox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(":: FORMULARIO DE PRODUCTO ::");
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
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 265, -1, -1));

        btnModificar.setBorder(null);
        btnModificar.setContentAreaFilled(false);
        btnModificar.setFocusable(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 320, 130, 40));

        btnEliminar.setBorder(null);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setFocusable(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 450, 120, 40));

        btnDarBaja.setBorder(null);
        btnDarBaja.setContentAreaFilled(false);
        btnDarBaja.setFocusable(false);
        btnDarBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDarBajaActionPerformed(evt);
            }
        });
        jPanel1.add(btnDarBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 390, 140, 40));

        btnLimpiar.setBorder(null);
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.setFocusable(false);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 520, 120, 40));

        txtPrecio.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(0, 0, 0));
        txtPrecio.setBorder(null);
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(421, 101, 40, -1));

        spnStock.setBorder(null);
        jPanel1.add(spnStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 99, 50, 20));

        chkEstado.setBorder(null);
        chkEstado.setContentAreaFilled(false);
        jPanel1.add(chkEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(635, 101, -1, -1));

        cboTamaño.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "----------", "PEQUEÑO", "GRANDE" }));
        cboTamaño.setSelectedIndex(-1);
        cboTamaño.setFocusable(false);
        cboTamaño.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboTamañoMouseClicked(evt);
            }
        });
        cboTamaño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTamañoActionPerformed(evt);
            }
        });
        jPanel1.add(cboTamaño, new org.netbeans.lib.awtextra.AbsoluteConstraints(387, 160, 150, 25));

        txtNombres.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNombres.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 101, 300, 16));

        cboSabor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "----------", "TRIGO", "FRESA", "PIÑA", "ARANDANO", "MACA", "KOLA INGLESA", "NARANJA", "LIMA", "WATERMELON", "FRAMBUESA", "CAMU CAMU", "DURAZNO", "LIMÓN", "ALOE" }));
        cboSabor.setSelectedIndex(-1);
        cboSabor.setFocusable(false);
        cboSabor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboSaborMouseClicked(evt);
            }
        });
        cboSabor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSaborActionPerformed(evt);
            }
        });
        jPanel1.add(cboSabor, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, 150, 25));

        txtID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtID.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 30, 20));

        cboMaterial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "----------", "VIDRIO", "PLÁSTICO", "LATA", "CAJA" }));
        cboMaterial.setSelectedIndex(-1);
        cboMaterial.setFocusable(false);
        cboMaterial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboMaterialMouseClicked(evt);
            }
        });
        cboMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaterialActionPerformed(evt);
            }
        });
        jPanel1.add(cboMaterial, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, 150, 25));

        cboMarca.setFocusable(false);
        cboMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboMarcaMouseClicked(evt);
            }
        });
        cboMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMarcaActionPerformed(evt);
            }
        });
        jPanel1.add(cboMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 160, 150, -1));

        cboCategoria.setFocusable(false);
        cboCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboCategoriaMouseClicked(evt);
            }
        });
        cboCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCategoriaActionPerformed(evt);
            }
        });
        jPanel1.add(cboCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 150, -1));

        jScrollPane1.setBorder(new javax.swing.border.MatteBorder(null));

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 730, 330));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/frmProducto.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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
                txtID.setText(objProducto.generarCodigoProducto().toString());
                txtNombres.requestFocus();
                cboMaterial.setSelectedIndex(-1);
                cboSabor.setSelectedIndex(-1);
                cboTamaño.setSelectedIndex(-1);
                chkEstado.setSelected(true);

            } else {
                if (txtPrecio.getText().isEmpty() || txtNombres.getText().isEmpty()
                        || cboMarca.getSelectedIndex() == -1 || cboCategoria.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(this, "Los campos Nombre, Precio, Marca y Categoría no pueden ser vacíos.",
                            "Mensaje", JOptionPane.ERROR_MESSAGE);
                    btnRegistrar.setText("REGISTRAR");
                    limpiarControles();
                } else {
                    btnRegistrar.setText("REGISTRAR");
                    //evaluar campos
                    String material = cboMaterial.getSelectedItem() != null ? cboMaterial.getSelectedItem().toString() : null;
                    String sabor = cboSabor.getSelectedItem() != null ? cboSabor.getSelectedItem().toString() : null;
                    String tamaño = cboTamaño.getSelectedItem() != null ? cboTamaño.getSelectedItem().toString() : null;
                    objProducto.registrarProducto(Integer.parseInt(txtID.getText()), txtNombres.getText(),
                            Double.parseDouble(txtPrecio.getText()), (int) spnStock.getValue(), chkEstado.isSelected(),
                            material, tamaño, sabor,
                            objMarca.obtenerIDMarca(cboMarca.getSelectedItem().toString()), objCategoria.obtenerIDCategoria(cboCategoria.getSelectedItem().toString()));
                    JOptionPane.showMessageDialog(this, "Producto registrado correctamente.",
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    limpiarControles();
                    listarProductos();
                    cboMaterial.setSelectedIndex(-1);
                    cboSabor.setSelectedIndex(-1);
                    cboTamaño.setSelectedIndex(-1);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            if (txtID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código de producto para poder modificar.",
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Obtener el ID ingresado como texto
                String idProducto = txtID.getText().trim();
                int id = Integer.parseInt(idProducto);
                if (objProducto.existenciaProducto(id)) {
                    Object[] options = {"Si", "No"};
                    int opcion = JOptionPane.showOptionDialog(this,
                            "¿Está seguro de modificar este producto?",
                            "Modificar Producto",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (opcion == JOptionPane.YES_NO_OPTION) {
                        objProducto.modificarProducto(Integer.parseInt(txtID.getText()), txtNombres.getText(),
                                Double.parseDouble(txtPrecio.getText()), (int) spnStock.getValue(), chkEstado.isSelected(),
                                cboMaterial.getSelectedItem().toString(), cboTamaño.getSelectedItem().toString(), cboSabor.getSelectedItem().toString(),
                                objMarca.obtenerIDMarca(cboMarca.getSelectedItem().toString()),
                                objCategoria.obtenerIDCategoria(cboCategoria.getSelectedItem().toString()));
                        limpiarControles();
                        listarProductos();
                        JOptionPane.showMessageDialog(this, "Producto modificado correctamente.",
                                "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        btnRegistrar.setText("REGISTRAR");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede modificar el producto debido a que no se encuentra registrado.",
                            "Modificar producto",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar producto", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        listarProductos();
        listarMarcas();
        listarCategorias();
    }//GEN-LAST:event_formWindowOpened

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtNombres.setText("");
        txtPrecio.setText("");
        spnStock.setValue(0);
        chkEstado.setSelected(false);
        cboMaterial.setSelectedIndex(-1);
        cboTamaño.setSelectedIndex(-1);
        cboSabor.setSelectedIndex(-1);
        cboMarca.setSelectedIndex(-1);
        cboCategoria.setSelectedIndex(-1);
        listarProductos();
        btnRegistrar.setText("REGISTRAR");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnDarBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDarBajaActionPerformed
        try {
            if (txtID.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un id para dar de baja al producto.",
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String idProducto = txtID.getText().trim();
                int id = Integer.parseInt(idProducto);

                if (objProducto.existenciaProducto(id)) {
                    Object[] options = {"Si", "No"};
                    int opcion = JOptionPane.showOptionDialog(this,
                            "¿Está seguro de dar de baja a este producto?",
                            "Dar de Baja Producto",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (opcion == JOptionPane.YES_OPTION) {
                        objProducto.darBajaProducto(Integer.parseInt(txtID.getText()));
                        limpiarControles();
                        listarProductos();
                        JOptionPane.showMessageDialog(this, "Producto dado de baja correctamente.",
                                "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        btnRegistrar.setText("REGISTRAR");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede dar de baja al producto debido a que no se "
                            + "encuentra registrado.",
                            "Dar de Baja Producto",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al dar de baja al producto.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnDarBajaActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            // Obtener el ID ingresado como texto
            String idProducto = txtID.getText().trim();
            int id = Integer.parseInt(idProducto);
            // Validar si la categoría con el ID especificado existe en la tabla
            if (objProducto.existenciaProducto(id)) {
                Object[] options = {"Si", "No"};
                int opcion = JOptionPane.showOptionDialog(this,
                        "¿Está seguro de eliminar este producto?",
                        "Eliminar producto",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (opcion == JOptionPane.YES_OPTION) {
                    objProducto.eliminarProducto(id);
                    limpiarControles();
                    listarProductos();
                    JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.",
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    btnRegistrar.setText("REGISTRAR");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se puede eliminar el producto debido a que no se encuentra registrado.",
                        "Eliminar producto",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el producto.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        txtID.setText(String.valueOf(tblProductos.getValueAt(tblProductos.getSelectedRow(), 0)));
        buscarProductos();
    }//GEN-LAST:event_tblProductosMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        tblProductos.clearSelection();
    }//GEN-LAST:event_formMouseClicked

    private void cboCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboCategoriaMouseClicked

    }//GEN-LAST:event_cboCategoriaMouseClicked

    private void cboMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboMarcaMouseClicked

    }//GEN-LAST:event_cboMarcaMouseClicked

    private void cboTamañoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboTamañoMouseClicked

    }//GEN-LAST:event_cboTamañoMouseClicked

    private void cboSaborMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboSaborMouseClicked

    }//GEN-LAST:event_cboSaborMouseClicked

    private void cboMaterialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboMaterialMouseClicked

    }//GEN-LAST:event_cboMaterialMouseClicked


    private void cboCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCategoriaActionPerformed
        txtNombres.setText(generarNombreProducto());
    }//GEN-LAST:event_cboCategoriaActionPerformed

    private void cboMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMarcaActionPerformed
        txtNombres.setText(generarNombreProducto());
    }//GEN-LAST:event_cboMarcaActionPerformed

    private void cboTamañoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTamañoActionPerformed
        txtNombres.setText(generarNombreProducto());
    }//GEN-LAST:event_cboTamañoActionPerformed

    private void cboSaborActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSaborActionPerformed
        txtNombres.setText(generarNombreProducto());
    }//GEN-LAST:event_cboSaborActionPerformed

    private void cboMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaterialActionPerformed
        txtNombres.setText(generarNombreProducto());
    }//GEN-LAST:event_cboMaterialActionPerformed

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
    private capaInterfaz.componentes.Combobox cboCategoria;
    private capaInterfaz.componentes.Combobox cboMarca;
    private capaInterfaz.componentes.Combobox cboMaterial;
    private capaInterfaz.componentes.Combobox cboSabor;
    private capaInterfaz.componentes.Combobox cboTamaño;
    private javax.swing.JCheckBox chkEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spnStock;
    private javax.swing.JTable tblProductos;
    private javax.swing.JLabel txtID;
    private javax.swing.JLabel txtNombres;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
