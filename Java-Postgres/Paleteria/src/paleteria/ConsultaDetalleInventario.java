package paleteria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import static paleteria.Restock.DataInventario;
import static paleteria.Restock.rt;
import static paleteria.Restock.st;

public class ConsultaDetalleInventario extends javax.swing.JFrame {

    private static Connection Conexion = null;
    public static Statement st = null;
    public static ResultSet rt;
    private static DefaultTableModel modelo, modeloP;
     private static String idInventario;
     
    public ConsultaDetalleInventario(String inv) {
        initComponents();
        Conexion =  Nexo.conex();
        
        idInventario =  inv;
        ActualizagridInventario();
        ActualizagridInventarioP();
    }

    
    @SuppressWarnings("unchecked")
    
    public static void ActualizagridInventario(){
        
        modelo = new DefaultTableModel(); // para diseno de la tabla
        modelo.addColumn("idInventario");
        modelo.addColumn("Dirección Socursal");
        modelo.addColumn("Total de productos recibidos");
        modelo.addColumn("Fecha de recepción");
        
        try{
            String Qery ="select  distinct(i.idInventario), s.direccion, SUM(p.cantidadRecibida) as total, i.fechaRecepcion from empleado.Inventario i inner join empleado.InventarioProducto p on i.idInventario = p.idInventario inner join empleado.Sucursal s on s.idSucursal = i.idSucursal WHERE i.idInventario = "+ idInventario+" group by i.idInventario, s.direccion,i.fechaRecepcion ";
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[4];

            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                modelo.addRow(Aux);
            }
            DataInventario.setModel(modelo);
        }
        catch(Exception e){
        
        }
    }
    
    public static void ActualizagridInventarioP()
    {
        modeloP = new DefaultTableModel(); // para diseno de la tabla
        modeloP.addColumn("Sabor");
        modeloP.addColumn("Categoria");
        modeloP.addColumn("Tamaño");
        modeloP.addColumn("Cantidad");
        
        try{
            String Qery = "select p.sabor, c.nombreCategoria as categoria, c.tamano, i.cantidadRecibida as cantidad from empleado.InventarioProducto i inner join empleado.Producto p on i.idProducto = p.idProducto inner join empleado.Categoria c on p.idCategoria = c.idCategoria where i.idInventario = "+idInventario;
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[4];

            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                modeloP.addRow(Aux);
            }
            DataDetalles.setModel(modeloP);
        }
        catch(Exception e){
        
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        InventarioPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DataInventario = new javax.swing.JTable();
        ProductosPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DataDetalles = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta Detalle Inventario");

        InventarioPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Inventario"));
        InventarioPanel.setPreferredSize(new java.awt.Dimension(503, 100));

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));

        DataInventario.setBackground(new java.awt.Color(204, 204, 204));
        DataInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        DataInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataInventarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(DataInventario);

        javax.swing.GroupLayout InventarioPanelLayout = new javax.swing.GroupLayout(InventarioPanel);
        InventarioPanel.setLayout(InventarioPanelLayout);
        InventarioPanelLayout.setHorizontalGroup(
            InventarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        InventarioPanelLayout.setVerticalGroup(
            InventarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
        );

        ProductosPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Productos recibidos"));

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));

        DataDetalles.setBackground(new java.awt.Color(204, 204, 204));
        DataDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        DataDetalles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataDetallesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(DataDetalles);

        javax.swing.GroupLayout ProductosPanelLayout = new javax.swing.GroupLayout(ProductosPanel);
        ProductosPanel.setLayout(ProductosPanelLayout);
        ProductosPanelLayout.setHorizontalGroup(
            ProductosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
        );
        ProductosPanelLayout.setVerticalGroup(
            ProductosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ProductosPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(InventarioPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InventarioPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ProductosPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DataInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataInventarioMouseClicked

    }//GEN-LAST:event_DataInventarioMouseClicked

    private void DataDetallesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataDetallesMouseClicked
        
    }//GEN-LAST:event_DataDetallesMouseClicked

    
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
            java.util.logging.Logger.getLogger(ConsultaDetalleInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaDetalleInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaDetalleInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaDetalleInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaDetalleInventario(idInventario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable DataDetalles;
    public static javax.swing.JTable DataInventario;
    private javax.swing.JPanel InventarioPanel;
    private javax.swing.JPanel ProductosPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
