package paleteria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import static paleteria.Venta.rt;
import static paleteria.Venta.st;


public class AltaDetalle extends javax.swing.JFrame {
    
   private static String idStock, idClie, idVenta, idSucursal;
   private static String idoldStock, oldunidades;
   public String idP,unidades;
   public static boolean editar = false;
   private DefaultTableModel modelo;
   private Connection Conexion = null;
   public static Statement st = null;
   public static ResultSet rt;
   
    
    public AltaDetalle(String idv, String idsu, boolean ed, String old) {
        initComponents();
        editar = ed;
        idVenta = idv;
        idSucursal =  idsu;
        idoldStock = old;
        Conexion = Nexo.conex();
        ActualizagridProd();
    }
    
     public void ActualizagridProd(){
        modelo = new DefaultTableModel(); // para diseno de la tabla 
        modelo.addColumn("idProducto");
        modelo.addColumn("Sabor");
        modelo.addColumn("Precio");
        modelo.addColumn("Categoria");
        modelo.addColumn("Tamaño");
        modelo.addColumn("Existencias");

        
        try{
            String Qery = "select p.idProducto, p.sabor, p.precio, c.nombreCategoria as categoria, c.tamano, s.existencias from empleado.Producto p inner join empleado.Categoria c on p.idCategoria = c.idCategoria inner join empleado.Stock s on s.idProducto = p.idProducto where s.idSucursal = "+this.idSucursal + "and s.existencias !=0";
                            
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[6];// las tres columnas
            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                Aux[4] = rt.getString(5);
                Aux[5] = rt.getString(6);

                modelo.addRow(Aux);
            }
            this.DataProducto.setModel(modelo);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
     
     public String obtieneidStockxProduc(String idPro)
     {
         String idStock = "";
         try{
            String Qery = "select idStock from empleado.Stock where idProducto = " + idPro + "AND idSucursal = "+ idSucursal;
                            
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            while(rt.next()){
                 idStock = rt.getString(1);
            }
        }
        catch(Exception e){
            //JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        
         return idStock;
     }        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DataProducto = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cantidadsp = new javax.swing.JSpinner();
        aceptarbtn = new javax.swing.JButton();
        cancelarbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AltaDetalle");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel1.setText("Productos");

        DataProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        DataProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(DataProducto);

        jLabel2.setText("Cantidad");

        cantidadsp.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(46, 46, 46))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cantidadsp, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidadsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cancelarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(aceptarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DataProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataProductoMouseClicked
        
        int fila = DataProducto.rowAtPoint(evt.getPoint());
        int valordeexistencias = Integer.valueOf(modelo.getValueAt(fila, 5).toString());
        idP = modelo.getValueAt(fila,0).toString();
        
        SpinnerNumberModel spinnermodel = new SpinnerNumberModel(1.0, 1.0, valordeexistencias, 1.0);        
        cantidadsp.setModel(spinnermodel);
        
        
    }//GEN-LAST:event_DataProductoMouseClicked

    private void aceptarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarbtnActionPerformed
        idStock = obtieneidStockxProduc(idP);
        unidades = cantidadsp.getValue().toString();
        
          if(idP != "")
        {
            if(editar)
        {
            Nexo.ejecutaSQL("update empleado.DetalleVenta set idStock = "+ idStock +", unidades = "+unidades+" where idVenta = " + idVenta + " AND idStock = " + idoldStock , false);
            this.dispose();
            Venta.ActualizagridDetalles();
            Venta.ActualizagridVenta();
            JOptionPane.showMessageDialog(this, "Producto modificado correctamente");
        }
        else
        {
            Nexo.ejecutaSQL("insert into empleado.DetalleVenta(idVenta,idStock,unidades,subTotal) values("+ idVenta +", "+ idStock +", "+ unidades + ",1);", false);
            this.dispose();
            Venta.ActualizagridDetalles();
            Venta.ActualizagridVenta();
            JOptionPane.showMessageDialog(this, "Producto agregado correctamente");
        }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Seleccione un Producto");

        }  
        
        
        

    }//GEN-LAST:event_aceptarbtnActionPerformed

    private void cancelarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarbtnActionPerformed
            this.dispose();
    }//GEN-LAST:event_cancelarbtnActionPerformed

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
            java.util.logging.Logger.getLogger(AltaDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AltaDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AltaDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AltaDetalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AltaDetalle(idVenta, idSucursal, editar, idoldStock).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DataProducto;
    private javax.swing.JButton aceptarbtn;
    private javax.swing.JButton cancelarbtn;
    private javax.swing.JSpinner cantidadsp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
