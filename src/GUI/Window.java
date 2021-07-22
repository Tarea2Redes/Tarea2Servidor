package GUI;

import Data.DiskNodeData;
import Domain.DiskNode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import servidor.ControllerNode;

/**
 *
 * @author Estudiante
 */
public class Window extends javax.swing.JFrame {

    private JComboBox jComboBox1, jComboBox2;

    public Window() {
        try {
            setLocationRelativeTo(null);
            initComponents();

            DiskNodeData d = new DiskNodeData();
            ArrayList<DiskNode> list = d.getActiveList();
            ArrayList<DiskNode> list2 = d.getDesactivatedList();

            String[] es = new String[list.size()];

            for (int i = 0; i < es.length; i++) {
                es[i] = String.valueOf(list.get(i).getNumber());
            }

            String[] es2 = new String[list2.size()];

            for (int i = 0; i < es2.length; i++) {
                es2[i] = String.valueOf(list2.get(i).getNumber());
            }

            jComboBox1 = new JComboBox(es);
            jComboBox1.setBounds(30, 80, 100, 50);
            add(jComboBox1);

            jComboBox2 = new JComboBox(es2);
            jComboBox2.setBounds(30, 300, 100, 50);
            add(jComboBox2);

            setSize(800, 500);
            setResizable(false);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");
        setBackground(new java.awt.Color(255, 102, 0));
        getContentPane().setLayout(null);

        jLabel1.setText("active disks:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(25, 12, 102, 27);

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));

        jButton1.setBackground(new java.awt.Color(51, 102, 0));
        jButton1.setText("Add new Disk Node +");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setText("disable disk node");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButton2)
                .addGap(37, 37, 37)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(170, 10, 270, 210);

        jLabel2.setText("inactive disks:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 250, 100, 20);

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));

        jButton3.setBackground(new java.awt.Color(0, 0, 153));
        jButton3.setText("enable disk node");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jButton3)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(180, 240, 250, 120);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ControllerNode cN = new ControllerNode();
        try {
            cN.addDisk();
            this.dispose();
            Window n = new Window();
            n.setVisible(true);
            JOptionPane.showMessageDialog(null, "DiskNode agregado!", "Respuesta!", JOptionPane.INFORMATION_MESSAGE);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int number = Integer.parseInt(jComboBox1.getSelectedItem().toString());

        ControllerNode cN = new ControllerNode();

        try {
            if (cN.getNumActivos() > 3) {
                if (number == 1) {
                    JOptionPane.showMessageDialog(null, "fatal_error:: La unidad #1 no se puede borrar!", "Atención!", JOptionPane.ERROR_MESSAGE);
                } else {
                    cN.disableDisk(number);
                    this.dispose();
                    Window n = new Window();
                    n.setVisible(true);
                    JOptionPane.showMessageDialog(null, "DiskNode #" + number + "deshabilitado!", "Respuesta!", JOptionPane.INFORMATION_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(null, "Deben haber al menos 3 discos activos!", "Atención!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        int number = Integer.parseInt(jComboBox2.getSelectedItem().toString());

        ControllerNode cN = new ControllerNode();
        try {
            cN.enableDisk(number);
            this.dispose();
            Window n = new Window();
            n.setVisible(true);
            JOptionPane.showMessageDialog(null, "DiskNode #" + number + " habilitado!", "Respuesta!", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
