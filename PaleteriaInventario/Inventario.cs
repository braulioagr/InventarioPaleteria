using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PaleteriaInventario
{
    public partial class Inventario : Form
    {
        #region Variables de Instancia

        private int idCliente;
        private int idCategoria;
        private int idVenta;
        private int idInventario;
        private string mensaje;
        private Nexo nexo;
        private ConsultaDetalleVenta consulta;
        private AltaCliente alta;
        private ModificaCliente modifica;
        private SqlCommand comando;
        private Categoria categoria;
        private ConsultaDetalleInventario consultaDetalleInventario;
        private SeleccionaSucursal seleccionaSucursal;
        private Restock restock;

        #endregion

        #region Constructores

        public Inventario()
        {
            InitializeComponent();
        }

        private void Inventario_Load(object sender, EventArgs e)
        {
            //Creamos el nexo
            this.idCliente = -1;
            this.idCategoria = -1;
            this.idInventario = -1;
            this.nexo = new Nexo();
            this.actualizaDataGrids();
        }

        private void actualizaDataGrids()
        {
            //Actualizamos el datagrid de Cliente
            this.nexo.actualizaGrid(this.dataGridViewCliente, "select * from empleado.Cliente", "Cliente");
            //Actualizamos el datagrid de Categoria
            this.nexo.actualizaGrid(this.dataGridViewCategoria, "select * from empleado.Categoria", "Categoria");
            //Actualizadmos el datagrid de Inventario
            this.nexo.actualizaGrid(this.dataGridViewInventario,
                "select  distinct(i.idInventario), s.direccion, SUM(p.cantidadRecibida) as total, i.fechaRecepcion from empleado.Inventario i " +
                "inner join empleado.InventarioProducto p on i.idInventario = p.idInventario " +
                "inner join empleado.Sucursal s on s.idSucursal = i.idSucursal " +
                "group by i.idInventario, s.direccion,i.fechaRecepcion", "Inventario");
            //Actualizadmos el datagrid de Producto
            this.nexo.actualizaGrid(this.dataGridViewProducto,
                "select p.idProducto,p.sabor, c.nombreCategoria as categoria, c.tamaño, p.precio from empleado.Producto p " +
"               inner join empleado.Categoria c on c.idCategoria = p.idCategoria", "Producto");
            //Actualizadmos el datagrid de Stock
            this.seteaDataGridsStock();
            //Actualizamos el datagrid de Cliente
            this.nexo.actualizaGrid(this.dataGridViewSucursal, "select * from empleado.Sucursal", "Sucursal");
        }

        private void seteaDataGridsStock()
        {
            //Actualizamos los datagrids de Stock
            this.nexo.actualizaGrid(this.dataGridViewStockProductos, "select idProducto, sabor, nombreCategoria," +
                                                                     " tamaño from empleado.Producto p inner join " +
                                                                     "empleado.Categoria c on p.idCategoria = c.idCategoria", "Producto");
            this.nexo.actualizaGrid(this.dataGridViewStockSucursales, "select idSucursal, direccion from empleado.Sucursal", "Sucursal");
            this.nexo.actualizaGrid(this.dataGridViewStock, "select idStock, direccion as sucursal, sabor, precio, nombreCategoria as categoria, tamaño, existencias"+
                                                            " from empleado.Stock s"+
                                                            " inner join empleado.Sucursal su on su.idSucursal = s.idProducto"+
                                                            " inner join empleado.Producto p on p.idProducto = s.idProducto"+
                                                            " inner join empleado.Categoria c on p.idCategoria = c.idCategoria",
                                                            "Stock");
        }

        #endregion

        #region Eventos

        #region menus


        /** 
         * Detecta el item pulsado dentro de la toolBar de clientes para saber cual operacion debe ejecutar.
         * @param sender objeto el cual disparo este evento
         * @param e argumento propio del evento
         */
        private void toolStripClientes_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {
            //Obtenemos el AccesibleName del item pulsado para saber que acciones tolbar
            switch(e.ClickedItem.AccessibleName)
            {
                case "Agregar":
                    alta = new AltaCliente();
                    //Construimos el objeto y lo mandamos llamar como dialogo
                    if (alta.ShowDialog().Equals(DialogResult.OK))
                    {
                        //Si el usuario dio Ok damos de alta el usuario
                        this.nexo.ejecutarSQL(alta.Comando,true);
                        this.nexo.actualizaGrid(this.dataGridViewCliente, "select * from empleado.Cliente", "Cliente");
                    }
                break;
                case "Actualizar":
                    //Verificamos
                    if (this.idCliente != -1)
                    {
                        //Concatenamos todos los valores dentro del datagrid para mostrar el disclaimer en el Messagebox
                        mensaje = dataGridViewCliente.CurrentRow.Cells[0].Value.ToString() + " , " +
                            dataGridViewCliente.CurrentRow.Cells[1].Value.ToString() + " , " +
                            dataGridViewCliente.CurrentRow.Cells[2].Value.ToString() + " , " +
                            dataGridViewCliente.CurrentRow.Cells[3].Value.ToString() + " , " +
                            dataGridViewCliente.CurrentRow.Cells[4].Value.ToString();
                        if (MessageBox.Show("El registro seleccionado es: \n" + mensaje + "\n ¿Es este el que desea modificar?",
                                            "Confirmacion", MessageBoxButtons.YesNo,MessageBoxIcon.Question).Equals(DialogResult.Yes))
                        {
                            mensaje = mensaje.Replace(" ", "");
                            //Construimos el objeto y lo mandamos llamar como dialogo y dividimos la cadena con los datos para rellenar el valor del datagrid
                            modifica = new ModificaCliente(mensaje.Split(','));
                            if (modifica.ShowDialog().Equals(DialogResult.OK))
                            {
                                //Si el usuario dio Ok damos de alta el usuario
                                this.nexo.ejecutarSQL(modifica.Comando,true);
                                this.nexo.actualizaGrid(this.dataGridViewCliente, "select * from empleado.Cliente", "Cliente");
                            }
                        }
                    }
                    break;
                case "Eliminar":
                    if (this.idCliente != -1)
                    {
                        mensaje = dataGridViewCliente.CurrentRow.Cells[0].Value.ToString() + " , " +
                            dataGridViewCliente.CurrentRow.Cells[1].Value.ToString() + " , " +
                            dataGridViewCliente.CurrentRow.Cells[2].Value.ToString() + " , " +
                            dataGridViewCliente.CurrentRow.Cells[3].Value.ToString() + " , " +
                            dataGridViewCliente.CurrentRow.Cells[4].Value.ToString();
                        if (MessageBox.Show("El registro seleccionado es: \n" + mensaje + "\n ¿Es este el que desea eliminar? (No se podran recuperar los datos)",
                                            "Confirmacion", MessageBoxButtons.YesNo, MessageBoxIcon.Question).Equals(DialogResult.Yes))
                        {
                            comando = new SqlCommand("delete from empleado.Cliente where idCliente = @id");
                            comando.Parameters.Add(new SqlParameter("@id", SqlDbType.BigInt));
                            comando.Parameters[0].Value = this.idCliente;
                            this.nexo.ejecutarSQL(comando,true);
                            this.nexo.actualizaGrid(this.dataGridViewCliente, "select * from empleado.Cliente", "Cliente");
                        }
                    }
                break;
            }
        }

        private void toolStripCategorias_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {

            //Obtenemos el AccesibleName del item pulsado para saber que acciones tolbar
            switch (e.ClickedItem.AccessibleName)
            {
                case "Agregar":
                    categoria = new Categoria();
                    //Construimos el objeto y lo mandamos llamar como dialogo
                    if (categoria.ShowDialog().Equals(DialogResult.OK))
                    {
                        //Si el usuario dio Ok damos de alta el usuario
                        this.nexo.ejecutarSQL(categoria.Comando,true);
                        this.nexo.actualizaGrid(this.dataGridViewCategoria, "select * from empleado.Categoria", "Categoria");
                    }
                break;
                case "Actualizar":
                    //Verificamos
                    if (this.idCategoria != -1)
                    {
                        //Concatenamos todos los valores dentro del datagrid para mostrar el disclaimer en el Messagebox
                        mensaje = this.dataGridViewCategoria.CurrentRow.Cells[0].Value.ToString() + " , " +
                            this.dataGridViewCategoria.CurrentRow.Cells[1].Value.ToString() + " , " +
                            this.dataGridViewCategoria.CurrentRow.Cells[2].Value.ToString();
                        if (MessageBox.Show("El registro seleccionado es: \n" + mensaje + "\n ¿Es este el que desea modificar?",
                                            "Confirmacion", MessageBoxButtons.YesNo, MessageBoxIcon.Question).Equals(DialogResult.Yes))
                        {
                            mensaje = mensaje.Replace(" ", "");
                            //Construimos el objeto y lo mandamos llamar como dialogo y dividimos la cadena con los datos para rellenar el valor del datagrid
                            categoria = new Categoria(mensaje.Split(','));
                            if (categoria.ShowDialog().Equals(DialogResult.OK))
                            {
                                //Si el usuario dio Ok damos de alta el usuario
                                this.nexo.ejecutarSQL(categoria.Comando,true);
                                this.nexo.actualizaGrid(this.dataGridViewCategoria, "select * from empleado.Categoria", "Categoria");
                            }
                        }
                    }
                    break;
                case "Eliminar":
                    if (this.idCategoria != -1)
                    {
                        //Concatenamos todos los valores dentro del datagrid para mostrar el disclaimer en el Messagebox
                        mensaje = this.dataGridViewCategoria.CurrentRow.Cells[0].Value.ToString() + " , " +
                            this.dataGridViewCategoria.CurrentRow.Cells[1].Value.ToString() + " , " +
                            this.dataGridViewCategoria.CurrentRow.Cells[2].Value.ToString();
                        if (MessageBox.Show("El registro seleccionado es: \n" + mensaje + "\n ¿Es este el que desea eliminar? (No se podran recuperar los datos)",
                                            "Confirmacion", MessageBoxButtons.YesNo, MessageBoxIcon.Question).Equals(DialogResult.Yes))
                        {
                            comando = new SqlCommand("delete from empleado.Categoria where idCategoria = " + this.idCategoria.ToString());
                            this.nexo.ejecutarSQL(comando,true);
                            this.nexo.actualizaGrid(this.dataGridViewCategoria, "select * from empleado.Categoria", "Categoria");
                        }
                    }
                    break;
            }
        }


        private void toolStripInventario_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {
            switch(e.ClickedItem.AccessibleName)
            {
                case "Agregar":
                    this.seleccionaSucursal = new SeleccionaSucursal(this.nexo);
                    if (this.seleccionaSucursal.ShowDialog().Equals(DialogResult.OK))
                    {
                        this.restock = new Restock(this.nexo, seleccionaSucursal.ID);
                        if (this.restock.ShowDialog().Equals(DialogResult.OK))
                        {
                            this.nexo.actualizaGrid(this.dataGridViewInventario,
                                "select  distinct(i.idInventario), s.direccion, SUM(p.cantidadRecibida) as total, i.fechaRecepcion from empleado.Inventario i " +
                                "inner join empleado.InventarioProducto p on i.idInventario = p.idInventario " +
                                "inner join empleado.Sucursal s on s.idSucursal = i.idSucursal " +
                                "group by i.idInventario, s.direccion,i.fechaRecepcion", "Inventario");
                        }
                    }
                    this.seleccionaSucursal.Dispose();
                break;
                case "Inspeccion":
                    if(this.idInventario != -1)
                    {
                        consultaDetalleInventario = new ConsultaDetalleInventario(this.idInventario, this.nexo);
                        consultaDetalleInventario.ShowDialog();
                        consultaDetalleInventario.Dispose();
                    }
                break;
            }
        }


        private void ToolStripProducto_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {
            //Obtenemos el AccesibleName del item pulsado para saber que acciones tolbar
            switch (e.ClickedItem.AccessibleName)
            {
                case "Agregar":
                    this.seleccionaSucursal = new SeleccionaSucursal(this.nexo);
                    if (this.seleccionaSucursal.ShowDialog().Equals(DialogResult.OK))
                    {

                    }
                break;
                case "Actualizar":
                break;
                case "Eliminar":
                break;
            }
            this.nexo.actualizaGrid(this.dataGridViewInventario,
                "select  distinct(i.idInventario), s.direccion, SUM(p.cantidadRecibida) as total, i.fechaRecepcion from empleado.Inventario i " +
                "inner join empleado.InventarioProducto p on i.idInventario = p.idInventario " +
                "inner join empleado.Sucursal s on s.idSucursal = i.idSucursal " +
                "group by i.idInventario, s.direccion,i.fechaRecepcion", "Inventario");
        }


        private void toolStripSucursal_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {
            //Obtenemos el AccesibleName del item pulsado para saber que acciones tolbar
            switch (e.ClickedItem.AccessibleName)
            {
                case "Agregar":
                break;
                case "Actualizar":
                break;
                case "Eliminar":
                break;
            }
        }

        #endregion

        #region Tamaño

        /** 
         * Redimenciona los objetos dentro de la form segun unas constantes que relacionan el tamaño de los controles
         * con el tamaño de la form.
         * @param sender objeto el cual disparo este evento
         * @param e argumento propio del evento
         */
        private void Inventario_Resize(object sender, EventArgs e)
        {
            Size size;
            Point point;
            //Creamos un nuevo Size restandole la diferencia de tamaño que tiene la form con el tab control y los asignamos
            size = new Size(this.Size.Width - 40, this.Size.Height - 63);
            this.tabControl.Size = size;
            //Creamos un nuevo Size restandole la diferencia de tamaño que tiene la form con todos los DataGridView y los asignamos
            size = new Size(this.Size.Width - 60, this.Size.Height - 183);
            this.dataGridViewCliente.Size = size;
            this.dataGridViewCategoria.Size = size;
            this.dataGridViewInventario.Size = size;
            this.dataGridViewSucursal.Size = size;
            this.dataGridViewProducto.Size = size;
            //Aqui poner todos los size de los datagrid todos guardan la misma relacion

            //Creamos un nuevo point restandole la diferencia de tamaño que tiene la form con todos los DataGridView y los asignamos
            point = new Point(this.Size.Width- 218, this.textBoxNombreCliente.Location.Y);
            this.textBoxNombreCliente.Location = point;
            this.textBoxCategoria.Location = point;
            this.textBoxInventario.Location = point;
            this.textBoxProducto.Location = point;
            this.textBoxSucursal.Location = point;
            //Aqui poner todos los location de los botones de busqueda todos guardan la misma relacion

            //Creamos un nuevo point restandole la diferencia de tamaño que tiene la form con todos los label de los textBox de busqueda y los asignamos
            point = new Point(this.textBoxNombreCliente.Location.X-3, this.textBoxNombreCliente.Location.Y-16);
            this.label1.Location = point;
            this.label2.Location = point;
            this.label6.Location = point;
            this.label7.Location = point;
            this.label8.Location = point;
            //Aqui poner todos los location de los label de busqueda todos guardan la misma relacion
        }
        
        #endregion

        #region TextBox

        /** 
         * Detecta cuando se levanta una tecla en algun textbox de busqueda para asi mandar hacer una busqueda de coincidencias en la BD
         * @param sender objeto el cual disparo este evento
         * @param e argumento propio del evento
         */
        private void busqueda_keyUp(object sender, KeyEventArgs e)
        {
            string nombre;
            string tabla;
            DataGridView dataGrid;
            nombre = "";
            dataGrid = null;
            //Obtenemos el accesible name del textbox para saber en cual tabla vamos a realizar la busqueda
            tabla = ((TextBox)sender).AccessibleName;
            switch (tabla)
            {
                case "Cliente":
                    //Asignamos el valor de la busqeda del textbox Cliente al nombre y el datagrid al datagrid del cliente
                    //Ya que en esta tabla vamos a trabajar
                    dataGrid = this.dataGridViewCliente;
                    nombre = this.textBoxNombreCliente.Text;
                    this.nexo.actualizaGrid(dataGrid, "select * from empleado." + tabla + " where nombreCliente like '" + nombre + "%';", tabla);
                    break;
                case "Categoria":
                    //Asignamos el valor de la busqeda del textbox Cliente al nombre y el datagrid al datagrid del cliente
                    //Ya que en esta tabla vamos a trabajar
                    dataGrid = this.dataGridViewCategoria;
                    nombre = this.textBoxCategoria.Text;
                    this.nexo.actualizaGrid(dataGrid, "select * from empleado." + tabla + " where nombreCategoria like '" + nombre + "%';", tabla);
                break;
                case "Inventario":
                    //Asignamos el valor de la busqeda del textbox Cliente al nombre y el datagrid al datagrid del cliente
                    //Ya que en esta tabla vamos a trabajar
                    dataGrid = this.dataGridViewInventario;
                    nombre = this.textBoxInventario.Text;
                    this.nexo.actualizaGrid(dataGrid, "select  distinct(i.idInventario), s.direccion, SUM(p.cantidadRecibida) as total, i.fechaRecepcion from empleado.Inventario i " +
                                                    "inner join empleado.InventarioProducto p on i.idInventario = p.idInventario " +
                                                    "inner join empleado.Sucursal s on s.idSucursal = i.idSucursal " +
                                                    " where s.direccion like '" + nombre + "%' " +
                                                    "group by i.idInventario, s.direccion,i.fechaRecepcion;", tabla);
                    break;
            }
        }


        private void textBoxSucursalesStock_KeyUp(object sender, KeyEventArgs e)
        {

            this.nexo.actualizaGrid(this.dataGridViewStockSucursales, "select * from empleado.Sucursal where direccion like '"+((TextBox)sender).Text+"%';", "Sucursal");
        }

        private void textBoxProductosStock_KeyUp(object sender, KeyEventArgs e)
        {
            this.nexo.actualizaGrid(this.dataGridViewStockProductos, "select idProducto, sabor, nombreCategoria," +
                                                                     " tamaño from empleado.Producto p inner join" +
                                                                     " empleado.Categoria c on p.idCategoria = c.idCategoria"+
                                                                     " where sabor like '" + ((TextBox)sender).Text + "%';", "Sucursal");
        }
        #endregion

        #region DataGrid

        private void dataGridViewCliente_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            this.idCliente = int.Parse(dataGridViewCliente.CurrentRow.Cells[0].Value.ToString());
        }

        private void dataGridViewCategoria_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            this.idCategoria = int.Parse(dataGridViewCategoria.CurrentRow.Cells[0].Value.ToString());
        }
        private void dataGridViewInventario_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            this.idInventario = int.Parse(dataGridViewInventario.CurrentRow.Cells[0].Value.ToString());
        }

        private void dataGridViewStockSucursales_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            string id;
            id = ((DataGridView)sender).CurrentRow.Cells[0].Value.ToString();
            this.nexo.actualizaGrid(this.dataGridViewStock, "select idStock, sabor, precio, existencias, nombreCategoria as categoria, tamaño" +
                                           " from empleado.Stock s" +
                                           " inner join empleado.Sucursal su on su.idSucursal = s.idSucursal" +
                                           " inner join empleado.Producto p on p.idProducto = s.idProducto" +
                                           " inner join empleado.Categoria c on p.idCategoria = c.idCategoria" +
                                           " where su.idSucursal = "+ id, "Stock");
        }

        private void dataGridViewStockProductos_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            string id;
            id = ((DataGridView)sender).CurrentRow.Cells[0].Value.ToString();
            this.nexo.actualizaGrid(this.dataGridViewStock, "select idStock, sabor, precio, existencias, nombreCategoria as categoria, tamaño, su.direccion" +
                                           " from empleado.Stock s" +
                                           " inner join empleado.Sucursal su on su.idSucursal = s.idSucursal" +
                                           " inner join empleado.Producto p on p.idProducto = s.idProducto" +
                                           " inner join empleado.Categoria c on p.idCategoria = c.idCategoria" +
                                           " where p.idProducto = " + id, "Stock");
        }

        #endregion

        #endregion

    }
}
