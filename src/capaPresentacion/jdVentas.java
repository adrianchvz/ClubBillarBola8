/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaPresentacion;

import capaInterfaz.componentes.ScrollBar;
import capaInterfaz.componentes.Table;
import capaNegocio.clsProducto;
import capaNegocio.clsSesionUsuario;
import capaNegocio.clsUsuario;
import capaNegocio.clsVenta;
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
public class jdVentas extends javax.swing.JDialog {

    clsVenta objVenta = new clsVenta();
    clsUsuario objUsuario = new clsUsuario();
    clsProducto objProducto = new clsProducto();
    private float total = 0;
    private int idProductoSeleccionado = -1;
    private int idVentaSeleccionado;
    private Timer timer;

    public jdVentas(java.awt.Frame parent, boolean modal, String codCliente) {
        super(parent, modal);
        initComponents();
        mostrarNumVenta();
        listarDetalleVenta();
        actualizarCliente(codCliente);

        // Inicializar el Timer para actualizar la fecha y hora cada segundo
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblFechaHora.setText(obtenerFechaHoraActual());
            }
        });
        timer.start(); // Iniciar el Timer

        tblDetalle = new Table();
        jScrollPane1.setViewportView(tblDetalle);
        jScrollPane1.setVerticalScrollBar(new ScrollBar());

        tblVentas = new Table();
        jScrollPane2.setViewportView(tblVentas);
        jScrollPane2.setVerticalScrollBar(new ScrollBar());

        tblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVentasMouseClicked(evt);
            }
        });

        tblDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleMouseClicked(evt);
            }
        });

    }

    public void actualizarCliente(String cli) {
        lblCliente.setText(String.valueOf(cli));
    }

    private String obtenerFechaHoraActual() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date fechaHoraActual = new Date();
        return dateFormat.format(fechaHoraActual);
    }

    private void mostrarNumVenta() {
        try {
            txtVenta.setText(String.valueOf(objVenta.generarCodigoVenta()));
        } catch (Exception e) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblFechaHora = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        btnBuscarCliente = new javax.swing.JButton();
        lblUsuario = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        btnAgregarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        lblTotalVenta = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        btnPagar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtVenta = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(":: FORMULARIO DE VENTAS ::");
        setIconImage(getIconImage());
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFechaHora.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFechaHora.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(lblFechaHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 72, 130, 22));

        lblCliente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCliente.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(lblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 126, 130, 20));

        btnBuscarCliente.setBorder(null);
        btnBuscarCliente.setContentAreaFilled(false);
        btnBuscarCliente.setFocusable(false);
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 30, 30));

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 126, 130, 20));

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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 215, 538, 180));

        btnAgregarProducto.setBorder(null);
        btnAgregarProducto.setContentAreaFilled(false);
        btnAgregarProducto.setFocusable(false);
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, 20, 20));

        btnEliminarProducto.setBorder(null);
        btnEliminarProducto.setContentAreaFilled(false);
        btnEliminarProducto.setFocusable(false);
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 166, 20, 20));

        lblTotalVenta.setBackground(new java.awt.Color(0, 0, 0));
        lblTotalVenta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalVenta.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalVenta.setText("0.00");
        jPanel2.add(lblTotalVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 424, 40, 20));

        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/registrarVenta.png"))); // NOI18N
        btnRegistrar.setBorder(null);
        btnRegistrar.setContentAreaFilled(false);
        btnRegistrar.setFocusable(false);
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 465, 150, 60));

        jScrollPane2.setBorder(new javax.swing.border.MatteBorder(null));

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
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
        tblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVentasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblVentas);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 100, 570, 330));

        btnPagar.setBorder(null);
        btnPagar.setContentAreaFilled(false);
        btnPagar.setFocusable(false);
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });
        jPanel2.add(btnPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 470, 150, 40));

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
        jPanel2.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        txtVenta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtVenta.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(txtVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 73, 40, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/frmVentas.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1183, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        jdBuscarClientes objBuscarClientes = new jdBuscarClientes((Frame) SwingUtilities.getWindowAncestor(this), true);
        objBuscarClientes.setEsVenta(true);
        objBuscarClientes.setLocationRelativeTo(this);
        objBuscarClientes.setVisible(true);

    }//GEN-LAST:event_btnBuscarClienteActionPerformed

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

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        jdAñadirProducto objAgregarProd = new jdAñadirProducto((Frame) SwingUtilities.getWindowAncestor(this), true);
        objAgregarProd.setLocationRelativeTo(this);
        objAgregarProd.setVisible(true);
        int producto = objAgregarProd.getCod();
        int cantidad = objAgregarProd.getCant();

        agregarProducto(producto, cantidad);
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        if (idProductoSeleccionado != -1) { // Verificar si se ha seleccionado un producto
            String cantidadEliminarString = JOptionPane.showInputDialog(this, "Ingrese la cantidad que desea eliminar:", "Eliminar Producto", JOptionPane.QUESTION_MESSAGE);

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
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto para eliminar.");
        }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            // Mostrar cuadro de diálogo de confirmación
            Object[] options = {"Si", "No"};
            int opcion = JOptionPane.showOptionDialog(this,
                    "¿Confirma que desea registrar esta venta?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (!txtVenta.getText().isEmpty() && !lblTotalVenta.getText().isEmpty()
                    && !lblFechaHora.getText().isEmpty() && !lblCliente.getText().isEmpty()
                    && !lblUsuario.getText().isEmpty()) {
                if (opcion == JOptionPane.YES_OPTION) {
                    objVenta.registrar(Integer.parseInt(txtVenta.getText()),
                            Double.parseDouble(lblTotalVenta.getText()), lblCliente.getText(),
                            lblUsuario.getText(), tblDetalle);
                    JOptionPane.showMessageDialog(this, "Se registró la venta con éxito.");
                    limpiarControles();
                    listarVentas();
                    lblUsuario.setText(clsSesionUsuario.nombreUsuario);

                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos antes "
                        + "de registrar la venta.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar la venta:" + e.getMessage());
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        lblUsuario.setText(clsSesionUsuario.nombreUsuario);
        listarVentas();
        listarDetalleVenta();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        timer.stop();
    }//GEN-LAST:event_formWindowClosed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        lblUsuario.setText(clsSesionUsuario.nombreUsuario);
        tblVentas.clearSelection();
    }//GEN-LAST:event_formMouseClicked

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        try {
            // Verificar si hay una fila seleccionada en la tabla
            int filaSeleccionada = tblVentas.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una venta para realizar el pago.");
                return; // Salir del método si no hay una fila seleccionada
            }

            // Obtener el id de la venta seleccionada
            int idVentaSeleccionado = (int) tblVentas.getValueAt(filaSeleccionada, 0);

            // Mostrar cuadro de diálogo de confirmación
            Object[] options = {"Si", "No"};
            int opcion = JOptionPane.showOptionDialog(this,
                    "¿Confirma que desea pagar esta venta?",
                    "Confirmar Pago",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            // Si el usuario confirma el pago
            if (opcion == JOptionPane.YES_OPTION) {
                // Actualizar el estado de pago del pedido a true
                objVenta.actualizarEstadoPago(idVentaSeleccionado);
                JOptionPane.showMessageDialog(this, "Se registró el pago con éxito.");
                // Volver a listar los pedidos para actualizar la tabla de pedidos
                listarVentas();
                limpiarControles();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al pagar la venta - " + e.getMessage());
        }
    }//GEN-LAST:event_btnPagarActionPerformed

    private void tblVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentasMouseClicked
        btnRegistrar.setEnabled(false);
        idVentaSeleccionado = (int) tblVentas.getValueAt(tblVentas.getSelectedRow(), 0);

        try {
            txtVenta.setText(String.valueOf(tblVentas.getValueAt(tblVentas.getSelectedRow(), 0)));
            lblCliente.setText(String.valueOf(tblVentas.getValueAt(tblVentas.getSelectedRow(), 4)));

            int filaSeleccionada = tblVentas.getSelectedRow();
            int idVenta = Integer.parseInt(tblVentas.getValueAt(filaSeleccionada, 0).toString());

            // Obtener el detalle del pedido seleccionado
            ResultSet rsDetalle = objVenta.obtenerDetalleVenta(idVenta);

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
    }//GEN-LAST:event_tblVentasMouseClicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarControles();
        btnRegistrar.setEnabled(true);
        tblDetalle.clearSelection();
        tblVentas.clearSelection();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void agregarProducto(int producto, int cantidad) {
        if (producto != 0 && cantidad != 0) {
            ResultSet rs = null;
            try {
                DefaultTableModel modelo = (DefaultTableModel) tblDetalle.getModel();
                boolean repetido = false;
                int fila = -1;
                for (int i = 0; i < tblDetalle.getRowCount(); i++) {
                    if (Integer.parseInt(String.valueOf(tblDetalle.getValueAt(i, 0))) == producto) {
                        repetido = true;
                        fila = i;
                    }
                }

                if (repetido) {
                    int aux = Integer.parseInt(String.valueOf(tblDetalle.getValueAt(fila, 3)));
                    cantidad += aux;
                    modelo.removeRow(fila);
                }

                int stock = objProducto.getStock(producto);
                if (cantidad > stock) {
                    cantidad = stock;
                    JOptionPane.showMessageDialog(rootPane, "Stock insuficiente");
                }

                rs = objProducto.buscarProductos(producto);
                while (rs.next()) {
                    modelo.addRow(new Object[]{rs.getString("idproducto"),
                        rs.getString("nombresproducto"), rs.getString("precioproducto"), cantidad,
                        cantidad * (rs.getFloat("precioproducto"))});
                }
                tblDetalle.setModel(modelo);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e.getMessage());
            }
        }
        calcularTotal();
    }

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("recursos/icono.png"));
        return retValue;
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
                        JOptionPane.showMessageDialog(rootPane, "Ingrese una cantidad menor o igual a la actual.");
                    }
                    break; // Salir del bucle después de encontrar la fila correspondiente
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

    private void listarDetalleVenta() {
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

    private void limpiarControles() {
        lblCliente.setText("");
        tblDetalle.setModel(new DefaultTableModel());
        calcularTotal();
        listarDetalleVenta();
        mostrarNumVenta();
    }

    private void listarVentas() {

        ResultSet rsVentas = null;
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Vector registro;
        String estado = "";

        modelo.addColumn("VENTA");
        modelo.addColumn("FECHA - HORA");
        modelo.addColumn("TOTAL VENTA");
        modelo.addColumn("ESTADO");
        modelo.addColumn("CLIENTE");
        modelo.addColumn("USUARIO");

        try {
            rsVentas = objVenta.listarVentas();
            while (rsVentas.next()) {

                registro = new Vector();

                registro.add(0, rsVentas.getInt("idventa"));
                // Convertir la fecha y hora de String a Date
                String fechaHoraString = rsVentas.getString("fechahoraventa");
                SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date fechaHoraVenta = sdfInput.parse(fechaHoraString);

                // Formatear la fecha y hora en el formato deseado ('yyyymmdd hh:mi:ss tt')
                SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                String fechaHoraFormateada = sdfOutput.format(fechaHoraVenta);

                registro.add(1, fechaHoraFormateada);
                registro.add(2, rsVentas.getDouble("totalventa"));
                if (rsVentas.getString("estadopago").equals("t")) {
                    estado = "PAGADO";
                } else {
                    estado = "NO PAGADO";
                }
                registro.add(3, estado);
                registro.add(4, rsVentas.getString("nombrescliente"));
                registro.add(5, rsVentas.getString("nombresusuario"));

                modelo.addRow(registro);
            }

            tblVentas.setModel(modelo);
            tblVentas.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblVentas.getColumnModel().getColumn(1).setPreferredWidth(130);
            tblVentas.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblVentas.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblVentas.getColumnModel().getColumn(4).setPreferredWidth(60);
            tblVentas.getColumnModel().getColumn(5).setPreferredWidth(60);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar las ventas - " + e.getMessage());
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnPagar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblFechaHora;
    private javax.swing.JLabel lblTotalVenta;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tblDetalle;
    private javax.swing.JTable tblVentas;
    private javax.swing.JLabel txtVenta;
    // End of variables declaration//GEN-END:variables
}
