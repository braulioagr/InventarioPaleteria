package paleteria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static paleteria.Inventario.DataVenta;
import static paleteria.Inventario.rt;
import static paleteria.Inventario.st;
import static paleteria.Venta.rt;
import static paleteria.Venta.st;


public class ConsultaDetalleVenta extends javax.swing.JFrame {

    private static String idVenta, idCliente;
    private static Connection Conexion = null;
    public static Statement st = null;
    public static ResultSet rt;
    private static DefaultTableModel modeloV;
    
    public ConsultaDetalleVenta(String idV) {
        initComponents();
        Conexion = Nexo.conex();
        idVenta = idV;
        ActualizagridVenta();
        ActualizagridCliente();
        ActualizagridDetalles();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        VentaPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DataVenta = new javax.swing.JTable();
        ClientesPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DataCliente = new javax.swing.JTable();
        DetallesPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        DataDetalles = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalle de venta");

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
        jScrollPane1.setViewportView(DataVenta);

        javax.swing.GroupLayout VentaPanelLayout = new javax.swing.GroupLayout(VentaPanel);
        VentaPanel.setLayout(VentaPanelLayout);
        VentaPanelLayout.setHorizontalGroup(
            VentaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
        );
        VentaPanelLayout.setVerticalGroup(
            VentaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
        );

        ClientesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));

        DataCliente.setBackground(new java.awt.Color(204, 204, 204));
        DataCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(DataCliente);

        javax.swing.GroupLayout ClientesPanelLayout = new javax.swing.GroupLayout(ClientesPanel);
        ClientesPanel.setLayout(ClientesPanelLayout);
        ClientesPanelLayout.setHorizontalGroup(
            ClientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
        );
        ClientesPanelLayout.setVerticalGroup(
            ClientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        DetallesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles de venta"));

        jScrollPane3.setBackground(new java.awt.Color(204, 204, 204));

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
        jScrollPane3.setViewportView(DataDetalles);

        javax.swing.GroupLayout DetallesPanelLayout = new javax.swing.GroupLayout(DetallesPanel);
        DetallesPanel.setLayout(DetallesPanelLayout);
        DetallesPanelLayout.setHorizontalGroup(
            DetallesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
        );
        DetallesPanelLayout.setVerticalGroup(
            DetallesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(VentaPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                    .addComponent(ClientesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DetallesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(VentaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClientesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DetallesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void ActualizagridVenta(){
        modeloV = new DefaultTableModel(); // para diseno de la tabla 
        modeloV.addColumn("idVenta");
        modeloV.addColumn("Cliente");
        modeloV.addColumn("Total");
        modeloV.addColumn("Fecha");
        
        try{
            String Qery = "SELECT * FROM empleado.Venta WHERE idVenta = " + idVenta;
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[4];// las tres columnas
            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                idCliente = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                modeloV.addRow(Aux);
            }
           DataVenta.setModel(modeloV);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void ActualizagridCliente(){
        modeloV = new DefaultTableModel(); // para diseno de la tabla 
        modeloV.addColumn("idCliente");
        modeloV.addColumn("Nombre");
        modeloV.addColumn("Telefono");
        modeloV.addColumn("Tipo cliente");
        modeloV.addColumn("Descuento");

        
        try{
            String Qery = "SELECT * FROM empleado.Cliente WHERE idCliente = " + idCliente;
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[5];// las tres columnas
            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                Aux[4] = rt.getString(5);
                modeloV.addRow(Aux);
            }
           DataCliente.setModel(modeloV);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
     public void ActualizagridDetalles(){
        modeloV = new DefaultTableModel(); // para diseno de la tabla 
        modeloV.addColumn("idVenta");
        modeloV.addColumn("idStock");
        modeloV.addColumn("Sabor");
        modeloV.addColumn("Categoria");
        modeloV.addColumn("Unidades");
        modeloV.addColumn("Precio");
        modeloV.addColumn("Subtotal");
        try{
            String Qery = "select d.idVenta, s.idStock, p.sabor, c.nombreCategoria as categoria, d.unidades, p.precio, d.subTotal from empleado.DetalleVenta d inner join empleado.Stock s on s.idStock = d.idStock inner join empleado.Producto p on p.idProducto = s.idProducto inner join empleado.Categoria c on c.idCategoria = p.idCategoria where idVenta = " + idVenta;                           
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
                modeloV.addRow(Aux);
            }
            DataDetalles.setModel(modeloV);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
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
            java.util.logging.Logger.getLogger(ConsultaDetalleVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaDetalleVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaDetalleVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaDetalleVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaDetalleVenta(idVenta).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ClientesPanel;
    private javax.swing.JTable DataCliente;
    private javax.swing.JTable DataDetalles;
    private javax.swing.JTable DataVenta;
    private javax.swing.JPanel DetallesPanel;
    private javax.swing.JPanel VentaPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
