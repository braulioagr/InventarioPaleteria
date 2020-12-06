
package paleteria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static paleteria.SeleccionaSucursal.rt;
import static paleteria.SeleccionaSucursal.st;


public class SeleccionaCliente extends javax.swing.JFrame {

    private static String idSocur;
    private String id = "";
    private DefaultTableModel modelo;
    private Connection Conexion = null;
    public static Statement st = null;
    public static ResultSet rt;

    public SeleccionaCliente(String ids) {
        initComponents();
        
        idSocur = ids;
        Conexion =  Nexo.conex();
        ActualizaTablaClientes();

    }

    @SuppressWarnings("unchecked")
    private void ActualizaTablaClientes(){
        
        modelo = new DefaultTableModel(); // para diseno de la tabla
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("Tipo Cliente");
        modelo.addColumn("Descuento");
        
        try{
            String Qery = "SELECT * FROM empleado.Cliente";
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[5];

            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                Aux[4] = rt.getString(5);
                modelo.addRow(Aux);
            }
            this.DataSelecCliente.setModel(modelo);
        }
        catch(Exception e){
        
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        DataSelecCliente = new javax.swing.JTable();
        cancelarbtn = new javax.swing.JButton();
        aceptarbtn1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Selecciona cliente");

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));

        DataSelecCliente.setBackground(new java.awt.Color(204, 204, 204));
        DataSelecCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        DataSelecCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataSelecClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(DataSelecCliente);

        cancelarbtn.setText("Cancelar");
        cancelarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarbtnActionPerformed(evt);
            }
        });

        aceptarbtn1.setText("Aceptar");
        aceptarbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarbtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(aceptarbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aceptarbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DataSelecClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataSelecClienteMouseClicked
        int fila = DataSelecCliente.rowAtPoint(evt.getPoint());
        
        id = modelo.getValueAt(fila,0).toString();
    }//GEN-LAST:event_DataSelecClienteMouseClicked

    private void cancelarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarbtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarbtnActionPerformed

    private void aceptarbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarbtn1ActionPerformed
        if(id != "")
        {
            
            int resp = JOptionPane.showConfirmDialog(null, "¿Quiere trabajar con el CLIENTE con identificador " + id + "?");
            if(resp == 0)
            {
                Venta Venta = new Venta(idSocur,id);
                Venta.setVisible(true);
                this.id = "";
                this.dispose();
                
            }

        }
        else
        {
            JOptionPane.showMessageDialog(this, "Seleccione un CLIENTE");
        }
    }//GEN-LAST:event_aceptarbtn1ActionPerformed

    
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
            java.util.logging.Logger.getLogger(SeleccionaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeleccionaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeleccionaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeleccionaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SeleccionaCliente(idSocur).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DataSelecCliente;
    private javax.swing.JButton aceptarbtn1;
    private javax.swing.JButton cancelarbtn;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
