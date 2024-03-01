package capaPresentacion;

import capaInterfaz.componentes.ScrollBar;
import capaInterfaz.componentes.Table;
import capaNegocio.clsInventario;
import capaNegocio.clsProducto;
import capaNegocio.clsSesionUsuario;
import capaNegocio.clsUsuario;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class jdInventario extends javax.swing.JDialog {

    clsInventario objInventario = new clsInventario();
    clsProducto objProducto = new clsProducto();
    clsUsuario objUsuario = new clsUsuario();

    private int idProductoSeleccionado = -1;
    private int idInventarioSeleccionado;
    private Timer timer;

    public jdInventario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        mostrarNumInventario();
        listarDetalleInventario();
        // Inicializar el Timer para actualizar la fecha y hora cada segundo
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarFechaHora();
            }
        });
        timer.start(); // Iniciar el Timer

        tblInventario = new Table();
        jScrollPane2.setViewportView(tblInventario);
        jScrollPane2.setVerticalScrollBar(new ScrollBar());

        tblDetalle = new Table();
        jScrollPane1.setViewportView(tblDetalle);
        jScrollPane1.setVerticalScrollBar(new ScrollBar());

        tblInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInventarioMouseClicked(evt);
            }
        });

        tblDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleMouseClicked(evt);
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

    private void listarInventarios() {

        ResultSet rsInventario = null;
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Vector registro;

        modelo.addColumn("INVENTARIO");
        modelo.addColumn("FECHA");
        modelo.addColumn("HORA");
        modelo.addColumn("USUARIO");

        try {
            rsInventario = objInventario.listarInventario();
            while (rsInventario.next()) {

                String fechaString = rsInventario.getString("fecha");
                String horaString = rsInventario.getString("hora");

                SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");
                SimpleDateFormat sdfOutput = new SimpleDateFormat("dd-MM-yyyy");

                Date fecha = sdfInput.parse(fechaString);
                Date hora = sdfHora.parse(horaString);

                String fechaFormateada = sdfOutput.format(fecha);
                String horaFormateada = sdfHora.format(hora);

                registro = new Vector();

                registro.add(0, rsInventario.getInt("idinventario"));
                registro.add(1, fechaFormateada);
                registro.add(2, horaFormateada);
                registro.add(3, rsInventario.getString("nombresusuario"));

                modelo.addRow(registro);
            }

            tblInventario.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al listar los inventarios - " + e.getMessage());
        }
    }

    private void limpiarControles() {
        txtInventario.setText("");
        lblFecha.setText("");
        lblHora.setText("");
        lblUsuario.setText("");
        txtInventario.requestFocus();
        tblDetalle.setModel(new DefaultTableModel());
        listarDetalleInventario();
        mostrarNumInventario();
    }

    private void mostrarNumInventario() {
        try {
            txtInventario.setText(String.valueOf(objInventario.generarCodigoInventario()));
        } catch (Exception e) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnAgregarProducto = new javax.swing.JButton();
        btnActualizarProducto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        btnRegistrar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblInventario = new javax.swing.JTable();
        lblUsuario = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        txtInventario = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(":: FORMULARIO DE INVENTARIO ::");
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

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setForeground(new java.awt.Color(153, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAgregarProducto.setBorder(null);
        btnAgregarProducto.setContentAreaFilled(false);
        btnAgregarProducto.setFocusable(false);
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(464, 125, 20, 20));

        btnActualizarProducto.setBorder(null);
        btnActualizarProducto.setContentAreaFilled(false);
        btnActualizarProducto.setFocusable(false);
        btnActualizarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarProductoActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(497, 125, 20, 20));

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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 170, 512, 230));

        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/registrarInventario.png"))); // NOI18N
        btnRegistrar.setBorder(null);
        btnRegistrar.setContentAreaFilled(false);
        btnRegistrar.setFocusable(false);
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(736, 378, 170, 60));

        jScrollPane2.setBorder(new javax.swing.border.MatteBorder(null));

        tblInventario.setModel(new javax.swing.table.DefaultTableModel(
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
        tblInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInventarioMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblInventario);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 466, 230));

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 80, 130, 20));

        lblFecha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 80, 83, 20));

        lblHora.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHora.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(lblHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 80, 60, 20));

        txtInventario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtInventario.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(txtInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 30, 20));

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
        jPanel2.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 413, -1, -1));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/frmInventario.png"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            if (tblDetalle.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese al menos un producto en el detalle del inventario.");
                return; // Salir del método sin registrar el inventario
            }

            // Mostrar cuadro de diálogo de confirmación
            Object[] options = {"Si", "No"};
            int opcion = JOptionPane.showOptionDialog(this,
                    "¿Confirma que desea registrar este inventario?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (!txtInventario.getText().isEmpty() && !lblFecha.getText().isEmpty()
                    && !lblHora.getText().isEmpty() && !lblUsuario.getText().isEmpty()) {
                if (opcion == JOptionPane.YES_OPTION) {
                    objInventario.registrarInventario(Integer.parseInt(txtInventario.getText()),
                            lblUsuario.getText(), tblDetalle);
                    JOptionPane.showMessageDialog(this, "Se registró el inventario con éxito.");
                    limpiarControles();
                    listarInventarios();
                    lblUsuario.setText(clsSesionUsuario.nombreUsuario);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos antes "
                        + "de registrar el inventario.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar el inventario:" + e.getMessage());
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        lblUsuario.setText(clsSesionUsuario.nombreUsuario);
        listarInventarios();
        listarDetalleInventario();
    }//GEN-LAST:event_formWindowOpened

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        jdAñadirProductoInventario objAgregarProd = new jdAñadirProductoInventario((Frame) SwingUtilities.getWindowAncestor(this), true);
        objAgregarProd.setLocationRelativeTo(this);
        objAgregarProd.setVisible(true);
        int producto = objAgregarProd.getProd();
        int agregado = objAgregarProd.getAgr();
        int dañado = objAgregarProd.getDañ();
        int perdido = objAgregarProd.getPer();

        agregarProducto(producto, agregado, dañado, perdido);

    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("recursos/icono.png"));
        return retValue;
    }

    private void btnActualizarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarProductoActionPerformed
        // Obtener la fila seleccionada en tblDetalle
        int filaSeleccionada = tblDetalle.getSelectedRow();
        if (filaSeleccionada != -1) { // Verificar si se ha seleccionado una fila
            // Mostrar cuadro de diálogo para seleccionar la cantidad a eliminar
            String[] opciones = {"AGREGADO", "DAÑADO", "PERDIDO"};
            int opcionSeleccionada = JOptionPane.showOptionDialog(this,
                    "Ingrese la cantidad que desea actualizar:",
                    "Actualizar cantidad",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            // Obtener las cantidades actuales de agregado, dañado y perdido
            int agregadoActual = Integer.parseInt(String.valueOf(tblDetalle.getValueAt(filaSeleccionada, 2)));
            int dañadoActual = Integer.parseInt(String.valueOf(tblDetalle.getValueAt(filaSeleccionada, 3)));
            int perdidoActual = Integer.parseInt(String.valueOf(tblDetalle.getValueAt(filaSeleccionada, 4)));

            // Procesar la cantidad a eliminar según la opción seleccionada
            switch (opcionSeleccionada) {
                case 0: // Agregado
                    actualizarCantidad(filaSeleccionada, agregadoActual, "agregado");
                    break;
                case 1: // Dañado
                    actualizarCantidad(filaSeleccionada, dañadoActual, "dañado");
                    break;
                case 2: // Perdido
                    actualizarCantidad(filaSeleccionada, perdidoActual, "perdido");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para actualizar.",
                    "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarProductoActionPerformed

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

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void tblInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInventarioMouseClicked
        btnRegistrar.setEnabled(false);
        idInventarioSeleccionado = (int) tblInventario.getValueAt(tblInventario.getSelectedRow(), 0);
        try {
            txtInventario.setText(String.valueOf(tblInventario.getValueAt(tblInventario.getSelectedRow(), 0)));
            lblUsuario.setText(String.valueOf(tblInventario.getValueAt(tblInventario.getSelectedRow(), 3)));

            int filaSeleccionada = tblInventario.getSelectedRow();
            int idInventario = Integer.parseInt(tblInventario.getValueAt(filaSeleccionada, 0).toString());

            // Obtener el detalle del pedido seleccionado
            ResultSet rsDetalle = objInventario.obtenerDetalleInventario(idInventario);

            // Crear un modelo de tabla para el detalle del pedido
            DefaultTableModel modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            modelo.addColumn("CÓDIGO");
            modelo.addColumn("PRODUCTO");
            modelo.addColumn("AGREGADO");
            modelo.addColumn("DAÑADO");
            modelo.addColumn("PERDIDO");

            // Llenar el modelo con los datos obtenidos del detalle del pedido
            while (rsDetalle.next()) {
                Object[] fila = {
                    rsDetalle.getInt("idproducto"),
                    rsDetalle.getString("nombresproducto"),
                    rsDetalle.getInt("agregado"),
                    rsDetalle.getInt("dañado"),
                    rsDetalle.getInt("perdido")
                };
                modelo.addRow(fila);
            }

            // Establecer el modelo de la tabla de detalle con los datos obtenidos
            tblDetalle.setModel(modelo);
            tblDetalle.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblDetalle.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblDetalle.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblDetalle.getColumnModel().getColumn(3).setPreferredWidth(60);
            tblDetalle.getColumnModel().getColumn(4).setPreferredWidth(80);
        } catch (Exception e) {
            e.printStackTrace(); // Manejo adecuado de la excepción
        }

    }//GEN-LAST:event_tblInventarioMouseClicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarControles();
        lblUsuario.setText(clsSesionUsuario.nombreUsuario);
        btnRegistrar.setEnabled(true);
        tblDetalle.clearSelection();
        tblInventario.clearSelection();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void agregarProducto(int producto, int agregado, int dañado, int perdido) {
        if (producto != 0) {
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
                    // Si el producto está repetido, actualizar las cantidades existentes
                    int auxAgregado = Integer.parseInt(String.valueOf(tblDetalle.getValueAt(fila, 2)));
                    int auxDañado = Integer.parseInt(String.valueOf(tblDetalle.getValueAt(fila, 3)));
                    int auxPerdido = Integer.parseInt(String.valueOf(tblDetalle.getValueAt(fila, 4)));

                    // Sumar las cantidades nuevas a las existentes
                    agregado += auxAgregado;
                    dañado += auxDañado;
                    perdido += auxPerdido;

                    // Eliminar la fila existente para reemplazarla con las nuevas cantidades
                    modelo.removeRow(fila);
                }

                // Asegurarse de que dañado y perdido no sean negativos
                if (dañado < 0) {
                    dañado = 0;
                }
                if (perdido < 0) {
                    perdido = 0;
                }

                // Obtener los detalles del producto y agregarlo al modelo de tabla
                rs = objProducto.buscarProductos(producto);
                while (rs.next()) {
                    modelo.addRow(new Object[]{rs.getString("idproducto"),
                        rs.getString("nombresproducto"), agregado, dañado, perdido});
                }

                // Establecer el modelo de tabla actualizado en tblDetalle
                tblDetalle.setModel(modelo);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e.getMessage());
            }
        }
    }

    private void listarDetalleInventario() {
        ResultSet rsDetalle = null;

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelo.addColumn("CÓDIGO");
        modelo.addColumn("PRODUCTO");
        modelo.addColumn("AGREGADO");
        modelo.addColumn("DAÑADO");
        modelo.addColumn("PERDIDO");
        tblDetalle.setModel(modelo);
        tblDetalle.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblDetalle.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblDetalle.getColumnModel().getColumn(2).setPreferredWidth(60);
        tblDetalle.getColumnModel().getColumn(3).setPreferredWidth(60);
        tblDetalle.getColumnModel().getColumn(4).setPreferredWidth(60);

    }

    private void actualizarCantidad(int filaSeleccionada, int cantidadActual, String tipoCantidad) {
        String input = JOptionPane.showInputDialog(this, "Ingrese la nueva cantidad:", 
                "Ingresar cantidad", JOptionPane.INFORMATION_MESSAGE);
        if (input != null && !input.isEmpty()) {
            try {
                int nuevaCantidad = Integer.parseInt(input);
                if (nuevaCantidad < 0) {
                    JOptionPane.showMessageDialog(this, "La nueva cantidad no puede ser negativa.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Actualizar la cantidad correspondiente
                    tblDetalle.setValueAt(nuevaCantidad, filaSeleccionada, getColumnIndexByTipoCantidad(tipoCantidad));
                    JOptionPane.showMessageDialog(this, "Se actualizó la cantidad correctamente.", 
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private int getColumnIndexByTipoCantidad(String tipoCantidad) {
        switch (tipoCantidad) {
            case "agregado":
                return 2;
            case "dañado":
                return 3;
            case "perdido":
                return 4;
            default:
                return -1;
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarProducto;
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tblDetalle;
    private javax.swing.JTable tblInventario;
    private javax.swing.JLabel txtInventario;
    // End of variables declaration//GEN-END:variables
}
