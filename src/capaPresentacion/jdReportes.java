
package capaPresentacion;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.SwingUtilities;

public class jdReportes extends javax.swing.JDialog {


    public jdReportes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnReporte1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(":: ÁREA DE REPORTES ::");
        setIconImage(getIconImage());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnReporte1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/stockproductosico.png"))); // NOI18N
        btnReporte1.setBorder(null);
        btnReporte1.setContentAreaFilled(false);
        btnReporte1.setFocusable(false);
        btnReporte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporte1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnReporte1, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 150, 100, 100));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ingresosventasico.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 120, 110));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ingresospedidossesico.png"))); // NOI18N
        jButton2.setActionCommand("");
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 135, 130, 120));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/frmReportes.png"))); // NOI18N
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

    private void btnReporte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporte1ActionPerformed
        jdReporteStockProductos objReporteStockProductos = new jdReporteStockProductos((Frame) 
                SwingUtilities.getWindowAncestor(this),true);
        objReporteStockProductos.setLocationRelativeTo(this);
        objReporteStockProductos.setVisible(true);
    }//GEN-LAST:event_btnReporte1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jdReporteIngresosVentas objReporteIngresos = new jdReporteIngresosVentas((Frame) 
                SwingUtilities.getWindowAncestor(this),true);
        objReporteIngresos.setLocationRelativeTo(this);
        objReporteIngresos.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jdReporteIngresosPedidos objReporteIngresos = new jdReporteIngresosPedidos((Frame) 
                SwingUtilities.getWindowAncestor(this),true);
        objReporteIngresos.setLocationRelativeTo(this);
        objReporteIngresos.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("recursos/icono.png"));
        return retValue;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReporte1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
