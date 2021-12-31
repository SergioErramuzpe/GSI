/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

/**
 *
 * @author ikera
 */
public class PanelConexion extends javax.swing.JFrame {

    /**
     * Creates new form PanelConexion
     */
    public PanelConexion() {
        initComponents();
    }

    private void sendLogin(String nombrePw){
        if(!nombrePw.equals("")){
            
        }
    }
    private void rcvLogin(){
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        UsuarioLabel = new javax.swing.JLabel();
        ContraseñaLabel = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jTextFieldContraseña = new javax.swing.JTextField();
        Restore = new javax.swing.JButton();
        Login1 = new javax.swing.JButton();
        Register = new javax.swing.JButton();

        Fondo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Fondo.setAlignmentY(0.0F);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        UsuarioLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        UsuarioLabel.setText("Usuario:");
        jPanel1.add(UsuarioLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 130, -1));

        ContraseñaLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ContraseñaLabel.setText("Contraseña:");
        jPanel1.add(ContraseñaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, -1, -1));

        jTextFieldUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 340, 30));
        jPanel1.add(jTextFieldContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 340, 30));

        Restore.setText("No recuerdo mi contraseña");
        Restore.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Restore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RestoreActionPerformed(evt);
            }
        });
        jPanel1.add(Restore, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 260, 30));

        Login1.setText("Iniciar sesión");
        Login1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Login1ActionPerformed(evt);
            }
        });
        jPanel1.add(Login1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, 120, 30));

        Register.setText("Registrar Usuario");
        Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterActionPerformed(evt);
            }
        });
        jPanel1.add(Register, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUsuarioActionPerformed

    private void RestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RestoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RestoreActionPerformed

    private void Login1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Login1ActionPerformed
        String nombrePw;
        nombrePw = jTextFieldUsuario.getText() + " " + jTextFieldContraseña.getText();
        sendLogin(nombrePw);
    }//GEN-LAST:event_Login1ActionPerformed

    private void RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RegisterActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PanelConexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelConexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelConexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelConexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelConexion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ContraseñaLabel;
    private javax.swing.JLabel Fondo;
    private javax.swing.JButton Login1;
    private javax.swing.JButton Register;
    private javax.swing.JButton Restore;
    private javax.swing.JLabel UsuarioLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldContraseña;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables
}
