
package paleteria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class Producto extends javax.swing.JFrame {

    private static String idProducto;
    private static boolean agregar;
    private static Connection Conexion = null;
    public static Statement st = null;
    public static ResultSet rt;
    public Producto(String idP, boolean agr) {
        initComponents();
        
        Conexion = Nexo.conex();
        idProducto  = idP;
        agregar = agr;
        categoria.setEnabled(false);
        
        if(idProducto != "" && agregar == false)
        {
            ObtienedatosProducto();
        }
        
        
    }
    
    public static void ObtienedatosProducto(){
        
        try{
            String Qery = "SELECT * FROM empleado.Producto WHERE idProducto = " + idProducto;
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[4];// las tres columnas
            while(rt.next()){
                Aux[0] = rt.getString(1);
                categoria.setText(rt.getString(2));
                preciotxt.setText(rt.getString(3));
                sabortxt.setText(rt.getString(4));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombre = new javax.swing.JLabel();
        categoria = new javax.swing.JTextField();
        catbtn = new javax.swing.JButton();
        tipoc = new javax.swing.JLabel();
        preciotxt = new javax.swing.JTextField();
        telefono = new javax.swing.JLabel();
        sabortxt = new javax.swing.JTextField();
        cancelarbtn = new javax.swing.JButton();
        aceptarbtn1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        nombre.setText("Categoria");
        nombre.setName("nombre"); // NOI18N

        categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaActionPerformed(evt);
            }
        });

        catbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Seleccionar.png"))); // NOI18N
        catbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catbtnActionPerformed(evt);
            }
        });

        tipoc.setText("Precio");
        tipoc.setName("nombre"); // NOI18N

        preciotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preciotxtActionPerformed(evt);
            }
        });

        telefono.setText("Sabor");
        telefono.setName("nombre"); // NOI18N

        sabortxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sabortxtActionPerformed(evt);
            }
        });

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
                    .addComponent(sabortxt)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nombre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tipoc, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telefono, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(preciotxt)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelarbtn)
                        .addGap(120, 120, 120)
                        .addComponent(aceptarbtn1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(catbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(catbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(tipoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(preciotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(telefono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sabortxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarbtn)
                    .addComponent(aceptarbtn1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void catbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catbtnActionPerformed
        SeleccionaCategoria SC = new SeleccionaCategoria();
        SC.setVisible(true);
    }//GEN-LAST:event_catbtnActionPerformed

    private void categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoriaActionPerformed

    private void sabortxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sabortxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sabortxtActionPerformed

    private void preciotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preciotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_preciotxtActionPerformed

    private void aceptarbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarbtn1ActionPerformed
        String cat = categoria.getText();
        String pr = preciotxt.getText();
        String sa = sabortxt.getText();

        if(!"".equals(pr) && !"".equals(cat) && !"".equals(sa))
        {
            if(!"".equals(idProducto) && agregar == false)
            {
                Nexo.ejecutaSQL("UPDATE empleado.Producto SET idCategoria = '" + categoria.getText() + "', precio = '" + preciotxt.getText() + "', sabor = '"+ sabortxt.getText() + "' WHERE idProducto = "+ idProducto, false);
                Inventario.ActualizagridProducto();
                Inventario.ActualizagridProductoStock();
                Inventario.ActualizagridStock();
                this.dispose();
                JOptionPane.showMessageDialog(null, "Actualización Correcta");
            }
            else
            {
                Nexo.ejecutaSQL("INSERT INTO empleado.Producto(idCategoria,precio,sabor) VALUES('"+ categoria.getText() +"', '"+preciotxt.getText()+"', '"+ sabortxt.getText() +"')", false);
                Inventario.ActualizagridProducto();
                Inventario.ActualizagridProductoStock();
                Inventario.ActualizagridStock();
                this.dispose();
                JOptionPane.showMessageDialog(null, "Registro agregado");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Escriba todos los datos requeridos");

        }
        
    }//GEN-LAST:event_aceptarbtn1ActionPerformed

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
            java.util.logging.Logger.getLogger(Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Producto(idProducto, agregar).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarbtn1;
    private javax.swing.JButton cancelarbtn;
    private javax.swing.JButton catbtn;
    public static javax.swing.JTextField categoria;
    private javax.swing.JLabel nombre;
    public static javax.swing.JTextField preciotxt;
    public static javax.swing.JTextField sabortxt;
    private javax.swing.JLabel telefono;
    private javax.swing.JLabel tipoc;
    // End of variables declaration//GEN-END:variables
}
