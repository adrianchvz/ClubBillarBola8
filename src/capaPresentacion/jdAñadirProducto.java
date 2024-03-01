/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaPresentacion;

import capaInterfaz.componentes.ScrollBar;
import capaInterfaz.componentes.Table;
import capaNegocio.clsProducto;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class jdAñadirProducto extends javax.swing.JDialog {

    clsProducto objProducto = new clsProducto();
    private int prod = 0; //Corresponde al código del producto.
    private int cant = 0; //Cantidad solicitada.

    public jdAñadirProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
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
    }

    private void listarProductos() {
        ResultSet rsProductos = null;
        String estado = "";
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("PRECIO");
        modelo.addColumn("STOCK");
        modelo.addColumn("ESTADO");

        try {
            rsProductos = objProducto.filtrar(txtNombreProducto.getText());
            while (rsProductos.next()) {

                if (rsProductos.getString("estadoproducto").equals("t")) {
                    estado = "Disponible";
                } else {
                    estado = "Agotado";
                }
                modelo.addRow(new Object[]{rsProductos.getInt("idproducto"),
                    rsProductos.getString("nombresproducto"),
                    rsProductos.getString("precioproducto"),
                    rsProductos.getString("stock"),
                    estado});
            }
            tblProductos.setModel(modelo);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(50); 
            tblProductos.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(60); 
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(60);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(60);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void pasarDatos(int cod, int ctd) {
        try {
            int stock = objProducto.getStock(cod);
            if (ctd <= stock) {
                prod = cod;
                cant = ctd;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Stock insuficiente");
            }
            // Obtener el stock restante después de agregar la cantidad
            int stockRestante = objProducto.getStock(cod) - ctd;
            // Mostrar un mensaje si el stock restante es bajo
            int umbralStockBajo = 5; // Umbral de stock bajo, ajusta según sea necesario
            if (stockRestante <= umbralStockBajo) {
                JOptionPane.showMessageDialog(rootPane, "¡Atención! Quedan pocas unidades en stock para este producto.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public int getCod() {
        return prod;
    }

    public int getCant() {
        return cant;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        txtNombreProducto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(":: LISTA DE PRODUCTOS ::");
        setIconImage(getIconImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 700, 310));

        txtNombreProducto.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreProducto.setForeground(new java.awt.Color(0, 0, 0));
        txtNombreProducto.setBorder(null);
        txtNombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreProductoActionPerformed(evt);
            }
        });
        txtNombreProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreProductoKeyReleased(evt);
            }
        });
        jPanel1.add(txtNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 73, 360, 18));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/frmAñadirProducto.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreProductoActionPerformed

    private void txtNombreProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProductoKeyReleased
        listarProductos();
    }//GEN-LAST:event_txtNombreProductoKeyReleased

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        int cod = Integer.parseInt(String.valueOf(tblProductos.getValueAt(tblProductos.getSelectedRow(), 0)));
        String ctd = String.valueOf(JOptionPane.showInputDialog(rootPane, "Por favor, ingrese la cantidad:", "Ingresar cantidad", 
                JOptionPane.INFORMATION_MESSAGE));

        // Verificar si se ingresó un valor válido
        if (ctd != null && !ctd.isEmpty() && ctd.matches("\\d+")) {
            pasarDatos(cod, Integer.parseInt(ctd));
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ingrese una cantidad válida (sólo números)");
        }
    }//GEN-LAST:event_tblProductosMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        listarProductos();
    }//GEN-LAST:event_formWindowOpened

    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("recursos/icono.png"));  
        return retValue;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtNombreProducto;
    // End of variables declaration//GEN-END:variables
}
