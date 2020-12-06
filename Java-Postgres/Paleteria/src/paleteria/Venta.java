
package paleteria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static paleteria.Inventario.rt;
import static paleteria.Inventario.st;
import static paleteria.SeleccionaCliente.rt;
import static paleteria.SeleccionaCliente.st;


public class Venta extends javax.swing.JFrame {

   private static String idSocur, idClie, idVenta, Qery;
   private static DefaultTableModel modelo;
   private static Connection Conexion = null;
   public static Statement st = null;
   public static ResultSet rt;
   private String idv, ids, u; 
   
   
    public Venta(String idS, String idC) {
        initComponents();
        
        idSocur = idS;
        idClie = idC;
        Conexion = Nexo.conex();
        Venta_carga();

    }
    
    private void Venta_carga()
    {
        Nexo.ejecutaSQL("insert into empleado.Venta(idCliente,montoTotal,fechaVenta) values("+ idClie +",0.0,current_date);", false);
        idVenta = Nexo.obtenultimoid("empleado.Venta","idVenta");
        ActualizagridVenta();
        ActualizagridDetalles();
    }
    
   
    
    @SuppressWarnings("unchecked")
    
    public void ActualizagridVenta(){
        modelo = new DefaultTableModel(); // para diseno de la tabla 
        modelo.addColumn("idVenta");
        modelo.addColumn("Cliente");
        modelo.addColumn("Total");
        modelo.addColumn("Descuento");
        modelo.addColumn("Fecha");
        
        try{
            String Qery = "select v.idVenta, c.nombreCliente as cliente, v.montoTotal, c.descuento, v.fechaVenta from empleado.Venta v inner join empleado.Cliente c on v.idCliente = c.idCliente where idVenta =" + this.idVenta;
                            
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[5];// las tres columnas
            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                Aux[4] = rt.getString(5);
                modelo.addRow(Aux);
            }
            this.DataVenta.setModel(modelo);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    public static void ActualizagridDetalles(){
        modelo = new DefaultTableModel(); // para diseno de la tabla 
        modelo.addColumn("idVenta");
        modelo.addColumn("idStock");
        modelo.addColumn("Sabor");
        modelo.addColumn("Categoria");
        modelo.addColumn("Unidades");
        modelo.addColumn("Precio");
        modelo.addColumn("Subtotal");
        try{
            Qery = "select d.idVenta, s.idStock, p.sabor, c.nombreCategoria as categoria, d.unidades, p.precio, d.subTotal from empleado.DetalleVenta d inner join empleado.Stock s on s.idStock = d.idStock inner join empleado.Producto p on p.idProducto = s.idProducto inner join empleado.Categoria c on c.idCategoria = p.idCategoria where idVenta = " + Venta.idVenta;                           
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[7];// las tres columnas
            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                Aux[4] = rt.getString(5);
                Aux[5] = rt.getString(6);
                Aux[6] = rt.getString(7);
                modelo.addRow(Aux);
            }
            Venta.DataProductos.setModel(modelo);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        VentaPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DataVenta = new javax.swing.JTable();
        ProductosPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DataProductos = new javax.swing.JTable();
        Ventatollbar = new javax.swing.JToolBar();
        Agregarbtn = new javax.swing.JButton();
        Eliminarbtn = new javax.swing.JButton();
        aceptarbtn = new javax.swing.JButton();
        cancelarbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Venta");

        VentaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Venta"));
        VentaPanel.setPreferredSize(new java.awt.Dimension(503, 100));

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));

        DataVenta.setBackground(new java.awt.Color(204, 204, 204));
        DataVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        DataVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataVentaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(DataVenta);

        javax.swing.GroupLayout VentaPanelLayout = new javax.swing.GroupLayout(VentaPanel);
        VentaPanel.setLayout(VentaPanelLayout);
        VentaPanelLayout.setHorizontalGroup(
            VentaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        VentaPanelLayout.setVerticalGroup(
            VentaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
        );

        ProductosPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Productos"));
        ProductosPanel.setPreferredSize(new java.awt.Dimension(503, 100));

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));

        DataProductos.setBackground(new java.awt.Color(204, 204, 204));
        DataProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        DataProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataProductosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(DataProductos);

        Ventatollbar.setRollover(true);

        Agregarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Agregar.png"))); // NOI18N
        Agregarbtn.setFocusable(false);
        Agregarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Agregarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Agregarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarbtnActionPerformed(evt);
            }
        });
        Ventatollbar.add(Agregarbtn);

        Eliminarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar.png"))); // NOI18N
        Eliminarbtn.setFocusable(false);
        Eliminarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Eliminarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Eliminarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarbtnActionPerformed(evt);
            }
        });
        Ventatollbar.add(Eliminarbtn);

        javax.swing.GroupLayout ProductosPanelLayout = new javax.swing.GroupLayout(ProductosPanel);
        ProductosPanel.setLayout(ProductosPanelLayout);
        ProductosPanelLayout.setHorizontalGroup(
            ProductosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
            .addComponent(Ventatollbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ProductosPanelLayout.setVerticalGroup(
            ProductosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProductosPanelLayout.createSequentialGroup()
                .addComponent(Ventatollbar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
        );

        aceptarbtn.setText("Aceptar");
        aceptarbtn.setFocusable(false);
        aceptarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        aceptarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        aceptarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarbtnActionPerformed(evt);
            }
        });

        cancelarbtn.setText("Cancelar");
        cancelarbtn.setFocusable(false);
        cancelarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cancelarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
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
                    .addComponent(VentaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .addComponent(ProductosPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelarbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(aceptarbtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(VentaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ProductosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelarbtn)
                    .addComponent(aceptarbtn))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DataVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataVentaMouseClicked

    }//GEN-LAST:event_DataVentaMouseClicked

    private void DataProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataProductosMouseClicked
       int fila = DataProductos.rowAtPoint(evt.getPoint());
        
        idv = modelo.getValueAt(fila,0).toString();
        ids = modelo.getValueAt(fila,1).toString();
        u = modelo.getValueAt(fila,4).toString();
    }//GEN-LAST:event_DataProductosMouseClicked

    private void EliminarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarbtnActionPerformed
      
       if(idv != "")
       {
            Nexo.ejecutaSQL("DELETE FROM empleado.DetalleVenta WHERE idVenta = "+ idv +" and idStock = "+ ids +" and unidades = " + u,false);
            Venta.ActualizagridDetalles();
            idv = "";
            ids = "";
            u = "";
       }
       else
       {
            JOptionPane.showMessageDialog(null, "Seleccione un producto para eliminar de la venta");
       }

    }//GEN-LAST:event_EliminarbtnActionPerformed

    private void cancelarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarbtnActionPerformed
        Nexo.ejecutaSQL("DELETE FROM empleado.DetalleVenta WHERE idVenta = "+Venta.idVenta,false);
        Nexo.ejecutaSQL("DELETE FROM empleado.Venta WHERE idVenta = "+Venta.idVenta,false);
        
        this.dispose();
    }//GEN-LAST:event_cancelarbtnActionPerformed

    private void AgregarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarbtnActionPerformed
        AltaDetalle AlD = new AltaDetalle(idVenta);
        AlD.setVisible(true);
    }//GEN-LAST:event_AgregarbtnActionPerformed

    private void aceptarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarbtnActionPerformed
        this.dispose();
        JOptionPane.showMessageDialog(this, "Venta Exitosa!!!");
        Inventario.ActualizagridVenta();

    }//GEN-LAST:event_aceptarbtnActionPerformed

    
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
            java.util.logging.Logger.getLogger(Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Venta(idSocur, idClie).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Agregarbtn;
    public static javax.swing.JTable DataProductos;
    private javax.swing.JTable DataVenta;
    private javax.swing.JButton Eliminarbtn;
    private javax.swing.JPanel ProductosPanel;
    private javax.swing.JPanel VentaPanel;
    private javax.swing.JToolBar Ventatollbar;
    private javax.swing.JButton aceptarbtn;
    private javax.swing.JButton cancelarbtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
