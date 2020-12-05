using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace PaleteriaInventario
{
    partial class Venta : Form
    {
        #region Variables de instancia
        private int idVenta;
        private int idStock;
        private int idCliente;
        private int idSucursal;        
        private int existencia;
        private float subTotal;
        private Nexo nexo;
        private SqlCommand comando;
        private AltaDetalle seleccionaProducto;
        #endregion

        #region Constructores
        public Venta(int idCliente, int idSucursal, Nexo nexo)
        {
            this.nexo = nexo;
            this.idCliente = idCliente;
            this.idSucursal = idSucursal;
            this.subTotal = -1;
            InitializeComponent();
        }

        private void Venta_Load(object sender, EventArgs e)
        {
            this.nexo.ejecutarSQL(new SqlCommand("insert into empleado.Venta(idCliente,montoTotal,fechaVenta) "+
                                                    "values("+this.idCliente+",0.0,getdate());"), false);
            this.idVenta = this.nexo.obtenUltimoId("empleado.Venta","idVenta");
            this.actualizaDataGrids();
        }
        #endregion

        #region Eventos
        private void detalles_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {
            switch (e.ClickedItem.AccessibleName)
            {

                case "Agregar":
                    this.seleccionaProducto = new AltaDetalle();
                    seleccionaProducto.actualizaDatagrid += this.cargaProductos;
                    if (this.seleccionaProducto.ShowDialog().Equals(DialogResult.OK))
                    {
                        this.idStock = this.seleccionaProducto.IdProducto;
                        this.existencia = this.seleccionaProducto.cantidad;
                        this.comando = new SqlCommand("insert into empleado.DetalleVenta(idVenta,idStock,unidades,subTotal) values(@idVenta,@idStock,@unidades,1);");
                        this.comando.Parameters.AddWithValue("@idVenta", this.idVenta);
                        this.comando.Parameters.AddWithValue("@idStock", this.idStock);
                        this.comando.Parameters.AddWithValue("@unidades", this.existencia);
                        this.nexo.ejecutarSQL(this.comando, false);

                    }
                break;
                case "Actualizar":
                    if (idStock != -1)
                    {
                        this.comando = new SqlCommand("update empleado.DetalleVenta " +
                                                      "set idStock = @idStock, unidades = @unidades " +
                                                      "where idVenta = @idVenta and idStock = @oldIdStock and unidades = @oldUnidades");
                        this.comando.Parameters.AddWithValue("@idVenta", this.idVenta);
                        this.comando.Parameters.AddWithValue("@oldIdStock", this.idStock);
                        this.comando.Parameters.AddWithValue("@oldUnidades", this.existencia);
                        this.comando.Parameters.AddWithValue("@oldSubTotal", this.subTotal);
                        if (this.seleccionaProducto.ShowDialog().Equals(DialogResult.OK))
                        {
                            this.idStock = this.seleccionaProducto.IdProducto;
                            this.existencia = this.seleccionaProducto.cantidad;
                            this.comando.Parameters.AddWithValue("@idStock", this.idStock);
                            this.comando.Parameters.AddWithValue("@unidades", this.existencia);
                            this.nexo.ejecutarSQL(this.comando, true);
                        }
                    }
                break;
                case "Eliminar":
                    if (idStock != -1)
                    {
                        if (MessageBox.Show("¿Quiere borrar este registro?", "Advertencia", MessageBoxButtons.YesNo).Equals(DialogResult.Yes))
                        {
                            this.comando = new SqlCommand("delete  from empleado.DetalleVenta "+
                                "where idVenta = @idVenta and idStock = @idStock and unidades = @unidades");
                            this.comando.Parameters.AddWithValue("@idVenta", this.idVenta);
                            this.comando.Parameters.AddWithValue("@idStock", this.idStock);
                            this.comando.Parameters.AddWithValue("@unidades", this.existencia);
                            this.nexo.ejecutarSQL(this.comando, false);
                        }
                    }
                break;
            }
            this.actualizaDataGrids();
            this.idStock = -1;
            this.existencia = -1;
        }
        #endregion

        #region Metodos
        private void cargaProductos(DataGridView dataGrid)
        {
            this.nexo.actualizaGrid(dataGrid,
                "select s.idStock, p.sabor, c.nombreCategoria as categoria, c.tamaño, s.existencias from empleado.Producto p " +
                "inner join empleado.Categoria c on p.idCategoria = c.idCategoria " +
                "inner join empleado.Stock s on s.idProducto = p.idProducto " +
                "where s.idSucursal = " + this.idSucursal + "and s.existencias !=0 ", "Producto");
        }

        private void actualizaDataGrids()
        {
            this.nexo.actualizaGrid(this.dataGridViewVenta,
                                    "select v.idVenta, c.nombreCliente as cliente, v.montoTotal, c.descuento, v.fechaVenta  " +
                                    "from empleado.Venta v " +
                                    "inner join empleado.Cliente c on v.idCliente = c.idCliente " +
                                    "where idVenta = " + this.idVenta, "Venta");
            this.nexo.actualizaGrid(this.dataGridViewDetalles,
                                    "select d.idVenta, s.idStock, p.sabor, c.nombreCategoria as categoria, d.unidades, p.precio, d.subTotal from empleado.DetalleVenta d " +
                                    "inner join empleado.Stock s on s.idStock = d.idStock " +
                                    "inner join empleado.Producto p on p.idProducto = s.idProducto " +
                                    "inner join empleado.Categoria c on c.idCategoria = p.idCategoria " +
                                    "where idVenta = " + this.idVenta, "DetalleVenta");
        }
        #endregion

        private void dataGridViewDetalles_CellClick(object sender, DataGridViewCellEventArgs e)
        {

            this.idStock = int.Parse(this.dataGridViewDetalles.CurrentRow.Cells[1].Value.ToString());
            this.existencia = int.Parse(this.dataGridViewDetalles.CurrentRow.Cells[4].Value.ToString());
            this.subTotal = float.Parse(this.dataGridViewDetalles.CurrentRow.Cells[5].Value.ToString());
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.Cancel;
            this.comando = new SqlCommand("delete from empleado.Venta where idVenta = "+this.idVenta);
            this.nexo.ejecutarSQL(this.comando, false);
        }
    }
}
