package capaPresentacion;

import capaNegocio.clsMesa;
import capaNegocio.clsSesionUsuario;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class frmMenuPrincipal extends javax.swing.JFrame {

    clsMesa objMesa = new clsMesa();

    public frmMenuPrincipal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        setResizable(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnMesa1 = new javax.swing.JButton();
        btnMesa2 = new javax.swing.JButton();
        btnMesa3 = new javax.swing.JButton();
        btnMesa4 = new javax.swing.JButton();
        lblUsuario = new javax.swing.JLabel();
        btnTransacciones = new javax.swing.JButton();
        btnMantenimientos = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnPoker = new javax.swing.JButton();
        lblCargo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(":: MENÚ PRINCIPAL ::");
        setIconImage(getIconImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMesa1.setBackground(new java.awt.Color(44, 52, 21));
        btnMesa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/btnMesa.png"))); // NOI18N
        btnMesa1.setBorder(null);
        btnMesa1.setContentAreaFilled(false);
        btnMesa1.setFocusable(false);
        btnMesa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMesa1MouseClicked(evt);
            }
        });
        btnMesa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesa1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMesa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 170, 150));

        btnMesa2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/btnMesa.png"))); // NOI18N
        btnMesa2.setBorder(null);
        btnMesa2.setContentAreaFilled(false);
        btnMesa2.setFocusable(false);
        btnMesa2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMesa2MouseClicked(evt);
            }
        });
        jPanel1.add(btnMesa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 110, 170, 160));

        btnMesa3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/btnMesa.png"))); // NOI18N
        btnMesa3.setBorder(null);
        btnMesa3.setContentAreaFilled(false);
        btnMesa3.setFocusable(false);
        btnMesa3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMesa3MouseClicked(evt);
            }
        });
        jPanel1.add(btnMesa3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 450, 170, 150));

        btnMesa4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/btnMesa.png"))); // NOI18N
        btnMesa4.setBorder(null);
        btnMesa4.setContentAreaFilled(false);
        btnMesa4.setFocusable(false);
        btnMesa4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMesa4MouseClicked(evt);
            }
        });
        jPanel1.add(btnMesa4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 440, 180, 160));

        lblUsuario.setBackground(new java.awt.Color(255, 255, 255));
        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("Jhon");
        jPanel1.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 31, 100, 20));

        btnTransacciones.setBorder(null);
        btnTransacciones.setContentAreaFilled(false);
        btnTransacciones.setFocusable(false);
        btnTransacciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTransaccionesMouseClicked(evt);
            }
        });
        btnTransacciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaccionesActionPerformed(evt);
            }
        });
        jPanel1.add(btnTransacciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 60, 50));

        btnMantenimientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/btnMantenimientos.png"))); // NOI18N
        btnMantenimientos.setBorder(null);
        btnMantenimientos.setContentAreaFilled(false);
        btnMantenimientos.setFocusable(false);
        btnMantenimientos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMantenimientosMouseClicked(evt);
            }
        });
        btnMantenimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMantenimientosActionPerformed(evt);
            }
        });
        jPanel1.add(btnMantenimientos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 70, 60));

        btnInventario.setForeground(new java.awt.Color(0, 0, 0));
        btnInventario.setBorder(null);
        btnInventario.setContentAreaFilled(false);
        btnInventario.setFocusable(false);
        btnInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInventarioMouseClicked(evt);
            }
        });
        btnInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventarioActionPerformed(evt);
            }
        });
        jPanel1.add(btnInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 50, 60));

        btnSalir.setBorder(null);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setFocusable(false);
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, 60, 50));

        btnReportes.setBorder(null);
        btnReportes.setContentAreaFilled(false);
        btnReportes.setFocusable(false);
        btnReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportesMouseClicked(evt);
            }
        });
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });
        jPanel1.add(btnReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 60, 60));

        btnPoker.setBorder(null);
        btnPoker.setContentAreaFilled(false);
        btnPoker.setFocusable(false);
        btnPoker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPokerActionPerformed(evt);
            }
        });
        jPanel1.add(btnPoker, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 280, 200, 200));

        lblCargo.setBackground(new java.awt.Color(255, 255, 255));
        lblCargo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCargo.setForeground(new java.awt.Color(255, 255, 255));
        lblCargo.setText("ADMINISTRADOR");
        jPanel1.add(lblCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1203, 29, 160, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/frmPrincipal.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(jPanel1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        lblUsuario.setText(clsSesionUsuario.nombreUsuario);
        lblCargo.setText(clsSesionUsuario.cargoUsuario);
        String cargoUsuario = clsSesionUsuario.obtenerCargoUsuario();
        if (cargoUsuario.equals("ADMINISTRADOR")) {
            // Permitir acceso completo al botón de mantenimientos
            btnMantenimientos.setEnabled(true);
        } else if (cargoUsuario.equals("VENDEDOR")) {
            // Deshabilitar acceso al botón de mantenimientos para vendedores
            btnMantenimientos.setEnabled(false);
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnMesa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMesa1ActionPerformed

    private void btnMesa1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMesa1MouseClicked

        abrirDialogoSesion("Mesa 1");
    }//GEN-LAST:event_btnMesa1MouseClicked

    private void btnMesa2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMesa2MouseClicked
        abrirDialogoSesion("Mesa 2");
    }//GEN-LAST:event_btnMesa2MouseClicked

    private void btnMesa3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMesa3MouseClicked
        abrirDialogoSesion("Mesa 3");
    }//GEN-LAST:event_btnMesa3MouseClicked

    private void btnMesa4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMesa4MouseClicked
        abrirDialogoSesion("Mesa 4");
    }//GEN-LAST:event_btnMesa4MouseClicked

    private void btnMantenimientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMantenimientosMouseClicked

    }//GEN-LAST:event_btnMantenimientosMouseClicked

    private void btnTransaccionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransaccionesMouseClicked
        String codCliente = "";
        jdVentas objVenta = new jdVentas(this, true, codCliente);
        objVenta.setLocationRelativeTo(this);
        objVenta.setVisible(true);
    }//GEN-LAST:event_btnTransaccionesMouseClicked

    private void btnInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInventarioMouseClicked
        jdInventario objInventario = new jdInventario((Frame) SwingUtilities.getWindowAncestor(this), true);
        objInventario.setLocationRelativeTo(this);
        objInventario.setVisible(true);
    }//GEN-LAST:event_btnInventarioMouseClicked

    private void btnReportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportesMouseClicked

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnSalirMouseClicked

    private void btnMantenimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMantenimientosActionPerformed
        jdMantenimientos objMantenimiento = new jdMantenimientos((Frame) SwingUtilities.getWindowAncestor(this), true);
        objMantenimiento.setLocationRelativeTo(this);
        objMantenimiento.setVisible(true);
    }//GEN-LAST:event_btnMantenimientosActionPerformed

    private void btnTransaccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaccionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTransaccionesActionPerformed

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInventarioActionPerformed

    private void btnPokerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPokerActionPerformed
        String codCliente = "";
        jdVentas objVenta = new jdVentas(this, true, codCliente);
        objVenta.setLocationRelativeTo(this);
        objVenta.setVisible(true);
    }//GEN-LAST:event_btnPokerActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        jdReportes objReporte = new jdReportes(this, true);
        objReporte.setLocationRelativeTo(this);
        objReporte.setVisible(true);
    }//GEN-LAST:event_btnReportesActionPerformed

    private void abrirDialogoSesion(String nombreMesa) {
        boolean estadoMesa = false;
        try {
            int idMesa = objMesa.obtenerIDMesa(nombreMesa);
            estadoMesa = objMesa.obtenerEstadoMesa(idMesa);

            // Abrir jdSesion y pasar el ID de la mesa
            jdSesion objSesion = new jdSesion(this, true, idMesa, estadoMesa);
            objSesion.setLocationRelativeTo(this);
            objSesion.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(frmMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("recursos/icono.png"));
        return retValue;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnMantenimientos;
    private javax.swing.JButton btnMesa1;
    private javax.swing.JButton btnMesa2;
    private javax.swing.JButton btnMesa3;
    private javax.swing.JButton btnMesa4;
    private javax.swing.JButton btnPoker;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnTransacciones;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblUsuario;
    // End of variables declaration//GEN-END:variables
}
