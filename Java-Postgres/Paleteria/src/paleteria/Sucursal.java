
package paleteria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static paleteria.Inventario.ActualizagridStock;
import static paleteria.Inventario.ActualizagridSucursalStock;


public class Sucursal extends javax.swing.JFrame {
    private static String idSucursal, horario;
    private static boolean agregar;
    private static Connection Conexion = null;
    public static Statement st = null;
    public static ResultSet rt;
    
    public Sucursal(String idS, boolean agr) {
        initComponents();
        Conexion = Nexo.conex();
        idSucursal  = idS;
        agregar = agr;
        
        if(idSucursal != "" && agregar != false)
        {
            ObtienedatosSucursal();
        }
    }
    
     public static void ObtienedatosSucursal(){
        
       try{
            String Qery = "SELECT * FROM empleado.Sucursal WHERE idSucursal = " + idSucursal;
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[4];// las tres columnas
            while(rt.next()){
                Aux[0] = rt.getString(1);
                direcciontext.setText(rt.getString(2));
                telefonotext.setText(rt.getString(3));
                horario = rt.getString(4);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
       
        String[] parts = horario.split("-");
        aperturacb.setSelectedItem(parts[0]);
        cierrecb.setSelectedItem(parts[1]);
    }
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelinicial = new javax.swing.JPanel();
        horariopanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        aperturacb = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cierrecb = new javax.swing.JComboBox<>();
        direccion = new javax.swing.JLabel();
        direcciontext = new javax.swing.JTextField();
        telefono = new javax.swing.JLabel();
        telefonotext = new javax.swing.JTextField();
        cancelarbtn = new javax.swing.JButton();
        aceptarbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Socursal");

        panelinicial.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));
        panelinicial.setPreferredSize(new java.awt.Dimension(503, 100));

        horariopanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Horario"));
        horariopanel.setPreferredSize(new java.awt.Dimension(503, 100));

        jLabel1.setText("Apertura");

        aperturacb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00" }));

        jLabel2.setText("Cierre");

        cierrecb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00" }));

        javax.swing.GroupLayout horariopanelLayout = new javax.swing.GroupLayout(horariopanel);
        horariopanel.setLayout(horariopanelLayout);
        horariopanelLayout.setHorizontalGroup(
            horariopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(horariopanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(horariopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(aperturacb, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(horariopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(cierrecb, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );
        horariopanelLayout.setVerticalGroup(
            horariopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(horariopanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(horariopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(horariopanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(aperturacb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(horariopanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cierrecb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        direccion.setText("Dirección");
        direccion.setName("direccion"); // NOI18N

        direcciontext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direcciontextActionPerformed(evt);
            }
        });

        telefono.setText("Teléfono");
        telefono.setName("nombre"); // NOI18N

        telefonotext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonotextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelinicialLayout = new javax.swing.GroupLayout(panelinicial);
        panelinicial.setLayout(panelinicialLayout);
        panelinicialLayout.setHorizontalGroup(
            panelinicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelinicialLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelinicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(horariopanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addComponent(direcciontext)
                    .addComponent(telefonotext)
                    .addGroup(panelinicialLayout.createSequentialGroup()
                        .addGroup(panelinicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(direccion)
                            .addComponent(telefono))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelinicialLayout.setVerticalGroup(
            panelinicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelinicialLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(direccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(direcciontext, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(telefono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(telefonotext, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(horariopanel, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addContainerGap())
        );

        cancelarbtn.setText("Cancelar");
        cancelarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarbtnActionPerformed(evt);
            }
        });

        aceptarbtn.setText("Aceptar");
        aceptarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelinicial, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addComponent(aceptarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelinicial, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarbtn)
                    .addComponent(aceptarbtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void direcciontextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direcciontextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direcciontextActionPerformed

    private void telefonotextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonotextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonotextActionPerformed

    private void cancelarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarbtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarbtnActionPerformed

    private void aceptarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarbtnActionPerformed
       String dir = direcciontext.getText();
        String tel = telefonotext.getText();

        if(!"".equals(dir) && !"".equals(tel))
        {
            if(!"".equals(idSucursal) && agregar != false)
            {
                horario = aperturacb.getSelectedItem().toString() + "-" + cierrecb.getSelectedItem().toString();
                Nexo.ejecutaSQL("UPDATE empleado.Sucursal SET direccion = '" + dir + "', telefono = '" +  tel + "', horario = '"+ horario + "' WHERE idSucursal = "+ idSucursal, false);
                Inventario.ActualizagridSucursal();
                ActualizagridSucursalStock();
                ActualizagridStock();
                this.dispose();
                JOptionPane.showMessageDialog(null, "Actualización Correcta");
            }
            else
            {
                horario = aperturacb.getSelectedItem().toString() + "-" + cierrecb.getSelectedItem().toString();
                Nexo.ejecutaSQL("INSERT INTO empleado.Sucursal(direccion,telefono,horario) VALUES('"+ dir +"', '"+tel+"', '"+ horario +"')", false);
                Inventario.ActualizagridSucursal();
                ActualizagridSucursalStock();
                ActualizagridStock();
                this.dispose();
                JOptionPane.showMessageDialog(null, "Registro agregado");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Escriba todos los datos requeridos");

        }
        
    }//GEN-LAST:event_aceptarbtnActionPerformed

   
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sucursal(idSucursal, agregar).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarbtn;
    public static javax.swing.JComboBox<String> aperturacb;
    private javax.swing.JButton cancelarbtn;
    public static javax.swing.JComboBox<String> cierrecb;
    private javax.swing.JLabel direccion;
    public static javax.swing.JTextField direcciontext;
    private javax.swing.JPanel horariopanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelinicial;
    private javax.swing.JLabel telefono;
    public static javax.swing.JTextField telefonotext;
    // End of variables declaration//GEN-END:variables
}
