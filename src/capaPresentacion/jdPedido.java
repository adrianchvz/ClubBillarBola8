package capaPresentacion;

import capaInterfaz.componentes.ScrollBar;
import capaInterfaz.componentes.Table;
import capaNegocio.clsPedido;
import capaNegocio.clsProducto;
import capaNegocio.clsSesionUsuario;
import capaNegocio.clsUsuario;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class jdPedido extends javax.swing.JDialog {

    clsPedido objPedido = new clsPedido();
    clsUsuario objUsuario = new clsUsuario();
    clsProducto objProducto = new clsProducto();
    private float total = 0;
    private int idProductoSeleccionado = -1;
    private int idPedidoSeleccionado;

    public jdPedido(java.awt.Frame parent, boolean modal, int codSesion, int codMesa, String codCliente) {
        super(parent, modal);
        initComponents();
        mostrarNumPedido();
        listarDetallePedido();
        actualizarSesion(codSesion, codMesa);
        actualizarCliente(codCliente);

        tblPedidos = new Table();
        jScrollPane2.setViewportView(tblPedidos);
        jScrollPane2.setVerticalScrollBar(new ScrollBar());

        tblDetalle = new Table();
        jScrollPane1.setViewportView(tblDetalle);
        jScrollPane1.setVerticalScrollBar(new ScrollBar());

        tblPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidosMouseClicked(evt);
            }
        });

        tblDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleMouseClicked(evt);
            }
        });
    }

    public void actualizarSesion(int ses, int mes) {
        txtSesion.setText(String.valueOf(ses));
        txtMesa.setText(String.valueOf(mes));
    }

    public void actualizarCliente(String cli) {
        lblCliente.setText(String.valueOf(cli));
    }

    private void mostrarNumPedido() {
        try {
            txtPedido.setText(String.valueOf(objPedido.generarCodigoPedido()));
        } catch (Exception e) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnBuscarSesion = new javax.swing.JButton();
        btnBuscarCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        btnAgregarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        lblTotalVenta = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnPagar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();
        btnLimpiar = new javax.swing.JButton();
        txtPedido = new javax.swing.JLabel();
        txtSesion = new javax.swing.JLabel();
        txtMesa = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(":: FORMULARIO DE PEDIDO ::");
        setIconImage(getIconImage());
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });
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

        btnBuscarSesion.setBorder(null);
        btnBuscarSesion.setContentAreaFilled(false);
        btnBuscarSesion.setFocusable(false);
        btnBuscarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarSesionMouseClicked(evt);
            }
        });
        btnBuscarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarSesionActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 30, 30));

        btnBuscarCliente.setBorder(null);
        btnBuscarCliente.setContentAreaFilled(false);
        btnBuscarCliente.setFocusable(false);
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 30, 30));

        jScrollPane1.setBorder(new javax.swing.border.MatteBorder(null));

        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetalle);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 583, 180));

        btnAgregarProducto.setBorder(null);
        btnAgregarProducto.setContentAreaFilled(false);
        btnAgregarProducto.setFocusable(false);
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(531, 180, 24, 20));

        btnEliminarProducto.setBorder(null);
        btnEliminarProducto.setContentAreaFilled(false);
        btnEliminarProducto.setFocusable(false);
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, 24, 20));

        lblTotalVenta.setBackground(new java.awt.Color(0, 0, 0));
        lblTotalVenta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalVenta.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalVenta.setText("0.00");
        jPanel1.add(lblTotalVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(558, 433, -1, -1));

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 133, 130, 20));

        lblCliente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCliente.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(lblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 135, 130, 16));

        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/registrarPedido.png"))); // NOI18N
        btnRegistrar.setBorder(null);
        btnRegistrar.setContentAreaFilled(false);
        btnRegistrar.setFocusable(false);
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 433, 150, 60));

        btnActualizar.setBorder(null);
        btnActualizar.setContentAreaFilled(false);
        btnActualizar.setFocusable(false);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 430, 150, 45));

        btnPagar.setBorder(null);
        btnPagar.setContentAreaFilled(false);
        btnPagar.setFocusable(false);
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });
        jPanel1.add(btnPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 430, 150, 45));

        jScrollPane2.setBorder(new javax.swing.border.MatteBorder(null));

        tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPedidos);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(645, 110, 570, 290));

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/limpiar1.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setBorder(null);
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.setFocusable(false);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, -1, -1));

        txtPedido.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtPedido.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 50, 20));

        txtSesion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSesion.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 79, 50, 20));

        txtMesa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtMesa.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtMesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 79, 20, 20));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/frmPedidos.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarSesionMouseClicked
        try {
            Frame frame = new Frame(); // Crea una nueva instancia de Frame si no tienes una disponible
            jdListarSesiones objListarSesiones; // Declara la variable primero
            objListarSesiones = new jdListarSesiones(frame, true); // Asigna el resultado de la creación del objeto jdPedido
            objListarSesiones.setLocationRelativeTo(frame);
            objListarSesiones.setVisible(true);
        } catch (Exception e) {
            // Manejar la excepción aquí
        }
    }//GEN-LAST:event_btnBuscarSesionMouseClicked

    private void btnBuscarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarSesionActionPerformed
        jdListarSesiones objListarSes = new jdListarSesiones((Frame) SwingUtilities.getWindowAncestor(this), true);
        objListarSes.setLocationRelativeTo(this);
        objListarSes.setVisible(true);
    }//GEN-LAST:event_btnBuscarSesionActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        lblUsuario.setText(clsSesionUsuario.nombreUsuario);
        listarPedidos();
        listarDetallePedido();
    }//GEN-LAST:event_formWindowOpened

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        jdBuscarClientes objBuscarClientes = new jdBuscarClientes((Frame) SwingUtilities.getWindowAncestor(this), true);
        objBuscarClientes.setLocationRelativeTo(this);
        objBuscarClientes.setVisible(true);
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        jdAñadirProducto objAgregarProd = new jdAñadirProducto((Frame) SwingUtilities.getWindowAncestor(this), true);
        objAgregarProd.setLocationRelativeTo(this);
        objAgregarProd.setVisible(true);
        int producto = objAgregarProd.getCod();
        int cantidad = objAgregarProd.getCant();

        agregarProducto(producto, cantidad);
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            // Mostrar cuadro de diálogo de confirmación
            Object[] options = {"Si", "No"};
            int opcion = JOptionPane.showOptionDialog(this,
                    "¿Confirma que desea registrar este pedido?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            // Verificar si los campos no están vacíos
            if (!txtPedido.getText().isEmpty() && !lblTotalVenta.getText().isEmpty()
                    && !txtSesion.getText().isEmpty() && !lblUsuario.getText().isEmpty()
                    && !lblCliente.getText().isEmpty()) {
                if (opcion == JOptionPane.YES_OPTION) {
                    objPedido.registrarPedido(Integer.parseInt(txtPedido.getText()),
                            Double.parseDouble(lblTotalVenta.getText()),
                            Integer.parseInt(txtSesion.getText()), lblUsuario.getText(),
                            lblCliente.getText(), tblDetalle);
                    JOptionPane.showMessageDialog(this, "Se registró el pedido con éxito.", 
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    limpiarControles();
                    listarPedidos();
                    lblUsuario.setText(clsSesionUsuario.nombreUsuario);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos antes de registrar el pedido.", 
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar el pedido:" + e.getMessage());
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void tblPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidosMouseClicked
        btnRegistrar.setEnabled(false);
        idPedidoSeleccionado = (int) tblPedidos.getValueAt(tblPedidos.getSelectedRow(), 0);
        try {
            txtPedido.setText(String.valueOf(tblPedidos.getValueAt(tblPedidos.getSelectedRow(), 0)));
            txtSesion.setText(String.valueOf(tblPedidos.getValueAt(tblPedidos.getSelectedRow(), 1)));
            txtMesa.setText(String.valueOf(tblPedidos.getValueAt(tblPedidos.getSelectedRow(), 2)));
            lblCliente.setText(String.valueOf(tblPedidos.getValueAt(tblPedidos.getSelectedRow(), 6)));

            int filaSeleccionada = tblPedidos.getSelectedRow();
            int idPedido = Integer.parseInt(tblPedidos.getValueAt(filaSeleccionada, 0).toString()); // Suponiendo que la primera columna es el idpedido

            // Obtener el detalle del pedido seleccionado
            ResultSet rsDetalle = objPedido.obtenerDetallePedido(idPedido);

            // Crear un modelo de tabla para el detalle del pedido
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            modelo.addColumn("CÓDIGO");
            modelo.addColumn("PRODUCTO");
            modelo.addColumn("PRECIO");
            modelo.addColumn("CANTIDAD");
            modelo.addColumn("NUEVO PRECIO");

            // Llenar el modelo con los datos obtenidos del detalle del pedido
            while (rsDetalle.next()) {
                Object[] fila = {
                    rsDetalle.getInt("idproducto"),
                    rsDetalle.getString("nombresproducto"),
                    rsDetalle.getFloat("precioproducto"),
                    rsDetalle.getInt("cantidad"),
                    rsDetalle.getFloat("nuevoprecio")
                };
                modelo.addRow(fila);
            }

            // Establecer el modelo de la tabla de detalle con los datos obtenidos
            tblDetalle.setModel(modelo);
            tblDetalle.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblDetalle.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblDetalle.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblDetalle.getColumnModel().getColumn(3).setPreferredWidth(60);
            tblDetalle.getColumnModel().getColumn(4).setPreferredWidth(80);

        } catch (Exception e) {
            e.printStackTrace(); // Manejo adecuado de la excepción
        }
        calcularTotal();
    }//GEN-LAST:event_tblPedidosMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            // Verificar si hay una fila seleccionada en la tabla
            int filaSeleccionada = tblPedidos.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un pedido para "
                        + "poder actualizar.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                return; // Salir del método si no hay una fila seleccionada
            }

            // Mostrar cuadro de diálogo de confirmación
            Object[] options = {"Si", "No"};
            int opcion = JOptionPane.showOptionDialog(this,
                    "¿Confirma que desea actualizar este pedido?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (opcion == JOptionPane.YES_OPTION) {
                objPedido.actualizarPedido(Integer.parseInt(txtPedido.getText()),
                        Double.parseDouble(lblTotalVenta.getText()),
                        Integer.parseInt(txtSesion.getText()), lblUsuario.getText(),
                        lblCliente.getText(), tblDetalle);
                JOptionPane.showMessageDialog(this, "Se actualizó el pedido con éxito.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                limpiarControles();
                listarPedidos();
                lblUsuario.setText(clsSesionUsuario.nombreUsuario);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "El pedido no se ha podido realizar debido a que la cantidad que "
                    + "desea actualizar es mayor al stock disponible.", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tblDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleMouseClicked
        // Obtener el índice de la fila seleccionada
        int filaSeleccionada = tblDetalle.getSelectedRow();
        if (filaSeleccionada != -1) { // Verificar si se ha seleccionado una fila
            // Obtener el ID del producto de la fila seleccionada (columna 0 en tu modelo de tabla)
            int idProducto = Integer.parseInt(String.valueOf(tblDetalle.getValueAt(filaSeleccionada, 0)));
            // Guardar el ID del producto en una variable para usarlo posteriormente
            idProductoSeleccionado = idProducto;
        }
    }//GEN-LAST:event_tblDetalleMouseClicked

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        if (idProductoSeleccionado != -1) { // Verificar si se ha seleccionado un producto
            String cantidadEliminarString = JOptionPane.showInputDialog(this, "Ingrese la cantidad que desea eliminar:", 
                    "Eliminar Producto", JOptionPane.QUESTION_MESSAGE);

            if (cantidadEliminarString != null && !cantidadEliminarString.isEmpty()) {
                try {
                    int cantidadEliminar = Integer.parseInt(cantidadEliminarString);
                    // Llamar al método eliminarProducto con el ID del producto seleccionado y la cantidad a eliminar.
                    eliminarProducto(idProductoSeleccionado, cantidadEliminar);
                    idProductoSeleccionado = -1; // Restablecer el ID del producto seleccionado después de eliminarlo
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "La cantidad ingresada no es válida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto para eliminar.", 
                    "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        try {
            // Verificar si hay una fila seleccionada en la tabla
            int filaSeleccionada = tblPedidos.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un pedido para "
                        + "realizar el pago.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                return; // Salir del método si no hay una fila seleccionada
            }

            // Obtener el id de la venta seleccionada
            int idPedidoSeleccionado = (int) tblPedidos.getValueAt(filaSeleccionada, 0);

            // Mostrar cuadro de diálogo de confirmación
            Object[] options = {"Si", "No"};
            int opcion = JOptionPane.showOptionDialog(this,
                    "¿Confirma que desea pagar este pedido?",
                    "Confirmar Pago",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            // Si el usuario confirma el pago
            if (opcion == JOptionPane.YES_OPTION) {
                // Actualizar el estado de pago del pedido a true
                objPedido.actualizarEstadoPago(idPedidoSeleccionado, true);
                JOptionPane.showMessageDialog(this, "Se registró el pago con éxito.", 
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                // Volver a listar los pedidos para actualizar la tabla de pedidos
                listarPedidos();
                limpiarControles();
                lblUsuario.setText(clsSesionUsuario.nombreUsuario);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al pagar el pedido - " + e.getMessage());
        }
    }//GEN-LAST:event_btnPagarActionPerformed

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost

    }//GEN-LAST:event_formFocusLost

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        lblUsuario.setText(clsSesionUsuario.nombreUsuario);
        tblPedidos.clearSelection();
    }//GEN-LAST:event_formMouseClicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarControles();
        btnRegistrar.setEnabled(true);
        tblDetalle.clearSelection();
        tblPedidos.clearSelection();
        lblUsuario.setText(clsSesionUsuario.nombreUsuario);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void agregarProducto(int producto, int cantidad) {
        if (producto != 0 && cantidad != 0) {
            try {
                DefaultTableModel modelo = (DefaultTableModel) tblDetalle.getModel();
                int fila = -1;
                for (int i = 0; i < tblDetalle.getRowCount(); i++) {
                    if (Integer.parseInt(String.valueOf(tblDetalle.getValueAt(i, 0))) == producto) {
                        fila = i;
                        break; // Salir del bucle al encontrar el producto
                    }
                }

                if (fila != -1) {
                    // Si el producto ya está en la tabla, actualiza su cantidad
                    int cantidadExistente = Integer.parseInt(String.valueOf(tblDetalle.getValueAt(fila, 3)));
                    cantidad += cantidadExistente;
                    modelo.setValueAt(cantidad, fila, 3); // Actualizar la cantidad en la fila existente
                    calcularNuevoPrecio(fila);
                } else {

                    ResultSet rs = objProducto.buscarProductos(producto);
                    while (rs.next()) {
                        modelo.addRow(new Object[]{rs.getString("idproducto"),
                            rs.getString("nombresproducto"), rs.getString("precioproducto"), cantidad,
                            cantidad * (rs.getFloat("precioproducto"))});
                    }
                }

                tblDetalle.setModel(modelo);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e.getMessage());
            }
        }
        calcularTotal();
    }

    private void eliminarProducto(int idproducto, int cantidadEliminar) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tblDetalle.getModel();
            int cantidadEliminada = 0; // Variable para almacenar la cantidad eliminada
            int filaAEliminar = -1; // Variable para almacenar la fila que se va a eliminar

            for (int i = 0; i < tblDetalle.getRowCount(); i++) {
                if (Integer.parseInt(String.valueOf(tblDetalle.getValueAt(i, 0))) == idproducto) {
                    int cantidadProducto = Integer.parseInt(String.valueOf(tblDetalle.getValueAt(i, 3))); // Cantidad actual del producto en la tabla
                    if (cantidadProducto >= cantidadEliminar) {
                        // Si la cantidad a eliminar es menor o igual a la cantidad actual del producto, 
                        //se realiza la eliminación
                        cantidadEliminada = cantidadEliminar;
                        modelo.setValueAt(cantidadProducto - cantidadEliminar, i, 3); // Restar la cantidad a eliminar a la cantidad actual del producto

                        // Si la cantidad actual del producto en la tabla es cero, almacenar la fila que se va a eliminar
                        if (cantidadProducto - cantidadEliminar == 0) {
                            filaAEliminar = i;
                        } else {
                            // Calcular el nuevo precio si la cantidad eliminada es menor que la cantidad total
                            calcularNuevoPrecio(i);
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Ingrese una cantidad menor o igual a la actual.", 
                                "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break; 
                }
            }

            if (filaAEliminar != -1) {
                // Obtener el ID del producto de la fila correspondiente
                int idProducto = Integer.parseInt(String.valueOf(tblDetalle.getValueAt(filaAEliminar, 0)));

                // Eliminar la fila del modelo de la tabla
                modelo.removeRow(filaAEliminar);

                // Reponer la cantidad eliminada en el stock del producto
                //objProducto.incrementarStock(idProducto, cantidadEliminada);
            }

            tblDetalle.setModel(modelo);

            // Volver a calcular el total
            calcularTotal();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void calcularNuevoPrecio(int fila) {
        try {
            double precioProducto = Double.parseDouble(String.valueOf(tblDetalle.getValueAt(fila, 2))); // Precio del producto
            int cantidadProducto = Integer.parseInt(String.valueOf(tblDetalle.getValueAt(fila, 3))); // Cantidad del producto
            double nuevoPrecio = precioProducto * cantidadProducto; // Nuevo precio calculado
            tblDetalle.setValueAt(nuevoPrecio, fila, 4); // Actualizar el nuevo precio en la tabla
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void listarDetallePedido() {
        ResultSet rsDetalle = null;

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("CÓDIGO");
        modelo.addColumn("PRODUCTO");
        modelo.addColumn("PRECIO");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("NUEVO PRECIO");
        tblDetalle.setModel(modelo);
        tblDetalle.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblDetalle.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblDetalle.getColumnModel().getColumn(2).setPreferredWidth(60);
        tblDetalle.getColumnModel().getColumn(3).setPreferredWidth(60);
        tblDetalle.getColumnModel().getColumn(4).setPreferredWidth(80);
    }

    private void limpiarControles() {
        lblUsuario.setText("");
        lblCliente.setText("");
        txtPedido.setText("");
        txtMesa.setText("");
        txtSesion.setText("");
        tblDetalle.setModel(new DefaultTableModel());
        calcularTotal();
        listarDetallePedido();
        mostrarNumPedido();
    }

    private void calcularTotal() {
        try {

            total = 0;

            for (int i = 0; i < tblDetalle.getRowCount(); i++) {
                total += Float.parseFloat(String.valueOf(tblDetalle.getValueAt(i, 4)));
            }

            lblTotalVenta.setText(String.valueOf(total));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }

    private void listarPedidos() {

        ResultSet rsPedidos = null;
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Vector registro;
        String estado = "";

        modelo.addColumn("PEDIDO");
        modelo.addColumn("SESIÓN");
        modelo.addColumn("MESA");
        modelo.addColumn("TOTAL");
        modelo.addColumn("ESTADO");
        modelo.addColumn("USUARIO");
        modelo.addColumn("CLIENTE");

        try {
            rsPedidos = objPedido.listarPedidos();
            while (rsPedidos.next()) {

                registro = new Vector();

                registro.add(0, rsPedidos.getInt("idpedido"));
                registro.add(1, rsPedidos.getInt("idsesion"));
                registro.add(2, rsPedidos.getInt("idmesa"));
                registro.add(3, rsPedidos.getDouble("totalpedido"));
                if (rsPedidos.getString("estadopago").equals("t")) {
                    estado = "PAGADO";
                } else {
                    estado = "NO PAGADO";
                }
                registro.add(4, estado);
                registro.add(5, rsPedidos.getString("nombresusuario"));
                registro.add(6, rsPedidos.getString("nombrescliente"));
                modelo.addRow(registro);
            }

            tblPedidos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar los pedidos - " + e.getMessage());
        }
    }

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("recursos/icono.png"));
        return retValue;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarSesion;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnPagar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblTotalVenta;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tblDetalle;
    private javax.swing.JTable tblPedidos;
    private javax.swing.JLabel txtMesa;
    private javax.swing.JLabel txtPedido;
    private javax.swing.JLabel txtSesion;
    // End of variables declaration//GEN-END:variables
}
