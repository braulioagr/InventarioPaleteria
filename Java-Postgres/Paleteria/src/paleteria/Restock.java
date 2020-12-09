package paleteria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static paleteria.Venta.ActualizagridDetalles;

public class Restock extends javax.swing.JFrame {

    
    private static Connection Conexion = null;
    public static Statement st = null;
    public static ResultSet rt;
    private static DefaultTableModel modelo, modeloP;
    
    private static String idSocursal, idInventario;
    
            
    public Restock(String idS) 
    {
        initComponents();
        
        Conexion =  Nexo.conex();
        idSocursal = idS;
        Inventario_carga();
        
    }
    
    private void Inventario_carga()
    {
        Nexo.ejecutaSQL("insert into empleado.Inventario(idSucursal, fechaRecepcion) values("+idSocursal+",current_date)", false);
        idInventario = Nexo.obtenultimoid("empleado.Inventario","idInventario");
        ActualizagridInventario2();
    }
    
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
            DataInventario1.setModel(modelo);
        }
        catch(Exception e){
        
        }
    }
    
    public static void ActualizagridInventario2(){
        
        modelo = new DefaultTableModel(); // para diseno de la tabla
        modelo.addColumn("idInventario");
        modelo.addColumn("Dirección Socursal");
        modelo.addColumn("Fecha de recepción");
        
        try{
            String Qery = "select  distinct(i.idInventario), s.direccion as Sucursal, i.fechaRecepcion from empleado.Inventario i inner join empleado.Sucursal s on s.idSucursal = i.idSucursal where i.idInventario = "+ idInventario +" group by i.idInventario, s.direccion,i.fechaRecepcion";
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[4];

            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                modelo.addRow(Aux);
            }
            DataInventario1.setModel(modelo);
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
            DataInventario.setModel(modeloP);
        }
        catch(Exception e){
        
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        categoriaToolBar = new javax.swing.JToolBar();
        categoriaagregarbtn = new javax.swing.JButton();
        InventarioPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DataInventario = new javax.swing.JTable();
        InventarioPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DataInventario1 = new javax.swing.JTable();
        cancelarbtn = new javax.swing.JButton();
        aceptarbtn1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Restock");

        categoriaToolBar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        categoriaToolBar.setRollover(true);

        categoriaagregarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Agregar.png"))); // NOI18N
        categoriaagregarbtn.setFocusable(false);
        categoriaagregarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        categoriaagregarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        categoriaagregarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaagregarbtnActionPerformed(evt);
            }
        });
        categoriaToolBar.add(categoriaagregarbtn);

        InventarioPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Stocks"));
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
        );

        InventarioPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Inventario"));
        InventarioPanel1.setPreferredSize(new java.awt.Dimension(503, 100));

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));

        DataInventario1.setBackground(new java.awt.Color(204, 204, 204));
        DataInventario1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        DataInventario1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataInventario1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(DataInventario1);

        javax.swing.GroupLayout InventarioPanel1Layout = new javax.swing.GroupLayout(InventarioPanel1);
        InventarioPanel1.setLayout(InventarioPanel1Layout);
        InventarioPanel1Layout.setHorizontalGroup(
            InventarioPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        InventarioPanel1Layout.setVerticalGroup(
            InventarioPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
        );

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
            .addComponent(categoriaToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
            .addComponent(InventarioPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
            .addComponent(InventarioPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cancelarbtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(aceptarbtn1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(categoriaToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(InventarioPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(InventarioPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aceptarbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DataInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataInventarioMouseClicked

    }//GEN-LAST:event_DataInventarioMouseClicked

    private void DataInventario1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataInventario1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DataInventario1MouseClicked

    private void aceptarbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarbtn1ActionPerformed
        this.dispose();
        Inventario.ActualizagridInventario();
        Inventario.ActualizagridStock();
       JOptionPane.showMessageDialog(this, "Inventario correcto");

    }//GEN-LAST:event_aceptarbtn1ActionPerformed

    private void categoriaagregarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaagregarbtnActionPerformed
        AltaRestock AR = new AltaRestock(idInventario);
        AR.setVisible(true);
    }//GEN-LAST:event_categoriaagregarbtnActionPerformed

    private void cancelarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarbtnActionPerformed
        Nexo.ejecutaSQL("DELETE FROM empleado.InventarioProducto WHERE idInventario = "+idInventario,false);
        Nexo.ejecutaSQL("DELETE FROM empleado.Inventario WHERE idInventario = "+idInventario,false);
        
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
            java.util.logging.Logger.getLogger(Restock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Restock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Restock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Restock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Restock(idSocursal).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable DataInventario;
    public static javax.swing.JTable DataInventario1;
    private javax.swing.JPanel InventarioPanel;
    private javax.swing.JPanel InventarioPanel1;
    private javax.swing.JButton aceptarbtn1;
    private javax.swing.JButton cancelarbtn;
    private javax.swing.JToolBar categoriaToolBar;
    private javax.swing.JButton categoriaagregarbtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
