/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaPresentacion;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.SwingUtilities;

/**
 *
 * @author Adrian
 */
public class jdMantenimientos extends javax.swing.JDialog {

    /**
     * Creates new form jdMantenimientos
     */
    public jdMantenimientos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnClientes = new javax.swing.JButton();
        btnCategorias = new javax.swing.JButton();
        btnMarcas = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnMesas = new javax.swing.JButton();
        btnUsuarios1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(":: ÁREA DE MANTENIMIENTO ::");
        setIconImage(getIconImage());
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnClientes.setBorder(null);
        btnClientes.setContentAreaFilled(false);
        btnClientes.setFocusable(false);
        btnClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClientesMouseClicked(evt);
            }
        });
        jPanel1.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, 140, 130));

        btnCategorias.setBorder(null);
        btnCategorias.setContentAreaFilled(false);
        btnCategorias.setFocusable(false);
        btnCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCategoriasMouseClicked(evt);
            }
        });
        jPanel1.add(btnCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 140, 130));

        btnMarcas.setBorder(null);
        btnMarcas.setContentAreaFilled(false);
        btnMarcas.setFocusable(false);
        btnMarcas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMarcasMouseClicked(evt);
            }
        });
        jPanel1.add(btnMarcas, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 140, 130));

        btnProductos.setBorder(null);
        btnProductos.setContentAreaFilled(false);
        btnProductos.setFocusable(false);
        btnProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductosMouseClicked(evt);
            }
        });
        jPanel1.add(btnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 140, 130));

        btnMesas.setBorder(null);
        btnMesas.setContentAreaFilled(false);
        btnMesas.setFocusable(false);
        btnMesas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMesasMouseClicked(evt);
            }
        });
        jPanel1.add(btnMesas, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, 140, 130));

        btnUsuarios1.setBorder(null);
        btnUsuarios1.setContentAreaFilled(false);
        btnUsuarios1.setFocusable(false);
        btnUsuarios1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUsuarios1MouseClicked(evt);
            }
        });
        jPanel1.add(btnUsuarios1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 130, 130));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/frmMantenimientos.png"))); // NOI18N
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

    private void btnCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCategoriasMouseClicked
        jdCategoria objCategoria = new jdCategoria((Frame) SwingUtilities.getWindowAncestor(this), true);
        objCategoria.setLocationRelativeTo(this);
        objCategoria.setVisible(true);
    }//GEN-LAST:event_btnCategoriasMouseClicked

    private void btnMarcasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMarcasMouseClicked
        jdMarca objMarca = new jdMarca((Frame) SwingUtilities.getWindowAncestor(this), true);
        objMarca.setLocationRelativeTo(this);
        objMarca.setVisible(true);
    }//GEN-LAST:event_btnMarcasMouseClicked

    private void btnProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductosMouseClicked
        jdProducto objProducto = new jdProducto((Frame) SwingUtilities.getWindowAncestor(this), true);
        objProducto.setLocationRelativeTo(this);
        objProducto.setVisible(true);
    }//GEN-LAST:event_btnProductosMouseClicked

    private void btnClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClientesMouseClicked
        jdCliente objCliente = new jdCliente((Frame) SwingUtilities.getWindowAncestor(this), true);
        objCliente.setLocationRelativeTo(this);
        objCliente.setVisible(true);
    }//GEN-LAST:event_btnClientesMouseClicked

    private void btnMesasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMesasMouseClicked
        jdMesa objMesa = new jdMesa((Frame) SwingUtilities.getWindowAncestor(this), true);
        objMesa.setLocationRelativeTo(this);
        objMesa.setVisible(true);
    }//GEN-LAST:event_btnMesasMouseClicked

    private void btnUsuarios1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuarios1MouseClicked
        jdUsuario objUsuario = new jdUsuario((Frame) SwingUtilities.getWindowAncestor(this), true);
        objUsuario.setLocationRelativeTo(this);
        objUsuario.setVisible(true);
    }//GEN-LAST:event_btnUsuarios1MouseClicked

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("recursos/icono.png"));
        return retValue;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCategorias;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnMarcas;
    private javax.swing.JButton btnMesas;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnUsuarios1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}