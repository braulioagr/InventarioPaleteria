
package paleteria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static paleteria.ConsultaDetalleVenta.rt;
import static paleteria.ConsultaDetalleVenta.st;
import static paleteria.Nexo.rt;
import static paleteria.Nexo.st;
import static paleteria.Venta.DataProductos;


public final class Inventario extends javax.swing.JFrame {

    private static Connection Conexion = null;
    public static Statement st = null;
    public static ResultSet rt;
    private static DefaultTableModel modeloV, modeloC, modeloP, modeloS, modeloSP, modeloSS, modeloSu, modeloCa, modeloI;
    public static String usuario, contra;
    private String idVenta = "";
    private String idCliente = "";
    private String idProducto = "";
    private String idSucursal = "";
    private String idCategoria = "";
    private String idInventario = "";
    

    public Inventario(String usu, String cont) {
        initComponents();
        
        usuario = usu;
        contra = cont;
        Conexion = Nexo.conex();
        
        if("vendedor".equals(usu))
        {
            
            //Inventario.setEnabledAt(3, false);
            ventaseliminarbtn.setEnabled(false);
            productosagregarbtn.setEnabled(false);
            productoseditarbtn.setEnabled(false);
            productoseliminarbtn.setEnabled(false);
            sucursalagregarbtn.setEnabled(false);
            sucursaleditarbtn.setEnabled(false);
            sucursaleliminarbtn.setEnabled(false);
            categoriaagregarbtn.setEnabled(false);
            categoriaeditarbtn.setEnabled(false);
            categoriaeliminarbtn.setEnabled(false);
            inventarioagregarbtn.setEnabled(false);
            inventariobuscarbtn.setEnabled(false);
            inventarioeliminarbtn.setEnabled(false);
        }
        
        if("empleado1".equals(usu))
        {
            Inventario.setEnabledAt(0, false);
            Inventario.setEnabledAt(1, false);
            productosagregarbtn.setEnabled(false);
            productoseditarbtn.setEnabled(false);
            productoseliminarbtn.setEnabled(false);
            sucursalagregarbtn.setEnabled(false);
            sucursaleditarbtn.setEnabled(false);
            sucursaleliminarbtn.setEnabled(false);
            categoriaagregarbtn.setEnabled(false);
            categoriaeditarbtn.setEnabled(false);
            categoriaeliminarbtn.setEnabled(false);
        }
        
        ActualizagridVenta();
        ActualizagridCliente();
        ActualizagridProducto();
        ActualizagridStock();
        ActualizagridProductoStock();
        ActualizagridSucursalStock();
        ActualizagridSucursal();
        ActualizagridCategoria();
        ActualizagridInventario();
    }
    
    @SuppressWarnings("unchecked")
    
    public static void ActualizagridVenta(){
        modeloV = new DefaultTableModel(); // para diseno de la tabla 
        modeloV.addColumn("idVenta");
        modeloV.addColumn("Cliente");
        modeloV.addColumn("Total");
        modeloV.addColumn("Descuento");
        modeloV.addColumn("Fecha");
        
        try{
            String Qery = "select v.idVenta, c.nombreCliente as cliente, v.montoTotal, c.descuento, v.fechaVenta  from empleado.Venta v inner join empleado.Cliente c on v.idCliente = c.idCliente";
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
           DataVenta.setModel(modeloV);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public static void ActualizagridCliente(){
        modeloC = new DefaultTableModel(); // para diseno de la tabla 
        modeloC.addColumn("idCliente");
        modeloC.addColumn("Nombre");
        modeloC.addColumn("Telefono");
        modeloC.addColumn("Tipo cliente");
        modeloC.addColumn("Descuento");

        
        try{
            String Qery = "SELECT * FROM empleado.Cliente";
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[5];// las tres columnas
            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                Aux[4] = rt.getString(5);
                modeloC.addRow(Aux);
            }
           DataClientes.setModel(modeloC);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public static void ActualizagridProducto(){
        modeloP = new DefaultTableModel(); // para diseno de la tabla 
        modeloP.addColumn("idProducto");
        modeloP.addColumn("idCategoria");
        modeloP.addColumn("Precio");
        modeloP.addColumn("Sabor");

        
        try{
            String Qery = "SELECT * FROM empleado.Producto";
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[4];// las tres columnas
            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                modeloP.addRow(Aux);
            }
           DataProductos.setModel(modeloP);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public static void ActualizagridStock(){
        modeloS = new DefaultTableModel(); // para diseno de la tabla 
        modeloS.addColumn("idStock");
        modeloS.addColumn("Sucursal");
        modeloS.addColumn("idProducto");
        modeloS.addColumn("Sabor");
        modeloS.addColumn("Precio");
        modeloS.addColumn("Categoria");
        modeloS.addColumn("Tamaño");
        modeloS.addColumn("Existencias");

        
        try{
            String Qery = "select idStock, direccion as Sucursal, p.idProducto, Sabor, Precio, NombreCategoria as Categoria, Tamano, Existencias from empleado.Stock s inner join empleado.Sucursal su on su.idSucursal = s.idSucursal inner join empleado.Producto p on p.idProducto = s.idProducto inner join empleado.Categoria c on p.idCategoria = c.idCategoria ORDER BY direccion";
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[8];// las tres columnas
            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                Aux[4] = rt.getString(5);
                Aux[5] = rt.getString(6);
                Aux[6] = rt.getString(7);
                Aux[7] = rt.getString(8);
                modeloS.addRow(Aux);
            }
           DataStock.setModel(modeloS);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
     
    public static void ActualizagridProductoStock(){
        modeloSP = new DefaultTableModel(); // para diseno de la tabla 
        modeloSP.addColumn("idProducto");
        modeloSP.addColumn("Sabor");
        modeloSP.addColumn("Categoria");
        modeloSP.addColumn("Tamaño");


        try{
            String Qery = "select idProducto, sabor, nombreCategoria, tamano from empleado.Producto p inner join empleado.Categoria c on p.idCategoria = c.idCategoria";
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[4];// las tres columnas
            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                modeloSP.addRow(Aux);
            }
           DataProductosstock.setModel(modeloSP);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public static void ActualizagridSucursalStock(){
        
        modeloSS = new DefaultTableModel(); // para diseno de la tabla
        modeloSS.addColumn("ID");
        modeloSS.addColumn("Dirección");
        modeloSS.addColumn("Telefono");
        modeloSS.addColumn("Horario");
        
        try{
            String Qery = "SELECT * FROM empleado.Sucursal";
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[4];

            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                modeloSS.addRow(Aux);
            }
            DataSucursalesstock.setModel(modeloSS);
        }
        catch(Exception e){
        
        }
    }
    
    public static void ActualizagridSucursal(){
        
        modeloSu = new DefaultTableModel(); // para diseno de la tabla
        modeloSu.addColumn("ID");
        modeloSu.addColumn("Dirección");
        modeloSu.addColumn("Telefono");
        modeloSu.addColumn("Horario");
        
        try{
            String Qery = "SELECT * FROM empleado.Sucursal";
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[4];

            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                modeloSu.addRow(Aux);
            }
            DataSucursal.setModel(modeloSu);
        }
        catch(Exception e){
        
        }
    }
    
    public static void ActualizagridCategoria(){
        
        modeloCa = new DefaultTableModel(); // para diseno de la tabla
        modeloCa.addColumn("ID");
        modeloCa.addColumn("Nombre");
        modeloCa.addColumn("Tamaño");
        
        try{
            String Qery = "SELECT * FROM empleado.Categoria";
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[4];

            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                modeloCa.addRow(Aux);
            }
            DataCategoria.setModel(modeloCa);
        }
        catch(Exception e){
        
        }
    }
    
    public static void ActualizagridInventario(){
        
        modeloI = new DefaultTableModel(); // para diseno de la tabla
        modeloI.addColumn("idInventario");
        modeloI.addColumn("Dirección Socursal");
        modeloI.addColumn("Total de productos recibidos");
        modeloI.addColumn("Fecha de recepción");
        
        try{
            String Qery = "select  distinct(i.idInventario), s.direccion, SUM(p.cantidadRecibida) as total, i.fechaRecepcion from empleado.Inventario i inner join empleado.InventarioProducto p on i.idInventario = p.idInventario inner join empleado.Sucursal s on s.idSucursal = i.idSucursal group by i.idInventario, s.direccion,i.fechaRecepcion";
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[4];

            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                modeloI.addRow(Aux);
            }
            DataInventario.setModel(modeloI);
        }
        catch(Exception e){
        
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        Inventario = new javax.swing.JTabbedPane();
        Ventas = new javax.swing.JPanel();
        ventasToolBar = new javax.swing.JToolBar();
        ventasagregarbtn = new javax.swing.JButton();
        ventasbuscarbtn = new javax.swing.JButton();
        ventaseliminarbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ventastxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        DataVenta = new javax.swing.JTable();
        Clientes = new javax.swing.JPanel();
        clientesToolBar1 = new javax.swing.JToolBar();
        clientesagregarbtn = new javax.swing.JButton();
        clienteseditarbtn = new javax.swing.JButton();
        clienteseliminarbtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        clientestxt1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        DataClientes = new javax.swing.JTable();
        Productos = new javax.swing.JPanel();
        productosToolBar = new javax.swing.JToolBar();
        productosagregarbtn = new javax.swing.JButton();
        productoseditarbtn = new javax.swing.JButton();
        productoseliminarbtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        productostxt = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        DataProductos = new javax.swing.JTable();
        Stock = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        DataStock = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        DataProductosstock = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        DataSucursalesstock = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        stocksocursalestxt = new javax.swing.JTextField();
        stockproductostxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Sucursal = new javax.swing.JPanel();
        sucursalToolBar = new javax.swing.JToolBar();
        sucursalagregarbtn = new javax.swing.JButton();
        sucursaleditarbtn = new javax.swing.JButton();
        sucursaleliminarbtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        sucursaltxt = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        DataSucursal = new javax.swing.JTable();
        Categoria = new javax.swing.JPanel();
        categoriaToolBar = new javax.swing.JToolBar();
        categoriaagregarbtn = new javax.swing.JButton();
        categoriaeditarbtn = new javax.swing.JButton();
        categoriaeliminarbtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        categoriatxt = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        DataCategoria = new javax.swing.JTable();
        InventarioP = new javax.swing.JPanel();
        inventarioToolBar = new javax.swing.JToolBar();
        inventarioagregarbtn = new javax.swing.JButton();
        inventariobuscarbtn = new javax.swing.JButton();
        inventarioeliminarbtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        inventariotxt = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        DataInventario = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Paleteria");

        Inventario.setBackground(new java.awt.Color(204, 204, 204));
        Inventario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        ventasToolBar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ventasToolBar.setRollover(true);

        ventasagregarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Agregar.png"))); // NOI18N
        ventasagregarbtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ventasagregarbtn.setFocusable(false);
        ventasagregarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ventasagregarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ventasagregarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasagregarbtnActionPerformed(evt);
            }
        });
        ventasToolBar.add(ventasagregarbtn);

        ventasbuscarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/busca.png"))); // NOI18N
        ventasbuscarbtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ventasbuscarbtn.setFocusable(false);
        ventasbuscarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ventasbuscarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ventasbuscarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasbuscarbtnActionPerformed(evt);
            }
        });
        ventasToolBar.add(ventasbuscarbtn);

        ventaseliminarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar.png"))); // NOI18N
        ventaseliminarbtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ventaseliminarbtn.setFocusable(false);
        ventaseliminarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ventaseliminarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ventaseliminarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventaseliminarbtnActionPerformed(evt);
            }
        });
        ventasToolBar.add(ventaseliminarbtn);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Busqueda");

        ventastxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ventastxtKeyReleased(evt);
            }
        });

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

        javax.swing.GroupLayout VentasLayout = new javax.swing.GroupLayout(Ventas);
        Ventas.setLayout(VentasLayout);
        VentasLayout.setHorizontalGroup(
            VentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ventasToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(VentasLayout.createSequentialGroup()
                .addContainerGap(722, Short.MAX_VALUE)
                .addGroup(VentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(ventastxt, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jScrollPane1)
        );
        VentasLayout.setVerticalGroup(
            VentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentasLayout.createSequentialGroup()
                .addComponent(ventasToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ventastxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addContainerGap())
        );

        Inventario.addTab("Ventas", Ventas);

        clientesToolBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        clientesToolBar1.setRollover(true);

        clientesagregarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Agregar.png"))); // NOI18N
        clientesagregarbtn.setFocusable(false);
        clientesagregarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        clientesagregarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        clientesagregarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesagregarbtnActionPerformed(evt);
            }
        });
        clientesToolBar1.add(clientesagregarbtn);

        clienteseditarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Editar.png"))); // NOI18N
        clienteseditarbtn.setFocusable(false);
        clienteseditarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        clienteseditarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        clienteseditarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteseditarbtnActionPerformed(evt);
            }
        });
        clientesToolBar1.add(clienteseditarbtn);

        clienteseliminarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar.png"))); // NOI18N
        clienteseliminarbtn.setFocusable(false);
        clienteseliminarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        clienteseliminarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        clienteseliminarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteseliminarbtnActionPerformed(evt);
            }
        });
        clientesToolBar1.add(clienteseliminarbtn);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Busqueda");

        clientestxt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                clientestxt1KeyReleased(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));

        DataClientes.setBackground(new java.awt.Color(204, 204, 204));
        DataClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        DataClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(DataClientes);

        javax.swing.GroupLayout ClientesLayout = new javax.swing.GroupLayout(Clientes);
        Clientes.setLayout(ClientesLayout);
        ClientesLayout.setHorizontalGroup(
            ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clientesToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ClientesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(clientestxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 927, Short.MAX_VALUE)
        );
        ClientesLayout.setVerticalGroup(
            ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClientesLayout.createSequentialGroup()
                .addComponent(clientesToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(clientestxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addContainerGap())
        );

        Inventario.addTab("Clientes", Clientes);

        productosToolBar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        productosToolBar.setRollover(true);

        productosagregarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Agregar.png"))); // NOI18N
        productosagregarbtn.setFocusable(false);
        productosagregarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        productosagregarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        productosagregarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productosagregarbtnActionPerformed(evt);
            }
        });
        productosToolBar.add(productosagregarbtn);

        productoseditarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Editar.png"))); // NOI18N
        productoseditarbtn.setFocusable(false);
        productoseditarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        productoseditarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        productoseditarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productoseditarbtnActionPerformed(evt);
            }
        });
        productosToolBar.add(productoseditarbtn);

        productoseliminarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar.png"))); // NOI18N
        productoseliminarbtn.setFocusable(false);
        productoseliminarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        productoseliminarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        productoseliminarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productoseliminarbtnActionPerformed(evt);
            }
        });
        productosToolBar.add(productoseliminarbtn);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Busqueda");

        productostxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                productostxtKeyReleased(evt);
            }
        });

        jScrollPane3.setBackground(new java.awt.Color(204, 204, 204));

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
        jScrollPane3.setViewportView(DataProductos);

        javax.swing.GroupLayout ProductosLayout = new javax.swing.GroupLayout(Productos);
        Productos.setLayout(ProductosLayout);
        ProductosLayout.setHorizontalGroup(
            ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(productosToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ProductosLayout.createSequentialGroup()
                .addContainerGap(722, Short.MAX_VALUE)
                .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(productostxt, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jScrollPane3)
        );
        ProductosLayout.setVerticalGroup(
            ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProductosLayout.createSequentialGroup()
                .addComponent(productosToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productostxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addContainerGap())
        );

        Inventario.addTab("Producto", Productos);

        jScrollPane4.setBackground(new java.awt.Color(204, 204, 204));

        DataStock.setBackground(new java.awt.Color(204, 204, 204));
        DataStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(DataStock);

        jScrollPane5.setBackground(new java.awt.Color(204, 204, 204));

        DataProductosstock.setBackground(new java.awt.Color(204, 204, 204));
        DataProductosstock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(DataProductosstock);

        jScrollPane6.setBackground(new java.awt.Color(204, 204, 204));

        DataSucursalesstock.setBackground(new java.awt.Color(204, 204, 204));
        DataSucursalesstock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(DataSucursalesstock);

        jLabel4.setText("Sucursales");

        stocksocursalestxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                stocksocursalestxtKeyReleased(evt);
            }
        });

        stockproductostxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                stockproductostxtKeyReleased(evt);
            }
        });

        jLabel5.setText("Productos");

        jLabel6.setText("Stock");

        javax.swing.GroupLayout StockLayout = new javax.swing.GroupLayout(Stock);
        Stock.setLayout(StockLayout);
        StockLayout.setHorizontalGroup(
            StockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StockLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StockLayout.createSequentialGroup()
                        .addGroup(StockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(StockLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(stockproductostxt, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(StockLayout.createSequentialGroup()
                        .addGroup(StockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 907, Short.MAX_VALUE)
                            .addGroup(StockLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(19, 19, 19)
                                .addComponent(stocksocursalestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );
        StockLayout.setVerticalGroup(
            StockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StockLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(StockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(stockproductostxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(StockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stocksocursalestxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Inventario.addTab("Stock", Stock);

        sucursalToolBar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sucursalToolBar.setRollover(true);

        sucursalagregarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Agregar.png"))); // NOI18N
        sucursalagregarbtn.setFocusable(false);
        sucursalagregarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sucursalagregarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        sucursalagregarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sucursalagregarbtnActionPerformed(evt);
            }
        });
        sucursalToolBar.add(sucursalagregarbtn);

        sucursaleditarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Editar.png"))); // NOI18N
        sucursaleditarbtn.setFocusable(false);
        sucursaleditarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sucursaleditarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        sucursaleditarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sucursaleditarbtnActionPerformed(evt);
            }
        });
        sucursalToolBar.add(sucursaleditarbtn);

        sucursaleliminarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar.png"))); // NOI18N
        sucursaleliminarbtn.setFocusable(false);
        sucursaleliminarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sucursaleliminarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        sucursaleliminarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sucursaleliminarbtnActionPerformed(evt);
            }
        });
        sucursalToolBar.add(sucursaleliminarbtn);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Busqueda");

        sucursaltxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sucursaltxtKeyReleased(evt);
            }
        });

        jScrollPane7.setBackground(new java.awt.Color(204, 204, 204));

        DataSucursal.setBackground(new java.awt.Color(204, 204, 204));
        DataSucursal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        DataSucursal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataSucursalMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(DataSucursal);

        javax.swing.GroupLayout SucursalLayout = new javax.swing.GroupLayout(Sucursal);
        Sucursal.setLayout(SucursalLayout);
        SucursalLayout.setHorizontalGroup(
            SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sucursalToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(SucursalLayout.createSequentialGroup()
                .addContainerGap(722, Short.MAX_VALUE)
                .addGroup(SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(sucursaltxt, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jScrollPane7)
        );
        SucursalLayout.setVerticalGroup(
            SucursalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SucursalLayout.createSequentialGroup()
                .addComponent(sucursalToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sucursaltxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addContainerGap())
        );

        Inventario.addTab("Sucursal", Sucursal);

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

        categoriaeditarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Editar.png"))); // NOI18N
        categoriaeditarbtn.setFocusable(false);
        categoriaeditarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        categoriaeditarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        categoriaeditarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaeditarbtnActionPerformed(evt);
            }
        });
        categoriaToolBar.add(categoriaeditarbtn);

        categoriaeliminarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar.png"))); // NOI18N
        categoriaeliminarbtn.setFocusable(false);
        categoriaeliminarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        categoriaeliminarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        categoriaeliminarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaeliminarbtnActionPerformed(evt);
            }
        });
        categoriaToolBar.add(categoriaeliminarbtn);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Busqueda");

        categoriatxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                categoriatxtKeyReleased(evt);
            }
        });

        jScrollPane8.setBackground(new java.awt.Color(204, 204, 204));

        DataCategoria.setBackground(new java.awt.Color(204, 204, 204));
        DataCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        DataCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataCategoriaMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(DataCategoria);

        javax.swing.GroupLayout CategoriaLayout = new javax.swing.GroupLayout(Categoria);
        Categoria.setLayout(CategoriaLayout);
        CategoriaLayout.setHorizontalGroup(
            CategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(categoriaToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(CategoriaLayout.createSequentialGroup()
                .addContainerGap(722, Short.MAX_VALUE)
                .addGroup(CategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(categoriatxt, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jScrollPane8)
        );
        CategoriaLayout.setVerticalGroup(
            CategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CategoriaLayout.createSequentialGroup()
                .addComponent(categoriaToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoriatxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addContainerGap())
        );

        Inventario.addTab("Categoria", Categoria);

        inventarioToolBar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        inventarioToolBar.setRollover(true);

        inventarioagregarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Agregar.png"))); // NOI18N
        inventarioagregarbtn.setFocusable(false);
        inventarioagregarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        inventarioagregarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        inventarioagregarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventarioagregarbtnActionPerformed(evt);
            }
        });
        inventarioToolBar.add(inventarioagregarbtn);

        inventariobuscarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/busca.png"))); // NOI18N
        inventariobuscarbtn.setFocusable(false);
        inventariobuscarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        inventariobuscarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        inventariobuscarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventariobuscarbtnActionPerformed(evt);
            }
        });
        inventarioToolBar.add(inventariobuscarbtn);

        inventarioeliminarbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar.png"))); // NOI18N
        inventarioeliminarbtn.setFocusable(false);
        inventarioeliminarbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        inventarioeliminarbtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        inventarioeliminarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventarioeliminarbtnActionPerformed(evt);
            }
        });
        inventarioToolBar.add(inventarioeliminarbtn);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Busqueda");

        inventariotxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inventariotxtKeyReleased(evt);
            }
        });

        jScrollPane9.setBackground(new java.awt.Color(204, 204, 204));

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
        jScrollPane9.setViewportView(DataInventario);

        javax.swing.GroupLayout InventarioPLayout = new javax.swing.GroupLayout(InventarioP);
        InventarioP.setLayout(InventarioPLayout);
        InventarioPLayout.setHorizontalGroup(
            InventarioPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(inventarioToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(InventarioPLayout.createSequentialGroup()
                .addContainerGap(722, Short.MAX_VALUE)
                .addGroup(InventarioPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(inventariotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jScrollPane9)
        );
        InventarioPLayout.setVerticalGroup(
            InventarioPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventarioPLayout.createSequentialGroup()
                .addComponent(inventarioToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inventariotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addContainerGap())
        );

        Inventario.addTab("Inventarios", InventarioP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Inventario))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Inventario)
                .addContainerGap())
        );

        Inventario.getAccessibleContext().setAccessibleName("tab");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //  <editor-fold defaultstate="collapsed" desc="Ventas">
    private void DataVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataVentaMouseClicked
        int fila = DataVenta.rowAtPoint(evt.getPoint());
        
        idVenta = modeloV.getValueAt(fila,0).toString();
    }//GEN-LAST:event_DataVentaMouseClicked

    private void ventasagregarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasagregarbtnActionPerformed
        JOptionPane.showMessageDialog(this,"Seleccione la sucursal de venta");
        SeleccionaSucursal selecsu = new SeleccionaSucursal(true);
        selecsu.setVisible(true);
        
    }//GEN-LAST:event_ventasagregarbtnActionPerformed

    private void ventasbuscarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasbuscarbtnActionPerformed
        if(idVenta != "")
        {
            int resp = JOptionPane.showConfirmDialog(this, "¿Quiere revisar la VENTA con identificador " + idVenta + "?");
            if(resp == 0)
            {
                ConsultaDetalleVenta DTV = new ConsultaDetalleVenta(idVenta);
                DTV.setVisible(true);
                idVenta = "";
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Seleccione un registro de VENTA");
        }
    }//GEN-LAST:event_ventasbuscarbtnActionPerformed

    private void ventaseliminarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventaseliminarbtnActionPerformed
        if(idVenta != "")
        {
            int resp = JOptionPane.showConfirmDialog(this, "¿Quiere eliminar este registro de VENTA con identificador " + idVenta + "?");
            if(resp == 0)
            {
                Nexo.ejecutaSQL("DELETE FROM empleado.DetalleVenta WHERE idVenta = "+ idVenta,false);
                Nexo.ejecutaSQL("DELETE FROM empleado.Venta WHERE idVenta = "+ idVenta,false);
                ActualizagridVenta();
                idVenta = "";
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Seleccione un registro de VENTA");
        }
    }//GEN-LAST:event_ventaseliminarbtnActionPerformed
    // </editor-fold>   
    
    //  <editor-fold defaultstate="collapsed" desc="Clientes">
    private void clientesagregarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesagregarbtnActionPerformed
        AltaCliente ALC = new AltaCliente();
        ALC.setVisible(true);
    }//GEN-LAST:event_clientesagregarbtnActionPerformed

    private void clienteseditarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteseditarbtnActionPerformed
        if(idCliente != "")
        {
            int resp = JOptionPane.showConfirmDialog(null, "¿Quiere editar el CLIENTE con identificador " + idCliente + "?");
            if(resp == 0)
            {
                ModificaCliente MC = new ModificaCliente(idCliente);
                MC.setVisible(true);
                idCliente = "";
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccione un CLIENTE");
        }
    }//GEN-LAST:event_clienteseditarbtnActionPerformed

    private void DataClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataClientesMouseClicked
        int fila = DataClientes.rowAtPoint(evt.getPoint());
        
        idCliente = modeloC.getValueAt(fila,0).toString();
    }//GEN-LAST:event_DataClientesMouseClicked

    private void clienteseliminarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteseliminarbtnActionPerformed
        if(idCliente != "")
        {
            int resp = JOptionPane.showConfirmDialog(null, "¿Quiere eliminar este CLIENTE con identificador " + idCliente + "?");
            if(resp == 0)
            {
                Nexo.ejecutaSQL("DELETE FROM empleado.Cliente WHERE idCliente = "+ idCliente,false);
                ActualizagridCliente();
                idCliente = "";
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccione un Cliente");
        }
    }//GEN-LAST:event_clienteseliminarbtnActionPerformed
    // </editor-fold> 
    
    //  <editor-fold defaultstate="collapsed" desc="Producto">
    private void productosagregarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productosagregarbtnActionPerformed
        Producto PD = new Producto("",true);
        PD.setVisible(true);
        
    }//GEN-LAST:event_productosagregarbtnActionPerformed
    
    private void productoseditarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productoseditarbtnActionPerformed
        if(idProducto != "")
        {
            Producto PD = new Producto(idProducto,false);
            PD.setVisible(true);
            idProducto = "";
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccione un PRODUCTO");

        }
        
    }//GEN-LAST:event_productoseditarbtnActionPerformed

    private void DataProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataProductosMouseClicked
         int fila = DataProductos.rowAtPoint(evt.getPoint());
        
         idProducto = modeloP.getValueAt(fila,0).toString();
    }//GEN-LAST:event_DataProductosMouseClicked

    private void productoseliminarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productoseliminarbtnActionPerformed
        
        if(idProducto != "")
        {
            int resp = JOptionPane.showConfirmDialog(null, "¿Quiere eliminar este PRODUCTO con identificador " + idProducto + "?");
            if(resp == 0)
            {
                Nexo.ejecutaSQL("DELETE FROM empleado.InventarioProducto WHERE idProducto = "+idProducto,false);
                Nexo.ejecutaSQL("DELETE FROM empleado.Producto WHERE idProducto = "+ idProducto,false);
                ActualizagridProducto();
                ActualizagridProductoStock();
                ActualizagridStock();
                idProducto = "";
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccione un PRODUCTO");

        }
    }//GEN-LAST:event_productoseliminarbtnActionPerformed
    // </editor-fold>

    //  <editor-fold defaultstate="collapsed" desc="Sucursal">
    private void sucursalagregarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sucursalagregarbtnActionPerformed
        Sucursal SU = new Sucursal("",false);
        SU.setVisible(true);
    }//GEN-LAST:event_sucursalagregarbtnActionPerformed

    private void sucursaleditarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sucursaleditarbtnActionPerformed
        if(idSucursal != "")
        {
            Sucursal SU = new Sucursal(idSucursal,true);
            SU.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccione una SUCURSAL");

        }
    }//GEN-LAST:event_sucursaleditarbtnActionPerformed

    private void DataSucursalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataSucursalMouseClicked
        int fila = DataSucursal.rowAtPoint(evt.getPoint());
        
         idSucursal = modeloSu.getValueAt(fila,0).toString();
    }//GEN-LAST:event_DataSucursalMouseClicked

    private void sucursaleliminarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sucursaleliminarbtnActionPerformed
        if(idSucursal != "")
        {
            int resp = JOptionPane.showConfirmDialog(null, "¿Quiere eliminar esta SUCURSAL con identificador " + idSucursal + "?");
            if(resp == 0)
            {
                Nexo.ejecutaSQL("DELETE FROM empleado.Inventario WHERE idSucursal = "+ idSucursal,false);
                Nexo.ejecutaSQL("DELETE FROM empleado.Sucursal WHERE idSucursal = "+ idSucursal,false);
                ActualizagridSucursal();
                ActualizagridSucursalStock();
                ActualizagridStock();
                idSucursal = "";
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccione un SUCURSAL");

        }
    }//GEN-LAST:event_sucursaleliminarbtnActionPerformed
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Categoria">
    private void categoriaagregarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaagregarbtnActionPerformed
        Categoria CA = new Categoria("",false);
        CA.setVisible(true);
    }//GEN-LAST:event_categoriaagregarbtnActionPerformed

    private void categoriaeditarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaeditarbtnActionPerformed
        if(idCategoria != "")
        {
            Categoria CA = new Categoria(idCategoria,true);
            CA.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccione una CATEGORIA");

        }
    }//GEN-LAST:event_categoriaeditarbtnActionPerformed

    private void DataCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataCategoriaMouseClicked
        int fila = DataCategoria.rowAtPoint(evt.getPoint());
        
         idCategoria = modeloCa.getValueAt(fila,0).toString();
    }//GEN-LAST:event_DataCategoriaMouseClicked

    private void categoriaeliminarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaeliminarbtnActionPerformed
       if(idCategoria != "")
        {
            int resp = JOptionPane.showConfirmDialog(null, "¿Quiere eliminar esta CATEGORIA con identificador " + idCategoria + "?");
            if(resp == 0)
            {
                Nexo.ejecutaSQL("DELETE FROM empleado.Categoria WHERE idCategoria = "+ idCategoria,false);
                ActualizagridCategoria();
                idCategoria = "";
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccione un Categoria");

        }
    }//GEN-LAST:event_categoriaeliminarbtnActionPerformed
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inventario">
    private void inventarioagregarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventarioagregarbtnActionPerformed
        JOptionPane.showMessageDialog(null,"Seleccione la sucursal donde llegara la carga");
        SeleccionaSucursal selecsu = new SeleccionaSucursal(false);
        selecsu.setVisible(true);
    }//GEN-LAST:event_inventarioagregarbtnActionPerformed

    private void DataInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataInventarioMouseClicked
        int fila = DataInventario.rowAtPoint(evt.getPoint());
        
        idInventario = modeloI.getValueAt(fila,0).toString();
    }//GEN-LAST:event_DataInventarioMouseClicked

    private void inventariobuscarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventariobuscarbtnActionPerformed
        if(idInventario != "")
        {
           ConsultaDetalleInventario CDI = new ConsultaDetalleInventario(idInventario);
           CDI.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccione un Inventario");

        }
        
        
    }//GEN-LAST:event_inventariobuscarbtnActionPerformed

    private void inventarioeliminarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventarioeliminarbtnActionPerformed
        if(idInventario != "")
        {
            int resp = JOptionPane.showConfirmDialog(null, "¿Quiere eliminar este INVENTARIO con identificador " + idInventario + "?");
            if(resp == 0)
            {
                Nexo.ejecutaSQL("DELETE FROM empleado.InventarioProducto WHERE idInventario = "+idInventario,false);
                Nexo.ejecutaSQL("DELETE FROM empleado.Inventario WHERE idInventario = "+idInventario,false);
                ActualizagridInventario();
                idInventario = "";
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccione un INVENTARIO");

        }
    }//GEN-LAST:event_inventarioeliminarbtnActionPerformed
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Búsquedas">

    private void ventastxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ventastxtKeyReleased
        modeloV = new DefaultTableModel(); // para diseno de la tabla 
        modeloV.addColumn("idVenta");
        modeloV.addColumn("Cliente");
        modeloV.addColumn("Total");
        modeloV.addColumn("Descuento");
        modeloV.addColumn("Fecha");
        
        try{
            String Qery = "select v.idVenta, c.nombreCliente as cliente, v.montoTotal, c.descuento, v.fechaVenta  from empleado.Venta v inner join empleado.Cliente c on v.idCliente = c.idCliente WHERE c.nombreCliente LIKE '%"+ventastxt.getText()+"%'";
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
           DataVenta.setModel(modeloV);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_ventastxtKeyReleased

    private void clientestxt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clientestxt1KeyReleased
        modeloC = new DefaultTableModel(); // para diseno de la tabla 
        modeloC.addColumn("idCliente");
        modeloC.addColumn("Nombre");
        modeloC.addColumn("Telefono");
        modeloC.addColumn("Tipo cliente");
        modeloC.addColumn("Descuento");

        
        try{
            String Qery = "SELECT * FROM empleado.Cliente WHERE NombreCliente  LIKE '%"+ clientestxt1.getText() +"%'";
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[5];// las tres columnas
            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                Aux[4] = rt.getString(5);
                modeloC.addRow(Aux);
            }
           DataClientes.setModel(modeloC);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_clientestxt1KeyReleased

    private void productostxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productostxtKeyReleased
        modeloP = new DefaultTableModel(); // para diseno de la tabla 
        modeloP.addColumn("idProducto");
        modeloP.addColumn("idCategoria");
        modeloP.addColumn("Precio");
        modeloP.addColumn("Sabor");

        
        try{
            String Qery = "SELECT * FROM empleado.Producto WHERE sabor LIKE '%"+ productostxt.getText() +"%'";
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[4];// las tres columnas
            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                modeloP.addRow(Aux);
            }
           DataProductos.setModel(modeloP);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_productostxtKeyReleased

    private void stockproductostxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stockproductostxtKeyReleased
        modeloSP = new DefaultTableModel(); // para diseno de la tabla 
        modeloSP.addColumn("idProducto");
        modeloSP.addColumn("Sabor");
        modeloSP.addColumn("Categoria");
        modeloSP.addColumn("Tamaño");


        try{
            String Qery = "select idProducto, sabor, nombreCategoria, tamano from empleado.Producto p inner join empleado.Categoria c on p.idCategoria = c.idCategoria WHERE sabor LIKE '%"+ stockproductostxt.getText() +"%'";
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[4];// las tres columnas
            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                modeloSP.addRow(Aux);
            }
           DataProductosstock.setModel(modeloSP);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_stockproductostxtKeyReleased

    private void stocksocursalestxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stocksocursalestxtKeyReleased
        modeloSS = new DefaultTableModel(); // para diseno de la tabla
        modeloSS.addColumn("ID");
        modeloSS.addColumn("Dirección");
        modeloSS.addColumn("Telefono");
        modeloSS.addColumn("Horario");
        
        try{
            String Qery = "SELECT * FROM empleado.Sucursal WHERE Direccion LIKE '%"+ stocksocursalestxt.getText() +"%'";
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[4];

            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                modeloSS.addRow(Aux);
            }
            DataSucursalesstock.setModel(modeloSS);
        }
        catch(Exception e){
        
        }
    }//GEN-LAST:event_stocksocursalestxtKeyReleased

    private void sucursaltxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sucursaltxtKeyReleased
        modeloSu = new DefaultTableModel(); // para diseno de la tabla
        modeloSu.addColumn("ID");
        modeloSu.addColumn("Dirección");
        modeloSu.addColumn("Telefono");
        modeloSu.addColumn("Horario");
        
        try{
            String Qery = "SELECT * FROM empleado.Sucursal WHERE Direccion LIKE '%"+ sucursaltxt.getText() +"%'";
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[4];

            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                modeloSu.addRow(Aux);
            }
            DataSucursal.setModel(modeloSu);
        }
        catch(Exception e){
        
        }
    }//GEN-LAST:event_sucursaltxtKeyReleased

    private void categoriatxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_categoriatxtKeyReleased
        modeloCa = new DefaultTableModel(); // para diseno de la tabla
        modeloCa.addColumn("ID");
        modeloCa.addColumn("Nombre");
        modeloCa.addColumn("Tamaño");
        
        try{
            String Qery = "SELECT * FROM empleado.Categoria WHERE NombreCategoria LIKE '%"+ categoriatxt.getText() +"%'";
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[4];

            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                modeloCa.addRow(Aux);
            }
            DataCategoria.setModel(modeloCa);
        }
        catch(Exception e){
        
        }
    }//GEN-LAST:event_categoriatxtKeyReleased

    private void inventariotxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inventariotxtKeyReleased
        modeloI = new DefaultTableModel(); // para diseno de la tabla
        modeloI.addColumn("idInventario");
        modeloI.addColumn("Dirección Socursal");
        modeloI.addColumn("Total de productos recibidos");
        modeloI.addColumn("Fecha de recepción");
        
        try{
            String Qery = "select  distinct(i.idInventario), s.direccion, SUM(p.cantidadRecibida) as total, i.fechaRecepcion from empleado.Inventario i inner join empleado.InventarioProducto p on i.idInventario = p.idInventario inner join empleado.Sucursal s on s.idSucursal = i.idSucursal WHERE s.direccion LIKE '%"+ inventariotxt.getText() +"%' group by i.idInventario, s.direccion,i.fechaRecepcion ";
            st = Conexion.createStatement();
            rt = st.executeQuery(Qery);
            String Aux[] = new String[4];

            while(rt.next()){
                Aux[0] = rt.getString(1);
                Aux[1] = rt.getString(2);
                Aux[2] = rt.getString(3);
                Aux[3] = rt.getString(4);
                modeloI.addRow(Aux);
            }
            DataInventario.setModel(modeloI);
        }
        catch(Exception e){
        
        }
    }//GEN-LAST:event_inventariotxtKeyReleased
    
    // </editor-fold>
    
    
    
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
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventario(usuario, contra).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Categoria;
    private javax.swing.JPanel Clientes;
    public static javax.swing.JTable DataCategoria;
    public static javax.swing.JTable DataClientes;
    public static javax.swing.JTable DataInventario;
    public static javax.swing.JTable DataProductos;
    public static javax.swing.JTable DataProductosstock;
    public static javax.swing.JTable DataStock;
    public static javax.swing.JTable DataSucursal;
    public static javax.swing.JTable DataSucursalesstock;
    public static javax.swing.JTable DataVenta;
    public static javax.swing.JTabbedPane Inventario;
    private javax.swing.JPanel InventarioP;
    private javax.swing.JPanel Productos;
    private javax.swing.JPanel Stock;
    private javax.swing.JPanel Sucursal;
    private javax.swing.JPanel Ventas;
    private javax.swing.JToolBar categoriaToolBar;
    private javax.swing.JButton categoriaagregarbtn;
    private javax.swing.JButton categoriaeditarbtn;
    private javax.swing.JButton categoriaeliminarbtn;
    private javax.swing.JTextField categoriatxt;
    private javax.swing.JToolBar clientesToolBar1;
    private javax.swing.JButton clientesagregarbtn;
    private javax.swing.JButton clienteseditarbtn;
    private javax.swing.JButton clienteseliminarbtn;
    private javax.swing.JTextField clientestxt1;
    private javax.swing.JToolBar inventarioToolBar;
    private javax.swing.JButton inventarioagregarbtn;
    private javax.swing.JButton inventariobuscarbtn;
    private javax.swing.JButton inventarioeliminarbtn;
    private javax.swing.JTextField inventariotxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JToolBar productosToolBar;
    private javax.swing.JButton productosagregarbtn;
    private javax.swing.JButton productoseditarbtn;
    private javax.swing.JButton productoseliminarbtn;
    private javax.swing.JTextField productostxt;
    private javax.swing.JTextField stockproductostxt;
    private javax.swing.JTextField stocksocursalestxt;
    private javax.swing.JToolBar sucursalToolBar;
    private javax.swing.JButton sucursalagregarbtn;
    private javax.swing.JButton sucursaleditarbtn;
    private javax.swing.JButton sucursaleliminarbtn;
    private javax.swing.JTextField sucursaltxt;
    private javax.swing.JToolBar ventasToolBar;
    private javax.swing.JButton ventasagregarbtn;
    private javax.swing.JButton ventasbuscarbtn;
    private javax.swing.JButton ventaseliminarbtn;
    private javax.swing.JTextField ventastxt;
    // End of variables declaration//GEN-END:variables
}
