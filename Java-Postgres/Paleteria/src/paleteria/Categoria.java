package paleteria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class Categoria extends javax.swing.JFrame {

    private static String idCategoria;
    private static boolean agregar;
    private static Connection Conexion = null;
    public static Statement st = null;
    public static ResultSet rt;
    
    public Categoria(String idC, boolean agr) {
        initComponents();
        
        Conexion = Nexo.conex();
        idCategoria = idC;
        agregar = agr;
        
        if(idCategoria != "" && agregar != false)
        {
            ObtienedatosCategoria();
        }
        
    }

    public static void ObtienedatosCategoria(){
        
        try{
            String Qery = "SELECT * FROM empleado.Categoria WHERE idCategoria = " + idCategoria;
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);// las tres columnas
            while(rt.next()){
                nombretext.setText(rt.getString(2));
                tipocbox.setSelectedItem(rt.getString(3));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoc = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        tipocbox = new javax.swing.JComboBox<>();
        nombretext = new javax.swing.JTextField();
        aceptarbtn = new javax.swing.JButton();
        cancelarbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Categoria");

        tipoc.setText("Tamaño");
        tipoc.setName("nombre"); // NOI18N

        nombre.setText("Nombre");
        nombre.setName("nombre"); // NOI18N

        tipocbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pequeño", "Mediano", "Grande" }));

        nombretext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombretextActionPerformed(evt);
            }
        });

        aceptarbtn.setText("Aceptar");
        aceptarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarbtnActionPerformed(evt);
            }
        });

        cancelarbtn.setText("Cancelar");
        cancelarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombre)
                    .addComponent(tipoc)
                    .addComponent(nombretext, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipocbox, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(cancelarbtn)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(aceptarbtn)
                    .addGap(12, 12, 12)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombretext, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tipoc)
                .addGap(9, 9, 9)
                .addComponent(tipocbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(138, 138, 138)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cancelarbtn)
                        .addComponent(aceptarbtn))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombretextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombretextActionPerformed
        
    }//GEN-LAST:event_nombretextActionPerformed

    private void aceptarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarbtnActionPerformed
        String nom = nombretext.getText();
        String tam = tipocbox.getSelectedItem().toString();

        if(!"".equals(nom) && !"".equals(tam))
        {
            if(!"".equals(idCategoria) && agregar != false)
            {
                Nexo.ejecutaSQL("UPDATE empleado.Categoria SET nombreCategoria = '" + nom + "', tamano = '" +  tam + "' WHERE idCategoria = "+ idCategoria, false);
                Inventario.ActualizagridCategoria();
                this.dispose();
                JOptionPane.showMessageDialog(this, "Actualización Correcta");
            }
            else
            {
                Nexo.ejecutaSQL("INSERT INTO empleado.Categoria(nombreCategoria,tamano) VALUES('"+ nom +"', '"+tam+"')", false);
                Inventario.ActualizagridCategoria();
                this.dispose();
                JOptionPane.showMessageDialog(this, "Registro agregado");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Escriba todos los datos requeridos");

        }
    }//GEN-LAST:event_aceptarbtnActionPerformed

    private void cancelarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarbtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarbtnActionPerformed

    
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
            java.util.logging.Logger.getLogger(Categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Categoria(idCategoria, agregar).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarbtn;
    private javax.swing.JButton cancelarbtn;
    private javax.swing.JLabel nombre;
    protected static javax.swing.JTextField nombretext;
    private javax.swing.JLabel tipoc;
    protected static javax.swing.JComboBox<String> tipocbox;
    // End of variables declaration//GEN-END:variables
}
