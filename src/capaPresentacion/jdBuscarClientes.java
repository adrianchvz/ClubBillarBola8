package capaPresentacion;

import capaInterfaz.componentes.ScrollBar;
import capaInterfaz.componentes.Table;
import capaNegocio.clsCliente;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class jdBuscarClientes extends javax.swing.JDialog {

    clsCliente objCliente = new clsCliente();
    private String cli = ""; //Corresponde al nombre del cliente.
    private boolean esVenta;

    public jdBuscarClientes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tblClientes = new Table(); 
        jScrollPane1.setViewportView(tblClientes);
        jScrollPane1.setVerticalScrollBar(new ScrollBar());

        // Agregar MouseListener a la tabla para manejar el evento de clic
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
    }

    public void setEsVenta(boolean esVenta) {
        this.esVenta = esVenta;
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
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");

        try {
            rsClientes = objCliente.filtrarClientes(txtBuscarClientes.getText());
            while (rsClientes.next()) {
                registro = new Vector();
                registro.add(0, rsClientes.getInt("idcliente"));
                registro.add(1, rsClientes.getString("nombrescliente"));
                modelo.addRow(registro);
            }
            tblClientes.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void pasarDatos(String cod) {
        try {
            cli = cod;

            // Mostrar cuadro de diálogo de confirmación
            Object[] options = {"Si", "No"};
            int opcion = JOptionPane.showOptionDialog(this,
                    "¿Confirma que desea agregar a "
                    + "" + cli + " como comprador?",
                    "Confirmar Cliente",
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
                    objPedido.actualizarCliente(cli);
                } else {
                }
            }
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void pasarDatosVentas(String cod) {
        try {
            cli = cod;

            // Mostrar cuadro de diálogo de confirmación
            Object[] options = {"Si", "No"};
            int opcion = JOptionPane.showOptionDialog(this,
                    "¿Confirma que desea agregar a "
                    + "" + cli + " como comprador?",
                    "Confirmar Cliente",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (opcion == JOptionPane.YES_OPTION) {
                // Obtener una referencia al formulario jdPedido existente
                jdVentas objVentas = obtenerFormularioPedidoAbiertoV();
                if (objVentas != null) {
                    // Si el formulario jdPedido ya está abierto, actualizar la sesión en ese formulario
                    objVentas.actualizarCliente(cli);
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

    private jdVentas obtenerFormularioPedidoAbiertoV() {
        for (Window window : Window.getWindows()) {
            if (window instanceof jdVentas && window.isShowing()) {
                return (jdVentas) window;
            }
        }
        return null;
    }

    public String getCli() {
        return cli;
    }

    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("recursos/icono.png"));  
        return retValue;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtBuscarClientes = new javax.swing.JTextField();
        btnAgregarCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(":: BUSCAR CLIENTE ::");
        setIconImage(getIconImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscarClientes.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarClientes.setForeground(new java.awt.Color(0, 0, 0));
        txtBuscarClientes.setBorder(null);
        txtBuscarClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClientesKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 31, 270, 20));

        btnAgregarCliente.setBorder(null);
        btnAgregarCliente.setContentAreaFilled(false);
        btnAgregarCliente.setFocusable(false);
        btnAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 25, 25));

        jScrollPane1.setBorder(new javax.swing.border.MatteBorder(null));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 464, 220));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/frmBuscarCliente.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

    private void txtBuscarClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClientesKeyReleased
        listarClientes();
    }//GEN-LAST:event_txtBuscarClientesKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        listarClientes();
    }//GEN-LAST:event_formWindowOpened

    private void btnAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClienteActionPerformed
        jdCliente objCliente = new jdCliente((Frame) SwingUtilities.getWindowAncestor(this), true);
        objCliente.setLocationRelativeTo(this);
        objCliente.setVisible(true);
    }//GEN-LAST:event_btnAgregarClienteActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        String nom = (String.valueOf(tblClientes.getValueAt(tblClientes.getSelectedRow(), 1)));
        if (esVenta) {
            pasarDatosVentas(nom);
        } else {
            pasarDatos(nom);
        }
    }//GEN-LAST:event_tblClientesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCliente;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtBuscarClientes;
    // End of variables declaration//GEN-END:variables
}
